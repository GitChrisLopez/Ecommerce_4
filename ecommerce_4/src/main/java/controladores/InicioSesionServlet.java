package controladores;

import BOs.UsuarioBO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dominio.AdministradorDTO;
import dominio.ClienteDTO;
import dominio.UsuarioDTO;
import dominio.restful.AutenticacionClienteDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers; 
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet para autenticar al administrador.
 *
 * @author chris
 */
@WebServlet(name = "InicioSesionServlet", urlPatterns = {"/login"})
public class InicioSesionServlet extends HttpServlet {

    private final String NOMBRE_HEADER_TOKEN = "jwtToken";
    private static final Logger LOG = Logger.getLogger(InicioSesionServlet.class.getName());
    
    
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
                session.invalidate();
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
            session.setAttribute("usuarioLogueado", usuarioLogueado);

            // Se redirecciona al administrador
            if (usuarioLogueado instanceof AdministradorDTO) {

                response.sendRedirect("admin-menu-principal");

            } else if (usuarioLogueado instanceof ClienteDTO) {

                // Se obtiene el token de la api rest en caso de que el usuario sea un cliente.
                String token = null;

                try {
                    
 
                    AutenticacionClienteDTO autenticacionCliente = new AutenticacionClienteDTO(email, password);

                    Gson gson = new Gson();
                    String jsonBody = gson.toJson(autenticacionCliente);

                    // Se configura el cliente HTTP.
                    HttpClient client = HttpClient.newHttpClient();

                    // Se crea la petición.
                    HttpRequest apiRequest = HttpRequest.newBuilder()
                            .uri(URI.create(obtenerUrlServidor(request)))
                            .header("Content-Type", "application/json")
                            .POST(BodyPublishers.ofString(jsonBody))
                            .build();

                    // Se envía la petición.
                    HttpResponse<String> apiResponse = client.send(apiRequest, BodyHandlers.ofString());

                    if (apiResponse.statusCode() == 200) {
                        JsonObject jsonResponse = gson.fromJson(apiResponse.body(), JsonObject.class);
                        if (jsonResponse.has("token")) {
                            token = jsonResponse.get("token").getAsString();
                        }
                    }

                } catch (InterruptedException ex) {
                    LOG.log(Level.SEVERE, "Error conectando con la API externa", ex);
                }

                // Si se obtuvo el token, se crea la cookie.
                if (token != null) {
                    
                    Cookie jwtCookie = new Cookie(NOMBRE_HEADER_TOKEN, token); 

                    jwtCookie.setHttpOnly(true);
                    jwtCookie.setSecure(false);
                    jwtCookie.setPath("/");
                    jwtCookie.setMaxAge(3600);
                    response.addCookie(jwtCookie);
                }

                // Se redirige al cliente.
                response.sendRedirect("principal-registrado.jsp");

            } else {

                response.sendRedirect("index.jsp");
            }
        } else {

            // Fallo de inicio de sesión local.
            response.sendRedirect("iniciar-sesion.jsp?error=true");
        }
    }
    
    private String obtenerUrlServidor(HttpServletRequest request){
        
        // Se obtiene el protocolo.
        String esquema = request.getScheme();
        // Se obtiene el nombre del servidor.
        String servidor = request.getServerName();
        // Se obtiene el puerto.
        int puerto = request.getServerPort();
        
        return esquema + "://" + servidor + ":" + puerto + "/api/autenticacion";        
    }

}
