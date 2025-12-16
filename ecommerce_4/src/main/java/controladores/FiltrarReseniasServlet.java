package controladores;

import definiciones.IReseniaBO;
import dominio.ReseniaDTO;
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
 * Servlet para filtrar resenias en relación a su libro vinculado.
 * @author norma
 */
@WebServlet(name = "FiltrarReseniasServlet", urlPatterns = {"/admin-filtrar-resenias"})
public class FiltrarReseniasServlet extends HttpServlet {

    private IReseniaBO reseniaBO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        this.reseniaBO = FabricaBO.obtenerReseniasBO();
        
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
            out.println("<title>Servlet FiltrarReseniasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FiltrarReseniasServlet at " + request.getContextPath() + "</h1>");
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
        List<ReseniaDTO> listaResenias = null;

        try {
            listaResenias = reseniaBO.obtenerReseniasFiltradasPorLibro(busqueda);

            request.setAttribute("listaResenias", listaResenias);

            request.getRequestDispatcher("/admin-historial-resenias.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorCarga", "Error al buscar reseñas: " + e.getMessage());
            request.getRequestDispatcher("/admin-historial-resenias.jsp").forward(request, response);
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
