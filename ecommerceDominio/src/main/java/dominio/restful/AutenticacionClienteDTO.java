
package dominio.restful;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public class AutenticacionClienteDTO {
    
    private String correo;
    private String contrasenia;

    public AutenticacionClienteDTO(String correo, String contrasenia) {
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

}
