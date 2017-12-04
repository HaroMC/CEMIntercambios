<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@include file="../WEB-INF/menu-cel.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h2>Para colocar una calificacion selecciona un programa</h2>      
            <p>Filtra tu busqueda aqui:</p>
            <input class="form-control" id="myInput" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Nombre del programa </th>
                        <th>Asignatura </th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="myTable">
                    <c:forEach var="a" items="${programasInscritos}">
                        <tr>
                            <td><c:out value="${a.nombrePrograma}"/></td>
                            <td>
                                <c:forEach var="b" items="${a.asignaturaList}">
                                    <c:out value="${b.nombreAsignatura}"/> <br />
                                </c:forEach>
                            </td>
                            <td>
                                <button type="button" class="btn btn-primary">
                                    Selecccionar
                                </button>
                            </td>
                        </tr>      
                    </c:forEach>
                </tbody>
            </table>
            <!--
                        <button type="button" class="btn btn-primary">
                            Rechazar
                        </button>
            -->
        </div>
    </body>
</html>
