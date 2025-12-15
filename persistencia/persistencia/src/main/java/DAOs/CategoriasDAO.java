
package DAOs;

import com.persistencia.ManejadorConexiones;
import definiciones.ICategoriasDAO;
import entidades.Categoria;
import excepciones.PersistenciaException;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * 
 * Archivo: CategoriasDAO.java
 * 
 * Clase que realiza operaciones sobre la base de datos, específicamente sobre 
 * la entidad Categoria.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 * 
 */
public class CategoriasDAO implements ICategoriasDAO{
    /**
     * Implementación del método registrarCategoria() de la interfaz ICategoriasDAO, 
     * que Permite el registro de un nuevo objeto de tipo Categoria.
     * 
     * @param nuevaCategoria Objeto Categoria que contiene la información necesaria
     * para el registro de la nueva Categoria.
     * @return Objeto Categoria, es el nuevo objeto Categoria que fue persistido
     * en la base de datos.
     * @throws PersistenciaException Se lanza si hay algún dato obligatorio faltante o si
     * ocurre un error al registrar la Categoria.
     * 
     */
    @Override
    public Categoria registrarCategoria(Categoria nuevaCategoria) throws PersistenciaException {
        
         // Se valida que el nombre no sea nulo.
        if(nuevaCategoria.getNombre() == null){
            throw new PersistenciaException("La categoría que se intentó registrar no tiene nombre.");
        }
        
        // Se crea el objeto EntityManger.
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            // Se crea la consulta JPQL para obtener los registro de la entidad
            // Categoria que tenga el mismo nombre recibido para el nuevo registro.
            String jpqlQuery = """
                                SELECT c FROM Categoria c
                                WHERE c.nombre = :nombre
                                """;

            // Se crea el objeto Query con la consulta
            Query query = entityManager.createQuery(jpqlQuery);

            // Se establecen el parámetro de nombre para la consulta.
            query.setParameter("nombre", nuevaCategoria.getNombre());

            // Se obtiene la cantidad de Categorías obtenidas.
            int cantidadCategoriasMismoNombre = query.getResultList().size();

            // Si la cantidad de categorías es mayor a 0, se lanza una excepción.
            if(cantidadCategoriasMismoNombre > 0){
                throw new PersistenciaException("Ya existe una categoría con el mismo nombre.");
            }

            // Se inicia una transacción para registrar la nueva Categoria.
            entityManager.getTransaction().begin();

            // Se persiste la nueva Categoria y finaliza la transacción.
            entityManager.persist(nuevaCategoria);

            entityManager.getTransaction().commit();

            return nuevaCategoria;

        } catch (PersistenceException e) {

            // Se maneja la excepción, asegurando rollback en caso de fallo.
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            // Se relanza la excepción.
            throw new PersistenciaException("Error al registrar la categoría.");
        }
    }

    /**
     * Implementación del método consultarCategoria() de la interfaz {@literal ICategoriasDAO},
     * permite obtener un objeto Categoria almacenado en la base de datos, cuyo valor
     * de atributo id sea el recibido como parámetro.
     * 
     * @param idCategoria Objeto Long que representa el id de la Categoria buscada.
     * @return Objeto Categoria que tiene el id del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Categoria
     * almacenado con el valor de id del parámetro o si el valor del parámetro idCategoria
     * es nulo.
     */
    @Override
    public Categoria consultarCategoria(Long idCategoria) throws PersistenciaException {
        
        // Se valida el id recibido
        if(idCategoria == null){
            throw new PersistenciaException("El Id utilizado para la consulta de categoría tiene valor nulo.");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery<Categoria>, representa la consulta que 
        // devolverá el objeto de tipo Categoria.
        CriteriaQuery<Categoria> criteriaQuery = criteraBuilder.createQuery(Categoria.class);

        // Se obtiene la entidad desde la que se obtendrán los
        // resultados de la consulta.
        Root<Categoria> entidadCategoria= criteriaQuery.from(Categoria.class);

        // Se seleccionan todos los atributos de la entidad Categoria,
        // luego se obtienen solo los que tiene el valor del id del parámetro
        // como valor de su atributo id.
        criteriaQuery.select(entidadCategoria).where(criteraBuilder.equal(entidadCategoria.get("id"), idCategoria));

        // Se crea el objeto TypedQuery<Categoria>, representa la consulta ejecutable.
        TypedQuery<Categoria> query = entityManager.createQuery(criteriaQuery);
        
        // Estructura try-catch para manejar la excepción NoResultException.
        try{
            // Se devuelve el objeto Categoria coincidente.
            return query.getSingleResult();
            
        } catch(NoResultException ex){
            // En caso que no haya Categorías coincidentes, se lanza una excepción.
            throw new PersistenciaException("No existe una categoría con el Id especificado.");
        }
        
    }

    /**
     * Implementación del método consultarCategorias() de la interfaz {@link ICategoriasDAO}, 
     * permite obtener una lista de objetos Categoria que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de una Categoria.
     * @return Objeto {@literal List<Categoria>} que representa la lista de Categorías coincidentes.
     * @throws PersistenciaException Se lanza si ocurre un error al consultar las Categorías.
     */
    @Override
    public List<Categoria> consultarCategorias(String nombre) throws PersistenciaException {
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Se crea el objeto CriteriaQuery, que devolverá objetos de tipo Categoria.
        CriteriaQuery<Categoria> criteriaQuery = criteriaBuilder.createQuery(Categoria.class);

        // Se obtiene la entidad de la que se obtendrán los objetos
        Root<Categoria> entidadCategoria = criteriaQuery.from(Categoria.class);

        // Filtro de nombre
        if (nombre != null && !nombre.trim().isEmpty()) {

            // El nombre se convierte a minúsculas y se añaden comodines.
            String patronBusqueda = "%" + nombre.trim().toLowerCase() + "%";

            // Se crea el predicado.
            Predicate predicadoNombre = criteriaBuilder.like(
                criteriaBuilder.lower(entidadCategoria.get("nombre")),
                patronBusqueda
            );

            // Se aplica el predicado a la consulta
            criteriaQuery.where(predicadoNombre);
        }

        // Se seleccionan todos los atributos de Categoria, y se ordenan en orden alfabético.
        criteriaQuery.select(entidadCategoria)
                     .orderBy(criteriaBuilder.asc(entidadCategoria.get("nombre")));

        // Se crea el objeto TypedQuery<Categoria>, es la consulta ejecutable.
        TypedQuery<Categoria> query = entityManager.createQuery(criteriaQuery);

        try {
            // Ejecución de la consulta en try-catch
            List<Categoria> categorias = query.getResultList();

            return categorias;

        } catch (PersistenceException e) {
            // Se relanza la excepción.
            throw new PersistenciaException("Error al consultar la lista de categorías.");
        }
    }

    /**
     * Implementación del método consultarCategorias() de la interfaz {@literal ICategoriasDAO}, 
     * permite obtener la lista de Categorias almacenada en la base de datos.
     * 
     * @return Objeto {@literal List<Categoria>} que representa la lista de Categorías.
     * consultadas.
     * @throws PersistenciaException Se lanza si ocurre un error al realizar la consulta
     * de Categorías.
     */
    @Override
    public List<Categoria> consultarCategorias() throws PersistenciaException {
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery,que representa la consulta, devolverá
        // objetos de tipo Categoria.
        CriteriaQuery<Categoria> criteriaQuery = criteraBuilder.createQuery(Categoria.class);
        
        // Se obtiene la entidad de la que se obtendrán los objetos, en este caso
        // Categoria.
        Root<Categoria> entidadCategoria = criteriaQuery.from(Categoria.class);
        
        // Se seleccionan todos los atributos de Categoria, los resultados se ordenarán
        // en orden alfabético.
        criteriaQuery.select(entidadCategoria).orderBy(criteraBuilder.asc(entidadCategoria.get("nombre")));
        
        // Se crea el objeto TypedQuery<Categoria>, es la consulta ejecutable.
        TypedQuery<Categoria> query = entityManager.createQuery(criteriaQuery);
        
        try {
            // Ejecución de la consulta en try-catch
            List<Categoria> categorias = query.getResultList();

            return categorias;

        } catch (PersistenceException e) {
            // Se relanza la excepción.
            throw new PersistenciaException("Error al consultar la lista de categorías.");
        }
    }

    
    /**
     * Implementación del método actualizarCategoria() de la interfaz {@literal ICategoriasDAO}, 
     * permite actualizar los valores de los campos de una Categoria almacenada.
     * 
     * @param categoriaActualizada Objeto Categoria que contiene los nuevos valores
     * de la nueva Categoria a modificar.
     * @throws PersistenciaException Se lanza si algún valor actualizado es nulo.
     */
    @Override
    public void actualizarCategoria(Categoria categoriaActualizada) throws PersistenciaException {
        
        // Se valida que el id no sea nulo.
        if(categoriaActualizada.getId() == null){
            throw new PersistenciaException("El Id recibido para la actualización de la categoría es nulo.");
        }
        
        // Se valida que el nombre no sea nulo.
        if(categoriaActualizada.getNombre() == null){
            throw new PersistenciaException("El nombre recibido para la actualización de la categoría es nulo.");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se inicia el bloque try-catch para manejar las excepciones de persistencia.
        try {
            // Se inicia una transacción para realizar la actualización de la Categoria.
            entityManager.getTransaction().begin();

            // Se comprueba que la Categoria exista
            String jpqlQueryValidarId = """
                                         SELECT c FROM Categoria c
                                         WHERE c.id = :id
                                         """;
 
            // Se crea el objeto TypedQuery<Categoria>, es la consulta ejecutable.
            TypedQuery<Categoria> queryValidarId = entityManager.createQuery(jpqlQueryValidarId, Categoria.class);

            // Se establece el valor de id del parámetro a la consulta
            queryValidarId.setParameter("id", categoriaActualizada.getId());

            // Estructura try catch para determinar si se lanza la excepción NoResultException
            try{
                // Se obtiene el resultado de la consulta
                queryValidarId.getSingleResult();

            } catch(NoResultException ex){
                // Se lanza una excepción en caso de que el objeto Categoria no exista
                throw new PersistenciaException("No existe una categoría con el Id especificado.");
            }

            // Se determina si existe otra categoría con el mismo nombre.
            // Se añade la condición de que se distinto al id de la Categoria del parámetro para no 
            // considerar al objeto Categoria a actualizar.
            String jpqlQuery = """
                                SELECT c FROM Categoria c
                                WHERE c.nombre = :nombre
                                AND c.id != :id
                                """;

            // Se crea una consulta Query
            Query query = entityManager.createQuery(jpqlQuery);

            // Se establecen los parámetros de nombre y id para la consulta
            query.setParameter("nombre", categoriaActualizada.getNombre());
            query.setParameter("id", categoriaActualizada.getId());

            // Se obtienen la cantidad de Cateogiras resultantes de la consulta
            int cantidadCategoriasMismoNombre = query.getResultList().size();

            // Se verifica si hay otras Categorías con el mismo nombre
            if(cantidadCategoriasMismoNombre > 0){
                // Se lanza una excepción en caso de que sí existan
                throw new PersistenciaException("Ya existe una Categoria con el mismo nombre.");
            }

            // Se actualiza la entidad con el método merge de EntityManager
            entityManager.merge(categoriaActualizada);

            // La transacción finaliza.
            entityManager.getTransaction().commit();

        } catch (PersistenceException ex) {
            // Se asegura rollback de la transacción si está activa
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw new PersistenciaException("Error al actualizar la categoría.");
        }
    }
}
