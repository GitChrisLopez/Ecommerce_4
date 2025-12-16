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
        <title>Registro</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-inicio-registro.css">
    </head>

    <body>
        <div class="background">
            <div class="container">

                <h1 class="register-title">Crear cuenta</h1>

                <form class="register-form">

                    <div class="form-juntos-top">
                        <div class="form-nombres-principal">
                            <label for="nombres">Nombres</label>
                            <input type="text" id="nombres" name="nombres" placeholder="Nombre" required>
                        </div>

                        <div class="form-foto-perfil">
                            <div class="perfil-icono-contenedor">
                                <img src="./icons/user.png" alt="Icono de usuario" class="perfil-imagen">
                            </div>

                            <label for="perfil-img" class="perfil-label-boton">Agregar imagen de perfil</label>

                            <input type="file" id="perfil-img" name="perfil-img" accept="image/*" style="display: none;">
                        </div>
                    </div>

                    <div class="form-juntos">
                        <div class="form-apellido-paterno">
                            <label for="apellido-paterno">Apellido paterno</label>
                            <input type="text" id="apellido-paterno" name="apellido-paterno" placeholder="Apellido paterno" required>
                        </div>
                        <div class="form-apellido-materno">
                            <label for="apellido-materno">Apellido materno</label>
                            <input type="text" id="apellido-materno" name="apellido-materno" placeholder="Apellido materno" required>
                        </div>
                    </div>

                    <div class="form-individual">
                        <label for="fecha-nacimiento">Fecha de nacimiento</label>
                        <input type="date" id="fecha-nacimiento" name="fecha-nacimiento" required>
                    </div>

                    <div class="form-individual">
                        <label for="email">Correo electrónico</label>
                        <input type="email" id="email" name="email" placeholder="Correo electrónico" required>
                    </div>

                    <div class="form-individual">
                        <label for="telefono">Teléfono</label>
                        <input type="text" id="telefono" name="telefono" placeholder="Teléfono" required>
                    </div>

                    <div class="form-individual">
                        <label for="password">Contraseña</label>
                        <input type="password" id="password" name="password" placeholder="Contraseña" required>
                    </div>

                    <button type="submit" class="button">Crear cuenta</button>

                    <p class="register-info-text">
                        <a href="iniciar-sesion.jsp" class="link">¿Ya tienes una cuenta?</a>
                    </p>

                </form>
            </div>
        </div>
    </body>

</html>
