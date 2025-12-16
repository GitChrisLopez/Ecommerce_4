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

    const nombresFormatos = {
        'TAPA_DURA': 'Tapa dura',
        'TAPA_BLANDA': 'Tapa blanda',
        'BOLSILLO': 'Bolsillo',
        'E_BOOK': 'E-Book'
    };

    formatosDisponibles.forEach(formato => {
        const label = document.createElement('label');
        label.innerHTML = `
            <input type="checkbox" name="formatos" value="${formato}"> 
            ${nombresFormatos[formato] || formato}
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

    const precioMinInput = document.getElementById('precioMinRango');
    if (precioMinInput && precioMinInput.value && parseFloat(precioMinInput.value) > 0) {
        filtros.precioMinimo = parseFloat(precioMinInput.value);
    }

    const precioMaxInput = document.getElementById('precioMaxRango');
    if (precioMaxInput && precioMaxInput.value) {
        filtros.precioMaximo = parseFloat(precioMaxInput.value);
    }

    const nombreLibro = document.querySelector('input[name="nombreLibro"]');
    if (nombreLibro) filtros.nombreLibro = nombreLibro.value.trim();

    return filtros;
}

function aplicarFiltros() {
    sessionStorage.setItem('filtrosCatalogo', JSON.stringify(obtenerFiltrosSeleccionados()));
    window.location.href = './catalogo.jsp';
}

document.addEventListener('DOMContentLoaded', () => {
    cargarCategorias().then(() => {
        cargarFormatos();
    });

    const btnAplicarFiltros = document.getElementById('btnAplicarFiltros');
    if (btnAplicarFiltros) {
        btnAplicarFiltros.addEventListener('click', e => {
            e.preventDefault();
            aplicarFiltros();
        });
    }

    const precioMinRango = document.getElementById('precioMinRango');
    const precioMaxRango = document.getElementById('precioMaxRango');
    const precioMinValor = document.getElementById('precioMinValor');
    const precioMaxValor = document.getElementById('precioMaxValor');

    if (precioMinRango && precioMinValor) {
        precioMinRango.oninput = () =>
            precioMinValor.textContent = precioMinRango.value;
        precioMinValor.textContent = precioMinRango.value;
    }

    if (precioMaxRango && precioMaxValor) {
        precioMaxRango.oninput = () =>
            precioMaxValor.textContent = precioMaxRango.value;
        precioMaxValor.textContent = precioMaxRango.value;
    }

    const guardados = sessionStorage.getItem('filtrosCatalogo');
    if (guardados) {
        try {
            const f = JSON.parse(guardados);
            const nombreLibro = document.querySelector('input[name="nombreLibro"]');
            if (nombreLibro && f.nombreLibro) nombreLibro.value = f.nombreLibro;
            
            f.categorias.forEach(id => {
                document.querySelector(`input[name="categorias"][value="${id}"]`)?.setAttribute('checked', 'checked');
            });
            
            f.formatos.forEach(formato => {
                document.querySelector(`input[name="formatos"][value="${formato}"]`)?.setAttribute('checked', 'checked');
            });
            
            if (precioMinRango && precioMinValor && f.precioMinimo) {
                precioMinRango.value = f.precioMinimo;
                precioMinValor.textContent = f.precioMinimo;
            }
            if (precioMaxRango && precioMaxValor && f.precioMaximo) {
                precioMaxRango.value = f.precioMaximo;
                precioMaxValor.textContent = f.precioMaximo;
            }
        } catch (e) {
            console.error('Error al restaurar filtros:', e);
        }
    }
});
