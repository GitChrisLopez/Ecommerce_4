
package controladores.filtros;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * Filtro para pasar mensajes de error en sesión a mensajes en solcitudes.
 * 
 * @author Romo López Manuel ID: 00000253080
 */

public class MensajeEliminacionProducto implements Filter {

    private FilterConfig filterConfig = null;
    
    /**
     *
     * Toma el mensaje de error de la sesión y lo coloca en la solicitud, eliminándo
     * el primero.
     * @param request La solicitud del servlet que se está procesando.
     * @param response La respuesta del servlet que se está procesando.
     * @param chain La cadena de filtros que se está procesando.
     *
     * @exception IOException Si ocurre un error de entrada/salida.
     * @exception ServletException Si ocurre un error de servlet.
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session != null) {

            // Se revisa si hay un mensaje de error.
            String error = (String) session.getAttribute("mensajeError");
            
            if (error != null) {
                
                // Si existe, se coloca en la solcitud y se elimina de la sesión.
                request.setAttribute("mensajeError", error);  
                session.removeAttribute("mensajeError");
            }
            
        }
        chain.doFilter(request, response);
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
    
    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }
    
}
