
package controladores;

import definiciones.IAutoresBO;
import dominio.AutorDTO;
import excepciones.NegocioException;
import fabrica.FabricaBO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 */
@WebServlet(name = "EditarAutorServlet", urlPatterns = {"/admin-editar-autor"})
public class EditarAutorServlet extends HttpServlet {

    private IAutoresBO autoresBO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        this.autoresBO = FabricaBO.obtenerAutoresBO();
        
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
            out.println("<title>Servlet EditarAutorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarAutorServlet at " + request.getContextPath() + "</h1>");
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
        
        String idAutorString = request.getParameter("id");
        Long idAutorUrl = null;

        if (idAutorString != null && !idAutorString.isEmpty()) {
            try {
                idAutorUrl = Long.valueOf(idAutorString);
            } catch (NumberFormatException e) {
                idAutorUrl = null;
            }
        }

        AutorDTO autorEditar = null;

        // Se revisa la sesión para buscar si hay un autor pendiente y/o un error.
        HttpSession session = request.getSession();
        AutorDTO autorPendiente = (AutorDTO) session.getAttribute("autorPendienteActualizar");
        String errorSesion = (String) session.getAttribute("errorSesion");

        // Se determina si hay un autor pendiente de actualizar en la sesión que coincida con el id de la URL.
        if (idAutorUrl != null && autorPendiente != null && idAutorUrl.equals(autorPendiente.getId())) {

            // Si hay un autor pendiente y coincide con el id especificado en la URL, éste será el que se editará.
            autorEditar = autorPendiente;

            // Se elimina el autor pendiente en la sesión.
            session.removeAttribute("autorPendienteActualizar");

            if (errorSesion != null) {
                // Se coloca el mensaje de error en la solicitud para la página destino.
                request.setAttribute("mensajeError", errorSesion);

                // Se elimina para evitar que salga al refrescar la página.
                session.removeAttribute("errorSesion");
            }
        } else {
            
            // Se limpia el error de sesión, si había.
            if(errorSesion != null){
                session.removeAttribute("errorSesion");
            }
            
            // Si no hay un autor pendiente, se consulta en la base de datos.
            if (idAutorUrl != null) {
                try {
                    autorEditar = autoresBO.consultarAutor(idAutorUrl);
                } catch (NegocioException ex) {
                    request.setAttribute("mensajeError", ex.getMessage());
                }
            }
        }

        request.setAttribute("autorEditar", autorEditar);
        
        request.getRequestDispatcher("admin-edicion-autor.jsp").forward(request, response);
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
