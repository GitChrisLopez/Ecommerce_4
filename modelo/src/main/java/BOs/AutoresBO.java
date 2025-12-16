
package BOs;

import definiciones.IAutoresBO;
import definiciones.IAutoresDAO;
import dominio.AutorDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import mappers.MapperAutor;

/**
 *
 * Archivo: AutoresBO.java
 * 
 * Clase que implementa la iterfaz {@link IAutoresBO} y que realiza operaciones
 * de negocio sobre autores.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class AutoresBO implements IAutoresBO{
    
    private IAutoresDAO autoresDAO;

    private int CANTIDAD_MAXIMA_CARACTERES_NOMBRES = 50;
    private int CANTIDAD_MAXIMA_CARACTERES_APELLIDO_PATERNO = 50;
    private int CANTIDAD_MAXIMA_CARACTERES_APELLIDO_MATERNO = 50;
    
    public AutoresBO(IAutoresDAO autoresDAO){
        this.autoresDAO = autoresDAO;
    }
    
    /**
     * Implementación del método registrarAutor() de la interfaz {@link IAutoresBO}, 
     * permite registrar una nueva categoría.
     * 
     * @param nuevoAutor Objeto DTO que contiene los valores de los atributos
     * necesarios para crear un nuevo Autor.
     * @return Objeto Autor, representa la categoría registrada.
     * @throws NegocioException Se lanza si algún atributo de la categoría recibido es nulo o
     * si ocurre una excepción al registrarla.
     */
    @Override
    public AutorDTO registrarAutor(AutorDTO nuevoAutor) throws NegocioException {
        
        // Se valida que nombre no es nulo.
        if(nuevoAutor.getNombre() == null || nuevoAutor.getNombre().isBlank()){
            throw new NegocioException("El autor debe tener un nombre.");
        }
        
        // Se valida que el nombre no exceda la cantidad máxima de caracteres.
        if(nuevoAutor.getNombre().length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRES){
            throw new NegocioException("El nombre del autor no debe pasar los " + CANTIDAD_MAXIMA_CARACTERES_NOMBRES + " caracteres.");
        }
        
        // Se valida que el apellido paterno no es nulo.
        if(nuevoAutor.getApellidoPaterno()== null || nuevoAutor.getApellidoPaterno().isBlank()){
            throw new NegocioException("El autor debe tener un apellido paterno.");
        }
        
        // Se valida que el apellido paterno no exceda la cantidad máxima de caracteres.
        if(nuevoAutor.getApellidoPaterno().length() > CANTIDAD_MAXIMA_CARACTERES_APELLIDO_PATERNO){
            throw new NegocioException("El apellido paterno del autor no debe pasar los " + CANTIDAD_MAXIMA_CARACTERES_APELLIDO_PATERNO + " caracteres.");
        }
        
        // Se valida que el apellido materno no exceda la cantidad máxima de caracteres (si lo tiene).
        if(nuevoAutor.getApellidoMaterno() != null &&  nuevoAutor.getApellidoMaterno().length() > CANTIDAD_MAXIMA_CARACTERES_APELLIDO_MATERNO){
            throw new NegocioException("El apellido materno del autor no debe pasar los " + CANTIDAD_MAXIMA_CARACTERES_APELLIDO_MATERNO + " caracteres.");
        }
        
        try{
            
            // Se registra el autor utilizando el objeto que implementa la interfaz IAutoresDAO.
            return MapperAutor.toDto(autoresDAO.registrarAutor(MapperAutor.toEntity(nuevoAutor)));
            
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Implementación del método consultarAutor de la interfaz {@link IAutoresBO}, 
     * permite obtener los datos de una autor a partir de su Id.
     * 
     * @param idAutor Dato Long que representa el Id del autor a consultar.
     * @return Objeto AutorDTO que contiene los datos del autor consultado.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar el autor, o si 
     * el Id del parámetro es nulo.
     */
    @Override
    public AutorDTO consultarAutor(Long idAutor) throws NegocioException {
        
        // Se valida el Id del autor.
        if(idAutor == null){
            throw new NegocioException("El Id del autor a consultar no puede ser nulo.");
        }
        
        try{
            return MapperAutor.toDto(autoresDAO.consultarAutor(idAutor));
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Implementación del método consultarAutores() de la interfaz {@link IAutoresBO}, 
     * permite obtener una lista de autores que tienen el nombre recibido, apellido paterno o materno, o parte de uno de ellos.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de un Autor.
     * @return Objeto {@literal List<AutorDTO>} que representa la lista de autores coincidentes.
     * @throws NegocioException Se lanza si ocurre un error al consultar las autores, o el valor del nombre
     * del parámetro es nulo.
     */
    @Override
    public List<AutorDTO> consultarAutores(String nombre) throws NegocioException {
        
        // Se valida el nombre del autor.
        if(nombre == null){
            throw new NegocioException("El nombre del autor a consultar no puede ser nulo.");
        }
        
        try{
            return MapperAutor.toDtoList(autoresDAO.consultarAutores(nombre));
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
        
    }

    /**
     * Implementación del método consultarAutores() de la interfaz {@link IAutoresBOs}, 
     * permite obtener la lista de autores registrados.
     * 
     * @return Objeto {@literal List<AutorDTO>} que representa la lista de autores
     * consultados.
     * @throws NegocioException Se lanza si ocurre un error al realizar la consulta
     * de autores.
     */
    @Override
    public List<AutorDTO> consultarAutores() throws NegocioException {
        
        try{
            return MapperAutor.toDtoList(autoresDAO.consultarAutores());
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
        
    }

    /**
     * Implementación del método actualizarAutor() de la interfaz {@link IAutorBO}, 
     * permite actualizar un autor.
     * 
     * @param autorActualizado Objeto Autor que contiene los nuevos datos de un autor.
     * @throws NegocioException Se lanza si algún valor actualizado es nulo u ocurre una excepción al actualizar el autor.
     */
    @Override
    public void actualizarAutor(AutorDTO autorActualizado) throws NegocioException {
        
        // Se valida que el Id no sea nulo.
        if(autorActualizado.getId() == null){
            throw new NegocioException("El autor actualizado debe tener un Id.");
        }
        
        // Se valida que el nombre no sea nulo o esté vacío.
        if(autorActualizado.getNombre() == null || autorActualizado.getNombre().isBlank()){
            throw new NegocioException("El autor actualizado debe tener un nombre.");
        }
        
        // Se valida que el nombre no exceda la cantidad máxima de caracteres.
        if(autorActualizado.getNombre().length() > CANTIDAD_MAXIMA_CARACTERES_NOMBRES){
            throw new NegocioException("El autor actualizado no debe tener un nombre que exceda los " + CANTIDAD_MAXIMA_CARACTERES_NOMBRES + " caracteres.");
        }
        
        // Se valida que el apellido paterno no sea nulo o esté vacío.
        if(autorActualizado.getApellidoPaterno() == null || autorActualizado.getApellidoPaterno().isBlank()){
            throw new NegocioException("El autor actualizado debe tener un apellido paterno.");
        }
        
        // Se valida que el apellido paterno no exceda la cantidad máxima de caracteres.
        if(autorActualizado.getApellidoPaterno().length() > CANTIDAD_MAXIMA_CARACTERES_APELLIDO_PATERNO){
            throw new NegocioException("El autor actualizado no debe tener un apellido paterno que exceda de los " + CANTIDAD_MAXIMA_CARACTERES_APELLIDO_PATERNO + " caracteres.");
        }
        
        // Se valida que el apellido materno no exceda la cantidad máxima de caracteres (si lo tiene).
        if(autorActualizado.getApellidoMaterno() != null &&  autorActualizado.getApellidoMaterno().length() > CANTIDAD_MAXIMA_CARACTERES_APELLIDO_MATERNO){
            throw new NegocioException("El autor actualizado no debe tener un apellido materno que exceda de los " + CANTIDAD_MAXIMA_CARACTERES_APELLIDO_MATERNO + " caracteres.");
        }
        
        try {
            
            // Se actualiza la categoría utilizando el objeto que implementa 
            // la interfaz ICategoriasBO.
            autoresDAO.actualizarAutor(MapperAutor.toEntity(autorActualizado));
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException(ex.getMessage());
        }
    }
}
