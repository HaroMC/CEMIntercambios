<%-- 
    Document   : postulaciones
    Created on : 04-12-2017, 15:26:54
    Author     : Bugueño
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/menu-alumno.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <div class="container">
            <h2>Programas a los cuales puede postular </h2>      
            <p>Si necesitas buscar un programa en especifico puedes hacerlo aqui:</p>
            <input class="form-control" id="myInput2" type="text" placeholder="Escribe aca lo que buscas..">
            <br/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>  
                        <th></th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="p" items="${listadoProgramasAlumnos}" >
                        <tr>
                            <td> <c:out value="${p.codigo}" /> </td>
                            <td> <c:out value="${p.nombre}" /> </td>
                            <td><button type="button" class="btn btn-primary">Postular</button></td>
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
