
async function apiFetch(endpoint, opciones = {}) {
    
    // Configuración por defecto
    const headersDefecto = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    // Fusionar opciones, utilizando el operador spread.
    const config = {
        ...opciones,
        headers: { ...headersDefecto, ...opciones.headers }
    };

    try {
        const response = await fetch(endpoint, config);

        // Sesión expirada
        if (response.status === 401) {
            console.warn("Sesión expirada. Redirigiendo...");
            window.location.href = "login.jsp?mensaje=Tu sesion expiro";
            return null;
        }

        // Uusario sin permiso.
        if (response.status === 403) {
            alert("No tienes permisos para realizar esta acción.");
            return null;
        }

        // Error inesperado.
        if (!response.ok) {
            const errorData = await response.json().catch(() => ({}));
            const mensajeError = errorData.mensaje || "Ocurrió un error inesperado.";
            
            throw new Error(mensajeError);
        }

        // Si la respuesta no tiene contenido.
        if (response.status === 204){
            return null;
        }
        
        return await response.json();

    } catch (error) {
        // Se relanza el error.
        throw error;
    }
}