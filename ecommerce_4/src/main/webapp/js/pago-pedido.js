const opcion = document.getElementById("radio-btn-opciones");
const metodo = document.getElementById("metodo");

function pedidoTransferencia() {
    document.getElementById();
    document.getElementByClass("button").onclick = function () {
        location.href = "/pago-exitoso-transferencia.jsp";
    };
    const idPedido = urlParams.get('id');

}



function pedidoContraentrega() {
    document.getElementByClass("button").onclick = function () {
        location.href = "/contraentrega-pedido.jsp";
    };
}

function pedidoTarjeta() {
    document.getElementByClass("button").onclick = function () {
        location.href = "/pago-tarjeta.jsp";
    };
}





function formatearPrecio(precio) {
    const num = Number(precio);
    return isNaN(num) ? '$0.00' : `$${num.toFixed(2)}`;
}

