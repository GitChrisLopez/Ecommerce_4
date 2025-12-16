
package dominio;

/**
 *
 * Archivo: Autor.java
 * 
 * Clase que representa un autor que escribe libros.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/11/2025
 */
public class AutorDTO {
    
    /**
     * Dato Long que representa el identificador del Autor.
     */
    private Long id;
    
    /**
     * Dato String que representa el o los nombres del Autor.
     */
    private String nombre;
    
    /**
     * Dato String que representa el apellido paterno del Autor.
     */
    private String apellidoPaterno;
    
    /**
     * Dato String que representa el apellido materno del Autor.
     */
    private String apellidoMaterno;

    /**
     * Constructor por defecto.
     */
    public AutorDTO(){
        
    }
    
    /**
     * Constructor que recibe los parámetros necesarios para crear un Autor.
     * @param nombre Dato String que representa el o lo nombres del Autor.
     * @param apellidoPaterno Dato String que representa el apellido paterno del Autor.
     * @param apellidoMaterno Dato String que representa el apellido materno del Autor.
     */
    public AutorDTO(
            String nombre, 
            String apellidoPaterno,
            String apellidoMaterno) {
        
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Permite obtener el Id del Autor.
     * @return Dato Long que representa el Id del Autor.
     */
    public Long getId() {
        return id;
    }

    /**
     * Permite establecer el Id del Autor.
     * @param id Dato Long que representa el Id del Autor.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Permite establecer el nombre del Autor.
     * @return Dato String que representa el nombre del Autor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite establecer el nombre del Autor.
     * @param nombre Dato String que representa el nombre del Autor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Permite obtener el apellido paterno del Autor.
     * @return Dato String que representa el apellido paterno del Autor.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Permite establecer el apellido paterno del Autor.
     * @param apellidoPaterno Dato String que representa el apellido paterno del Autor.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Permite obtener el apellido materno del Autor.
     * @return Dato String que representa el apellido materno del autor.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Permite establecer el apellido materno del Autor.
     * @param apellidoMaterno Dato String que representa el apellido materno del autor.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    
    
    
}
