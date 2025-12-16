

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Agregar Libro</title>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-admin-agregar.css"/>
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <main>
                <div class="general-container">
                    <c:if test="${not empty mensajeError}">
                        <div class="error-alert">
                            <c:out value="${mensajeError}" />
                        </div>
                    </c:if>
                    <div class="container-add-edition-page">

                        <div class="title-container">
                            <h1 class="h1-page">Agregar libro</h1>
                        </div>

                        <form action="admin-agregar-libro" class="form-add-edition-page" id="form-add-edition" method="POST">

                            <div>
                                <label>Título: </label>
                                <input class="input-form-add-edition" type="text" name="titulo" required />
                            </div>

                            <div class="field-with-button">
                                <div class="field-left">
                                    <label>Autor:</label>
                                    <select name="id-autor" required>
                                        <c:forEach var="autor" items="${listaAutores}">
                                            <option value="${autor.id}">
                                                <c:out value="${autor.nombre}" /> <c:out value="${autor.apellidoPaterno}" />
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <a href="admin-autores-registrados">
                                    <input type="button" value="Seleccionar" class="btn-inline"/>
                                </a>
                            </div>


                            <div class="field-with-button">
                                <div class="field-left">
                                    <label>Categoría:</label> <select name="ids-categorias" required>                                 
                                        <c:forEach var="categoria" items="${listaCategorias}">
                                            <option value="${categoria.id}">
                                                <c:out value="${categoria.nombre}" />
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <a href="admin-categorias-registradas">
                                    <input type="button" value="Seleccionar" class="btn-inline"/>
                                </a>

                            </div>



                            <div class="field-with-button">
                                <div class="field-left">
                                    <label>Editorial:</label>
                                    <select name="id-editorial" required>
                                        <c:forEach var="editorial" items="${listaEditoriales}">
                                            <option value="${editorial.id}">
                                                <c:out value="${editorial.nombre}" />
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <a href="admin-editoriales-registradas">
                                    <input type="button" value="Seleccionar" class="btn-inline"/>
                                </a>
                            </div>


                            <div>
                                <label>Publicación: </label>
                                <input class="input-form-add-edition" type="date" name="fecha-publicacion" required />
                            </div>

                            <div class="div-synopsis">
                                <label>Sinopsis: </label>
                                <textarea class="input-form-add-edition" name="sinopsis" maxlength="350" required></textarea>
                            </div>

                            <div class="buttons-add-edition-page">
                                <a href="admin-libros-registrados">
                                    <input type="button" value="Cancelar" class="cancel-input-page" />
                                </a>
                                <input type="submit" value="Guardar" class="add-input-page" />
                            </div>

                        </form>

                    </div>

                </div>
            </main>
        </div>
    </body>
</html>
