package fabrica;

import BOs.AutoresBO;
import BOs.CategoriasBO;
import BOs.ClienteBO;
import BOs.EditorialesBO;
import BOs.LibrosBO;
import BOs.PedidoBO;
import BOs.ProductosBO;
import BOs.ReseniaBO;
import DAOs.AutoresDAO;
import DAOs.CategoriasDAO;
import DAOs.ClienteDAO;
import DAOs.EditorialesDAO;
import DAOs.LibrosDAO;
import DAOs.PedidoDAO;
import DAOs.ProductosDAO;
import DAOs.ReseniaDAO;
import DAOs.UsuarioDAO;
import definiciones.IAutoresBO;
import definiciones.IAutoresDAO;
import definiciones.ICategoriasBO;
import definiciones.ICategoriasDAO;
import definiciones.IClienteBO;
import definiciones.IClienteDAO;
import definiciones.IEditorialesBO;
import definiciones.IEditorialesDAO;
import definiciones.ILibrosBO;
import definiciones.ILibrosDAO;
import definiciones.IPedidoBO;
import definiciones.IPedidoDAO;
import definiciones.IProductosBO;
import definiciones.IProductosDAO;
import definiciones.IReseniaBO;
import definiciones.IReseniaDAO;
import definiciones.IUsuarioDAO;

/**
 * Fábrica de BO.
 *
 * @author Romo López Manuel ID: 00000253080
 *
 */
public class FabricaBO {

    public static IProductosBO obtenerProductosBO() {

        IProductosDAO productosDAO = new ProductosDAO();

        IProductosBO productosBO = new ProductosBO(productosDAO);

        return productosBO;

    }

    public static ILibrosBO obtenerLibrosBO(){
        
        ILibrosDAO librosDAO = new LibrosDAO();
        
        ILibrosBO librosBO = new LibrosBO(librosDAO);
        
        return librosBO;
        
    }
    
    public static IReseniaBO obtenerReseniasBO() {

        IReseniaDAO reseniaDAO = new ReseniaDAO();

        IReseniaBO reseniaBO = new ReseniaBO(reseniaDAO);

        return reseniaBO;

    }
    
    public static IPedidoBO obtenerPedidosBO(){
        
        IPedidoDAO pedidoDAO = new PedidoDAO();
        
        IPedidoBO pedidoBO = new PedidoBO(pedidoDAO);
        
        return pedidoBO;
    }
    
    
    public static ICategoriasBO obtenerCategoriasBO(){
        
        ICategoriasDAO categoriaDAO = new CategoriasDAO();
        
        ICategoriasBO categoriasBO = new CategoriasBO(categoriaDAO);
        
        return categoriasBO;
    }
    
    public static IAutoresBO obtenerAutoresBO(){
        
        IAutoresDAO autoresDAO = new AutoresDAO();
        
        IAutoresBO autoresBO = new AutoresBO(autoresDAO);
        
        return autoresBO;
    }
    
    public static IEditorialesBO obtenerEditorialesBO(){
        
        IEditorialesDAO editorialesDAO = new EditorialesDAO();
        
        IEditorialesBO editorialesBO = new EditorialesBO(editorialesDAO);
        
        return editorialesBO;
    }
    
    public static IClienteBO obtenerClientesBO(){
        
        IClienteDAO clientesDAO = new ClienteDAO();
        IUsuarioDAO usuariosDAO = new UsuarioDAO();
        
        IClienteBO clientesBO = new ClienteBO(clientesDAO, usuariosDAO);
        
        return clientesBO;
    }
    
}
