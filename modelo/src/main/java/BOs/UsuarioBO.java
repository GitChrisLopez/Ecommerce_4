/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import DAOs.UsuarioDAO;
import dominio.UsuarioDTO;
import mappers.MapperAdministrador;
import mappers.MapperCliente;
import entidades.Usuario;
import entidades.Cliente;
import entidades.Administrador;

/**
 *
 * @author chris
 */
public class UsuarioBO {

    private UsuarioDAO usuarioDAO;

    public UsuarioBO() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public UsuarioDTO iniciarSesion(String correo, String contrasenia) {
        if (correo == null || correo.isBlank() || contrasenia == null || contrasenia.isBlank()) {
            return null;
        }

        Usuario usuarioEntity = usuarioDAO.iniciarSesion(correo, contrasenia);

        if (usuarioEntity == null) {
            return null;
        }

        if(usuarioEntity instanceof Cliente) {
            return MapperCliente.toDto((Cliente) usuarioEntity);
        }
        else if (usuarioEntity instanceof Administrador) {
            // entidad a DTO con MapperAdministrador
            return MapperAdministrador.toDto((Administrador) usuarioEntity);
        }

        return null; // error
    }
}
