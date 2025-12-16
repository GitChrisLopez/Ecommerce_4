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
        <title>Error Pago Pedido</title>
        <link rel="stylesheet" href="./styles/styles-error-pago-pedido.css" />
        <link rel="stylesheet" href="./styles/styles-nav.css" />
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <div class="c2">
                <main>
                    <div class="pago-container">
                        <div class="texto-contenido">
                            <h3>Ups... algo salió mal con tu pago.</h3>

                            <span class="texto-pago">
                                Puede que haya un problema temporal con tu banco o método de pago.
                            </span>

                            <span class="texto-pago">
                                Intenta nuevamente en unos minutos o usa otra tarjeta.
                            </span>
                        </div>

                        <div class="imagen">
                            <img src="./imgs/error-pagina.png" class="imagen-error" />
                        </div>

                        <div class="botones">
                            <a href="./carrito.jsp" class="btn-link">
                                <button class="btn-volver">Volver al carrito</button>
                            </a>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    </body>
</html>
