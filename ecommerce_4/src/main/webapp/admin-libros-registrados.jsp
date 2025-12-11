

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
        <title>Libros Registrados</title>
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

                    <h1>Libros registrados</h1>

                    <div class="search-bar">
                        <input type="search" placeholder="Buscar" id="input-search">
                        <label for="input-search">
                            <img src="./icons/lupa.png">    
                        </label>
                    </div>

                </div>

                <form action="admin-editar-libro" class="container-list" id="form">

                    <c:if test="${empty listaLibros}">
                        <p>No hay libros registrados.</p>
                    </c:if>

                    <c:forEach var="libro" items="${listaLibros}" varStatus="status">

                        <input type="radio" 
                               id="libro_${status.index}" 
                               name="titulo-libro" 
                               value="${libro.titulo}" 
                               required>

                        <label for="libro_${status.index}">

                            <p>
                                <c:out value="${libro.titulo}" />
                                <br>
                                <span>
                                    Autor: <c:out value="${libro.autor.nombre}"/> <c:out value="${libro.autor.apellidoPaterno}" />
                                </span>
                            </p>

                            <a href="admin-editar-libro?titulo=${libro.titulo}">
                                <img src="icons/edition.png" alt="Editar libro" title="Editar libro">  
                            </a>                      
                        </label>

                    </c:forEach>

                </form>

                <div class="buttons">

                    <div class="buttons-cancel-save">
                        <a href="admin-menu-principal">
                            <input type="submit" value="Cancelar" class="cancel-input">
                        </a>

                        <a href="admin-nuevo-libro">
                            <input type="submit" value="Agregar" class="add-input">
                        </a>
                    </div>
                </div>

            </div>

        </main>
    </body>
</html>
