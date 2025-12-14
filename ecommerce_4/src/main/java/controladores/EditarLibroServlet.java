
package controladores;

import definiciones.IAutoresBO;
import definiciones.ICategoriasBO;
import definiciones.IEditorialesBO;
import definiciones.ILibrosBO;
import dominio.AutorDTO;
import dominio.CategoriaDTO;
import dominio.EditorialDTO;
import dominio.LibroDTO;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
@WebServlet(name = "EditarLibroServlet", urlPatterns = {"/admin-editar-libro"})
public class EditarLibroServlet extends HttpServlet {

    private ILibrosBO librosBO;
    private ICategoriasBO categoriasBO;
    private IAutoresBO autoresBO;
    private IEditorialesBO editorialesBO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        this.librosBO = FabricaBO.obtenerLibrosBO();
        this.categoriasBO = FabricaBO.obtenerCategoriasBO();
        this.autoresBO = FabricaBO.obtenerAutoresBO();
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
            out.println("<title>Servlet EditarLibroServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarLibroServlet at " + request.getContextPath() + "</h1>");
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
        
        String idLibroString = request.getParameter("id");
        Long idLibroUrl = null;

        if (idLibroString != null && !idLibroString.isEmpty()) {
            try {
                idLibroUrl = Long.valueOf(idLibroString);
            } catch (NumberFormatException e) {
                idLibroUrl = null;
            }
        }

        LibroDTO libroEditar = null;

        // Se revisa la sesión para buscar si hay un libro pendiente y/o un error.
        HttpSession session = request.getSession();
        LibroDTO libroPendiente = (LibroDTO) session.getAttribute("libroPendienteActualizar");
        String errorSesion = (String) session.getAttribute("errorSesion");

        // Se determina si hay un libro pendiente de actualizar en la sesión que coincida con el id de la URL.
        if (idLibroUrl != null && libroPendiente != null && idLibroUrl.equals(libroPendiente.getId())) {

            // Si hay un libro pendiente y coincide con el id especificado en la URL, éste será el que se editará.
            libroEditar = libroPendiente;

            // Se elimina el libro pendiente en la sesión.
            session.removeAttribute("libroPendienteActualizar");

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
            
            // Si no hay un libro pendiente, se consulta en la base de datos.
            if (idLibroUrl != null) {
                try {
                    libroEditar = librosBO.consultarLibro(idLibroUrl);
                } catch (NegocioException ex) {
                    request.setAttribute("mensajeError", ex.getMessage());
                }
            }
        }
        
        
        List<AutorDTO> listaAutores = null;
        
        try {
            listaAutores = autoresBO.consultarAutores();
        } catch (NegocioException ex) {
            request.setAttribute("mensajeError", ex.getMessage());
        }
        
        List<CategoriaDTO> listaCategorias = null;
        
        try {
            listaCategorias = categoriasBO.consultarCategorias();
        } catch (NegocioException ex) {
            request.setAttribute("mensajeError", ex.getMessage());
        }
        
        
        List<EditorialDTO> listaEditoriales = null;
        
        try {
            listaEditoriales = editorialesBO.consultarEditoriales();
        } catch (NegocioException ex) {
            request.setAttribute("mensajeError", ex.getMessage());
        }

        request.setAttribute("listaAutores", listaAutores);
        request.setAttribute("listaEditoriales", listaEditoriales);
        request.setAttribute("listaCategorias", listaCategorias);
        request.setAttribute("libroEditar", libroEditar);
        
        request.getRequestDispatcher("admin-edicion-libro.jsp").forward(request, response);
        
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
