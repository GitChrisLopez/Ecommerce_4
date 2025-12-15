package DAOs;

import com.persistencia.ManejadorConexiones;
import definiciones.IClienteDAO;
import entidades.Cliente;
import excepciones.PersistenciaException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author norma
 */
public class ClienteDAO implements IClienteDAO {

    public Cliente iniciarSesion(String correo, String contrasenia) {
        
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            // jPQL para buscar un Cliente
            TypedQuery<Cliente> query = em.createQuery(
                    "SELECT c FROM Cliente c WHERE c.correo = :correo AND c.contrasenia = :contra",
                    Cliente.class
            );
            query.setParameter("correo", correo);
            query.setParameter("contra", contrasenia);

            return query.getSingleResult();
            
        } catch (NoResultException e) {
            // No se encontro ningun cliente
            return null;
        } finally {
            em.close();
        }
    }
    
    @Override
    public Cliente obtenerClientePorId(Long idCliente) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        Cliente cliente = null;
        try {
            cliente = entityManager.find(Cliente.class, idCliente);
        } catch (Exception e) {
            throw new PersistenciaException("No se pudo obtener el cliente con ID " + idCliente, e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return cliente;
    }

    @Override
    public Cliente editarCliente(Cliente cliente) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Cliente clienteEditado = null;

        try {
            transaction.begin();

            if (cliente.getId() == null) {
                throw new PersistenciaException("El ID del cliente no puede ser null.");
            }

            clienteEditado = entityManager.merge(cliente);

            transaction.commit();

            return clienteEditado;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaException("No se pudo editar el cliente con ID " + cliente.getId(), e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public Cliente obtenerClientePorCorreo(String correo) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        Cliente cliente = null;
        try {

            String jpql = "SELECT c FROM Cliente c WHERE c.correo = :correo";

            TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
            query.setParameter("correo", correo);

            cliente = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el correo: " + correo, e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return cliente;
    }

}
