<%-- 
    Document   : admin-menu-administrador
    Created on : 18 nov 2025, 17:20:51
    Author     : romom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Usuarios</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 

        <link rel="stylesheet" href="./styles/styles-nav.css" />
        <link rel="stylesheet" href="./styles/styles-usuarios.css" />
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file ="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <main>
                <div class="container-users">
                    <div class="title-search">
                        <h1>Usuarios registrados</h1>

                        <div class="search-bar">
                            <input type="search" placeholder="Buscar" id="input-search" />
                            <label for="input-search">
                                <img src="./icons/lupa.png" />
                            </label>
                        </div>
                    </div>

                    <div class="container-users-list">
                        <div class="user-data-headers">
                            <p></p>
                            <p>Nombre</p>
                            <p>Teléfono</p>
                            <p>Correo</p>
                            <p></p>
                        </div>

                        <div class="container-user">
                            <div class="container-image-user">
                                <img src="./icons/user-1.png" />
                            </div>

                            <div class="user-data">
                                <p>Lucia Lagarda</p>
                                <p><span>Teléfono:</span> 6442132659</p>
                                <p><span>Correo:</span> luciaLagar050102@hotmail.com</p>
                            </div>

                            <div class="buttons-user">
                                <button class="button-deactivate-account">Desactivar</button>
                                <button class="button-delete-account">Eliminar</button>
                            </div>
                        </div>

                        <!-- 2 -->
                        <div class="container-user">
                            <div class="container-image-user">
                                <img src="./icons/user-2.png" />
                            </div>

                            <div class="user-data">
                                <p>Víctor Almendra</p>
                                <p><span>Teléfono:</span> 6441236594</p>
                                <p><span>Correo:</span> xxviktorxxAL@gmail.com</p>
                            </div>

                            <div class="buttons-user">
                                <button class="button-deactivate-account">Desactivar</button>
                                <button class="button-delete-account">Eliminar</button>
                            </div>
                        </div>

                        <!-- 3 -->
                        <div class="container-user">
                            <div class="container-image-user">
                                <img src="./icons/user-3.png" />
                            </div>

                            <div class="user-data">
                                <p>Catarina Demacia</p>
                                <p><span>Teléfono:</span> 5512089743</p>
                                <p><span>Correo:</span> CatiDemacia367@yahoo.com.mx</p>
                            </div>

                            <div class="buttons-user">
                                <button class="button-deactivate-account">Desactivar</button>
                                <button class="button-delete-account">Eliminar</button>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>
