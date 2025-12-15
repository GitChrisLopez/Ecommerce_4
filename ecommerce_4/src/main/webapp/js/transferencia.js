const titular = document.getElementById("titular");
const numero = document.getElementById("num-tarjeta");
const fecha = document.getElementById("fecha-vencimiento");
const cvv = document.getElementById("cvv");


function pagarPedido() {
    if (titular.value === "") {
        window.alert("Favor de llenar el campo.");
    }
    if (numero.value === "" || titular.value.length < 13) {
        window.alert("Favor de llenar el campo.");
    }
    if (fecha.value === "") {
        window.alert("Favor de llenar el campo.");
    }
    if (cvv.value === "" || cvv.value.length < 4) {
        window.alert("Favor de llenar el campo.");
    }
    
    
    
}


function errorPedido(){
    
}
