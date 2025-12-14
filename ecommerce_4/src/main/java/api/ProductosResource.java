package api;

import definiciones.ICategoriasBO;
import definiciones.IProductosBO;
import dominio.CategoriaDTO;
import dominio.FormatoDTO;
import dominio.ProductoDTO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import fabrica.FabricaBO;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * REST Web Service para productos
 *
 * @author norma
 */
@Path("productos")
@RequestScoped
public class ProductosResource {

    private IProductosBO productosBO;
    private ICategoriasBO categoriasBO;

    /**
     * Creates a new instance of ProductosResource
     */
    public ProductosResource() {
        this.productosBO = FabricaBO.obtenerProductosBO();
        this.categoriasBO = FabricaBO.obtenerCategoriasBO();
    }

    /**
     * Obtiene productos con filtros.
     *
     * @param categorias IDs de categorías
     * @param formatos Formatos separados por coma
     * @param precioMinimo Precio mínimo
     * @param precioMaximo Precio máximo
     * @param nombreLibro Texto para buscar en título
     * @return Lista de productos filtrados
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductos(
            @QueryParam("categorias") String categorias,
            @QueryParam("formatos") String formatos,
            @QueryParam("precioMinimo") Double precioMinimo,
            @QueryParam("precioMaximo") Double precioMaximo,
            @QueryParam("nombreLibro") String nombreLibro) {

        try {
            List<CategoriaDTO> filtrosCategoria = null;
            if (categorias != null && !categorias.trim().isEmpty()) {
                String[] idsCategorias = categorias.split(",");
                filtrosCategoria = new ArrayList<>();
                for (String idCategoria : idsCategorias) {
                    CategoriaDTO categoria = new CategoriaDTO();
                    categoria.setId(Long.valueOf(idCategoria.trim()));
                    filtrosCategoria.add(categoria);
                }
            }

            List<FormatoDTO> filtrosFormato = null;
            if (formatos != null && !formatos.trim().isEmpty()) {
                String[] listaFormatos = formatos.split(",");
                filtrosFormato = new ArrayList<>();
                for (String formatoStr : listaFormatos) {
                    FormatoDTO formato = FormatoDTO.valueOf(formatoStr.trim().toUpperCase());
                    filtrosFormato.add(formato);
                }
            }

            List<ProductoDTO> productos = productosBO.consultarProductosConFiltros(
                    nombreLibro,
                    filtrosCategoria,
                    filtrosFormato,
                    precioMinimo,
                    precioMaximo
            );

            return Response.ok(productos).build();

        } catch (NegocioException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtiene todas las categorías.
     *
     * @return Lista de categorías
     */
    @GET
    @Path("categorias")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategorias() {
        try {
            List<CategoriaDTO> categorias = categoriasBO.consultarCategorias();
            return Response.ok(categorias).build();

        } catch (NegocioException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Obtiene el detalle de un producto por su ID.
     *
     * @param id ID del producto
     * @return Detalle del producto
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducto(@PathParam("id") Long id) {
        try {
            ProductoDTO producto = productosBO.consultarProducto(id);
            return Response.ok(producto).build();

        } catch (NegocioException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
