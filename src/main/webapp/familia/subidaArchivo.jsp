<%-- 
    Document   : subidaArchivo
    Created on : 08-12-2017, 1:18:05
    Author     : FrancisBrianPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/menu_familia.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Link Bootstrap CSS -->
        <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css" >
        <!--Link J.S.-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
    </head>
    <body>
        <h1>Resultado de carga</h1>
        <ul>
            <li>Nombre del archivo:<c:out value="${fileName}"/></li>
            <li>Tipo de documento:<c:out value="${contentType}"/></li>
            <li>Tamaño de fichero:<c:out value="${size/1024.0} KB"/></li>
            <li>Ubicación:<c:out value="${ubicacion}"/></li>
        </ul>
        <a href="inicio.jsp">Volver al menú principal</a>
    </body>
</html>
