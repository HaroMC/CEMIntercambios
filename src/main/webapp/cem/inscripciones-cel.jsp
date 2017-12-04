<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../WEB-INF/menu-cem.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Inscripciones CEL </title>
    </head>
    <body>
        <div class="container">
            <h2> Estado de inscripciones : : Centros de Estudio Locales </h2>
            <p>
                Si necesita buscar algo, puede hacerlo desde aquí
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
                        <th> CEL </th>
                        <th> Programa </th>
                        <th> Estado de la inscripción </th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="ic" items="${inscripcionesCel}" >
                        <tr>
                            <td> <c:out value="${ic.rutCel.persona.nombreCompleto}" /> </td>
                            <td> <c:out value="${ic.codPrograma.nombrePrograma}" /> </td>
                            <td>
                                <c:choose>
                                    <c:when test="${ic.estado == 1}">
                                        Postulando
                                    </c:when>
                                    <c:when test="${ic.estado == 2}">
                                        Inscrito
                                    </c:when>                                   
                                    <c:otherwise>
                                        Rechazado
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form method="post" action="inscripciones-cel?accion=modificar">
                                    <input type="hidden" name="codigo"
                                           value="<c:out value="${ic.codigo}" />" />
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
            // Filtro
            $(document).ready(function () {
                $("#myInput2").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $("#myTable2 tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase()
                                .indexOf(value) > -1);
                    });
                });
            });
        </script>
        <script>
            function habilitar() {
                //$(document).ready(function () {
                //$('#btn-editar').click(function () {
                if ($('#estadoInsc').attr('disabled') === true) {
                    $('#estadoInsc').prop('disabled', false);
                    $('#btn-aceptar').prop('disabled', false);
                }
                //});
                //});
            }
        </script>
        <script>
            function deshabilitar() {
                //$(document).ready(function () {
                //$('#btn-aceptar').click(function () {
                $('#estadoInsc').prop('disabled', true);
                $('#btn-aceptar').prop('disabled', true);
                //}
                //});
            }
        </script>
    </body>
</html>
