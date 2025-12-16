package controladores;

import definiciones.ILibrosBO;
import definiciones.IProductosBO;
import dominio.FormatoDTO;
import dominio.LibroDTO;
import dominio.ProductoDTO;
import fabrica.FabricaBO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)

@WebServlet(name = "AgregarProductoServlet", urlPatterns = {"/admin-agregar-producto"})
public class AgregarProductoServlet extends HttpServlet {

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
            out.println("<title>Servlet AgregarProductoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarProductoServlet at " + request.getContextPath() + "</h1>");
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

        try {

            // Se obtienen los datos ingresados.
            String idLibroStr = request.getParameter("id-libro");
            String isbnStr = request.getParameter("isbn");

            Long isbn = null;
            if (isbnStr != null && !isbnStr.isEmpty()) {
                isbn = Long.valueOf(isbnStr);
            }

            Integer numeroPaginas = Integer.valueOf(request.getParameter("numero-paginas"));
            BigDecimal precio = new BigDecimal(request.getParameter("precio"));
            Integer stock = Integer.valueOf(request.getParameter("stock"));

            String formatoString = request.getParameter("formato");
            FormatoDTO formatoEnum = FormatoDTO.valueOf(formatoString);

            // Se guarda la imagen en el servidor.
            Part filePart = request.getPart("foto-nueva");
            String urlImagenFinal = request.getParameter("url-imagen");

            if (filePart != null && filePart.getSize() > 0 && filePart.getSubmittedFileName().length() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String path = request.getServletContext().getRealPath("");
                String pathGuardar = path + File.separator + "imgs";

                File directorio = new File(pathGuardar);
                if (!directorio.exists()) {
                    directorio.mkdirs();
                }

                File targetFile = new File(pathGuardar + File.separator + fileName);
                try (InputStream fileContent = filePart.getInputStream()) {
                    Files.copy(fileContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                urlImagenFinal = "imgs/" + fileName;
            }

            if (idLibroStr == null || idLibroStr.isEmpty()) {
                throw new IllegalArgumentException("Debe seleccionar un libro.");
            }
            Long idLibro = Long.valueOf(idLibroStr);

            // Se obtiene el libro con el ID ingresado. 
            LibroDTO libroEncontrado = librosBO.consultarLibro(idLibro);

            // Se crea dto con los datos nuevos del producto.
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setIsbn(String.valueOf(isbn));
            productoDTO.setNumeroPaginas(numeroPaginas);
            productoDTO.setPrecio(precio);
            productoDTO.setStock(stock);
            productoDTO.setFormato(formatoEnum);
            productoDTO.setUrlImagen(urlImagenFinal);
            productoDTO.setLibro(libroEncontrado);

            productosBO.registrarProducto(productoDTO);

            // Como el registro fue exitoso, se elimina cualquier producto pendiente y error guardados
            // en la sesión.
            request.getSession().removeAttribute("productoPendienteAgregar");
            request.getSession().removeAttribute("errorSesion");

            response.sendRedirect("admin-menu-principal");

        } catch (Exception e) {

            e.printStackTrace();
            // Se guardan los datos ingresados por el usuario.
            ProductoDTO productoPendienteAgregar = new ProductoDTO();
            try {

                // Se intentan recuperar valores numéricos.
                try {
                    productoPendienteAgregar.setPrecio(new BigDecimal(request.getParameter("precio")));
                } catch (Exception ex) {

                }

                try {
                    productoPendienteAgregar.setStock(Integer.valueOf(request.getParameter("stock")));
                } catch (Exception ex) {

                }

                try {
                    productoPendienteAgregar.setNumeroPaginas(Integer.valueOf(request.getParameter("numero-paginas")));
                } catch (Exception ex) {

                }

                // Se obtiene el ISBN del producto.
                productoPendienteAgregar.setIsbn(request.getParameter("isbn"));

                // Se obtiene la imagen del producto.
                productoPendienteAgregar.setUrlImagen(request.getParameter("url-imagen"));

                // Se obtiene el formato seleccionado.
                String formato = request.getParameter("formato");

                productoPendienteAgregar.setFormato(FormatoDTO.valueOf(formato));

                String idLibroStr = request.getParameter("id-libro");
                if (idLibroStr != null && !idLibroStr.isEmpty()) {
                    Long idLibroPendiente = Long.valueOf(idLibroStr);
                    LibroDTO libro = librosBO.consultarLibro(idLibroPendiente);
                    productoPendienteAgregar.setLibro(libro);
                } else {
                    productoPendienteAgregar.setLibro(new LibroDTO()); 
                }

            } catch (Exception ex) {
            }

            // Se guarda el producto pendiente en la sesión y el mensaje de error.
            HttpSession session = request.getSession();
            session.setAttribute("productoPendienteAgregar", productoPendienteAgregar);
            session.setAttribute("errorSesion", e.getMessage());

            response.sendRedirect("admin-nuevo-producto");
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
