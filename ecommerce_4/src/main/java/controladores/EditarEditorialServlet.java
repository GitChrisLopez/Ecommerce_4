
package controladores;

import definiciones.IEditorialesBO;
import dominio.EditorialDTO;
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
 * @author Romo López Manuel ID: 00000253080
 */
@WebServlet(name = "EditarEditorialServlet", urlPatterns = {"/admin-editar-editorial"})
public class EditarEditorialServlet extends HttpServlet {

    private IEditorialesBO editorialesBO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        this.editorialesBO = FabricaBO.obtenerEditorialesBO();
        
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
            out.println("<title>Servlet EditarEditorialServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarEditorialServlet at " + request.getContextPath() + "</h1>");
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
        
        String idEditorialString = request.getParameter("id");
        Long idEditorialUrl = null;

        if (idEditorialString != null && !idEditorialString.isEmpty()) {
            try {
                idEditorialUrl = Long.valueOf(idEditorialString);
            } catch (NumberFormatException e) {
                idEditorialUrl = null;
            }
        }

        EditorialDTO editorialEditar = null;

        // Se revisa la sesión para buscar si hay una editorial pendiente y/o un error.
        HttpSession session = request.getSession();
        EditorialDTO editorialPendiente = (EditorialDTO) session.getAttribute("editorialPendienteActualizar");
        String errorSesion = (String) session.getAttribute("errorSesion");

        // Se determina si hay una editorial pendiente de actualizar en la sesión que coincida con el id de la URL.
        if (idEditorialUrl != null && editorialPendiente != null && idEditorialUrl.equals(editorialPendiente.getId())) {

            // Si hay una editorial pendiente y coincide con el id especificado en la URL, ésta será la que se editará.
            editorialEditar = editorialPendiente;

            // Se elimina la editorial pendiente en la sesión.
            session.removeAttribute("editorialPendienteActualizar");

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
            
            // Si no hay una editorial pendiente, se consulta en la base de datos.
            if (idEditorialUrl != null) {
                try {
                    // Se asume que tienes un IEditorialesBO y el método consultarEditorial(Long id)
                    editorialEditar = editorialesBO.consultarEditorial(idEditorialUrl);
                } catch (NegocioException ex) {
                    request.setAttribute("mensajeError", ex.getMessage());
                }
            }
        }

        request.setAttribute("editorialEditar", editorialEditar);
        
        request.getRequestDispatcher("admin-edicion-editorial.jsp").forward(request, response);
        
        
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
