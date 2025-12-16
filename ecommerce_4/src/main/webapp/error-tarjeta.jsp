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
        <title>Error Pago Tarjeta</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-error-tarjeta.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <main>
                <div class="c2">
                    <div class="error-tarjeta-container">
                        <h1 class="error-tarjeta-title">Ups... algo salió mal con tu pago.</h1>

                        <div class="general-container">

                            <div class="mensanje-error">
                                <p>Puede que haya un problema temporal con tu banco o método de pago.</p>
                                <p>Intenta nuevamente en unos minutos o usa otra tarjeta.</p>
                            </div>

                            <div class="error-container">
                                <img src="./icons/error-tarjeta.png" alt="Error tarjeta">
                            </div>

                        </div>

                        <div class="error-btn">
                            <a href="./carrito.jsp" class="volver-carrito-btn">Volver al carrito</a>
                        </div>
                    </div>
                </div>
            </main>
        </div> 
    </body>
</html>
