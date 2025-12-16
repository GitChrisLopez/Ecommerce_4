
document.addEventListener('DOMContentLoaded', () => {
    cargarDatosCliente();
    
    const formulario = document.querySelector('.edicion-form');
    formulario.addEventListener('submit', guardarPerfil);
});

async function cargarDatosCliente() {
    const contextPath = window.location.pathname.split('/')[1] || '';
    const url = contextPath ? `/${contextPath}/api/clientes/perfil` : '/api/clientes/perfil';
    
    const respuesta = await fetch(url, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include'
    });
    
    const datos = await respuesta.json();
    
    document.getElementById('nombres').value = datos.nombre;
    document.getElementById('apellido-paterno').value = datos.apellidoPaterno;
    document.getElementById('apellido-materno').value = datos.apellidoMaterno || '';
    document.getElementById('telefono').value = datos.telefono;
    document.getElementById('email').value = datos.correo;
}

async function guardarPerfil(evento) {
    evento.preventDefault();
    
    const elementoNombres = document.getElementById('nombres'); 
    const elementoApellidoPaterno = document.getElementById('apellido-paterno'); 
    const elementoApellidoMaterno = document.getElementById('apellido-materno');
    const elementoCorreo = document.getElementById('email');
    const elementoTelefono = document.getElementById('telefono');
    
    const cliente = {
        nombre: elementoNombres.value, 
        apellidoPaterno: elementoApellidoPaterno.value,
        apellidoMaterno: elementoApellidoMaterno.value,
        correo: elementoCorreo.value,
        telefono: elementoTelefono.value
    };
    
    const contextPath = window.location.pathname.split('/')[1] || '';
    const url = contextPath ? `/${contextPath}/api/clientes/perfil` : '/api/clientes/perfil';
    
    const respuesta = await fetch(url, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        credentials: 'include',
        body: JSON.stringify(cliente)
    });
    
    if (respuesta.ok) {
        mostrarMensaje('Perfil actualizado correctamente', 'exito');
        setTimeout(() => cargarDatosCliente(), 1000);
    } else {
        try {
            const resultado = await respuesta.json();
            const mensajeError = resultado.error || 'Error al actualizar el perfil';
            mostrarMensaje(mensajeError, 'error');
        } catch (e) {
            mostrarMensaje('Error al actualizar el perfil', 'error');
        }
    }
}

function mostrarMensaje(mensaje, tipo) {
    const mensajeAnterior = document.getElementById('mensaje-perfil');
    if (mensajeAnterior) {
        mensajeAnterior.remove();
    }
    
    const div = document.createElement('div');
    div.id = 'mensaje-perfil';
    div.className = 'mensaje-perfil ' + tipo;
    div.textContent = mensaje;
    
    document.body.appendChild(div);
    
    setTimeout(() => {
        if (div.parentNode) {
            div.remove();
        }
    }, 3000);
}

