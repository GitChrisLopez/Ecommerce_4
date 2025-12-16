package dominio;

import java.time.LocalDateTime;

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
public class ReseniaDTO {

    /**
     * Identificador unico de la resenia.
     */
    private Long id;

    /**
     * Libro al que pertenece la resenia.
     */
    private LibroDTO libro;

    /**
     * Formato del Producto al que hace referencia la reseña.
     */
    private FormatoDTO formato;

    /**
     * Calificacion numerica dada por el usuario.
     */
    private int calificacion;

    /**
     * Comentario del usuario sobre el libro.
     */
    private String comentario;

    /**
     * Fecha en que se publico la resenia.
     */
    private LocalDateTime fecha;

    /**
     * Usuario que realizo la resenia.
     */
    private ClienteDTO cliente;

    /**
     * Constructor por defecto
     */
    public ReseniaDTO() {

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
    public ReseniaDTO(
            Long id,
            LibroDTO libro,
            FormatoDTO formato,
            int calificacion,
            String comentario,
            LocalDateTime fecha,
            ClienteDTO cliente) {

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
    public ReseniaDTO(LibroDTO libro, FormatoDTO formato, int calificacion, String comentario, LocalDateTime fecha, ClienteDTO cliente) {
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
    public LibroDTO getLibro() {
        return libro;
    }

    /**
     * @param libro libro al que pertenece la resenia
     */
    public void setLibro(LibroDTO libro) {
        this.libro = libro;
    }

    /**
     *
     * @return formato del producto al que hace referencia la reseña.
     */
    public FormatoDTO getFormato() {
        return formato;
    }

    /**
     *
     * @param formato formato del producto al que hace referencia la reseña.
     */
    public void setFormato(FormatoDTO formato) {
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
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * @param cliente cliente que escribio la resenia
     */
    public void setCliente(ClienteDTO cliente) {
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
