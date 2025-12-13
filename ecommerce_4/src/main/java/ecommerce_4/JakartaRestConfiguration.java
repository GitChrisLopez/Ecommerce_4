package ecommerce_4;


import api.ClientesResource;
import api.PedidosResource;
import api.ProductosResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Configures Jakarta RESTful Web Services for the application.
 * @author Juneau
 */
@ApplicationPath("api")
public class JakartaRestConfiguration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(ClientesResource.class);
        classes.add(PedidosResource.class);
        classes.add(ProductosResource.class);
        return classes;
    }
    
}
