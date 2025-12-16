
package enumeradores;

/**
 *
 * Estado.java Enumerador que representa el estado de un Pedido realizado por un
 * Cliente.
 *
 * @author Norma Alicia Beltrán Martín - 252102
 * @author Oscar Adrián Castán López - 260318
 * @author Chris Fitch Lopez - 252379
 * @author Manuel Romo López - 253080
 *
 * Fecha: 15/10/2025
 */
public enum Estado {
    /**
     * Pedido pendiente que aún no se ha preparado.
     */
    PENDIENTE,
    /**
     * Pedido que ha sido enviado al Cliente.
     */
    ENVIADO,
    /**
     * Pedido que fue entregado al Cliente.
     */
    ENTREGADO,
    /**
     * Pedido que fue cancelado.
     */
    CANCELADO
}
