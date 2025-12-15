


async function cargarProductosDelCarrito() {
    try {

        const respuesta = await apiFetch('/api/carritos', 'GET');
        
        // Si se devuelve nulo.
        if (!respuesta) return; 

        cargarCarrito(datos);

    } catch (error) {
        
        //TODO manejar error
    }
}

function cargarCarrito(productos) {
    
    const contenedorProductos = document.getElementById('contenedor-productos');
    let totalCalculado = 0;
    
    // Variable para almacenar el html obtenido.
    let htmlAcumulado = ''; 

    if (productos.length === 0) {
        contenedor.innerHTML = "<p>Tu carrito está vacío</p>";
        actualizarTotales(0);
        return;
    }

    productos.forEach(prod => {
        totalCalculado += prod.precio * prod.cantidad;

        // Se suma el html a la variable.
        htmlAcumulado += `
            <div class="info-libro" data-id="${prod.idProducto}">
                </div>
        `;
    });

    // Se actualiza el contenedor.
    contenedor.innerHTML = htmlAcumulado;

    actualizarTotales(totalCalculado);
}

function actualizarTotales(monto) {
    const textoPrecio = `$${monto.toFixed(2)}`;
    document.getElementById('subtotal-desktop').innerText = textoPrecio;
    document.getElementById('subtotal-movil').innerText = textoPrecio;
}

// 
function eliminarProducto(id) {
    console.log("Eliminar producto ID:", id);
    // Aquí harías otro fetch DELETE a la API
}