
package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_pago", discriminatorType = DiscriminatorType.STRING)
@Table(name = "metodo_pago")
public abstract class MetodoPago implements Serializable {

    /**
     * Objeto Long que representa el Id del MetodoPago.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo_pago")
    private Long id;

    /**
     * Objeto LocalDate que representa la fecha de realización del pago.
     */
    @Column(name = "fecha_realizacion", nullable = false)
    private LocalDate fecha;

    /**
     * Constructor vacío.
     */
    public MetodoPago() {
    }

    /**
     * Constructor que inicializa todos los atributos.
     *
     * @param id id del método de pago
     * @param fecha fecha de realización del pago
     */
    public MetodoPago(Long id, LocalDate fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    /**
     * Método que inicializa solo la fecha
     *
     * @param fecha fecha de realización del pago.
     */
    public MetodoPago(LocalDate fecha) {
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
