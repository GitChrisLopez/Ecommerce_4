package mappers;

import dominio.PagoContraEntregaDTO;
import dominio.PagoTarjetaDTO;
import dominio.PagoTransferenciaDTO;
import dominio.enumeradores.MetodoPagoDTO;
import entidades.MetodoPago;
import entidades.PagoContraEntrega;
import entidades.PagoTarjeta;
import entidades.PagoTransferencia;

/**
 * Mapper base que convierte un Objeto Entidad MetodoPago (abstracto) a un
 * Objeto MetodoPagoDTO (abstracto), y viceversa. Se utiliza para mapear los
 * campos comunes de la herencia de MetodoPago.
 */
public abstract class MapperMetodoPago {

    /**
     * Convierte un objeto Entidad MetodoPago a un objeto MetodoPagoDTO. Este
     * método solo mapea los campos comunes de la clase abstracta.
     *
     * @param entity La entidad MetodoPago.
     * @param dto El DTO del MetodoPago.
     * @return El DTO del MetodoPago.
     */
    protected static MetodoPagoDTO toDto(MetodoPago entity, MetodoPagoDTO dto) {
        if (entity == null || dto == null) {
            return null;
        }

        dto.setId(entity.getId());
        dto.setFecha(entity.getFecha());

        return dto;
    }

    /**
     * Convierte un objeto MetodoPagoDTO a un objeto Entidad MetodoPago. Este
     * método solo mapea los campos comunes de la clase abstracta.
     *
     * @param dto El DTO del MetodoPago.
     * @param entity La entidad MetodoPago.
     * @return La entidad MetodoPago.
     */
    protected static MetodoPago toEntity(MetodoPagoDTO dto, MetodoPago entity) {
        if (dto == null || entity == null) {
            return null;
        }

        entity.setId(dto.getId());
        entity.setFecha(dto.getFecha());

        return entity;
    }

    /**
     * Convierte una entidad MetodoPago a su DTO concreto correspondiente,
     * determinando el tipo real de la entidad.
     *
     * @param entity La entidad MetodoPago.
     * @return El DTO concreto.
     */
    public static MetodoPagoDTO toDto(MetodoPago entity) {
        if (entity == null) {
            return null;
        }

        if (entity instanceof PagoTarjeta) {
            return MapperPagoTarjeta.toDto((PagoTarjeta) entity);
        } else if (entity instanceof PagoTransferencia) {
            return MapperPagoTransferencia.toDto((PagoTransferencia) entity);
        } else if (entity instanceof PagoContraEntrega) {
            return MapperPagoContraEntrega.toDto((PagoContraEntrega) entity);
        } else {
            throw new IllegalArgumentException("Tipo de MetodoPago no soportado: " + entity.getClass().getName());
        }
    }

    /**
     * Convierte un DTO de MetodoPago a su entidad concreta correspondiente,
     * determinando el tipo real del DTO.
     *
     * @param dto El DTO de MetodoPago.
     * @return La entidad concreta.
     */
    public static MetodoPago toEntity(MetodoPagoDTO dto) {
        if (dto == null) {
            return null;
        }

        if (dto instanceof PagoTarjetaDTO) {
            return MapperPagoTarjeta.toEntity((PagoTarjetaDTO) dto);
        } else if (dto instanceof PagoTransferenciaDTO) {
            return MapperPagoTransferencia.toEntity((PagoTransferenciaDTO) dto);
        } else if (dto instanceof PagoContraEntregaDTO) {
            return MapperPagoContraEntrega.toEntity((PagoContraEntregaDTO) dto);
        } else {
            throw new IllegalArgumentException("Tipo de MetodoPagoDTO no soportado: " + dto.getClass().getName());
        }
    }
}
