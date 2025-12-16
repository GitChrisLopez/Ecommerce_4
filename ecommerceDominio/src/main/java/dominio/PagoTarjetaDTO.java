/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import dominio.enumeradores.MetodoPagoDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Representa un tipo de método de pago (Pago con Tarjeta).
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */

public class PagoTarjetaDTO extends MetodoPagoDTO {

    /**
     * Objeto Integer que representa el número (o una parte de él) de la tarjeta.
     */
    Integer numero;

    /**
     * Objeto LocalDate que representa la fecha de vencimiento de la tarjeta (MM/AA).
     */
    private LocalDate fechaVencimiento;

    /**
     * Objeto String que representa el nombre completo del titular de la tarjeta.
     */
    private String nombreTitular;

    /**
     * Objeto String que representa el código de seguridad de la tarjeta.
     */
    private String cvv;

    /**
     * Constructor vacío.
     */
    public PagoTarjetaDTO() {
       super(); 
    }

    /**
     * Constructor que inicializa los atributos propios de la tarjeta.
     * @param numero Número de la tarjeta (o últimos dígitos).
     * @param fechaVencimiento Fecha de vencimiento.
     * @param nombreTitular Nombre del titular de la tarjeta.
     * @param cvv Código de seguridad de la tarjeta.
     */
    public PagoTarjetaDTO(Integer numero, LocalDate fechaVencimiento, String nombreTitular, String cvv) {
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreTitular = nombreTitular;
        this.cvv = cvv;
    }

    /**
     * Constructor que inicializa los atributos propios de la tarjeta e inicializa la fecha de la superclase.
     * @param numero Número de la tarjeta (o últimos dígitos).
     * @param fechaVencimiento Fecha de vencimiento.
     * @param nombreTitular Nombre del titular de la tarjeta.
     * @param cvv Código de seguridad de la tarjeta.
     * @param MontoPagar Monto total a pagar (heredado, aunque se recomienda mover a Pedido).
     * @param fecha Fecha de realización del pago (heredado).
     */
    public PagoTarjetaDTO(Integer numero, LocalDate fechaVencimiento, String nombreTitular, String cvv,
            BigDecimal MontoPagar, LocalDate fecha) {
        super(fecha); // Llama al constructor de MetodoPago con la fecha
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreTitular = nombreTitular;
        this.cvv = cvv;
    }

    // --- Getters y Setters ---

    /**
     * Obtiene el número de la tarjeta (o últimos dígitos).
     * @return El número de la tarjeta.
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Establece el número de la tarjeta (o últimos dígitos).
     * @param numero El nuevo número de la tarjeta.
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Obtiene la fecha de vencimiento de la tarjeta.
     * @return La fecha de vencimiento.
     */
    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Establece la fecha de vencimiento de la tarjeta.
     * @param fechaVencimiento La nueva fecha de vencimiento.
     */
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    /**
     * Obtiene el nombre del titular de la tarjeta.
     * @return El nombre del titular.
     */
    public String getNombreTitular() {
        return nombreTitular;
    }

    /**
     * Establece el nombre del titular de la tarjeta.
     * @param nombreTitular El nuevo nombre del titular.
     */
    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    /**
     * Obtiene el código de seguridad (CVV).
     * @return El CVV.
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Establece el código de seguridad (CVV).
     * @param cvv El nuevo CVV.
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    
    /**
     * Método sobrescrito para mostrar un nombre descriptivo del método de pago.
     * @return Cadena que describe el método de pago.
     */
    @Override
    public String toString() {
        return "Pago Tarjeta";
    }
    
}
