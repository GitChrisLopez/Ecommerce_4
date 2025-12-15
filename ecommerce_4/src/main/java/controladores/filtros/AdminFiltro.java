/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.filtros;

import dominio.AdministradorDTO;
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
import utils.AutenticacionUtils;

/**
 * Filtro para asegurar que solo admins logueados ingresen
 *
 * @author chris
 */

public class AdminFiltro implements Filter {

    private static final String NOMBRE_COOKIE_TOKEN = "jwtToken"; 
    private static final String RUTA_LOGIN = "/iniciar-sesion.jsp";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getServletPath();

        if (path.startsWith("/js/") || path.startsWith("/css/") || path.startsWith("/styles/") || path.startsWith("/imgs/") || path.equals(RUTA_LOGIN)) {
            chain.doFilter(request, response);
            return;
        }

        boolean esRutaAdmin = path.startsWith("/admin-");

        if (esRutaAdmin) {

            HttpSession session = httpRequest.getSession(false);
            boolean logueado = false;

            Object usuarioSesion = session != null ? session.getAttribute("usuarioLogueado") : null;

            if (usuarioSesion != null && usuarioSesion instanceof AdministradorDTO) {
                logueado = true;
            }

            if (logueado) {

                chain.doFilter(request, response);
            } else {

                httpResponse.sendRedirect(httpRequest.getContextPath() + RUTA_LOGIN);
            }

        } else {

            String token = null;
            Cookie[] cookies = httpRequest.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (NOMBRE_COOKIE_TOKEN.equals(cookie.getName())) { 
                        token = cookie.getValue();
                        break;
                    }
                }
            }
            
            if (token != null && !token.isEmpty()) {
                Long idCliente = AutenticacionUtils.extraerIdUsuario(token);

                if (idCliente != null) {
                    
                    request.setAttribute("idCliente", idCliente);
                    chain.doFilter(request, response);
                    return; 
                }
            }
            
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
