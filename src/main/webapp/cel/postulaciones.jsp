<%-- 
    Document   : postulaciones
    Created on : 04-12-2017, 14:06:37
    Author     : Bugueño
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@include file="../WEB-INF/menu-cel.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <div class="container">
            <h2>Programas dispnibles para postular</h2>      
            <p>Si necesitas buscar un programa específico, puedes hacerlo desde aquí</p>
            <input class="form-control" id="myInput2" type="text" placeholder="Escribe aca lo que buscas..">
            <br/>
            <div class="form-group">
                <label class="col-sm-2 control-label">${mensaje}</label>
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
                    <c:forEach var="p" items="${programasDisponibles}" >
                        <tr>
                            <td>
                                <c:out value="${p.nombrePrograma}" />
                            </td>
                            <td>
                                <c:out value="${p.fechaInicio}" />
                            </td>
                            <td>
                                <c:out value="${p.fechaTermino}" />
                            </td>
                            <td>
                                <c:if test="${p.estado == 1}">
                                Sin Cel Asignado
                                </c:if>
                            </td>
                            <td>
                                <button type="button" class="btn btn-primary">
                                    Postular
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
        </div>
             <div class="container">
            <h2>Programas a los que estas postulando </h2>      
            <p>Si necesitas buscar un programa a los cuales has postulado hazlo aqui:</p>
            <input class="form-control" id="myInput3" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>Fecha de Inicio</th>
                        <th>Fecha de Termino</th>
                        <th>Valor</th>
                        <th>Estado</th>
                    </tr>
                </thead>

                <tbody id="myTable3">
                    <tr>
                        <td>001</td>
                        <td>Ingles</td>
                        <td>11/06/1017</td>
                        <td>11/07/1017</td>
                        <td>$1.800.000</td>
                        <td>En Espera</td>
                    </tr>
                    <tr>
                        <td>002</td>
                        <td>Recursos Humanos</td>
                        <td>11/06/1017</td>
                        <td>11/07/1017</td>
                        <td>$1.400.000</td>
                        <td>Aceptado</td>
                    </tr>   
                </tbody>
            </table>
            <button type="button" class="btn btn-primary">
                Eliminar Postulación
            </button>
        </div>

        <script>
            $(document).ready(function () {
                $("#myInput3").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $("#myTable3 tr").filter(function () {
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
    </body>
</html>
