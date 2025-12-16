package controladores;

import definiciones.IPedidoBO;
import dominio.PedidoDTO;
import fabrica.FabricaBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet para filtrar pedidos.
 * @author norma
 */
@WebServlet(name = "FiltrarPedidosServlet", urlPatterns = {"/admin-filtrar-pedidos"})
public class FiltrarPedidosServlet extends HttpServlet {

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
            out.println("<title>Servlet FiltrarPedidosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FiltrarPedidosServlet at " + request.getContextPath() + "</h1>");
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
       
        String busqueda = request.getParameter("busqueda");
        List<PedidoDTO> listaPedidos = null;

        try {
            listaPedidos = pedidoBO.obtenerPedidosFiltradosPorNumero(busqueda);

            request.setAttribute("listaPedidos", listaPedidos);

            request.getRequestDispatcher("/admin-historial-pedidos.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorCarga", "Error al buscar pedidos: " + e.getMessage());
            request.getRequestDispatcher("/admin-historial-pedidos.jsp").forward(request, response);
        }
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
        processRequest(request, response);
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
