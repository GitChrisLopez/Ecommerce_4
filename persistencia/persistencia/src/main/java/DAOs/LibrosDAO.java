package DAOs;

import com.persistencia.ManejadorConexiones;
import definiciones.ILibrosDAO;
import entidades.Libro;
import excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * Archivo: LibrosDAO.java
 *
 * Clase que realiza operaciones sobre la base de datos, específicamente sobre
 * la entidad Libro.
 *
 * @author Romo López Manuel ID: 00000253080
 *
 * Fecha: 18/11/2025
 *
 */
public class LibrosDAO implements ILibrosDAO {

    /**
     * Implementación del método registrarLibro() de la interfaz ILibrosDAO, que
     * Permite el registro de un nuevo objeto de tipo Libro.
     *
     * @param nuevoLibro Objeto Libro que contiene la información necesaria para
     * el registro del nuevo Libro.
     * @return Objeto Libro, es el nuevo objeto Libro que fue persistido en la
     * base de datos.
     * @throws PersistenciaException Se lanza si hay algún dato obligatorio
     * faltante o si ocurre un error al registrar el Libro.
     *
     */
    @Override
    public Libro registrarLibro(Libro nuevoLibro) throws PersistenciaException {

        // Se valida que el título no sea nulo.
        if (nuevoLibro.getTitulo() == null) {
            throw new PersistenciaException("El libro que se intentó registrar no tiene título.");
        }

        // Se valida que el Autor no sea nulo.
        if (nuevoLibro.getAutor() == null) {
            throw new PersistenciaException("El libro que se intentó registrar no tiene autor.");
        }

        // Se valida que el Autor tenga Id.
        if (nuevoLibro.getAutor().getId() == null) {
            throw new PersistenciaException("El autor asociado al libro no tiene un Id válido.");
        }

        // Se valida que la categoria no sea nula.
        if (nuevoLibro.getCategoria() == null) {
            throw new PersistenciaException("El libro que se intentó registrar no tiene categoría.");
        }

        // Se valida que la editorial no sea nula.
        if (nuevoLibro.getEditorial() == null) {
            throw new PersistenciaException("El libro que se intentó registrar no tiene editorial.");
        }

        // Se valida que la fecha de publicación no sea nula.
        if (nuevoLibro.getFechaPublicacion() == null) {
            throw new PersistenciaException("El libro que se intentó registrar no tiene fecha de publicación.");
        }

        // Se valida que la sinopsis no sea nula.
        if (nuevoLibro.getSinopsis() == null) {
            throw new PersistenciaException("El libro que se intentó registrar no tiene sinopsis.");
        }

        // Se crea el objeto EntityManger
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        try {

            // Se crea la consulta JPQL para obtener los registro de la entidad
            // Libro que tenga el mismo nombre y autor que el recibido para el nuevo registro.
            String jpqlQuery = """
                                SELECT l FROM Libro l
                                WHERE l.titulo = :titulo
                                AND l.autor.id = :autorId
                                """;

            // Se crea el objeto Query con la consulta
            TypedQuery<Libro> query = entityManager.createQuery(jpqlQuery, Libro.class);

            // Se establecen los parámetros de titulo y id de autor para la consulta.
            query.setParameter("titulo", nuevoLibro.getTitulo());
            query.setParameter("autorId", nuevoLibro.getAutor().getId());

            // Se obtiene la cantidad de Libros obtenidos.
            int cantidadLibrosMismoTituloAutor = query.getResultList().size();

            // Si la cantidad de libros es mayor a 0, se lanza una excepción.
            if (cantidadLibrosMismoTituloAutor > 0) {
                throw new PersistenciaException("Ya existe un libro con el mismo título y Autor.");
            }

            // Se inicia una transacción para registrar el nuevo Libro.
            entityManager.getTransaction().begin();

            // Se persiste el nuevo Libro y finaliza la transacción.
            entityManager.persist(nuevoLibro);

            entityManager.getTransaction().commit();

            return nuevoLibro;

        } catch (PersistenceException e) {

            // Se maneja la excepción, asegurando rollback en caso de fallo.
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al registrar el libro.");
        }
    }

