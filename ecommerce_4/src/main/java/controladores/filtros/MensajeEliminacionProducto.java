
package controladores.filtros;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
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

    @Override
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
        // Este filtro siempre permite que la cadena continúe al siguiente filtro/servlet.
        chain.doFilter(request, response); 
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
    }

}
