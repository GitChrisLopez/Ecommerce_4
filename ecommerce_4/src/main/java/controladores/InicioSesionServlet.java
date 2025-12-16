package controladores;

import BOs.UsuarioBO;
import dominio.AdministradorDTO;
import dominio.ClienteDTO;
import dominio.UsuarioDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JwtUtil;

/**
 * Servlet para autenticar al administrador.
 *
 * @author chris
 */
@WebServlet(name = "InicioSesionServlet", urlPatterns = {"/login"})
public class InicioSesionServlet extends HttpServlet {

    private UsuarioBO usuarioBO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.usuarioBO = new UsuarioBO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AutorizacionAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AutorizacionAdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("logout")) {
            // Invalidar Sesión
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }

            // Matar Cookie x_x
            Cookie jwtCookie = new Cookie("jwtToken", "");
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(0);
            response.addCookie(jwtCookie);

            // Redirigir
            response.sendRedirect("index.jsp");

        } else {
            // Si entran por GET sin acción, mostrar formulario
            response.sendRedirect("iniciar-sesion.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UsuarioDTO usuarioLogueado = usuarioBO.iniciarSesion(email, password);

        if (usuarioLogueado != null) {
            // Generar Token y Cookie (ESTO YA LO TIENES Y ESTÁ BIEN)
            String token = JwtUtil.generarToken(usuarioLogueado);
            Cookie jwtCookie = new Cookie("jwtToken", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(false);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(jwtCookie);

            // Guardar usuario en la Sesión del Servidor
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuarioLogueado);

            // Redirección
            if (usuarioLogueado instanceof AdministradorDTO) {
                response.sendRedirect("admin-menu-principal");
            } else if (usuarioLogueado instanceof ClienteDTO) {
                response.sendRedirect("principal-registrado.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        } else {
            response.sendRedirect("iniciar-sesion.jsp?error=true");
        }
    }
}
