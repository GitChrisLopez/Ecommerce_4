
package api;

import definiciones.ICarritosBO;
import dominio.restful.ProductoCarritoActualizarDTO;
import dominio.restful.ProductoEliminarCarritoDTO;
import excepciones.NegocioException;
import fabrica.FabricaBO;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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

    private final String MENSAJE_PRODUCTO_AGREGADO = "Producto agregado con éxito";
    private final String MENSAJE_ERROR_AGREGAR_PRODUCTO = "Ha ocurrido un error al agregar el producto.";
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
            @Context HttpHeaders headers,
            ProductoCarritoActualizarDTO productoActualizar){
        
        Long idProductoActualizar = productoActualizar.getIdProducto();
        int cantidadActualizar = productoActualizar.getCantidad(); 
        Long idCliente = obtenerIdUsuarioToken(headers.getCookies().get(NOMBRE_HEADER_TOKEN));
        
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
            
            Response respuestaError = ResponseUtils.construirResponseError(
                    MENSAJE_ERROR_AGREGAR_PRODUCTO, 
                    Response.Status.BAD_REQUEST);

            return respuestaError;
            
        }
          
    }
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarProductoCarrito(
            @Context HttpHeaders headers,
            ProductoEliminarCarritoDTO productoEliminar) {

        Long idProductoEliminar = productoEliminar.getIdProducto();
        Long idCliente = obtenerIdUsuarioToken(headers.getCookies().get(NOMBRE_HEADER_TOKEN));
        
        if(idCliente == null){
            
            Response respuestaNoAutorizado = ResponseUtils.construirResponseError(
                    MENSAJE_NO_AUTORIZADO, 
                    Response.Status.UNAUTHORIZED);
            
            return respuestaNoAutorizado;
        }
        
        try {
            
            carritosBO.eliminarProductoCarrito(idCliente, idProductoEliminar);
            Response respuestaExito = ResponseUtils.construirResponseExito(
                    MENSAJE_PRODUCTO_AGREGADO, 
                    null);

            return respuestaExito;
            
            
        } catch (NegocioException ex) {
            
            Response respuestaError = ResponseUtils.construirResponseError(
                    MENSAJE_ERROR_AGREGAR_PRODUCTO, 
                    Response.Status.BAD_REQUEST);

            return respuestaError;
            
        }
        
    }
    
    
    private Long obtenerIdUsuarioToken(Cookie jwtTokenCookie){
        
        if (jwtTokenCookie == null) {
            
            return null;
        }
        
        String token = jwtTokenCookie.getValue();
        
        return AutenticacionUtils.extraerIdUsuario(token);
    }
}
