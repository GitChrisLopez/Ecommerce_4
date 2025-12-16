package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Archivo: Direccion.java 
 * 
 * Clase que representa una dirección asociada con un Cliente o a un Pedido.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */

public class DireccionDTO {

    /**
     * Dato Long que representa el id de la Dirección.
     */
    private Long id;

    /**
     * Objeto String que representa el código postal de la dirección.
     */
    private String codigoPostal;

    /**
     * Objeto String que representa la colonia de la dirección.
     */
    private String colonia;

    /**
     * Objeto String que representa la calle de la dirección.
     */
    private String calle;

    /**
     * Objeto String que representa el número exterior de la dirección.
     */
    private String numero;

    /**
     * Objeto Cliente que representa el Cliente al que pertenece esta Dirección.
     */
    private ClienteDTO cliente;

    /**
     * Constructor por defecto
     */
    public DireccionDTO(){
        
    }
    
    /**
     * Constructor para inicializar una instancia de Direccion.
     *
     * @param id Dato Long que representa el id de la Dirección.
     * @param codigoPostal Objeto String que representa el código postal.
     * @param colonia Objeto String que representa la colonia.
     * @param calle Objeto String que representa la calle.
     * @param numero Objeto String que representa el número de la dirección.
     * @param cliente Objeto Cliente que representa el Cliente asociado.
     */
    public DireccionDTO(
            Long id, 
            String codigoPostal, 
            String colonia, 
            String calle, 
            String numero, 
            ClienteDTO cliente) {
        
        this.id = id;
        this.codigoPostal = codigoPostal;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
        this.cliente = cliente;
    }

    /**
     * Permite obtener el id de esta Dirección.
     *
     * @return Dato Long que representa el id de la Dirección.
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Permite establecer el idl de esta Dirección.
     *
     * @param id Dato Long que representa el id de la Dirección.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Permite obtener el código postal de esta Dirección.
     *
     * @return Objeto String que representa el código postal.
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Permite establecer el código postal de esta Dirección.
     *
     * @param codigoPostal Objeto String que representa el código postal.
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Permite obtener la colonia de esta Dirección.
     *
     * @return Objeto String que representa la colonia.
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Permite establecer la colonia de esta Dirección.
     *
     * @param colonia Objeto String que representa la colonia.
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Permite obtener la calle de esta Dirección.
     *
     * @return Objeto String que representa la calle.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Permite establecer la calle de esta Dirección.
     *
     * @param calle Objeto String que representa la calle.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Permite obtener el número de esta Dirección (exterior/interior).
     *
     * @return Objeto String que representa el número.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Permite establecer el número de esta Dirección (exterior/interior).
     *
     * @param numero Objeto String que representa el número.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Permite obtener el Cliente asociado a esta Dirección.
     *
     * @return Objeto Cliente que representa el Cliente asociado.
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Permite establecer el Cliente asociado a esta Dirección.
     *
     * @param cliente Objeto Cliente que representa el Cliente asociado.
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

}
