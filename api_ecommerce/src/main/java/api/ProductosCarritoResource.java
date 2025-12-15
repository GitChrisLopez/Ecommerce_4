
package api;

import definiciones.ICarritosBO;
import dominio.restful.ProductoCarritoActualizarDTO;
import dominio.restful.ProductoEliminarCarritoDTO;
import excepciones.NegocioException;
import fabrica.FabricaBO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.AutenticacionUtils;
import utils.ResponseUtils;

/**
 * REST Web Service
 *
 * @author Romo López Manuel ID: 00000253080
 */
@Path("productos-carrito")
@RequestScoped
public class ProductosCarritoResource {

    private static final Logger LOG = Logger.getLogger(ProductosCarritoResource.class.getName());

    private final String MENSAJE_PRODUCTO_AGREGADO = "Producto agregado con éxito";
    private final String MENSAJE_PRODUCTO_ELIMINADO = "Producto elimnado con éxito";
    private final String MENSAJE_ERROR_AGREGAR_PRODUCTO = "Ha ocurrido un error al agregar el producto.";
    private final String MENSAJE_ERROR_ELIMINAR_PRODUCTO = "Ha ocurrido un error al eliminar el producto.";
    private final String MENSAJE_NO_AUTORIZADO = "No autorizado";
    
    private final String NOMBRE_HEADER_TOKEN = "jwtToken";
    
    private ICarritosBO carritosBO;
    
    public ProductosCarritoResource() {
        
        carritosBO = FabricaBO.obtenerCarritosBO();
        
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarProductoCarrito( 
            @CookieParam(NOMBRE_HEADER_TOKEN) String token,
            ProductoCarritoActualizarDTO productoActualizar){
        
        Long idProductoActualizar = productoActualizar.getIdProducto();
        int cantidadActualizar = productoActualizar.getCantidad(); 
        Long idCliente = AutenticacionUtils.extraerIdUsuario(token);
        
        System.out.println(productoActualizar.getIdProducto());
        System.out.println(productoActualizar.getCantidad());
        
        if(idCliente == null){
            
            Response respuestaNoAutorizado = ResponseUtils.construirResponseError(
                    MENSAJE_NO_AUTORIZADO, 
                    Response.Status.UNAUTHORIZED);
            
            return respuestaNoAutorizado;
        }
        
        try {
            
            carritosBO.actualizarProductoCarrito(idCliente, idProductoActualizar, cantidadActualizar);
            Response respuestaExito = ResponseUtils.construirResponseExito(
                    MENSAJE_PRODUCTO_AGREGADO, 
                    null);

            return respuestaExito;
            
            
        } catch (NegocioException ex) {
            
            LOG.log(Level.SEVERE, ex.getMessage());
            Response respuestaError = ResponseUtils.construirResponseError(
                    MENSAJE_ERROR_AGREGAR_PRODUCTO, 
                    Response.Status.BAD_REQUEST);

            return respuestaError;
            
        }
          
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProductoCarrito(
            @CookieParam(NOMBRE_HEADER_TOKEN) String token,
            ProductoEliminarCarritoDTO productoEliminar) {

        Long idProductoEliminar = productoEliminar.getIdProducto();
        Long idCliente = AutenticacionUtils.extraerIdUsuario(token);
        
        if(idCliente == null){
            
            Response respuestaNoAutorizado = ResponseUtils.construirResponseError(
                    MENSAJE_NO_AUTORIZADO, 
                    Response.Status.UNAUTHORIZED);
            
            return respuestaNoAutorizado;
        }
        
        try {
            
            carritosBO.eliminarProductoCarrito(idCliente, idProductoEliminar);
            Response respuestaExito = ResponseUtils.construirResponseExito(
                    MENSAJE_PRODUCTO_ELIMINADO, 
                    null);

            return respuestaExito;
            
            
        } catch (NegocioException ex) {
            
            LOG.log(Level.SEVERE, ex.getMessage());
            Response respuestaError = ResponseUtils.construirResponseError(
                    MENSAJE_ERROR_ELIMINAR_PRODUCTO, 
                    Response.Status.BAD_REQUEST);

            return respuestaError;
            
        }
        
    }

}
