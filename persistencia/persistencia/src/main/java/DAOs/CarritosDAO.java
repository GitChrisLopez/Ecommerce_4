
package DAOs;

import com.persistencia.ManejadorConexiones;
import definiciones.ICarritosDAO;
import entidades.Carrito;
import entidades.Cliente;
import entidades.Producto;
import entidades.ProductoCarrito;
import excepciones.PersistenciaException;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public class CarritosDAO implements ICarritosDAO{

    public void crearProductoCarrito(Long idCliente, Long idProducto, int cantidad)
        throws PersistenciaException {

        if (idCliente == null || idProducto == null) {
            throw new PersistenciaException("Los Ids de cliente y producto no deben ser nulos.");
        }
        
        if (cantidad <= 0) {
            throw new PersistenciaException("La cantidad debe ser mayor a 0.");
        }

        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Se obtiene el carrito del cliente.
            String jpql = "SELECT c FROM Carrito c WHERE c.cliente.id = :idCliente";
            TypedQuery<Carrito> query = entityManager.createQuery(jpql, Carrito.class);
            query.setParameter("idCliente", idCliente);

            Carrito carrito = query.getSingleResult();

            // Se obtiene el producto.
            Producto producto = entityManager.find(Producto.class, idProducto);
            if (producto == null) {
                throw new PersistenciaException("El producto no existe.");
            }

            // Se crea el ProductoCarrito.
            ProductoCarrito productoCarrito = new ProductoCarrito();
            productoCarrito.setProducto(producto);
            productoCarrito.setCarrito(carrito);
            productoCarrito.setCantidad(cantidad);
            productoCarrito.setPrecioUnitario(producto.getPrecio());

            carrito.getProductosCarrito().add(productoCarrito);

            // Se actualiza el total del carrito.
            BigDecimal monto = producto.getPrecio()
                    .multiply(BigDecimal.valueOf(cantidad));
            carrito.setTotal(carrito.getTotal().add(monto));

            entityManager.merge(carrito);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al crear el producto en el carrito.");
        }
    }

    public void actualizarCantidadProductoCarrito(Long idCliente, Long idProducto, int nuevaCantidad)
        throws PersistenciaException {

        if (idCliente == null || idProducto == null) {
            throw new PersistenciaException("Los Ids de cliente y producto no deben ser nulos.");
        }
        
        if (nuevaCantidad <= 0) {
            throw new PersistenciaException("La cantidad debe ser mayor a 0.");
        }

        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            String jpql = """
                SELECT pc FROM ProductoCarrito pc
                WHERE pc.carrito.cliente.id = :idCliente
                AND pc.producto.id = :idProducto
            """;

            TypedQuery<ProductoCarrito> query =
                    entityManager.createQuery(jpql, ProductoCarrito.class);
            query.setParameter("idCliente", idCliente);
            query.setParameter("idProducto", idProducto);

            ProductoCarrito productoCarrito = query.getSingleResult();

            // Se recalcula el total del carrito.
            Carrito carrito = productoCarrito.getCarrito();
            BigDecimal totalActual = carrito.getTotal();

            BigDecimal totalAnterior = productoCarrito.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(productoCarrito.getCantidad()));

            BigDecimal totalNuevo = productoCarrito.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(nuevaCantidad));

            carrito.setTotal(totalActual.subtract(totalAnterior).add(totalNuevo));

            // Se actualiza la cantidad.
            productoCarrito.setCantidad(nuevaCantidad);

            entityManager.merge(carrito);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar la cantidad del producto.");
        }
    }
    
    @Override
    public void eliminarProductoCarrito(Long idCliente, Long idProducto)
            throws PersistenciaException {

        if (idCliente == null || idProducto == null) {
            throw new PersistenciaException("Los Ids de cliente y producto no deben ser nulos.");
        }
        
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            String jpql = """
                SELECT pc FROM ProductoCarrito pc
                WHERE pc.carrito.cliente.id = :idCliente
                AND pc.producto.id = :idProducto
            """;

            TypedQuery<ProductoCarrito> query =
                    entityManager.createQuery(jpql, ProductoCarrito.class);
            query.setParameter("idCliente", idCliente);
            query.setParameter("idProducto", idProducto);

            ProductoCarrito productoCarrito = query.getSingleResult();

            // Se actualiza el total del carrito.
            BigDecimal montoEliminar = productoCarrito.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(productoCarrito.getCantidad()));

            Carrito carrito = productoCarrito.getCarrito();
            carrito.setTotal(carrito.getTotal().subtract(montoEliminar));

            carrito.getProductosCarrito().remove(productoCarrito);

            entityManager.merge(carrito);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al eliminar el producto del carrito.");
        }
    }
    
    @Override
    public ProductoCarrito consultarProductoCarrito(Long idCliente, Long idProducto)
            throws PersistenciaException {

        if (idCliente == null || idProducto == null) {
            throw new PersistenciaException("Los Ids de cliente y producto no deben ser nulos.");
        }
         
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            String jpql = """
                SELECT pc FROM ProductoCarrito pc
                WHERE pc.carrito.cliente.id = :idCliente
                AND pc.producto.id = :idProducto
            """;

            TypedQuery<ProductoCarrito> query =
                    entityManager.createQuery(jpql, ProductoCarrito.class);
            query.setParameter("idCliente", idCliente);
            query.setParameter("idProducto", idProducto);

            return query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el producto del carrito.");
        }
    }

    @Override
    public void crearCarrito(Long idCliente) throws PersistenciaException {
        
        if (idCliente == null) {
            throw new PersistenciaException("El Id del cliente es nulo.");
        }

        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Se busca al cliente.
            Cliente cliente = entityManager.find(Cliente.class, idCliente);
            if (cliente == null) {
                throw new PersistenciaException("No existe el cliente especificado.");
            }

            // Se crea el carrito.
            Carrito nuevoCarrito = new Carrito();
            nuevoCarrito.setCliente(cliente);
            nuevoCarrito.setTotal(BigDecimal.ZERO);
            nuevoCarrito.setProductosCarrito(new ArrayList<>());

            entityManager.persist(nuevoCarrito);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al crear el carrito.");
        }
    }

    @Override
    public void eliminarCarrito(Long idCliente) throws PersistenciaException {
        
        if (idCliente == null) {
            throw new PersistenciaException("El Id del cliente es nulo.");
        }

        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Se busca el carrito.
            String jpql = "SELECT c FROM Carrito c WHERE c.cliente.id = :idCliente";
            TypedQuery<Carrito> query = entityManager.createQuery(jpql, Carrito.class);
            query.setParameter("idCliente", idCliente);

            Carrito carritoEliminar;
            try {
                carritoEliminar = query.getSingleResult();
            } catch (NoResultException e) {
                throw new PersistenciaException("El cliente no tiene carrito para eliminar.");
            }

            // Se elimina el carrito y todos los productos carrito en cascada.
            entityManager.remove(carritoEliminar);

            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al eliminar el carrito.");
        }
    }


    @Override
    public Carrito consultarPorIdCliente(Long idCliente) throws PersistenciaException {
        
        if (idCliente == null) {
            throw new PersistenciaException("El Id del cliente es nulo.");
        }

        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {
            // Se busca al carrito.
            String jpql = "SELECT c FROM Carrito c WHERE c.cliente.id = :idCliente";
            TypedQuery<Carrito> query = entityManager.createQuery(jpql, Carrito.class);
            query.setParameter("idCliente", idCliente);

            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                // El cliente no tiene carrito.
                return null; 
            }

        } catch (Exception e) {
            throw new PersistenciaException("Error al consultar el carrito.");
        }
    }

    
}
