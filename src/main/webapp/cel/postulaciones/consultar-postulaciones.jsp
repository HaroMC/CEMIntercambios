<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../../WEB-INF/menu-cel.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Programas disponibles </title>
    </head>
    <body>
        <div class="container">
            <h2> Programas dispnibles para postular </h2>      
            <p>
                Si necesitas buscar un programa específico, puedes hacerlo
                desde aquí
            </p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br/>
            <div class="form-group">
                <label class="col-sm-2 control-label">
                    <c:out value="${mensajeResultado}" />
                </label>
            </div>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> Nombre </th>
                        <th> Fecha de inicio </th>   
                        <th> Fecha de término </th>
                        <th> Estado </th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="pd" items="${programasDisponibles}" >
                        <tr>
                            <td>
                                <c:out value="${pd.nombrePrograma}" />
                            </td>
                            <td>
                                <c:out value="${pd.fechaInicio}" />
                            </td>
                            <td>
                                <c:out value="${pd.fechaTermino}" />
                            </td>
                            <td>
                                <c:if test="${pd.estado == 1}">
                                    Sin Cel Asignado
                                </c:if>
                            </td>
                            <td>
                                <input type="hidden" name="${pd.codigo}" />
                                <button type="button" class="btn btn-primary">
                                    Postular
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $("#myInput3").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#myTable3 tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase()
                            .indexOf(value) > -1);
                });
            });
        });
    </script>
</html>
