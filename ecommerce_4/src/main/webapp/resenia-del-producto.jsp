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
        <script src="js/resenia.js"></script>
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <main>
                <div class="producto-container">
                    <div class="info-libro">
                        <img src="./imgs/morir-en-la-arena.png" alt="Morir en la arena (Andanzas)" />
                        <div class="detalles-libro">
                            <h3>Morir en la arena (Andanzas)</h3>
                            <label>Autor: Leonardo Padura</label>

                            <div class="calificacion">
                                <p>3</p>
                                <div class="estrella"></div>
                                <div class="estrella"></div>
                                <div class="estrella"></div>
                                <div class="estrella"></div>
                    </div>

                            <p class="p-editorial">Editorial: TusQuets Editores</p>
                            <p class="p-publicacion">Publicación: 23 de septiembre, 2025</p>
                            <p class="p-no-paginas">384 Páginas</p>
                            <p class="p-formato">Tapa blanda</p>
                            <p class="p-precio">$250.00</p>
                            <p class="p-stock">3 disponibles</p>

                            <form action="carrito.jsp" class="div-add-cart">
                                <label for="product-quantity" class="label-cantidad">Cantidad:</label>
                                <input type="number" id="product-quantity" class="product-quantity" min="0" max="3" required>
                                <input type="submit" value="Agregar al carrito" class="btn-add-cart">
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
                                Narra la historia de Rodolfo, un hombre jubilado cuyo pasado vuelve a acecharlo cuando su hermano Geni,
                                un asesino convicto, es excarcelado debido a una enfermedad incurable. La trama se desarrolla a lo largo
                                de una semana, mientras Rodolfo espera la llegada de su hermano a la casa familiar, un evento que reaviva viejos...
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
                                        <span>Carlos Gutiérrez (Tú)</span>
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

</html>
