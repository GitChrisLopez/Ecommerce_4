package mappers;

import dominio.FormatoDTO;
import enumeradores.Formato;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper que convierte un Objeto Entidad Formato a un Objeto FormatoDTO, y
 * viceversa.
 *
 * @author norma
 */
public class MapperFormato {

    /**
     * Convierte el Enum Formato de Entidad a FormatoDTO.
     *
     * @param entityEnum El Enum Formato de la Entidad.
     * @return El Enum FormatoDTO.
     */
    public static FormatoDTO toDto(Formato entityEnum) {
        if (entityEnum == null) {
            return null;
        }
        return FormatoDTO.valueOf(entityEnum.name());
    }

    /**
     * Convierte una lista de Enum Formato a una lista de Enum FormatoDTO.
     * @param entityList La lista de Enum Formato.
     * @return La lista de DTOs.
     */
    public static List<FormatoDTO> toDtoList(List<Formato> entityList) {
        return entityList.stream()
                         .map(MapperFormato::toDto)
                         .collect(Collectors.toList());
    }
    
    /**
     * Convierte el Enum FormatoDTO a Formato de Entidad.
     *
     * @param dtoEnum El Enum FormatoDTO.
     * @return El Enum Formato de la Entidad.
     */
    public static Formato toEntity(FormatoDTO dtoEnum) {
        if (dtoEnum == null) {
            return null;
        }
        return Formato.valueOf(dtoEnum.name());
    }
    
    /**
     * Convierte una lista de Enum FormatoDTO a una lista de Enum Formato.
     * @param dtoList La lista de Enum FormatoDTO.
     * @return La lista de Enum Formato.
     */
    public static List<Formato> toEntityList(List<FormatoDTO> dtoList) {
        
        if(dtoList == null){
            return null;
        }
        
        return dtoList.stream()
                      .map(MapperFormato::toEntity)
                      .collect(Collectors.toList());
    }
    
}
