package dominio;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Archivo: ProductoDTO.java
 
 Clase que representa un ProductoDTO dentro del catalogo del sistema de comercio.
 * 
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/11/2025
 */

public class ProductoDTO {
    
    /**
     * Objeto Long que representa el Id del ProductoDTO.
     */
    private Long id;

    /**
     * Objeto Integer que representa el número de páginas del ProductoDTO.
     */
    private Integer numeroPaginas;

    /**
     * Objeto String que representa el ISBN (International Standard Book Number) del ProductoDTO.
     */
    private String isbn;

    /**
     * Objeto String que representa la url de la imagen del ProductoDTO.
     */
    private String urlImagen;
    
    /**
     * Enumerador Formato que determina el formato del ProductoDTO.
     */
    private FormatoDTO formato;
    
    /**
     * Objeto Integer que representa el stock ProductoDTO actual.
     */
    private Integer stock = 0;
    
    /**
     * Objeto BigDecimal que representa el precio del ProductoDTO.
     */
    private BigDecimal precio; 

    /**
     * Objeto Libro que representa el Libro que representa el ProductoDTO.
     */
    private LibroDTO libro;

    /**
     * Objeto List<ProductoCarrito> que representa los productosCarrito que
 hacen referencia a este ProductoDTO.
     */
    private List<ProductoCarritoDTO> productosCarrito;

    /**
     * Objeto List<ProductoPedido> que representa los productosPedido que hacen
 referencia a este ProductoDTO.
     */
    private List<ProductoPedidoDTO> productosPedido;

    /**
     * Constructor por defecto.
     */
    public ProductoDTO() {
    }

    /**
     * Constructor que inicializa todos los atributos.
     * @param id Objeto Long que representa el Id del Producto.
     * @param numeroPaginas Objeto Integer que representa el número de páginas del Producto.
     * @param isbn Objeto String que representa el ISBN del Producto.
     * @param urlImagen Objeto String que representa la url de la imagen del Producto.
     * @param formato Enumerador Formato que determina el formato del Producto.
     * @param precio Objeto BigDecimal que representa el precio del Producto.
     * @param libro Objeto Libro que representa el Libro que representa el Producto.
     * @param productosCarrito 
     * @param productosPedido 
     */
    public ProductoDTO(
            Long id,
            Integer numeroPaginas,
            String isbn,
            String urlImagen, 
            FormatoDTO formato, 
            BigDecimal precio, 
            LibroDTO libro, 
            List<ProductoCarritoDTO> productosCarrito, 
            List<ProductoPedidoDTO> productosPedido) {
        
        this.id = id;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.urlImagen = urlImagen;
        this.formato = formato;
        this.precio = precio;
        this.libro = libro;
        this.productosCarrito = productosCarrito;
        this.productosPedido = productosPedido;
    }

    /**
     * Obtiene el identificador único del producto.
     * @return El id del producto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del producto.
     * @param id El nuevo id del producto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el número total de páginas del producto.
     * @return El número de páginas del producto.
     */
    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
     * Establece el número total de páginas del producto.
     * @param numeroPaginas El nuevo número de páginas del producto.
     */
    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * Obtiene el ISBN (International Standard Book Number) del producto.
     * @return El ISBN del producto.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Establece el ISBN (International Standard Book Number) del producto.
     * @param isbn El nuevo ISBN del producto.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Obtiene la URL donde se encuentra la imagen del producto.
     * @return La url de la imagen del producto.
     */
    public String getUrlImagen() {
        return urlImagen;
    }

    /**
     * Establece la URL donde se encuentra la imagen del producto.
     * @param urlImagen La nueva url de la imagen del producto.
     */
    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    /**
     * Obtiene el formato del producto.
     * @return El formato del producto.
     */
    public FormatoDTO getFormato() {
        return formato;
    }

    /**
     * Establece el formato del producto.
     * @param formato El nuevo formato del producto.
     */
    public void setFormato(FormatoDTO formato) {
        this.formato = formato;
    }

    /**
     * Obtiene la cantidad de unidades disponibles en inventario.
     * @return El stock disponible del producto.
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Establece la cantidad de unidades disponibles en inventario.
     * @param stock El nuevo stock del producto.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * Obtiene el precio de venta del producto.
     * @return El precio del producto.
     */
    public BigDecimal getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de venta del producto.
     * @param precio El nuevo precio del producto.
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el objeto Libro asociado a este producto.
     * @return El libro del producto.
     */
    public LibroDTO getLibro() {
        return libro;
    }

    /**
     * Establece el objeto Libro asociado a este producto.
     * @param libro El nuevo libro del producto.
     */
    public void setLibro(LibroDTO libro) {
        this.libro = libro;
    }

    /**
     * Obtiene la lista de ProductoCarrito que hacen referencia a este producto.
     * @return La lista de items del carrito asociados al producto.
     */
    public List<ProductoCarritoDTO> getProductosCarrito() {
        return productosCarrito;
    }

    /**
     * Establece la lista de ProductoCarrito que hacen referencia a este producto.
     * @param productosCarrito La nueva lista de items del carrito.
     */
    public void setProductosCarrito(List<ProductoCarritoDTO> productosCarrito) {
        this.productosCarrito = productosCarrito;
    }

    /**
     * Obtiene la lista de ProductoPedido que hacen referencia a este producto.
     * @return La lista de items de pedido asociados al producto.
     */
    public List<ProductoPedidoDTO> getProductosPedido() {
        return productosPedido;
    }

    /**
     * Establece la lista de ProductoPedido que hacen referencia a este producto.
     * @param productosPedido La nueva lista de items de pedido.
     */
    public void setProductosPedido(List<ProductoPedidoDTO> productosPedido) {
        this.productosPedido = productosPedido;
    }
    
}
