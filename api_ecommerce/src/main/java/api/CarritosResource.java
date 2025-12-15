
package api;

import definiciones.ICarritosBO;
import dominio.CarritoDTO;
import excepciones.NegocioException;
import fabrica.FabricaBO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import utils.ResponseUtils;

/**
 * REST Web Service
 *
 * @author Romo López Manuel ID: 00000253080
 */
@Path("carritos")
@RequestScoped
public class CarritosResource {

    private final String MENSAJE_CLIENTE_SIN_CARRITO = "No se encontró un carrito activo para el cliente con ID: ";
    private final String MENSAJE_CONSULTA_CARRITO_EXITOSA = "Carrito consultado exitosamente.";
    private final String MENSAJE_ERROR_CONSULTA_CARRITO = "Ha ocurrido un error en la consulta del carrito.";
    
    private ICarritosBO carritosBO;

    /**
     * Creates a new instance of CarritosResource
     */
    public CarritosResource() {
        
        carritosBO = FabricaBO.obtenerCarritosBO();
        
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarrito(@QueryParam("idCliente") Long idCliente) { 
        
        try {
            
            CarritoDTO carritoEncontrado = carritosBO.consultarCarrito(idCliente); 
            
            if (carritoEncontrado == null) {
                return ResponseUtils.construirResponseError( MENSAJE_CLIENTE_SIN_CARRITO, Status.NOT_FOUND);
            }

            return ResponseUtils.construirResponseExito(MENSAJE_CONSULTA_CARRITO_EXITOSA, carritoEncontrado);
            
        } catch (NegocioException ex) {
            
            return ResponseUtils.construirResponseError( MENSAJE_ERROR_CONSULTA_CARRITO, Status.INTERNAL_SERVER_ERROR);
            
        }
        
    }
    
}
