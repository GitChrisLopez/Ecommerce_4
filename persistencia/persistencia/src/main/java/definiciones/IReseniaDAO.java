package definiciones;

import entidades.Resenia;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define los métodos que deben implementar las clases que
 * manipulen la base de datos, sobre la entidad Resenia.
 * @author norma
 */
public interface IReseniaDAO {
    
    /**
     * Guarda una nueva resenia en la base de datos.
     *
     * @param resenia Objeto que se desea guardar.
     * @return El mismo objeto resenia si se guardó correctamente.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public Resenia persistirResenia(Resenia resenia) throws PersistenciaException;

    /**
     * Elimina un resenia de la base de datos a partir de su ID.
     *
     * @param idResenia ID de la resenia que se desea eliminar.
     * @return true si se eliminó correctamente, false si no se encontró.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean eliminarResenia(Long idResenia) throws PersistenciaException;

    /**
     * Actualiza una resenia de la base de datos.
     * @param idResenia ID de la resenia que se desea actualizar.
     * @param nuevoComentario el nuevo comentario de la resenia.
     * @return true si se actualizó correctamente, false si no se actualizó.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean actualizarComentario(Long idResenia, String nuevoComentario) throws PersistenciaException;

    /**
     * Obtiene todas las resenias de la base de datos.
     * @return Lista de resenias.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public List<Resenia> obtenerTodasLasResenias() throws PersistenciaException;

    /**
     * Obtiene una resenia de la base de datos a partir de su ID.
     * @param idResenia ID de la resenia que se desea obtener.
     * @return El objeto resenia que se encontró con el id.
     * @throws PersistenciaException Si ocurre un error durante la operación. 
     */
    public Resenia obtenerResenia(Long idResenia) throws PersistenciaException;

    /**
     * Obtiene una lista de resenias que coincidan con los filtros
     * proporcionados.
     *
     * @param libro nombre del libro para aplicar el filtro y buscar las resenias.
     * @return Lista de resenias que cumple con el filtro.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    public List<Resenia> obtenerReseniasFiltradasPorLibro(String libro) throws PersistenciaException;
    
    public List<Resenia> obtenerReseniasPorIdLibro(Long idLibro) throws PersistenciaException;
}
