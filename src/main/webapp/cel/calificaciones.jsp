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
                        <th> Alumno </th>
                        <th> Programa </th>
                        <th> Notas </th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="adp" items="${alumnosDelPrograma}">
                        <tr>
                            <td> <c:out value="${adp.alumno.persona.nombreCompleto}" /> </td>
                            <td> <c:out value="${adp.programa.nombrePrograma}" /> </td>
                            <td>
                                <form method="post" action="cel_programas">
                                    <input type="submit" value="Ver" class="btn btn-primary" />
                                    <input type="hidden" name="accion" value="agregar_notas" />
                                    <input type="hidden" name="rut" value="${fn:escapeXml(adp.alumno.rutPersona)}" />
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
