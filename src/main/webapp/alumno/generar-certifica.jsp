<%-- 
    Document   : generar-certifica
    Created on : 09-12-2017, 4:53:24
    Author     : FrancisBrianPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/menu-alumno.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certificado de aprobación</title>
    </head>
    <body>
        <h1>Pulse acá para generar certificado!</h1>
        <form action="generar-certificado-servlet" method="get">
            <button class="btn btn-primary" type="submit" onclick="window.open(this.href,'popUpWindow')">Obtener Archivo </button>
        </form>
    </body>
</html>
