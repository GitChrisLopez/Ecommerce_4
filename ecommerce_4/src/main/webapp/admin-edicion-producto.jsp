

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
    <head>
        <title>Editar Producto</title>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-agregar-producto.css"/>
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file ="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <main>

                <div class="general-container">
                    <c:if test="${not empty mensajeError}">
                        <div class="error-alert">
                            <c:out value="${mensajeError}" />
                        </div>
                    </c:if>
                    <div class="container-add-edition">

                        <div class="image-container">

                            <img src="${pageContext.request.contextPath}/${productoEditar.urlImagen}" 
                                 alt="Imagen del producto" 
                                 class="product-image">

                            <input form="form-add" type="file" name="foto-nueva" id="input-foto" accept="image/*">

                            <label class="container-icon-add-edition" for="input-foto">
                                <img src="icons/edition.png" alt="Editar foto" title="Cambiar imagen">
                            </label>
                        </div>

                        <form id="form-add" action="admin-actualizar-producto" method="POST" enctype="multipart/form-data" class="form-add-edition">

                            <input type="hidden" name="id" value="${productoEditar.id}">
                            <input type="hidden" name="url-imagen" value="${productoEditar.urlImagen}">

                            <div> 
                                <label>Libro:</label>
                                <select name="id-libro" required>
                                    <c:forEach var="libro" items="${listaLibros}">
                                        <option value="${libro.id}" 
                                                ${libro.id == productoEditar.libro.id ? 'selected' : ''}>
                                            <c:out value="${libro.titulo}" />
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div>
                                <label>ISBN</label>
                                <input type="text" name="isbn" maxlength="13" pattern="[0-9]{13}" value="${productoEditar.isbn}" required title="Debe ingresar un número de 13 dígitos"/>
                            </div>

                            <div>
                                <label>Formato:</label>
                                <select name="formato" class="form-control">
                                    <c:forEach var="entradaFormato" items="${mapaFormatos}">
                                        <option value="${entradaFormato.key}" ${entradaFormato.key == productoEditar.formato ? 'selected' : ''}>
                                            <c:out value="${entradaFormato.value}" />
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div>
                                <label>No. de páginas:</label>
                                <input type="number" name="numero-paginas" min="1" max="10000" value="${productoEditar.numeroPaginas}" required />
                            </div>

                            <div>
                                <label>Precio:</label>
                                <input type="number" name="precio" min="0" max="50000" step="0.01" value="${productoEditar.precio}" required />
                                <span>pesos</span>
                            </div>

                            <div>
                                <label>Stock:</label>
                                <input type="number" name="stock" min="0" max="99999.99" value="${productoEditar.stock}" required />
                            </div>

                            <div class="buttons-cancel-add-edition">
                                <input type="submit" value="Eliminar Producto" class="delete-button-input" 
                                       formaction="admin-eliminar-producto" formenctype="application/x-www-form-urlencoded" formnovalidate>

                                <a class="cancel-a" href="admin-menu-principal">
                                    <input type="button" value="Cancelar" class="cancel-input" />
                                </a>

                                <input type="submit" value="Guardar Cambios" class="add-edition-input add-input"/>
                            </div>

                        </form>
                    </div>
                </div>
            </main>

        </div>
    </body>
</html>
