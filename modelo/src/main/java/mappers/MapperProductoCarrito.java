
package mappers;

import dominio.ProductoCarritoDTO;
import dominio.ProductoDTO;
import entidades.ProductoCarrito;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class MapperProductoCarrito {
    

    public static ProductoCarritoDTO toDto(ProductoCarrito entity) {
        if (entity == null) {
            return null;
        }
        
        ProductoCarritoDTO dto = new ProductoCarritoDTO();
        dto.setId(entity.getId());
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());

        if (entity.getProducto() != null) {
            ProductoDTO productoBasicoDto = MapperProducto.toDtoBasico(entity.getProducto());
            dto.setProducto(productoBasicoDto);
            
        }

        return dto;
    }

    /**
     * Convierte una lista de entidades ProductoCarrito a una lista de ProductoCarritoDTO.
     * @param entityList La lista de entidades ProductoCarrito.
     * @return La lista de DTOs.
     */
    public static List<ProductoCarritoDTO> toDtoList(List<ProductoCarrito> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream()
                         .map(MapperProductoCarrito::toDto)
                         .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto ProductoCarritoDTO a un objeto Entidad ProductoCarrito.
     * @param dto El DTO de ProductoCarrito.
     * @return La entidad ProductoCarrito.
     */
    public static ProductoCarrito toEntity(ProductoCarritoDTO dto) {
        if (dto == null) {
            return null;
        }

        ProductoCarrito entity = new ProductoCarrito();
        entity.setId(dto.getId());
        entity.setCantidad(dto.getCantidad());
        entity.setPrecioUnitario(dto.getPrecioUnitario());


        if (dto.getProducto() != null && dto.getProducto().getId() != null) {
              entity.setProducto(MapperProducto.toEntityReference(dto.getProducto().getId()));
        }
        
        return entity;
    }

    /**
     * Convierte una lista de ProductoCarritoDTO a una lista de entidades ProductoCarrito.
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades ProductoCarrito.
     */
    public static List<ProductoCarrito> toEntityList(List<ProductoCarritoDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream()
                      .map(MapperProductoCarrito::toEntity)
                      .collect(Collectors.toList());
    }
    
    
}
