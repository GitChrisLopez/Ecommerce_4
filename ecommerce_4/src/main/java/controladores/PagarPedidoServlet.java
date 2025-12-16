/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores;

import definiciones.IPedidoBO;
import dominio.PedidoDTO;
import excepciones.NegocioException;
import fabrica.FabricaBO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Adrián
 */
@WebServlet(name = "PagarPedidoServlet", urlPatterns = {"/pago-de-pedido"})
public class PagarPedidoServlet extends HttpServlet {

    private IPedidoBO pedidoBO;

    @Override
    public void init() throws ServletException {
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
            out.println("<title>Servlet PagarPedidoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PagarPedidoServlet at " + request.getContextPath() + "</h1>");
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

        /**
         * String parametro = request.getParameter("id"); if (parametro == null
         * || parametro.isEmpty()) { request.setAttribute("errorCarga", "Error:
         * No se proporcionó el ID del pedido a editar.");
         * request.getRequestDispatcher("/admin-mostrar-pedidos").forward(request,
         * response); return; }*
         */
        response.sendRedirect(request.getContextPath() + "/index.jsp");

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
        String idPedidoStr = request.getParameter("id");
        String metodo = request.getParameter("metodoPago"); // Campo oculto que deberías agregar en tus formularios

        try {
            if (idPedidoStr == null || idPedidoStr.isEmpty()) {
                throw new NegocioException("ID de pedido no proporcionado.");
            }

            Long idPedido = Long.parseLong(idPedidoStr);

            // Ejemplo de lógica para Tarjeta
            if ("tarjeta".equalsIgnoreCase(metodo)) {
                String titular = request.getParameter("titular");
                String numeroStr = request.getParameter("num-tarjeta");
                String fechaStr = request.getParameter("fecha-vencimiento"); // Asumiendo formato compatible
                String cvv = request.getParameter("cvv");

                // Aquí deberías crear el objeto PagoTarjetaDTO y llamar a un método en tu BO
                // Nota: Tu PedidoBO actual solo tiene actualizarEstado, necesitarás agregar lógica
                // para guardar el método de pago en tu PedidoBO y DAO.
                // Simulación de éxito:
                response.sendRedirect(request.getContextPath() + "/pedido-realizado-exitosamente.jsp");
            } else {
                // Otros métodos de pago
                response.sendRedirect(request.getContextPath() + "/pedido-realizado-exitosamente.jsp");
            }

        } catch (NegocioException | NumberFormatException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error-pago-pedido.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error procesando el pago");
        }
    }

}
