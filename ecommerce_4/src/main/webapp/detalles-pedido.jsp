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
        <title>Detalles Pedido</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-detalles-pedido.css">
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
                    <div class="pedido-container">
                        <h1 class="pedido-title">Detalles del pedido</h1>
                        <div class="info-pedido-general">
                            <div class="info-pedido">
                                
                            </div>
                        </div>
                        <div class="productos-container">
                            
                        </div>
                        
                        <div class="pedido-btn">
                            <a href="./pedidos-realizados.jsp" class="ver-pedidos-btn">Volver</a>
                        </div>
                    </div>
                </div>
            </main>
        </div> 
    </body>
</html>
