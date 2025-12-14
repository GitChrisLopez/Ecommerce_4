
package controladores;

import definiciones.IAutoresBO;
import definiciones.ICategoriasBO;
import definiciones.IEditorialesBO;
import definiciones.ILibrosBO;
import dominio.AutorDTO;
import dominio.CategoriaDTO;
import dominio.EditorialDTO;
import dominio.LibroDTO;
import fabrica.FabricaBO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
@WebServlet(name = "ActualizarLibroServlet", urlPatterns = {"/admin-actualizar-libro"})
public class ActualizarLibroServlet extends HttpServlet {

    private IAutoresBO autoresBO;
    private IEditorialesBO editorialesBO;
    private ICategoriasBO categoriasBO;
    private ILibrosBO librosBO;

    @Override
    public void init() throws ServletException {
        super.init();

        this.autoresBO = FabricaBO.obtenerAutoresBO();
        this.editorialesBO = FabricaBO.obtenerEditorialesBO();
        this.categoriasBO = FabricaBO.obtenerCategoriasBO();
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
            out.println("<title>Servlet ActualizarLibroServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarLibroServlet at " + request.getContextPath() + "</h1>");
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
        String idLibroStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String sinopsis = request.getParameter("sinopsis");
        String idAutorStr = request.getParameter("id-autor");
        String idEditorialStr = request.getParameter("id-editorial");
        String[] idsCategoriasSeleccionadasStr = request.getParameterValues("ids-categorias");
        String fechaPublicacionStr = request.getParameter("fecha-publicacion");

        try{
            
            // Se obtiene el Id del libro.
            Long idLibro = null;
            if(idLibroStr != null && !idLibroStr.isEmpty()){
                idLibro = Long.valueOf(idLibroStr);
            }
            
            // Se obtiene el Id del autor.
            Long idAutor = null;
            if(idAutorStr != null && !idAutorStr.isEmpty()){
                idAutor = Long.valueOf(idAutorStr);
            }
            
            // Se obtiene el Id de la editorial.
            Long idEditorial = null;
            if(idEditorialStr != null && !idEditorialStr.isEmpty()){
                idEditorial = Long.valueOf(idEditorialStr);
            }
            
            // Se obtiene la fecha de publicación.
            LocalDate fechaPublicacion = null;
            if(fechaPublicacionStr != null && !fechaPublicacionStr.isEmpty()){
                fechaPublicacion = LocalDate.parse(fechaPublicacionStr);
            }
            

            // Se obtiene el autor con el Id ingresado. 
            AutorDTO autor = autoresBO.consultarAutor(idAutor);
            
            // Se obtiene la editorial con el Id ingreasdo.
            EditorialDTO editorial = editorialesBO.consultarEditorial(idEditorial);
            
            // Se obtienen las categorías seleccionadas.
            List<CategoriaDTO> categorias = new LinkedList<>();
           
            if(idsCategoriasSeleccionadasStr != null){
                
                for(String idCategoriaStr: idsCategoriasSeleccionadasStr){

                    Long idCategoria = Long.valueOf(idCategoriaStr);
                    categorias.add(categoriasBO.consultarCategoria(idCategoria));

                }
                
            }
            

            // Se crea un dto con los nuevos datos del libro.
            LibroDTO libroDTO = new LibroDTO();
            libroDTO.setId(idLibro);
            libroDTO.setAutor(autor);
            libroDTO.setEditorial(editorial);
            libroDTO.setCategorias(categorias);
            libroDTO.setTitulo(titulo);
            libroDTO.setSinopsis(sinopsis);
            libroDTO.setFechaPublicacion(fechaPublicacion);

            librosBO.actualizarLibro(libroDTO);

            // Como la actualización fue exitosa, se elimina cualquier libro pendiente y error guardados
            // en la sesión.
            request.getSession().removeAttribute("libroPendienteActualizar");
            request.getSession().removeAttribute("errorSesion");
            request.getSession().removeAttribute("mensajeError");

            response.sendRedirect("admin-libros-registrados");

        } catch (Exception e) {

            // Se guardan los datos ingresados por el usuario.
            LibroDTO libroPendienteActualizar = new LibroDTO();
            try {

                libroPendienteActualizar.setId(Long.valueOf(idLibroStr));

                // Se obtiene el título del libro.
                libroPendienteActualizar.setTitulo(request.getParameter("titulo"));
                
                // Se obtiene la sinopsis del libro.
                libroPendienteActualizar.setSinopsis(request.getParameter("sinopsis"));

                // Se crea un objeto AutorDTO para almacenar el Id del autor seleccionado.
                try {
                    String idAutorRecuperado = request.getParameter("id-autor");

                    if (idAutorRecuperado != null && !idAutorRecuperado.isEmpty()) {
                        AutorDTO autor = new AutorDTO();
                        autor.setId(Long.valueOf(idAutorRecuperado));
                        libroPendienteActualizar.setAutor(autor);
                    }
                } catch (Exception ex) {

                }

                // Se crea un objeto EditorialDTO para almacenar el Id de la editorial seleccionada.
                try {

                    String idEditorialRecuperado = request.getParameter("id-editorial");

                    if (idEditorialRecuperado != null && !idEditorialRecuperado.isEmpty()) {

                        EditorialDTO editorial = new EditorialDTO();
                        editorial.setId(Long.valueOf(idEditorialRecuperado));
                        libroPendienteActualizar.setEditorial(editorial);
                    }
                } catch (Exception ex) {
                }

                // Se crea un objeto EditorialDTO para almacenar los Id de las categorías seleccionadas.
                try {
                    String[] idsCategoriasRecuperados = request.getParameterValues("ids-categorias");
                    List<CategoriaDTO> categoriasRecuperadas = new LinkedList<>();

                    if (idsCategoriasRecuperados != null) {

                        for (String idCategoriaRecuperado : idsCategoriasRecuperados) {

                            CategoriaDTO categoria = new CategoriaDTO();
                            categoria.setId(Long.valueOf(idCategoriaRecuperado));
                            categoriasRecuperadas.add(categoria);
                        }
                    }
                    libroPendienteActualizar.setCategorias(categoriasRecuperadas);

                } catch (Exception ex) {
                    
                }

                // Se recupera la fecha ingresada.
                try {
                     String fechaStr = request.getParameter("fecha-publicacion");
                     if(fechaStr != null && !fechaStr.isEmpty()){
                         libroPendienteActualizar.setFechaPublicacion(LocalDate.parse(fechaStr));
                     }
                } catch (Exception ex) {
                
                }


            } catch (Exception ex) {
                // Si falla la recuperación de algún dato, no se considera.
            }

            // Se guarda el libro pendiente en la sesión y el mensaje de error.
            HttpSession session = request.getSession();
            session.setAttribute("libroPendienteActualizar", libroPendienteActualizar);
            session.setAttribute("errorSesion", e.getMessage());

            response.sendRedirect("admin-edicion-libro?id=" + idLibroStr);
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
