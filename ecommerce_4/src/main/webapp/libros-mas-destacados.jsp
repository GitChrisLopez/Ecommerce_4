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
        <title>Libros Destacados</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-pedidos-realizados.css">
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <div class="c2">
                <main>
                    <div class="catalogo-container">
                        <div class="area-total-container">
                            <h1 class="titulo">Libros más destacados</h1>

                            <div class="libros-container">

                                <button class="libro">
                                    <img src="./imgs/morir-en-la-arena.png" alt="Morir en la arena">
                                    <h3>Morir en la arena</h3>
                                    <p>Leonardo Padura</p>
                                    <p class="precio">$250.00</p>
                                </button>

                                <button class="libro">
                                    <img src="./imgs/la-novela-de-mi-vida.png" alt="La novela de mi vida (Andanzas)">
                                    <h3>La novela de mi vida (Andanzas)</h3>
                                    <p>Leonardo Padura</p>
                                    <p class="precio">$300.00</p>
                                </button>

                            </div>
                        </div>
                    </div> 
                </main>
            </div>
        </div>
    </body>    
</html>
