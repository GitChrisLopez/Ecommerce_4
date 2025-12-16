package dominio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Archivo: Cliente.java 
 * 
 * Clase que representa a un cliente del sistema. Contiene información específica 
 * como su teléfono, direcciones de envío, el carrito de compras actual y el historial de pedidos.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */

public class ClienteDTO extends UsuarioDTO {

    /**
     * Objeto String que representa el número de teléfono del Cliente.
     */
    private String telefono;

    /**
     * Objeto Srtring que representa la dirección de la imagen de perfil del Cliente.
     */
    private String urlImagenPerfil;
    
    /**
     * Lista de objetos Direccion que representa el conjunto de direcciones
     * asociadas a este Cliente.
     */
    private List<DireccionDTO> direcciones;

    /**
     * Objeto CarritoDTO que representa el carrito de compras actual del Cliente.
     */
    private CarritoDTO carrito;

    /**
     * Lista de objetos Pedido que representa el historial de pedidos realizados
     * por este Cliente.
     */
    private List<PedidoDTO> pedidos;
    
    /**
     * Dato boolean que indica si el usuario esta activo o no.
     */
    private boolean activo;

    /**
     * Constructor por defecto.
     */
    public ClienteDTO(){
        
    }
    
    /**
     * Constructor que permite crear un Cliente, recibe los valores de todos sus atributos.
     * @param telefono Objeto String que representa el número de teléfono del Cliente.
     * @param imagenPerfil Objeto String que representa la dirección de la imagen del perfil del Cliente.
     * @param direcciones Lista de objetos Direccion que representa la lista de direcciones del Cliente.
     * @param carrito Objeto CarritoDTO que representa el carrito del Cliente.
     * @param pedidos Lista de obetos Pedido que representa la lista de pedidos del Cliente.
     * @param activo Dato boolean que determina si el Cliente está activo o no.
     */
    public ClienteDTO(
            String telefono,
            String imagenPerfil,
            List<DireccionDTO> direcciones,
            CarritoDTO carrito,
            List<PedidoDTO> pedidos,
            boolean activo) {
        
        this.telefono = telefono;
        this.direcciones = direcciones;
        this.carrito = carrito;
        this.pedidos = pedidos;
        this.activo = activo;
    }

    /**
     * Permite obtener la dirección de la imagen de perfil del Cliente.
     * @return Objeto String que representa la dirección de la imagen del perfil del Cliente.
     */
    public String getUrlImagenPerfil() {
        return urlImagenPerfil;
    }

    /**
     * Permite establecer la dirección de la imagen de perfil del Cliente.
     * @param imagenPerfil Objeto String que representa la dirección de la imagen del perfil del Cliente.
     */
    public void setUrlImagenPerfil(String imagenPerfil) {
        this.urlImagenPerfil = imagenPerfil;
    }
    
    /**
     * Permite obtener el número de teléfono del Cliente.
     * @return Objeto String que representa el número de teléfono.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Permite establecer el número de teléfono del Cliente.
     * @param telefono Objeto String que representa el número de teléfono.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    

    /**
     * Permite obtener la lista de direcciones del Cliente.
     * @return Objeto List<Direccion> que representa las direcciones del
     * Cliente.
     */
    public List<DireccionDTO> getDirecciones() {
        return direcciones;
    }

    /**
     * Permite establecer la lista de direcciones del Cliente.
     * @param direcciones Objeto List<Direccion> que representa las direcciones
     * del Cliente.
     */
    public void setDirecciones(List<DireccionDTO> direcciones) {
        this.direcciones = direcciones;
    }

    /**
     * Permite obtener el CarritoDTO de compras actual del Cliente.
     * @return Objeto CarritoDTO que representa el carrito de compras.
     */
    public CarritoDTO getCarrito() {
        return carrito;
    }

    /**
     * Permite establecer el CarritoDTO de compras actual del Cliente.
     * @param carrito Objeto CarritoDTO que representa el carrito de compras.
     */
    public void setCarrito(CarritoDTO carrito) {
        this.carrito = carrito;
    }

    /**
     * Permite obtener el historial de pedidos del Cliente.
     * @return Objeto List<Pedido> que representa el historial de pedidos.
     */
    public List<PedidoDTO> getPedidos() {
        return pedidos;
    }

    /**
     * Permite establecer el historial de pedidos del Cliente.
     * @param pedidos Objeto List<Pedido> que representa el historial de
     * pedidos.
     */
    public void setPedidos(List<PedidoDTO> pedidos) {
        this.pedidos = pedidos;
    }
    
    /**
     * Permite determinar si el Cliente está activo o no.
     * @return Dato boolean que determina si el Cliente está activo o no.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Permite establecer si el Cliente está activo o no.
     * @param activo Dato boolean que determina si el Cliente está activo o no.
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
