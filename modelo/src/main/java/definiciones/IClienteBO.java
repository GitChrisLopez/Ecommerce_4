
package definiciones;

import dominio.ClienteDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;

/**
 *
 * @author norma
 */
public interface IClienteBO {
    
    public abstract ClienteDTO iniciarSesion(String correo, String contrasenia);
    
    public abstract ClienteDTO obtenerClientePorId(Long idCliente) throws NegocioException, PersistenciaException;
    
    public abstract ClienteDTO editarCliente(ClienteDTO clienteDTO) throws NegocioException, PersistenciaException;
}
