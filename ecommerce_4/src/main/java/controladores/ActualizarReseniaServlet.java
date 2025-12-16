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
 * Servlet para actualizar una resenia.
 * @author norma
 */
@WebServlet(name = "ActualizarReseniaServlet", urlPatterns = {"/admin-actualizar-resenia"})
public class ActualizarReseniaServlet extends HttpServlet {

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
            out.println("<title>Servlet ActualizarReseniaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarReseniaServlet at " + request.getContextPath() + "</h1>");
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

        Long idResenia = null;
        String mensaje;

        try {
            String parametro = request.getParameter("id");
            String nuevoComentario = request.getParameter("comentario");

            if (parametro == null || nuevoComentario == null || parametro.isEmpty()) {
                throw new IllegalArgumentException("Datos incompletos.");
            }

            idResenia = Long.valueOf(parametro);

            reseniaBO.actualizarComentarioResenia(idResenia, nuevoComentario);

            request.getSession().setAttribute("mensajeEstado", "Comentario actualizado con éxito.");
            response.sendRedirect(request.getContextPath() + "/admin-mostrar-resenias");

        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "Error al actualizar la reseña: " + e.getMessage();
            request.getSession().setAttribute("mensajeEstado", mensaje);

            response.sendRedirect(request.getContextPath() + "/admin-mostrar-resenias");
        }
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
