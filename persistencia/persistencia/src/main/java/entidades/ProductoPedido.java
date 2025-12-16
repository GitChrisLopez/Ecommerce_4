
package entidades;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Representa los productos que contiene un pedido.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 */

@Entity
@Table(name = "productos_pedido")
public class ProductoPedido {

    /**
     * Dato Long que representa el id del ProductoCarrito.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_pedido")
    private Long id;

    /**
     * Objeto Producto que representa el producto de ProductoPedido.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    /**
     * Objeto Pedido que representa el pedido en el que esta ProductoPedido.
     */
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    /**
     * Cantidad de ProductoPedido.
     */
    @Column(name = "cantidad")
    private Integer cantidad;

    /**
     * Precio unitario de ProductoPedido.
     */
    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    /**
     * Constructor vacío.
     */
    public ProductoPedido() {
    }

    /**
     * Constructor que inicializa todos los atributos.
     *
     * @param id id del productoPedido
     * @param producto id del producto
     * @param pedido id del pedido
     * @param cantidad cantidad del productoPedido
     * @param precioUnitario precio unitario del productoPedido
     */
    public ProductoPedido(Long id, Producto producto, Pedido pedido, Integer cantidad, BigDecimal precioUnitario) {
        this.id = id;
        this.producto = producto;
        this.pedido = pedido;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el identificador único de este producto del pedido.
     *
     * @return El id de la entidad ProductoPedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de este producto del pedido.
     *
     * @param id El nuevo id de la entidad ProductoPedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el Producto asociado a este producto del pedido.
     *
     * @return El producto referenciado.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el Producto asociado a este producto del pedido.
     *
     * @param producto El nuevo producto referenciado.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene el Pedido a la que pertenece este productoPedido.
     *
     * @return El pedido contenedor.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Establece el Pedido a la que pertenece este productoPedido.
     *
     * @param pedido El nuevo pedido contenedor.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Obtiene la cantidad de unidades de este producto en el pedido.
     *
     * @return La cantidad del producto.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de unidades de este producto en el pedido.
     *
     * @param cantidad La nueva cantidad del producto.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del producto registrado en el momento de la
     * creación del pedido.
     *
     * @return El precio unitario.
     */
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del producto registrado.
     *
     * @param precioUnitario El nuevo precio unitario.
     */
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
