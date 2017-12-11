<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../WEB-INF/menu_cem.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Administrar alumnos </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <br/>
        <div class="container">
            <h2>
                Alumnos registrados en el sistema
            </h2>
            <form method="post" action="actualizar_alumnos_registrados">
                <button type="submit"
                        class="btn btn-default">
                    <i class=""> Actualizar datos de alumnos </i>
                </button>
            </form>
            <p> Filtrar su búsqueda de alumnos </p>
            <input class="form-control" id="myInput" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Rut</th>
                        <th>Nombre completo</th>
                        <th>Nombre de usuario</th>
                        <th>Teléfono</th>
                        <th>Correo</th>
                        <th>Morosidad</th>
                        <th>Eliminar</th>
                        <th>Consultar notas</th>
                    </tr>
                </thead>
                <tbody id="myTable">
                    <c:forEach var="a" items="${listadoAlumnos}">
                        <tr>
                            <td> <c:out value="${a.rutPersona}"/> </td>
                            <td> <c:out value="${a.persona.nombreCompleto}"/> </td>
                            <td> <c:out value="${a.persona.usuario.nombreUsuario}"/> </td>
                            <td> <c:out value="${a.persona.telefono}"/> </td>
                            <td> <c:out value="${a.persona.correo}"/> </td>
                            <td>
                                <c:choose>
                                    <c:when test="${a.esMoroso == 0}">
                                        No presenta mora.
                                    </c:when>
                                    <c:otherwise>
                                        Moroso.
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form action="cem_alumnos" method="post">
                                    <input type="hidden" name="accion"
                                           value="<c:out value="eliminar_alumno"/>"/>
                                    <input type="hidden" name="rut"
                                           value="<c:out value="${a.rutPersona}"/>"/>
                                    <button type="submit"
                                            class="btn btn-primary center-block">
                                        <i class="glyphicon glyphicon-minus"></i>
                                    </button>
                                </form>
                            </td>
                            <td>
                                <form action="cem_alumnos" method="post">
                                    <input type="hidden" name="accion"
                                           value="<c:out value="ver_notas"/>"/>
                                    <input type="hidden" name="rut"
                                           value="<c:out value="${a.rutPersona}"/>"/>
                                    <button type="submit"
                                            class="btn btn-primary center-block">
                                        <i class=""> Notas </i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="container">
            <h2>Notas por asignaturas segun programa</h2>                
            <p>Si necesitas buscar algo especifico puedes hacerlo aqui:</p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Escribe aca lo que buscas..">
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
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="ia" items="${inscripcionesAl}">
                        <c:forEach var="asig" items="${inscripcionesAl.programa.asignaturaList}">
                            <tr>
                                <td> <c:out value="${inscripcionesAl.alumno.persona.nombreCompleto}" /> </td>

                                <td> <c:out value="${inscripcionesAl.programa.nombrePrograma}" /> </td>

                                <td> <c:out value="${asig.nombreAsignatura}" /> </td>

                                <c:set var="totalNotas" value="0" scope="page"/>
                                <c:forEach var="cal" items="${asig.calificacionList}">
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

                                <td> - </td>

                            </tr>
                        </c:forEach>
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
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                });
            });
        });
    </script>
    <script>
        $(document).ready(function () {
            $("#myInput2").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#myTable2 tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                });
            });
        });
    </script>
</html>
