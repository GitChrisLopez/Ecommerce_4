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
        <title>Pedido realizado Contraentrega</title>
        <link rel="stylesheet" href="./styles/styles-contraentrega-pedido.css" />
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
                        <h3>Pago contra entrega (efectivo o tarjeta al recibir)</h3>
                        <span class="texto-pago">
                            Tu pedido se pagará directamente al momento de la entrega.
                        </span>

                        <div class="alerta-pago">
                            <img
                                src="https://us.123rf.com/450wm/saryuuu10/saryuuu102201/saryuuu10220100950/181152006-icono-moderno-de-precaución-y-advertencia-icono-de-signo-de-exclamación-vector.jpg?ver=6"
                                alt="icono de advertencia"
                                class="icono-alerta"
                                />

                            <span class="texto-pago1">
                                Por favor, asegúrate de tener el monto exacto o una tarjeta válida
                                para completar el pago con el repartidor.
                            </span>
                        </div>

                        <ul class="lista-detalles">
                            <li>El pago se realizará únicamente al recibir el paquete.</li>
                            <li>Asegúrate de que tú o alguien autorizado esté disponible para recibir el pedido.</li>
                            <li>En caso de no poder recibirlo, contacta a soporte antes de la fecha de entrega.</li>
                            <li>Si eliges pago con tarjeta, el repartidor llevará terminal móvil.</li>
                        </ul>
                        <br/>

                        <div class="botones">
                            <a href="./pedido-realizado-exitosamente.jsp" class="btn-link">
                                <button class="btn-aceptar">Aceptar</button>
                            </a>
                        </div>

                    </div>
                </main>
            </div>
        </div>
    </body>
</html>
