<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/menu-cem.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h2> Listado de alumnos postulantes a programas</h2>
            <p>
                Si necesita buscar qlgo, 
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
                        <th>Cod.</th>
                        <th>Fecha Inscripcion</th>
                        <th>Estado</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="ia" items="${inscrpcionesAlumnos}" >
                        <tr>
                            <td> <c:out value="${ia.codigo}" /> </td>

                            <td>
                                <fmt:formatDate dateStyle="long" type="date"
                                                value="${ia.fechaInscripcion}" />
                            </td>

                            <td> <c:choose>
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
                                <button type="submit"
                                        class="btn btn-primary center-block">
                                    <i>Aceptar</i>
                                </button>                              
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
