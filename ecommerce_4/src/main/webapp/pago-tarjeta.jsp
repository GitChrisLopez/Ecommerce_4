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

                        <form class="pago-tarjeta-form">

                            <div class="form-inputs">

                                <div class="form-individual">
                                    <label for="titular">Nombre del titular</label>
                                    <input type="text" id="titular" name="titular" required>
                                </div>

                                <div class="form-individual">
                                    <label for="num-tarjeta">Número de tarjeta</label>
                                    <input type="number" id="num-tarjeta" name="num-tarjeta" required>
                                </div>

                                <div class="form-individual">
                                    <label for="fecha-vencimiento">Fecha de vencimiento</label>
                                    <input type="number" id="fecha-vencimiento" name="fecha-vencimiento" required>
                                </div>

                                <div class="form-individual">
                                    <label for="cvv">CVV</label>
                                    <input type="number" id="cvv" name="cvv" required>
                                </div>

                            </div>

                            <div class="form-btns">

                                <div class="pago-tarjeta-btns">
                                    <button type="submit" class="confirmar-pago-btn">Confirmar pago</button>
                                    <a href="/metodo-pago.jsp" class="cancelar-btn">Cancelar</a>
                                </div>

                            </div>

                        </form>

                    </div>

                </div>
            </main>

        </div> 
    </body>

</html>
