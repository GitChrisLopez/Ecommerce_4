
package definiciones;

import entidades.Editorial;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Archivo: IEditorialesDAO.java
 * 
 * Interfaz que define los métodos que deben implementar las clases que manipulen la base de datos,
 * sobre la entidad Editorial.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 * 
 */
public interface IEditorialesDAO {
    
    /**
     * Permite el registro de un nuevo objeto de tipo Editorial.
     * 
     * @param nuevaEditorial Objeto Editorial que contiene la información necesaria
     * para el registro de la nueva Editorial.
     * @return Objeto Editorial, es el nuevo objeto Editorial que fue persistido
     * en la base de datos.
     * @throws PersistenciaException Se lanza si hay algún dato obligatorio faltante o si
     * ocurre un error al registrar la Editorial.
     * 
     */
    public abstract Editorial registrarEditorial(Editorial nuevaEditorial) throws PersistenciaException;
    
    /**
     * Permite obtener un objeto Editorial almacenado en la base de datos, cuyo valor
     * de atributo id sea el recibido como parámetro.
     * 
     * @param idEditorial Objeto Long que representa el id de la Editorial buscada.
     * @return Objeto Editorial que tiene el id del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Editorial
     * almacenado con el valor de id del parámetro o si el valor del parámetro idEditorial
     * es nulo.
     */
    public abstract Editorial consultarEditorial(Long idEditorial) throws PersistenciaException;
    
    /**
     * Permite obtener una lista de objetos Editorial que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de una Editorial.
     * @return Objeto {@literal List<Editorial>} que representa la lista de Editoriales coincidentes.
     * @throws PersistenciaException Se lanza si ocurre un error al consultar las Editoriales.
     */
    public abstract List<Editorial> consultarEditoriales(String nombre) throws PersistenciaException;
    
    /**
     * Permite obtener la lista de Editoriales almacenada en la base de datos.
     * 
     * @return Objeto {@literal List<Editorial>} que representa la lista de Editoriales
     * consultadas.
     * @throws PersistenciaException Se lanza si ocurre un error al realizar la consulta
     * de Editoriales.
     */
    public abstract List<Editorial> consultarEditoriales() throws PersistenciaException;
    
    /**
     * Permite actualizar los valores de los campos de una Editorial almacenada.
     * 
     * @param editorialActualizada Objeto Editorial que contiene los nuevos valores
     * de la nueva Editorial a modificar.
     * @throws PersistenciaException Se lanza si algún valor actualizado es nulo.
     */
    public abstract void actualizarEditorial(Editorial editorialActualizada) throws PersistenciaException;
    
}
