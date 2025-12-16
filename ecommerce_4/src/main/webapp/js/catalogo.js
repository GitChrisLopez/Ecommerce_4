let productos = [];
let categorias = [];
let formatosDisponibles = ['TAPA_DURA', 'TAPA_BLANDA', 'BOLSILLO', 'E_BOOK'];

function getApi() {
    const base = window.location.pathname.split('/')[1];
    return base && base !== 'api' ? `/${base}/api` : '/api';
}

async function cargarCategorias() {
    try {
        const url = `${getApi()}/productos/categorias`;
        const respuesta = await fetch(url, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include'
        });

        if (respuesta.ok) {
            categorias = await respuesta.json();
            const filtroCategoria = document.querySelector('.filtros.filtro-categoria');
            const tituloCategoria = filtroCategoria.querySelector('h2');

            categorias.forEach(categoria => {
                const label = document.createElement('label');
                label.innerHTML = `
                    <input type="checkbox" name="categorias" value="${categoria.id}"> 
                    ${categoria.nombre}
                `;
                tituloCategoria.after(label);
            });
        } else {
            console.error('Error al cargar categorías');
        }
    } catch (error) {
        console.error('Error al cargar categorías:', error);
    }
}

function cargarFormatos() {
    const filtroFormato = document.querySelector('.filtros.filtro-formato');
    const tituloFormato = filtroFormato.querySelector('h2');

    formatosDisponibles.forEach(formato => {
        const label = document.createElement('label');
        label.innerHTML = `
            <input type="checkbox" name="formatos" value="${formato}"> 
            ${formato}
        `;
        tituloFormato.after(label);
    });
}

function obtenerFiltrosSeleccionados() {
    const filtros = {
        categorias: [],
        formatos: [],
        precioMinimo: null,
        precioMaximo: null,
        nombreLibro: ''
    };

    document.querySelectorAll('input[name="categorias"]:checked')
        .forEach(c => filtros.categorias.push(c.value));

    document.querySelectorAll('input[name="formatos"]:checked')
        .forEach(f => filtros.formatos.push(f.value));

    const precioMinInput = document.getElementById('precioMin');
    if (precioMinInput && precioMinInput.value) {
        filtros.precioMinimo = parseFloat(precioMinInput.value);
    }

    const precioMaxInput = document.getElementById('precioMax');
    if (precioMaxInput && precioMaxInput.value) {
        filtros.precioMaximo = parseFloat(precioMaxInput.value);
    }

    const nombreLibro = document.querySelector('input[name="nombreLibro"]');
    if (nombreLibro) filtros.nombreLibro = nombreLibro.value.trim();

    return filtros;
}

function urlFiltros(filtros) {
    const url = new URL(`${getApi()}/productos`, window.location.origin);

    if (filtros.categorias.length)
        url.searchParams.append('categorias', filtros.categorias.join(','));

    if (filtros.formatos.length)
        url.searchParams.append('formatos', filtros.formatos.join(','));

    if (filtros.precioMinimo !== null && filtros.precioMinimo !== undefined && filtros.precioMinimo > 0)
        url.searchParams.append('precioMinimo', filtros.precioMinimo);

    if (filtros.precioMaximo)
        url.searchParams.append('precioMaximo', filtros.precioMaximo);

    if (filtros.nombreLibro)
        url.searchParams.append('nombreLibro', filtros.nombreLibro);

    return url.pathname + url.search;
}

async function cargarProductos() {
    const filtros = obtenerFiltrosSeleccionados();
    const url = urlFiltros(filtros);

    try {
        const respuesta = await fetch(url, {
            method: 'GET',
            headers: {'Content-Type': 'application/json'},
            credentials: 'include'
        });

        if (respuesta.ok) {
            productos = await respuesta.json();
            
            const container = document.querySelector('.libros-container');
            container.innerHTML = '';

            if (!productos || !productos.length) {
                container.innerHTML = '<p>No se encontraron productos.</p>';
                return;
            }

            productos.forEach(p => container.appendChild(crearProductoIndividual(p)));
            
        } else {
            console.error('Error al cargar productos');
        }
    } catch (error) {
        console.error('Error al cargar productos:', error);
    }
}

function crearProductoIndividual(producto) {
    const div = document.createElement('div');
    div.className = 'libro';
    const nombreAutor = `${producto.libro.autor.nombre || ''} ${producto.libro.autor.apellidoPaterno || ''} ${producto.libro.autor.apellidoMaterno || ''}`.trim();

    div.innerHTML = `
        <a href="./resenia-del-producto.jsp?id=${producto.id}">
            <img src="${producto.urlImagen}" alt="${producto.libro.titulo}">
            <h3>${producto.libro.titulo}</h3>
            <p>${nombreAutor}</p>
            <p class="precio">${formatearPrecio(producto.precio)}</p>
        </a>
    `;

    return div;
}

function formatearPrecio(precio) {
    const num = Number(precio);
    return isNaN(num) ? '$0.00' : `$${num.toFixed(2)}`;
}

function restaurarFiltros() {
    const guardados = sessionStorage.getItem('filtrosCatalogo');
    if (!guardados) return;
    
    try {
        const f = JSON.parse(guardados);
        
        f.categorias.forEach(id => {
            document.querySelector(`input[name="categorias"][value="${id}"]`)?.setAttribute('checked', 'checked');
        });
        
        f.formatos.forEach(formato => {
            document.querySelector(`input[name="formatos"][value="${formato}"]`)?.setAttribute('checked', 'checked');
        });
        
        const precioMin = document.getElementById('precioMin');
        const precioMinValor = document.getElementById('precioMinValor');
        if (precioMin && precioMinValor && f.precioMinimo) {
            precioMin.value = f.precioMinimo;
            precioMinValor.textContent = f.precioMinimo;
        }
        
        const precioMax = document.getElementById('precioMax');
        const precioMaxValor = document.getElementById('precioMaxValor');
        if (precioMax && precioMaxValor && f.precioMaximo) {
            precioMax.value = f.precioMaximo;
            precioMaxValor.textContent = f.precioMaximo;
        }
        
        const nombreLibro = document.querySelector('input[name="nombreLibro"]');
        if (nombreLibro && f.nombreLibro) nombreLibro.value = f.nombreLibro;
        
        sessionStorage.removeItem('filtrosCatalogo');
    } catch (e) {
        console.error('Error al restaurar filtros:', e);
    }
}

document.addEventListener('DOMContentLoaded', () => {
    cargarCategorias().then(() => {
        cargarFormatos();
        restaurarFiltros();
        cargarProductos();
    });

    document.querySelector('.sidebar')?.addEventListener('submit', e => {
        e.preventDefault();
        cargarProductos();
    });

    const precioMinRango = document.getElementById('precioMin');
    const precioMaxRango = document.getElementById('precioMax');

    if (precioMinRango) {
        precioMinRango.oninput = () =>
            document.getElementById('precioMinValor').textContent = precioMinRango.value;
        document.getElementById('precioMinValor').textContent = precioMinRango.value;
    }

    if (precioMaxRango) {
        precioMaxRango.oninput = () =>
            document.getElementById('precioMaxValor').textContent = precioMaxRango.value;
        document.getElementById('precioMaxValor').textContent = precioMaxRango.value;
    }
});
