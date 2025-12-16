package definiciones;

import entidades.Cliente;
import excepciones.PersistenciaException;

/**
 *
 * @author norma
 */
public interface IClienteDAO {

    public abstract Cliente iniciarSesion(String correo, String contrasenia);
            
    public abstract Cliente obtenerClientePorId(Long idCliente) throws PersistenciaException;

    public abstract Cliente editarCliente(Cliente cliente) throws PersistenciaException;
    
    public abstract Cliente obtenerClientePorCorreo(String correo) throws PersistenciaException;
    
}
