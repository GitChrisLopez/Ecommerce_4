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
        <title>Pedido Exitoso</title>
        <link rel="stylesheet" href="./styles/styles-pedido-realizado-exitosamente.css" />
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
                        <h3>¡Pedido realizado!</h3>

                        <span class="texto-pago">
                            Se ha logrado realizar la compra. Su recibo será enviado a su correo electrónico.
                        </span>

                        <p class="texto-pago1">
                            <span class="etiqueta">Número de compra:</span>
                            <span class="texto-pago2">#923942</span><br />

                            <span class="etiqueta">Fecha estimada de entrega:</span>
                            <span class="texto-pago2">23 de octubre de 2025</span><br />

                            <span class="etiqueta">Monto total a pagar:</span>
                            <span class="texto-pago2">$300.00 MXN</span><br />

                            <span class="etiqueta">Dirección de entrega:</span>
                            <span class="texto-pago2">
                                Calle de los Cipreses #248, Col. Jardines del Sol
                            </span>
                        </p>

                        <br />

                        <div class="botones">
                            <a href="./detalles-pedido.jsp" class="btn-link">
                                <button class="btn-ver-pedido">Ver pedido</button>
                            </a>
                            <a href="./catalogo.jsp" class="btn-link">
                                <button class="btn-seguir-comprando">Seguir comprando</button>
                            </a>
                        </div>

                    </div>
                </main>
            </div>
        </div>
    </body>
</html>
