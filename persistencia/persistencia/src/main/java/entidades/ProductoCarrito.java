
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
 * Representa los productos que contiene un carrito de compra.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 */

@Entity
@Table(name = "productos_carrito")
public class ProductoCarrito {

    /**
     * Dato Long que representa el id del ProductoCarrito.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_carrito")
    private Long id;

    /**
     * Objeto Producto que representa el producto de ProductoCarrito.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    /**
     * Objeto Carrito que representa el carrito en el que esta ProductoCarrito.
     */
    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private Carrito carrito;

    /**
     * Cantidad de ProductoCarrito.
     */
    @Column(name = "cantidad")
    private Integer cantidad = 1;

    /**
     * Precio unitario de ProductoCarrito.
     */
    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    /**
     * Constructor vacío.
     */
    public ProductoCarrito() {
    }

    /**
     * Constructor que inicializa todos los atributos.
     *
     * @param id id del productoCarrito
     * @param producto id del producto
     * @param carrito id del carrito
     * @param precioUnitario precio unitario del productoCarrito
     */
    public ProductoCarrito(Long id, Producto producto, Carrito carrito, BigDecimal precioUnitario) {
        this.id = id;
        this.producto = producto;
        this.carrito = carrito;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el identificador único de este producto del carrito.
     *
     * @return El id de ProductoCarrito.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de este producto del carrito.
     *
     * @param id El nuevo id de ProductoCarrito.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el Producto asociada a este producto del carrito.
     *
     * @return El producto referenciado.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el Producto asociada a este producto del carrito.
     *
     * @param producto El nuevo producto referenciado.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene el Carrito a la que pertenece este productoCarrito.
     *
     * @return El carrito contenedor.
     */
    public Carrito getCarrito() {
        return carrito;
    }

    /**
     * Establece el Carrito a la que pertenece este productoCarrito.
     *
     * @param carrito El nuevo carrito contenedor.
     */
    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    /**
     * Obtiene la cantidad de unidades de este producto en el carrito.
     *
     * @return La cantidad del producto.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de unidades de este producto en el carrito.
     *
     * @param cantidad La nueva cantidad del producto.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del producto registrado en el momento de la
     * adición.
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
