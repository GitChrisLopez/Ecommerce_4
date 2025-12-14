
package entidades;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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

@Entity
@Table(name = "libros")
public class Libro {

    /**
     * Dato Long que representa el Id del Libro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_libro")
    private Long id;

    /**
     * Objeto String que representa el título del Libro.
     */
    @Column (name = "titulo", length = 100, nullable = false)
    private String titulo;

    /**
     * Objeto String que representa la sinopsis del Libro.
     */
    @Column (name = "sinopsis", length = 350, nullable = false) 
    private String sinopsis;

    /**
     * Objeto List<Categoria> que representa las categorías del Libro.
     */
    @ManyToMany
    @JoinTable(
        name = "libro_categoria",
        joinColumns = @JoinColumn(name = "id_libro"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private List<Categoria> categorias;

    /**
     * Objeto Autor que representa el Autor de este Libro.
     */
    @ManyToOne
    @JoinColumn (name = "id_autor", nullable = false) 
    private Autor autor;
    
    /**
     * Objeto Editorial que representa la editorial que publicó el Libro.
     */
    @ManyToOne
    @JoinColumn (name = "id_editorial", nullable = false) 
    private Editorial editorial;

    /**
     * Objeto LocalDate que representa la fecha de publicación del Libro.
     */
    @Column (name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;
    
    /**
     * Constructor por defecto.
     */
    public Libro(){
        
    }

    /**
     * Constructor que permite crear un Libro, recibe todos los valores de los
     * parámetros de la clase.
     * @param id Dato Long que representa el Id del Libro.
     * @param titulo Dato String que representa el título del Libro.
     * @param precio Dato double que representa el precio del Libro.
     * @param sinopsis Objeto String que representa la sinopsis del Libro.
     * @param categoria Objeto Categoria que representa la categoría del Libro.
     * @param autor Objeto Autor que representa el Autor del Libro.
     * @param editorial Objeto Editorial que representa la Editorial del Libro.
     * @param fechaPublicacion Objeto LocalDate que representa la fecha de publicación del Libro.
     */
    public Libro(
            Long id,
            String titulo,
            String sinopsis,
            List<Categoria> categoria,
            Autor autor, 
            Editorial editorial,
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
    public List<Categoria> getCategoria() {
        return categorias;
    }

    /**
     * Permite establecer las categorías del Libro.
     * @param categoria Objeto List<Categoria> que representa la categoría del Libro.
     */
    public void setCategoria(List<Categoria> categoria) {
        this.categorias = categoria;
    }

    /**
     * Permite obtener la editorial que publicó el Libro.
     * @return Objeto Editorial que representa la editorial que publicó el Libro.
     */
    public Editorial getEditorial() {
        return editorial;
    }

    /**
     * Permite establecer la editorial que publicó el Libro.
     * @param editorial Objeto Editorial que representa la editorial que publicó el Libro.
     */
    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    /**
     * Permite obtener el Autor del Libro.
     * @return Objeto Autor que representa el autor del Libro.
     */
    public Autor getAutor() {
        return autor;
    }

    /**
     * Permite establecer el Autor del Libro.
     * @param autor Objeto Autor que representa el autor del Libro.
     */
    public void setAutor(Autor autor) {
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
