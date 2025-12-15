package controladores;

import BOs.AdministradorBO;
import BOs.UsuarioBO;
import dominio.AdministradorDTO;
import dominio.ClienteDTO;
import dominio.UsuarioDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            HttpSession session = request.getSession(true);

            // guardamos la sesion temporal
            session.setAttribute("usuarioLogueado", usuarioLogueado);

            // se redirecciona según el tipo de usuario (user/admin)
            if (usuarioLogueado instanceof AdministradorDTO) {
                response.sendRedirect("admin-menu-principal");
            } else if (usuarioLogueado instanceof ClienteDTO) {
                response.sendRedirect("principal-registrado.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }

        } else {
            // fallo de la autenticación
            response.sendRedirect("iniciar-sesion.jsp?error=true");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet de Login General (Admin y Cliente)";
    }
}
