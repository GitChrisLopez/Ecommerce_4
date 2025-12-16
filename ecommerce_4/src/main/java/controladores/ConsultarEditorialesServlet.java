
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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
@WebServlet(name = "ConsultarEditorialesServlet", urlPatterns = {"/admin-editoriales-registradas"})
public class ConsultarEditorialesServlet extends HttpServlet {

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
            out.println("<title>Servlet ConsultarEditorialesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConsultarEditorialesServlet at " + request.getContextPath() + "</h1>");
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
        
        // Se obtiene el nombre de las editoriales buscadas, si las hay.
        String nombreEditorialBuscada = request.getParameter("nombre-buscado");

        // Se crea una lista vacía de dto para guardar las editoriales recuperadas.
        List<EditorialDTO> listaEditoriales = new LinkedList<>(); 
        
        // Se consultan los libros coincidentes con el nobre ingresado si se ingresó.
        if(nombreEditorialBuscada != null && !nombreEditorialBuscada.isBlank()){
            try {
                
                listaEditoriales = editorialesBO.consultarEditoriales(nombreEditorialBuscada);
                
            } catch (Exception ex) {
                
                request.setAttribute("mensajeError", "Error al cargar las editoriales con el nombre ingresado.");
            }
        } else{
            
            try {
                listaEditoriales = editorialesBO.consultarEditoriales();

            } catch (Exception ex) {

                request.setAttribute("mensajeError", "Error al cargar las editoriales registradas.");

            }
        
        }

        request.setAttribute("listaEditoriales", listaEditoriales);
        request.setAttribute("nombreBuscado", nombreEditorialBuscada);

        request.getRequestDispatcher("admin-editoriales-registradas.jsp").forward(request, response);
        
        
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
