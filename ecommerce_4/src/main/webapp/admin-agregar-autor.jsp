<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Agregar Autor</title>
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
                            <h1>Agregar autor</h1>
                        </div>

                        <form action="admin-agregar-autor" class="form-add-edition" id="form-add-edition" method="POST">
                            
                            <div>
                                <label>Nombre: </label>
                                <input class="input-form-add-edition" type="text" name="nombre" required />
                            </div>
                            
                            <div>
                                <label>Apellido paterno: </label>
                                <input class="input-form-add-edition" type="text" name="apellido-paterno" required />
                            </div>
                            
                            <div>
                                <label>Apellido materno: </label>
                                <input class="input-form-add-edition" type="text" name="apellido-materno" />
                            </div>
                            <div class="autor-btns-mobile">
                                <a href="admin-autores-registrados">
                                    <input type="button" value="Cancelar" class="btn-autor-cancel" />
                                </a>
                                <input type="submit" value="Guardar" class="btn-autor-save" />
                            </div>
                        </form>
                    </div>
                    <div class="autor-btns-desktop">
                        <a href="admin-autores-registrados">
                            <input type="button" value="Cancelar" class="btn-autor-cancel" />
                        </a>
                        <input type="submit" value="Guardar" class="btn-autor-save" form="form-add-edition" />
                    </div>

                </div>
            </main>
        </div>
    </body>
</html>