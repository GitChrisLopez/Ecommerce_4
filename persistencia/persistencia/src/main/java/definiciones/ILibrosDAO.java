
package definiciones;

import entidades.Libro;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Archivo: ILibrosDAO.java
 * 
 * Interfaz que define los métodos que deben implementar las clases que manipulen la base de datos,
 * sobre la entidad Libro.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 * 
 */
public interface ILibrosDAO {
    
    /**
     * Permite el registro de un nuevo objeto de tipo Libro.
     * 
     * @param nuevoLibro Objeto Libro que contiene la información necesaria
     * para el registro del nuevo Libro.
     * @return Objeto Libro, es el nuevo objeto Libro que fue persistido en la base de datos.
     * @throws PersistenciaException Se lanza si hay algún dato obligatorio faltante o si
     * ocurre un error al registrar el Libro.
     * 
     */
    public abstract Libro registrarLibro(Libro nuevoLibro) throws PersistenciaException;
    
    /**
     * Permite obtener un objeto Libro almacenado en la base de datos, cuyo valor
     * de atributo id sea el recibido como parámetro.
     * 
     * @param idLibro Objeto Long que representa el id del Libro buscado.
     * @return Objeto Libro que tiene el id del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Libro
     * almacenado con el valor de id del parámetro o si el valor del parámetro idLibro
     * es nulo.
     */
    public abstract Libro consultarLibro(Long idLibro) throws PersistenciaException;
    
    /**
     * Permite obtener un objeto Libro almacenado en la base de datos, cuyo valor
     * de atributo titulo sea el recibido como parámetro.
     * 
     * @param titulo Objeto Long que representa el título del Libro buscado.
     * @return Objeto Libro que tiene el título del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Libro
     * almacenado con el valor de título del parámetro o si el valor del parámetro titulo
     * es nulo.
     */
    public abstract Libro consultarLibro(String titulo) throws PersistenciaException;
    
    /**
     * Permite obtener una lista de objetos Libro que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un título completo o parcial de un Libro.
     * @return Objeto {@literal List<Libro>} que representa la lista de Libros coincidentes.
     * @throws PersistenciaException Se lanza si ocurre un error al consultar los Libros.
     */
    public abstract List<Libro> consultarLibros(String titutlo) throws PersistenciaException;
    
    /**
     * Permite obtener la lista de Libros almacenada en la base de datos.
     * 
     * @return Objeto {@literal List<Libro>} que representa la lista de Libros
     * consultadas.
     * @throws PersistenciaException Se lanza si ocurre un error al realizar la consulta
     * de Libros.
     */
    public abstract List<Libro> consultarLibros() throws PersistenciaException;
    
    /**
     * Permite actualizar los valores de los campos de un Libro almacenado.
     * 
     * @param libroActualizado Objeto Libro que contiene los nuevos valores
     * del nuevo Libro a modificar.
     * @throws PersistenciaException Se lanza si algún valor actualizado es nulo.
     */
    public abstract void actualizarLibro(Libro libroActualizado) throws PersistenciaException;
}
