
package controladores;

import definiciones.ILibrosBO;
import dominio.LibroDTO;
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
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */

@WebServlet(name = "ConsultarLibrosServlet", urlPatterns = {"/admin-libros-registrados"})
public class ConsultarLibrosServlet extends HttpServlet {

    private ILibrosBO librosBO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        this.librosBO = FabricaBO.obtenerLibrosBO();
        
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
            out.println("<title>Servlet ConsultarLibrosServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConsultarLibrosServlet at " + request.getContextPath() + "</h1>");
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
        
        // Se obtiene el título de los libros buscados, si los hay.
        String tituloLibroBuscado = request.getParameter("titulo-buscado");

        // Se crea una lista vacía de dto para guardar los libros recuperados.
        List<LibroDTO> listaLibros = new LinkedList<>(); 
        
        // Se consultan los libros coincidentes con el título ingresado si se ingresó.
        if(tituloLibroBuscado != null && !tituloLibroBuscado.isBlank()){
            try {
                
                listaLibros = librosBO.consultarLibros(tituloLibroBuscado);
                
            } catch (Exception ex) {
                
                request.setAttribute("mensajeError", "Error al cargar los libros con el título ingresado.");
            }
        } else{
            
            try {
                listaLibros = librosBO.consultarLibros();

            } catch (Exception ex) {

                request.setAttribute("mensajeError", "Error al cargar los libros registrados.");

            }
        
        }

        request.setAttribute("listaLibros", listaLibros);
        request.setAttribute("tituloBuscado", tituloLibroBuscado);

        request.getRequestDispatcher("admin-libros-registrados.jsp").forward(request, response);

        
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
