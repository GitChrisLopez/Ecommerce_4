<%-- 
Document   : comunidad
Created on : 19 nov 2025, 4:10:47 p.m.
Author     : chris
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
    <head>
        <title>Historial de Pagos</title>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-historial-pagos-admin.css"/>

    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file ="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <main>
                <div class="c2">
                    <div class="search-box">
                        <d class="buscador">
                            <input type="text" placeholder="Buscar" />
                        </d>
                    </div>

                    <div class="pagos-container">
                        <h1 class="historial-pagos-title">Historial de pagos</h1>
                        <div class="lista-pagos">
                            <div class="pago1">
                                <div class="pago-info">
                                    <p><span>Tipo de operacion:</span> Transferencia</p>
                                    <p><span>Monto recibido:</span> $320.00 MXN</p>
                                    <p><span>Estado del pago:</span> Confirmado</p>
                                    <p><span>Numero de pedido asociado:</span> #43569846</p>
                                </div>
                                <div class="pago-btn">
                                    <a href="./admin-detalles-pago.jsp" class="detalles-btn"
                                       >Detalles</a
                                    >
                                </div>
                            </div>

                            <div class="pago2">
                                <div class="pago-info">
                                    <p><span>Tipo de operacion:</span> Pago por tarjeta</p>
                                    <p><span>Monto recibido:</span> $200.00 MXN</p>
                                    <p><span>Estado del pago:</span> Confirmado</p>
                                    <p><span>Numero de pedido asociado:</span> #23519306</p>
                                </div>
                                <div class="pago-btn">
                                    <a href="./admin-detalles-pago.jsp" class="detalles-btn"
                                       >Detalles</a
                                    >
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>
