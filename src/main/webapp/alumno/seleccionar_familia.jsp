<%@ page contentType = "text/html" pageEncoding = "UTF-8" %>
<%@ include file = "../WEB-INF/menu_alumno.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Seleccionar familia </title>
        <link rel="stylesheet" 
             href="../bootstrap-3.3.7-dist/css/terminosYcondiciones.css">
    </head>
    <body>           

        <div class="container well">         
            <h2 class="text-center">
                Para elegir una familia debe aceptar los
                <a href="acuerdo.jsp"> terminos y condiciones </a>
                <input type="checkbox" name="check" id="check" value="1"
                       onchange="javascript:showContent()" />
            </h2>                                   
        </div>
        <br/>
        <div class="container" id="content" style="display: none;">
            <h2>
                Seleccione una familia con la cual quedarse  </h2>
            <p>Si necesitas buscar un programa en especifico puedes hacerlo aqui:
            </p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> Codigo </th>
                        <th> Nombre del representante familiar </th>  
                        <th> País de residencia </th>
                        <th> Ciudad </th>
                        <th> Domicilio </th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="lf" items="${listadoFamilias}" >
                        <tr>
                            <td>
                                <c:out value="${lf.rutPersona}" />
                            </td>
                            <td>
                                <c:out value="${lf.persona.nombreCompleto}" />
                            </td>
                            <td>
                                <c:out value="${lf.persona.pais}" />
                            </td>
                            <td>
                                <c:out value="${lf.persona.ciudad}" />
                            </td>
                            <td>
                                <c:out value="${lf.persona.domicilio}" />
                            </td>
                            <td>
                                <form method="post" action="alumno_postulaciones">

                                    <input name="accion" type="hidden"
                                           value="${fn:escapeXml("confirmar_postulacion")}" />
                                    <input name="programa" type="hidden"
                                           value="${fn:escapeXml(param.programa)}" />
                                    <input name="rutFamilia" type="hidden"
                                           value="${fn:escapeXml(lf.rutPersona)}" />
                                    <button type="submit" class="btn btn-primary">
                                        Elegir
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
                        $(this).toggle($(this).text().toLowerCase()
                                .indexOf(value) > -1);
                    });
                });
            });
        </script>
        <script type="text/javascript">
            function showContent() {
                element = document.getElementById("content");
                check = document.getElementById("check");
                if (check.checked) {
                    element.style.display = 'block';
                } else {
                    element.style.display = 'none';
                }
            }
        </script>
    </body>
</html>
