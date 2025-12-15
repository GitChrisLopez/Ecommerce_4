package dominio;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa los productos que contiene un carrito de compra.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/11/2025
 */

public class ProductoCarrito {

    /**
     * Dato Long que representa el id del ProductoCarrito.
     */
    private Long id;

    /**
     * Objeto ProductoDTO que representa el producto de ProductoCarrito.
     */
    private ProductoDTO producto;

    /**
     * Objeto CarritoDTO que representa el carrito en el que esta ProductoCarrito.
     */
    private CarritoDTO carrito;

    /**
     * Cantidad de ProductoCarrito.
     */
    private Integer cantidad = 1;

    /**
     * Precio unitario de ProductoCarrito.
     */
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
    public ProductoCarrito(Long id, ProductoDTO producto, CarritoDTO carrito, BigDecimal precioUnitario) {
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
     * Obtiene el ProductoDTO asociada a este producto del carrito.
     *
     * @return El producto referenciado.
     */
    public ProductoDTO getProducto() {
        return producto;
    }

    /**
     * Establece el ProductoDTO asociada a este producto del carrito.
     *
     * @param producto El nuevo producto referenciado.
     */
    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    /**
     * Obtiene el CarritoDTO a la que pertenece este productoCarrito.
     *
     * @return El carrito contenedor.
     */
    public CarritoDTO getCarrito() {
        return carrito;
    }

    /**
     * Establece el CarritoDTO a la que pertenece este productoCarrito.
     *
     * @param carrito El nuevo carrito contenedor.
     */
    public void setCarrito(CarritoDTO carrito) {
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
