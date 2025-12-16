package controladores;

import definiciones.ILibrosBO;
import definiciones.IProductosBO;
import dominio.FormatoDTO;
import dominio.LibroDTO;
import dominio.ProductoDTO;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 *
 */
@WebServlet(name = "EdicionProducto", urlPatterns = {"/admin-edicion-producto"})
public class EditarProductoServlet extends HttpServlet {

    private IProductosBO productosBO;
    private ILibrosBO librosBO;

    @Override
    public void init() throws ServletException {
        super.init();

        this.productosBO = FabricaBO.obtenerProductosBO();
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
            out.println("<title>Servlet EdicionProducto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EdicionProducto at " + request.getContextPath() + "</h1>");
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

        String idProductoEditar = request.getParameter("id");
        Long idProductoUrl = null;

        if (idProductoEditar != null && !idProductoEditar.isEmpty()) {
            idProductoUrl = Long.valueOf(idProductoEditar);
        }

        ProductoDTO productoEditar = null;

        List<LibroDTO> listaLibros = new ArrayList<>();

        // Se revisa la sesión para buscar si hay un producto pendiente y/o un error.
        HttpSession session = request.getSession();
        ProductoDTO productoPendiente = (ProductoDTO) session.getAttribute("productoPendienteActualizar");
        String errorSesion = (String) session.getAttribute("errorSesion");

        // Se determina si hay un producto pendiente de actualizar en la sesión que coincida con el id de la URL.
        if (idProductoUrl != null && productoPendiente != null && productoPendiente.getId().equals(idProductoUrl)) {

            // Si hay un producto pendiente y coincide con el id especificado en la URL, éste será el que se editará.
            productoEditar = productoPendiente;

            // Se elimina el producto pendiente en la sesión.
            session.removeAttribute("productoPendienteActualizar");

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

            // Si no hay un producto pendiente, se consulta en la base de datos.
            if (idProductoUrl != null) {
                try {
                    productoEditar = productosBO.consultarProducto(idProductoUrl);
                } catch (NegocioException ex) {
                    request.setAttribute("mensajeError", ex.getMessage());
                }
            }
        }

        try {
            listaLibros = librosBO.consultarLibros();
        } catch (NegocioException ex) {
            String mensajeActual = (String) request.getAttribute("mensajeError");
            if (mensajeActual == null) {
                request.setAttribute("mensajeError", "No se pudieron cargar los libros disponibles.");
            } else {
                request.setAttribute("mensajeError", mensajeActual + " Además, no se pudieron cargar los libros disponibles.");
            }
        }

        request.setAttribute("listaLibros", listaLibros);
        request.setAttribute("productoEditar", productoEditar);

        // Carga de lista de formatos.
        Map<String, String> mapaFormatos = new LinkedHashMap<>();
        for (FormatoDTO formato : FormatoDTO.values()) {
            String clave = formato.name();
            String valor = formato.name().toLowerCase().replace("_", " ");
            valor = valor.substring(0, 1).toUpperCase() + valor.substring(1);
            mapaFormatos.put(clave, valor);
        }
        request.setAttribute("mapaFormatos", mapaFormatos);

        request.getRequestDispatcher("admin-edicion-producto.jsp").forward(request, response);
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
