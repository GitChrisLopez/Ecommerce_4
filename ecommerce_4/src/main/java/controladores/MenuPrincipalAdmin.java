
package controladores;

import definiciones.ICategoriasBO;
import definiciones.IProductosBO;
import dominio.CategoriaDTO;
import dominio.FormatoDTO;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
@WebServlet(name = "MenuPrincipalAdmin", urlPatterns = {"/admin-menu-principal"})
public class MenuPrincipalAdmin extends HttpServlet {

    private IProductosBO productosBO;
    private ICategoriasBO categoriasBO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        
        this.productosBO = FabricaBO.obtenerProductosBO();
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
            out.println("<title>Servlet MenuPrincipalAdmin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MenuPrincipalAdmin at " + request.getContextPath() + "</h1>");
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
        
        // Se obtienen las categorías registradas en el sistema y se agregan a la solicitud.
        List<CategoriaDTO> categorias = new LinkedList<>();
        try {
            categorias = categoriasBO.consultarCategorias(); 
        } catch (Exception e) {
            request.setAttribute("mensajeError", "No se pudieron cargar las categorías.");
        }
        request.setAttribute("listaCategorias", categorias);

        // Se agregan los formatos.
        Map<String, String> mapaFormatos = new LinkedHashMap<>();
        for (FormatoDTO formato : FormatoDTO.values()) {
            String clave = formato.name();
            String valor = formato.name().toLowerCase().replace("_", " ");
            valor = valor.substring(0, 1).toUpperCase() + valor.substring(1);
            mapaFormatos.put(clave, valor);
        }
        request.setAttribute("mapaFormatos", mapaFormatos);
        
        // Se obtiene el nombre ingresado.
        String tituloAutorIngresado = request.getParameter("titulo-autor-buscado");  
        
        // Se obtienen las categorías seleccionadas.
        String[] categoriasSeleccionadas = request.getParameterValues("categorias"); 
        
        // Se obtienen los formatos seleccionados.
        String[] formatosSeleccionados = request.getParameterValues("formatos");
        
        // Se obtiene el precio mínimo seleccionado.
        String precioMinStr = request.getParameter("precioMinimo");
        
        // Se obtiene el precio máximo seleccionado.
        String precioMaxStr = request.getParameter("precioMaximo");
        
        List<CategoriaDTO> filtrosCategoria = null; 
        List<FormatoDTO> filtrosFormato = null;     
        Double precioMinimo = 0d; 
        Double precioMaximo = null;

        // Se asignan los valores (filtros) seleccionados si estos tienen un valor.
        try {
            if (precioMinStr != null && !precioMinStr.trim().isEmpty()) {
                precioMinimo = Double.valueOf(precioMinStr);
            }
            
            if (precioMaxStr != null && !precioMaxStr.trim().isEmpty()) {
                precioMaximo = Double.valueOf(precioMaxStr);
            }

            if (categoriasSeleccionadas != null && categoriasSeleccionadas.length > 0) {
                filtrosCategoria = new ArrayList<>();
                for (String idCategoria : categoriasSeleccionadas) {
                    CategoriaDTO categoria = new CategoriaDTO();
                    categoria.setId(Long.valueOf(idCategoria));
                    filtrosCategoria.add(categoria);
                }
            }
            
            if (formatosSeleccionados != null && formatosSeleccionados.length > 0) {
                filtrosFormato = new ArrayList<>();
                for (String formato : formatosSeleccionados) {
                    filtrosFormato.add(FormatoDTO.valueOf(formato));
                }
            }

            List<ProductoDTO> listaProductos = productosBO.consultarProductosConFiltros(
                    tituloAutorIngresado,
                    filtrosCategoria, 
                    filtrosFormato, 
                    precioMinimo, 
                    precioMaximo
            );
            
            request.setAttribute("listaProductos", listaProductos);

        } catch (NumberFormatException e) {
            
            request.setAttribute("mensajeError", "Error en los filtros numéricos.");
            
            request.setAttribute("listaProductos", new LinkedList<>());
            
        } catch (NegocioException ex) {
            request.setAttribute("mensajeError", "No se pudo cargar la lista de productos.");
            
        } catch (Exception ex) {
            request.setAttribute("mensajeError", "Ocurrió un error inesperado: ");
        }

        request.getRequestDispatcher("admin-menu-administrador.jsp").forward(request, response);
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
