<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Prueba de comunicación con Web Service </title>
    </head>
    <body>
        <h2> Prueba de comunicación con el servicio web </h2>
        <form method="post" action="WebServiceConsumerServlet">
            Rut del alumno: ( <input type="text" name="rut" /> )
            <input type="submit" value="Consultar" />
        </form>
        <br />
        RESPUESTA : <c:out value="${mensajeEstado}" />
        <br />
        <br />
        <c:if test="${alumnoEncontrado != null}">
            <table border="0" cellspacing="1" cellpadding="1">
                <tbody>
                    <tr>
                        <td>Alumno</td>
                        <td>:</td>
                        <td>
                            <c:out value="${alumnoEncontrado.nombreCompleto}" />
                        </td>
                    </tr>
                    <tr>
                        <td>Rut</td>
                        <td>:</td>
                        <td>
                            <c:out value="${alumnoEncontrado.rutPersona}" />
                        </td>
                    </tr>
                    <tr>
                        <td>Carrera</td>
                        <td>:</td>
                        <td>
                            <c:out value="${alumnoEncontrado.nombreCarrera}" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
