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
        <title>Direcciones Guardadas</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-direcciones-guardadas.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <div class="c2">

                <form action="./proceder-pago.jsp" class="direcciones-container">
                    <h1 class="direcciones-title">Direcciones guardadas</h1>

                    <div class="general-container">

                        <div>
                            <input type="radio" id="direccion1" name="direccion" required>
                            <label class="direccion-individual" for="direccion1">
                                <p class="direccion-texto">Calle de los Cipreses #248, Colonia Jardines del Sol</p>
                                <a href="./editar-direccion.jsp" class="editar-btn">
                                    <img src="./icons/edition.png" alt="editar" class="editar-icon">
                                </a>
                            </label>
                        </div>

                        <div>
                            <input type="radio" id="direccion2" name="direccion" required>
                            <label for="direccion2" class="direccion-individual">
                                <p class="direccion-texto">Calle Azul #1, Colonia Colores</p>
                                <a href="./editar-direccion.jsp" class="editar-btn">
                                    <img src="./icons/edition.png" alt="editar" class="editar-icon">
                                </a>
                            </label>
                        </div>

                        <div>
                            <input type="radio" id="direccion3" name="direccion" required>
                            <label for="direccion3" class="direccion-individual">
                                <p class="direccion-texto">Calle Nainari #123, Colonia Morelos</p>
                                <a href="./editar-direccion.jsp" class="editar-btn">
                                    <img src="./icons/edition.png" alt="editar" class="editar-icon">
                                </a>
                            </label>
                        </div>

                    </div>

                    <div class="direcciones-btns">
                        <a href="./admin-agregar-direccion.jsp" class="agregar-direccion-btn">Agregar dirección</a>
                        <button type="submit" class="escoger-direccion-btn">Escoger</button>
                    </div>
                </form>
            </div>
        </div>  
    </body>
</html>
