
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>

<html>
    <head>
        <title>Editoriales Registradas</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-registrado.css">
    </head>

    <body>
        <main>
            <div class="c1">
                <%@ include file ="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <div class="container-registered">

                <div class="title-search">

                    <h1>Editoriales registradas</h1>

                    <div class="search-bar">
    
                        <form class="form-search" action="admin-editoriales-registradas" method="GET">

                            <input type="search" 
                                   placeholder="Buscar" 
                                   id="input-search" 
                                   name="nombre-buscado">
                            
                            <button type="submit" class="btn-lupa">
                                <img src="./icons/lupa.png" alt="Buscar">    
                            </button>

                        </form>
                    </div>

                </div>

                <div class="container-list">

                    <c:if test="${empty listaEditoriales}">
                        <c:choose>
        
                            <c:when test="${not empty nombreBuscado}">
                                <p>
                                    No se encontraron resultados para "<c:out value="${nombreBuscado}"/>".
                                </p>
                            </c:when>

                            <c:otherwise>
                                <p>No hay editoriales registradas.</p>
                            </c:otherwise>

                        </c:choose>
                    </c:if>

                    <c:forEach var="editorial" items="${listaEditoriales}" varStatus="contador">

                        <label for="editorial-${contador.index}">

                            <p>
                                <c:out value="${editorial.nombre}" />
                            </p>

                            <a href="admin-editar-editorial?id=${editorial.id}" class="button-edition">
                                <img src="icons/edition.png" alt="Editar">
                            </a>

                        </label>

                    </c:forEach>

                </div>

                <div class="buttons">

                    <div class="buttons-cancel-save">
                        <a href="admin-menu-principal">
                            <input type="submit" value="Volver" class="cancel-input">
                        </a>

                        <a href="admin-nueva-editorial">
                            <input type="submit" value="Agregar" class="add-input">
                        </a>
                    </div>
                </div>

            </div>

        </main>
    </body>
</html>
