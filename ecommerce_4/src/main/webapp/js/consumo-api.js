const API_BASE_URL = window.location.origin + "/api_ecommerce/api/";

/**
 * Función genérica para consumir la API.
 * Firma actualizada para aceptar: endpoint, método y cuerpo.
 */
async function apiFetch(endpoint, method = 'GET', body = null) {

    // Configuración base
    const config = {
        method: method,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        credentials: 'include'
    };

    // Si hay datos para enviar (POST, PUT) y no es GET, los convertimos a JSON
    if (body && method !== 'GET') {
        config.body = JSON.stringify(body);
    }

    try {
        console.log(`Petición API: ${method} ${API_BASE_URL + endpoint}`); // Log para depurar

        const response = await fetch(API_BASE_URL + endpoint, config);

        if (response.status === 401) {
            alert("Tu sesión ha expirado. Por favor, inicia sesión de nuevo.");
            window.location.href = "iniciar-sesion.jsp";
            return {exito: false, mensaje: "Sesión expirada"};
        }

        if (response.status === 403) {
            alert("No tienes permisos para realizar esta acción.");
            return {exito: false, mensaje: "Acceso denegado"};
        }

        if (!response.ok) {
            const errorData = await response.json().catch(() => null);
            const mensaje = errorData && errorData.mensaje ? errorData.mensaje : `Error ${response.status}: El servidor no respondió correctamente.`;
            console.error("Error respuesta API:", mensaje);
            throw new Error(mensaje);
        }

        if (response.status === 204) {
            return null;
        }

        return await response.json();

    } catch (error) {
        console.error("Fallo crítico en apiFetch:", error);
        // Lanzamos el error para que carrito.js muestre el mensaje en pantalla
        throw error;
}
}