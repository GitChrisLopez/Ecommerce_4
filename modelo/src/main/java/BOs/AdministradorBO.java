package BOs;

import DAOs.AdministradorDAO;
import dominio.AdministradorDTO;
/**
 *
 * @author chris
 */
public class AdministradorBO {

    private AdministradorDAO adminDAO;

    public AdministradorBO() {
        this.adminDAO = new AdministradorDAO();
    }

    public AdministradorDTO iniciarSesion(String correo, String contrasenia) {

        if (correo == null || correo.isBlank() || contrasenia == null || contrasenia.isBlank()) {
            System.out.println("Correo o contraseña vacíos");
            return null;
        }

        entidades.Administrador adminEntity = adminDAO.iniciarSesion(correo, contrasenia);

        if (adminEntity == null) {
            return null;
        }
        // convertir ENTIDAD a DOMINIO
        return mappers.MapperAdministrador.toDto(adminEntity);
    }

}
