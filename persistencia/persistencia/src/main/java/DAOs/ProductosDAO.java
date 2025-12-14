
package DAOs;

import com.persistencia.ManejadorConexiones;
import definiciones.IProductosDAO;
import entidades.Autor;
import entidades.Categoria;
import entidades.Libro;
import entidades.Producto;
import enumeradores.Formato;
import excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 
 * Archivo: ProductosDAO.java
 * 
 * Clase que realiza operaciones sobre la base de datos, específicamente sobre 
 * la entidad Producto.
 * 
 * @author Romo López Manuel
 * ID: 00000253080
 * 
 * Fecha: 18/11/2025
 * 
 */
public class ProductosDAO implements IProductosDAO{
    /**
     * Implementación del método registrarProducto() de la interfaz {@link IProductosDAO}, 
     * que permite el registro de un nuevo objeto de tipo Producto.
     * @param nuevoProducto Objeto Producto que contiene la información necesaria
     * para el registro del nuevo Producto.
     * @return Objeto Producto, es el nuevo objeto Producto que fue persistido
     * en la base de datos.
     * @throws PersistenciaException Se lanza si hay algún dato obligatorio faltante o si
     * ocurre un error al registrar el Producto.
     * 
     */
    @Override
    public Producto registrarProducto(Producto nuevoProducto) throws PersistenciaException{
        
        // Se valida que el isbn no sea nulo.
        if(nuevoProducto.getIsbn() == null){
            throw new PersistenciaException("El producto que se intentó registrar no tiene ISBN.");
        }
        
        // Se valida que el precio no sea nulo.
        if(nuevoProducto.getPrecio() == null){
            throw new PersistenciaException("El producto que se intentó registrar no tiene precio.");
        }
        
        // Se valida que el stock no sea nulo.
        if(nuevoProducto.getStock() == null){
            throw new PersistenciaException("El producto que se intentó registrar no tiene stock.");
        }
        
        // Se valida que la dirección de imagen no sea nula
        if(nuevoProducto.getUrlImagen()== null){
            throw new PersistenciaException("El producto que se intentó registrar no tiene direccion de imagen.");
        }
        
        // Se valiada que el formato no sea nulo.
        if(nuevoProducto.getFormato() == null){
            throw new PersistenciaException("El producto que se intentó registrar no tiene formato.");
        }
        
        // Se crea el objeto EntityManger.
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        try {
            // Se crea la consulta JPQL para obtener los registro de la entidad
            // Producto que tenga el mismo isbn recibido para el nuevo registro.
            String jpqlQuery = """
                                SELECT p FROM Producto p
                                WHERE p.isbn = :isbn
                                """;

            // Se crea el objeto Query con la consulta
            Query query = entityManager.createQuery(jpqlQuery);

            // Se establecen el parámetro de isbn para la consulta.
            query.setParameter("isbn", nuevoProducto.getIsbn());

            // Se obtiene la cantidad de Productos obtenidos.
            int cantidadProductosMismoIsbn = query.getResultList().size();

            // Si la cantidad de productos es mayor a 0, se lanza una excepción.
            if(cantidadProductosMismoIsbn > 0){
                throw new PersistenciaException("Ya existe un producto con el mismo isbn.");
            }

            // Se inicia una transacción para registrar el nuevo Producto.
            entityManager.getTransaction().begin();

            // Se persiste el nuevo Producto y finaliza la transacción.
            entityManager.persist(nuevoProducto);

            entityManager.getTransaction().commit();

            return nuevoProducto;

        } catch (PersistenceException e) {

            // Se maneja la excepción, asegurando rollback en caso de fallo.
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            // Se relanza la excepción.
            throw new PersistenciaException("Error al registrar el producto.");
        }
    } 
    
