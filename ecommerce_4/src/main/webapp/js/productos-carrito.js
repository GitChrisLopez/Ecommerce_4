/* 
Manuel Romo López
ID: 00000253080
 */


// Función que registra el nuevo producto agregado.
async function agregarProductoCarrito() {
    
    const idProducto = document.getElementById("product-id").value;
    const cantidad = document.getElementById("product-quantity").value;

    // Se obtiene el id del producto y la cantidad ingresada.
    const datosProducto = {
        idProducto: parseInt(idProducto),
        cantidad: parseInt(cantidad)
    };

    try {
        // Se hace fetch
        const response = await fetch('api/productos-carrito', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include', 
            body: JSON.stringify(datosProducto)
        });

        // Se obtiene el resultado.
        const resultado = await response.json();
        
        window.location.href = "./ver-carrito";

    } catch (error) {
        
        console.error(error);
    }
}