
package entidades;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Representa un tipo de método de pago (Transferencia).
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */

@Entity
@DiscriminatorValue("TRANSFERENCIA")
@Table(name = "pago_transferencia")
public class PagoTransferencia extends MetodoPago{

    /**
     * Objeto String que representa el nombre del banco desde donde se emite la transferencia.
     */
    @Column(name = "banco_emisor")
    public String bancoEmisor;

    /**
     * Objeto String que representa los últimos cuatro dígitos de la cuenta de origen.
     */
    @Column(name = "cuatro_digitos")
    public String cuatroDigitos;

    /**
     * Constructor vacío.
     */
    public PagoTransferencia() {
        super();
    }

    /**
     * Constructor que inicializa los atributos propios de la transferencia.
     * @param bancoEmisor Nombre del banco emisor.
     * @param cuatroDigitos Últimos cuatro dígitos de la cuenta.
     */
    public PagoTransferencia(String bancoEmisor, String cuatroDigitos) {
        this.bancoEmisor = bancoEmisor;
        this.cuatroDigitos = cuatroDigitos;
    }

    /**
     * Constructor que inicializa los atributos propios y hereda la fecha de la superclase MetodoPago.
     * @param bancoEmisor Nombre del banco emisor.
     * @param cuatroDigitos Últimos cuatro dígitos de la cuenta.
     * @param fecha Fecha de realización del pago.
     */
    public PagoTransferencia(String bancoEmisor, String cuatroDigitos, LocalDate fecha) {
        super(fecha);
        this.bancoEmisor = bancoEmisor;
        this.cuatroDigitos = cuatroDigitos;
    }
    
    

    /**
     * Obtiene el nombre del banco emisor de la transferencia.
     * @return El nombre del banco emisor.
     */
    public String getBancoEmisor() {
        return bancoEmisor;
    }

    /**
     * Establece el nombre del banco emisor de la transferencia.
     * @param bancoEmisor El nuevo nombre del banco emisor.
     */
    public void setBancoEmisor(String bancoEmisor) {
        this.bancoEmisor = bancoEmisor;
    }

    /**
     * Obtiene los últimos cuatro dígitos de la cuenta de origen.
     * @return Los cuatro dígitos.
     */
    public String getCuatroDigitos() {
        return cuatroDigitos;
    }

    /**
     * Establece los últimos cuatro dígitos de la cuenta de origen.
     * @param cuatroDigitos Los nuevos cuatro dígitos.
     */
    public void setCuatroDigitos(String cuatroDigitos) {
        this.cuatroDigitos = cuatroDigitos;
    }

}

