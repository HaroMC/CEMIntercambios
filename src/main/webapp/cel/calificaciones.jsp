<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@include file="../WEB-INF/menu_cel.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Calificaciones alumno </title>
    </head>
    <body>
        <div class="container">
            <h2> Programas impartidos </h2>
            <p> Filtra tu busqueda aquí: </p>
            <input class="form-control" id="myInput" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> Nombre del programa </th>
                        <th> Asignatura </th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody id="myTable">
                    <c:forEach var="a" items="${programasInscritos}">
                        <tr>
                            <td><c:out value="${a.nombrePrograma}"/></td>
                            <td>
                                <c:forEach var="b" items="${a.asignaturaList}">
                                    <c:out value="${b.nombreAsignatura}"/> <br />
                                </c:forEach>
                            </td>
                            <td>
                                <form method="post" action="cel_programas">
                                    <input type="hidden" name="accion" value="ver_notas_alumno" />
                                    <input type="hidden" name="codigoPrograma" value="${a.codigo}" />
                                    <input type="submit" value="Selecccionar"
                                           class="btn btn-primary" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="container">
            <h2> Alumnos inscritos en el programa </h2>                
            <p> Filtra tu busqueda aquí: </p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Escribe lo que buscas.">
            <br/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> Alumno(a) </th>
                        <th> Programa </th>
                        <th> Asignatura </th>
                        <th> Nota 1 </th>
                        <th> Nota 2 </th>
                        <th> Nota 3 </th>
                        <th> Examen </th>
                        <th> Promedio </th>
                        <th>  </th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="adp" items="${alumnosDelPrograma}">
                        <tr>
                            <td> <c:out value="${adp.alumno.persona.nombreCompleto}" /> </td>
                            <td> <c:out value="${adp.programa.nombrePrograma}" /> </td>
                            <c:forEach var="asi" items="${adp.programa.asignaturaList}">
                                <td> <c:out value="${asi.nombreAsignatura}" /> </td>
                                <c:set var="totalNotas" value="0" scope="page" />
                                <c:forEach var="cal" items="${asi.calificacionList}">
                                    <td> <c:out value="${cal.nota}" /> </td>
                                    <c:set var="totalNotas" value="${totalNotas + 1}" scope="page"/>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${totalNotas == 0}">
                                        <td>-</td> <td>-</td> <td>-</td> <td>-</td>
                                    </c:when>
                                    <c:when test="${totalNotas == 1}">
                                        <td>-</td> <td>-</td> <td>-</td>
                                    </c:when>
                                    <c:when test="${totalNotas == 2}">
                                        <td>-</td> <td>-</td>
                                    </c:when>
                                    <c:when test="${totalNotas == 3}">
                                        <td>-</td>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                            <td>-</td>
                            <td>
                                <form method="post" action="cel_programas">
                                    <input type="submit" value="Calificar" class="btn btn-primary" />
                                    <input type="hidden" name="accion" value="agregar_notas" />
                                    <input type="hidden" name="rutAlumno" value="${fn:escapeXml(adp.alumno.rutPersona)}" />
                                    <input type="hidden" name="codigoPrograma" value="${fn:escapeXml(adp.programa.codigo)}" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $("#myInput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>
    <script>
        $(document).ready(function () {
            $("#myInput2").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#myTable2 tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>
</html>
