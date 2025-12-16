/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.filtros;

import dominio.AdministradorDTO;
import io.jsonwebtoken.Claims;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import utils.AutenticacionUtils;

/**
 * Filtro para asegurar que solo admins logueados ingresen
 *
 * @author chris
 */
@WebFilter(filterName = "AdminFiltro", urlPatterns = {"/admin-*", "/perfil-*", "/carrito.jsp", "/compra-*"})
public class AdminFiltro implements Filter {

    private static final String NOMBRE_COOKIE_TOKEN = "jwtToken";
    private static final String RUTA_LOGIN = "/iniciar-sesion.jsp";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Obtener la URL que se está pidiendo
        String path = httpRequest.getServletPath();

        if (path.startsWith("/js/") || path.startsWith("/css/")
                || path.startsWith("/styles/") || path.startsWith("/imgs/")
                || path.startsWith("/icons/") || path.equals(RUTA_LOGIN)) {
            chain.doFilter(request, response);
            return;
        }

        // Obtener el token de la cookie
        String token = null;
        if (httpRequest.getCookies() != null) {
            for (Cookie c : httpRequest.getCookies()) {
                if (NOMBRE_COOKIE_TOKEN.equals(c.getName())) {
                    token = c.getValue();
                    break;
                }
            }
        }

        // Si no hay token, redirigir al login
        if (token == null) {
            // Si la ruta es protegida, forzar login. Si es pública (ej. index.jsp), dejar pasar.
            if (esRutaProtegida(path)) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + RUTA_LOGIN);
            } else {
                chain.doFilter(request, response);
            }
            return;
        }

        // Validar el token y obtener Rol
        Claims claims = AutenticacionUtils.validarToken(token);

        if (claims == null) {
            // Token inválido/expirado -> Login
            if (esRutaProtegida(path)) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + RUTA_LOGIN);
            } else {
                // Posiblemente borrar cookie inválida aquí
                chain.doFilter(request, response);
            }
            return;
        }

        String rol = claims.get("rol", String.class);

        // Autorización según Rol y Ruta
        boolean esRutaAdmin = path.startsWith("/admin-");

        if (esRutaAdmin) {
            if ("AdministradorDTO".equals(rol)) {
                // Es Admin en zona Admin -> OK
                chain.doFilter(request, response);
            } else {
                // Cliente intentando entrar a Admin -> Error 403
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado: Se requieren permisos de administrador.");
            }
        } else {
            // Rutas de cliente (perfil, carrito, etc)
            // Aquí dejamos pasar a cualquiera que tenga token válido (Cliente o Admin)
            chain.doFilter(request, response);
        }
    }

    // Método auxiliar para saber si la ruta requiere login obligatorio
    private boolean esRutaProtegida(String path) {
        return path.startsWith("/admin-")
                || path.startsWith("/perfil-")
                || path.startsWith("/carrito.jsp")
                || path.startsWith("/pedidos-")
                || path.startsWith("/compra-");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
