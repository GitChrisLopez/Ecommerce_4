package mappers;

import dominio.UsuarioDTO;
import entidades.Usuario;


/**
 * Mapper base que convierte un Objeto Entidad Usuario (abstracto) 
 * a un Objeto UsuarioDTO (abstracto), y viceversa.
 * Se utiliza para mapear los campos comunes de la herencia de Usuario.
 */
public abstract class MapperUsuario {
    
    /**
     * Convierte un objeto Entidad Usuario a un objeto UsuarioDTO.
     * Este método solo mapea los campos comunes de la clase abstracta.
     * @param entity La entidad Usuario.
     * @param dto El DTO del Usuario (ya instanciado con el tipo concreto).
     * @return El DTO del Usuario con los campos base mapeados.
     */
    protected static UsuarioDTO toDto(Usuario entity, UsuarioDTO dto) {
        if (entity == null || dto == null) {
            return null;
        }
        
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellidoPaterno(entity.getApellidoPaterno());
        dto.setApellidoMaterno(entity.getApellidoMaterno());
        dto.setCorreo(entity.getCorreo());
        //?? creo q no va en el dto para empezar
        dto.setContrasenia(entity.getContrasenia());
        
        return dto;
    }

    /**
     * Convierte un objeto UsuarioDTO a un objeto Entidad Usuario.
     * Este método solo mapea los campos comunes de la clase abstracta.
     * @param dto El DTO del Usuario.
     * @param entity La entidad Usuario (ya instanciada con el tipo concreto).
     * @return La entidad Usuario con los campos base mapeados.
     */
    protected static Usuario toEntity(UsuarioDTO dto, Usuario entity) {
        if (dto == null || entity == null) {
            return null;
        }

        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setApellidoPaterno(dto.getApellidoPaterno());
        entity.setApellidoMaterno(dto.getApellidoMaterno());
        entity.setCorreo(dto.getCorreo());
        //?? creo q no va en el dto para empezar
        entity.setContrasenia(dto.getContrasenia());
        
        return entity;
    }

}
