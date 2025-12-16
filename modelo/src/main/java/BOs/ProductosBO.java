
package BOs;

import definiciones.IProductosBO;
import definiciones.IProductosDAO;
import dominio.CategoriaDTO;
import dominio.FormatoDTO;
import dominio.ProductoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.math.BigDecimal;
import java.util.List;
import mappers.MapperCategoria;
import mappers.MapperFormato;
import mappers.MapperProducto;

/**
 *
 * Archivo: ProductosBO.java
 * 
 * Clase que implementa la iterfaz {@link IProductosBO} y que realiza operaciones
 * de negocio sobre productos.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class ProductosBO implements IProductosBO{
    
    /**
     * Objeto que implementa la interfaz IProductosDAO, permite el acceso a datos para objetos de la clase ProductoDTO.
     */
    private IProductosDAO productosDAO;
    
    private int CANTIDAD_MAXIMA_PAGINAS = 10000;
    private double PRECIO_MAXIMO = 9999.99;
    private int STOCK_MAXIMO = 50000;
    
    /**
     * Contructor de la clase que recibe un objeto que implementa la interfaz
     * IProductosDAO.
     * 
     * @param productosDAO Objeto que implementa la interfaz IProductosDAO,
     * permite el acceso a datos para objetos de la clase Producto.
     */
    public ProductosBO(IProductosDAO productosDAO) {
        this.productosDAO = productosDAO;
    }

    /**
     * Implementación del método registrarProducto(), de la interfaz {@link IProductosDAO}
     * que permite registrar un nuevo producto.
     * 
     * @param nuevoProducto Objeto DTO que contiene los valores de los atributos
     * necesarios para crear un nuevo producto.
     * @return Objeto Producto, representa el producto registrado.
     * @throws NegocioException Se lanza si algún atributo del Producto recibido es nulo o
     * si ocurre una excepción al registrar el Producto.
     */
    @Override
    public ProductoDTO registrarProducto(ProductoDTO nuevoProducto) throws NegocioException {
        
        // Se valida que el Libro no sea nulo.
        if(nuevoProducto.getLibro() == null){
            throw new NegocioException("El producto debe ser de un Libro.");
        }
        
        // Se valida que el Formato no sea nulo.
        if(nuevoProducto.getFormato() == null){
            throw new NegocioException("El producto debe tener un Formato.");
        }
        
        // Se valida que el número de páginas no sea nulo.
        if(nuevoProducto.getNumeroPaginas() == null){
            throw new NegocioException("El producto debe tener un número de páginas.");
        }
        
        // Se valida que el número de páginas no sea 0 o negativo.
        if(nuevoProducto.getNumeroPaginas() <= 0){
            throw new NegocioException("El número de páginas debe ser un número positivo.");
        }
        
        // Se valida que el número de páginas no exceda el número máximo.
        if(nuevoProducto.getNumeroPaginas() > CANTIDAD_MAXIMA_PAGINAS){
            throw new NegocioException("El número de páginas no debe exceder las" + CANTIDAD_MAXIMA_PAGINAS + ".");
        }
        
        // Se valida que el precio no sea nulo.
        if(nuevoProducto.getPrecio() == null){
            throw new NegocioException("El producto debe tener un precio.");
        }
        
        // Se valida que el precio no exceda el máximo permitido.
        if(nuevoProducto.getPrecio().compareTo(new BigDecimal(PRECIO_MAXIMO)) == 1){
            throw new NegocioException("El precio del producto no debe exceder los " + PRECIO_MAXIMO + " pesos.");
        }
        
        // Se valida que el precio no sea negativo.
        if(nuevoProducto.getPrecio().compareTo(BigDecimal.ZERO) < 0){
            throw new NegocioException("El precio no puede ser un número negativo.");
        }
        
        // Se valida que el stock no sea nulo.
        if(nuevoProducto.getStock()== null){
            throw new NegocioException("El producto debe tener stock.");
        }
        
        // Se valida que el stock no sea negativo.
        if(nuevoProducto.getStock() < 0){
            throw new NegocioException("El stock no puede ser un número negativo.");
        }
        
        // Se valida que el stock no exceda el máximo permitido.
        if(nuevoProducto.getStock() > STOCK_MAXIMO){
            throw new NegocioException("El stock del producto no debe exceder las " + STOCK_MAXIMO);
        }
        
        try{
            
            // Se registra el producto utilizando el objeto que implementa la interfaz IProductosDAO.
            return MapperProducto.toDto(productosDAO.registrarProducto(MapperProducto.toEntity(nuevoProducto)));
            
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
     
    }
    
    /**
     * Implementación del método consultarProducto() de la interfaz {@literal IProductosBO},
     * que permite obtener los datos de un producto a partir de su Id.
     * 
     * @param idProducto Dato Long que representa el Id del producto a consultar.
     * @return Objeto ProductoDTO que contiene los datos del producto consultado.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar el producto.
     */
    @Override
    public ProductoDTO consultarProducto(Long idProducto) throws NegocioException{
        
        // Se valida el Id del producto.
        if(idProducto == null){
            throw new NegocioException("El Id del producto a consultar no puede ser nulo.");
        }
        
        try{
            return MapperProducto.toDto(productosDAO.consultarProducto(idProducto));
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }
    
    /**
     * Implementación del método consultarProductosConFiltros() de la interfaz {@link IProductosBO},
     * permite obtener la lista de Productos registrados, dados los filtros de los parámetros.
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
    @Override
    public List<ProductoDTO> consultarProductosConFiltros(
            String tituloAutor,
            List<CategoriaDTO> categorias, 
            List<FormatoDTO> formatos, 
            Double precioMinimo, 
            Double precioMaximo) 
            throws NegocioException{
        
        try {
            List<ProductoDTO> listaProductosConsultados = MapperProducto.toDtoList(
                    productosDAO.consultarProductosConFiltros( 
                            tituloAutor,
                            MapperCategoria.toEntityList(categorias),
                            MapperFormato.toEntityList(formatos),
                            precioMinimo, 
                            precioMaximo
                    )
            );
            
            return listaProductosConsultados;
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException(ex.getMessage());
            
        }
    }
    
    
    /**
     * Implementación del método actualizarProducto(), de la interfaz {@IProductosBO},
     * permite actualizar los valores de los atributos de un producto registrado.
     * 
     * @param productoActualizado Objeto DTO que contiene los valores actualizados para 
     * los atributos del objeto producto a actualizar. 
     * @throws NegocioException Se lanza si algún valor del nuevo producto es nulo, o si
     * ocurre un error al actualizar el producto.
     */
    @Override
    public void actualizarProducto(ProductoDTO productoActualizado) throws NegocioException{  
        
        // Se valida que el Id no sea nulo.
        if(productoActualizado.getId() == null){
            throw new NegocioException("El producto actualizado debe tener un Id.");
        }
        
        // Se valida que el Libro no sea nulo.
        if(productoActualizado.getLibro() == null){
            throw new NegocioException("El producto actualizado debe ser de un Libro.");
        }
        
        // Se valida que el Formato no sea nulo.
        if(productoActualizado.getFormato() == null){
            throw new NegocioException("El producto actualizado debe tener un Formato.");
        }
        
        // Se valida que el número de páginas no sea nulo.
        if(productoActualizado.getNumeroPaginas() == null){
            throw new NegocioException("El producto actualizado debe tener un número de páginas.");
        }
        
        // Se valida que el número de páginas no sea 0 o negativo.
        if(productoActualizado.getNumeroPaginas() <= 0){
            throw new NegocioException("El número de páginas debe ser un número positivo.");
        }
        
        
        // Se valida que el número de páginas no exceda el número máximo.
        if(productoActualizado.getNumeroPaginas() > CANTIDAD_MAXIMA_PAGINAS){
            throw new NegocioException("El número de páginas no debe exceder las" + CANTIDAD_MAXIMA_PAGINAS + ".");
        }
        
        // Se valida que el precio no exceda el máximo permitido.
        if(productoActualizado.getPrecio().compareTo(new BigDecimal(PRECIO_MAXIMO)) == 1){
            throw new NegocioException("El precio del producto no debe exceder los " + PRECIO_MAXIMO + " pesos.");
        }


        
        // Se valida que el precio no sea nulo.
        if(productoActualizado.getPrecio() == null){
            throw new NegocioException("El producto actualizado debe tener un precio.");
        }
        
        
        // Se valida que el precio no sea negativo.
        if(productoActualizado.getPrecio().compareTo(BigDecimal.ZERO) < 0){
            throw new NegocioException("El precio no puede ser un número negativo.");
        }
        
        // Se valida que el precio no exceda el máximo permitido.
        if(productoActualizado.getPrecio().compareTo(new BigDecimal(PRECIO_MAXIMO)) == 1){
            throw new NegocioException("El precio del producto no debe exceder los " + PRECIO_MAXIMO + " pesos.");
        }
        
        // Se valida que el stock no sea nulo.
        if(productoActualizado.getStock()== null){
            throw new NegocioException("El producto actualizado debe tener stock");
        }
        
        // Se valida que el stock no sea negativo.
        if(productoActualizado.getStock() < 0){
            throw new NegocioException("El stock no puede ser un número negativo.");
        }  
        
        // Se valida que el stock no exceda el máximo permitido.
        if(productoActualizado.getStock() > STOCK_MAXIMO){
            throw new NegocioException("El stock del producto no debe exceder las " + STOCK_MAXIMO);
        }
        
        try {
            
            // Se actualiza el producto utilizando el objeto que implementa 
            // la interfaz IProductosDAO
            productosDAO.actualizarProducto(MapperProducto.toEntity(productoActualizado));
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Implementación del método actualizarProducto(), de la interfaz {@IProductosBO}, 
     * permite eliminar un producto registrado.
     * 
     * @param idProducto Objeto Long que representa el Id del produco que se quiere eliminar.
     * @throws NegocioException Se lanza si ocurren un error al eliminar el producto.
     */
    @Override
    public void eliminarProducto(Long idProducto) throws NegocioException {
        
        // Se valida que el id no sea nulo.
        if(idProducto == null){
            throw new NegocioException("El Id del producto a eliminar no puede ser nulo.");
        }
        
        try {
            
            // Se elimina el producto utilizando el objeto que implementa 
            // la interfaz IProductosDAO
            productosDAO.eliminarProducto(idProducto);
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException(ex.getMessage());
        }
        
    }
    
}
