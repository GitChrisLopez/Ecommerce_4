package dominio;

import java.math.BigDecimal;

/**
 * Representa los productos que contiene un pedido.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 */
public class ProductoPedidoDTO {

    /**
     * Dato Long que representa el id del ProductoCarrito.
     */
    private Long id;

    /**
     * Objeto ProductoDTO que representa el producto de ProductoPedido.
     */
    private ProductoDTO producto;

    /**
     * ID del Pedido al que pertenece el producto.
     */
    private Long idPedido;

    /**
     * Cantidad de ProductoPedido.
     */
    private Integer cantidad;

    /**
     * Precio unitario de ProductoPedido.
     */
    private BigDecimal precioUnitario;

    /**
     * Constructor vacío.
     */
    public ProductoPedidoDTO() {
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
    public ProductoPedidoDTO(Long id, ProductoDTO producto, Long pedido, Integer cantidad, BigDecimal precioUnitario) {
        this.id = id;
        this.producto = producto;
        this.idPedido = pedido;
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
     * Obtiene el ProductoDTO asociado a este producto del pedido.
     *
     * @return El producto referenciado.
     */
    public ProductoDTO getProducto() {
        return producto;
    }

    /**
     * Establece el ProductoDTO asociado a este producto del pedido.
     *
     * @param producto El nuevo producto referenciado.
     */
    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    /**
     * Obtiene el ID del Pedido al que pertenece este productoPedido.
     *
     * @return El ID del pedido.
     */
    public Long getIdPedido() { 
        return idPedido;
    }

    /**
     * Establece el ID del Pedido al que pertenece este productoPedido.
     *
     * @param idPedido El nuevo ID del pedido.
     */
    public void setIdPedido(Long idPedido) { 
        this.idPedido = idPedido;
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
