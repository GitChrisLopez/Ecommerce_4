package mappers;

import dominio.PedidoDTO;
import entidades.Pedido;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper que convierte un Objeto Entidad Pedido a un Objeto PedidoDTO, y
 * viceversa.
 *
 * @author norma
 */
public class MapperPedido {

    /**
     * Convierte un objeto Entidad Pedido a un objeto PedidoDTO.
     *
     * @param entity La entidad Pedido.
     * @return El DTO del Pedido.
     */
    public static PedidoDTO toDto(Pedido entity) {
        if (entity == null) {
            return null;
        }

        PedidoDTO dto = new PedidoDTO();

        dto.setId(entity.getId());
        dto.setNumeroUnico(entity.getNumeroUnico());
        dto.setFecha(entity.getFecha());
        dto.setEstado(MapperEstado.toDto(entity.getEstado()));

        dto.setTotal(entity.getTotal()); 
        dto.setProductosPedido(MapperProductoPedido.toDtoList(entity.getProductosPedido())); 

        dto.setDireccionEnvio(MapperDireccion.toDto(entity.getDireccionEnvio()));
        dto.setMetodoPago(MapperMetodoPago.toDto(entity.getMetodoPago()));
        dto.setCliente(MapperCliente.toDto(entity.getCliente()));

        return dto;
    }

    /**
     * Convierte una lista de entidades Pedido a una lista de PedidoDTO.
     *
     * @param entityList La lista de entidades Pedido.
     * @return La lista de DTOs.
     */
    public static List<PedidoDTO> toDtoList(List<Pedido> entityList) {
        return entityList.stream()
                .map(MapperPedido::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto PedidoDTO a un objeto Entidad Pedido.
     *
     * @param dto El DTO del Pedido.
     * @return La entidad Pedido.
     */
    public static Pedido toEntity(PedidoDTO dto) {
        if (dto == null) {
            return null;
        }

        Pedido entity = new Pedido();

        entity.setId(dto.getId());
        entity.setNumeroUnico(dto.getNumeroUnico());
        entity.setFecha(dto.getFecha());
        entity.setEstado(MapperEstado.toEntity(dto.getEstado()));

        entity.setTotal(dto.getTotal()); 
        entity.setProductosPedido(MapperProductoPedido.toEntityList(dto.getProductosPedido())); 

        entity.setDireccionEnvio(MapperDireccion.toEntity(dto.getDireccionEnvio()));
        entity.setMetodoPago(MapperMetodoPago.toEntity(dto.getMetodoPago()));
        entity.setCliente(MapperCliente.toEntity(dto.getCliente()));

        return entity;
    }

    /**
     * Convierte una lista de PedidoDTO a una lista de entidades Pedido.
     *
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades Pedido.
     */
    public static List<Pedido> toEntityList(List<PedidoDTO> dtoList) {
        return dtoList.stream()
                .map(MapperPedido::toEntity)
                .collect(Collectors.toList());
    }
}
