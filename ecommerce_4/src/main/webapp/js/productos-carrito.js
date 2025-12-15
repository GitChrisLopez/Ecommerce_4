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
    
    try{
        
        const respuesta = await apiFetch('/api_ecommerce/api/productos-carrito', 'POST', datosProducto);
        
        if (!respuesta) return; 

        window.location.href = "./ver-carrito";
        
    } catch(error){
        
        alert(error);
        
    }
    
}