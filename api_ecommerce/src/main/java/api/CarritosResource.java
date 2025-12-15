
package api;

import definiciones.ICarritosBO;
import dominio.restful.CarritoResponseDTO;
import dominio.restful.ProductoCarritoActualizarDTO;
import excepciones.NegocioException;
import fabrica.FabricaBO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import utils.ResponseUtils;

/**
 * REST Web Service
 *
 * @author Romo López Manuel ID: 00000253080
 */
@Path("carritos")
@RequestScoped
public class CarritosResource {

    private ICarritosBO carritosBO;

    /**
     * Creates a new instance of CarritosResource
     */
    public CarritosResource() {
        
        carritosBO = FabricaBO.obtenerCarritosBO();
        
    }
    
    /**
     * Retrieves representation of an instance of api.CarritosResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CarritoResponseDTO getCarrito() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    
    
    
}