    /**
     * Implementación del método consultarProducto(), de la interfaz {@link IProductosDAO},
     * permite obtener un objeto Producto almacenado en la base de datos, cuyo valor
     * de atributo id sea el recibido como parámetro.
     * @param idProducto Objeto Long que representa el id del Producto buscado.
     * @return Objeto Producto que tiene el id del parámetro.
     * @throws PersistenciaException Se lanza si no existe un objeto Producto
     * almacenado con el valor de id del parámetro o si el valor del parámetro idProducto
     * es nulo.
     */
    @Override
    public Producto consultarProducto(Long idProducto) throws PersistenciaException{
        
        // Se valida el id recibido
        if(idProducto == null){
            throw new PersistenciaException("El Id utilizado para la consulta de producto tiene valor nulo.");
        }
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteraBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery<Producto>, representa la consulta que 
        // devolverá el objeto de tipo Producto.
        CriteriaQuery<Producto> criteriaQuery = criteraBuilder.createQuery(Producto.class);

        // Se obtiene la entidad desde la que se obtendrán los
        // resultados de la consulta.
        Root<Producto> entidadProducto = criteriaQuery.from(Producto.class);

        // Se seleccionan todos los atributos de la entidad Producto,
        // luego se obtienen solo los que tiene el valor del id del parámetro
        // como valor de su atributo id.
        criteriaQuery.select(entidadProducto).where(criteraBuilder.equal(entidadProducto.get("id"), idProducto));

        // Se crea el objeto TypedQuery<Producto>, representa la consulta ejecutable.
        TypedQuery<Producto> query = entityManager.createQuery(criteriaQuery);
        
        // Estructura try-catch para manejar la excepción NoResultException.
        try{
            // Se devuelve el objeto Producto coincidente.
            return query.getSingleResult();
            
        } catch(NoResultException ex){
            // En caso que no haya Productos coincidentes, se lanza una excepción.
            throw new PersistenciaException("No existe un producto con Id especificado.");
        }
        
    }

    /**
     * Implementación del método consultarProductosConFiltros(), de la interfaz {@link IProductosDAO},
     * permite obtener la lista de objetos Producto alamcenados en la base de datos. Dados los filtros
     * de los parámetros.
     * @param tituloAutor Objeto String que representa el titulo del libro, o del autor, del producto buscado, o parte de él.
     * @param categorias Objeto {@literal List<CategoriaDTO>} que contiene las categorías de los libros de los productos
     * a consultar.
     * @param formatos Objeto {@literal List<FormatoDTO>} que contiene los formatos de los productos
     * a consultar.
     * @param precioMinimo Objeto Double que representa el precio mínimo de los productos 
     * a consultar.
     * @param precioMaximo Objeto Double que representa el precio maximo de los productos 
     * a consultar.
     * @return Objeto {@literal List<Producto>} que contiene la lista de 
     * objetos Producto almacenados, que cumplen con los filtros de los parámetros.
     * @throws PersistenciaException Se lanza si ocurre un error en la consulta.
     */
    @Override
    public List<Producto> consultarProductosConFiltros(
        String tituloAutor,
        List<Categoria> categorias, 
        List<Formato> formatos, 
        Double precioMinimo, 
        Double precioMaximo) throws PersistenciaException {

        
        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se crea el objeto CriteriaBuilder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        
        // Se crea el objeto CriteriaQuery<Producto>, es la
        // consulta ejecutable, devolverá objetos Producto
        CriteriaQuery<Producto> criteriaQuery = criteriaBuilder.createQuery(Producto.class);
        
        // Se obtiene la entidad Producto, desde donde se realizará la búsqueda
        Root<Producto> entidadProducto = criteriaQuery.from(Producto.class);

        // Lista para acumular los predicados que se aplicarán.
        List<Predicate> predicados = new ArrayList<>(); 

        // Se realiza un INNER JOIN entre Productos y Libros.
        Join<Producto, Libro> joinLibro = entidadProducto.join("libro", JoinType.INNER); 
        
        //Se realiza un INNER JOIN con Categoria.
        Join<Libro, Categoria> joinCategoria = joinLibro.join("categorias", JoinType.INNER); 

        //Se realiza un INNER JOIN con Autor.
        Join<Libro, Autor> joinAutor = joinLibro.join("autor", JoinType.INNER);

        // Predicado para búsqueda por título o nombre completo de autor.
        if (tituloAutor != null && !tituloAutor.trim().isEmpty()) {
            
            String patron = "%" + tituloAutor.toLowerCase() + "%";

            // Predicado para el título del libro.
            Predicate predicadoTitulo = criteriaBuilder.like(
                criteriaBuilder.lower(joinLibro.get("titulo")), 
                patron
            );

            // Comprobación con nombre de autor.
            String terminoBusqueda = "%" + tituloAutor.trim().toLowerCase().replace(" ", "") + "%";

            // Se convierten valores nulos a cadenas vacías para que la concatenación no falle.
            // Se une el nombre y el apellido paterno.
            Expression<String> nombreApellidoPaterno = criteriaBuilder.concat(
                criteriaBuilder.coalesce(joinAutor.get("nombre"), ""), 
                criteriaBuilder.coalesce(joinAutor.get("apellidoPaterno"), "")
            );

            // Se une lo anterior, al apellido materno.
            Expression<String> nombreCompleto = criteriaBuilder.concat(
                nombreApellidoPaterno, 
                criteriaBuilder.coalesce(joinAutor.get("apellidoMaterno"), "")
            );

            // Predicado para determinar si el nombre completo contiene el nombre buscado.
            Predicate predicadoAutor = criteriaBuilder.like(
                criteriaBuilder.lower(nombreCompleto), 
                terminoBusqueda
            );

            // Se unen los dos predicados.
            Predicate predicadoBusqueda = criteriaBuilder.or(predicadoTitulo, predicadoAutor);

            predicados.add(predicadoBusqueda);
        }
        
        
        
        // Predicado para categorías.
        if (categorias != null && !categorias.isEmpty()) {
            Predicate predicadoCategoria = joinCategoria.in(categorias);
            predicados.add(predicadoCategoria);
        }

        // Predicado para formatos.
        if (formatos != null && !formatos.isEmpty()) {
            Path<Formato> pathFormato = entidadProducto.get("formato");
            Predicate predicadoFormato = pathFormato.in(formatos);
            predicados.add(predicadoFormato);
        }

        // Predicado para precio mínimo.
        if (precioMinimo != null) {
            Predicate predicadoPrecioMinimo = criteriaBuilder.greaterThanOrEqualTo(entidadProducto.get("precio"), precioMinimo);
            predicados.add(predicadoPrecioMinimo);
        }

        // Rango máximo (Precio <= precioMaximo)
        if (precioMaximo != null) {
            Predicate predicadoPrecioMaximo = criteriaBuilder.lessThanOrEqualTo(entidadProducto.get("precio"), precioMaximo);
            predicados.add(predicadoPrecioMaximo);
        }

        // Se obtienen productos únicos.
        criteriaQuery.distinct(true);
        
        // Si hay predicados, se unen todos y se aplican al objeto Criteria Query.
        if (!predicados.isEmpty()) {

            criteriaQuery.where(criteriaBuilder.and(predicados.toArray(new Predicate[0])));
        }

        // Los resultados se ordenarán por título de libro.
        criteriaQuery.orderBy(criteriaBuilder.asc(joinLibro.get("titulo")));

        // Se crea el objeto TypedQuery<Producto>, es la consulta ejecutable
        TypedQuery<Producto> query = entityManager.createQuery(criteriaQuery);

        try {
            // Se obtiene la lista de Productos coincidentes.
            List<Producto> productos = query.getResultList();

            return productos;
        } catch (PersistenceException e) {

            // Se lanza una excepción si ocurre un error en la consulta.
            throw new PersistenciaException("Error al consultar los productos.");
        }

    }
    
