package excepciones;

/**
 * Excepción personalizada para errores relacionados con la capa de
 * persistencia.
 */
public class PersistenciaException extends Exception {

    /**
     * Crea una nueva instancia de PersistenciaException sin mensaje de error.
     */
    public PersistenciaException() {
    }

    /**
     * Crea una nueva instancia de PersistenciaException con un mensaje de error
     * especificado.
     *
     * @param message El mensaje descriptivo del error.
     */
    public PersistenciaException(String message) {
        super(message);
    }

    /**
     * Crea una nueva instancia de PersistenciaException con un mensaje de error
     * y una causa.
     *
     * @param message El mensaje descriptivo del error.
     * @param cause La causa original del error.
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
