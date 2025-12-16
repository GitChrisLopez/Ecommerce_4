
package definiciones;

import dominio.AutorDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * Archivo: IAutoresBO
 * 
 * Interfaz que define los métodos que deben implementar las clases que realizan 
 * opraciones de negocio sobre autores.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 */
public interface IAutoresBO {
    
    /**
     * Permite registrar un nuevo autor.
     * 
     * @param nuevoAutor Objeto DTO que contiene los valores de los atributos
     * necesarios para crear un nuevo autor.
     * @return Objeto Autor, representa el autor registrado.
     * @throws NegocioException Se lanza si algún atributo del autor recibido es nulo o
     * si ocurre una excepción al registrarla.
     */
    public abstract AutorDTO registrarAutor(AutorDTO nuevoAutor) throws NegocioException;
    
    /**
     * Permite obtener los datos de un autor a partir de su Id.
     * 
     * @param idProducto Dato Long que representa el Id del autor a consultar.
     * @return Objeto AutorDTO que contiene los datos del autor consultado.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar el autor, o si 
     * el Id del parámetro es nulo.
     */
    public abstract AutorDTO consultarAutor(Long idAutor) throws NegocioException;
    
    /**
     * Permite obtener una lista de autores que tienen el nombre, apellido paterno o materno recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de un autor.
     * @return Objeto {@literal List<AutorDTO>} que representa la lista de autores coincidentes.
     * @throws NegocioException Se lanza si ocurre un error al consultar las autores, o el valor del nombre
     * del parámetro es nulo.
     */
    public abstract List<AutorDTO> consultarAutores(String nombre) throws NegocioException;
    
    /**
     * Permite obtener la lista de autores registrados.
     * 
     * @return Objeto {@literal List<AutorDTO>} que representa la lista de autores.
     * consultadas.
     * @throws NegocioException Se lanza si ocurre un error al realizar la consulta
     * de autores.
     */
    public abstract List<AutorDTO> consultarAutores() throws NegocioException;
    
    /**
     * Permite actualizar un autor.
     * 
     * @param autorActualizado Objeto AutorDTO que contiene los nuevos datos de un autor.
     * @throws NegocioException Se lanza si algún valor actualizado es nulo u ocurre una excepción al actualizar el autor.
     */
    public abstract void actualizarAutor(AutorDTO autorActualizado) throws NegocioException;
}
