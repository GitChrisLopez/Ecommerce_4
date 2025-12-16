/* 
Manuel Romo López
ID: 00000253080
 */


// Función que registra el nuevo producto agregado.
async function agregarProductoCarrito(event) {
    
    event.preventDefault();
    
    const idProducto = document.getElementById('product-id').value;
    const cantidad = document.getElementById('product-quantity').value;
    
    const parsedId = parseInt(idProducto);
    const parsedCantidad = parseInt(cantidad);
    
    // Se obtiene el id del producto y la cantidad ingresada.
    const datosProducto = {
        idProducto: parseInt(idProducto),
        cantidad: parseInt(cantidad)
    };
    
    try{
        
        const opcionesDePeticion = {
            method: 'POST',
            body: JSON.stringify(datosProducto)
        };
        
        const respuesta = await apiFetch('productos-carrito', opcionesDePeticion);

        if (!respuesta) return; 

        const contextPath = window.location.pathname.split('/')[1] || '';

        window.location.href = `/${contextPath}/ver-carrito`;
        
    } catch(error){
        
        alert("Ha ocurrido un error al agregar el producto.");
        
    }
    
}