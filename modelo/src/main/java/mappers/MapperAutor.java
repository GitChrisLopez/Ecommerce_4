package mappers;

import dominio.AutorDTO;
import entidades.Autor;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper que convierte un Objeto Entidad Autor a un Objeto AutorDTO, y viceversa.
 * @author norma
 */
public class MapperAutor {
    
    /**
     * Convierte un objeto Entidad Autor a un objeto AutorDTO.
     * @param entity La entidad Autor.
     * @return El DTO del Autor.
     */
    public static AutorDTO toDto(Autor entity) {
        if (entity == null) {
            return null;
        }
        
        AutorDTO dto = new AutorDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellidoPaterno(entity.getApellidoPaterno());
        dto.setApellidoMaterno(entity.getApellidoMaterno()); 
        return dto;
    }

    /**
     * Convierte una lista de entidades Autor a una lista de AutorDTO.
     * @param entityList La lista de entidades Autor.
     * @return La lista de DTOs.
     */
    public static List<AutorDTO> toDtoList(List<Autor> entityList) {
        return entityList.stream()
                         .map(MapperAutor::toDto)
                         .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto AutorDTO a un objeto Entidad Autor.
     * @param dto El DTO del Autor.
     * @return La entidad Autor.
     */
    public static Autor toEntity(AutorDTO dto) {
        if (dto == null) {
            return null;
        }

        Autor entity = new Autor();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setApellidoPaterno(dto.getApellidoPaterno());
        entity.setApellidoMaterno(dto.getApellidoMaterno());
        return entity;
    }

    /**
     * Convierte una lista de AutorDTO a una lista de entidades Autor.
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades Autor.
     */
    public static List<Autor> toEntityList(List<AutorDTO> dtoList) {
        return dtoList.stream()
                      .map(MapperAutor::toEntity)
                      .collect(Collectors.toList());
    }
}
