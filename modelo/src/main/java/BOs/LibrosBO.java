
package BOs;

import definiciones.ILibrosBO;
import definiciones.ILibrosDAO;
import dominio.LibroDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;
import mappers.MapperLibro;


public class LibrosBO implements ILibrosBO {
    
    private ILibrosDAO librosDAO;
    
    private int CARACTERES_MAXIMO_TITULO = 100;
    private int CARACTERES_MAXIMO_SINOPSIS = 350;
    private LocalDate FECHA_ACTUAL = LocalDate.now();

    public LibrosBO(ILibrosDAO librosDAO){
        this.librosDAO = librosDAO;
    }
    
    /**
     * Implementación del método registrarLibro() de la interfaz {@link ILibrosBO}, 
     * permite registrar una nuevo libro.
     * 
     * @param nuevoLibro Objeto DTO que contiene los valores de los atributos
     * necesarios para crear un nuevo libro.
     * @return Objeto LibroDTO, representa el libro registrada.
     * @throws NegocioException Se lanza si algún atributo del libro recibido es nulo o
     * si ocurre una excepción al registrarla.
     */
    @Override
    public LibroDTO registrarLibro(LibroDTO nuevoLibro) throws NegocioException {
        
        // Se valida que título no es nulo o está vacío.
        if(nuevoLibro.getTitulo() == null || nuevoLibro.getTitulo().isBlank()){
            throw new NegocioException("El libro debe tener un título.");
        }
        
        // Se valida que el título no exceda el máximo de caracteres.
        if(nuevoLibro.getTitulo().length() > CARACTERES_MAXIMO_TITULO){
            throw new NegocioException("El título del libro no debe superar los " + CARACTERES_MAXIMO_TITULO + " caracteres.");
        }
        
        // Se valida que el Auto no es nulo.
        if(nuevoLibro.getAutor() == null){
            throw new NegocioException("El libro debe tener un autor.");
        }
        
        // Se valida que la categoría no es nula.
        if(nuevoLibro.getCategorias() == null){
            throw new NegocioException("El libro debe tener una categoria.");
        }
        
        // Se valida que la editorial no es nula.
        if(nuevoLibro.getEditorial() == null){
            throw new NegocioException("El libro debe tener una editorial.");
        }
        
        // Se valida que la fecha de publicación no es nula.
        if(nuevoLibro.getFechaPublicacion() == null){
            throw new NegocioException("El libro debe tener una fecha de publicación.");
        }
        
        // Se valida que la fecha de publicación no sea futura.
        if(nuevoLibro.getFechaPublicacion().isAfter(FECHA_ACTUAL)){
            throw new NegocioException("La fecha de publicación no puede ser mayor que la actual.");
        }
        
        // Se valida que la sinopsis no es nula o está vacía.
        if(nuevoLibro.getSinopsis() == null || nuevoLibro.getSinopsis().isBlank()){
            throw new NegocioException("El libro debe tener una sinopsis.");
        }
        
        // Se valida que la sinopsis no exceda el máximo de caracteres.
        if(nuevoLibro.getSinopsis().length() > CARACTERES_MAXIMO_SINOPSIS){
            throw new NegocioException("La sinopsis del libro no debe superar los " + CARACTERES_MAXIMO_SINOPSIS + " caracteres.");
        }
      
        try{
            
            // Se registra el libro utilizando el objeto que implementa la interfaz ILibrosDAO.
            return MapperLibro.toDto(librosDAO.registrarLibro(MapperLibro.toEntity(nuevoLibro)));
            
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Implementación del método consultarLibro() de la interfaz {@link ILibrosBO}, 
     * permite obtener los datos de un libro a partir de su Id.
     * 
     * @param idLibro Dato Long que representa el Id del libro a consultar.
     * @return Objeto LibroDTO que contiene los datos del libro consultado.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar el libro, o si 
     * el Id del parámetro es nulo.
     */
    @Override
    public LibroDTO consultarLibro(Long idLibro) throws NegocioException {
        
        // Se valida el Id del libro.
        if(idLibro == null){
            throw new NegocioException("El Id del libro a consultar no puede ser nulo.");
        }
        
        try{
            return MapperLibro.toDto(librosDAO.consultarLibro(idLibro));
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }
    
    /**
     * Implementación del método consultarLibro() de la interfaz {@link ILibrosBO}, 
     * permite obtener los datos de un libro a partir de su título.
     * 
     * @param idLibro Dato Long que representa el título del libro a consultar.
     * @return Objeto LibroDTO que contiene los datos del libro consultado.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar el libro, o si 
     * el título del parámetro es nulo.
     */
    @Override
    public LibroDTO consultarLibro(String titulo) throws NegocioException {
        
        // Se valida el título del libro.
        if(titulo == null){
            throw new NegocioException("El título del libro a consultar no puede ser nulo.");
        }
        
        try{
            return MapperLibro.toDto(librosDAO.consultarLibro(titulo));
            
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Implementación del método consultarLibros() de la interfaz {@link ILibrosBO}, 
     * permite obtener una lista libros que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un título completo o parcial de un libro.
     * @return Objeto {@literal List<LibroDTO>} que representa la lista de libros coincidentes.
     * @throws NegocioException Se lanza si ocurre un error al consultar los libros, o el valor del título
     * del parámetro es nulo.
     */
    @Override
    public List<LibroDTO> consultarLibros(String titulo) throws NegocioException {
        
        // Se valida el título del libro.
        if(titulo == null){
            throw new NegocioException("El nombre del libro a consultar no puede ser nulo.");
        }
        
        try{
            return MapperLibro.toDtoList(librosDAO.consultarLibros(titulo));
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
        
    }

    /**
     * Implementación del método consultarLibros() de la interfaz {@link ILibrosBO}, 
     * permite obtener la lista de libros registrados.
     * 
     * @return Objeto {@literal List<LibroDTO>} que representa la lista de libros.
     * consultadas.
     * @throws NegocioException Se lanza si ocurre un error al realizar la consulta
     * de libros.
     */
    @Override
    public List<LibroDTO> consultarLibros() throws NegocioException {
        
        try{
            return MapperLibro.toDtoList(librosDAO.consultarLibros());
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
        
    }

    /**
     * Implementación del método actualizarLibro() de la interfaz {@link ILibrosBO}, 
     * permite actualizar un libro.
     * 
     * @param libroActualizado Objeto LibroDTO que contiene los nuevos datos de un libro.
     * @throws NegocioException Se lanza si algún valor actualizado es nulo u ocurre una excepción al actualizar el libro.
     */
    @Override
    public void actualizarLibro(LibroDTO libroActualizado) throws NegocioException {
        
        // Se valida que el Id no sea nulo.
        if(libroActualizado.getId()== null){
            throw new NegocioException("El libro actualizado debe tener un Id.");
        }
        
        // Se valida que título no es nulo o está vacío.
        if(libroActualizado.getTitulo() == null || libroActualizado.getTitulo().isBlank()){
            throw new NegocioException("El libro actualizado debe tener un título.");
        }
        
        // Se valida que el título no exceda el máximo de caracteres.
        if(libroActualizado.getTitulo().length() > CARACTERES_MAXIMO_TITULO){
            throw new NegocioException("El libro actualizado debe tener un título que no debe superar los " + CARACTERES_MAXIMO_TITULO + " caracteres.");
        }
        
        // Se valida que el Auto no es nulo.
        if(libroActualizado.getAutor() == null){
            throw new NegocioException("El libro actualizado debe tener una autor.");
        }
        
        // Se valida que la categoría no es nula.
        if(libroActualizado.getCategorias() == null){
            throw new NegocioException("El libro actualizado debe tener una categoria.");
        }
        
        // Se valida que la editorial no es nula.
        if(libroActualizado.getEditorial() == null){
            throw new NegocioException("El libro actualizado debe tener una editorial.");
        }
        
        // Se valida que la fecha de publicación no es nula.
        if(libroActualizado.getFechaPublicacion() == null){
            throw new NegocioException("El libro debe tener una fecha de publicación.");
        }
        
        // Se valida que la fecha de publicación no sea futura.
        if(libroActualizado.getFechaPublicacion().isAfter(FECHA_ACTUAL)){
            throw new NegocioException("La fecha de publicación no puede ser mayor que la actual.");
        }
        
        // Se valida que la sinopsis no es nula o está vacía.
        if(libroActualizado.getSinopsis() == null || libroActualizado.getSinopsis().isBlank()){
            throw new NegocioException("El libro actualizado debe tener una sinopsis.");
        }
        
        // Se valida que la sinopsis no exceda el máximo de caracteres.
        if(libroActualizado.getSinopsis().length() > CARACTERES_MAXIMO_SINOPSIS){
            throw new NegocioException("El libro actualizado debe tener una sinopsis que no exceda los " + CARACTERES_MAXIMO_SINOPSIS + " caracteres.");
        }
        
        
        try {
            
            // Se actualiza el libro utilizando el objeto que implementa 
            // la interfaz ILibrosDAO.
            librosDAO.actualizarLibro(MapperLibro.toEntity(libroActualizado));
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException(ex.getMessage());
        }
    }
    
}
