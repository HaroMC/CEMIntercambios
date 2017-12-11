<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../WEB-INF/menu_cel.jsp" %>

<!DOCTYPE html>
<html>
    <head>    
        <title> Calificar </title>
    </head>
    <body>
        <div class="container well">

            <div class="row">
                <div class="col-md-12">
                    <h2>
                        <p class="text-center">
                            Registrar calificaciones
                        </p>
                    </h2>
                </div>
            </div>

            <br /> <br />

            <div class="col-md-6 col-md-offset-3">
                <form action="cel_programas" method="post"
                      class="form-horizontal">
                    <fieldset>
                        <legend> Detalles de la calificación </legend>
                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Programa
                            </label>
                            <div class="col-md-8">
                                <label class=form-control">
                                    <c:out value="${programaSe.nombrePrograma}" />
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Alumno
                            </label>
                            <div class="col-md-8">
                                <label class=form-control">
                                    <c:out value="${alumnoSe.persona.nombreCompleto}" />
                                </label>
                            </div>
                        </div>
                    </fieldset>

                    <fieldset>
                        <legend> Asignaturas </legend>

                        <c:set var="largoLista" value="0" scope="page"/>
                        <c:forEach var="asi" items="${programaSe.asignaturaList}">
                            <c:set var="largoLista" value="${largoLista + 1}" scope="page"/>
                            <c:choose>
                                <c:when test="${largoLista == 1}">
                                    <c:set var="asigna1" value="${asi.nombreAsignatura}" />
                                    <c:set var="asigCod1" value="${asi.codigo}" />
                                    <input type="hidden" name="asigCod1" value="${fn:escapeXml(asi.codigo)}" />
                                </c:when>
                                <c:when test="${largoLista == 2}">
                                    <c:set var="asigna2" value="${asi.nombreAsignatura}" />
                                    <c:set var="asigCod2" value="${asi.codigo}" />
                                    <input type="hidden" name="asigCod2" value="${fn:escapeXml(asi.codigo)}" />
                                </c:when>
                                <c:when test="${largoLista == 3}">
                                    <c:set var="asigna3" value="${asi.nombreAsignatura}" />
                                    <c:set var="asigCod3" value="${asi.codigo}" />
                                    <input type="hidden" name="asigCod3" value="${fn:escapeXml(asi.codigo)}" />
                                </c:when>
                            </c:choose>
                        </c:forEach>

                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                <c:out value="${asigna1}" />
                            </label>
                            <div class="col-md-8">
                                <input type="number" class="form-control"
                                       step="0.1" min="1" max="7"
                                       name="nota1" style="width: 80px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                <c:out value="${asigna2}" />
                            </label>
                            <div class="col-md-8">
                                <input type="number" class="form-control"
                                       step="0.1" min="1" max="7"
                                       name="nota2" style="width: 80px" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                <c:out value="${asigna3}" />
                            </label>
                            <div class="col-md-8">
                                <input type="number" class="form-control"
                                       step="0.1" min="1" max="7"
                                       name="nota3" style="width: 80px" />
                            </div>
                        </div>
                        <!--RESP-->
                    </fieldset>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            <c:out value="${mensajeEstado}" />
                        </label>
                        <div class="col-md-8">

                            <input type="hidden" name="rutAlumno"
                                   value="${fn:escapeXml(alumnoSe.rutPersona)}" />
                            <input type="hidden" name="codigoPrograma"
                                   value="${fn:escapeXml(programaSe.codigo)}" />
                            <input type="hidden" name="accion"
                                   value="${fn:escapeXml("agregar_notas")}" />
                            <input type="hidden" name="confirmar"
                                   value="${fn:escapeXml("si")}" />
                            <input type="submit" class="btn btn-primary"
                                   value="Agregar" />
                            <input type="button" class="btn btn-primary"
                                   value="Cancelar"
                                   onclick="location.href = 'cem_programas';"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
