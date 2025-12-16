
package definiciones;

import dominio.CarritoDTO;
import excepciones.NegocioException;


/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public interface ICarritosBO {
    
    public abstract void actualizarProductoCarrito(Long idCliente, Long iProducto, int cantidad) throws NegocioException; 
    
    public abstract void eliminarProductoCarrito(Long idCliente, Long idProducto) throws NegocioException;
    
    public abstract void eliminarCarrito(Long idCliente) throws NegocioException;
    
    public abstract CarritoDTO consultarCarrito(Long idCliente) throws NegocioException;
    
}
