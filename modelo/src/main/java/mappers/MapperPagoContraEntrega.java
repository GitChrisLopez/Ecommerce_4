package mappers;

import dominio.PagoContraEntregaDTO;
import dominio.enumeradores.MetodoPagoDTO;
import entidades.PagoContraEntrega;

/**
 * Mapper que convierte un Objeto Entidad PagoContraEntrega a un Objeto
 * PagoContraEntregaDTO, y viceversa.
 */
public class MapperPagoContraEntrega extends MapperMetodoPago {

    /**
     * Convierte un objeto Entidad PagoContraEntrega a un objeto
     * PagoContraEntregaDTO. Mapea los campos comunes usando el método base.
     *
     * @param entity La entidad PagoContraEntrega.
     * @return El DTO PagoContraEntregaDTO.
     */
    public static PagoContraEntregaDTO toDto(PagoContraEntrega entity) {
        if (entity == null) {
            return null;
        }

        PagoContraEntregaDTO dto = new PagoContraEntregaDTO();

        toDto(entity, (MetodoPagoDTO) dto);

        return dto;
    }

    /**
     * Convierte un objeto PagoContraEntregaDTO a un objeto Entidad
     * PagoContraEntrega. Mapea los campos comunes usando el método base.
     *
     * @param dto El DTO PagoContraEntregaDTO.
     * @return La entidad PagoContraEntrega.
     */
    public static PagoContraEntrega toEntity(PagoContraEntregaDTO dto) {
        if (dto == null) {
            return null;
        }

        PagoContraEntrega entity = new PagoContraEntrega();

        toEntity((MetodoPagoDTO) dto, entity);

        return entity;
    }
}
