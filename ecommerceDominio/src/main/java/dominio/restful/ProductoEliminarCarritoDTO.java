
package dominio.restful;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public class ProductoEliminarCarritoDTO {
    
    private Long idProducto;

    public ProductoEliminarCarritoDTO() {
    }

    public ProductoEliminarCarritoDTO(Long idProducto, int cantidad) {
        this.idProducto = idProducto;
    }
    
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
    
}
