package definiciones;

import dominio.ReseniaDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define los métodos que deben implementar las clases que realizan
 * opraciones de negocio sobre resenias.
 *
 * @author norma
 */
public interface IReseniaBO {

    /**
     * Elimina una resenia.
     *
     * @param idResenia ID de la resenia a eliminar.
     * @return true si fue eliminado correctamente.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si el ID no es válido o no se pudo eliminar.
     */
    public boolean eliminarResenia(Long idResenia) throws PersistenciaException, NegocioException;

    /**
     * Actualiza el comentario de una resenia.
     *
     * @param idResenia ID de la resenia a actualizar.
     * @param nuevoComentario nuevo comentario de la resenia.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si el ID o comentario no es válido o no se pudo
     * actualizar.
     */
    public void actualizarComentarioResenia(Long idResenia, String nuevoComentario) throws PersistenciaException, NegocioException;

    /**
     * Obtiene todas las resenias.
     *
     * @return Lista de resenias.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si no se pudo obtener la lista de resenias.
     */
    public List<ReseniaDTO> obtenerResenias() throws PersistenciaException, NegocioException;

    /**
     * Obtiene una resenia específica.
     *
     * @param idResenia ID de la resenia a obtener.
     * @return Resenia específica.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si no se pudo obtener la resenia.
     */
    public ReseniaDTO obtenerReseniaPorId(Long idResenia) throws PersistenciaException, NegocioException;

    /**
     * Obtiene las resenias en relación a su libro vinculado.
     *
     * @param busqueda libro que se quiere que las resenias tengan.
     * @return Lista de resenias que les aplica el filtro.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si no se pudo obtener la lista de resenias.
     */
    public List<ReseniaDTO> obtenerReseniasFiltradasPorLibro(String busqueda) throws PersistenciaException, NegocioException;
    
    public ReseniaDTO crearResenia(ReseniaDTO resenia) throws PersistenciaException, NegocioException;
    
    public List<ReseniaDTO> obtenerReseniasPorIdLibro(Long idLibro) throws PersistenciaException, NegocioException;
}
