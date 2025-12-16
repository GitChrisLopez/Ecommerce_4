
package controladores;

import definiciones.ICategoriasBO;
import dominio.CategoriaDTO;
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
@WebServlet(name = "AgregarCategoriaServlet", urlPatterns = {"/admin-agregar-categoria"})
public class AgregarCategoriaServlet extends HttpServlet {

    private ICategoriasBO categoriasBO;

    @Override
    public void init() throws ServletException {
        super.init();

        this.categoriasBO = FabricaBO.obtenerCategoriasBO();
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
            out.println("<title>Servlet AgregarCategoriaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarCategoriaServlet at " + request.getContextPath() + "</h1>");
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
        
        // Se obtienen los datos enviados para agregar la categoría.
        String nombre = request.getParameter("nombre");

        try{

            // Se crea un dto con los datos de la categoría.
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setNombre(nombre);
            
            categoriasBO.registrarCategoria(categoriaDTO);

            // Como la actualización fue exitosa, se elimina cualquier categoría pendiente y error guardados
            // en la sesión.
            request.getSession().removeAttribute("categoriaPendienteAgregar");
            request.getSession().removeAttribute("errorSesion");
            request.getSession().removeAttribute("mensajeError");

            response.sendRedirect("admin-categorias-registradas");

        } catch (Exception e) {

            // Se guardan los datos ingresados por el usuario.
            CategoriaDTO categoriaPendienteAgregar = new CategoriaDTO();

            // Se obtiene el nombre de la categoría.
            categoriaPendienteAgregar.setNombre(request.getParameter("nombre"));

            // Se guarda la categoría pendiente en la sesión y el mensaje de error.
            HttpSession session = request.getSession();
            session.setAttribute("categoriaPendienteAgregar", categoriaPendienteAgregar);
            session.setAttribute("errorSesion", e.getMessage());

            response.sendRedirect("admin-agregar-categoria?id=");
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
