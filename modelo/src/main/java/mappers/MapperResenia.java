package mappers;

import dominio.ReseniaDTO;
import entidades.Resenia;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author norma
 */
public class MapperResenia {
   
    /**
     * Convierte un objeto Entidad Resenia a un objeto ReseniaDTO.
     * @param entity La entidad Resenia.
     * @return El DTO de la Resenia.
     */
    public static ReseniaDTO toDto(Resenia entity) {
        if (entity == null) {
            return null;
        }
        
        ReseniaDTO dto = new ReseniaDTO();
        dto.setId(entity.getId());
        
        dto.setLibro(MapperLibro.toDto(entity.getLibro())); 
        dto.setCliente(MapperCliente.toDto(entity.getCliente()));
        
        dto.setFormato(MapperFormato.toDto(entity.getFormato()));
        dto.setCalificacion(entity.getCalificacion());
        dto.setComentario(entity.getComentario());
        dto.setFecha(entity.getFecha());
        
        return dto;
    }

    /**
     * Convierte una lista de entidades Resenia a una lista de ReseniaDTO.
     * @param entityList La lista de entidades Resenia.
     * @return La lista de DTOs.
     */
    public static List<ReseniaDTO> toDtoList(List<Resenia> entityList) {
        return entityList.stream()
                         .map(MapperResenia::toDto)
                         .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto ReseniaDTO a un objeto Entidad Resenia.
     * @param dto El DTO de la Resenia.
     * @return La entidad Resenia.
     */
    public static Resenia toEntity(ReseniaDTO dto) {
        if (dto == null) {
            return null;
        }

        Resenia entity = new Resenia();
        entity.setId(dto.getId());

        entity.setLibro(MapperLibro.toEntity(dto.getLibro()));
        entity.setCliente(MapperCliente.toEntity(dto.getCliente())); 
        
        entity.setFormato(MapperFormato.toEntity(dto.getFormato()));
        entity.setCalificacion(dto.getCalificacion());
        entity.setComentario(dto.getComentario());
        entity.setFecha(dto.getFecha());

        return entity;
    }

    /**
     * Convierte una lista de ReseniaDTO a una lista de entidades Resenia.
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades Resenia.
     */
    public static List<Resenia> toEntityList(List<ReseniaDTO> dtoList) {
        return dtoList.stream()
                      .map(MapperResenia::toEntity)
                      .collect(Collectors.toList());
    }
}
