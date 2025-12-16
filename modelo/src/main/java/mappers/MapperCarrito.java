
package mappers;

import dominio.CarritoDTO;
import dominio.ClienteDTO;
import entidades.Carrito;
import entidades.Cliente;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
public class MapperCarrito {
    
    /**
     * Convierte un objeto Entidad Carrito a un objeto CarritoDTO.
     * @param entity La entidad Carrito.
     * @return El DTO del Carrito.
     */
    public static CarritoDTO toDto(Carrito entity) {
        if (entity == null) {
            return null;
        }

        CarritoDTO dto = new CarritoDTO();
        dto.setId(entity.getId());
        dto.setTotal(entity.getTotal());
        
        // Mapeo de la relación OneToOne (Cliente)
        if (entity.getCliente() != null) {
            // Asume la existencia de MapperCliente.toDto(Cliente)
            ClienteDTO clienteDto = MapperCliente.toDto(entity.getCliente());
            // Nota: Se recomienda limitar lo que se mapea del ClienteDTO para evitar bucles.
            dto.setCliente(clienteDto);
        }

        // 🛑 [CORRECCIÓN AÑADIDA] 🛑
        // Mapeo de la lista de ProductosCarrito a DTOs para la respuesta de la API.
        if (entity.getProductosCarrito() != null && !entity.getProductosCarrito().isEmpty()) {
            // Se asume la existencia de MapperProductoCarrito.toDtoList()
            dto.setProductosCarrito(MapperProductoCarrito.toDtoList(entity.getProductosCarrito()));
        }
        // ------------------------

        return dto;
    }

    /**
     * Convierte una lista de entidades Carrito a una lista de CarritoDTO.
     * @param entityList La lista de entidades Carrito.
     * @return La lista de DTOs.
     */
    public static List<CarritoDTO> toDtoList(List<Carrito> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream()
                         .map(MapperCarrito::toDto)
                         .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto CarritoDTO a un objeto Entidad Carrito.
     * @param dto El DTO del Carrito.
     * @return La entidad Carrito.
     */
    public static Carrito toEntity(CarritoDTO dto) {
        if (dto == null) {
            return null;
        }

        Carrito entity = new Carrito();
        entity.setId(dto.getId());
        entity.setTotal(dto.getTotal());
        
        // Mapeo de la relación OneToOne (Cliente)
        if (dto.getCliente() != null) {
            // Asume la existencia de MapperCliente.toEntity(ClienteDTO)
            Cliente entityCliente = MapperCliente.toEntity(dto.getCliente());
            entity.setCliente(entityCliente);
        }
        
        // La lista de productos NO se mapea de DTO a Entidad aquí,
        // ya que esa es una operación de negocio (agregar/eliminar).
        
        return entity;
    }

    /**
     * Convierte una lista de CarritoDTO a una lista de entidades Carrito.
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades Carrito.
     */
    public static List<Carrito> toEntityList(List<CarritoDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream()
                      .map(MapperCarrito::toEntity)
                      .collect(Collectors.toList());
    }
    
}
