
package entidades;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import entidades.Usuario;

/**
 * Archivo: Administrador.java 
 * 
 * Clase que representa a un usuario con privilegios de
 * administrador en el sistema.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */

@Entity
@Table(name = "administradores")
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {

    /**
     * Constructor por defecto.
     */
    public Administrador(){
        
    }
    
    /**
     * Constructor para inicializar una instancia de Administrador. Llama al
     * constructor de la superclase (Usuario) para establecer los datos básicos.
     *
     * @param id Dato Long que representa el id del Administrador.
     * @param nombre Objeto String que representa el nombre del Administrador.
     * @param correo Objeto String que representa el correo electrónico del
     * Administrador.
     * @param contrasena Objeto String que representa la contraseña del
     * Administrador.
     */
    public Administrador(
            Long id,
            String nombre, 
            String apellidoPaterno, 
            String apellidoMaterno,
            String correo, 
            String contrasena) {
        
        super(
                id, 
                nombre, 
                apellidoPaterno,
                apellidoMaterno, 
                correo, 
                contrasena);
    }

}
