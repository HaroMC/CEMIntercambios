<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/menu_cem.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Postulaciones alumnos </title>
    </head>
    <body>
        <div class="container">
            <h2> Listado de alumnos postulantes a programas</h2>
            <p>
                Si necesita buscar algo, 
                puedes hacerlo aquí:
            </p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder=""
                   autofocus="true">
            <br />
            <div class="form-group">
                <label class="col-sm-2 control-label">
                    <c:out value="${mensajeEstado}" />
                </label>
            </div>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> Alumno </th>
                        <th> Programa inscrito </th>
                        <th> Estado </th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="ia" items="${inscripcionesAlumnos}" >
                        <tr>
                            <td> <c:out value="${ia.alumno.persona.nombreCompleto}" /> </td>
                            <td> <c:out value="${ia.programa.codigo}" /> </td>
                            <td>
                                <c:choose>
                                    <c:when test="${ia.estado == 1}">
                                        Postulando
                                    </c:when>
                                    <c:when test="${ia.estado == 2}">
                                        Inscrito
                                    </c:when>                                   
                                    <c:otherwise>
                                        Rechazado
                                    </c:otherwise>
                                </c:choose>                           
                            </td>
                            <td>
                                <form method="post" action="inscripciones_alumnos">
                                    
                                    <input type="hidden" name="accion"
                                           value="${fn:escapeXml("modificar")}" />
                                    
                                    <input type="hidden" name="codigoPrograma"                                           
                                           value="${fn:escapeXml(ia.inscripcionAlumnoPK.codPrograma)}" />
                                    
                                    <input type="hidden" name="rutAlumno"
                                           value="${fn:escapeXml(ia.inscripcionAlumnoPK.rutAlumno)}" />
                                    
                                    <button type="submit" id="btn-aceptar"
                                            class="btn btn-primary center-block">
                                        <i> Ver detalles </i>
                                    </button>
                                    
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
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
    </body>
</html>
