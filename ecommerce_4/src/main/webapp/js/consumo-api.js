
const API_BASE_URL = window.location.origin + "/api_ecommerce/api/";

async function apiFetch(endpoint, opciones = {}) {
    
    // Configuración por defecto
    const headersDefecto = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };

    // Fusionar opciones, utilizando el operador spread.
    const config = {
        ...opciones,
        headers: { ...headersDefecto, ...opciones.headers },
        credentials: 'include'
    };

    try {
        
        const response = await fetch(API_BASE_URL + endpoint, config);

        // Sesión expirada
        if (response.status === 401) {
            alert("Sesión expirada. Redirigiendo...");
            
            return null;
        }

        // Usuario sin permiso.
        if (response.status === 403) {
            alert("No tienes permisos para realizar esta acción.");
            return null;
        }
        
        // Error inesperado.
        if (!response.ok) {
            const errorData = await response.json().catch(() => ({}));
            const mensajeError = "Ha ocurrido un error inesperado.";
            alert(mensajeError);
            
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