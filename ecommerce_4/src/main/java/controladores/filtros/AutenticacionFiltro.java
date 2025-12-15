/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.filtros;

import io.jsonwebtoken.Claims;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JwtUtil;

/**
 *
 * @author chris
 */
@WebFilter(filterName = "AutenticacionFiltro", urlPatterns = {"/admin-*", "/perfil-*", "/carrito.jsp", "/compra-*"})
public class AutenticacionFiltro implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getServletPath();

        // Obtener el token de las cookies
        String token = null;
        if (httpRequest.getCookies() != null) {
            for (Cookie c : httpRequest.getCookies()) {
                if ("jwtToken".equals(c.getName())) {
                    token = c.getValue();
                    break;
                }
            }
        }

        // Si no hay token, mandar al login
        if (token == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/iniciar-sesion.jsp");
            return;
        }

        // Validar el token con JwtUtil
        Claims claims = JwtUtil.validarToken(token);

        if (claims == null) {
            // Token inválido
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/iniciar-sesion.jsp");
            return;
        }

        // Obtener el rol del token
        String rol = claims.get("rol", String.class); // Retornará "ClienteDTO" o "AdministradorDTO"

        // Lógica de autorización según la ruta y el rol
        boolean esRutaAdmin = path.startsWith("/admin-");
        boolean esAdmin = "AdministradorDTO".equals(rol);
        boolean esCliente = "ClienteDTO".equals(rol);

        if (esRutaAdmin) {
            if (esAdmin) {
                // Es ruta de admin y el token dice que es admin = PASE true
                chain.doFilter(request, response);
            } else {
                // Intenta entrar a admin pero el token no es de admin
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado: Se requieren permisos de administrador.");
            }
        } else {
            if (esCliente) {
                // Permitimos pasar a clientes
                chain.doFilter(request, response);
            } else {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/iniciar-sesion.jsp");
            }
        }

    }
}
