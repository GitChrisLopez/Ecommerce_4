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
        <title>Detalles Producto</title>
        <link rel="stylesheet" href="./styles/styles-detalles-producto.css" />
        <link rel="stylesheet" href="./styles/styles-nav.css" />
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <main>
                <div class="producto-container">
                    <div class="info-libro">
                        <img src="" alt="" />
                        <div class="detalles-libro">
                            <h3></h3>
                            <label></label>

                            <div class="calificacion">
                                <p>3</p>
                                <div class="estrella"></div>
                                <div class="estrella"></div>
                                <div class="estrella"></div>
                                <div class="estrella"></div>
                            </div>

                            <p class="p-editorial"></p>
                            <p class="p-publicacion"></p>
                            <p class="p-no-paginas"></p>
                            <p class="p-formato"></p>
                            <p class="p-precio"></p>
                            <p class="p-stock"></p>

                            <form id="form-agregar-carrito" class="div-add-cart">
                                
                                <input type="hidden" id="product-id" value="${producto.id}">
                                <label for="product-quantity" class="label-cantidad">Cantidad:</label>
                                <input type="number" id="product-quantity" class="product-quantity" required>
                                <input type="submit" value="Agregar al carrito" class="btn-add-cart" onclick="agregarProductoCarrito(event)">
                            </form>
                        </div>
                    </div>

                    <div class="resenia-container">

                        <input type="checkbox" id="sinopsis" class="input-checkbox">
                        <label for="sinopsis" class="titulo-sinopsis-container">
                            <h2>Sinopsis</h2>
                            <img src="./icons/information.png">
                        </label>

                        <div class="separador-sinopsis"></div>

                        <div class="resumen">
                            <p>
                                
                            </p>
                        </div>

                        <br />

                        <div class="container-encabezado-resenia">
                            <h2>Reseñas</h2>
                            <label for="btn-agregar-checkbox" class="btn-agregar">
                                <span class="span-agregar-resenia">Agregar reseña</span>
                                <span class="span-cancelar-resenia">Cancelar reseña</span>
                            </label>
                            <input type="checkbox" class="btn-agregar-checkbox" id="btn-agregar-checkbox">
                        </div>

                        <div class="separador-resenias"></div>

                        <div class="resenias-usuarios">

                            <div class="nueva-resenia-container">

                                <div class="resenia-usuario">
                                    <img src="./icons/user.png">
                                    <div class="nombre-calificacion-usuario">
                                        <span>Tú</span>
                                        <div class="calificacion">
                                            <p>3</p>
                                            <div class="estrella"></div>
                                            <div class="estrella"></div>
                                            <div class="estrella"></div>
                                            <div class="estrella"></div>
                                        </div>
                                    </div>
                                </div>

                                <form class="form-nueva-resenia">

                                    <label for="calificacion-range">Calificación:</label>
                                    <input class="input-range" id="calificacion-range" type="range" min="1" max="5" step="1" value="3">
                                    <span id="calificacion-value">3</span>

                                    <label for="titulo-nueva-resenia">Título:</label>
                                    <input type="text" id="titulo-nueva-resenia">

                                    <label for="contenido-nueva-resenia">Contenido:</label>
                                    <textarea id="contenido-nueva-resenia"></textarea>

                                    <input type="submit" value="Enviar reseña" class="btn-enviar-resenia">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </body>

    <script src="js/consumo-api.js"></script>
    <script src="js/resenia.js"></script>
    <script src="js/productos-carrito.js"></script>
</html>
