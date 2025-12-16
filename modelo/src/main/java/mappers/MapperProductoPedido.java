package mappers;

import dominio.ProductoPedidoDTO;
import entidades.Pedido;
import entidades.ProductoPedido;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper que convierte un Objeto Entidad ProductoPedido a un Objeto
 * ProductoPedidoDTO, y viceversa.
 *
 * @author norma
 */
public class MapperProductoPedido {

    /**
     * Convierte un objeto Entidad ProductoPedido a un objeto ProductoPedidoDTO.
     *
     * @param entity La entidad ProductoPedido a convertir.
     * @return El DTO de ProductoPedido.
     */
    public static ProductoPedidoDTO toDto(ProductoPedido entity) {
        if (entity == null) {
            return null;
        }

        ProductoPedidoDTO dto = new ProductoPedidoDTO();
        dto.setId(entity.getId());
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());

        dto.setProducto(MapperProducto.toDto(entity.getProducto()));
        if (entity.getPedido() != null) {
            dto.setIdPedido(entity.getPedido().getId());
        }

        return dto;
    }

    /**
     * Convierte una lista de entidades ProductoPedido a una lista de
     * ProductoPedidoDTO.
     *
     * @param entityList La lista de entidades ProductoPedido.
     * @return La lista de DTOs.
     */
    public static List<ProductoPedidoDTO> toDtoList(List<ProductoPedido> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream()
                .map(MapperProductoPedido::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto ProductoPedidoDTO a un objeto Entidad ProductoPedido.
     *
     * @param dto El DTO de ProductoPedido.
     * @return La entidad ProductoPedido.
     */
    public static ProductoPedido toEntity(ProductoPedidoDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductoPedido entity = new ProductoPedido();
        entity.setId(dto.getId());
        entity.setCantidad(dto.getCantidad());
        entity.setPrecioUnitario(dto.getPrecioUnitario());

        entity.setProducto(MapperProducto.toEntity(dto.getProducto()));
        if (dto.getIdPedido() != null) {
            Pedido pedido = new Pedido();
            pedido.setId(dto.getIdPedido());
            entity.setPedido(pedido);
        }

        return entity;
    }

    /**
     * Convierte una lista de ProductoPedidoDTO a una lista de entidades
     * ProductoPedido.
     *
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades ProductoPedido.
     */
    public static List<ProductoPedido> toEntityList(List<ProductoPedidoDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream()
                .map(MapperProductoPedido::toEntity)
                .collect(Collectors.toList());
    }
}
