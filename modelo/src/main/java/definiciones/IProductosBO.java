
package definiciones;

import dominio.CategoriaDTO;
import dominio.FormatoDTO;
import dominio.ProductoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: IProductosBO
 * 
 * Interfaz que define los métodos que deben implementar las clases que realizan 
 * opraciones de negocio sobre productos.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 */
public interface IProductosBO {
    
    /**
     * Permite registrar un nuevo producto.
     * 
     * @param nuevoProducto Objeto DTO que contiene los valores de los atributos
     * necesarios para crear un nuevo producto.
     * @return Objeto Producto, representa el producto registrado.
     * @throws NegocioException Se lanza si algún atributo del producto recibido es nulo o
     * si ocurre una excepción al registrar el Producto.
     */
    public abstract ProductoDTO registrarProducto(ProductoDTO nuevoProducto) throws NegocioException;
    
    /**
     * Implementación del método consultarProducto() de la interfaz {@literal IProductosBO},
     * que permite obtener los datos de un producto a partir de su Id.
     * 
     * @param idProducto Dato Long que representa el Id del producto a consultar.
     * @return Objeto ProductoDTO que contiene los datos del producto consultado.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar el producto.
     */
    public abstract ProductoDTO consultarProducto(Long idProducto) throws NegocioException;
    
    /**
     * Permite obtener la lista de Productos registrados, dados los filtros de los parámetros.
     * 
     * @param tituloAutor Objeto String que representa el titulo del libro, o del autor, del producto buscado, o parte de él.
     * @param categorias Objeto {@literal List<CategoriaDTO>} que contiene las categorías de los libros de los productos
     * a consultar.
     * @param formatos Objeto {@literal List<FormatoDTO>} que contiene los formatos de los productos
     * a consultar.
     * @param precioMinimo Objeto Double que representa el precio mínimo de los productos 
     * a consultar.
     * @param precioMaximo Objeto Double que representa el precio maximo de los productos 
     * a consultar.
     * @return Objeto {@literal List<ProductoDTO>} que contiene los productos consultados.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar los productos.
     */
    public abstract List<ProductoDTO> consultarProductosConFiltros(
            String tituloAutor,
            List<CategoriaDTO> categorias, 
            List<FormatoDTO> formatos, 
            Double precioMinimo, 
            Double precioMaximo) 
            throws NegocioException;
    
    /**
     * Permite actualizar los valores de los atributos de un producto registrado.
     * 
     * @param productoActualizado Objeto DTO que contiene los valores actualizados para 
     * los atributos del objeto producto a actualizar. 
     * @throws NegocioException Se lanza si algún valor del nuevo producto es nulo, o si
     * ocurre un error al actualizar el producto.
     */
    public abstract void actualizarProducto(ProductoDTO productoActualizado) throws NegocioException;
    
    /**
     * Permite eliminar un producto registrado.
     * 
     * @param idProducto Objeto Long que representa el Id del produco que se quiere eliminar.
     * @throws NegocioException Se lanza si ocurren un error al eliminar el producto.
     */
    public abstract void eliminarProducto(Long idProducto) throws NegocioException;
}
