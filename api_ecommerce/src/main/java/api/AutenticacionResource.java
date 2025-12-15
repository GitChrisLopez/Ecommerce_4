
package api;

import definiciones.IClienteBO;
import dominio.ClienteDTO;
import dominio.restful.AutenticacionClienteDTO;
import fabrica.FabricaBO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import utils.AutenticacionUtils;
import utils.ResponseUtils;

/**
 * REST Web Service
 *
 * @author Romo López Manuel ID: 00000253080
 */
@Path("autenticacion")
@RequestScoped
public class AutenticacionResource {

    private IClienteBO clientesBO;
    
    private final String MENSAJE_AUTORIZADO = "Autorizado";
    private final String MENSAJE_NO_AUTORIZADO = "No autorizado";
    
    public AutenticacionResource() {
        
        clientesBO = FabricaBO.obtenerClientesBO();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(AutenticacionClienteDTO autenticacionCliente) {
        
        String correoCliente = autenticacionCliente.getCorreo();
        String contraseniaCliente = autenticacionCliente.getContrasenia();
        
        ClienteDTO cliente = clientesBO.iniciarSesion(correoCliente, contraseniaCliente);
        
        if(cliente == null){
            
            return ResponseUtils.construirResponseError(MENSAJE_NO_AUTORIZADO, Response.Status.UNAUTHORIZED);

        }
        
        Long idCliente = cliente.getId();
        
        String token = AutenticacionUtils.generarToken(idCliente);
        
        return ResponseUtils.construirResponseExito(MENSAJE_AUTORIZADO, token);
        
    }

    /**
     * PUT method for updating or creating an instance of AutenticacionResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
