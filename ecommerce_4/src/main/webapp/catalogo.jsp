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
        <title>Catálogo</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-catalogo.css">
        <script src="./js/catalogo.js"></script>
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <main>
                <div class="c2">

                    <div class="catalogo-container">

                        <form class="sidebar">

                            <div class="options-lookup">
                                <div class="search-filtros">
                                    <a href="./catalogo-filtros-mobile.jsp" class="filtrar-btn">
                                        <img src="./icons/filtro.png" alt="Filtro" class="icono-filtro">
                                        <span>Filtrar</span>
                                    </a>
                                </div>
                                <div class="search-box">
                                    <input type="text" name="nombreLibro" placeholder="Buscar por libro">
                                </div>
                            </div>

                            <input type="submit" value="Aplicar filtros" class="btn-aplicar-filtros">

                            <div class="filtros filtro-categoria">
                                <h2>Categoría</h2>
                                
                            </div>

                            <div class="filtros filtro-formato">
                                <h2>Formato</h2>
                                
                            </div>

                            <div class="filtros">
                                <h2>Precio</h2>
                                <p>Mínimo: $<span id="precioMinValor">0</span></p>
                                <input type="range" name="precioMinimo" min="0" max="1000" value="0" id="precioMin">
                                <p>Máximo: $<span id="precioMaxValor">1000</span></p>
                                <input type="range" name="precioMaximo" min="0" max="1000" value="1000" id="precioMax">
                            </div>

                        </form>

                        <div class="area-total-container">
                            <div class="libros-container">
                                
                            </div>
                        </div>
                    </div> 
                </div>
            </main>
        </div>
    </body>    
</html>
