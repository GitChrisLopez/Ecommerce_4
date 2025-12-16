

<!DOCTYPE html>
<html>
    <head>
        <title>Editar Categoría</title>
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
                            <h1>Editar categoría</h1>
                        </div>

                        <form action="admin-actualizar-categoria" class="form-add-edition" id="form-add-edition" method="POST">

                            <input type="hidden" value="${categoriaEditar.id}" name="id">
                            <div>
                                <label>Nombre: </label>
                                <input class="input-form-add-edition" type="text" name="nombre" value="${categoriaEditar.nombre}" required />
                            </div>

                            <div class="buttons-cancel-add-edition">
                                <a href="admin-categorias-registradas">
                                    <input type="button" value="Cancelar" class="cancel-input" />
                                </a>
                                <input type="submit" value="Guardar" class="add-edition-input add-input" />
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>
