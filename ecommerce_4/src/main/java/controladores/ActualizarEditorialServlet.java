
package controladores;

import definiciones.IEditorialesBO;
import dominio.EditorialDTO;
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
@WebServlet(name = "ActualizarEditorial", urlPatterns = {"/admin-actualizar-editorial"})
public class ActualizarEditorialServlet extends HttpServlet {

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
            out.println("<title>Servlet ActualizarEditorial</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarEditorial at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        
        // Se obtienen los datos enviados para la actualización.
        String idEditorialStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");

        try {
            
            // Se obtiene el Id de la editorial.
            Long idEditorial = null;
            if(idEditorialStr != null && !idEditorialStr.isEmpty()){
                idEditorial = Long.valueOf(idEditorialStr);
            }

            // Se crea un dto con los nuevos datos de la editorial.
            EditorialDTO editorialDTO = new EditorialDTO();
            editorialDTO.setId(idEditorial);
            editorialDTO.setNombre(nombre);
            
            editorialesBO.actualizarEditorial(editorialDTO);

            // Como la actualización fue exitosa, se elimina cualquier editorial pendiente y error guardados
            // en la sesión.
            request.getSession().removeAttribute("editorialPendienteActualizar");
            request.getSession().removeAttribute("errorSesion");
            request.getSession().removeAttribute("mensajeError");

            response.sendRedirect("admin-editoriales-registradas");

        } catch (Exception e) {

            // Se guardan los datos ingresados por el usuario.
            EditorialDTO editorialPendienteActualizar = new EditorialDTO();
            try {

                // Se obtiene el Id de la editoral.
                editorialPendienteActualizar.setId(Long.valueOf(idEditorialStr));

                // Se obtiene el nombre de la editorial.
                editorialPendienteActualizar.setNombre(request.getParameter("nombre"));

            } catch (Exception ex) {
                // Si falla la recuperación de algún dato, no se considera.
            }

            // Se guarda la editorial pendiente en la sesión y el mensaje de error.
            HttpSession session = request.getSession();
            session.setAttribute("editorialPendienteActualizar", editorialPendienteActualizar);
            session.setAttribute("errorSesion", e.getMessage());

            response.sendRedirect("admin-edicion-editorial?id=" + idEditorialStr);
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
