
package DAOs;

import com.persistencia.ManejadorConexiones;
import definiciones.IEditorialesDAO;
import entidades.Editorial;
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
 * Archivo: EditorialesDAO.java
 * 
 * Clase que realiza operaciones sobre la base de datos, específicamente sobre 
 * la entidad Editorial.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 * 
 */
public class EditorialesDAO implements IEditorialesDAO{

    /**
     * Implementación del método registrarEditorial() de la interfaz IEditorialesDAO, 
     * que Permite el registro de un nuevo objeto de tipo Editorial.
     * 
     * @param nuevaEditorial Objeto Editorial que contiene la información necesaria
     * para el registro de la nueva Editorial.
     * @return Objeto Editorial, es el nuevo objeto Editorial que fue persistido
     * en la base de datos.
     * @throws PersistenciaException Se lanza si hay algún dato obligatorio faltante o si
     * ocurre un error al registrar la Editorial.
     * 
     */
    @Override
    public Editorial registrarEditorial(Editorial nuevaEditorial) throws PersistenciaException {
        
         // Se valida que el nombre no sea nulo.
        if(nuevaEditorial.getNombre() == null){
            throw new PersistenciaException("La editorial que se intentó registrar no tiene nombre.");
        }
        
        // Se crea el objeto EntityManger.
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            // Se crea la consulta JPQL para obtener los registro de la entidad
            // Editorial que tenga el mismo nombre recibido para el nuevo registro.
            String jpqlQuery = """
                                SELECT e FROM Editorial e
                                WHERE e.nombre = :nombre
                                """;

            // Se crea el objeto Query con la consulta
            Query query = entityManager.createQuery(jpqlQuery);

            // Se establecen el parámetro de nombre para la consulta.
            query.setParameter("nombre", nuevaEditorial.getNombre());

            // Se obtiene la cantidad de Editoriales obtenidas.
            int cantidadEditorialesMismoNombre = query.getResultList().size();

            // Si la cantidad de editoriales es mayor a 0, se lanza una excepción.
            if(cantidadEditorialesMismoNombre > 0){
                throw new PersistenciaException("Ya existe una editorial con el mismo nombre.");
            }

            // Se inicia una transacción para registrar la nueva Editorial.
            entityManager.getTransaction().begin();

            // Se persiste la nueva Editorial y finaliza la transacción.
            entityManager.persist(nuevaEditorial);

            entityManager.getTransaction().commit();

            return nuevaEditorial;

        } catch (PersistenceException e) {

            // Se maneja la excepción, asegurando rollback en caso de fallo.
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            // Se relanza la excepción.
            throw new PersistenciaException("Error al registrar la editorial.");
        }
    }

    /**
     * Implementación del método consultarEditorial() de la interfaz {@literal IEditorialesDAO},
     * permite obtener un objeto Editorial almacenado en la base de datos, cuyo valor
     * de atributo id sea el recibido como parámetro.
     * 
     * @param idEditorial Objeto Long que representa el id de la Editorial buscada.
     * @return Objeto Editorial que tiene el id del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Editorial
     * almacenado con el valor de id del parámetro o si el valor del parámetro idEditorial
     * es nulo.
     */
    @Override
    public Editorial consultarEditorial(Long idEditorial) throws PersistenciaException {
        
        // Se valida el id recibido
        if(idEditorial == null){
            throw new PersistenciaException("El Id utilizado para la consulta de editorial tiene valor nulo.");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery<Editorial>, representa la consulta que 
        // devolverá el objeto de tipo Editorial.
        CriteriaQuery<Editorial> criteriaQuery = criteraBuilder.createQuery(Editorial.class);

        // Se obtiene la entidad desde la que se obtendrán los
        // resultados de la consulta.
        Root<Editorial> entidadEditorial = criteriaQuery.from(Editorial.class);

        // Se seleccionan todos los atributos de la entidad Editorial,
        // luego se obtienen solo los que tiene el valor del id del parámetro
        // como valor de su atributo id.
        criteriaQuery.select(entidadEditorial).where(criteraBuilder.equal(entidadEditorial.get("id"), idEditorial));

        // Se crea el objeto TypedQuery<Editorial>, representa la consulta ejecutable.
        TypedQuery<Editorial> query = entityManager.createQuery(criteriaQuery);
        
        // Estructura try-catch para manejar la excepción NoResultException.
        try{
            // Se devuelve el objeto Editorial coincidente.
            return query.getSingleResult();
            
        } catch(NoResultException ex){
            // En caso que no haya Editoriales coincidentes, se lanza una excepción.
            throw new PersistenciaException("No existe una editorial con el Id especificado.");
        }
        
    }

    /**
     * Implementación del método consultarEditoriales() de la interfaz {@link IEditorialesDAO}, 
     * permite obtener una lista de objetos Editorial que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de una Editorial.
     * @return Objeto {@literal List<Editorial>} que representa la lista de Editoriales coincidentes.
     * @throws PersistenciaException Se lanza si ocurre un error al consultar las Editoriales.
     */
    @Override
    public List<Editorial> consultarEditoriales(String nombre) throws PersistenciaException {
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Se crea el objeto CriteriaQuery, que devolverá objetos de tipo Editorial.
        CriteriaQuery<Editorial> criteriaQuery = criteriaBuilder.createQuery(Editorial.class);

        // Se obtiene la entidad de la que se obtendrán los objetos
        Root<Editorial> entidadEditorial = criteriaQuery.from(Editorial.class);

        // Filtro de nombre
        if (nombre != null && !nombre.trim().isEmpty()) {

            // El nombre se convierte a minúsculas y se añaden comodines.
            String patronBusqueda = "%" + nombre.trim().toLowerCase() + "%";

            // Se crea el predicado.
            Predicate predicadoNombre = criteriaBuilder.like(
                criteriaBuilder.lower(entidadEditorial.get("nombre")),
                patronBusqueda
            );

            // Se aplica el predicado a la consulta
            criteriaQuery.where(predicadoNombre);
        }

        // Se seleccionan todos los atributos de Editorial, y se ordenan en orden alfabético.
        criteriaQuery.select(entidadEditorial)
                     .orderBy(criteriaBuilder.asc(entidadEditorial.get("nombre")));

        // Se crea el objeto TypedQuery<Editorial>, es la consulta ejecutable.
        TypedQuery<Editorial> query = entityManager.createQuery(criteriaQuery);

        try {
            // Ejecución de la consulta en try-catch
            List<Editorial> editoriales = query.getResultList();

            return editoriales;

        } catch (PersistenceException e) {
            // Se relanza la excepción.
            throw new PersistenciaException("Error al consultar la lista de editoriales.");
        }
    }

    /**
     * Implementación del método consultarEditoriales() de la interfaz {@literal IEditorialesDAO}, 
     * permite obtener la lista de Editoriales almacenada en la base de datos.
     * 
     * @return Objeto {@literal List<Editorial>} que representa la lista de Editoriales
     * consultadas.
     * @throws PersistenciaException Se lanza si ocurre un error al realizar la consulta
     * de Editoriales.
     */
    @Override
    public List<Editorial> consultarEditoriales() throws PersistenciaException {
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery,que representa la consulta, devolverá
        // objetos de tipo Editorial.
        CriteriaQuery<Editorial> criteriaQuery = criteraBuilder.createQuery(Editorial.class);
        
        // Se obtiene la entidad de la que se obtendrán los objetos, en este caso
        // Editorial.
        Root<Editorial> entidadEditorial = criteriaQuery.from(Editorial.class);
        
        // Se seleccionan todos los atributos de Editorial, los resultados se ordenarán
        // en orden alfabético.
        criteriaQuery.select(entidadEditorial).orderBy(criteraBuilder.asc(entidadEditorial.get("nombre")));
        
        // Se crea el objeto TypedQuery<Editorial>, es la consulta ejecutable.
        TypedQuery<Editorial> query = entityManager.createQuery(criteriaQuery);
        
        try {
            // Ejecución de la consulta en try-catch
            List<Editorial> editoriales = query.getResultList();

            return editoriales;

        } catch (PersistenceException e) {
            // Se relanza la excepción.
            throw new PersistenciaException("Error al consultar la lista de editoriales.");
        }
    }

    
    /**
     * Implementación del método actualizarEditorial() de la interfaz {@literal IEditorialesDAO}, 
     * permite actualizar los valores de los campos de una Editorial almacenada.
     * 
     * @param editorialActualizada Objeto Editorial que contiene los nuevos valores
     * de la nueva Editorial a modificar.
     * @throws PersistenciaException Se lanza si algún valor actualizado es nulo.
     */
    @Override
    public void actualizarEditorial(Editorial editorialActualizada) throws PersistenciaException {
        
        // Se valida que el id no sea nulo.
        if(editorialActualizada.getId() == null){
            throw new PersistenciaException("El Id recibido para la actualización de la editorial es nulo.");
        }
        
        // Se valida que el nombre no sea nulo.
        if(editorialActualizada.getNombre() == null){
            throw new PersistenciaException("El nombre recibido para la actualización de la editorial es nulo.");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            // Se inicia una transacción para realizar la actualización de la Editorial.
            entityManager.getTransaction().begin();

            // Se comprueba que la Editorial exista
            String jpqlQueryValidarId = """
                                         SELECT e FROM Editorial e
                                         WHERE e.id = :id
                                         """;

            // Se crea el objeto TypedQuery<Editorial>, es la consulta ejecutable.
            TypedQuery<Editorial> queryValidarId = entityManager.createQuery(jpqlQueryValidarId, Editorial.class);

            // Se establece el valor de id del parámetro a la consulta
            queryValidarId.setParameter("id", editorialActualizada.getId());

            // Estructura try catch para determinar si se lanza la excepción NoResultException
            try{
                // Se obtiene el resultado de la consulta
                queryValidarId.getSingleResult();

            } catch(NoResultException ex){
                // Se lanza una excepción en caso de que el objeto Editorial no exista
                throw new PersistenciaException("No existe una editorial con el Id especificado.");
            }

            // Se determina si existe otra editorial con el mismo nombre.
            // Se añade la condición de que sea distinto al id de la Editorial del parámetro para no 
            // considerar al objeto Editorial a actualizar.
            String jpqlQuery = """
                                SELECT e FROM Editorial e
                                WHERE e.nombre = :nombre
                                AND e.id != :id
                                """;

            // Se crea una consulta Query
            Query query = entityManager.createQuery(jpqlQuery);

            // Se establecen los parámetros de nombre y id para la consulta
            query.setParameter("nombre", editorialActualizada.getNombre());
            query.setParameter("id", editorialActualizada.getId());

            // Se obtienen la cantidad de Editoriales resultantes de la consulta
            int cantidadEditorialesMismoNombre = query.getResultList().size();

            // Se verifica si hay otras Editoriales con el mismo nombre
            if(cantidadEditorialesMismoNombre > 0){
                // Se lanza una excepción en caso de que sí existan
                throw new PersistenciaException("Ya existe una Editorial con el mismo nombre.");
            }

            // Se actualiza la entidad con el método merge de EntityManager
            entityManager.merge(editorialActualizada);

            // La transacción finaliza
            entityManager.getTransaction().commit();

        } catch (PersistenceException ex) {
            // Se asegura rollback de la transacción si está activa
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw new PersistenciaException("Error al actualizar la editorial");
        }
    }
    
}
