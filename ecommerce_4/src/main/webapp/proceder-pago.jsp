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
        <title>Confirmación Pedido</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles-proceder-pago.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/styles-nav.css" />
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <input type="hidden" id="idCliente" value="${sessionScope.usuarioLogueado.id}">

            <div class="c2">
                <main>
                    <div class="pago-container">
                        <h3>Resumen del Pedido</h3>

                        <div class="resumen-compra" id="contenedor-resumen">
                            <p>Cargando detalles...</p>
                        </div>

                        <br /><br />

                        <form id="form-crear-pedido" class="direccion-container" onsubmit="return false;">
                            <label for="direccion"><strong>Enviar a:</strong></label>
                            <br /><br />

                            <input
                                class="input-direccion"
                                type="text"
                                id="direccion"
                                name="direccion"
                                placeholder="Seleccione una dirección..."
                                readonly
                                required
                                />

                            <br /><br />

                            <div class="botones-direccion">
                                <a href="${pageContext.request.contextPath}/direcciones-guardadas.jsp" class="btn-link">
                                    <button type="button" class="btn-direccion">
                                        Seleccionar dirección
                                    </button>
                                </a>

                                <button type="submit" class="btn-realizar-pedido">
                                    Confirmar y Pagar
                                </button>
                            </div>
                        </form>
                    </div>
                </main>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/js/consumo-api.js"></script>
        <script src="${pageContext.request.contextPath}/js/pedido.js"></script>
    </body>
</html>