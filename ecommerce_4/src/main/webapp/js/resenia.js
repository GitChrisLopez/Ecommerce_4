document.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    const idProducto = urlParams.get('id');
    
    if (idProducto) {
        cargarDetalleProducto(idProducto);
        cargarResenias(idProducto);
    }
    
    const nuevaResenia = document.querySelector('.form-nueva-resenia');
    if (nuevaResenia) {
        nuevaResenia.addEventListener('submit', (e) => {
            e.preventDefault();
            if (idProducto) {
                crearResenia(idProducto);
            }
        });
    }
    
    const calificacionRango = document.getElementById('calificacion-range');
    const calificacion = document.getElementById('calificacion-value');
        calificacionRango.addEventListener('input', () => {
            calificacion.textContent = calificacionRango.value;
        });
});

function formatearFecha(fecha) {
    return `${fecha[2]}/${fecha[1]}/${fecha[0]}`;
}

async function cargarDetalleProducto(idProducto) {
    const contextPath = window.location.pathname.split('/')[1] || '';
    const url = contextPath ? `/${contextPath}/api/productos/${idProducto}` : `/api/productos/${idProducto}`;
    
    try {
        const respuesta = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include'
        });
        
        const producto = await respuesta.json();
        mostrarDetallesProducto(producto);
        
    } catch (error) {
        console.error('Error:', error);
    }
}

function mostrarDetallesProducto(producto) {
    document.querySelector('.info-libro img').src = producto.urlImagen;
    document.querySelector('.detalles-libro h3').textContent = producto.libro.titulo;
    
    const autor = document.querySelector('.detalles-libro label');
    autor.textContent = `Autor: ${producto.libro.autor.nombre || ''} ${producto.libro.autor.apellidoPaterno || ''} ${producto.libro.autor.apellidoMaterno || ''}`.trim();
    
    const editorial = document.querySelector('.p-editorial');
    editorial.textContent = `Editorial: ${producto.libro.editorial.nombre || ''}`;
    
    const fechaPublicacion = document.querySelector('.p-publicacion');
    fechaPublicacion.textContent = `Publicación: ${formatearFecha(producto.libro.fechaPublicacion)}`;

    
    document.querySelector('.p-no-paginas').textContent = `${producto.numeroPaginas} Páginas`;
    document.querySelector('.p-formato').textContent = producto.formato.toString();
    document.querySelector('.p-precio').textContent = `$${parseFloat(producto.precio).toFixed(2)}`;
    document.querySelector('.p-stock').textContent = `${producto.stock} disponibles`;
    
    const inputCantidad = document.getElementById('product-quantity');
    inputCantidad.max = producto.stock;

    
    const sinopsis = document.querySelector('.resumen p');
    sinopsis.textContent = producto.libro.sinopsis;

}

async function cargarResenias(idProducto) {
    const contextPath = window.location.pathname.split('/')[1] || '';
    const url = contextPath ? `/${contextPath}/api/resenias/producto/${idProducto}` : `/api/resenias/producto/${idProducto}`;
    
    try {
        const respuesta = await fetch(url, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include'
        });
        
        const resenias = await respuesta.json();
        const contenedorResenias = document.querySelector('.resenias-usuarios');
        
        const nuevaResenia= contenedorResenias.querySelector('.nueva-resenia-container');
        contenedorResenias.innerHTML = '';
        if (nuevaResenia) {
            contenedorResenias.appendChild(nuevaResenia);
        }
        
        resenias.forEach(resenia => {
            const divResenia = crearReseniaIndividual(resenia);
            contenedorResenias.appendChild(divResenia);
        });
    } catch (error) {
        console.error('Error:', error);
    }
}

function crearReseniaIndividual(resenia) {
    const divResenia = document.createElement('div');
    divResenia.className = 'resenia-item';
    
    const nombreCompleto = `${resenia.cliente.nombre || ''} ${resenia.cliente.apellidoPaterno || ''} ${resenia.cliente.apellidoMaterno || ''}`.trim();
    const fechaFormateada = formatearFecha(resenia.fecha);
    
    let titulo = '';
    let comentario = resenia.comentario || '';
    
    if (comentario.includes(':')) {
        const partes = comentario.split(':');
        titulo = partes[0].trim();
        comentario = partes.slice(1).join(':').trim();
    }
    
    divResenia.innerHTML = `
        <div class="resenia-usuario">
            <img src="./icons/user.png" alt="Usuario">
            <div class="nombre-calificacion-usuario">
                <span>${nombreCompleto}</span>
                <div class="calificacion">
                    <p>${mostrarEstrellas(resenia.calificacion)}</p>
                </div>
                <p>${fechaFormateada}</p>
            </div>
        </div>
        ${comentario || titulo ? `
        <div class="contenido-resenia-usuario">
            <p class="titulo-resenia">${titulo}</p>
                <input type="checkbox" id="ver-${resenia.id}" class="input-checkbox">
                <label class="btn-ver" for="ver-${resenia.id}">
                    <span class="span-ver">Ver</span>
                    <span class="span-ocultar">Ocultar</span>
                </label>
                <div class="contenido-resenia-ampliada">
                    <p>${comentario}</p>
                </div>
        </div>
        ` : ''}
    `;
    
    return divResenia;
}

function mostrarEstrellas(calificacion) {
    let estrellas = '';
    for (let i = 0; i < calificacion; i++) {
        estrellas += '<div class="estrella"></div>';
    }
    return estrellas;
}

async function crearResenia(idProducto) {
    const contextPath = window.location.pathname.split('/')[1] || '';
    const urlProducto = contextPath ? `/${contextPath}/api/productos/${idProducto}` : `/api/productos/${idProducto}`;
    
    const inputCalificacion = document.getElementById('calificacion-range');
    const inputTitulo = document.getElementById('titulo-nueva-resenia');
    const inputContenido = document.getElementById('contenido-nueva-resenia');
    
    if (!inputCalificacion || !inputTitulo || !inputContenido) {
        return;
    }
    
    const calificacion = parseInt(inputCalificacion.value);
    const titulo = inputTitulo.value.trim();
    const contenido = inputContenido.value.trim();
    

    try {
        const respuestaProducto = await fetch(urlProducto, {
            method: 'GET',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include'
        });
        
        const producto = await respuestaProducto.json();
        
        let comentario = '';
        if (titulo && contenido) {
            comentario = `${titulo}: ${contenido}`;
        } else if (titulo) {
            comentario = titulo;
        } else if (contenido) {
            comentario = contenido;
        }
        
        const reseniaNueva = {
            calificacion: calificacion,
            comentario: comentario,
            formato: producto.formato
        };
        
        const url = contextPath ? `/${contextPath}/api/resenias/producto/${idProducto}` : `/api/resenias/producto/${idProducto}`;
        
        const respuesta = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include',
            body: JSON.stringify(reseniaNueva)
        });
        
        if (respuesta.ok) {
            inputCalificacion.value = 3;
            document.getElementById('calificacion-value').textContent = '3';
            inputTitulo.value = '';
            inputContenido.value = '';
            
            const checkbox = document.getElementById('btn-agregar-checkbox');
            if (checkbox) {
                checkbox.checked = false;
            }
            
            cargarResenias(idProducto);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

