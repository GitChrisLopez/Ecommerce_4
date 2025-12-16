
package dominio;

import dominio.enumeradores.MetodoPagoDTO;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Representa un tipo de método de pago (Pago Contra Entrega).
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */

public class PagoContraEntregaDTO extends MetodoPagoDTO {

    /**
     * Constructor vacío.
     */
    public PagoContraEntregaDTO() {
        super();
    }

    /**
     * Constructor que inicializa al padre.
     *
     * @param fecha fecha de realización del pago.
     */
    public PagoContraEntregaDTO(LocalDate fecha) {
        super(fecha);
    }
    
    /**
     * Método sobrescrito para mostrar un nombre descriptivo del método de pago.
     * @return Cadena que describe el método de pago.
     */
    @Override
    public String toString() {
        return "Pago Contra Entrega";
    }
}
