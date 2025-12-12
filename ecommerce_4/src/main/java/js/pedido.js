
document.addEventListener('DOMContentLoaded', () => {
    if (document.querySelector('.lista-pedidos')) {
        cargarPedidos();
    }
    
    if (document.querySelector('.pedido-container')) {
        cargarDetallesPedido();
    }
});

async function cargarPedidos() {
    const pathParts = window.location.pathname.split('/').filter(p => p);
    const contextPath = pathParts.length > 0 && pathParts[0] !== 'api' ? pathParts[0] : '';
    const url = contextPath ? `/${contextPath}/api/pedidos` : '/api/pedidos';
    
    try {
        const respuesta = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include'
        });
        
        const pedidos = await respuesta.json();
        mostrarPedidos(pedidos);
        
    } catch (error) {
        console.error('Error al cargar pedidos:', error);
    }
}

function mostrarPedidos(pedidos) {
    const listaPedidos = document.querySelector('.lista-pedidos');
   
    listaPedidos.innerHTML = '';
    
    if (!pedidos || pedidos.length === 0) {
        listaPedidos.innerHTML = '<p>No tiene pedidos realizados aún.</p>';
        return;
    }
    
    pedidos.forEach((pedido, i) => {
        const pedidoDiv = crearPedidoIndividual(pedido, i);
        listaPedidos.appendChild(pedidoDiv);
    });
}

function crearPedidoIndividual(pedido, i) {
    const div = document.createElement('div');
    const fecha = formatearFecha(pedido.fecha);
    const total = formatearPrecio(pedido.total);
    
    div.innerHTML = `
        <div class="pedido-info">
            <ul>
                <li><span>Pedido:</span> #${pedido.numeroUnico}</li>
                <li><span>Realizado:</span> ${fecha}</li>
                <li><span>Estado:</span> ${pedido.estado}</li>
                <li><span>Total:</span> ${total}</li>
            </ul>
        </div>
        <div class="pedido-btn">
            <a href="./detalles-pedido.jsp?id=${pedido.id}" class="ver-detalles-btn">Detalles</a>
        </div>
    `;
    
    return div;
}

async function cargarDetallesPedido() {
    const urlParams = new URLSearchParams(window.location.search);
    const idPedido = urlParams.get('id');
    
    const pathParts = window.location.pathname.split('/').filter(p => p);
    const contextPath = pathParts.length > 0 && pathParts[0] !== 'api' ? pathParts[0] : '';
    const url = contextPath ? `/${contextPath}/api/pedidos/${idPedido}` : `/api/pedidos/${idPedido}`;
    
    try {
        const respuesta = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include'
        });
        
        const pedido = await respuesta.json();
        mostrarDetallesPedido(pedido);
        
    } catch (error) {
        console.error('Error al cargar detalles del pedido:', error);
    }
}

function mostrarDetallesPedido(pedido) {
    const container = document.querySelector('.pedido-container');
    const infoPedido = container.querySelector('.info-pedido');
    
    if (infoPedido) {
        infoPedido.innerHTML = `
            <p><span>Número de pedido:</span> ${pedido.numeroUnico}</p>
            <p><span>Fecha del pedido:</span> ${fecha}</p>
            <p><span>Estado:</span> ${estado}</p>
            <p><span>Método de pago:</span> ${pedido.metodoPago}</p>
            <p><span>Total del pedido:</span> ${total} MXN</p>
        `;
    }
    
    mostrarProductosPedido(pedido.productosPedido, container);
}

function mostrarProductosPedido(productos, container) {
    const productosContainer = container.querySelector('.productos-container');
    
    productosContainer.innerHTML = '';
    
    productos.forEach((producto) => {
        const productoDiv = crearElementoProducto(producto);
        productosContainer.appendChild(productoDiv);
    });
}

function crearElementoProducto(producto) {
    const div = document.createElement('div');
    div.className = 'producto-pedido-item';
    
    const productoDTO = producto.producto;
    const libro = productoDTO?.libro;
    const tituloLibro = libro?.titulo || 'Libro sin título';
    
    const autor = libro?.autor;
    let nombreAutor = '';
    if (autor) {
        const partesNombre = [];
        if (autor.nombre) partesNombre.push(autor.nombre);
        if (autor.apellidoPaterno) partesNombre.push(autor.apellidoPaterno);
        if (autor.apellidoMaterno) partesNombre.push(autor.apellidoMaterno);
        nombreAutor = partesNombre.length > 0 ? partesNombre.join(' ') : 'Autor desconocido';
    }
    
    const urlImagen = productoDTO?.urlImagen || './imgs/default-book.png';
    
    div.innerHTML = `
        <div class="libro-container">
            <img src="${urlImagen}" alt="${tituloLibro}">
        </div>
        <div class="info-producto-pedido">
            <h3 class="titulo-libro">${tituloLibro}</h3>
            <p class="autor-libro">${nombreAutor}</p>
            <p class="cantidad-producto">Cantidad: ${producto.cantidad || 1}</p>
            <p class="precio-producto">Precio unitario: ${formatearPrecio(producto.precioUnitario)}</p>
        </div>
    `;
    
    return div;
}

function formatearFecha(fecha) {
    const date = new Date(fecha);

    const dia = String(date.getDate()).padStart(2, '0');
    const mes = String(date.getMonth() + 1).padStart(2, '0');
    const año = date.getFullYear();
    return `${dia}/${mes}/${año}`;
}

function formatearPrecio(precio) {
    const num = Number(precio);
    return isNaN(num) ? '$0.00' : `$${num.toFixed(2)}`;
}