
package definiciones;

import dominio.LibroDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: ILibrosBO.java
 * 
 * Interfaz que define los métodos que deben implementar las clases que realizan 
 * opraciones de negocio sobre Libros.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 */
public interface ILibrosBO {
    
    /**
     * Permite registrar un nuevo libro.
     * 
     * @param nuevoLibro Objeto DTO que contiene los valores de los atributos
     * necesarios para crear un nuevo Libro.
     * @return Objeto Libro, representa el libro registrado.
     * @throws NegocioException Se lanza si algún atributo del libro recibido es nulo o
     * si ocurre una excepción al registrarla.
     */
    public abstract LibroDTO registrarLibro(LibroDTO nuevoLibro) throws NegocioException;
    
    /**
     * Permite obtener los datos de un libro a partir de su Id.
     * 
     * @param idLibro Dato Long que representa el Id del libro a consultar.
     * @return Objeto LibroDTO que contiene los datos del libro consultado.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar la editoria, o si 
     * el Id del parámetro es nulo.
     */
    public abstract LibroDTO consultarLibro(Long idLibro) throws NegocioException;
    
    /**
     * Permite obtener los datos de un libro a partir de su título.
     * 
     * @param titulo Objet String que representa el título del libro a consultar.
     * @return Objeto LibroDTO que contiene los datos del libro consultado.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar la editoria, o si 
     * el título del parámetro es nulo.
     */
    public abstract LibroDTO consultarLibro(String titulo) throws NegocioException;
    
    /**
     * Permite obtener una lista de libros que tienen el nombre recibido, o parte de él.
     * 
     * @param titulo Objeto String que representa un título completo o parcial de un libro.
     * @return Objeto {@literal List<LibroDTO>} que representa la lista de libros coincidentes.
     * @throws NegocioException Se lanza si ocurre un error al consultar los libros, o el valor del título
     * del parámetro es nulo.
     */
    public abstract List<LibroDTO> consultarLibros(String titulo) throws NegocioException;
    
    /**
     * Permite obtener la lista de libros registrados.
     * 
     * @return Objeto {@literal List<LibroDTO>} que representa la lista de libros
     * consultados.
     * @throws NegocioException Se lanza si ocurre un error al realizar la consulta
     * de libros.
     */
    public abstract List<LibroDTO> consultarLibros() throws NegocioException;
    
    /**
     * Permite actualizar un libro.
     * 
     * @param libroActualizado Objeto LibroDTO que contiene los nuevos datos de un libro.
     * @throws NegocioException Se lanza si algún valor actualizado es nulo u ocurre una excepción al actualizar el libro.
     */
    public abstract void actualizarLibro(LibroDTO libroActualizado) throws NegocioException;
}
