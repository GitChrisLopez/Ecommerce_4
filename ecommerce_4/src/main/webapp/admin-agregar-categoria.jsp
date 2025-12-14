

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Agregar Categoría</title>
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

                    <div class="container-add-edition">
                        <div class="title-container">
                            <h1>Agregar categoría</h1>
                        </div>

                        <form action="admin-agregar-categoria" class="form-add-edition" id="form-add-edition" method="POST">
                            <input type="hidden" value="${categoriaAgregar.id}" name="id">

                            <div>
                                <label>Nombre: </label>
                                <input class="input-form-add-edition" type="text" name="nombre" value="${categoriaAgregar.nombre}" required />
                            </div>

                            <div class="cat-btns-mobile">
                                <a href="admin-categorias-registradas">
                                    <input type="button" value="Cancelar" class="btn-cat-cancel" />
                                </a>
                                <input type="submit" value="Guardar" class="btn-cat-save" />
                            </div>
                        </form>
                    </div>

                    <div class="cat-btns-desktop">
                        <a href="admin-categorias-registradas">
                            <input type="button" value="Cancelar" class="btn-cat-cancel" />
                        </a>
                        <input type="submit" value="Guardar" class="btn-cat-save" form="form-add-edition" />
                    </div>

                </div>
            </main>
        </div>
    </body>
</html>


