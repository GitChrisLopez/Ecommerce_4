<%-- 
    Document   : carrito
    Created on : 19 nov 2025, 10:26:26 p.m.
    Author     : chris
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8" />
        <title>Catálogo</title>
        <link rel="stylesheet" href="./styles/styles-catalogo-filtros-mobile.css" />
        <link rel="stylesheet" href="./styles/styles-nav.css" />
        <script src="./js/catalogo-filtros-mobile.js"></script>
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <div class="c2">
                <main>
                    <div class="catalogo-container">
                        <div class="sidebar">
                            <div class="filtros-container">
                                <div class="options-lookup">
                                    <div class="search-filtros">
                                        <button class="filtrar-btn">
                                            <img src="./icons/filtro.png" alt="Filtro" class="icono-filtro" />
                                            <span>Filtrar</span>
                                        </button>
                                    </div>

                                    <div class="search-box">
                                        <input type="text" name="nombreLibro" placeholder="Buscar por Libro" />
                                    </div>
                                </div>

                                <div class="filtros-book-container">

                                    <div class="filtros filtro-categoria">
                                        <h2>Categoría</h2>
                                    </div>

                                    <div class="filtros filtro-formato">
                                        <h2>Formato</h2>
                                    </div>

                                    <div class="filtros filtro-rango-precio">
                                        <h2>Precio</h2>
                                        <p>Mínimo: $<span id="precioMinValor">0</span></p>
                                        <input type="range" name="precioMinimo" min="0" max="1000" value="0" id="precioMinRango" />
                                        <p>Máximo: $<span id="precioMaxValor">1000</span></p>
                                        <input type="range" name="precioMaximo" min="0" max="1000" value="1000" id="precioMaxRango" />
                                    </div>

                                    <div class="filtros-btn">
                                        <a href="#" class="aplicar-filtros-btn" id="btnAplicarFiltros">Aplicar filtros</a>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="area-total-container">
                            
                        </div>
                    </div>
                </main>
            </div>
        </div>
    </body>
</html>
