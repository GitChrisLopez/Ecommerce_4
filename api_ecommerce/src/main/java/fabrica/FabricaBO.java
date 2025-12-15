
package fabrica;

import BOs.CarritosBO;
import BOs.ClienteBO;
import DAOs.CarritosDAO;
import DAOs.ClienteDAO;
import DAOs.UsuarioDAO;
import definiciones.ICarritosBO;
import definiciones.ICarritosDAO;
import definiciones.IClienteBO;
import definiciones.IClienteDAO;
import definiciones.IUsuarioDAO;

/**
 *
 * @author Romo López Manuel ID: 00000253080S
 */
public class FabricaBO {
    
    public static ICarritosBO obtenerCarritosBO(){
        
        ICarritosDAO carritosDAO = new CarritosDAO();
        ICarritosBO carritosBO = new CarritosBO(carritosDAO);
        
        return carritosBO;
        
    }
    
    public static IClienteBO obtenerClientesBO(){
        
        IClienteDAO clienteDAO = new ClienteDAO();
        IUsuarioDAO usuariosDAO = new UsuarioDAO();
        
        IClienteBO clienteBO = new ClienteBO(clienteDAO, usuariosDAO);
        
        return clienteBO;
        
    }
    
}
