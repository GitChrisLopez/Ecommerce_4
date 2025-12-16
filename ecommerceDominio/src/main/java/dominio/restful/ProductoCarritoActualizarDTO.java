
package dominio.restful;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public class ProductoCarritoActualizarDTO {
    
    private Long idProducto;
    private int cantidad;

    public ProductoCarritoActualizarDTO() {
    }

    public ProductoCarritoActualizarDTO(Long idProducto, int cantidad) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    } 

    public Long getIdProducto() {
        return idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
}
