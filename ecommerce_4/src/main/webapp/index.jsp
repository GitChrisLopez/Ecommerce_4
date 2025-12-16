<%-- 
    Document   : carrito
    Created on : 19 nov 2025, 10:26:26 p.m.
    Author     : chris
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Inicio</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-principal.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <div class="c2">
                <main>
                    <div class="content-container">
                        <h1>Miles de libros, <br>infinitas ideas.</h1>
                        <p class="main-p1">
                            Explora nuestra colección y deja que las palabras te acompañen en cada momento.
                            Desde clásicos imperdibles hasta nuevas voces que te sorprenderán.
                        </p>
                        <p>
                            Libros académicos, guías y textos especializados para acompañarte en cada etapa del conocimiento.
                        </p>
                    </div>
                    <div class="cover-container">
                        <img src="./imgs/cover.png" alt="Ilustración de estantería y persona leyendo" class="cover">
                    </div>
                </main>

                <div class="buttons-container">
                    <a href="./catalogo.jsp" class="btn genero-1" target="_blank">Drama</a>
                    <a href="./catalogo.jsp" class="btn genero-2" target="_blank">Thriller</a>
                    <a href="./catalogo.jsp" class="btn genero-3" target="_blank">Ciencia ficción</a>
                    <a href="./catalogo.jsp" class="btn genero-4" target="_blank">Terror</a>
                    <a href="./libros-mas-destacados.jsp" class="btn destacados-btn" target="_blank">Libros destacados</a>
                    <a href="./catalogo.jsp" class="btn catalogo-btn" target="_blank">Catálogo</a>
                </div>
            </div>
        </div>
    </body>

</html>
