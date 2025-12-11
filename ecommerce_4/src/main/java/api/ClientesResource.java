/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package api;

import BOs.ClienteBO;
import definiciones.IClienteBO;
import definiciones.IClienteDAO;
import dominio.ClienteDTO;
import dominio.UsuarioDTO;
import dtos.ClienteResponseDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import fabrica.FabricaBO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.Response;
import mappers.MapperCliente;

/**
 * REST Web Service
 *
 * @author norma
 */
@Path("clientes")
@RequestScoped
public class ClientesResource {

    @Context
    private HttpServletRequest httpRequest;

    private IClienteBO clienteBO;

    /**
     * Creates a new instance of ClientesResource
     */
    public ClientesResource() {
        this.clienteBO = FabricaBO.obtenerClientesBO();
    }

    /**
     * Obtiene el perfil del usuario.
     * @return Response
     */
    @GET
    @Path("perfil")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerfil() {

        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\": \"Debe iniciar sesión para ver su perfil.\"}")
                    .build();
        }

        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute("usuarioLogueado");
        
        Long idCliente = usuarioLogueado.getId();

        try {
            ClienteDTO cliente = clienteBO.obtenerClientePorId(idCliente);

            ClienteResponseDTO perfil = MapperCliente.toResponseDTO(cliente);

            return Response.ok(perfil).build();

        } catch (NegocioException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (PersistenciaException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Edita el perfil del usuario.
     * @param clienteEditado Cliente editado
     * @return Response
     */
    @PUT
    @Path("perfil")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPerfil(ClienteResponseDTO clienteEditado) {

        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("usuarioLogueado") == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute("usuarioLogueado");
        
        Long idCliente = usuarioLogueado.getId();

        try {
            ClienteDTO clienteEditar = MapperCliente.toClienteDTO(clienteEditado);

            clienteEditar.setId(idCliente);

            clienteBO.editarCliente(clienteEditar);

            return Response.ok("{\"mensaje\": \"Perfil actualizado con éxito.\"}").build();

        } catch (NegocioException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + ex.getMessage() + "\"}")
                    .build();

        } catch (PersistenciaException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"Error al actualizar el perfil.\"}")
                    .build();
        }
    }
}