    /**
     * Implementación del método actualizarProducto(), de la interfaz {@link IProductosDAO},
     * permite actualizar los valores de los campos de Producto almacenado.
     * 
     * @param productoActualizado Objeto Producto que contiene los nuevos valores
     * del Producto a modificar.
     * 
     * @throws PersistenciaException Se lanza si algún valor actualizado es nulo.
     */
    @Override
    public void actualizarProducto(Producto productoActualizado) throws PersistenciaException{
        
        // Se valida que el id no sea nulo.
        if(productoActualizado.getId() == null){
            throw new PersistenciaException("El Id recibido para la actualización del producto es nulo.");
        }
        
        // Se valida que el isbn no sea nulo.
        if(productoActualizado.getIsbn() == null){
            throw new PersistenciaException("El ISBN recibido para la actualización del producto es nulo.");
        }
        
        // Se valida que el precio no sea nulo.
        if(productoActualizado.getPrecio() == null){
            throw new PersistenciaException("El precio recibido para la actualización del producto es nulo.");
        }
        
        // Se valida que el stock no sea nulo.
        if(productoActualizado.getStock() == null){
            throw new PersistenciaException("El stock recibido para la actualización del producto es nulo.");
        }
        
        // Se valida que la dirección de imagen no sea nula
        if(productoActualizado.getUrlImagen()== null){
            throw new PersistenciaException("La dirección de imagen recibida para la actualzación del producto es nula.");
        }
        
        // Se valiada que el formato no sea nulo.
        if(productoActualizado.getFormato() == null){
            throw new PersistenciaException("El formato recibido para la actualización del producoto es nulo.");
        }

        
        // Se crea el objeto EntityManager
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se inicia el bloque try-catch para manejar las excepciones de persistencia.
        try {
            // Se inicia una transacción para realizar la actualización del Producto.
            entityManager.getTransaction().begin();

            // Se comprueba que el Producto exista
            String jpqlQueryValidarId = """
                                         SELECT p FROM Producto p
                                         WHERE p.id = :id
                                         """;
 
            // Se crea el objeto TypedQuery<Producto>, es la consulta ejecutable.
            TypedQuery<Producto> queryValidarId = entityManager.createQuery(jpqlQueryValidarId, Producto.class);

            // Se establece el valor de id del parámetro a la consulta
            queryValidarId.setParameter("id", productoActualizado.getId());

            // Estructura try catch para determinar si se lanza la excepción NoResultException
            try{
                // Se obtiene el resultado de la consulta
                queryValidarId.getSingleResult();

            } catch(NoResultException ex){
                // Se lanza una excepción en caso de que el objeto Producto no exista
                throw new PersistenciaException("No existe un producto con el Id especificado.");
            }

            // Se determina si existe otro producto con el mismo isbn 
            // Se añade la condición de que sea distinto al id del Producto del parámetro para no 
            // considerar al objeto Producto a actualizar.
            String jpqlQuery = """
                                SELECT p FROM Producto p
                                WHERE p.isbn = :isbn
                                AND p.id != :id
                                """;

            // Se crea una consulta Query
            Query query = entityManager.createQuery(jpqlQuery);

            // Se establecen los parámetros de isbn y id para la consulta
            query.setParameter("isbn", productoActualizado.getIsbn());
            query.setParameter("id", productoActualizado.getId());

            // Se obtienen la cantidad de Productos resultantes de la consulta
            int cantidadProductosMismoIsbn = query.getResultList().size();

            // Se verifica si hay otros Productos con el mismo ISBN
            if(cantidadProductosMismoIsbn > 0){
                // Se lanza una excepción en caso de que sí existan
                throw new PersistenciaException("Ya existe un Producto con el mismo ISBN.");
            }

            // Se actualiza la entidad con el método merge() de EntityManager,
            // que es el método estándar para actualizar entidades.
            entityManager.merge(productoActualizado);

            // La transacción finaliza.
            entityManager.getTransaction().commit();

        } catch (PersistenceException ex) {

            // Se maneja la excepción, asegurando rollback en caso de fallo.
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            // Se relanza la excepción.
            throw new PersistenciaException("Error al actualizar el producto.");
        }
        
    }
    
    
    /**
     * Implementación del método eliminarProducto(), de la interfaz {@link IProductosDAO},
     * permite eliminar un Producto de la base de datos, que tiene el Id del parámetro.
     * 
     * @param idProducto Objeto Long que representa el Id del Producto a eliminar.
     * @throws PersistenciaException Se lanza si ocurre un error al eliminar el Producto.
     */

