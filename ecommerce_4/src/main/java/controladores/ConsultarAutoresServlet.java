
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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */

@WebServlet(name = "ConsultarAutoresServlet", urlPatterns = {"/admin-autores-registrados"})
public class ConsultarAutoresServlet extends HttpServlet {

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
            out.println("<title>Servlet ConsultarAutoresServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConsultarAutoresServlet at " + request.getContextPath() + "</h1>");
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
        
        // Se obtiene el nombre de los autores, si las hay.
        String nombreAutorBuscado = request.getParameter("nombre-buscado");

        // Se crea una lista vacía de dto para guardar los autores recuperados.
        List<AutorDTO> listaAutores = new LinkedList<>(); 
        
        // Se consultan los autores coincidentes con el nombre ingresado si se ingresó.
        if(nombreAutorBuscado != null && !nombreAutorBuscado.isBlank()){
            try {
                
                listaAutores = autoresBO.consultarAutores(nombreAutorBuscado);
                
            } catch (Exception ex) {
                
                request.setAttribute("mensajeError", "Error al cargar los autores con el nombre ingresado.");
            }
        } else{
            
            try {
                listaAutores = autoresBO.consultarAutores();

            } catch (Exception ex) {

                request.setAttribute("mensajeError", "Error al cargar las editoriales registradas.");

            }
        
        }

        request.setAttribute("listaAutores", listaAutores);
        request.setAttribute("nombreBuscado", nombreAutorBuscado);

        request.getRequestDispatcher("admin-autores-registrados.jsp").forward(request, response);
        
        
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
