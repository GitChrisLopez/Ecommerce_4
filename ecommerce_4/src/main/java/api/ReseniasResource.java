package api;

import definiciones.IProductosBO;
import definiciones.IReseniaBO;
import dominio.ClienteDTO;
import dominio.LibroDTO;
import dominio.ProductoDTO;
import dominio.ReseniaDTO;
import dominio.UsuarioDTO;
import excepciones.NegocioException;
import fabrica.FabricaBO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

/**
 * REST Web Service para reseñas
 *
 * @author norma
 */
@Path("resenias")
@RequestScoped
public class ReseniasResource {

    @Context
    private HttpServletRequest httpRequest;

    private IReseniaBO reseniaBO;
    private IProductosBO productosBO;

    /**
     * Creates a new instance of ReseniasResource
     */
    public ReseniasResource() {
        this.reseniaBO = FabricaBO.obtenerReseniasBO();
        this.productosBO = FabricaBO.obtenerProductosBO();
    }

    /**
     * Obtiene las reseñas del libro asociado a un producto.
     *
     * @param idProducto ID del producto
     * @return Lista de reseñas del libro
     */
    @GET
    @Path("producto/{idProducto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReseniasPorProducto(@PathParam("idProducto") Long idProducto) {
        try {
            ProductoDTO producto = productosBO.consultarProducto(idProducto);

            List<ReseniaDTO> resenias = reseniaBO.obtenerReseniasPorIdLibro(producto.getLibro().getId());

            return Response.ok(resenias).build();

        } catch (NegocioException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Crea una nueva reseña.
     *
     * @param idProducto ID del producto
     * @param resenia Reseña a crear.
     * @return Reseña creada
     */
    @POST
    @Path("producto/{idProducto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearResenia(@PathParam("idProducto") Long idProducto, ReseniaDTO resenia) {
        HttpSession session = httpRequest.getSession(false);

        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute("usuarioLogueado");

        try {
            ProductoDTO producto = productosBO.consultarProducto(idProducto);

            ClienteDTO cliente = (ClienteDTO) usuarioLogueado;
            ClienteDTO clienteResenia = new ClienteDTO();
            clienteResenia.setId(cliente.getId());

            LibroDTO libro = producto.getLibro();
            LibroDTO libroResenia = new LibroDTO();
            libroResenia.setId(libro.getId());

            ReseniaDTO reseniaNueva = new ReseniaDTO();
            reseniaNueva.setLibro(libroResenia);
            reseniaNueva.setFormato(resenia.getFormato());
            reseniaNueva.setCalificacion(resenia.getCalificacion());
            reseniaNueva.setComentario(resenia.getComentario() != null ? resenia.getComentario().trim() : "");
            reseniaNueva.setCliente(clienteResenia);
            reseniaNueva.setFecha(LocalDateTime.now());

            ReseniaDTO reseniaCreada = reseniaBO.crearResenia(reseniaNueva);

            return Response.ok(reseniaCreada).build();

        } catch (NegocioException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
