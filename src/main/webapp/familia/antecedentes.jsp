<%-- 
    Document   : antecedentes
    Created on : 04-12-2017, 17:30:35
    Author     : Bugueño
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/menu-familia.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <div class="container">
            <h1>aqui se dene subir los antecedentes</h1>
            <br/>
            <form action="" method="" enctype="multipart/form-data">
                <h4>Cerificado de antecedentes</h4>
                <input type="file" name="file" size="50" />
                <br />
                <h4>Certificado de residencia</h4>
                <input type="file" name="file" size="50" />
                <br />
                <h4>Fotos del lugar de residencia</h4>
                <input type="file" name="file" size="50" />
                <input type="file" name="file" size="50" />
                <input type="file" name="file" size="50" />
                <br />
                <input type="submit" value="subir archivo" />
            </form>
        </div>
    </body>
</html>
