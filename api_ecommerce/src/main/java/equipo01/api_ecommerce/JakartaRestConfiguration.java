package equipo01.api_ecommerce;

import api.AutenticacionResource;
import api.CarritosResource;
import api.ProductosCarritoResource;
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
        
        classes.add(CarritosResource.class);
        classes.add(ProductosCarritoResource.class);
        classes.add(AutenticacionResource.class);
        return classes;
        
    }
    
    
    
}
