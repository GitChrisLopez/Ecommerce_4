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
        <title>Agregar Dirección</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-agregar-editar-direccion.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <main>
                <div class="c2">
                    <div class="direccion-container">
                        <h1 class="direccion-title">Editar dirección</h1>
                        <form action="./direcciones-guardadas.jsp" class="direccion-form">
                            <div class="form-inputs">

                                <div class="form-individual">
                                    <label for="codigo-postal">Código Postal:</label>
                                    <select id="codigo-postal" name="codigo-postal" required>
                                        <option value="45050">45050</option>
                                    </select>
                                </div>

                                <div class="form-individual">
                                    <label for="colonia">Colonia</label>
                                    <input type="text" id="colonia" name="colonia" value="Jardines del Sol" required>
                                </div>

                                <div class="form-individual">
                                    <label for="calle">Calle</label>
                                    <input type="text" id="calle" name="calle" value="De los Cipreses" required>
                                </div>

                                <div class="form-individual">
                                    <label for="numero">Número</label>
                                    <input type="text" id="numero" name="numero" value="248" required>
                                </div>
                            </div>

                            <div class="form-btns">

                                <div class="direccion-btns">
                                    <button type="submit" class="guardar-btn">Guardar</button>
                                    <a href="./direcciones-guardadas.jsp" class="cancelar-btn">Cancelar</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </main>
        </div> 
    </body>
</html>
