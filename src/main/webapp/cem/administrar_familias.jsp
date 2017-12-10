<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="../WEB-INF/menu_cem.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h2> Familias registradas </h2>
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
                        <th>Rut jefe de familia</th>
                        <th>Cantidad integrantes</th>
                        <th>Estado</th>
                        <th>Editar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                <c:forEach var="p" items="${listadoFamilias}" >
                    <tr>
                        <td> <c:out value="${p.rutPersona}" /> </td>
                    <td> <c:out value="${p.cantidadIntegrantes}" /> </td>
                    <td> <c:out value="${p.estado}" /> </td>
                    <td>                                   
                        <button type="submit"
                                class="btn btn-primary center-block">
                            <i class="glyphicon glyphicon-pencil"></i>
                        </button>
                    </td>
                    <td>                            
                        <button type="submit"
                                class="btn btn-primary center-block">
                            <i class="glyphicon glyphicon-minus"></i>
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
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });
        </script>
    </body>
</html>
