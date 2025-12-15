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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import utils.JwtUtil;

/**
 * Filtro para asegurar que solo admins logueados ingresen
 *
 * @author chris
 */
public class AdminFiltro implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Obtener la URL que se está pidiendo
        String path = httpRequest.getServletPath();

        // Verificar si es ruta de admin
        boolean esRutaAdmin = path.startsWith("/admin-");

        if (esRutaAdmin) {
            // logica de la cookie jwt
            String token = null;
            if (httpRequest.getCookies() != null) {
                for (Cookie c : httpRequest.getCookies()) {
                    if ("jwtToken".equals(c.getName())) {
                        token = c.getValue();
                        break;
                    }
                }
            }

            // Si no hay token, al login
            if (token == null) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/iniciar-sesion.jsp");
                return;
            }

            // Validar token
            Claims claims = JwtUtil.validarToken(token);
            if (claims != null) {
                String rol = claims.get("rol", String.class);

                // Verificar que sea Administrador
                if ("AdministradorDTO".equals(rol)) {
                    // Es admin y el token es válido -> PASE
                    chain.doFilter(request, response);
                } else {
                    // El token es válido pero es un Cliente intentando entrar a Admin -> ERROR 403
                    httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado: No eres administrador.");
                }
            } else {
                // Token manipulado o expirado -> al login
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/iniciar-sesion.jsp");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
