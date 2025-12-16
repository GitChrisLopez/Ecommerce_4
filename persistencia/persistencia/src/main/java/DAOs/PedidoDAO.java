package DAOs;

import com.persistencia.ManejadorConexiones;
import definiciones.IPedidoDAO;
import entidades.Pedido;
import enumeradores.Estado;
import excepciones.PersistenciaException;
import java.util.List;
import jakarta.persistence.EntityManager;

/**
 * Clase que se encarga de realizar operaciones de persistencia sobre objetos
 * Pedido, como guardar, actualizar su estado, y realizar consultas filtradas.
 * Utiliza JPA para interactuar con la base de datos
 *
 * @author norma
 */
public class PedidoDAO implements IPedidoDAO {

    /**
     * Guarda un nuevo pedido en la base de datos.
     *
     * @param pedido Objeto que se desea guardar.
     * @return El mismo objeto pedido si se guardó correctamente.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Pedido persistirPedido(Pedido pedido) throws PersistenciaException {

        //falta la lógica de asignarle un número único
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(pedido);
            em.getTransaction().commit();

            return pedido;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al persistir pedido", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }

    /**
     * Actualiza una pedido de la base de datos.
     *
     * @param idPedido ID de la pedido que se desea actualizar.
     * @param estado estado nuevo del pedido.
     * @return true si se actualizó correctamente, false si no se actualizó.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public boolean actualizarPedidoo(Long idPedido, Estado estado) throws PersistenciaException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();

            int filasAfectadas = em.createQuery(
                    "UPDATE Pedido p SET p.estado = :estado WHERE p.id = :id")
                    .setParameter("estado", estado)
                    .setParameter("id", idPedido)
                    .executeUpdate();

            em.getTransaction().commit();

            return filasAfectadas == 1;

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar el estado del pedido con ID " + idPedido, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Obtiene todos los pedidos de la base de datos.
     *
     * @return Lista de pedidos.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public List<Pedido> obtenerTodosLosPedidos() throws PersistenciaException {

        EntityManager em = ManejadorConexiones.getEntityManager();
        List<Pedido> pedidos = null;
        try {

            pedidos = em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();

            return pedidos;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todos los pedidos", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Obtiene un pedido de la base de datos a partir de su ID.
     *
     * @param idPedido ID de la pedido que se desea obtener.
     * @return El objeto pedido que se encontró con el id.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    @Override
    public Pedido obtenerPedido(Long idPedido) throws PersistenciaException {

        EntityManager em = ManejadorConexiones.getEntityManager();

        try {
            return em.find(Pedido.class, idPedido);
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener resenia con id" + idPedido, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Obtiene una lista de pedidos que coincidan con los filtros
     * proporcionados.
     *
     * @param numeroPedido número del pedido
     * @return Lista de pedidos que cumple con el filtro.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public List<Pedido> obtenerPedidosFiltradosPorNumero(String numeroPedido) throws PersistenciaException {

        EntityManager em = ManejadorConexiones.getEntityManager();

        List<Pedido> pedidos = null;
        try {
            String jpql = "SELECT p FROM Pedido p WHERE p.numeroUnico LIKE :busqueda";

            String patronBusqueda = "%" + numeroPedido + "%";

            pedidos = em.createQuery(jpql, Pedido.class)
                    .setParameter("busqueda", patronBusqueda)
                    .getResultList();

            return pedidos;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener pedidos filtrados por número de pedido: " + numeroPedido, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Pedido> obtenerPedidosPorCliente(Long idCliente) throws PersistenciaException {

        EntityManager em = ManejadorConexiones.getEntityManager();
        List<Pedido> pedidos = null;

        try {
        } catch (NumberFormatException e) {
            throw new PersistenciaException("El ID del cliente debe ser un número válido.", e);
        }

        try {
            String jpql = "SELECT p FROM Pedido p WHERE p.cliente.id = " + idCliente;

            pedidos = em.createQuery(jpql, Pedido.class)
                    .getResultList(); 

            return pedidos;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener los pedidos por cliente con ID: " + idCliente, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }
}
