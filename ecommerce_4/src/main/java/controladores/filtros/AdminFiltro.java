/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores.filtros;

import dominio.Administrador;
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

        // Se verifica la URL que se está pidiendo
        String path = httpRequest.getServletPath();

        // Se verifica que sea una pantalla admin (que empiezan con admin)con startsWith (empieza con...)
        boolean esRutaAdmin = path.startsWith("/admin-");

        if (esRutaAdmin) {

            // Validamos la sesion
            HttpSession session = httpRequest.getSession(false);
            boolean logueado = false;

            Object usuarioSesion = session != null ? session.getAttribute("usuarioLogueado") : null;

            if (usuarioSesion != null) {
                if (usuarioSesion instanceof Administrador) {
                    logueado = true;
                }
            }

            if (logueado) {
                // el usuario es admin
                chain.doFilter(request, response);
            } else {
                // no es admin
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/iniciar-sesion.jsp");
            }
        } else {
            // si no empieza con admin pues no se hace nada
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
