
package entidades;

import enumeradores.Formato;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Archivo: Resenia.java
 *
 * Clase que representa una resenia hecha por un usuario sobre un libro.
 * Contiene calificacion, comentario, fecha y referencias al libro y usuario.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */
@Entity
@Table(name = "resenias")
public class Resenia {

    /**
     * Identificador unico de la resenia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resenia")
    private Long id;

    /**
     * Libro al que pertenece la resenia.
     */
    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;

    /**
     * Formato del Producto al que hace referencia la reseña.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "formato", nullable = false)
    private Formato formato;

    /**
     * Calificacion numerica dada por el usuario.
     */
    @Column(name = "calificacion", nullable = false)
    private int calificacion;

    /**
     * Comentario del usuario sobre el libro.
     */
    @Column(name = "comentario", length = 350, nullable = false)
    private String comentario;

    /**
     * Fecha en que se publico la resenia.
     */
    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fecha;

    /**
     * Usuario que realizo la resenia.
     */
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    /**
     * Constructor por defecto
     */
    public Resenia() {

    }

    /**
     * Crea una nueva resenia con todos sus datos.
     *
     * @param id identificador unico de la resenia
     * @param libro libro al que pertenece la resenia
     * @param formato formato del producto al que hace referencia la reseña.
     * @param calificacion valor numerico de la calificacion
     * @param comentario comentario del usuario
     * @param fecha fecha de publicacion
     * @param cliente cliente que realizo la resenia
     */
    public Resenia(
            Long id,
            Libro libro,
            Formato formato,
            int calificacion,
            String comentario,
            LocalDateTime fecha,
            Cliente cliente) {

        this.id = id;
        this.libro = libro;
        this.formato = formato;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    /**
     * Constructor que inicializa todos los atributos menos el id
     *
     * @param libro libro al que pertenece la resenia
     * @param formato formato del producto al que hace referencia la reseña.
     * @param calificacion valor numerico de la calificacion
     * @param comentario comentario del usuario
     * @param fecha fecha de publicacion
     * @param cliente cliente que realizo la resenia
     */
    public Resenia(Libro libro, Formato formato, int calificacion, String comentario, LocalDateTime fecha, Cliente cliente) {
        this.libro = libro;
        this.formato = formato;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    /**
     * @return identificador de la resenia.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id identificador al que pertenece la resenia
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return libro reseñado
     */
    public Libro getLibro() {
        return libro;
    }

    /**
     * @param libro libro al que pertenece la resenia
     */
    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    /**
     *
     * @return formato del producto al que hace referencia la reseña.
     */
    public Formato getFormato() {
        return formato;
    }

    /**
     *
     * @param formato formato del producto al que hace referencia la reseña.
     */
    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    /**
     * @return calificacion del usuario
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion nueva calificacion
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return comentario del usuario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario nuevo comentario
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return fecha de publicacion
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * @param fecha nueva fecha de publicacion
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * @return cliente que escribio la resenia
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente cliente que escribio la resenia
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Regresa una cadena de texto con los atributos de la Resenia.
     * @return cadena de texto.
     */
    @Override
    public String toString() {
        return "Resenia{" + "id=" + id + ", libro=" + libro + ", formato=" + formato + ", calificacion=" + calificacion + ", comentario=" + comentario + ", fecha=" + fecha + ", cliente=" + cliente + '}';
    }

}
