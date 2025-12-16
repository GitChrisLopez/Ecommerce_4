package controladores;

import definiciones.IReseniaBO;
import fabrica.FabricaBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet para eliminar una resenia.
 * @author norma
 */
@WebServlet(name = "EliminarReseniaServlet", urlPatterns = {"/admin-eliminar-resenia"})
public class EliminarReseniaServlet extends HttpServlet {

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
            out.println("<title>Servlet EliminarReseniaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EliminarReseniaServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect(request.getContextPath() + "/admin-mostrar-resenias");
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

        String mensaje;
        String parametro = request.getParameter("id");
        Long idResenia = null;

        try {
            idResenia = Long.valueOf(parametro);

            boolean exito = reseniaBO.eliminarResenia(idResenia);

            if (exito) {
                mensaje = "La reseña ID " + idResenia + " fue eliminada con éxito.";
            } else {
                mensaje = "Error: No se pudo eliminar la reseña ID " + idResenia + ". Podría no existir.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error interno del sistema al intentar eliminar la reseña.";
        }

        request.getSession().setAttribute("mensajeEstado", mensaje);

        response.sendRedirect(request.getContextPath() + "/admin-mostrar-resenias");
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
