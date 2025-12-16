package BOs;

import definiciones.IPedidoBO;
import definiciones.IPedidoDAO;
import dominio.PedidoDTO;
import dominio.enumeradores.EstadoDTO;
import entidades.Pedido;
import enumeradores.Estado;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import mappers.MapperPedido;
import mappers.MapperEstado;

/**
 * Se encarga de implementar lalógica de negocio relacionada con los pedidos,
 * como actualizar, y realizar consultas filtradas. Además, valida los datos
 * antes de realizar operaciones sobre la base de datos.
 *
 * @author norma
 */
public class PedidoBO implements IPedidoBO {

    /**
     * Objeto que implementa la interfaz IPedidoDAO, permite el acceso a datos
     * para objetos de la clase PedidoDTO.
     */
    private final IPedidoDAO pedidoDAO;

    /**
     * Contructor de la clase que recibe un objeto que implementa la interfaz
     * IReseniaDAO.
     *
     * @param pedidoDAO Objeto que implementa la interfaz IPedidoDAO, permite el
     * acceso a datos para objetos de la clase Pedido.
     */
    public PedidoBO(IPedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }

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
    @Override
    public void actualizarPedido(Long idPedido, EstadoDTO estado) throws PersistenciaException, NegocioException {

        if (idPedido == null || idPedido <= 0) {
            throw new NegocioException("El ID del pedido es inválido.");
        }
        if (estado == null || estado.toString().isEmpty()) {
            throw new NegocioException("El estado no puede estar vacío.");
        }

        Estado nuevoEstado = MapperEstado.toEntity(estado);

        try {
            Pedido pedidoActual = pedidoDAO.obtenerPedido(idPedido);

            if (pedidoActual == null) {
                throw new NegocioException("El pedido con ID " + idPedido + " no existe.");
            }

            Estado estadoActual = pedidoActual.getEstado();

            if (estadoActual == Estado.CANCELADO
                    || estadoActual == Estado.ENTREGADO) {

                throw new NegocioException("No se puede modificar el estado de un pedido que ya está en estado no modificable: " + estadoActual.toString());
            }

            if (nuevoEstado == Estado.CANCELADO) {
                if (estadoActual != Estado.PENDIENTE) {
                    throw new NegocioException("Solo se puede cancelar un pedido PENDIENTE");
                }
            }

            if (nuevoEstado == Estado.ENVIADO && estadoActual != Estado.PENDIENTE) {
                throw new NegocioException("Un pedido solo puede ser ENVIADO si antes estaba PENDIENTE.");
            }

            if (nuevoEstado == Estado.ENTREGADO && estadoActual != Estado.ENVIADO) {
                throw new NegocioException("Un pedido solo puede ser ENTREGADO si antes estaba ENVIADO.");
            }

            boolean exito = pedidoDAO.actualizarPedidoo(idPedido, nuevoEstado);

            if (!exito) {
                throw new NegocioException("El pedido con ID " + idPedido + " no se pudo actualizar.");
            }

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en la capa de persistencia al actualizar el estado.", ex);
        } catch (NegocioException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new NegocioException("Error inesperado al actualizar el estado.", ex);
        }
    }

    /**
     * Obtiene todos los pedidos.
     *
     * @return Lista de pedidos.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si no se pudo obtener la lista de pedidos.
     */
    @Override
    public List<PedidoDTO> obtenerPedidos() throws PersistenciaException, NegocioException {
        try {
            List<Pedido> pedidos = pedidoDAO.obtenerTodosLosPedidos();

            if (pedidos == null || pedidos.isEmpty()) {
                throw new NegocioException("No se encontraron pedidos.");
            }
            return MapperPedido.toDtoList(pedidos);
        } catch (PersistenciaException e) {
            throw e;
        } catch (Exception e) {
            throw new NegocioException("Error al obtener la lista de pedidos.", e);
        }
    }

    /**
     * Obtiene un pedido específica.
     *
     * @param idPedido ID del pedido a obtener.
     * @return pedido específico.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si no se pudo obtener el pedido.
     */
    @Override
    public PedidoDTO obtenerPedidoPorId(Long idPedido) throws PersistenciaException, NegocioException {
        if (idPedido == null || idPedido <= 0) {
            throw new NegocioException("El ID del pedido debe ser un número válido.");
        }

        try {
            Pedido pedido = pedidoDAO.obtenerPedido(idPedido);

            if (pedido == null) {
                throw new NegocioException("No se encontró el pedido con ID: " + idPedido);
            }

            return MapperPedido.toDto(pedido);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en la capa de persistencia al obtener el pedido.", ex);
        } catch (Exception ex) {
            throw new NegocioException("Error inesperado al obtener el pedido.", ex);
        }
    }

    /**
     * Obtiene los pedidos en relación a su número vinculado.
     *
     * @param busqueda número que se quiere que los pedidos tengan.
     * @return Lista de pedidos que les aplica el filtro.
     * @throws PersistenciaException si ocurre un error en la capa de
     * persistencia.
     * @throws NegocioException si no se pudo obtener la lista de resenias.
     */
    @Override
    public List<PedidoDTO> obtenerPedidosFiltradosPorNumero(String busqueda) throws PersistenciaException, NegocioException {

        try {
            List<Pedido> listaPedidos;

            if (busqueda == null || busqueda.trim().isEmpty()) {
                listaPedidos = pedidoDAO.obtenerTodosLosPedidos();
            } else {
                listaPedidos = pedidoDAO.obtenerPedidosFiltradosPorNumero(busqueda);
            }

            return MapperPedido.toDtoList(listaPedidos);

        } catch (PersistenciaException ex) {
            throw new NegocioException("Error en la capa de persistencia al obtener los pedidos filtrados.", ex);
        } catch (Exception ex) {
            throw new NegocioException("Error inesperado al obtener los pedidos filtrados.", ex);
        }
    }

    @Override
    public List<PedidoDTO> obtenerPedidosPorCliente(Long idCliente) throws PersistenciaException, NegocioException {
        if (idCliente == null || idCliente <= 0) {
            throw new NegocioException("El ID del cliente debe ser un número válido.");
        }

        try {
            List<Pedido> pedidos = pedidoDAO.obtenerPedidosPorCliente(idCliente);

            if (pedidos == null || pedidos.isEmpty()) {
                throw new NegocioException("No se encontraron pedidos.");
            }
            return MapperPedido.toDtoList(pedidos);
        } catch (Exception e) {
            throw new NegocioException("Error al obtener la lista de pedidos por cliente.", e);
        }
    }
}
