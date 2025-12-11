package definiciones;

import entidades.Cliente;
import excepciones.PersistenciaException;

/**
 *
 * @author norma
 */
public interface IClienteDAO {

    public Cliente obtenerClientePorId(Long idCliente) throws PersistenciaException;

    public Cliente editarCliente(Cliente cliente) throws PersistenciaException;
    
    public Cliente obtenerClientePorCorreo(String correo) throws PersistenciaException;
    
}
