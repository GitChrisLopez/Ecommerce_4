package mappers;

import dominio.ClienteDTO;
import dtos.ClienteResponseDTO;
import entidades.Cliente;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper que convierte un Objeto Entidad Cliente a un Objeto ClienteDTO, y viceversa.
 * Extiende de MapperUsuario para manejar los campos heredados.
 */
public class MapperCliente extends MapperUsuario {

    /**
     * Convierte un objeto Entidad Cliente a un objeto ClienteDTO.
     *
     * @param entity La entidad Cliente.
     * @return El DTO del Cliente.
     */
    public static ClienteDTO toDto(Cliente entity) {
        if (entity == null) {
            return null;
        }

        ClienteDTO dto = new ClienteDTO();

        MapperUsuario.toDto(entity, dto);

        dto.setTelefono(entity.getTelefono());
        dto.setUrlImagenPerfil(entity.getUrlImagenPerfil());
        dto.setActivo(entity.isActivo());

//        if (entity.getCarrito() != null) {
//            dto.setCarrito(MapperCarrito.toDto(entity.getCarrito()));
//        }
        
        if (entity.getDirecciones() != null) {
            dto.setDirecciones(MapperDireccion.toDtoList(entity.getDirecciones()));
        }

//        if (entity.getPedidos() != null) {
//            dto.setPedidos(MapperPedido.toDtoList(entity.getPedidos()));
//        }

        return dto;
    }

    /**
     * Convierte una lista de entidades Cliente a una lista de ClienteDTO.
     *
     * @param entityList La lista de entidades Cliente.
     * @return La lista de DTOs.
     */
    public static List<ClienteDTO> toDtoList(List<Cliente> entityList) {
        return entityList.stream()
                .map(MapperCliente::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto ClienteDTO a un objeto Entidad Cliente.
     *
     * @param dto El DTO del Cliente.
     * @return La entidad Cliente.
     */
    public static Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }

        Cliente entity = new Cliente();

        MapperUsuario.toEntity(dto, entity);

        entity.setTelefono(dto.getTelefono());
        entity.setUrlImagenPerfil(dto.getUrlImagenPerfil());
        entity.setActivo(dto.isActivo());

//        if (dto.getCarrito() != null) {
//            entity.setCarrito(MapperCarrito.toEntity(dto.getCarrito()));
//        }
        if (dto.getDirecciones() != null) {
            entity.setDirecciones(MapperDireccion.toEntityList(dto.getDirecciones()));
        }

//        if (dto.getPedidos() != null) {
//            entity.setPedidos(MapperPedido.toEntityList(dto.getPedidos()));
//        }
        return entity;
    }

    /**
     * Convierte una lista de ClienteDTO a una lista de entidades Cliente.
     *
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades Cliente.
     */
    public static List<Cliente> toEntityList(List<ClienteDTO> dtoList) {
        return dtoList.stream()
                .map(MapperCliente::toEntity)
                .collect(Collectors.toList());
    }

    public static ClienteResponseDTO toResponseDTO(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }

        return new ClienteResponseDTO(
                dto.getId(),
                dto.getNombre(),
                dto.getApellidoPaterno(),
                dto.getApellidoMaterno(),
                dto.getCorreo(),
                dto.getTelefono(),
                dto.getUrlImagenPerfil(),
                dto.isActivo()
        );
    }

    public static ClienteDTO toClienteDTO(ClienteResponseDTO responseDTO) {
        if (responseDTO == null) {
            return null;
        }

        ClienteDTO dto = new ClienteDTO();

        dto.setId(responseDTO.getId());
        dto.setNombre(responseDTO.getNombre());
        dto.setApellidoPaterno(responseDTO.getApellidoPaterno());
        dto.setApellidoMaterno(responseDTO.getApellidoMaterno());
        dto.setCorreo(responseDTO.getCorreo());
        dto.setTelefono(responseDTO.getTelefono());
        dto.setUrlImagenPerfil(responseDTO.getUrlImagenPerfil());
        dto.setActivo(responseDTO.isActivo());

        return dto;
    }
}
