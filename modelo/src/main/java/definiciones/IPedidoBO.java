package definiciones;

import dominio.PedidoDTO;
import dominio.enumeradores.EstadoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;


/**
 * Interfaz que define los métodos que deben implementar las clases que realizan 
 * operaciones de negocio sobre pedidos.
 * @author norma
 */
public interface IPedidoBO {
    
    /**
     * Actualiza el estado de un pedido.
     *
     * @param idPedido ID del pedido a actualizar.
     * @param estado nuevo estado del pedido.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si el ID o estado no es válido o no se pudo
     * actualizar.
     */
    public void actualizarPedido(Long idPedido, EstadoDTO estado) throws PersistenciaException, NegocioException;

    /**
     * Obtiene todos los pedidos.
     *
     * @return Lista de pedidos.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si no se pudo obtener la lista de pedidos.
     */
    public List<PedidoDTO> obtenerPedidos() throws PersistenciaException, NegocioException;

    /**
     * Obtiene un pedido específica.
     *
     * @param idPedido ID del pedido a obtener.
     * @return pedido específico.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si no se pudo obtener el pedido.
     */
    public PedidoDTO obtenerPedidoPorId(Long idPedido) throws PersistenciaException, NegocioException;

    /**
     * Obtiene los pedidos en relación a su número vinculado.
     *
     * @param busqueda número que se quiere que los pedidos tengan.
     * @return Lista de pedidos que les aplica el filtro.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si no se pudo obtener la lista de resenias.
     */
    public List<PedidoDTO> obtenerPedidosFiltradosPorNumero(String busqueda) throws PersistenciaException, NegocioException;
    
    public List<PedidoDTO> obtenerPedidosPorCliente(Long idCliente) throws PersistenciaException, NegocioException;
}
