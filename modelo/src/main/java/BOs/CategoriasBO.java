
package BOs;

import definiciones.ICategoriasBO;
import definiciones.ICategoriasDAO;
import dominio.CategoriaDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.util.List;
import mappers.MapperCategoria;


/**
 *
 * Archivo: CategoriasBO.java
 * 
 * Clase que implementa la iterfaz {@link ICategoriasBO} y que realiza operaciones
 * de negocio sobre categorías.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 */
public class CategoriasBO implements ICategoriasBO{

    private ICategoriasDAO categoriasDAO;
    
    private int CARACTERES_MAXIMO_NOMBRE = 20;
    
    public CategoriasBO(ICategoriasDAO categoriasDAO){
        
        this.categoriasDAO = categoriasDAO;
    }

    /**
     * Implementación del método registrarCategoria() de la interfaz {@link ICategoriasBO}, 
     * permite registrar una nueva categoría.
     * 
     * @param nuevaCategoria Objeto DTO que contiene los valores de los atributos
     * necesarios para crear una nueva categoría.
     * @return Objeto Categoria, representa la categoría registrada.
     * @throws NegocioException Se lanza si algún atributo de la categoría recibido es nulo o
     * si ocurre una excepción al registrarla.
     */
    @Override
    public CategoriaDTO registrarCategoria(CategoriaDTO nuevaCategoria) throws NegocioException {
        
        // Se valida que nombre no es nulo o está vacío.
        if(nuevaCategoria.getNombre() == null || nuevaCategoria.getNombre().isBlank()){
            throw new NegocioException("La categoría debe tener un nombre.");
        }
        
        // Se valida que el nombre no exceda la cantidad máxima de caracteres.
        if(nuevaCategoria.getNombre().length() > CARACTERES_MAXIMO_NOMBRE){
            throw new NegocioException("El nombre de la categoría no debe superar los " + CARACTERES_MAXIMO_NOMBRE + " caracteres.");
        }
      
        try{
            
            // Se registra la categoría utilizando el objeto que implementa la interfaz ICategoriasDAO.
            return MapperCategoria.toDto(categoriasDAO.registrarCategoria(MapperCategoria.toEntity(nuevaCategoria)));
            
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Implementación del método consultarCategoria de la interfaz {@link ICategoriasBO}, 
     * permite obtener los datos de una categoría a partir de su Id.
     * 
     * @param idCategoria Dato Long que representa el Id de la categoría a consultar.
     * @return Objeto CategoriaDTO que contiene los datos de la categoría consultada.
     * @throws NegocioException Se lanza si se obtiene una excepción al consultar la categoría, o si 
     * el Id del parámetro es nulo.
     */
    @Override
    public CategoriaDTO consultarCategoria(Long idCategoria) throws NegocioException {
        
        // Se valida el Id de la categoría.
        if(idCategoria == null){
            throw new NegocioException("El Id de la categoría a consultar no puede ser nulo.");
        }
        
        try{
            return MapperCategoria.toDto(categoriasDAO.consultarCategoria(idCategoria));
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
    }

    /**
     * Implementación del método consultarCategorias() de la interfaz {@link ICateogirasBO}, 
     * permite obtener una lista categorías que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de una categoría.
     * @return Objeto {@literal List<CategoriaDTO>} que representa la lista de categorías coincidentes.
     * @throws NegocioException Se lanza si ocurre un error al consultar las categorías, o el valor del nombre
     * del parámetro es nulo.
     */
    @Override
    public List<CategoriaDTO> consultarCategorias(String nombre) throws NegocioException {
        
        // Se valida el nombre de la categoría.
        if(nombre == null){
            throw new NegocioException("El nombre de la categoría a consultar no puede ser nulo.");
        }
        
        try{
            return MapperCategoria.toDtoList(categoriasDAO.consultarCategorias(nombre));
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
        
    }

    /**
     * Implementación del método consultarCategorias() de la interfaz {@link ICategoriasBO}, 
     * permite obtener la lista de categorías registradas.
     * 
     * @return Objeto {@literal List<CategoriaDTO>} que representa la lista de categorías
     * consultadas.
     * @throws NegocioException Se lanza si ocurre un error al realizar la consulta
     * de categorías.
     */
    @Override
    public List<CategoriaDTO> consultarCategorias() throws NegocioException {
        
        try{
            return MapperCategoria.toDtoList(categoriasDAO.consultarCategorias());
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage());
        }
        
    }

    /**
     * Implementación del método actualizarCategoria() de la interfaz {@link ICategoriasBO}, 
     * permite actualizar una categoría.
     * 
     * @param categoriaActualizada Objeto Categoria que contiene los nuevos datos de una categoría.
     * @throws NegocioException Se lanza si algún valor actualizado es nulo u ocurre una excepción al actualizar la categoría.
     */
    @Override
    public void actualizarCategoria(CategoriaDTO categoriaActualizada) throws NegocioException {
        
        // Se valida que el Id no sea nulo.
        if(categoriaActualizada.getId() == null){
            throw new NegocioException("La categoría actualizada debe tener un Id.");
        }
        
        // Se valida que el nombre no sea nulo o está vacío.
        if(categoriaActualizada.getNombre() == null || categoriaActualizada.getNombre().isBlank()){
            throw new NegocioException("La categoría actualizada debe tener un nombre.");
        }
        
        // Se valida que el nombre no exceda la cantidad máxima de caracteres.
        if(categoriaActualizada.getNombre().length() > CARACTERES_MAXIMO_NOMBRE){
            throw new NegocioException("La categoría actualizada no debe tener un nombre que supere los " + CARACTERES_MAXIMO_NOMBRE + " caracteres.");
        }
        
        
        try {
            
            // Se actualiza la categoría utilizando el objeto que implementa 
            // la interfaz ICategoriasBO.
            categoriasDAO.actualizarCategoria(MapperCategoria.toEntity(categoriaActualizada));
            
        } catch (PersistenciaException ex) {
            
            throw new NegocioException(ex.getMessage());
        }
    }
    
}
