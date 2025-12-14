
package controladores;

import definiciones.IAutoresBO;
import dominio.AutorDTO;
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
@WebServlet(name = "AgregarAutorServlet", urlPatterns = {"/admin-agregar-autor"})
public class AgregarAutorServlet extends HttpServlet {

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
            out.println("<title>Servlet AgregarAutorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarAutorServlet at " + request.getContextPath() + "</h1>");
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
        
        // Se obtienen los datos enviados para agregar el autor.
        String nombre = request.getParameter("nombre");
        String apellidoPaterno = request.getParameter("apellido-paterno");
        String apellidoMaterno = request.getParameter("apellido-materno");
        

        try{

            // Se crea un dto con los datos del autor.
            AutorDTO autorDTO = new AutorDTO();
            autorDTO.setNombre(nombre);
            autorDTO.setApellidoPaterno(apellidoPaterno);
            autorDTO.setApellidoMaterno(apellidoMaterno);
            
            autoresBO.registrarAutor(autorDTO);

            // Como la actualización fue exitosa, se elimina cualquier autor pendiente y error guardados
            // en la sesión.
            request.getSession().removeAttribute("autorPendienteAgregar");
            request.getSession().removeAttribute("errorSesion");
            request.getSession().removeAttribute("mensajeError");

            response.sendRedirect("admin-autores-registrados");

        } catch (Exception e) {

            // Se guardan los datos ingresados por el usuario.
            AutorDTO autorPendienteAgregar = new AutorDTO();

            // Se obtiene el nombre del autor.
            autorPendienteAgregar.setNombre(request.getParameter("nombre"));
            
            // Se guarda el apellido paterno del autor.
            autorPendienteAgregar.setApellidoPaterno(request.getParameter("apellido-paterno"));
            
            // Se guarda el apellido materno del autor.
            autorPendienteAgregar.setApellidoMaterno(request.getParameter("apellido-materno"));

            // Se guarda el autor pendiente en la sesión y el mensaje de error.
            HttpSession session = request.getSession();
            session.setAttribute("autorPendienteAgregar", autorPendienteAgregar);
            session.setAttribute("errorSesion", e.getMessage());

            response.sendRedirect("admin-agregar-autor?id=");
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
