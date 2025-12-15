

document.addEventListener('DOMContentLoaded', () => {
    const ID_CLIENTE_ACTUAL = obtenerIdClienteActual();
    
    if (ID_CLIENTE_ACTUAL) {
        cargarProductosDelCarrito(ID_CLIENTE_ACTUAL);
    } else {
        const contenedor = document.getElementById('contenedor-productos');
        contenedor.innerHTML = "<p>Por favor, inicia sesión para ver tu carrito.</p>";
    }

    document.getElementById('contenedor-productos').addEventListener('click', (e) => {
        
        const target = e.target;
        
        if (target.classList.contains('btn-cantidad')) {
            const idProducto = parseInt(target.dataset.id);
            const accion = target.dataset.action;

            ajustarCantidadLocal(idProducto, accion); 
        }
        
        if (target.classList.contains('btn-actualizar')) {
            const idProducto = parseInt(target.dataset.id);
            actualizarProductoEnCarrito(idProducto);
        }
    });
});

function obtenerIdClienteActual() {
    const inputId = document.getElementById('clienteIdOculto');
    
    if (inputId && inputId.value) {
        return parseInt(inputId.value);
    }
    return null;
}


async function cargarProductosDelCarrito(idCliente) {
    try {
        const respuesta = await apiFetch(`carritos?idCliente=${idCliente}`, 'GET');
        
        const contenedor = document.getElementById('contenedor-productos');

        if (!respuesta || !respuesta.exito || !respuesta.data) {
            
            const mensajeError = respuesta && respuesta.mensaje ? respuesta.mensaje : "Error desconocido al cargar el carrito.";
            
            if (respuesta.exito === false && mensajeError.includes("No se encontró")) { 
                cargarCarrito([]);
            } else {
                
                contenedor.innerHTML = `<p class="error">Error: ${mensajeError}</p>`;
            }
            return; 
        }

        const carritoDTO = respuesta.data; 
        const productosDelCarrito = carritoDTO.productosCarrito || [];

        cargarCarrito(productosDelCarrito, carritoDTO.total); 

    } catch (error) {
        console.error('Error de conexión al cargar el carrito:', error);
        const contenedor = document.getElementById('contenedor-productos');
        contenedor.innerHTML = "<p class='error'>Error de conexión al cargar el carrito.</p>";
    }
}

function cargarCarrito(productos, totalCalculado = 0) {
    
    const contenedorProductos = document.getElementById('contenedor-productos');
    let htmlAcumulado = ''; 

    if (!productos || productos.length === 0) {
        contenedorProductos.innerHTML = '<p class="carrito-vacio-mensaje">Tu carrito está vacío.</p>';
        actualizarTotales(0);
        return;
    }

    productos.forEach(item => {
        
        const producto = item.producto;
        const libro = producto.libro;
        
        const idProducto = producto.id; 
        
        const precioUnitario = item.precioUnitario || 0; 
        const cantidad = item.cantidad || 0;
        
        const nombreProducto = libro.titulo || 'Título desconocido';
        const urlImagen = producto.urlImagen || '';
        const nombreAutor = `${libro.autor.nombre || ''} ${libro.autor.apellidoPaterno || ''}`;
        
        const subtotalProducto = (precioUnitario * cantidad).toFixed(2);
        
        htmlAcumulado += `
            <div class="libro-en-carro" data-id-producto="${idProducto}">
                <img src="${urlImagen}" alt="Portada de ${nombreProducto}" class="img-carro">
                <div class="detalle-libro">
                    <p class="titulo-libro">${nombreProducto}</p>
                    <p class="autor-libro">Autor: ${nombreAutor}</p> 
                    <p class="precio-unitario">Precio Unitario: $${precioUnitario.toFixed(2)}</p>
                </div>
                <div class="cantidad-control">
                    <button class="btn-cantidad" data-id="${idProducto}" data-action="restar">-</button>
                    <input type="number" value="${cantidad}" min="1" data-id="${idProducto}" class="input-cantidad"> 
                    <button class="btn-cantidad" data-id="${idProducto}" data-action="sumar">+</button>
                </div>
                <div class="acciones-producto">
                    <button class="btn-actualizar" data-id="${idProducto}">Actualizar</button>
                    <p class="precio-final">$${subtotalProducto}</p>
                    <button class="btn-eliminar" onclick="eliminarProducto(${idProducto})">X</button> 
                </div>
            </div>
            <hr/>
        `;
    });

    contenedorProductos.innerHTML = htmlAcumulado;
    actualizarTotales(totalCalculado); 
}

