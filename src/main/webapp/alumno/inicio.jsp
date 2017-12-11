<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../WEB-INF/menu_alumno.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Perfil de control </title>
    </head>
    <body>
        <!--<div class="row">
            <div class="col-sm-3 col-md-4">                  
                <img id="logoUsuario" src="../res/img/logo_alumnos.jpg"
                     style="width:30%; margin-left:70%" >
            </div>
            <div class="col-sm-3 col-md-4">
                <p>
                    Alumno: < c:out value="$ {usuarioActual.persona.nombreCompleto}" />
                </p>
                <p>
                    País de residencia: < c:out value="$ {usuarioActual.persona.pais}" />
                </p>
                <p>
                    Ciudad: < c:out value="$ {usuarioActual.persona.ciudad}" />
                </p>
                <p>
                    Correo: < c:out value="$ {usuarioActual.persona.correo}" />
                </p>
                <p>
                    Teléfono: < c:out value="$ {usuarioActual.persona.telefono}" />
                </p>
            </div>
        </div>-->

        <div class="row">         
            <div class="col-sm-3 col-md-12 text-center" style="width: 80%; margin-left: 150px">
                <fieldset>
                    <legend>Datos del Usuario</legend>
                    <div class="col-md-6 text-right">
                        <p> <b>Alumno:</b> <c:out value="${usuarioActual.persona.nombreCompleto}" /> </p>
                        <p> <b>País de residencia:</b> <c:out value="${usuarioActual.persona.pais}" /> </p>
                        <p> <b>Ciudad:</b> <c:out value="${usuarioActual.persona.ciudad}" /> </p>
                    </div>
                    <div class="col-md-6 text-left">
                        <p> <b>Correo:</b> <c:out value="${usuarioActual.persona.correo}" /> </p>
                        <p> <b>Teléfono:</b> <c:out value="${usuarioActual.persona.telefono}" /> </p>
                    </div>
                </fieldset>
            </div>
        </div>

        <br/>

        <div class="container">
            <h3>Selecciona un programa</h3>      
            <p>Filtra tu búsqueda aquí:</p>
            <input class="form-control" id="myInput" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br />
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> Programa </th>
                        <th> Centro de estudios </th>
                        <th> Ubicación </th>
                        <th> Fecha de inicio </th>
                        <th> Fecha de término </th>
                        <th> Valor </th>
                        <th> Estado de la inscripción </th>
                        <!--<th> Cancelar </th>-->
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <c:forEach var="ia" items="${inscripcionesAlumno}" >
                        <tr>
                            <td> <c:out value="${ia.programa.nombrePrograma}" /> </td>
                            <td>
                                <c:forEach var="nom" items="${ia.programa.inscripcionCelList}">
                                    <c:out value="${nom.centroEstudiosLocal.persona.nombreCompleto}" />
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="loc" items="${ia.programa.inscripcionCelList}">
                                    <c:out value="${loc.centroEstudiosLocal.persona.domicilio}" />,
                                    <c:out value="${loc.centroEstudiosLocal.persona.ciudad}" />,
                                    <c:out value="${loc.centroEstudiosLocal.persona.pais}" />
                                </c:forEach>
                            </td>
                            <td> <fmt:formatDate dateStyle="short" type="date" value="${ia.programa.fechaInicio}" /> </td>
                            <td> <fmt:formatDate dateStyle="short" type="date" value="${ia.programa.fechaTermino}" /> </td>
                            <td> <c:out value="${ia.programa.valor}" /> </td>
                            <td>
                                <c:choose>
                                    <c:when test="${ia.estado == 1}">
                                        Postulando
                                    </c:when>
                                    <c:when test="${ia.estado == 2}">
                                        Postulando
                                    </c:when>
                                    <c:when test="${ia.estado == 3}">
                                        No seleccionado
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <form method="post" action="../inicializar_perfil">
                                    <input type="hidden" name="accion" value="consultar" />
                                    <input type="hidden" name="rutAlumno" value="${ia.alumno.rutPersona}" />
                                    <input type="hidden" name="codPrograma" value="${ia.programa.codigo}" />
                                    <button type="submit" class="btn btn-primary center-block">
                                        Consultar
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <br /> <br />

        <div class="container">
            <h2>Notas por asignaturas segun programa</h2>                
            <p>Si necesitas buscar algo especifico puedes hacerlo aqui:</p>
            <input class="form-control" id="myInput2" type="text"
                   placeholder="Escribe aca lo que buscas..">
            <br/>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th> Nombre completo </th>
                        <th> Programa </th>
                        <th> Estado </th>
                        <th> Asignatura </th>
                        <th> Nota 1 </th>
                        <th> Nota 2 </th>
                        <th> Nota 3 </th>
                        <th> Examen </th>
                        <th> Promedio </th>
                    </tr>
                </thead>
                <tbody id="myTable2">
                    <tr>
                        <td> <c:out value="${inscPrograAsigCaliAlum.alumno.persona.nombreCompleto}" /> </td>
                        <td> <c:out value="${inscPrograAsigCaliAlum.programa.nombrePrograma}" /> </td>
                        <td> <c:out value="${inscPrograAsigCaliAlum.estado}" /> </td>
                        <c:forEach var="asi" items="${inscPrograAsigCaliAlum.programa.asignaturaList}">
                            <td> <c:out value="${asi.nombreAsignatura}" /> </td>
                            <c:set var="totalNotas" value="0" scope="page" />
                            <c:forEach var="cal" items="${asi.calificacionList}">
                                <c:set var="totalNotas" value="${totalNotas + 1}" scope="page"/>
                                <td> <c:out value="${cal.nota}" /> </td>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${totalNotas == 0}">
                                    <td>-</td> <td>-</td> <td>-</td> <td>-</td>
                                </c:when>
                                <c:when test="${totalNotas == 1}">
                                    <td>-</td> <td>-</td> <td>-</td>
                                </c:when>
                                <c:when test="${totalNotas == 2}">
                                    <td>-</td> <td>-</td>
                                </c:when>
                                <c:when test="${totalNotas == 3}">
                                    <td>-</td>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        <td>-</td>
                    </tr>
                </tbody>
            </table>           
        </div>
    </body>
</html>
