document.addEventListener('DOMContentLoaded', () => {
    cargarResumenPedido();

    const formPedido = document.getElementById('form-crear-pedido');
    if (formPedido) {
        formPedido.addEventListener('submit', procesarCreacionPedido);
    }
});

let totalCalculado = 0;

async function cargarResumenPedido() {
    const idCliente = document.getElementById('idCliente').value;
    const contenedor = document.getElementById('contenedor-resumen');

    if (!idCliente) {
        window.location.href = "iniciar-sesion.jsp";
        return;
    }

    try {
        // 1. Obtenemos el carrito actual
        const carrito = await apiFetch(`carritos?idCliente=${idCliente}`, 'GET');

        if (!carrito || !carrito.productos || carrito.productos.length === 0) {
            contenedor.innerHTML = "<p>No hay productos en el pedido.</p>";
            return;
        }

        let html = '';
        totalCalculado = 0;

        // 2. Generamos el HTML del resumen
        carrito.productos.forEach(item => {
            const subtotal = item.precio * item.cantidad;
            totalCalculado += subtotal;

            html += `
                <div class="libro-detalles">
                    <p>
                        ${item.titulo} (x${item.cantidad})
                        <span class="espacio">----------------------------</span>
                        <span class="costo">$${subtotal.toFixed(2)}</span>
                    </p>
                </div>
            `;
        });

        html += `
            <hr />
            <div class="linea total">
                <p>
                    <strong>
                        Total
                        <span class="espacio">---------------------------------------------------------</span>
                        <span class="costo">$${totalCalculado.toFixed(2)}</span>
                    </strong>
                </p>
            </div>
        `;

        contenedor.innerHTML = html;

        // Opcional: Si tienes una API de direcciones, podrías cargar la dirección por defecto aquí
        // cargarDireccionPredeterminada(idCliente);

    } catch (error) {
        console.error("Error al cargar resumen:", error);
        contenedor.innerHTML = "<p>Error al cargar el resumen del pedido.</p>";
    }
}

async function procesarCreacionPedido(event) {
    event.preventDefault(); // Evita recarga

    const idCliente = document.getElementById('idCliente').value;
    const direccionInput = document.getElementById('direccion');
    const direccion = direccionInput.value.trim();

    // Validación básica
    if (!direccion) {
        alert("Por favor selecciona una dirección de envío antes de continuar.");
        return;
    }

    const datosPedido = {
        idCliente: parseInt(idCliente),
        direccion: direccion,
        total: totalCalculado,
        metodoPago: "PENDIENTE" // Se definirá en la siguiente pantalla, pero la BD lo requiere
    };

    try {
        // 3. Enviamos la petición POST a la API para crear el pedido
        // Asegúrate que tu PedidosResource.java tenga un @POST que acepte este JSON
        const respuesta = await apiFetch('pedidos', 'POST', datosPedido);

        if (respuesta && respuesta.id) {
            console.log("Pedido creado con ID:", respuesta.id);

            // 4. AQUÍ ESTÁ LA SOLUCIÓN: Redirigimos llevando el ID generado
            window.location.href = `metodo-pago.jsp?id=${respuesta.id}`;
        } else {
            throw new Error("La API no devolvió el ID del pedido.");
        }

    } catch (error) {
        console.error("Error creando pedido:", error);
        alert("Hubo un error al crear el pedido: " + error.message);
    }
}