function actualizarTotales(monto) {
    const textoPrecio = `$${monto.toFixed(2)}`;
    
    const subtotalDesktop = document.getElementById('subtotal-desktop');
    const subtotalMovil = document.getElementById('subtotal-movil');
    
    if(subtotalDesktop) subtotalDesktop.innerText = textoPrecio;
    if(subtotalMovil) subtotalMovil.innerText = textoPrecio;
}

function ajustarCantidadLocal(idProducto, accion) {

    const inputCantidad = document.querySelector(`.input-cantidad[data-id="${idProducto}"]`);
    if (!inputCantidad) return;

    let cantidad = parseInt(inputCantidad.value);
    const min = parseInt(inputCantidad.min) || 1;

    if (accion === 'sumar') {
        cantidad += 1;
    } else if (accion === 'restar' && cantidad > min) {
        cantidad -= 1;
    } else {
        return; 
    }

    inputCantidad.value = cantidad;
    
}

async function actualizarProductoEnCarrito(idProducto) {
    const idCliente = obtenerIdClienteActual();
    const inputCantidad = document.querySelector(`.input-cantidad[data-id="${idProducto}"]`);
    
    if (!idCliente || !inputCantidad) {
        alert("Error: ID de cliente o producto no encontrado.");
        return;
    }
    
    const nuevaCantidad = parseInt(inputCantidad.value);

    if (isNaN(nuevaCantidad) || nuevaCantidad < 1) {
        alert("La cantidad debe ser un número válido mayor o igual a 1.");
        cargarProductosDelCarrito(idCliente); 
        return;
    }

    const productoActualizarDTO = {
        idProducto: idProducto,
        cantidad: nuevaCantidad
    };

    try {
        const respuesta = await apiFetch('productos-carrito', 'POST', productoActualizarDTO);
        
        if (respuesta && respuesta.exito) {
            cargarProductosDelCarrito(idCliente); 

        } else {
            alert(`Error al actualizar: ${respuesta.mensaje || 'Inténtalo de nuevo.'}`);
            cargarProductosDelCarrito(idCliente); 
        }

    } catch (error) {
        console.error("Error de conexión al actualizar producto:", error);
        alert("Error de conexión con el servidor. No se pudo actualizar el producto.");
        cargarProductosDelCarrito(idCliente); 
    }
}


async function eliminarProducto(idProducto) {
    const contenedor = document.getElementById('contenedor-productos');
    const idCliente = obtenerIdClienteActual(); 

    if (!confirm(`¿Estás seguro de que deseas eliminar este producto de tu carrito?`)) {
        return;
    }

    const productoEliminarDTO = {
        idProducto: idProducto
    };

    try {
        contenedor.innerHTML = '<p class="cargando">Eliminando producto...</p>';

        const respuesta = await apiFetch('productos-carrito', 'DELETE', productoEliminarDTO); 

        if (respuesta && respuesta.exito) {
            alert("Producto eliminado del carrito.");
            cargarProductosDelCarrito(idCliente); 

        } else {
            alert(`Error al eliminar: ${respuesta.mensaje || 'Inténtalo de nuevo.'}`);
            cargarProductosDelCarrito(idCliente); 
        }

    } catch (error) {
        console.error("Error de conexión al eliminar producto:", error);
        alert("Error de conexión con el servidor. No se pudo eliminar el producto.");
        cargarProductosDelCarrito(idCliente); 
    }
}