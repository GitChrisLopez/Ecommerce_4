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
        <title>Pago exitoso - Transferencia</title>
        <link rel="stylesheet" href="./styles/styles-pago-transferencia.css" />
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
                        <h3>Cuenta a transferir el dinero</h3>
                        <br />
                        <span class="texto-pago">
                            Por favor, realiza tu pago usando los siguientes datos.  
                            Recuerda incluir tu número de pedido como referencia para identificar tu pago correctamente.
                        </span>
                        <br />
                        <span class="texto-pago">
                            Usted cuenta con 24 horas para realizar su pago; de lo contrario, este será cancelado.
                        </span>

                        <br /><br />

                        <div class="titulos-pago">
                            <span class="titulo-p">
                                <span class="espacio">---------</span>
                                Referencias de pago
                                <span class="espacio">-----------------------------</span>
                                Clave
                                <span class="espacio">
                                    -------------------------------------------------------------------
                                </span>
                                Referencia
                            </span>
                        </div>

                        <div class="referencias-pago">

                            <span class="titulo-pago1">Referencias de pago</span>

                            <div class="grupo">
                                <span class="titulo-pago">Clave</span>

                                <div class="fila">
                                    <span class="texto-referencia1">HSBC</span>
                                    <span class="texto-referencia2">0493</span>
                                </div>

                                <div class="fila">
                                    <span class="texto-referencia1">BBVA</span>
                                    <span class="texto-referencia2">9590</span>
                                </div>

                                <div class="fila">
                                    <span class="texto-referencia1">SANTANDER</span>
                                    <span class="texto-referencia2">3256940</span>
                                </div>

                                <div class="fila">
                                    <span class="texto-referencia1">BANORTE</span>
                                    <span class="texto-referencia2">1405</span>
                                </div>
                            </div>

                            <div class="grupo">
                                <span class="titulo-pago">Referencia</span>

                                <div class="fila">
                                    <span class="texto-referencia3">HSBC</span>
                                    <span class="texto-referencia2">15201486</span>
                                </div>

                                <div class="fila">
                                    <span class="texto-referencia3">BBVA</span>
                                    <span class="texto-referencia2">35632844</span>
                                </div>

                                <div class="fila">
                                    <span class="texto-referencia3">SANTANDER</span>
                                    <span class="texto-referencia2">85987420</span>
                                </div>

                                <div class="fila">
                                    <span class="texto-referencia3">BANORTE</span>
                                    <span class="texto-referencia2">78952163</span>
                                </div>
                            </div>

                        </div>

                        <br /><br />

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

