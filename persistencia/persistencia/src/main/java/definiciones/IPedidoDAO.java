package definiciones;

import entidades.Pedido;
import enumeradores.Estado;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define los métodos que deben implementar las clases que manipulen la base de datos,
 * sobre la entidad Pedido.
 * @author norma
 */
public interface IPedidoDAO {
    
    /**
     * Guarda un nuevo pedido en la base de datos.
     *
     * @param pedido Objeto que se desea guardar.
     * @return El mismo objeto pedido si se guardó correctamente.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public Pedido persistirPedido(Pedido pedido) throws PersistenciaException;

    /**
     * Actualiza una pedido de la base de datos.
     *
     * @param idPedido ID de la pedido que se desea actualizar.
     * @param estado estado nuevo del pedido.
     * @return true si se actualizó correctamente, false si no se actualizó.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public boolean actualizarPedidoo(Long idPedido, Estado estado) throws PersistenciaException;

    /**
     * Obtiene todos los pedidos de la base de datos.
     *
     * @return Lista de pedidos.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public List<Pedido> obtenerTodosLosPedidos() throws PersistenciaException;

    /**
     * Obtiene un pedido de la base de datos a partir de su ID.
     *
     * @param idPedido ID de la pedido que se desea obtener.
     * @return El objeto pedido que se encontró con el id.
     * @throws PersistenciaException Si ocurre un error durante la operación.
     */
    public Pedido obtenerPedido(Long idPedido) throws PersistenciaException;

    /**
     * Obtiene una lista de pedidos que coincidan con los filtros
     * proporcionados.
     *
     * @param numeroPedido número del pedido
     * @return Lista de pedidos que cumple con el filtro.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    public List<Pedido> obtenerPedidosFiltradosPorNumero(String numeroPedido) throws PersistenciaException;
    
    public List<Pedido> obtenerPedidosPorCliente(Long idCliente) throws PersistenciaException;
}
