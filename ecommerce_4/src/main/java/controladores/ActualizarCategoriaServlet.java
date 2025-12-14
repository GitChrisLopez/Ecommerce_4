
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
@WebServlet(name = "ActualizarCategoriaServlet", urlPatterns = {"/admin-actualizar-categoria"})
public class ActualizarCategoriaServlet extends HttpServlet {

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
            out.println("<title>Servlet ActualizarCategoriaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarCategoriaServlet at " + request.getContextPath() + "</h1>");
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
        String idCategoriaStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");

        try{
            
            // Se obtiene el Id de la categoría.
            Long idCategoria = null;
            if(idCategoriaStr != null && !idCategoriaStr.isEmpty()){
                idCategoria = Long.valueOf(idCategoriaStr);
            }

            // Se crea un dto con los nuevos datos de la categoría.
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setId(idCategoria);
            categoriaDTO.setNombre(nombre);
            
            categoriasBO.actualizarCategoria(categoriaDTO);

            // Como la actualización fue exitosa, se elimina cualquier categoría pendiente y error guardados
            // en la sesión.
            request.getSession().removeAttribute("categoriaPendienteActualizar");
            request.getSession().removeAttribute("errorSesion");
            request.getSession().removeAttribute("mensajeError");

            response.sendRedirect("admin-categorias-registradas");

        } catch (Exception e) {

            // Se guardan los datos ingresados por el usuario.
            CategoriaDTO categoriaPendienteActualizar = new CategoriaDTO();
            try {

                // Se obtiene el Id de la categoría.
                categoriaPendienteActualizar.setId(Long.valueOf(idCategoriaStr));

                // Se obtiene el nombre de la categoría.
                categoriaPendienteActualizar.setNombre(request.getParameter("nombre"));

            } catch (Exception ex) {
                // Si falla la recuperación de algún dato, no se considera.
            }

            // Se guarda la categoría pendiente en la sesión y el mensaje de error.
            HttpSession session = request.getSession();
            session.setAttribute("categoriaPendienteActualizar", categoriaPendienteActualizar);
            session.setAttribute("errorSesion", e.getMessage());

            response.sendRedirect("admin-edicion-categoria?id=" + idCategoriaStr);
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
