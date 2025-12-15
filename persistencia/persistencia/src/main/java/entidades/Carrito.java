package entidades;

import java.math.BigDecimal;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Archivo: Carrito.java
 * 
 * Representa el Carrito de compra de un Cliente.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 */

@Entity
@Table(name = "carritos")
public class Carrito {

    /**
     * Dato Long que representa el Id del Carrito.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    private Long id;

    /**
     * Objeto BigDecimal que representa el monto total del Carrito.
     */
    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    /**
     * Objeto Cliente que representa el Cliente al que pertenece el Carrito.
     */
    @OneToOne
    @JoinColumn(name = "id_cliente", unique = true, nullable = false)
    private Cliente cliente;

    /**
     * Objseto List<ProductoCarrito> que representa una lista de ProductosCarrito que
     * se han agregado al Carrito.
     */
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<ProductoCarrito> productosCarrito;

    /**
     * Constructor vacío.
     */
    public Carrito() {

    }

    /**
     * Constructor que inicializa todos los atributos al crear el Carrito.
     * @param id Dato Long que representa el Id del Carrito.
     * @param cliente Objeto Cliente que representa el Cliente al que pertenece el Carrito.
     * @param productosCarrito Objeto List<ProductoCarrito> que representa una lista de ProductosCarrito que
     * añadidos al Carrito.
     */
    public Carrito(
            Long id, 
            Cliente cliente,
            List<ProductoCarrito> productosCarrito) {
        
        this.id = id;
        this.cliente = cliente;
        this.productosCarrito = productosCarrito;
    }

    /**
     * Permite obtener el Id del carrito.
     * @return Dato Long que representa el Id del Carrito.
     */
    public Long getId() {
        return id;
    }

    /**
     * Permite establecer el Id del Carrito.
     * @param id Dato Long que representa el Id del Carrito.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Permite obtener el valor total de los productos contenidos en el carrito.
     * @return Objeto BigDecimal que representa el monto total del Carrito.
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Permite establecer el valor total de los productos contenidos en el carrito.
     * @param total Objeto BigDecimal que representa el monto total del Carrito.
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Permite obtener el Cliente dueño de este Carrito.
     * @return Objeto Cliente que representa el dueño del Carrito.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Permite establecer el Cliente dueño de este Carrito.
     * @param cliente Objeto Cliente que representa el dueño del Carrito.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Permite obtener la lista de objetos ProductoCarrito que representan los productos 
     * que se han agregado al Carrito.
     * @return Objeto List<ProductoCarrito> que representa la lista de productos que se han agregado
     * al Carrito.
     */
    public List<ProductoCarrito> getProductosCarrito() {
        return productosCarrito;
    }

    /**
     * Permite establecer la lista de objetos ProductoCarrito que representan los productos 
     * que se han agregado al Carrito.
     * @param productosCarrito Objeto List<ProductoCarrito> que representa la lista de productos que se han agregado
     * al Carrito.
     */
    public void setProductosCarrito(List<ProductoCarrito> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }

}