<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Link Bootstrap CSS -->
        <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css" >
        <!--Link J.S.-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <h2> Selecciona un alumno </h2>
            <p>
                Si necesitas buscar un alumno específico, puedes hacerlo
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
                        <th> Estado </th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                <c:forEach var="sa" items="${alumnosPorPrograma}" >
                    <tr>
                    <td>
                    <c:out value="${sa.a}" />
                    </td>
                    <td>
                    <c:out value="${sa.a}" />
                    </td>
                    <td>
                    <c:out value="${sa.a}" />
                    </td>
                    <td>
                        <button type="button" class="btn btn-primary">
                            seleccionar
                        </button>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
