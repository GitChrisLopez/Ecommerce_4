
package BOs;

import definiciones.IEditorialesBO;
import definiciones.IEditorialesDAO;
import dominio.EditorialDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import mappers.MapperEditorial;

/**
 *
 * Archivo: EditorialesBO.java
 * 
 * Clase que implementa la iterfaz {@link IEditorialesBO} y que realiza operaciones
 * de negocio sobre editoriales.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */

public class EditorialesBO implements IEditorialesBO{
    
    private IEditorialesDAO editorialesDAO;
    
    private int CARACTERES_MAXIMO_NOMBRE = 100;

    public EditorialesBO(IEditorialesDAO editorialesDAO){
        
        this.editorialesDAO = editorialesDAO;
        
    }
    
    /**
     * Implementación del método registrarEditorial() de la interfaz {@link IEditorialesBO}, 
     * permite registrar una nueva editorial.
     * 
     * @param nuevaEditorial Objeto DTO que contiene los valores de los atributos
     * necesarios para crear una nueva editorial.
     * @return Objeto Editorial, representa la editorial registrada.
     * @throws NegocioException Se lanza si algún atributo de la editorial recibido es nulo o
     * si ocurre una excepción al registrarla.
     */
    @Override
    public EditorialDTO registrarEditorial(EditorialDTO nuevaEditorial) throws NegocioException {
        
        // Se valida que nombre no es nulo o está vacío.
        if(nuevaEditorial.getNombre() == null || nuevaEditorial.getNombre().isBlank()){
            throw new NegocioException("La editorial debe tener un nombre.");
        }
        
        // Se valida que nombre no exceda la cantidad máxima de caracteres.
        if(nuevaEditorial.getNombre().length() > CARACTERES_MAXIMO_NOMBRE){
            throw new NegocioException("El nombre de la editorial no debe superar los " + CARACTERES_MAXIMO_NOMBRE + " caracteres.");
        }
      
        try{
            
            // Se registra la editorial utilizando el objeto que implementa la interfaz IEditorialesDAO.
            return MapperEditorial.toDto(editorialesDAO.registrarEditorial(MapperEditorial.toEntity(nuevaEditorial)));
            
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Implementación del método consultarEditorial() de la interfaz {@link IEditorialesBO}, 
     * permite obtener los datos de una editorial a partir de su Id.
     * 
     * @param idEditorial Dato Long que representa el Id de la editorial a consultar.
     * @return Objeto EditorialDTO que contiene los datos de la editorial consultada.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar la editorial, o si 
     * el Id del parámetro es nulo.
     */
    @Override
    public EditorialDTO consultarEditorial(Long idEditorial) throws NegocioException {
        
        // Se valida el Id de la editorial.
        if(idEditorial == null){
            throw new NegocioException("El Id de la editorial a consultar no puede ser nulo.");
        }
        
        try{
            return MapperEditorial.toDto(editorialesDAO.consultarEditorial(idEditorial));
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Implementación del método consultarEditoriales() de la interfaz {@link IEditorialesBO}, 
     * permite obtener una lista editoriales que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de una editorial.
     * @return Objeto {@literal List<EditorialDTO>} que representa la lista de editoriales coincidentes.
     * @throws NegocioException Se lanza si ocurre un error al consultar las editoriales, o el valor del nombre
     * del parámetro es nulo.
     */
    @Override
    public List<EditorialDTO> consultarEditoriales(String nombre) throws NegocioException {
        
        // Se valida el nombre de la editorial.
        if(nombre == null){
            throw new NegocioException("El nombre de la editorial a consultar no puede ser nulo.");
        }
        
        try{
            return MapperEditorial.toDtoList(editorialesDAO.consultarEditoriales(nombre));
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
        
    }

    /**
     * Implementación del método consultarEditoriales() de la interfaz {@link IEditorialesBO}, 
     * permite obtener la lista de editoriales registradas.
     * 
     * @return Objeto {@literal List<EditorialDTO>} que representa la lista de editoriales
     * consultadas.
     * @throws NegocioException Se lanza si ocurre un error al realizar la consulta
     * de editoriales.
     */
    @Override
    public List<EditorialDTO> consultarEditoriales() throws NegocioException {
        
        try{
            return MapperEditorial.toDtoList(editorialesDAO.consultarEditoriales());
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
        
    }

    /**
     * Implementación del método actualizarEditorial() de la interfaz {@link IEditorialesBO}, 
     * permite actualizar una editorial.
     * 
     * @param editorialActualizada Objeto EditorialDTO que contiene los nuevos datos de una editorial.
     * @throws NegocioException Se lanza si algún valor actualizado es nulo u ocurre una excepción al actualizar la editorial.
     */
    @Override
    public void actualizarEditorial(EditorialDTO editorialActualizada) throws NegocioException {
        
        // Se valida que el Id no sea nulo.
        if(editorialActualizada.getId() == null){
            throw new NegocioException("La editorial actualizada debe tener un Id.");
        }
        
        // Se valida que el nombre no sea nulo o esté vacío.
        if(editorialActualizada.getNombre() == null || editorialActualizada.getNombre().isBlank()){
            throw new NegocioException("La editorial actualizada debe tener un nombre.");
        }
        
        // Se valida que nombre no exceda la cantidad máxima de caracteres.
        if(editorialActualizada.getNombre().length() > CARACTERES_MAXIMO_NOMBRE){
            throw new NegocioException("La editorial actualizada no debe tener un nombre que supere los " + CARACTERES_MAXIMO_NOMBRE + " caracteres.");
        }
        
        try {
            
            // Se actualiza la editorial utilizando el objeto que implementa 
            // la interfaz IEditorialesBO.
            editorialesDAO.actualizarEditorial(MapperEditorial.toEntity(editorialActualizada));
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException(ex.getMessage());
        }
    }
    
    
}
