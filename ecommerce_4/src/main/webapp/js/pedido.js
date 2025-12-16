
document.addEventListener('DOMContentLoaded', () => {
    if (document.querySelector('.lista-pedidos')) {
        cargarPedidos();
    }
    if (document.querySelector('.pedido-container')) {
        cargarDetallesPedido();
    }
});

async function cargarPedidos() {
    const base = window.location.pathname.split('/')[1];
    const url = base && base !== 'api' ? `/${base}/api/pedidos` : '/api/pedidos';
    
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
        listaPedidos.innerHTML = '<p>No tiene pedidos.</p>';
        return;
    }
    
    pedidos.forEach((pedido) => {
        const pedidoDiv = crearPedidoIndividual(pedido);
        listaPedidos.appendChild(pedidoDiv);
    });
}


function crearPedidoIndividual(pedido) {
    const div = document.createElement('div');
    const claseEstado = obtenerTipoEstado(pedido.estado);
    div.className = `pedido-item ${claseEstado}`;
    const fecha = formatearFecha(pedido.fecha);
    
    div.innerHTML = `
        <div class="pedido-info">
            <ul>
                <li><span>Pedido:</span> #${pedido.numeroUnico || pedido.id}</li>
                <li><span>Realizado:</span> ${fecha}</li>
                <li><span>Estado:</span> ${pedido.estado}</li>
                <li><span>Total:</span> ${formatearPrecio(pedido.total)}</li>
            </ul>
        </div>
        <div class="pedido-btn">
            <a href="./detalles-pedido.jsp?id=${pedido.id}" class="ver-detalles-btn">Detalles</a>
        </div>
    `;
    return div;
}

function obtenerTipoEstado(estado) {
    const estados = {
        'PENDIENTE': 'pedido-pendiente',
        'ENVIADO': 'pedido-enviado',
        'ENTREGADO': 'pedido-entregado',
        'CANCELADO': 'pedido-cancelado'
    };
    return estados[estado];
}

async function cargarDetallesPedido() {
    const urlParams = new URLSearchParams(window.location.search);
    const idPedido = urlParams.get('id');
    const base = window.location.pathname.split('/')[1];
    const url = base && base !== 'api' ? `/${base}/api/pedidos/${idPedido}`: `/api/pedidos/${idPedido}`;
    
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
    const fecha = formatearFecha(pedido.fecha);
    const metodoPago = formatearMetodoPago(pedido.metodoPago);
    const total = formatearPrecio(pedido.total);
    
    const infoPedido = container.querySelector('.info-pedido');
    if (infoPedido) {
        infoPedido.innerHTML = `
            <p><span>Número de pedido:</span> ${pedido.numeroUnico}</p>
            <p><span>Fecha del pedido:</span> ${fecha}</p>
            <p><span>Estado:</span> ${pedido.estado}</p>
            <p><span>Método de pago:</span> ${metodoPago}</p>
            <p><span>Total del pedido:</span> ${total} MXN</p>
        `;
    }
    mostrarProductosPedido(pedido.productosPedido, container);
}

function mostrarProductosPedido(productos, container) {
    const productosContainer = container.querySelector('.productos-container');
    
    productosContainer.innerHTML = '';
 
    productos.forEach((producto) => {
        const productoDiv = crearProductoIndividual(producto);
        productosContainer.appendChild(productoDiv);
    });
}

function crearProductoIndividual(producto) {
    const div = document.createElement('div');
    div.className = 'producto-pedido-item';
    
    const productoDTO = producto.producto;
    const libro = productoDTO.libro;
    const tituloLibro = libro.titulo;
    const autor = libro.autor;
    const nombreAutor = `${autor.nombre || ''} ${autor.apellidoPaterno || ''} ${autor.apellidoMaterno || ''}`.trim();
    const urlImagen = productoDTO.urlImagen;
    
    div.innerHTML = `
        <div class="libro-container">
            <img src="${urlImagen}" alt="${tituloLibro}">
        </div>
        <div class="info-producto-pedido">
            <h3 class="titulo-libro">${tituloLibro}</h3>
            <p class="autor-libro">${nombreAutor}</p>
            <p class="cantidad-producto">Cantidad: ${producto.cantidad}</p>
            <p class="precio-producto">Precio unitario: ${formatearPrecio(producto.precioUnitario)}</p>
        </div>
    `;
    return div;
}


function formatearFecha(fecha) {
    return fecha[2] + '/' + fecha[1] + '/' + fecha[0];
}

function formatearMetodoPago(metodoPago) {
    if (metodoPago.cuatroDigitos !== undefined || metodoPago.bancoEmisor !== undefined) {
        return 'Transferencia';
    }
    if (metodoPago.numero !== undefined || metodoPago.nombreTitular !== undefined) {
        return 'Tarjeta';
    }
    return 'Contra Entrega';
}

function formatearPrecio(precio) {
    const num = Number(precio);
    return `$${num.toFixed(2)}`;
}
