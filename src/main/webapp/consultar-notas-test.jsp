<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Prueba </title>
    </head>
    <body>
        <table border="1" cellspacing="2" cellpadding="2" sortable="true">
            <thead>
                <tr>
                    <th sorted="true">Rut</th>
                    <th>Alumno</th>
                    <th>Programas que registra</th>
                    <th>Asignatura</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="alumno" items="${alumnos}">
                    <tr>
                        <td> <c:out value="${alumno.rutPersona}" /> </td>
                        <td> <c:out value="${alumno.persona.nombreCompleto}" /> </td>
                        <c:forEach var="postulacion" items="${alumno.postulacionesAlumnosList}">
                            <c:if test="${postulacion.estado == 'Inscrito'}">
                                <td> <c:out value="${postulacion.codPrograma.nombre}" /> </td>
                                <td> <c:out value="${postulacion.codPrograma.codAsignatura.nombreAsignatura}" /> </td>
                            </c:if>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


        <!--
        <form method="post" action="pruebas">
            <input type="hidden" value="nada" />
            <input type="submit" value="Consultar notas" />
        </form>
        -->
    </body>
</html>
