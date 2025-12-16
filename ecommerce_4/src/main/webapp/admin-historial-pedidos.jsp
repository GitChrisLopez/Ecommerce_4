<%--
    Document   : admin-historial-pedidos
    Created on : 18 nov 2025, 22:45:24
    Author     : norma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Historial de Pedidos</title>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css" /> 
        <link rel="stylesheet" type="text/css" href="./styles/styles-historial-pedidos-admin.css" />

    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file ="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <main>
                <div class="c2">
                    <div class="search-box">
                        <form action="${pageContext.request.contextPath}/admin-filtrar-pedidos" method="GET"> 
                            <div class="buscador">
                                <input 
                                    type="text" 
                                    name="busqueda" 
                                    placeholder="Buscar por Número de Pedido" 
                                    value="${param.busqueda != null ? param.busqueda : ''}" 
                                    />
                                <button type="submit" style="display:none;">Buscar</button>
                            </div>
                        </form>
                    </div>

                    <div class="pedidos-container">
                        <h1 class="historial-pedidos-title">Historial de pedidos</h1>
                        <div class="lista-pedidos">

                            <c:choose>
                                <c:when test="${not empty listaPedidos}">
                                    <c:forEach var="pedido" items="${listaPedidos}">
                                        <div class="pedido">
                                            <div class="pedido-info">
                                                <div class="info-1">
                                                    <p>Pedido #${pedido.numeroUnico}</p>
                                                    <p>${pedido.cliente.nombre}</p>
                                                    <p>${pedido.estado}</p>
                                                    <p><span>${pedido.metodoPago}</span></p>
                                                </div>

                                                <div class="info-2">
                                                    <p><span>Total del pedido: </span></p>
                                                    <p>$<c:out value="${pedido.total}"/> MXN</p>
                                                </div>

                                            </div>
                                            <div class="pedido-btn">
                                                <a href="${pageContext.request.contextPath}/admin-detalles-pedido?id=${pedido.id}" class="detalles-btn">Ver detalles</a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <p>No se encontraron pedidos para mostrar.</p>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>