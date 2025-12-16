/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import dominio.enumeradores.MetodoPagoDTO;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Representa un tipo de método de pago (Transferencia).
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */

public class PagoTransferenciaDTO extends MetodoPagoDTO{

    /**
     * Objeto String que representa el nombre del banco desde donde se emite la transferencia.
     */
    public String bancoEmisor;

    /**
     * Objeto String que representa los últimos cuatro dígitos de la cuenta de origen.
     */
    public String cuatroDigitos;

    /**
     * Constructor vacío.
     */
    public PagoTransferenciaDTO() {
        super();
    }

    /**
     * Constructor que inicializa los atributos propios de la transferencia.
     * @param bancoEmisor Nombre del banco emisor.
     * @param cuatroDigitos Últimos cuatro dígitos de la cuenta.
     */
    public PagoTransferenciaDTO(String bancoEmisor, String cuatroDigitos) {
        this.bancoEmisor = bancoEmisor;
        this.cuatroDigitos = cuatroDigitos;
    }

    /**
     * Constructor que inicializa los atributos propios y hereda la fecha de la superclase MetodoPago.
     * @param bancoEmisor Nombre del banco emisor.
     * @param cuatroDigitos Últimos cuatro dígitos de la cuenta.
     * @param fecha Fecha de realización del pago.
     */
    public PagoTransferenciaDTO(String bancoEmisor, String cuatroDigitos, LocalDate fecha) {
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
    
    /**
     * Método sobrescrito para mostrar un nombre descriptivo del método de pago.
     * @return Cadena que describe el método de pago.
     */
    @Override
    public String toString() {
        return "Pago Transferencia";
    }

}
