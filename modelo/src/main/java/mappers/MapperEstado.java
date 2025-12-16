package mappers;

import dominio.enumeradores.EstadoDTO;
import enumeradores.Estado;

/**
 * Mapper que convierte un Objeto Entidad Estado a un Objeto EstadoDTO, y
 * viceversa.
 *
 * @author norma
 */
public class MapperEstado {

    /**
     * Convierte el Enum Estado de Entidad a EstadoDTO.
     *
     * @param entityEnum El Enum Estado de la Entidad.
     * @return El Enum EstadoDTO.
     */
    public static EstadoDTO toDto(Estado entityEnum) {
        if (entityEnum == null) {
            return null;
        }
        return EstadoDTO.valueOf(entityEnum.name());
    }

    /**
     * Convierte el Enum EstadoDTO a Estado de Entidad.
     *
     * @param dtoEnum El Enum EstadoDTO.
     * @return El Enum Estado de la Entidad.
     */
    public static Estado toEntity(EstadoDTO dtoEnum) {
        if (dtoEnum == null) {
            return null;
        }
        return Estado.valueOf(dtoEnum.name());
    }
}
