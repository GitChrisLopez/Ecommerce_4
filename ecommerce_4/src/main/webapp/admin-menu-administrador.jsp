

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Menú principal - Administrador</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="./styles/styles-nav.css">
        <link rel="stylesheet" type="text/css" href="./styles/styles-catalogo-menu-administrador.css">
    </head>

    <body>
        <div class="body-container">
            <div class="c1">
                <%@ include file ="/WEB-INF/fragmentos/AdminNavBar.jspf" %>
            </div>

            <main>
                <div class="c2">

                    <div class="catalogo-container">

                        <form class="sidebar" action="admin-menu-principal" method="GET">
                            <div class="options-lookup">
                                <div class="search-filtros">
                                    <a href="./catalogo-filtros-mobile.html" class="filtrar-btn">
                                        <img src="./icons/filtro.png" alt="Filtro" class="icono-filtro"> 
                                        <span>Filtrar</span>
                                    </a>
                                </div>
                                <div class="search-box">
                                    <input type="text" name="titulo-autor-buscado" placeholder="Buscar por Libro o Autor" value="${param.busqueda}">
                                </div>
                            </div>

                            <input type="submit" value="Aplicar filtros" class="btn-aplicar-filtros">

                            <div class="filtros">
                                <h2>Categoría</h2>
                                <c:forEach var="categoria" items="${listaCategorias}">

                                    <c:set var="categoriaEstaMarcada" value="false" />
                                    <c:forEach var="idCategoriaSeleccionada" items="${paramValues.categorias}">
                                        <c:if test="${idCategoriaSeleccionada eq categoria.id.toString()}">
                                            <c:set var="categoriaEstaMarcada" value="true" />
                                        </c:if>
                                    </c:forEach>

                                    <label>
                                        <input type="checkbox" 
                                               name="categorias" 
                                               value="${categoria.id}" 
                                               ${categoriaEstaMarcada ? 'checked' : ''}> 
                                        <c:out value="${categoria.nombre}" />
                                    </label>
                                </c:forEach>
                            </div>

                            <div class="filtros">
                                <h2>Formato</h2>
                                <c:forEach var="entradaFormato" items="${mapaFormatos}">

                                    <c:set var="formatoEstaMarcado" value="false" />
                                    <c:forEach var="claveFormatoSeleccionado" items="${paramValues.formatos}">
                                        <c:if test="${claveFormatoSeleccionado eq entradaFormato.key}">
                                            <c:set var="formatoEstaMarcado" value="true" />
                                        </c:if>
                                    </c:forEach>

                                    <label>
                                        <input type="checkbox" 
                                               name="formatos" 
                                               value="${entradaFormato.key}" 
                                               ${formatoEstaMarcado ? 'checked' : ''}> 
                                        <c:out value="${entradaFormato.value}" />
                                    </label>
                                </c:forEach>
                            </div>

                            <div class="filtros">
                                <h2>Precio</h2>
                                <p>Mínimo: $100</p>
                                <input type="range" 
                                       name="precioMinimo" 
                                       min="0" 
                                       max="5000" 
                                       value="${param.precioMinimo != null ? param.precioMinimo : '0'}">

                                <p>Máximo: $800</p>
                                <input type="range" 
                                       name="precioMaximo" 
                                       min="0" 
                                       max="5000" 
                                       value="${param.precioMaximo != null ? param.precioMaximo : '5000'}">
                            </div>

                        </form>

                        <div class="button-add-container">
                            <a href="admin-nuevo-producto">
                                <button type="button">Agregar producto</button>
                            </a>
                        </div>
                        
                        <div class="area-total-container">
                            <div class="libros-container">
                                
                                <c:choose>
                                    
                                    <c:when test="${empty listaProductos}">
                                        
                                        <div>No hay productos registrados.</div>
                                        
                                    </c:when>
                                        
                                    <c:otherwise>
                                        
                                        <c:forEach var="producto" items="${listaProductos}">
                                            
                                            <div class="libro">
                                                <img src="${pageContext.request.contextPath}/${producto.urlImagen}" 
                                                    alt="${producto.libro.titulo}" />
                                                <h3><c:out value="${producto.libro.titulo}" /></h3>
                                                <p><c:out value="${producto.libro.autor.nombre}" /> <c:out value="${producto.libro.autor.apellidoPaterno}" /></p>

                                                <p class="precio">$<c:out value="${producto.precio}" /></p>

                                                <div class="options-product">
                                                    <a href="admin-edicion-producto?id=${producto.id}">Editar datos</a>
                                                </div>

                                                <div class="options-product">
                                                    <a href="admin-edicion-producto?id=${producto.id}">Editar datos</a>
                                                    
                                                    <c:choose>
                                                        
                                                        <c:when test="${producto.stock > 0}">
                                                            
                                                            <form class="form-marcar-agotado" action="admin-actualizar-producto?id=${producto.id}" method="POST">
                                                                
                                                                <input type="hidden" name="id" value="${producto.id}">
                                        
                                                                <input type="hidden" name="accion" value="agotar">
                                                                
                                                                <input type="submit" value="Marcar como agotado">
                                                                
                                                            </form>
                                                            
                                                        </c:when>
                                                        
                                                    </c:choose>
                                                    
                                                </div>
                                                    
                                                    
                                            </div>

                                        </c:forEach>

                                    </c:otherwise>
                                    
                                </c:choose>

                            </div>
                        </div>
                    </div> 
                </div>
            </main>
        </div>
    </body>    
</html>
