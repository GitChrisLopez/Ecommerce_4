
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Carrito</title>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-carrito.css" />
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <input type="hidden" id="clienteIdOculto" value="${idCliente}">
            
            <main>
                <div class="area-total-container">
                    <div class="carrito-container">
                        <div class="lista-libros">
                            
                            <div class="carro" id="contenedor-productos">
                                <p>Cargando tu carrito...</p>
                            </div>
                            
                            <div class="acciones acciones-desktop">
                                <p class="subtotal">
                                    Subtotal: <span class="precio" id="subtotal-desktop">$0.00</span>
                                </p>
                            </div>
                            
                            <div class="acciones acciones-movil">
                                <p class="subtotal">
                                    Subtotal: <span class="precio" id="subtotal-movil">$0.00</span>
                                </p>
                            </div>

                        </div>
                    </div>
                </div>
            </main>
        </div>

        <script src="js/consumo-api.js"></script>
        <script src="js/carrito.js"></script>
    </body>
</html>