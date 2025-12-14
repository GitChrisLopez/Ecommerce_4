
package controladores;

import definiciones.ICategoriasBO;
import dominio.CategoriaDTO;
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
@WebServlet(name = "EditarCategoriasServlet", urlPatterns = {"/admin-editar-categoria"})
public class EditarCategoriasServlet extends HttpServlet {

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
            out.println("<title>Servlet EditarCategoriasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarCategoriasServlet at " + request.getContextPath() + "</h1>");
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
        
        
        String idCategoriaString = request.getParameter("id");
        Long idCategoriaUrl = null;

        if (idCategoriaString != null && !idCategoriaString.isEmpty()) {
            try {
                idCategoriaUrl = Long.valueOf(idCategoriaString);
            } catch (NumberFormatException e) {
                idCategoriaUrl = null;
            }
        }

        CategoriaDTO categoriaEditar = null;

        // Se revisa la sesión para buscar si hay una categoría pendiente y/o un error.
        HttpSession session = request.getSession();
        CategoriaDTO categoriaPendiente = (CategoriaDTO) session.getAttribute("categoriaPendienteActualizar");
        String errorSesion = (String) session.getAttribute("errorSesion");

        // Se determina si hay una categoría pendiente de actualizar en la sesión que coincida con el id de la URL.
        if (idCategoriaUrl != null && categoriaPendiente != null && idCategoriaUrl.equals(categoriaPendiente.getId())) {

            // Si hay una categoría pendiente y coincide con el id especificado en la URL, ésta será la que se editará.
            categoriaEditar = categoriaPendiente;

            // Se elimina la categoría pendiente en la sesión.
            session.removeAttribute("categoriaPendienteActualizar");

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
            
            // Si no hay una categoría pendiente, se consulta en la base de datos.
            if (idCategoriaUrl != null) {
                try {
                    // Se asume que tienes un ICategoriasBO y el método consultarCategoria(Long id)
                    categoriaEditar = categoriasBO.consultarCategoria(idCategoriaUrl);
                } catch (NegocioException ex) {
                    request.setAttribute("mensajeError", ex.getMessage());
                }
            }
        }

        request.setAttribute("categoriaEditar", categoriaEditar);
        
        request.getRequestDispatcher("admin-edicion-categoria.jsp").forward(request, response);
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
