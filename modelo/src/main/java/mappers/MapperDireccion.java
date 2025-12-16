package mappers;

import dominio.DireccionDTO;
import entidades.Direccion;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper que convierte un Objeto Entidad Direccion a un Objeto DireccionDTO, y
 * viceversa
 *
 * @author norma
 */
public class MapperDireccion {

    /**
     * Convierte un objeto Entidad Direccion a un objeto DireccionDTO. * NOTA:
     * El campo 'cliente' de la Entidad NO es mapeado al DTO para romper el
     * ciclo de dependencia bidireccional.
     *
     * @param entity La entidad Direccion a convertir.
     * @return El DTO de la Direccion.
     */
    public static DireccionDTO toDto(Direccion entity) {
        if (entity == null) {
            return null;
        }

        DireccionDTO dto = new DireccionDTO();
        dto.setId(entity.getId());
        dto.setCodigoPostal(entity.getCodigoPostal());
        dto.setColonia(entity.getColonia());
        dto.setCalle(entity.getCalle());
        dto.setNumero(entity.getNumero());

        return dto;
    }

    /**
     * Convierte una lista de entidades Direccion a una lista de DireccionDTO.
     *
     * @param entityList La lista de entidades Direccion.
     * @return La lista de DTOs.
     */
    public static List<DireccionDTO> toDtoList(List<Direccion> entityList) {
        return entityList.stream()
                .map(MapperDireccion::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto DireccionDTO a un objeto Entidad Direccion.
     * El campo 'cliente' de la Entidad debe ser establecido en la
     * capa de persistencia después de llamar a este método (si es necesario), ya
     * que el DTO no contiene esta información.
     *
     * @param dto El DTO de la Direccion.
     * @return La entidad Direccion.
     */
    public static Direccion toEntity(DireccionDTO dto) {
        if (dto == null) {
            return null;
        }

        Direccion entity = new Direccion();
        entity.setId(dto.getId());
        entity.setCodigoPostal(dto.getCodigoPostal());
        entity.setColonia(dto.getColonia());
        entity.setCalle(dto.getCalle());
        entity.setNumero(dto.getNumero());

        return entity;
    }

    /**
     * Convierte una lista de DireccionDTO a una lista de entidades Direccion.
     *
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades Direccion.
     */
    public static List<Direccion> toEntityList(List<DireccionDTO> dtoList) {
        return dtoList.stream()
                .map(MapperDireccion::toEntity)
                .collect(Collectors.toList());
    }
}
