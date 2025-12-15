
package BOs;

import definiciones.ICarritosBO;
import definiciones.ICarritosDAO;
import dominio.CarritoDTO;
import entidades.Carrito;
import entidades.ProductoCarrito;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public class CarritosBO implements ICarritosBO{

    private ICarritosDAO carritosDAO;

    public CarritosBO(ICarritosDAO carritosDAO) {
        this.carritosDAO = carritosDAO;
    }
    
    private final String MENSAJE_CANTIDAD_ACTUALIZAR_CERO = "La cantidad a actualizar no puede ser 0";
    private final String MENSAJE_CLIENTE_SIN_CARRITO = "El cliente no tiene carrito";
    private final String MENSAJE_PRODUCTO_NO_AGREGADO = "El producto que se intentó actualizar no se ha agregado al carrito.";
    private final String MENSAJE_CANTIDAD_ELIMINAR_SUPERA_AGREGADO = "La cantidad que se intentó eliminar del producto es mayor a la agregada en el carrito";

    @Override
    public void actualizarProductoCarrito(Long idCliente, Long idProducto, int cantidad) throws NegocioException{
        
        if(idCliente == null || idProducto == null){
            throw new NegocioException("Los Id de cliente y producto no deben ser nulos.");  
        }
        
        if(cantidad == 0){
            throw new NegocioException(MENSAJE_CANTIDAD_ACTUALIZAR_CERO);
        }
        
        // Se obtiene el carrito del cliente.
        Carrito carritoCliente;
        try {
            carritoCliente = carritosDAO.consultarPorIdCliente(idCliente);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        
        // Si la cantidad es menor a 0, se comprueba si el cliente tiene un carrito o unidades
        // agregadas del producto que quiere actualizar.
        if(cantidad < 0){
            
            disminuirCantidadProductoCarrito(idCliente, idProducto, cantidad, carritoCliente);
             
        } else{
            
            aumentarCantidadProductoCarrito(idCliente, idProducto, cantidad, carritoCliente);
        }
    }
    
    private void disminuirCantidadProductoCarrito(Long idCliente, Long idProducto, int cantidad, Carrito carritoCliente) throws NegocioException{
        
        ProductoCarrito productoCarrito;
                
        if(carritoCliente == null){
            throw new NegocioException(MENSAJE_CLIENTE_SIN_CARRITO);
        }

        try {
            productoCarrito = carritosDAO.consultarProductoCarrito(idCliente, idProducto);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }

        // Si el producto no se ha agregado.
        if(productoCarrito == null){
            throw new NegocioException(MENSAJE_PRODUCTO_NO_AGREGADO);
        }

        int cantidadOriginal = productoCarrito.getCantidad();

        int nuevaCantidad = cantidadOriginal + cantidad;

        // Si la cantidad a eliminar es mayor a la actual.
        if(nuevaCantidad < 0){
            throw new NegocioException(MENSAJE_CANTIDAD_ELIMINAR_SUPERA_AGREGADO);
        }

        // Si se quiere eliminar todas las unidades del producto agregadas al carrito.
        if(nuevaCantidad == 0){

            try {
                carritosDAO.eliminarProductoCarrito(idCliente, idProducto);
            } catch (PersistenciaException ex) {
                throw new NegocioException(ex.getMessage());
            }

        } else{

            try {
                carritosDAO.actualizarCantidadProductoCarrito(idCliente, idProducto, nuevaCantidad);
            } catch (PersistenciaException ex) {
                throw new NegocioException(ex.getMessage());
            }

        }

    }
    
    private void aumentarCantidadProductoCarrito(Long idCliente, Long idProducto, int cantidad, Carrito carritoCliente) throws NegocioException{
        
        ProductoCarrito productoCarrito;
        // Si al cantidad es positiva y el cliente no tiene carrito, se crea uno y se agrega el producto.
        if(carritoCliente == null){

            try {
                carritosDAO.crearCarrito(idCliente);
            } catch (PersistenciaException ex) {
                throw new NegocioException(ex.getMessage());
            }

            try {
                carritosDAO.crearProductoCarrito(idCliente, idProducto, cantidad);
            } catch (PersistenciaException ex) {
                throw new NegocioException(ex.getMessage());
            }


        } else{

            try {
                productoCarrito = carritosDAO.consultarProductoCarrito(idCliente, idProducto);
            } catch (PersistenciaException ex) {
                throw new NegocioException(ex.getMessage());
            }

            if(productoCarrito == null){
                try {
                    carritosDAO.crearProductoCarrito(idCliente, idProducto, cantidad);
                } catch (PersistenciaException ex) {
                    throw new NegocioException(ex.getMessage());
                }
            } else{

                int cantidadOriginal = productoCarrito.getCantidad();

                int nuevaCantidad = cantidadOriginal + cantidad;
        
                try {
                    carritosDAO.actualizarCantidadProductoCarrito(idCliente, idProducto, nuevaCantidad);
                } catch (PersistenciaException ex) {
                    throw new NegocioException(ex.getMessage());
                }

            }

        }
        
    }
    
    @Override
    public void eliminarProductoCarrito(Long idCliente, Long idProducto) throws NegocioException {
        
        if(idCliente == null || idProducto == null){
            throw new NegocioException("Los Id de cliente y producto no deben ser nulos.");  
        }
        
        try {
            carritosDAO.eliminarProductoCarrito(idCliente, idCliente);
        } catch (PersistenciaException ex) {
            throw new NegocioException();
        }
        
    }

    @Override
    public void eliminarCarrito(Long idCliente) throws NegocioException {
        
        if(idCliente == null){
            throw new NegocioException("El Id de cliente no debe ser nulo.");   
        }
        
        try {
            carritosDAO.eliminarCarrito(idCliente);
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        
    }

    @Override
    public CarritoDTO consultarCarrito(Long idCliente) throws NegocioException {
        
        if(idCliente == null){
            throw new NegocioException("El Id de cliente no debe ser nulo.");   
        }
        
        CarritoDTO carrito;
        try {
            carrito = mappers.MapperCarrito.toDto(carritosDAO.consultarPorIdCliente(idCliente));
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage());
        }
        
        return carrito;
        
    }
    
    
}
