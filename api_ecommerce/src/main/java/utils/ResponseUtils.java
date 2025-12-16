
package utils;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Romo López Manuel ID: 00000253080
 */
public class ResponseUtils {
    
    public static Response construirResponseExito(String mensajeExito, Object data) {
        
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("exito", true);
        cuerpo.put("mensaje", mensajeExito);
        
        if (data != null) {
            cuerpo.put("data", data);
        }
        
        return Response.ok(cuerpo).build();
    }
    

    public static Response construirResponseError(String mensajeError, Status status) {
        
        Map<String, Object> cuerpo = new HashMap<>();
        cuerpo.put("exito", false);
        cuerpo.put("error", mensajeError);

        return Response.status(status)
                       .entity(cuerpo)
                       .build();
    }
    
}
