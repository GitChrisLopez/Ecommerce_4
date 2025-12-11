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
        <title>Edición Datos de Cuenta</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-formulario.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
        <script src="js/perfil.js" defer></script>
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file="/WEB-INF/fragmentos/NavBar.jspf" %>
            </div>

            <main>
                <div class="c2">
                    <h1 class="edicion-title">Editar perfil</h1>

                    <form class="edicion-form">
                        <div class="form-inputs">

                            <div class="form-individual">
                                <label for="nombres">Nombres</label>
                                <input type="text" id="nombres" name="nombres" placeholder="Nombre" required>
                            </div>

                            <div class="form-individual">
                                <label for="apellido-paterno">Apellido paterno</label>
                                <input type="text" id="apellido-paterno" name="apellido-paterno" placeholder="Apellido paterno" required>
                            </div>

                            <div class="form-individual">
                                <label for="apellido-materno">Apellido materno</label>
                                <input type="text" id="apellido-materno" name="apellido-materno" placeholder="Apellido materno" required>
                            </div>

                            <div class="form-individual">
                                <label for="telefono">Teléfono</label>
                                <input type="text" id="telefono" name="telefono" placeholder="Teléfono" required>
                            </div>

                            <div class="form-individual">
                                <label for="email">Correo electrónico</label>
                                <input type="email" id="email" name="email" placeholder="Correo electrónico" required>
                            </div>
                        </div>

                        <div class="form-btns">
                            <div class="edicion-btns">
                                <button type="submit" class="guardar-btn">Guardar cambios</button>
                            </div>
                        </div>
                    </form>

                </div>
            </main>

        </div> 
    </body>
</html>
