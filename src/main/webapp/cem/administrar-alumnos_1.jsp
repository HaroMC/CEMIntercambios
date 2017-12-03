<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../WEB-INF/menu-cem.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Administrar alumnos </title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <br/>
        <div class="container">
            <h2>Alumnos registrados en el sistema</h2>      
            <p>Si necesitas buscar un Alumno en especifico puedes hacerlo aqui:</p>
            <input class="form-control" id="myInput" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Rut</th>
                        <th>Nombre completo</th>
                        <th>Nombre de usuario</th>
                        <th>Teléfono</th>
                        <th>Correo</th>
                        <th>Morosidad</th>
                        <th>Eliminar</th>
                        <th>Consultar notas</th>
                    </tr>
                </thead>
                <tbody id="myTable">
                    <c:forEach var="a" items="${listadoAlumnos}">
                        <tr>
                            <td> <c:out value="${a.rutPersona}"/> </td>
                            <td> <c:out value="${a.persona.nombreCompleto}"/> </td>
                            <td> <c:out value="${a.persona.usuario.nombre}"/> </td>
                            <td> <c:out value="${a.persona.telefono}"/> </td>
                            <td> <c:out value="${a.persona.correo}"/> </td>
                            <td>
                                <c:choose>
                                    <c:when test="${a.esMoroso == 0}">
                                        No presenta mora.
                                    </c:when>
                                    <c:otherwise>
                                        Moroso.
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form action="cem-alumnos?accion=eliminar"
                                      method="post">
                                    <input type="hidden" name="rut"
                                           value="<c:out value="${a.rutPersona}"/>"/>
                                    <button type="submit"
                                            class="btn btn-primary center-block">
                                        <i class="glyphicon glyphicon-minus"></i>
                                    </button>
                                </form>
                            </td>
                            <td>
                                <form action="#?accion=modificar"
                                      method="post">
                                    <button type="submit"
                                            class="btn btn-primary center-block">
                                        <i class="">Notas</i>
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="container">
            <h2>Notas por asignaturas segun programa</h2>                
            <p>Si necesitas buscar algo especifico puedes hacerlo aqui:</p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Nombre completo</th>
                        <th>Programa</th>
                        <th>Estado</th>
                        <th>Asignatura</th>
                        <th>Nota 1</th>
                        <th>Nota 2</th>
                        <th>Nota 3</th>
                        <th>Nota Final</th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <tr>
                        <td>Cecilia Fernanda Moreno Lira</td>
                        <td>Programa1</td>  
                        <td>Terminado</td>
                        <td>Asignatura 1</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>Asignatura 2</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>Asignatura 3</td> 
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Cecilia Fernanda Moreno Lira</td>
                        <td>Programa2</td>  
                        <td>Cursando</td>
                        <td>Asignatura 1</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>Asignatura 2</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>Asignatura 3</td> 
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>           
        </div>
        <!-- <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        -->
    </body>
    <script>
        $(document).ready(function () {
            $("#myInput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function () {
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
</html>
