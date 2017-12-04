<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/menu-cem.jsp" %>

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
                        <th> Rut </th>
                        <th> CEL </th>
                        <th> Programa </th>
                        <th> Fecha de postulación </th>
                        <th> Fecha de inscripción </th>
                        <th> Estado de la <br /> inscripción </th>
                        <th> Cambiar estado </th>
                        <th> Guardar cambios </th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="lpc" items="${listadoPostulacionesCel}" >
                        <tr>
                            <td> <c:out value="${lpc.rutCel.persona.rut}" /> </td>
                            <td> <c:out value="${lpc.rutCel.persona.nombreCompleto}" /> </td>
                            <td> <c:out value="${lpc.codPrograma.nombrePrograma}" /> </td>
                            <td>
                                <fmt:formatDate dateStyle="long" type="date"
                                                value="${lpc.fechaInscripcion}" />
                            </td>
                            <td> </td>
                            <td>
                                <select name="estado" required="true"
                                        id="estadoInsc" disabled="true">
                                    <c:choose>
                                        <c:when test="${lpc.estado == 1}">
                                            <option value="1" selected> Postulando </option>
                                            <option value="2"> Inscrito </option>
                                            <option value="3"> Rechazado </option>
                                        </c:when>
                                        <c:when test="${lpc.estado == 2}">
                                            <option value="1"> Postulando </option>
                                            <option value="2" selected> Inscrito </option>
                                            <option value="3"> Rechazado </option>
                                        </c:when>                                   
                                        <c:otherwise>
                                            <option value="1"> Postulando </option>
                                            <option value="2"> Inscrito </option>
                                            <option value="3" selected> Rechazado </option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </td>
                            <td>
                                <button type="submit" id="btn-editar"
                                        class="btn btn-primary center-block"
                                        onclick="habilitar()">
                                    <i> Estado </i>
                                </button>
                            </td>
                            <td>
                                <form method="post" action="#">
                                    <button type="submit" id="btn-aceptar"
                                            class="btn btn-primary center-block"
                                            disabled="true"
                                            onclick="deshabilitar()">
                                        <i> Aceptar </i>
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
