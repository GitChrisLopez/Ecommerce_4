<%-- 
Document   : comunidad
Created on : 19 nov 2025, 4:10:47 p.m.
Author     : chris
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
    <head>
        <title>Detalles de Pago</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-detalles-pago-admin.css">
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file ="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <main>
                <div class="c2">
                    <div class="pago-container">
                        <div class="pago1">
                            <div class="detalles-pago">
                                <p><span>Tipo de operación:</span> Transferencia</p>
                                <p><span>Número de referencia:</span> 845726391046567</p>
                                <p><span>Banco emisor:</span> Bancomer BBVA México</p>
                                <p><span>Cuenta receptora:</span> ****1234</p>
                                <p><span>Monto recibido:</span> $320.00 MXN</p>
                                <p><span>Fecha de operación:</span> 17/10/2025 – 10:42 a.m.</p>
                                <p><span>Número de pedido asociado:</span> #43569846</p>
                            </div>
                        </div>

                        <div class="pago-btns">
                            <a href="./admin-historial-pagos.jsp" class="volver-btn">Volver</a>
                            <a href="./admin-detalles-pedido.jsp" class="pedido-btn">Pedido</a>
                        </div>
                    </div>
                </div>
            </main>
        </div> 
    </body>
</html>
