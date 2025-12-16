package dominio.enumeradores;

import java.time.LocalDate;

/**
 *
 * Representa el método de pago utilizado por un Cliente para pagar un Pedido.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */

public abstract class MetodoPagoDTO {

    /**
     * Objeto Long que representa el Id del MetodoPago.
     */
    private Long id;

    /**
     * Objeto LocalDate que representa la fecha de realización del pago.
     */
    private LocalDate fecha;

    /**
     * Constructor vacío.
     */
    public MetodoPagoDTO() {
    }

    /**
     * Constructor que inicializa todos los atributos.
     *
     * @param id id del método de pago
     * @param montoPagar monto a pagar
     * @param fecha fecha de realización del pago
     */
    public MetodoPagoDTO(Long id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    /**
     * Método que inicializa solo la fecha
     *
     * @param fecha fecha de realización del pago.
     */
    public MetodoPagoDTO(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el identificador único del método de pago.
     *
     * @return El ID del método de pago (tipo Long).
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del método de pago. * Nota: En entidades
     * JPA, este método generalmente solo se usa antes de persistir la entidad
     * si el ID no es auto-generado.
     *
     * * @param id El nuevo ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha de realización del pago.
     *
     * @return La fecha de pago.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de realización del pago.
     *
     * @param fecha La nueva fecha de pago.
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
