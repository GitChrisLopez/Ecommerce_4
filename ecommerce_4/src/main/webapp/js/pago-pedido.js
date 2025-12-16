document.addEventListener('DOMContentLoaded', function() {
    // Obtenemos el botón de pagar
    const btnPagar = document.querySelector('.btn-pagar-pedido');
    
    if(btnPagar) {
        btnPagar.addEventListener('click', function(event) {
            event.preventDefault(); // Evitamos que el formulario se envíe automáticamente

            // Buscamos el radio button seleccionado
            const metodoSeleccionado = document.querySelector('input[name="metodo"]:checked');
            
            if (!metodoSeleccionado) {
                alert("Por favor selecciona un método de pago.");
                return;
            }

            // Obtenemos el ID del pedido de la URL actual
            const urlParams = new URLSearchParams(window.location.search);
            const idPedido = urlParams.get('id');

            if (!idPedido) {
                alert("Error: No se encontró el ID del pedido.");
                return;
            }

            const metodo = metodoSeleccionado.value;

            // Redireccionamos según la opción elegida, pasando el ID
            if (metodo === "Transferencia") {
                // Redirige a la página de éxito de transferencia (o instrucciones)
                window.location.href = `/pago-exitoso-transferencia.jsp?id=${idPedido}`;
            } else if (metodo === "Tarjeta de débito/crédito") {
                // Redirige al formulario de tarjeta
                window.location.href = `/pago-tarjeta.jsp?id=${idPedido}`;
            } else if (metodo === "Contraentrega") {
                // Redirige a la confirmación de contraentrega
                window.location.href = `/contraentrega-pedido.jsp?id=${idPedido}`;
            }
        });
    }
});

function formatearPrecio(precio) {
    const num = Number(precio);
    return isNaN(num) ? '$0.00' : `$${num.toFixed(2)}`;
}