    @Override
    public void eliminarProducto(Long idProducto) throws PersistenciaException {
        
        // Se valida que el id no sea nulo.
        if(idProducto == null){
            throw new PersistenciaException("El Id recibido para la eliminación del producto es nulo.");
        }
        
        // Se crea el objeto EntityManager.
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        // Se inicia el bloque try-catch para manejar las excepciones de persistencia.
        try {
            // Se inicia una transacción para realizar la eliminación del Producto.
            entityManager.getTransaction().begin();

            // Se busca la entidad Producto a eliminar utilizando el método find().
            Producto productoAEliminar = entityManager.find(Producto.class, idProducto);

            // Se valida si el producto existe en la base de datos.
            if (productoAEliminar == null) {
                // Si no se encuentra, se hace rollback y se lanza la excepción.
                entityManager.getTransaction().rollback();
                throw new PersistenciaException("No existe un producto con el Id especificado para eliminar.");
            }

            // Se elimina la entidad utilizando el método remove() de EntityManager.
            entityManager.remove(productoAEliminar);

            // La transacción finaliza y se aplican los cambios en la base de datos.
            entityManager.getTransaction().commit();

        } catch (PersistenceException ex) {

            // Se maneja la excepción, asegurando rollback en caso de fallo.
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
           
            // Se relanza la excepción.
            throw new PersistenciaException("Error al eliminar el producto.");
        }
        
        
        
    }
    
}
