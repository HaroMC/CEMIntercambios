<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../WEB-INF/menu-cem.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title> Administración de programas </title>
    </head>
    <body>
        <div class="container">
            <h2> Programas registrados </h2>
            <p>
                Si necesita buscar un programa en específico, 
                puedes hacerlo aquí:
            </p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Filtrar por nombre del programa"
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
                        <th>Nombre</th>
                        <th>Fecha de salida</th>
                        <th>Fecha de retorno</th>
                        <th>Total de cupos</th>
                        <th>Cupos restantes</th>
                        <th>Valor</th>
                        <th>Estado</th>
                        <th>Modificar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="p" items="${listadoProgramas}" >
                        <tr>
                            <td> <c:out value="${p.codigo}" /> </td>
                            <td> <c:out value="${p.nombrePrograma}" /> </td>
                            <td>
                                <fmt:formatDate dateStyle="long" type="date"
                                                value="${p.fechaInicio}" />
                            </td>
                            <td>
                                <fmt:formatDate dateStyle="long" type="date"
                                                value="${p.fechaTermino}" />
                            </td>
                            <td> <c:out value="${p.cupos}" /> </td>
                            <td> - </td>
                            <td>
                                <fmt:formatNumber type="currency"
                                                  currencySymbol="$"
                                                  value="${p.valor}"
                                                  currencyCode="CLP" />
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${p.estado == 1}">
                                        Sin CEL asignado
                                    </c:when>
                                    <c:when test="${p.estado == 2}">
                                        Disponible
                                    </c:when>
                                    <c:when test="${p.estado == 3}">
                                        Sin cupos
                                    </c:when>
                                    <c:when test="${p.estado == 4}">
                                        Iniciado
                                    </c:when>
                                    <c:when test="${p.estado == 5}">
                                        Terminado
                                    </c:when>
                                    <c:otherwise>
                                        Definir
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form action="cem-programas?accion=modificar"
                                      method="post">
                                    <input type="hidden" name="codigo"
                                           value="<c:out value="${p.codigo}" />" />
                                    <button type="submit"
                                            class="btn btn-primary center-block">
                                        <i class="glyphicon glyphicon-pencil"></i>
                                    </button>
                                </form>
                            </td>
                            <td>
                                <form action="cem-programas?accion=eliminar"
                                      method="post">
                                    <input type="hidden" name="codigo"
                                           value="<c:out value="${p.codigo}" />" />
                                    <button type="submit"
                                            class="btn btn-primary center-block">
                                        <i class="glyphicon glyphicon-minus"></i>
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
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });
        </script>
    </body>
</html>
