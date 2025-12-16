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
        <title>Pago Tarjeta</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-pago-tarjeta.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
        <script src="/js/transferencia.js"></script>
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <main>
                <div class="c2">

                    <div class="pago-tarjeta-container">
                        <h1 class="pago-tarjeta-title">Datos de la tarjeta</h1>

                        <form class="pago-tarjeta-form" action="${pageContext.request.contextPath}/pago-de-pedido" method="POST">
                            <input type="hidden" name="id" value="<%= request.getParameter("id")%>">
                            <input type="hidden" name="metodoPago" value="tarjeta">

                            <div class="pago-tarjeta-btns">
                                <button type="submit" class="confirmar-pago-btn">Confirmar pago</button>
                            </div>
                        </form>

                    </div>

                </div>
            </main>

        </div> 
    </body>

</html>
