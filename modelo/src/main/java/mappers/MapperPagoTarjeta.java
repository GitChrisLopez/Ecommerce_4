package mappers;

import dominio.PagoTarjetaDTO;
import dominio.enumeradores.MetodoPagoDTO;
import entidades.PagoTarjeta;

/**
 * Mapper que convierte un Objeto Entidad PagoTarjeta a un Objeto
 * PagoTarjetaDTO, y viceversa.
 *
 * @author norma
 */
public class MapperPagoTarjeta extends MapperMetodoPago {

    /**
     * Convierte un objeto Entidad PagoTarjeta a un objeto PagoTarjetaDTO.
     *
     * @param entity La entidad PagoTarjeta.
     * @return El DTO PagoTarjetaDTO.
     */
    public static PagoTarjetaDTO toDto(PagoTarjeta entity) {
        if (entity == null) {
            return null;
        }

        PagoTarjetaDTO dto = new PagoTarjetaDTO();

        toDto(entity, (MetodoPagoDTO) dto);

        dto.setNumero(entity.getNumero());
        dto.setFechaVencimiento(entity.getFechaVencimiento());
        dto.setNombreTitular(entity.getNombreTitular());
        dto.setCvv(entity.getCvv());

        return dto;
    }

    /**
     * Convierte un objeto PagoTarjetaDTO a un objeto Entidad PagoTarjeta.
     *
     * @param dto El DTO PagoTarjetaDTO.
     * @return La entidad PagoTarjeta.
     */
    public static PagoTarjeta toEntity(PagoTarjetaDTO dto) {
        if (dto == null) {
            return null;
        }

        PagoTarjeta entity = new PagoTarjeta();

        toEntity((MetodoPagoDTO) dto, entity);

        entity.setNumero(dto.getNumero());
        entity.setFechaVencimiento(dto.getFechaVencimiento());
        entity.setNombreTitular(dto.getNombreTitular());
        entity.setCvv(dto.getCvv());

        return entity;
    }
}
