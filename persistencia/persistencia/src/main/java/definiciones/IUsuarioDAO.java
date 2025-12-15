
package definiciones;

import entidades.Usuario;
/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public interface IUsuarioDAO {
    
    public abstract Usuario iniciarSesion(String correo, String contrasenia);

    public abstract Usuario obtenerUsuarioPorCorreo(String correo);
    
}
