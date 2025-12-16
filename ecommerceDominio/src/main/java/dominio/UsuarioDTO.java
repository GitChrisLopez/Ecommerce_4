package dominio;

/**
 * 
 * Archivo: Usuario.java
 * 
 * Clase abstracta que representa a un Usuario del sistema. Puede ser un Cliente
 * o un Administrador.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 * 
 * Fecha: 15/10/2025
 * 
 */

public abstract class UsuarioDTO {

    /**
     * Dato Long que representa el Id del usuario.
     */
    private Long id;

    /**
     * Dato String que representa el o los nombres del Usuario.
     */
    private String nombre;
    
    /**
     * Dato String que representa el apellido paterno del Usuario.
     */
    private String apellidoPaterno;
    
    /**
     * Dato String que representa el apellido materno del Usuario.
     */
    private String apellidoMaterno;

    /**
     * Dato String que representa el correo electrónico del Usuario.
     */
    private String correo;

    /**
     * Dato String que representa el hash de la contraseña del usuario.
     */
    private String contrasenia;

    /**
     * Constructor por defecto.
     */
    public UsuarioDTO(){    
    }
    
    /**
     * Constructor que permite crear un Usuario con todos los datos de sus atributos.
     * @param id Dato int que representa el Id del Usuario.
     * @param nombre Objeto String que representa el o los nombres del Usuario.
     * @param apellidoPaterno Objeto String que representa el apellido paterno del Usuario.
     * @param apellidoMaterno Objeto String que representa el apellido materno del Usuario.
     * @param correo Objeto String que representa el correo electrónico del Usuario.
     * @param contrasenia Objeto String que representa el hash de la contraseña del Usuario.
     * @param activo Dato boolean que determina si el Usuario está activo o no.
     */
    public UsuarioDTO(
            Long id, 
            String nombre, 
            String apellidoPaterno,
            String apellidoMaterno, 
            String correo, 
            String contrasenia) {
        
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    /**
     * Permite obtener el Id del Usuario.
     * @return Dato Long que representa el Id del Usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Permite establecer el Id del Usuario.
     * @param id Dato Long que representa el Id del Usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Permite obtener el o los nombres del Usuario.
     * @return Objeto String que representa el o los nombres del Usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite establecer el o los nombres del Usuario.
     * @param nombre Objeto String que representa el o los nombres del Usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Permite obtener el apellido paterno del Usuario.
     * @return Objeto String que representa el apellido paterno de Usuario.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Permite establecer el apellido paterno del Usuario.
     * @param apellidoPaterno Objeto String que representa el apellido paterno de Usuario.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Permite obtener el apellido materno del Usuario.
     * @return Objeto String que representa el apellido materno de Usuario.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Permite establecer el apellido materno del Usuario.
     * @param apellidoPaterno Objeto String que representa el apellido materno de Usuario.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Permite obtener el correo electrónico del Usuario.
     * @return Objeto String que representa el correo electrónico del Usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Permite establecer el correo electrónico del Usuario.
     * @param correo Objeto String que representa el correo electrónico del Usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Permite obtener el hash de la contraseña del Usuario.
     * @return Objeto String que representa el hash de la contraseña del Usuario.
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Permite obtene el hash de la contraseña del Usuario.
     * @param contrasenia Objeto String que representa el hash de la contraseña del Usuario.
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    

}
