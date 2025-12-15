/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

@Entity
@Table(name = "categorias")
public class Categoria {

    /**
     * Dato Long que representa el Id de la Categoria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_categoria")
    private Long id;
    
    /**
     * Objeto String que representa el nombre de la Categoria.
     */
    @Column (name = "nombre", length = 20, nullable = false)
    private String nombre;

    /**
     * Constructor por defecto.
     */
    public Categoria(){
        
    }
    
    /**
     * Constructor que permite crear una Categoría, recibe los valores de todos
     * sus atributos.
     *
     * @param id Objeto Long que representa el Id de la Categoria.
     * @param nombre Objeto String que representa el nombre de la Categoria.
     */
    public Categoria(Long id, String nombre) {
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

