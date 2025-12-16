
package definiciones;

import entidades.Categoria;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Archivo: ICategoriasDAO.java
 * 
 * Interfaz que define los métodos que deben implementar las clases que manipulen la base de datos,
 * sobre la entidad Categoria.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 * 
 */
public interface ICategoriasDAO {
    
    /**
     * Permite el registro de un nuevo objeto de tipo Categoria.
     * 
     * @param nuevaCategoria Objeto Categoria que contiene la información necesaria
     * para el registro de la nueva Categoria.
     * @return Objeto Categoria, es el nuevo objeto Categoria que fue persistido
     * en la base de datos.
     * @throws PersistenciaException Se lanza si hay algún dato obligatorio faltante o si
     * ocurre un error al registrar la Categoria.
     * 
     */
    public abstract Categoria registrarCategoria(Categoria nuevaCategoria) throws PersistenciaException;
    
    /**
     * Permite obtener un objeto Categoria almacenado en la base de datos, cuyo valor
     * de atributo id sea el recibido como parámetro.
     * 
     * @param idCategoria Objeto Long que representa el id de la Categoria buscada.
     * @return Objeto Categoria que tiene el id del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Categoria
     * almacenado con el valor de id del parámetro o si el valor del parámetro idCategoria
     * es nulo.
     */
    public abstract Categoria consultarCategoria(Long idCategoria) throws PersistenciaException;
    
    /**
     * Permite obtener una lista de objetos Categoria que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de una Categoria.
     * @return Objeto {@literal List<Categoria>} que representa la lista de Categorías coincidentes.
     * @throws PersistenciaException Se lanza si ocurre un error al consultar las Categorías.
     */
    public abstract List<Categoria> consultarCategorias(String nombre) throws PersistenciaException;
    
    /**
     * Permite obtener la lista de Categorías almacenada en la base de datos.
     * 
     * @return Objeto {@literal List<Categoria>} que representa la lista de Categorías
     * consultadas.
     * @throws PersistenciaException Se lanza si ocurre un error al realizar la consulta
     * de Categorías.
     */
    public abstract List<Categoria> consultarCategorias() throws PersistenciaException;
    
    /**
     * Permite actualizar los valores de los campos de una Categoria almacenada.
     * 
     * @param categoriaActualizada Objeto Categoria que contiene los nuevos valores
     * de la nueva Categoria a modificar.
     * @throws PersistenciaException Se lanza si algún valor actualizado es nulo.
     */
    public abstract void actualizarCategoria(Categoria categoriaActualizada) throws PersistenciaException;
}
