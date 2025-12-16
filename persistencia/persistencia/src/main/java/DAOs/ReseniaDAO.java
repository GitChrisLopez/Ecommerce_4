package DAOs;

import com.persistencia.ManejadorConexiones;
import definiciones.IReseniaDAO;
import entidades.Resenia;
import excepciones.PersistenciaException;
import java.util.List;
import jakarta.persistence.EntityManager;

/**
 * Clase que se encarga de realizar
 * operaciones de persistencia sobre objetos Resenia, como guardar,
 * eliminar, actualizar, y realizar consultas filtradas. 
 * Utiliza JPA para interactuar con la base de datos.
 * @author norma
 */
public class ReseniaDAO implements IReseniaDAO {

    /**
     * Guarda una nueva resenia en la base de datos.
     *
     * @param resenia Objeto que se desea guardar.
     * @return El mismo objeto resenia si se guardó correctamente.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Resenia persistirResenia(Resenia resenia) throws PersistenciaException {

        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(resenia);
            em.getTransaction().commit();

            return resenia;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al persistir resenia", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }

    /**
     * Elimina un resenia de la base de datos a partir de su ID.
     *
     * @param idResenia ID de la resenia que se desea eliminar.
     * @return true si se eliminó correctamente, false si no se encontró.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public boolean eliminarResenia(Long idResenia) throws PersistenciaException {

        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            Resenia resenia = em.find(Resenia.class, idResenia);
            if (resenia != null) {
                em.remove(resenia);
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al eliminar resenia", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Actualiza una resenia de la base de datos.
     * @param idResenia ID de la resenia que se desea actualizar.
     * @param nuevoComentario el nuevo comentario de la resenia.
     * @return true si se actualizó correctamente, false si no se actualizó.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public boolean actualizarComentario(Long idResenia, String nuevoComentario) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();

            int filasAfectadas = em.createQuery(
                    "UPDATE Resenia r SET r.comentario = :comentario WHERE r.id = :id")
                    .setParameter("comentario", nuevoComentario)
                    .setParameter("id", idResenia)
                    .executeUpdate();

            em.getTransaction().commit();

            return filasAfectadas == 1;

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar el comentario de la reseña con ID " + idResenia, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Obtiene todas las resenias de la base de datos.
     * @return Lista de resenias.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public List<Resenia> obtenerTodasLasResenias() throws PersistenciaException {

        EntityManager em = ManejadorConexiones.getEntityManager();
        List<Resenia> resenias = null;
        try {

            resenias = em.createQuery("SELECT r FROM Resenia r", Resenia.class).getResultList();

            return resenias;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todas las resenias", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Obtiene una resenia de la base de datos a partir de su ID.
     * @param idResenia ID de la resenia que se desea obtener.
     * @return El objeto resenia que se encontró con el id.
     * @throws PersistenciaException Si ocurre un error durante la operación. 
     */
    @Override
    public Resenia obtenerResenia(Long idResenia) throws PersistenciaException {

        EntityManager em = ManejadorConexiones.getEntityManager();

        try {
            return em.find(Resenia.class, idResenia);
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener resenia con id" + idResenia, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Obtiene una lista de resenias que coincidan con los filtros
     * proporcionados.
     *
     * @param libro nombre del libro para aplicar el filtro y buscar las resenias.
     * @return Lista de resenias que cumple con el filtro.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public List<Resenia> obtenerReseniasFiltradasPorLibro(String libro) throws PersistenciaException {

        EntityManager em = ManejadorConexiones.getEntityManager();

        List<Resenia> resenias = null;
        try {

            String jpql = "SELECT r FROM Resenia r WHERE r.libro.titulo LIKE :busqueda";

            String patronBusqueda = "%" + libro.toLowerCase() + "%";

            resenias = em.createQuery(jpql, Resenia.class)
                    .setParameter("busqueda", patronBusqueda)
                    .getResultList();

            return resenias;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener resenias filtradas por libro: " + libro, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Resenia> obtenerReseniasPorIdLibro(Long idLibro) throws PersistenciaException {

        EntityManager em = ManejadorConexiones.getEntityManager();

        List<Resenia> resenias = null;
        try {

            String jpql = "SELECT r FROM Resenia r WHERE r.libro.id = :idLibro";

            resenias = em.createQuery(jpql, Resenia.class)
                    .setParameter("idLibro", idLibro)
                    .getResultList();

            return resenias;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener resenias por ID de libro: " + idLibro, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
