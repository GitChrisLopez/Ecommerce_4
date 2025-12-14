
package DAOs;

import com.persistencia.ManejadorConexiones;
import definiciones.IAutoresDAO;
import entidades.Autor;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 
 * Archivo: AutoresDAO.java
 * 
 * Clase que realiza operaciones sobre la base de datos, específicamente sobre 
 * la entidad Autor.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 * 
 */
public class AutoresDAO implements IAutoresDAO{
    
    /**
     * Implementación del método registrarAutor() de la interfaz IAutoresDAO, 
     * que Permite el registro de un nuevo objeto de tipo Autor.
     * 
     * @param nuevoAutor Objeto Autor que contiene la información necesaria
     * para el registro del nuevo Autor.
     * @return Objeto Autor, es el nuevo objeto Autor que fue persistido
     * en la base de datos.
     * @throws PersistenciaException Se lanza si hay algún dato obligatorio faltante o si
     * ocurre un error al registrar el Autor.
     * 
     */
    @Override
    public Autor registrarAutor(Autor nuevoAutor) throws PersistenciaException {
        
         // Se valida que el nombre no sea nulo.
        if(nuevoAutor.getNombre() == null){
            throw new PersistenciaException("El autor que se intentó registrar no tiene nombre.");
        }
        
        // Se crea el objeto EntityManger
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            // Se crea la consulta JPQL para obtener los registro de la entidad
            // Autor que tenga el mismo nombre completo recibido para el nuevo registro.
            String jpqlQuery = """
                                SELECT a FROM Autor a
                                WHERE a.nombre = :nombre
                                AND a.apellidoPaterno = :apellidoPaterno
                                AND a.apellidoMaterno = :apellidoMaterno
                                """;

            // Se crea el objeto Query con la consulta
            Query query = entityManager.createQuery(jpqlQuery);

            // Se establecen los parámetros para la consulta.
            query.setParameter("nombre", nuevoAutor.getNombre());
            query.setParameter("apellidoPaterno", nuevoAutor.getApellidoPaterno());
            query.setParameter("apellidoMaterno", nuevoAutor.getApellidoMaterno());

            // Se obtiene la cantidad de Autores obtenidos.
            int cantidadAutoresMismoNombre = query.getResultList().size();

            // Si la cantidad de autores es mayor a 0, se lanza una excepción.
            if(cantidadAutoresMismoNombre > 0){
                throw new PersistenciaException("Ya existe un autor con el mismo nombre completo.");
            }

            // Se inicia una transacción para registrar el nuevo Autor.
            entityManager.getTransaction().begin();

            // Se persiste el nuevo Autor y finaliza la transacción.
            entityManager.persist(nuevoAutor);

            entityManager.getTransaction().commit();

            return nuevoAutor;

        } catch (PersistenceException e) {

            // Se maneja la excepción, asegurando rollback en caso de fallo.
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            // Se relanza la excepción.
            throw new PersistenciaException("Error al registrar el autor.");
        }
    }

    /**
     * Implementación del método consultarAutor() de la interfaz {@literal IAutoresDAO},
     * permite obtener un objeto Autor almacenado en la base de datos, cuyo valor
     * de atributo id sea el recibido como parámetro.
     * 
     * @param idAutor Objeto Long que representa el id del Autor buscado.
     * @return Objeto Autor que tiene el id del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Autor
     * almacenado con el valor de id del parámetro o si el valor del parámetro idAutor
     * es nulo.
     */
    @Override
    public Autor consultarAutor(Long idAutor) throws PersistenciaException {
        
        // Se valida el id recibido
        if(idAutor == null){
            throw new PersistenciaException("El Id utilizado para la consulta de autor tiene valor nulo.");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery<Autor>, representa la consulta que 
        // devolverá el objeto de tipo Autor.
        CriteriaQuery<Autor> criteriaQuery = criteraBuilder.createQuery(Autor.class);

        // Se obtiene la entidad desde la que se obtendrán los
        // resultados de la consulta.
        Root<Autor> entidadAutor= criteriaQuery.from(Autor.class);

        // Se seleccionan todos los atributos de la entidad Autor,
        // luego se obtienen solo los que tiene el valor del id del parámetro
        // como valor de su atributo id.
        criteriaQuery.select(entidadAutor).where(criteraBuilder.equal(entidadAutor.get("id"), idAutor));

        // Se crea el objeto TypedQuery<Autor>, representa la consulta ejecutable.
        TypedQuery<Autor> query = entityManager.createQuery(criteriaQuery);
        
        // Estructura try-catch para manejar la excepción NoResultException.
        try{
            // Se devuelve el objeto Autor coincidente.
            return query.getSingleResult();
            
        } catch(NoResultException ex){
            // En caso que no haya Autores coincidentes, se lanza una excepción.
            throw new PersistenciaException("No existe un autor con el Id especificado.");
        }
        
    }

    /**
     * Implementación del método consultarAutores() de la interfaz {@link IAutoresDAO}, 
     * permite obtener una lista de objetos Autor que tienen el nombre recibido, o parte de él.
     * 
     * @param nombre Objeto String que representa un nombre completo o parcial de un Autor.
     * @return Objeto {@literal List<Autor>} que representa la lista de Autores coincidentes.
     * @throws PersistenciaException Se lanza si ocurre un error al consultar las Autores.
     */
    @Override
    public List<Autor> consultarAutores(String nombre) throws PersistenciaException {
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Se crea el objeto CriteriaQuery, que devolverá objetos de tipo Autor.
        CriteriaQuery<Autor> criteriaQuery = criteriaBuilder.createQuery(Autor.class);

        // Se obtiene la entidad de la que se obtendrán los objetos
        Root<Autor> entidadAutor = criteriaQuery.from(Autor.class);

        // Filtro de nombre
        if (nombre != null && !nombre.trim().isEmpty()) {

            // Se eliminan espacios y se convierte a minúsculas.
            String terminoBusqueda = "%" + nombre.trim().toLowerCase().replace(" ", "") + "%";

            // Se convierten valores nulos a cadenas vacías para que la concatenación no falle.
            // Se une el nombre y el apellido paterno.
            Expression<String> nombreApellidoPaterno = criteriaBuilder.concat(
                criteriaBuilder.coalesce(entidadAutor.get("nombre"), ""), 
                criteriaBuilder.coalesce(entidadAutor.get("apellidoPaterno"), "")
            );

            // Se une lo anterior, al apellido materno.
            Expression<String> nombreCompleto = criteriaBuilder.concat(
                nombreApellidoPaterno, 
                criteriaBuilder.coalesce(entidadAutor.get("apellidoMaterno"), "")
            );

            // Predicado para determinar si el nombre completo contiene el nombre buscado.
            Predicate predicado = criteriaBuilder.like(
                criteriaBuilder.lower(nombreCompleto), 
                terminoBusqueda
            );

            criteriaQuery.where(predicado);
        }

        // Se seleccionan todos los atributos de Autor, y se ordenan en orden alfabético por el apellido paterno.
        criteriaQuery.select(entidadAutor).orderBy(criteriaBuilder.asc(entidadAutor.get("apellidoPaterno")));

        // Se crea el objeto TypedQuery<Autor>, es la consulta ejecutable.
        TypedQuery<Autor> query = entityManager.createQuery(criteriaQuery);

        try {
            // Ejecución de la consulta en try-catch
            List<Autor> autores = query.getResultList();

            return autores;

        } catch (PersistenceException e) {
            // Se relanza la excepción.
            throw new PersistenciaException("Error al consultar la lista de autores.");
        }
    }

    /**
     * Implementación del método consultarAutorez() de la interfaz {@literal IAutoresDAO}, 
     * permite obtener la lista de Autores almacenada en la base de datos.
     * 
     * @return Objeto {@literal List<Autor>} que representa la lista de Autores.
     * consultadas.
     * @throws PersistenciaException Se lanza si ocurre un error al realizar la consulta
     * de Autores.
     */
    @Override
    public List<Autor> consultarAutores() throws PersistenciaException {
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery,que representa la consulta, devolverá
        // objetos de tipo Autor.
        CriteriaQuery<Autor> criteriaQuery = criteraBuilder.createQuery(Autor.class);
        
        // Se obtiene la entidad de la que se obtendrán los objetos, en este caso
        // Autor.
        Root<Autor> entidadAutor = criteriaQuery.from(Autor.class);
        
        // Se seleccionan todos los atributos de Autor, los resultados se ordenarán
        // en orden alfabético.
        criteriaQuery.select(entidadAutor).orderBy(criteraBuilder.asc(entidadAutor.get("apellidoPaterno")));
        
        // Se crea el objeto TypedQuery<Autor>, es la consulta ejecutable.
        TypedQuery<Autor> query = entityManager.createQuery(criteriaQuery);
        
        try {
            // Ejecución de la consulta en try-catch
            List<Autor> autores = query.getResultList();

            return autores;

        } catch (PersistenceException e) {
            // Se relanza la excepción.
            throw new PersistenciaException("Error al consultar la lista de autores.");
        }
    }

    
    /**
     * Implementación del método actualizarAutor() de la interfaz {@literal IAutoresDAO}, 
     * permite actualizar los valores de los campos de una Autor almacenada.
     * 
     * @param autorActualizado Objeto Autor que contiene los nuevos valores
     * del nuevo Autor a modificar.
     * @throws PersistenciaException Se lanza si algún valor actualizado es nulo.
     */
    @Override
    public void actualizarAutor(Autor autorActualizado) throws PersistenciaException {
        
        // Se valida que el id no sea nulo.
        if(autorActualizado.getId() == null){
            throw new PersistenciaException("El Id recibido para la actualización del autor es nulo.");
        }
        
        // Se valida que el nombre no sea nulo.
        if(autorActualizado.getNombre() == null){
            throw new PersistenciaException("El nombre recibido para la actualización del autor es nulo.");
        }
        
        // Se valida que el apellido paterno no sea nulo.
        if(autorActualizado.getApellidoPaterno()== null){
            throw new PersistenciaException("El apellido paterno recibido para la actualización del autor es nulo.");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            // Se inicia una transacción para realizar la actualización del Autor.
            entityManager.getTransaction().begin();

            // Se comprueba que el Autor exista
            String jpqlQueryValidarId = """
                                         SELECT a FROM Autor a
                                         WHERE a.id = :id
                                         """;

            // Se crea el objeto TypedQuery<Autor>, es la consulta ejecutable.
            TypedQuery<Autor> queryValidarId = entityManager.createQuery(jpqlQueryValidarId, Autor.class);

            // Se establece el valor de id del parámetro a la consulta
            queryValidarId.setParameter("id", autorActualizado.getId());

            // Estructura try catch para determinar si se lanza la excepción NoResultException
            try{
                // Se obtiene el resultado de la consulta
                queryValidarId.getSingleResult();

            } catch(NoResultException ex){
                // Se lanza una excepción en caso de que el objeto Autor no exista
                throw new PersistenciaException("No existe un autor con el Id especificado.");
            }

            // Se determina si existe otro autor con el mismo nombre completo.
            // Se añade la condición de que sea distinto al id del Autor del parámetro para no 
            // considerar al objeto Autor a actualizar.
            String jpqlQuery = """
                                SELECT a FROM Autor a
                                WHERE a.nombre = :nombre
                                AND a.apellidoPaterno = :apellidoPaterno
                                AND a.apellidoMaterno = :apellidoMaterno
                                AND a.id != :id
                                """;

            // Se crea una consulta Query
            Query query = entityManager.createQuery(jpqlQuery);

            // Se establecen los parámetros del nombre completo y id para la consulta
            query.setParameter("nombre", autorActualizado.getNombre());
            query.setParameter("apellidoPaterno", autorActualizado.getApellidoPaterno());
            query.setParameter("apellidoMaterno", autorActualizado.getApellidoMaterno());
            query.setParameter("id", autorActualizado.getId());

            // Se obtienen la cantidad de Autores resultantes de la consulta
            int cantidadAutoresMismoNombre = query.getResultList().size();

            // Se verifica si hay otros Autores con el mismo nombre
            if(cantidadAutoresMismoNombre > 0){
                // Se lanza una excepción en caso de que sí existan
                throw new PersistenciaException("Ya existe un Autor con el mismo nombre completo.");
            }

            // Se actualiza la entidad con el método merge de EntityManager
            entityManager.merge(autorActualizado);

            // La transacción finaliza
            entityManager.getTransaction().commit();

        } catch (PersistenceException ex) {
            // Se asegura rollback de la transacción si está activa
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            throw new PersistenciaException("Error al actualizar el autor");
        }
    }
}
