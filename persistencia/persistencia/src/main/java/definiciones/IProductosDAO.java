package definiciones;

import entidades.Categoria;
import entidades.Producto;
import enumeradores.Formato;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Archivo: IProductosDAO.java
 *
 * Interfaz que define los métodos que deben implementar las clases que
 * manipulen la base de datos, sobre la entidad Producto.
 *
 * @author Romo López Manuel ID: 00000253080
 *
 */
public interface IProductosDAO {

    /**
     * Permite el registro de un nuevo objeto de tipo Producto.
     *
     * @param nuevoProducto Objeto Producto que contiene la información
     * necesaria para el registro del nuevo Producto.
     * @return Objeto Producto, es el nuevo objeto Producto que fue persistido
     * en la base de datos.
     * @throws PersistenciaException Se lanza si hay algún dato obligatorio
     * faltante o si ocurre un error al registrar el Producto.
     *
     */
    public abstract Producto registrarProducto(Producto nuevoProducto) throws PersistenciaException;

    /**
     * Permite obtener un objeto Producto almacenado en la base de datos, cuyo
     * valor de atributo id sea el recibido como parámetro.
     *
     * @param idProducto Objeto Long que representa el id del Producto buscado.
     * @return Objeto Producto que tiene el id del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Producto
     * almacenado con el valor de id del parámetro o si el valor del parámetro
     * idProducto es nulo.
     */
    public abstract Producto consultarProducto(Long idProducto) throws PersistenciaException;

    /**
     * Permite obtener la lista de objetos Producto alamcenados en la base de
     * datos. Dados los filtros de los parámetros.
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
     * @return Objeto {@literal List<Producto>} que contiene la lista de ojbetos
     * Producto almacenados, que cumplen con los filtros de los parámetros.
     * @throws PersistenciaException Se lanza si ocurre un error en la consulta.
     */
    public abstract List<Producto> consultarProductosConFiltros(
            String tituloAutor,
            List<Categoria> categorias,
            List<Formato> formatos,
            Double precioMinimo,
            Double precioMaximo)
            throws PersistenciaException;

    /**
     * Permite actualizar los valores de los campos de Producto almacenado.
     *
     * @param productoActualizado Objeto Producto que contiene los nuevos
     * valores del Producto a modificar.
     *
     * @throws PersistenciaException Se lanza si algún valor actualizado es
     * nulo.
     */
    public abstract void actualizarProducto(Producto productoActualizado) throws PersistenciaException;

    /**
     * Permite eliminar un Producto de la base de datos, que tiene el Id del
     * parámetro.
     *
     * @param idProducto Objeto Long que representa el Id del Producto a
     * eliminar.
     * @throws PersistenciaException Se lanza si ocurre un error al eliminar el
     * Producto.
     */
    public abstract void eliminarProducto(Long idProducto) throws PersistenciaException;

}
