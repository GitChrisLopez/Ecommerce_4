package mappers;

import dominio.PagoTransferenciaDTO;
import dominio.enumeradores.MetodoPagoDTO;
import entidades.PagoTransferencia;

/**
 * Mapper que convierte un Objeto Entidad PagoTransferencia a un Objeto
 * PagoTransferenciaDTO, y viceversa.
 */
public class MapperPagoTransferencia extends MapperMetodoPago {

    /**
     * Convierte un objeto Entidad PagoTransferencia a un objeto
     * PagoTransferenciaDTO. Mapea los campos comunes usando el método base y
     * los campos específicos.
     *
     * @param entity La entidad PagoTransferencia.
     * @return El DTO PagoTransferenciaDTO.
     */
    public static PagoTransferenciaDTO toDto(PagoTransferencia entity) {
        if (entity == null) {
            return null;
        }

        PagoTransferenciaDTO dto = new PagoTransferenciaDTO();

        toDto(entity, (MetodoPagoDTO) dto);

        dto.setBancoEmisor(entity.getBancoEmisor());
        dto.setCuatroDigitos(entity.getCuatroDigitos());

        return dto;
    }

    /**
     * Convierte un objeto PagoTransferenciaDTO a un objeto Entidad
     * PagoTransferencia. Mapea los campos comunes usando el método base y los
     * campos específicos.
     *
     * @param dto El DTO PagoTransferenciaDTO.
     * @return La entidad PagoTransferencia.
     */
    public static PagoTransferencia toEntity(PagoTransferenciaDTO dto) {
        if (dto == null) {
            return null;
        }

        PagoTransferencia entity = new PagoTransferencia();

        toEntity((MetodoPagoDTO) dto, entity);

        entity.setBancoEmisor(dto.getBancoEmisor());
        entity.setCuatroDigitos(dto.getCuatroDigitos());

        return entity;
    }
}
