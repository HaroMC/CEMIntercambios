<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/menu_alumno.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Programas disponibles </title>
    </head>
    <body>
        <div class="container">
            <h2>Programas a los cuales puede postular </h2>      
            <p>Si necesitas buscar un programa en especifico puedes hacerlo aqui:</p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>País</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="p" items="${listadoProgramas}" >
                        <c:if test="${listadoProgrmas.estado != 1}">
                            <tr>
                                <td>
                                    <c:out value="${p.programa.codigo}" />
                                </td>
                                <td>
                                    <c:out value="${p.programa.nombrePrograma}" />
                                </td>
                                <td>
                                    <c:out value="${p.programa.valor}" />
                                </td>
                                <td>
                                    <c:out value="${p.centroEstudiosLocal.persona.pais}" />
                                </td>
                                <td>
                                    <form method="get" action="alumno_postulaciones">
                                        <input type="hidden" name="accion"
                                               value="${fn:escapeXml("seleccionar_familia")}" />
                                        <input type="hidden" name="pais"
                                               value="${fn:escapeXml(p.centroEstudiosLocal.persona.pais)}" />
                                        <input type="hidden" name="programa"
                                               value="${fn:escapeXml(p.programa.codigo)}" />
                                        <button type="submit" class="btn btn-primary">
                                            Postular
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script>
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
    </body>
</html>
