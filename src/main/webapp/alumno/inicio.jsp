<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../WEB-INF/menu-alumno.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="row">
            <div class="col-sm-3 col-md-4">                  
                <img id="logoUsuario" src="../res/img/logo_alumnos.jpg"
                     style="width:30%; margin-left:70%" >
            </div>
            <div class="col-sm-3 col-md-4">
                <p>
                    Alumno: <c:out value="${usuarioActual.persona.nombreCompleto}" />
                </p>
                <p>
                    País de residencia: <c:out value="${usuarioActual.persona.pais}" />
                </p>
                <p>
                    Ciudad: <c:out value="${usuarioActual.persona.ciudad}" />
                </p>
                <p>
                    Correo: <c:out value="${usuarioActual.persona.correo}" />
                </p>
                <p>
                    Teléfono: <c:out value="${usuarioActual.persona.telefono}" />
                </p>
            </div>
        </div>
        <br/>
        <div class="container">
            <h3>Selecciona un programa</h3>      
            <p>Filtra tu busqueda aqui:</p>
            <input class="form-control" id="myInput" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> Programa </th>
                        <th>  </th>
                        <th> Valor del programa </th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="p" items="${listadoProgramas}" >
                        <tr>
                            <td>
                                <c:out value="${p.codPrograma.codigo}" />
                            </td>
                            <td>
                                <c:out value="${p.codPrograma.nombrePrograma}" />
                            </td>
                            <td>
                                <c:out value="${p.codPrograma.nombrePrograma}" />
                            </td>
                            <td>
                                <c:out value="${p.codPrograma.nombrePrograma}" />
                            </td>
                            <td>
                                <form method="get" action="alumno-postulaciones">
                                    
                                    <input type="hidden" name="accion"
                                           value="${fn:escapeXml("seleccionar_familia")}" />
                                    <input type="hidden" name="pais"
                                           value="${fn:escapeXml(p.rutCel.persona.pais)}" />
                                    <input type="hidden" name="programa"
                                           value="${fn:escapeXml(p.codPrograma.codigo)}" />
                                    
                                    <button type="submit" class="btn btn-primary">
                                        Postular
                                    </button>
                                    
                                </form>
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

        <br/>
        <br/>
        <div class="container">
            <h2>Notas por asignaturas segun programa</h2>                
            <p>Si necesitas buscar algo especifico puedes hacerlo aqui:</p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Nombre  Completo</th>
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

    </body>
</html>
