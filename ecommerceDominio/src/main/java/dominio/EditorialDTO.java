
package dominio;

/**
 * Archivo: Editorial.java
 * 
 * Clas que representa una Editorial que publica libros.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 * 
 * Fecha: 15/11/2025
 * 
 */
public class EditorialDTO {
    
    /**
     * Dato Long que representa el Id de la editorial.
     */
    private Long id;
    
    /**
     * Objeto String que representa el nombre de la Editorial.
     */
    private String nombre;

    /**
     * Constructor por defecto.
     */
    public EditorialDTO(){
    }
    
    /**
     * Constructor que permite crear una Editorial, recibe los valores de todos
     * sus atributos.
     * @param id Dato Long que representa el Id de la Editorial.
     * @param nombre Objeto String que representa el nombre de la Editorial.
     */
    public EditorialDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Permite obetner el Id de la Editorial.
     * @return Dato Long que representa el Id de la Editorial.
     */
    public Long getId() {
        return id;
    }

    /**
     * Permite establecer el Id de la Editorial.
     * @param id Dato Long que representa el Id de la Editorial.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Permite obtener el nombre de la Editorial.
     * @return Objeto String que representa el nombre de la Editorial.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite establecer el nombre de la Editorial.
     * @param nombre Objeto String que representa el nombre de la Editorial.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
