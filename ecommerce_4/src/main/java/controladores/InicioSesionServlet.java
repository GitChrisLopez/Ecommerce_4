package controladores;

import BOs.AdministradorBO;
import BOs.UsuarioBO;
import dominio.AdministradorDTO;
import dominio.ClienteDTO;
import dominio.UsuarioDTO;
import dominio.ClienteDTO;
import dominio.UsuarioDTO;
import entidades.Administrador;
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
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate(); // Destruye la sesión
            }
            response.sendRedirect("index.jsp");
        } else {
            // si se intenta entrar por GET al inicio de sesion sin cerrar sesion, los mandamos al formulario
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
            // generar string token
            String token = JwtUtil.generarToken(usuarioLogueado);

            // generar cookie
            Cookie jwtCookie = new Cookie("jwtToken", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(false);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(60 * 60 * 24);
            
            // enviar la cookie
            response.addCookie(jwtCookie);

            // guarda el usuario en la sesión para que el AdminFiltro lo encuentre
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuarioLogueado);

            // Mantienes tu redirección normal
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

    @Override
    public String getServletInfo() {
        return "Servlet de Login General (Admin y Cliente)";
    }
}
