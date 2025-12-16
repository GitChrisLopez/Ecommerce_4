package dominio;

/**
 * Archivo: Categoria.java
 *
 * Clase que representa una categoría que puede tener un Libro.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 */
public class CategoriaDTO {

    /**
     * Dato Long que representa el Id de la Categoria.
     */
    private Long id;

    /**
     * Objeto String que representa el nombre de la Categoria.
     */
    private String nombre;

    /**
     * Constructor por defecto.
     */
    public CategoriaDTO() {

    }

    /**
     * Constructor que permite crear una Categoría, recibe los valores de todos
     * sus atributos.
     *
     * @param id Objeto Long que representa el Id de la Categoria.
     * @param nombre Objeto String que representa el nombre de la Categoria.
     */
    public CategoriaDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Metodo que obtiene el id de una categoria
     *
     * @return el ID que se obtuvo
     */
    public Long getId() {
        return id;
    }

    /**
     * Método establecer el id de una categoria
     * @param id el ID a declarar
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Metodo que obtiene el nombre de la categoria
     *
     * @return el nombre de la categoria buscada
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que declara el nombre de la categoria
     *
     * @param nombre el nombre a declarar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
