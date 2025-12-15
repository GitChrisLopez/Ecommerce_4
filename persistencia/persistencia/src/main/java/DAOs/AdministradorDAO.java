/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import com.persistencia.ManejadorConexiones;
import entidades.Administrador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

/**
 * 
 * @author chris
 */
public class AdministradorDAO {

    public Administrador iniciarSesion(String correo, String contrasenia) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            // jPQL para buscar un Administrador
            TypedQuery<Administrador> query = em.createQuery(
                    "SELECT a FROM Administrador a WHERE a.correo = :correo AND a.contrasenia = :contra",
                    Administrador.class
            );
            query.setParameter("correo", correo);
            query.setParameter("contra", contrasenia);

            return query.getSingleResult();
        } catch (NoResultException e) {
            // No se encontro ningun admin
            return null;
        } finally {
            em.close();
        }
    }
}
