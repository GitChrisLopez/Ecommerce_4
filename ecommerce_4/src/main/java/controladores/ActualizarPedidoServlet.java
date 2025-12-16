package controladores;

import definiciones.IPedidoBO;
import dominio.enumeradores.EstadoDTO;
import excepciones.NegocioException;
import fabrica.FabricaBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet para actualizar un pedido.
 *
 * @author norma
 */
@WebServlet(name = "ActualizarPedidoServlet", urlPatterns = {"/admin-actualizar-pedido"})
public class ActualizarPedidoServlet extends HttpServlet {

    private IPedidoBO pedidoBO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        this.pedidoBO = FabricaBO.obtenerPedidosBO();   
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActualizarPedidoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarPedidoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/admin-mostrar-pedidos");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Long idPedido = null;
        EstadoDTO nuevoEstado = null;
        String mensaje = null;
        String redireccionURL = request.getContextPath() + "/admin-mostrar-pedidos";

        try {
            String parametro = request.getParameter("id");
            String accion = request.getParameter("accion");

            if (parametro == null || accion == null || parametro.isEmpty()) {
                throw new IllegalArgumentException("Datos incompletos.");
            }

            idPedido = Long.valueOf(parametro);

            redireccionURL = request.getContextPath() + "/admin-detalles-pedido?id=" + idPedido;

            if ("cancelar".equalsIgnoreCase(accion)) {
                nuevoEstado = EstadoDTO.CANCELADO;
                redireccionURL = request.getContextPath() + "/admin-mostrar-pedidos";

            } else if ("actualizar".equalsIgnoreCase(accion)) {
                String nuevoEstadoStr = request.getParameter("nuevoEstado");

                if (nuevoEstadoStr == null || nuevoEstadoStr.isEmpty()) {
                    throw new IllegalArgumentException("El nuevo estado no fue especificado.");
                }

                nuevoEstado = EstadoDTO.valueOf(nuevoEstadoStr.toUpperCase());

            } else {
                throw new NegocioException("Acción de actualización de estado no válida.");
            }

            pedidoBO.actualizarPedido(idPedido, nuevoEstado);
            request.getSession().setAttribute("mensajeEstado", "Estado actualizado con éxito a " + nuevoEstado.toString() + ".");

        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error al actualizar el estado: " + e.getMessage();
            request.getSession().setAttribute("mensajeError", mensaje);
            if (idPedido != null) {
                redireccionURL = request.getContextPath() + "/admin-detalles-pedido?id=" + idPedido;
            } else {
                redireccionURL = request.getContextPath() + "/admin-mostrar-pedidos";
            }
        }
        response.sendRedirect(redireccionURL);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
