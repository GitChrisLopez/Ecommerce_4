package excepciones;

/**
 * Excepción personalizada que se utiliza para manejar errores relacionados con 
 * la lógica de negocio en la aplicación.
 * 
 * @author norma
 */
public class NegocioException extends Exception {

    /**
     * Constructor por defecto que crea una nueva instancia de la excepción sin mensaje.
     */
    public NegocioException() {
    }

    /**
     * Constructor que crea una nueva instancia de la excepción con un mensaje de error.
     *
     * @param message el mensaje que describe el motivo del error.
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * Constructor que crea una nueva instancia de la excepción con un mensaje de error
     * y una causa que originó la excepción.
     *
     * @param message el mensaje que describe el motivo del error.
     * @param cause la causa original del error.
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}