    /**
     * Implementación del método consultarLibro() de la interfaz
     * {@literal ILibrosDAO}, permite obtener un objeto Libro almacenado en la
     * base de datos, cuyo valor de atributo id sea el recibido como parámetro.
     *
     * @param idLibro Objeto Long que representa el id del Libro buscado.
     * @return Objeto Libro que tiene el id del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Libro
     * almacenado con el valor de id del parámetro o si el valor del parámetro
     * idLibro es nulo.
     */
    @Override
    public Libro consultarLibro(Long idLibro) throws PersistenciaException {

        // Se valida el id recibido
        if (idLibro == null) {
            throw new PersistenciaException("El Id utilizado para la consulta de libro tiene valor nulo.");
        }

        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();

        // Se crea el objeto CriteriaQuery<Libro>, representa la consulta que 
        // devolverá el objeto de tipo Libro.
        CriteriaQuery<Libro> criteriaQuery = criteraBuilder.createQuery(Libro.class);

        // Se obtiene la entidad desde la que se obtendrán los
        // resultados de la consulta.
        Root<Libro> entidadLibro = criteriaQuery.from(Libro.class);

        // Se indica que se quieren obtener las categorías del libro.
        entidadLibro.fetch("categorias", JoinType.INNER);
        
        // Se seleccionan todos los atributos de la entidad Libro,
        // luego se obtienen solo los que tiene el valor del id del parámetro
        // como valor de su atributo id.
        criteriaQuery.select(entidadLibro).where(criteraBuilder.equal(entidadLibro.get("id"), idLibro)).distinct(true);

        // Se crea el objeto TypedQuery<Libro>, representa la consulta ejecutable.
        TypedQuery<Libro> query = entityManager.createQuery(criteriaQuery);

        // Estructura try-catch para manejar la excepción NoResultException.
        try {
            // Se devuelve el objeto Libro coincidente.
            return query.getSingleResult();

        } catch (NoResultException ex) {
            // En caso que no haya Libro coincidentes, se lanza una excepción.
            throw new PersistenciaException("No existe un libro con el Id especificado.");
        }

    }
    
    /**
     * Implementación del método consultarLibro() de la interfaz
     * {@literal ILibrosDAO}, permite obtener un objeto Libro almacenado en la
     * base de datos, cuyo valor de atributo titulo sea el recibido como parámetro.
     *
     * @param titulo Objeto String que representa el título del Libro buscado.
     * @return Objeto Libro que tiene el título del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Libro
     * almacenado con el valor de título del parámetro o si el valor del parámetro
     * titulo es nulo.
     */
    @Override
    public Libro consultarLibro(String titulo) throws PersistenciaException {

        // Se valida el título recibido
        if (titulo == null) {
            throw new PersistenciaException("El título utilizado para la consulta de libro tiene valor nulo.");
        }

        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();

        // Se crea el objeto CriteriaQuery<Libro>, representa la consulta que 
        // devolverá el objeto de tipo Libro.
        CriteriaQuery<Libro> criteriaQuery = criteraBuilder.createQuery(Libro.class);

        // Se obtiene la entidad desde la que se obtendrán los
        // resultados de la consulta.
        Root<Libro> entidadLibro = criteriaQuery.from(Libro.class);

        // Se seleccionan todos los atributos de la entidad Libro,
        // luego se obtienen solo los que tiene el valor del título del parámetro
        // como valor de su atributo titulo.
        criteriaQuery.select(entidadLibro).where(criteraBuilder.equal(entidadLibro.get("titulo"), titulo));

        // Se crea el objeto TypedQuery<Libro>, representa la consulta ejecutable.
        TypedQuery<Libro> query = entityManager.createQuery(criteriaQuery);

        // Estructura try-catch para manejar la excepción NoResultException.
        try {
            // Se devuelve el objeto Libro coincidente.
            return query.getSingleResult();

        } catch (NoResultException ex) {
            // En caso que no haya Libro coincidentes, se lanza una excepción.
            throw new PersistenciaException("No existe un libro con el título especificado.");
        }

    }

