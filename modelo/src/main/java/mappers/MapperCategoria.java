
package mappers;

import dominio.CategoriaDTO;
import entidades.Categoria;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper que convierte un Objeto Entidad Categoria a un Objeto CategoriaDTO, y viceversa.
 * @author norma
 */
public class MapperCategoria {
    
    /**
     * Convierte un objeto Entidad Categoria a un objeto CategoriaDTO.
     * @param entity La entidad Categoria.
     * @return El DTO de la Categoria.
     */
    public static CategoriaDTO toDto(Categoria entity) {
        if (entity == null) {
            return null;
        }
        
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    /**
     * Convierte una lista de entidades Categoria a una lista de CategoriaDTO.
     * @param entityList La lista de entidades Categoria.
     * @return La lista de DTOs.
     */
    public static List<CategoriaDTO> toDtoList(List<Categoria> entityList) {
        
        if(entityList == null){
            return null;
        }
        
        return entityList.stream()
                         .map(MapperCategoria::toDto)
                         .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto CategoriaDTO a un objeto Entidad Categoria.
     * @param dto El DTO de la Categoria.
     * @return La entidad Categoria.
     */
    public static Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) {
            return null;
        }

        Categoria entity = new Categoria();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    /**
     * Convierte una lista de CategoriaDTO a una lista de entidades Categoria.
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades Categoria.
     */
    public static List<Categoria> toEntityList(List<CategoriaDTO> dtoList) {
        
        if(dtoList == null){
            return null;
        }
        
        return dtoList.stream()
                      .map(MapperCategoria::toEntity)
                      .collect(Collectors.toList());
    }
}
