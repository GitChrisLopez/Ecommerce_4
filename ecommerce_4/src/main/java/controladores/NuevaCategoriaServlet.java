
 
package controladores;

import dominio.CategoriaDTO;
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
 * 
 */
@WebServlet(name = "NuevaCategoriaServlet", urlPatterns = {"/admin-nueva-categoria"})
public class NuevaCategoriaServlet extends HttpServlet {

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
            out.println("<title>Servlet NuevaCategoriaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NuevaCategoriaServlet at " + request.getContextPath() + "</h1>");
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
        
        CategoriaDTO categoriaAgregar = null;
        
        // Se revisa la sesión para buscar si hay una categoría pendiente y/o un error.
        HttpSession session = request.getSession();
        CategoriaDTO categoriaPendiente = (CategoriaDTO) session.getAttribute("categoriaPendienteAgregar");
        String errorSesion = (String) session.getAttribute("errorSesion");

        // Se determina si hay una categoría pendiente de actualizar en la sesión.
        if (categoriaPendiente != null) {

            // Si hay una categoría pendiente, ésta será la que se editará.
            categoriaAgregar = categoriaPendiente;

            // Se elimina la categoría pendiente en la sesión.
            session.removeAttribute("categoriaPendienteAgregar");

            if (errorSesion != null) {
                // Se coloca el mensaje de error en la solicitud para la página destino.
                request.setAttribute("mensajeError", errorSesion);

                // Se elimina para evitar que salga al refrescar la página.
                session.removeAttribute("errorSesion");
            }
        } else{
             // Si no hay una categoría pendiente, se limpia el mensaje de error para carga inicial.
            request.removeAttribute("mensajeError");
        }

        request.setAttribute("categoriaAgregar", categoriaAgregar);
        
        request.getRequestDispatcher("admin-agregar-categoria.jsp").forward(request, response);
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
