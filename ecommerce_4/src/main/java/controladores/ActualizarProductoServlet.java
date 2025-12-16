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
@WebServlet(name = "ActualizarProducto", urlPatterns = {"/admin-actualizar-producto"})

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)

public class ActualizarProductoServlet extends HttpServlet {

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
            out.println("<title>Servlet ActualizarProducto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActualizarProducto at " + request.getContextPath() + "</h1>");
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

        String idProducto = request.getParameter("id");

        try {

            // Se obtienen los datos ingresados.
            Long id = Long.valueOf(idProducto);

            String accion = request.getParameter("accion");
            
            if ("agotar".equals(accion)) {

                ProductoDTO productoActual = productosBO.consultarProducto(id);
                
                if (productoActual != null) {
                    // Se actualiza el stock.
                    productoActual.setStock(0);
                    productosBO.actualizarProducto(productoActual);
                }

                response.sendRedirect("admin-menu-principal");
                return;
            }

            
            String isbnStr = request.getParameter("isbn");
            String idLibroStr = request.getParameter("id-libro");

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
                throw new IllegalArgumentException("El ID del libro es requerido.");
            }
            Long idLibro = Long.valueOf(idLibroStr);

            // Se obtiene el libro con el ID ingresado. 
            LibroDTO libroEncontrado = librosBO.consultarLibro(idLibro);

            // Se crea dto con los datos nuevos del producto.
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setId(id);
            productoDTO.setIsbn(String.valueOf(isbn));
            productoDTO.setNumeroPaginas(numeroPaginas);
            productoDTO.setPrecio(precio);
            productoDTO.setStock(stock);
            productoDTO.setFormato(formatoEnum);
            productoDTO.setUrlImagen(urlImagenFinal);
            productoDTO.setLibro(libroEncontrado);

            productosBO.actualizarProducto(productoDTO);

            // Como la actualización fue exitosa, se elimina cualquier producto pendiente y error guardados
            // en la sesión.
            request.getSession().removeAttribute("productoPendienteActualizar");
            request.getSession().removeAttribute("errorSesion");
            request.getSession().removeAttribute("mensajeError");

            response.sendRedirect("admin-menu-principal");

        } catch (Exception e) {

            // Se guardan los datos ingresados por el usuario.
            ProductoDTO productoPendienteActualizar = new ProductoDTO();
            try {

                productoPendienteActualizar.setId(Long.valueOf(idProducto));
                // Se intentan recuperar valores numéricos.
                try {
                    productoPendienteActualizar.setPrecio(new BigDecimal(request.getParameter("precio")));
                } catch (Exception ex) {

                }

                try {
                    productoPendienteActualizar.setStock(Integer.valueOf(request.getParameter("stock")));
                } catch (Exception ex) {

                }

                try {
                    productoPendienteActualizar.setNumeroPaginas(Integer.valueOf(request.getParameter("numero-paginas")));
                } catch (Exception ex) {

                }

                // Se obtiene el ISBN del producto.
                productoPendienteActualizar.setIsbn(request.getParameter("isbn"));

                // Se obtiene la imagen del producto.
                productoPendienteActualizar.setUrlImagen(request.getParameter("url-imagen"));

                // Se obtiene el formato seleccionado.
                String formato = request.getParameter("formato");

                productoPendienteActualizar.setFormato(FormatoDTO.valueOf(formato));

                try {
                    String idLibroPendienteStr = request.getParameter("id-libro");
                    if (idLibroPendienteStr != null && !idLibroPendienteStr.isEmpty()) {
                        Long idLibroPendiente = Long.valueOf(idLibroPendienteStr);
                        LibroDTO libroPendiente = librosBO.consultarLibro(idLibroPendiente);
                        productoPendienteActualizar.setLibro(libroPendiente);
                    }
                } catch (Exception ex) {
                }

            } catch (Exception ex) {
                // Si falla la recuperación de algún dato, no se considera.
            }

            // Se guarda el producto pendiente en la sesión y el mensaje de error.
            HttpSession session = request.getSession();
            session.setAttribute("productoPendienteActualizar", productoPendienteActualizar);
            session.setAttribute("errorSesion", e.getMessage());

            response.sendRedirect("admin-edicion-producto?id=" + idProducto);
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
