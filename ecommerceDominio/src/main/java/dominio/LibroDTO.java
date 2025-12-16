package dominio;

import java.time.LocalDate;
import java.util.List;

/**
 * Archivo: Libro.java
 * 
 * Clase que representa un Libro dentro del catálogo.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 * 
 * Fecha: 15/10/2025
 * 
 */

public class LibroDTO {

    /**
     * Dato Long que representa el Id del Libro.
     */
    private Long id;

    /**
     * Objeto String que representa el título del Libro.
     */
    private String titulo;

    /**
     * Objeto String que representa la sinopsis del Libro.
     */
    private String sinopsis;

    /**
     * Objeto List<Categoria> que representa las categorías del Libro.
     */
    private List<CategoriaDTO> categorias;

    /**
     * Objeto Autor que representa el Autor de este Libro.
     */
    private AutorDTO autor;
    
    /**
     * Objeto Editorial que representa la editorial que publicó el Libro.
     */
    private EditorialDTO editorial;

    /**
     * Objeto LocalDate que representa la fecha de publicación del Libro.
     */
    private LocalDate fechaPublicacion;
    
    /**
     * Constructor por defecto.
     */
    public LibroDTO(){
        
    }

    /**
     * Constructor que permite crear un Libro, recibe todos los valores de los
     * parámetros de la clase.
     * @param id Dato Long que representa el Id del Libro.
     * @param titulo Dato String que representa el título del Libro.
     * @param sinopsis Objeto String que representa la sinopsis del Libro.
     * @param categoria Objeto Categoria que representa la categoría del Libro.
     * @param autor Objeto Autor que representa el Autor del Libro.
     * @param editorial Objeto Editorial que representa la Editorial del Libro.
     * @param fechaPublicacion Objeto LocalDate que representa la fecha de publicación del Libro.
     */
    public LibroDTO(
            Long id,
            String titulo,
            String sinopsis,
            List<CategoriaDTO> categoria,
            AutorDTO autor, 
            EditorialDTO editorial,
            LocalDate fechaPublicacion) {
        
        this.id = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.categorias = categoria;
        this.autor = autor;
        this.editorial = editorial;
        this.fechaPublicacion = fechaPublicacion;
    }
    
    /**
     * Permite obtener el Id del Libro.
     * @return Dato Long que representa el Id del Libro.
     */
    public Long getId() {
        return id;
    }

    /**
     * Permite establecer el Id dl Libro.
     * @param id Dato Long que representa el Id del Libro.
     */
    public void setId(Long id) {
        this.id = id;
    }    
    
    /**
     * Permite obtener el título del Libro.
     * @return Objeto String que representa el título del Libro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Pemrite establecer el título del Libro.
     * @param titulo Objeto String que representa el título del Libro.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Permite obtener la sinopsis del Libro.
     * @return Objeto String que representa la sinopsis del Libro.
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * Permite establecer la sinopsis del Libro.
     * @param sinopsis Objeto String que representa la sinopsis del Libro.
     */
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }


    /**
     * Permite obtener las categorías del Libro.
     * @return Objeto List<Categoria> que representa la categoría del Libro.
     */
    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    /**
     * Permite establecer las categorías del Libro.
     * @param categoria Objeto List<Categoria> que representa la categoría del Libro.
     */
    public void setCategorias(List<CategoriaDTO> categoria) {
        this.categorias = categoria;
    }

    /**
     * Permite obtener la editorial que publicó el Libro.
     * @return Objeto Editorial que representa la editorial que publicó el Libro.
     */
    public EditorialDTO getEditorial() {
        return editorial;
    }

    /**
     * Permite establecer la editorial que publicó el Libro.
     * @param editorial Objeto Editorial que representa la editorial que publicó el Libro.
     */
    public void setEditorial(EditorialDTO editorial) {
        this.editorial = editorial;
    }

    /**
     * Permite obtener el Autor del Libro.
     * @return Objeto Autor que representa el autor del Libro.
     */
    public AutorDTO getAutor() {
        return autor;
    }

    /**
     * Permite establecer el Autor del Libro.
     * @param autor Objeto Autor que representa el autor del Libro.
     */
    public void setAutor(AutorDTO autor) {
        this.autor = autor;
    }

    /**
     * Permite obtener la fecha de publicación del Libro.
     * @return Objeto LocalDate que representa la fecha de publicación del Libro.
     */
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Permite establecer la fecha de publicación del Libro.
     * @param fechaPublicacion Objeto LocalDate que representa la fecha de publicación del Libro.
     */
    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }


}
