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
        <title>Método de Pago</title>
        <link rel="stylesheet" href="./styles/styles-metodo-pago.css" />
        <link rel="stylesheet" href="./styles/styles-nav.css" />
        <script src="/js/pago-pedido.js"></script>
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <div class="c2">
                <main>
                    <div class="pago-container">
                        <h3>Método de pago</h3>
                        <br />
                        <span class="texto-pago">Seleccione un método de pago</span>
                        <br /><br />
                        <div class="metodos-pago">
                            <div class="pago-detalles"></div>
                            <form action="./pedido-realizado-exitosamente.jsp">
                                <div class="radio-btn-opciones">

                                    <label class="container">
                                        Transferencia
                                        <input
                                            type="radio"
                                            name="metodo"
                                            value="Transferencia"
                                            checked
                                            />
                                        <span class="checkmark"></span>
                                    </label>

                                    <label class="container">
                                        Tarjeta de débito/crédito
                                        <input
                                            type="radio"
                                            name="metodo"
                                            value="Tarjeta de débito/crédito"
                                            />
                                        <span class="checkmark"></span>
                                    </label>

                                    <label class="container">
                                        Contraentrega
                                        <input 
                                            type="radio" 
                                            name="metodo" 
                                            value="Contraentrega" 
                                            />
                                        <span class="checkmark"></span>
                                    </label>

                                </div>

                                <br /><br />

                                <div class="div-botones">
                                    <a href="./carrito.jsp" class="btn-link">
                                        <button type="button" class="btn-cancelar">Cancelar pedido</button>
                                    </a>
                                    <button type="submit" class="btn-pagar-pedido">Pagar pedido</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    </body>
</html>

