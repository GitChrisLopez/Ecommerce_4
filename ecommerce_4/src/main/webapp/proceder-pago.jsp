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
        <link rel="stylesheet" href="./styles/styles-proceder-pago.css" />
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
                        <h3>Artículos</h3>

                        <div class="resumen-compra">
                            <div class="libro-detalles">
                                <p>
                                    Morir en la arena (Andanzas)
                                    <span class="espacio">----------------------------</span>
                                    <span class="costo">$300.00 MXN</span>
                                </p>
                            </div>

                            <hr />

                            <div class="linea total">
                                <p>
                                    <strong>
                                        Total
                                        <span class="espacio">
                                            ---------------------------------------------------------
                                        </span>
                                        <span class="costo">$300.00 MXN</span>
                                    </strong>
                                </p>
                            </div>
                        </div>

                        <br /><br /><br /><br />

                        <form action="./metodo-pago.jsp" class="direccion-container">
                            <label for="direccion"><strong>Enviar a:</strong></label>
                            <br /><br />

                            <input
                                class="input-direccion"
                                type="text"
                                id="direccion"
                                name="direccion"
                                readonly
                                />

                            <br /><br />

                            <div class="botones-direccion">
                                <a href="./direcciones-guardadas.jsp" class="btn-link">
                                    <button type="button" class="btn-direccion">
                                        Seleccionar dirección
                                    </button>
                                </a>

                                <input
                                    type="submit"
                                    class="btn-realizar-pedido"
                                    value="Realizar pedido"
                                    />
                            </div>
                        </form>
                    </div>
                </main>
            </div>
        </div>
    </body>
</html>

