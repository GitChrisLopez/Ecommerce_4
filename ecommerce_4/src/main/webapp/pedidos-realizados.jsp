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
        <title>Pedidos Realizados</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-pedidos-realizados.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
        <script src="js/pedido.js"></script>
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <main>
                <div class="c2">
                    <div class="pedidos-container">
                        <h1 class="pedidos-title">Pedidos realizados</h1>

                        <div class="lista-pedidos">
                        </div>
                    </div>
                </div>
            </main>
        </div> 
    </body>
</html>
