
package dominio.restful;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public class ConsultaCarritoDTO {

    public ConsultaCarritoDTO() {
    }

    public ConsultaCarritoDTO(Long idCliente) {
        this.idCliente = idCliente;
    }

    private Long idCliente;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }
    
    
}
