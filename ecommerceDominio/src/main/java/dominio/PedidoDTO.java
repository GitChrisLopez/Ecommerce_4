package dominio;

import dominio.enumeradores.MetodoPagoDTO;
import dominio.enumeradores.EstadoDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * Archivo: Pedido.java
 *
 * Clase que representa un pedido realizado por un Cliente al finalizar una
 * compra y realizar su pago.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */
public class PedidoDTO {

    /**
     * Dato Long que representa el id del Pedido.
     */
    private Long id;
    /**
     * Objeto String que representa el número del Pedido.
     */
    private String numeroUnico;
    /**
     * Objeto LocalDateTime que representa la fecha en que se realizó el pedido.
     */
    private LocalDateTime fecha;
    /**
     * Objeto
     */
    private EstadoDTO estado;
    
    /**
     * Objeto BigDecimal que representa el total del Pedido.
     */
    private BigDecimal total;
    
    /**
     * Objeto Direccion que representa la dirección de envío del pedido.
     */
    private DireccionDTO direccionEnvio;
    /**
     * Objeto MetodoPago que representa el método de pago que se utilizó para
     * pagar el Pedido.
     */
    private MetodoPagoDTO metodoPago;

    /**
     * Objeto Cliente que representa el Cliente que realizó el Pedido.
     */
    private ClienteDTO cliente;
    
    /**
     * Lista de Productos Pedido.
     */
    private List<ProductoPedidoDTO> productosPedido;

    /**
     * Constructor vacío.
     */
    public PedidoDTO() {

    }

    /**
     * Constructor que inicializa todos los atributos de la clase.
     * @param id id del pedido.
     * @param numeroUnico número único del pedido.
     * @param fecha fecha del pedido.
     * @param estado estado del pedido.
     * @param total total del pedido.
     * @param direccionEnvio dirección de envío del pedido.
     * @param metodoPago método de pago del pedido.
     * @param cliente cliente que realizó el pedido.
     * @param productosPedido lista de productos del pedido.
     */
    public PedidoDTO(Long id, String numeroUnico, LocalDateTime fecha, EstadoDTO estado, BigDecimal total, DireccionDTO direccionEnvio, MetodoPagoDTO metodoPago, ClienteDTO cliente, List<ProductoPedidoDTO> productosPedido) {
        this.id = id;
        this.numeroUnico = numeroUnico;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
        this.direccionEnvio = direccionEnvio;
        this.metodoPago = metodoPago;
        this.cliente = cliente;
        this.productosPedido = productosPedido;
    }

    /**
     * @return identificador del pedido.
     */
    public Long getId() {
        return id;
    }

    /**
     * Permite establecer el id de este Pedido.
     *
     * @param id identificador del pedido.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Permite obtener el número de este Pedido.
     *
     * @return Objeto String que representa el número del Pedido.
     */
    public String getNumeroUnico() {
        return numeroUnico;
    }

    /**
     * Permite establecer el número de este Pedido.
     *
     * @param numeroUnico Objeto String que representa el número del Pedido.
     */
    public void setNumeroUnico(String numeroUnico) {
        this.numeroUnico = numeroUnico;
    }

    /**
     * Permite obtener la fecha de realización del Pedido.
     *
     * @return Objeto LocalDateTime que representa la fecha en que se hizo el
     * Pedido.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Permite establecer la fecha de realización del Pedido.
     *
     * @param fecha bjeto LocalDateTime que representa la fecha en que se hizo
     * el Pedido.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Permite obtener el estado del Pedido.
     *
     * @return Objeto Estado que representa el Estado del Pedido.
     */
    public EstadoDTO getEstado() {
        return estado;
    }

    /**
     * Permite establecer el estado del Pedido.
     *
     * @param estado Objeto Estado que representa el Estado del Pedido.
     */
    public void setEstado(EstadoDTO estado) {
        this.estado = estado;
    }

    /**
     * Permite obtener la dirección de envío del Pedido.
     *
     * @return Objeto Direccion que representa la dirección de envío del Pedido.
     */
    public DireccionDTO getDireccionEnvio() {
        return direccionEnvio;
    }

    /**
     * Permite establecer la dirección de envío del Pedido.
     *
     * @param direccionEnvio Objeto Direccion que representa la dirección de
     * envío del Pedido.
     */
    public void setDireccionEnvio(DireccionDTO direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    /**
     * Permite obtener el método de pago del Pedido.
     *
     * @return Objeto MetodoPago que representa el método de pago con el que se
     * realizó el pedido.
     */
    public MetodoPagoDTO getMetodoPago() {
        return metodoPago;
    }

    /**
     * Permite establecer el método de pago del Pedido.
     *
     * @param metodoPago Objeto MetodoPago que representa el método de pago con el
     * que se realiza el pedido.
     */
    public void setMetodoPago(MetodoPagoDTO metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Permite obtener el Cliente que realizó el Pedido.
     *
     * @return Objeto Cliente que realizó el Pedido.
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Permite establecer el Cliente que realizó el Pedido.
     *
     * @param cliente Objeto Cliente que realizó el Pedido.
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
    
    /**
     * Permite obtener el total del Pedido.
     * @return Objeto BigDecimal que representa el total.
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Permite establecer el total del Pedido
     * @param total Objeto BigDecimal que representa el total.
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * Permite obtener los productos pedido del pedido.
     * @return Lista de Objetos de ProductoPedido.
     */
    public List<ProductoPedidoDTO> getProductosPedido() {
        return productosPedido;
    }

    /**
     * Permite establecer la lista de productos pedido del pedio.
     * @param productosPedido Lista de Objetos de ProductoPedido.
     */
    public void setProductosPedido(List<ProductoPedidoDTO> productosPedido) {
        this.productosPedido = productosPedido;
    }

}