    /**
     * Implementación del método consultarLibros() de la interfaz
     * {@link ILibrosDAO}, permite obtener una lista de objetos Libro que tienen
     * el nombre recibido, o parte de él.
     *
     * @param titulo Objeto String que representa el título completo o parcial
     * de un Libro.
     * @return Objeto {@literal List<Libro>} que representa la lista de Libros
     * coincidentes.
     * @throws PersistenciaException Se lanza si ocurre un error al consultar
     * los Libros.
     */
    @Override
    public List<Libro> consultarLibros(String titulo) throws PersistenciaException {

        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Se crea el objeto CriteriaQuery, que devolverá objetos de tipo Libro.
        CriteriaQuery<Libro> criteriaQuery = criteriaBuilder.createQuery(Libro.class);

        // Se obtiene la entidad de la que se obtendrán los objetos
        Root<Libro> entidadLibro = criteriaQuery.from(Libro.class);

        // Filtro de nombre
        if (titulo != null && !titulo.trim().isEmpty()) {

            // El nombre se convierte a minúsculas y se añaden comodines.
            String patronBusqueda = "%" + titulo.trim().toLowerCase() + "%";

            // Se crea el predicado.
            Predicate predicadoTitulo = criteriaBuilder.like(
                    criteriaBuilder.lower(entidadLibro.get("titulo")),
                    patronBusqueda
            );

            // Se aplica el predicado a la consulta
            criteriaQuery.where(predicadoTitulo);
        }

        // Se seleccionan todos los atributos de Libro, y se ordenan en orden alfabético.
        criteriaQuery.select(entidadLibro)
                .orderBy(criteriaBuilder.asc(entidadLibro.get("titulo")));

        // Se crea el objeto TypedQuery<Libro>, es la consulta ejecutable.
        TypedQuery<Libro> query = entityManager.createQuery(criteriaQuery);

        try {
            // Ejecución de la consulta en try-catch
            List<Libro> libros = query.getResultList();

            return libros;

        } catch (PersistenceException e) {
            // Se relanza la excepción.
            throw new PersistenciaException("Error al consultar la lista de libros.");
        }
    }

    /**
     * Implementación del método consultarLibros() de la interfaz
     * {@literal ILibrosDAO}, permite obtener la lista de Libros almacenada en
     * la base de datos.
     *
     * @return Objeto {@literal List<Libro>} que representa la lista de Libros
     * consultadas.
     * @throws PersistenciaException Se lanza si ocurre un error al realizar la
     * consulta de Libros.
     */
    @Override
    public List<Libro> consultarLibros() throws PersistenciaException {

        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();

        // Se crea el objeto CriteriaQuery,que representa la consulta, devolverá
        // objetos de tipo Libro.
        CriteriaQuery<Libro> criteriaQuery = criteraBuilder.createQuery(Libro.class);

        // Se obtiene la entidad de la que se obtendrán los objetos, en este caso
        // Libro.
        Root<Libro> entidadLibro = criteriaQuery.from(Libro.class);

        // Se seleccionan todos los atributos de Libro, los resultados se ordenarán
        // en orden alfabético.
        criteriaQuery.select(entidadLibro).orderBy(criteraBuilder.asc(entidadLibro.get("titulo")));

        // Se crea el objeto TypedQuery<Libro>, es la consulta ejecutable.
        TypedQuery<Libro> query = entityManager.createQuery(criteriaQuery);

        try {
            // Ejecución de la consulta en try-catch
            List<Libro> libros = query.getResultList();

            return libros;

        } catch (PersistenceException e) {
            // Se relanza la excepción.
            throw new PersistenciaException("Error al consultar la lista de libros.");
        }
    }

