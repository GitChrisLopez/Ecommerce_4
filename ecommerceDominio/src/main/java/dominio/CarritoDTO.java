package dominio;

import java.math.BigDecimal;
import java.util.List;

/**
 * Archivo: CarritoDTO.java
 
 Representa el CarritoDTO de compra de un Cliente.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 */

public class CarritoDTO {

    /**
     * Dato Long que representa el Id del CarritoDTO.
     */
    private Long id;

    /**
     * Objeto BigDecimal que representa el monto total del CarritoDTO.
     */
    private BigDecimal total = BigDecimal.ZERO;

    /**
     * Objeto Cliente que representa el Cliente al que pertenece el CarritoDTO.
     */
    private ClienteDTO cliente;

    /**
     * Objseto List<ProductoCarrito> que representa una lista de ProductosCarrito que
 se han agregado al CarritoDTO.
     */
    private List<ProductoCarrito> productosCarrito;

    /**
     * Constructor vacío.
     */
    public CarritoDTO() {

    }

    /**
     * Constructor que inicializa todos los atributos al crear el Carrito.
     * @param id Dato Long que representa el Id del Carrito.
     * @param cliente Objeto Cliente que representa el Cliente al que pertenece el Carrito.
     * @param productosCarrito Objeto List<ProductoCarrito> que representa una lista de ProductosCarrito que
     * añadidos al Carrito.
     */
    public CarritoDTO(
            Long id, 
            ClienteDTO cliente,
            List<ProductoCarrito> productosCarrito) {
        
        this.id = id;
        this.cliente = cliente;
        this.productosCarrito = productosCarrito;
    }

    /**
     * Permite obtener el Id del carrito.
     * @return Dato Long que representa el Id del CarritoDTO.
     */
    public Long getId() {
        return id;
    }

    /**
     * Permite establecer el Id del CarritoDTO.
     * @param id Dato Long que representa el Id del CarritoDTO.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Permite obtener el valor total de los productos contenidos en el carrito.
     * @return Objeto BigDecimal que representa el monto total del CarritoDTO.
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Permite establecer el valor total de los productos contenidos en el carrito.
     * @param total Objeto BigDecimal que representa el monto total del CarritoDTO.
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Permite obtener el Cliente dueño de este CarritoDTO.
     * @return Objeto Cliente que representa el dueño del CarritoDTO.
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Permite establecer el Cliente dueño de este CarritoDTO.
     * @param cliente Objeto Cliente que representa el dueño del CarritoDTO.
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * Permite obtener la lista de objetos ProductoCarrito que representan los productos 
 que se han agregado al CarritoDTO.
     * @return Objeto List<ProductoCarrito> que representa la lista de productos que se han agregado
 al CarritoDTO.
     */
    public List<ProductoCarrito> getProductosCarrito() {
        return productosCarrito;
    }

    /**
     * Permite establecer la lista de objetos ProductoCarrito que representan los productos 
 que se han agregado al CarritoDTO.
     * @param productosCarrito Objeto List<ProductoCarrito> que representa la lista de productos que se han agregado
 al CarritoDTO.
     */
    public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }

}
