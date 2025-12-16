 package controladores;

import definiciones.ILibrosBO;
import dominio.FormatoDTO;
import dominio.LibroDTO;
import dominio.ProductoDTO;
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
 */
@WebServlet(name = "NuevoProductoServlet", urlPatterns = {"/admin-nuevo-producto"})
public class NuevoProductoServlet extends HttpServlet {

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
            out.println("<title>Servlet NuevoProductoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NuevoProductoServlet at " + request.getContextPath() + "</h1>");
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

        ProductoDTO productoAgregar = null;
        List<LibroDTO> listaLibros = new ArrayList<>();
        String mensajeError = null;

        // Se revisa la sesión para buscar si hay un producto pendiente y/o un error.
        HttpSession session = request.getSession();
        ProductoDTO productoPendiente = (ProductoDTO) session.getAttribute("productoPendienteAgregar");
        String errorSesion = (String) session.getAttribute("errorSesion");

        // Se determina si hay un producto pendiente de actualizar en la sesión.
        if (productoPendiente != null) {

            // Si hay un producto pendiente, éste será el que se editará.
            productoAgregar = productoPendiente;

            // Se elimina el producto pendiente en la sesión.
            session.removeAttribute("productoPendienteAgregar");

            if (errorSesion != null) {
                // Se coloca el mensaje de error en la solicitud para la página destino.
                request.setAttribute("mensajeError", errorSesion);

                // Se elimina para evitar que salga al refrescar la página.
                session.removeAttribute("errorSesion");
            }
        } else {
            request.removeAttribute("mensajeError");
        }

        request.setAttribute("productoAgregar", productoAgregar);

        try {
            listaLibros = librosBO.consultarLibros();
        } catch (Exception e) {
            if (mensajeError == null) {
                mensajeError = "Error al cargar la lista de libros: " + e.getMessage();
            }
        }

        request.setAttribute("mensajeError", mensajeError);
        request.setAttribute("listaLibros", listaLibros);

        // Carga de lista de formatos.
        Map<String, String> mapaFormatos = new LinkedHashMap<>();
        for (FormatoDTO formato : FormatoDTO.values()) {
            String clave = formato.name();
            String valor = formato.name().toLowerCase().replace("_", " ");
            valor = valor.substring(0, 1).toUpperCase() + valor.substring(1);
            mapaFormatos.put(clave, valor);
        }

        request.setAttribute("mapaFormatos", mapaFormatos);

        request.getRequestDispatcher("admin-agregar-producto.jsp").forward(request, response);
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
