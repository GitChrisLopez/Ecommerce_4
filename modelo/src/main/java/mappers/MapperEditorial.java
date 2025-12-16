package mappers;

import dominio.EditorialDTO;
import entidades.Editorial;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper que convierte un Objeto Entidad Editorial a un Objeto EditorialDTO, y viceversa.
 * @author norma
 */
public class MapperEditorial {
    
    /**
     * Convierte un objeto Entidad Editorial a un objeto EditorialDTO.
     * @param entity La entidad Editorial.
     * @return El DTO de la Editorial.
     */
    public static EditorialDTO toDto(Editorial entity) {
        if (entity == null) {
            return null;
        }
        
        EditorialDTO dto = new EditorialDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    /**
     * Convierte una lista de entidades Editorial a una lista de EditorialDTO.
     * @param entityList La lista de entidades Editorial.
     * @return La lista de DTOs.
     */
    public static List<EditorialDTO> toDtoList(List<Editorial> entityList) {
        return entityList.stream()
                         .map(MapperEditorial::toDto)
                         .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto EditorialDTO a un objeto Entidad Editorial.
     * @param dto El DTO de la Editorial.
     * @return La entidad Editorial.
     */
    public static Editorial toEntity(EditorialDTO dto) {
        if (dto == null) {
            return null;
        }

        Editorial entity = new Editorial();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    /**
     * Convierte una lista de EditorialDTO a una lista de entidades Editorial.
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades Editorial.
     */
    public static List<Editorial> toEntityList(List<EditorialDTO> dtoList) {
        return dtoList.stream()
                      .map(MapperEditorial::toEntity)
                      .collect(Collectors.toList());
    }
}
