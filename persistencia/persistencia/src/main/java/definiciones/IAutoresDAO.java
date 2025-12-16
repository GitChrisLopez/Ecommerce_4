
package definiciones;

import entidades.Autor;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Archivo: IAutoresDAO.java
 * 
 * Interfaz que define los métodos que deben implementar las clases que manipulen la base de datos,
 * sobre la entidad Autor.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 * 
 */
public interface IAutoresDAO {
    /**
     * Permite el registro de un nuevo objeto de tipo Autor.
     * 
     * @param nuevoAutor Objeto Autor que contiene la información necesaria
     * para el registro de la nueva Autor.
     * @return Objeto Autor, es el nuevo objeto Autor que fue persistido
     * en la base de datos.
     * @throws PersistenciaException Se lanza si hay algún dato obligatorio faltante o si
     * ocurre un error al registrar el Autor.
     * 
     */
    public abstract Autor registrarAutor(Autor nuevoAutor) throws PersistenciaException;
    
    /**
     * Permite obtener un objeto Autor almacenado en la base de datos, cuyo valor
     * de atributo id sea el recibido como parámetro.
     * 
     * @param idAutor Objeto Long que representa el id del Autor buscado.
     * @return Objeto Autor que tiene el id del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Autor
     * almacenado con el valor de id del parámetro o si el valor del parámetro idAutor
     * es nulo.
     */
    public abstract Autor consultarAutor(Long idAutor) throws PersistenciaException;
    
    /**
     * Permite obtener una lista de objetos Autor que tienen el nombre, apellido materno o paterno recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de un Autor.
     * @return Objeto {@literal List<Autor>} que representa la lista de Autores coincidentes.
     * @throws PersistenciaException Se lanza si ocurre un error al consultar los Autores.
     */
    public abstract List<Autor> consultarAutores(String nombre) throws PersistenciaException;
    
    /**
     * Permite obtener la lista de Autores almacenada en la base de datos.
     * 
     * @return Objeto {@literal List<Autores>} que representa la lista de Autores
     * consultadas.
     * @throws PersistenciaException Se lanza si ocurre un error al realizar la consulta
     * de Autores.
     */
    public abstract List<Autor> consultarAutores() throws PersistenciaException;
    
    /**
     * Permite actualizar los valores de los campos de un Autor almacenado.
     * 
     * @param autorActualizado Objeto Autor que contiene los nuevos valores
     * del nuevo Autor a modificar.
     * @throws PersistenciaException Se lanza si algún valor actualizado es nulo.
     */
    public abstract void actualizarAutor(Autor autorActualizado) throws PersistenciaException;
}
