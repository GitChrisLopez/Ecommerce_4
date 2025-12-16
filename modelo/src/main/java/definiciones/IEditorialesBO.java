
package definiciones;

import dominio.EditorialDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: IEditorialesBO
 * 
 * Interfaz que define los métodos que deben implementar las clases que realizan 
 * opraciones de negocio sobre Editoriales.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 */

public interface IEditorialesBO {
    
    /**
     * Permite registrar una nueva editorial.
     * 
     * @param nuevaEditorial Objeto DTO que contiene los valores de los atributos
     * necesarios para crear una nueva editorial.
     * @return Objeto Editorial, representa la editorial registrada.
     * @throws NegocioException Se lanza si algún atributo de la editorial recibido es nulo o
     * si ocurre una excepción al registrarla.
     */
    public abstract EditorialDTO registrarEditorial(EditorialDTO nuevaEditorial) throws NegocioException;
    
    /**
     * Permite obtener los datos de una editorial a partir de su Id.
     * 
     * @param idProducto Dato Long que representa el Id de la editorial a consultar.
     * @return Objeto EditorialDTO que contiene los datos de la editorial consultada.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar la editoria, o si 
     * el Id del parámetro es nulo.
     */
    public abstract EditorialDTO consultarEditorial(Long idEditorial) throws NegocioException;
    
    /**
     * Permite obtener una lista editoriales que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de una editorial.
     * @return Objeto {@literal List<EditorialDTO>} que representa la lista de editoriales coincidentes.
     * @throws NegocioException Se lanza si ocurre un error al consultar las editoriales, o el valor del nombre
     * del parámetro es nulo.
     */
    public abstract List<EditorialDTO> consultarEditoriales(String nombre) throws NegocioException;
    
    
    /**
     * Permite obtener la lista de editoriales registradas.
     * 
     * @return Objeto {@literal List<EditorialDTO>} que representa la lista de editoriales
     * consultadas.
     * @throws NegocioException Se lanza si ocurre un error al realizar la consulta
     * de editoriales.
     */
    public abstract List<EditorialDTO> consultarEditoriales() throws NegocioException;
    
    /**
     * Permite actualizar una editorial.
     * 
     * @param editorialActualizada Objeto EditorialDTO que contiene los nuevos datos de una editorial.
     * @throws NegocioException Se lanza si algún valor actualizado es nulo u ocurre una excepción al actualizar la editorial.
     */
    public abstract void actualizarEditorial(EditorialDTO editorialActualizada) throws NegocioException;
    
    
}
