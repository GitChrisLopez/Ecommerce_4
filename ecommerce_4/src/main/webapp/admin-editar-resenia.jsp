<%-- 
Document   : comunidad
Created on : 19 nov 2025, 4:10:47 p.m.
Author     : chris
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
    <head>
        <title>Editar Reseña</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-editar-resenia-admin.css">
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file ="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <main>
                <div class="c2">

                    <c:if test="${not empty resenia}">

                        <div class="resenia-container">

                            <form action="${pageContext.request.contextPath}/admin-actualizar-resenia" method="POST">

                                <input type="hidden" name="id" value="${resenia.id}">

                                <div class="resenia-item">
                                    <div class="detalles-resenia">

                                        <p><span>Producto:</span> ${resenia.libro.titulo} (${resenia.formato})</p>
                                        <p><span>Usuario:</span> ${resenia.cliente.nombre}</p>
                                        <p><span>Calificación:</span> ${resenia.calificacion}</p>
                                        <p><span>Fecha:</span> ${resenia.fecha}</p>

                                        <p><span>Comentario:</span></p>

                                        <textarea name="comentario" class="comentario-box" rows="4">${resenia.comentario}</textarea>

                                    </div>
                                </div>

                                <div class="resenia-btns">
                                    <a href="${pageContext.request.contextPath}/admin-mostrar-resenias" class="cancelar-btn">Cancelar</a> 
                                    <button type="submit" class="guardar-btn">Guardar</button>
                                </div>
                            </form>
                        </div>
                    </c:if>

                    <c:if test="${empty resenia and empty sessionScope.mensajeEstado}">
                        <p>No se pudo cargar la reseña para editar. Por favor, regrese al historial.</p>
                    </c:if>

                </div>
            </main>
        </div> 
    </body>
</html>