    /**
     * Implementación del método actualizarLibro() de la interfaz
     * {@literal ILibrosDAO}, permite actualizar los valores de los campos de un
     * Libro almacenado.
     *
     * @param libroActualizado Objeto Libro que contiene los nuevos valores del
     * nuevo Libro a modificar.
     * @throws PersistenciaException Se lanza si algún valor actualizado es
     * nulo.
     */
    @Override
    public void actualizarLibro(Libro libroActualizado) throws PersistenciaException {
        
        // Se valida que el id no sea nulo.
        if (libroActualizado.getId() == null) {
            throw new PersistenciaException("El Id recibido para la actualización del libro es nulo.");
        }

        // Se valida que el Autor no sea nulo.
        if (libroActualizado.getAutor() == null) {
            throw new PersistenciaException("El autor recibido para la actualización del libro es nulo.");
        }
        
        // Se valida que el Autor tenga Id.
        if (libroActualizado.getAutor().getId() == null) {
            throw new PersistenciaException("El id del autor recibido para la actualización del libro es nulo.");   
        }

        // Se valida que la categoria no sea nula.
        if (libroActualizado.getCategoria() == null) {
            throw new PersistenciaException("La categoría recibida para la actualización del libro es nula.");
        }
        
        // Se valida que la editorial no sea nula.
        if (libroActualizado.getEditorial() == null) {
            throw new PersistenciaException("La editorial recibida para la actualización del libro es nula.");
        }


        // Se valida que la fecha de publicación no sea nula.
        if (libroActualizado.getFechaPublicacion() == null) {
            throw new PersistenciaException("La fecha de publicación recibida para la actualización del libro es nula.");
        }
        
        // Se valida que la sinopsis no sea nula.
        if (libroActualizado.getSinopsis() == null) {
            throw new PersistenciaException("La sinopsis recibida para la actualización del libro es nula.");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se inicia el bloque try-catch para manejar las excepciones de persistencia
        // y asegurar integridad transaccional.
        
        try {
            // Se inicia una transacción para realizar la actualización del Libro.
            entityManager.getTransaction().begin();

            // Se comprueba que el Libro exista.
            String jpqlQueryValidarId = """
                                     SELECT l FROM Libro l
                                     WHERE l.id = :id
                                     """;

            // Se crea el objeto TypedQuery<Libro>, es la consulta ejecutable.
            TypedQuery<Libro> queryValidarId = entityManager.createQuery(jpqlQueryValidarId, Libro.class);

            // Se establece el valor de id del parámetro a la consulta
            queryValidarId.setParameter("id", libroActualizado.getId());

            // Estructura try catch para determinar si se lanza la excepción NoResultException
            try {
                // Se obtiene el resultado de la consulta
                queryValidarId.getSingleResult();
                
            } catch (NoResultException ex) {
                // Se lanza una excepción en caso de que el objeto Libro no exista
                throw new PersistenciaException("No existe un libro con el Id especificado.");
            }

            // Se determina si existe otro libro con el mismo título y Autor.
            // Se añade la condición de que se distinto al id del Libro del parámetro para no 
            // considerar al objeto Libro a actualizar.
            String jpqlQuery = """
                            SELECT l FROM Libro l
                            WHERE l.titulo = :titulo
                            AND l.autor.id = :autorId  
                            AND l.id != :id
                            """;

            // Se crea una consulta Query
            Query query = entityManager.createQuery(jpqlQuery);

            // Se establecen los parámetros de título, id de Autor y id para la consulta
            query.setParameter("titulo", libroActualizado.getTitulo());
                query.setParameter("autorId", libroActualizado.getAutor().getId());
                query.setParameter("id", libroActualizado.getId());

            // Se obtienen la cantidad de Libros resultantes de la consulta
            int cantidadLibrosMismoTituloAutor = query.getResultList().size();

            // Se verifica si hay otros Libros con el mismo título y Autor
            if (cantidadLibrosMismoTituloAutor > 0) {
                // Se lanza una excepción en caso de que sí existan
                throw new PersistenciaException("Ya existe un Libro con el mismo título y autor.");
            }

        // Se actualiza la entidad con el merge de EntityManager
        entityManager.merge(libroActualizado);

            // La transacción finaliza
            entityManager.getTransaction().commit();

        } catch (PersistenceException ex) {
            // Se asegura rollback de la transacción si está activa
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            
            throw new PersistenciaException("Error al actualizar el libro");
        }
    }
}
