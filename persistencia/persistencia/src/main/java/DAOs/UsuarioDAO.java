/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import com.persistencia.ManejadorConexiones;
import entidades.Administrador;
import entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author chris
 */
public class UsuarioDAO {

    public Usuario iniciarSesion(String correo, String contrasenia) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.correo = :correo AND u.contrasenia = :contra",
                    Usuario.class
            );
            query.setParameter("correo", correo);
            query.setParameter("contra", contrasenia);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Usuario obtenerUsuarioPorCorreo(String correo) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE LOWER(u.correo) = LOWER(:correo)",
                    Usuario.class
            );
            query.setParameter("correo", correo);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
