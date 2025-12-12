/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import BOs.PedidoBO;
import definiciones.IPedidoBO;
import dominio.PedidoDTO;
import dominio.UsuarioDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import fabrica.FabricaBO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/**
 * REST Web Service
 *
 * @author norma
 */
@Path("pedidos")
@RequestScoped
public class PedidosResource {

    @Context
    private HttpServletRequest httpRequest;

    private IPedidoBO pedidoBO;

    /**
     * Creates a new instance of PedidosResource
     */
    public PedidosResource() {
        this.pedidoBO = FabricaBO.obtenerPedidosBO();
    }

    /**
     * Obtiene todos los pedidos del cliente autenticado.
     * @return Lista de pedidos del cliente
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidosCliente() {
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute("usuarioLogueado");
        Long idCliente = usuarioLogueado.getId();

        try {
            List<PedidoDTO> pedidos = pedidoBO.obtenerPedidosPorCliente(idCliente);
            return Response.ok(pedidos).build();

        } catch (NegocioException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (PersistenciaException ex) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (Exception ex) {;
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtiene un pedido específico por su ID.
     * @param id ID del pedido
     * @return Pedido específico
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidoClientePorId(@PathParam("id") Long id) {
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute("usuarioLogueado");
        Long idCliente = usuarioLogueado.getId();

        try {
            PedidoDTO pedido = pedidoBO.obtenerPedidoPorId(id);
            return Response.ok(pedido).build();

        } catch (NegocioException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (PersistenciaException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
