<%--
    Document : admin-historial-resenias
    Created on : 16 nov 2025
    Author : norma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Historial de Reseñas</title>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-historial-resenias-admin.css"/>

        <script>
            function confirmarEliminacion(id) {
                if (confirm("¿Estás seguro de que quieres eliminar la reseña")) {
                    document.getElementById('form-eliminar-' + id).submit();
                }
                return false;
            }

        </script>

    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file ="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <main>
                <div class="c2">
                    <div class="search-box">
                        <form action="${pageContext.request.contextPath}/admin-filtrar-resenias" method="GET"> 
                            <div class="buscador">
                                <input 
                                    type="text" 
                                    name="busqueda" 
                                    placeholder="Buscar por Título de Libro" 
                                    value="${param.busqueda != null ? param.busqueda : ''}" 
                                    />
                                <button type="submit" style="display:none;">Buscar</button>
                            </div>
                        </form>
                    </div>

                    <div class="resenias-container">
                        <h1 class="historial-resenias-title">Historial de Reseñas</h1>

                        <div class="lista-resenias">

                            <c:forEach var="resenia" items="${listaResenias}">

                                <div class="resenia">
                                    <div class="resenia-info">
                                        <p><span>Producto:</span> ${resenia.libro.titulo} (${resenia.formato})</p>
                                        <p><span>Usuario:</span> ${resenia.cliente.nombre}</p>
                                        <p><span>Calificación:</span> ${resenia.calificacion}</p>
                                        <p class="resenia-comentario">
                                            <span>Comentario:</span> "${resenia.comentario}"
                                        </p>
                                        <p><span>Fecha:</span> ${resenia.fecha}</p>
                                    </div>

                                    <div class="resenia-btn">
                                        <a href="${pageContext.request.contextPath}/admin-editar-resenia?id=${resenia.id}" class="editar-btn">Editar</a>

                                        <form id="form-eliminar-${resenia.id}" 
                                              action="${pageContext.request.contextPath}/admin-eliminar-resenia" 
                                              method="POST" 
                                              style="margin: 0;">

                                            <input type="hidden" name="id" value="${resenia.id}">

                                            <button 
                                                type="button" 
                                                class="eliminar-btn" 
                                                onclick="confirmarEliminacion(${resenia.id});">
                                                Eliminar
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>

                            <c:if test="${empty listaResenias and empty errorCarga}">
                                <p>No se encontraron reseñas para mostrar.</p>
                            </c:if>

                        </div>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>