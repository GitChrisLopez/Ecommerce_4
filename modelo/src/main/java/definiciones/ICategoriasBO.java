
package definiciones;

import dominio.CategoriaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: ICategoriasBO
 * 
 * Interfaz que define los métodos que deben implementar las clases que realizan 
 * opraciones de negocio sobre Categorías.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 */

public interface ICategoriasBO {
    
    
    /**
     * Permite registrar una nueva categoría.
     * 
     * @param nuevaCategoria Objeto DTO que contiene los valores de los atributos
     * necesarios para crear una nueva categoría.
     * @return Objeto Categoria, representa la categoria registrada.
     * @throws NegocioException Se lanza si algún atributo de la categoria recibido es nulo o
     * si ocurre una excepción al registrarla.
     */
    public abstract CategoriaDTO registrarCategoria(CategoriaDTO nuevaCategoria) throws NegocioException;
    
    /**
     * Permite obtener los datos de una categoría a partir de su Id.
     * 
     * @param idCategoria Dato Long que representa el Id de la categoría a consultar.
     * @return Objeto CategoriaDTO que contiene los datos de la categoría consultada.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar la categoría, o si 
     * el Id del parámetro es nulo.
     */
    public abstract CategoriaDTO consultarCategoria(Long idCategoria) throws NegocioException;
    
    /**
     * Permite obtener una lista de categorías que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de una categoría.
     * @return Objeto {@literal List<CategoriaDTO>} que representa la lista de categorías coincidentes.
     * @throws NegocioException Se lanza si ocurre un error al consultar las categorías, o el valor del nombre
     * del parámetro es nulo.
     */
    public abstract List<CategoriaDTO> consultarCategorias(String nombre) throws NegocioException;
    
    /**
     * Permite obtener la lista de categorías registradas.
     * 
     * @return Objeto {@literal List<CategoriaDTO>} que representa la lista de categorías
     * consultadas.
     * @throws NegocioException Se lanza si ocurre un error al realizar la consulta
     * de categorías.
     */
    public abstract List<CategoriaDTO> consultarCategorias() throws NegocioException;
    
    /**
     * Permite actualizar una categoría.
     * 
     * @param categoriaActualizada Objeto CategoriaDTO que contiene los nuevos datos de una categoría.
     * @throws NegocioException Se lanza si algún valor actualizado es nulo u ocurre una excepción al actualizar la categoría.
     */
    public abstract void actualizarCategoria(CategoriaDTO categoriaActualizada) throws NegocioException;
}
