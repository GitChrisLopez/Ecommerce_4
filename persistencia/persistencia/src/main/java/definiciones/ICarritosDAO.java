
package definiciones;

import entidades.Carrito;
import entidades.ProductoCarrito;
import excepciones.PersistenciaException;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public interface ICarritosDAO {
    
    public abstract void crearProductoCarrito(Long idCliente, Long idProducto, int cantidad) throws PersistenciaException;

    public abstract void actualizarCantidadProductoCarrito(Long idCliente, Long idProducto, int nuevaCantidad) throws PersistenciaException;

    public abstract void eliminarProductoCarrito(Long idCliente, Long idProducto) throws PersistenciaException;

    public abstract ProductoCarrito consultarProductoCarrito(Long idCliente, Long idProducto) throws PersistenciaException;
    
    public abstract void crearCarrito(Long idCliente) throws PersistenciaException;
    
    public abstract void eliminarCarrito(Long idCliente) throws PersistenciaException;
    
    public abstract Carrito consultarPorIdCliente(Long idCliente) throws PersistenciaException;
    
}
