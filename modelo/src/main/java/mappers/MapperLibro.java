package mappers;

import dominio.LibroDTO;
import entidades.Libro;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper que convierte un Objeto Entidad Libro a un Objeto LibroDTO, y viceversa.
 * @author norma
 */
public class MapperLibro {
    
    /**
     * Convierte un objeto Entidad Libro a un objeto LibroDTO.
     * @param entity La entidad Libro.
     * @return El DTO del Libro.
     */
    public static LibroDTO toDto(Libro entity) {
        if (entity == null) {
            return null;
        }
        
        LibroDTO dto = new LibroDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setSinopsis(entity.getSinopsis());
        
        dto.setAutor(MapperAutor.toDto(entity.getAutor()));
        dto.setEditorial(MapperEditorial.toDto(entity.getEditorial()));
        dto.setCategorias(MapperCategoria.toDtoList(entity.getCategoria()));
        
        dto.setFechaPublicacion(entity.getFechaPublicacion());
        return dto;
    }

    /**
     * Convierte una lista de entidades Libro a una lista de LibroDTO.
     * @param entityList La lista de entidades Libro.
     * @return La lista de DTOs.
     */
    public static List<LibroDTO> toDtoList(List<Libro> entityList) {
        return entityList.stream()
                         .map(MapperLibro::toDto)
                         .collect(Collectors.toList());
    }

    /**
     * Convierte un objeto LibroDTO a un objeto Entidad Libro.
     * @param dto El DTO del Libro.
     * @return La entidad Libro.
     */
    public static Libro toEntity(LibroDTO dto) {
        if (dto == null) {
            return null;
        }

        Libro entity = new Libro();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setSinopsis(dto.getSinopsis());

        entity.setAutor(MapperAutor.toEntity(dto.getAutor()));
        entity.setEditorial(MapperEditorial.toEntity(dto.getEditorial()));
        entity.setCategoria(MapperCategoria.toEntityList(dto.getCategorias()));
        
        entity.setFechaPublicacion(dto.getFechaPublicacion());
        return entity;
    }

    /**
     * Convierte una lista de LibroDTO a una lista de entidades Libro.
     * @param dtoList La lista de DTOs.
     * @return La lista de entidades Libro.
     */
    public static List<Libro> toEntityList(List<LibroDTO> dtoList) {
        
        if(dtoList == null){
            return null;
        }
        
        return dtoList.stream()
                      .map(MapperLibro::toEntity)
                      .collect(Collectors.toList());
    }
    
    public static LibroDTO toDtoBasico(Libro entity) {
        if (entity == null) {
            return null;
        }
        
        LibroDTO dto = new LibroDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        
        if (entity.getAutor() != null) {

            dto.setAutor(MapperAutor.toDto(entity.getAutor())); 
        }
        return dto;

    }
}
