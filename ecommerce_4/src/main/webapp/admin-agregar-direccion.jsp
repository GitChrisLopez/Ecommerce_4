<%-- 
    Document   : comunidad
    Created on : 19 nov 2025, 4:10:47 p.m.
    Author     : chris
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Agregar Dirección</title>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css" />
        <link rel="stylesheet" type="text/css" href="./styles/styles-admin-agregar.css"/>    
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file ="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <main>
                <div class="general-container">

                    <div class="container-add-edition">

                        <div class="title-container">
                            <h1>Agregar dirección</h1>
                        </div>

                        <form class="form-add-edition" id="form-direccion">

                            <div>
                                <label for="codigo-postal">Código Postal:</label>
                                <select id="codigo-postal" name="codigo-postal" class="input-address" required>
                                    <option value="" disabled selected>Selecciona un código postal</option>
                                    <option value="45050">45050</option>
                                </select>
                            </div>

                            <div>
                                <label for="colonia">Colonia:</label>
                                <input type="text" id="colonia" name="colonia" class="input-address" required />
                            </div>

                            <div>
                                <label for="calle">Calle:</label>
                                <input type="text" id="calle" name="calle" class="input-address" required />
                            </div>

                            <div>
                                <label for="numero">Número:</label>
                                <input type="text" id="numero" name="numero" class="input-address" required />
                            </div>

                            <div class="dir-btns-mobile">
                                <a href="./direcciones-guardadas.html">
                                    <input type="button" value="Cancelar" class="btn-dir-cancel" />
                                </a>
                                <input type="submit" value="Agregar" class="btn-dir-save" />
                            </div>

                        </form>
                    </div> <div class="dir-btns-desktop">
                        <a href="./direcciones-guardadas.html">
                            <input type="button" value="Cancelar" class="btn-dir-cancel" />
                        </a>
                        <input type="submit" value="Agregar" class="btn-dir-save" form="form-direccion" />
                    </div>

                </div>
            </main>
        </div>
    </body>
</html>