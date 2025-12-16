<%--
    Document : admin-detalles-pedido
    Created on : 19 nov 2025, 00:46:23
    Author : norma
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Detalles de Pedido</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-detalles-pedido-admin.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
    </head>

    <body>
        <div class="body-container">

            <!-- NAV — AHORA COLOCADO CORRECTAMENTE -->
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <!-- CONTENIDO -->
            <main>
                <div class="c2">
                    <c:if test="${not empty pedido}">
                        <div class="pedido-container">
                            <div class="pedido1">

                                <div class="detalles-pago">
                                    <h3>Detalles del pago:</h3>
                                    <p><span>Tipo de pago:</span> ${pedido.metodoPago}</p>
                                    <p><span>Estado del pedido:</span> <strong>${pedido.estado}</strong></p>
                                    <p><span>Fecha de pago:</span> ${pedido.metodoPago.fecha}</p>
                                    <p><span>Número de pedido:</span> <strong>#${pedido.numeroUnico}</strong></p>
                                </div>

                                <div class="detalles-cliente">
                                    <h3>Detalles del cliente:</h3>
                                    <p><span>Nombre:</span> ${pedido.cliente.nombre}</p>
                                    <p><span>Correo electrónico:</span> ${pedido.cliente.correo}</p>
                                    <p><span>Teléfono:</span> ${pedido.cliente.telefono}</p>
                                    <p><span>Dirección de envío:</span> 
                                        ${pedido.direccionEnvio.calle} #${pedido.direccionEnvio.numero}, 
                                        Col. ${pedido.direccionEnvio.colonia}, 
                                        C.P. ${pedido.direccionEnvio.codigoPostal}
                                    </p>
                                </div>

                                <div class="detalles-productos">
                                    <h3>Productos del pedido:</h3>
                                    <c:forEach var="producto" items="${pedido.productosPedido}">
                                        <p>"${producto.producto.libro.titulo}" – Cantidad: ${producto.cantidad}</p>
                                        <p><span>Precio Unitario:</span> $${producto.precioUnitario} MXN</p>
                                        <br>
                                    </c:forEach>

                                    <p><span>TOTAL:</span> <strong>$${pedido.total} MXN</strong></p>
                                </div>

                            </div>

                            <div class="pedido-btns">
                                <div class="btn-1">
                                    <a href="${pageContext.request.contextPath}/admin-mostrar-pedidos" class="volver-btn">
                                        Volver
                                    </a>
                                </div>

                                <div class="btn-2">

                                    <div class="estado-acciones">
                                        <button class="actualizar-estado-btn" type="button">Actualizar estado</button>

                                        <div class="estado-dropdown">
                                            <form method="POST" action="${pageContext.request.contextPath}/admin-actualizar-pedido">
                                                <input type="hidden" name="id" value="${pedido.id}">
                                                <input type="hidden" name="accion" value="actualizar">
                                                <button class="opcion-estado" type="submit" name="nuevoEstado" value="PENDIENTE">Pendiente</button>
                                                <button class="opcion-estado" type="submit" name="nuevoEstado" value="ENVIADO">Enviado</button>
                                                <button class="opcion-estado" type="submit" name="nuevoEstado" value="ENTREGADO">Entregado</button>
                                            </form>
                                        </div>
                                    </div>

                                    <form method="POST" action="${pageContext.request.contextPath}/admin-actualizar-pedido">
                                        <input type="hidden" name="id" value="${pedido.id}">
                                        <input type="hidden" name="accion" value="cancelar">
                                        <button class="cancelar-btn" type="submit">Cancelar Pedido</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${empty pedido}">
                        <p class="error-msg">No se encontró el pedido solicitado.</p>
                    </c:if>
                </div>
            </main>

        </div> 
    </body>
</html>
