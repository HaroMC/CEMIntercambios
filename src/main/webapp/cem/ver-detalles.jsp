<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/menu-cem.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Detalles de la postulación </title>
    </head>
    <body>
        <div class="container well">

            <div class="row">
                <div class="col-md-12">
                    <h2>
                        <p class="text-center"> Detalles de la postulación </p>
                    </h2>
                </div>
            </div>

            <br> <br />

            <c:choose>
                <c:when test="${tipo == 'alumno'}">
                    <c:set var="accion" value="inscripciones-alumnos?accion=modificar&confirmar=si" />
                </c:when>
                <c:when test="${tipo == 'CEL'}">
                    <c:set var="accion" value="inscripciones-cel?accion=modificar&confirmar=si" />
                </c:when>
            </c:choose>

            <div class="col-md-8 col-md-offset-3">
                <form method="post" action="<c:out value="${accion}"/>">

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Rut
                        </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <c:choose>
                                    <c:when test="${tipo == 'alumno'}">
                                        <c:out value="${inscripcion.rutAlumno.rutPersona}" />
                                    </c:when>
                                    <c:when test="${tipo == 'CEL'}">
                                        <c:out value="${inscripcion.rutCel.rutPersona}" />
                                    </c:when>
                                </c:choose>
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Nombre del <c:out value="${tipo}"/>
                        </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <c:choose>
                                    <c:when test="${tipo == 'alumno'}">
                                        <c:out value="${inscripcion.rutAlumno.persona.nombreCompleto}" />
                                    </c:when>
                                    <c:when test="${tipo == 'CEL'}">
                                        <c:out value="${inscripcion.rutCel.persona.nombreCompleto}" />
                                    </c:when>
                                </c:choose>
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Programa al que postula
                        </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <c:out value="${inscripcion.codPrograma.nombrePrograma}" />
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Fecha en la que postula
                        </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <fmt:formatDate dateStyle="long" type="date"
                                                value="${inscripcion.fechaPostulacion}"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Fecha en la que fue inscrito
                        </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <fmt:formatDate dateStyle="long" type="date"
                                                value="${inscripcion.fechaInscripcion}"/>
                            </label>
                        </div>
                    </div>

                    <c:if test="${tipo == 'alumno'}">
                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Familia con la que se alojará
                            </label>
                            <div class="col-md-8">
                                <label class="form-control">
                                    <c:out value="${inscripcion.rutFamilia.persona.nombreCompleto}"/>
                                </label>
                            </div>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Estado
                        </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <c:choose>
                                    <c:when test="${inscripcion.estado == 2}">
                                        Inscrito
                                    </c:when>
                                    <c:when test="${inscripcion.estado == 1}">
                                        <select name="estado" required="true">
                                            <option value="1" selected> Postulando </option>
                                            <option value="2"> Inscrito </option>
                                            <option value="3"> Rechazado </option>
                                        </select>
                                    </c:when>
                                    <c:when test="${inscripcion.estado == 3}">
                                        <select name="estado" required="true">
                                            <option value="1"> Postulando </option>
                                            <option value="2"> Inscrito </option>
                                            <option value="3" selected> Rechazado </option>
                                        </select>
                                    </c:when>
                                </c:choose>
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            <c:out value="${mensajeEstado}" />
                        </label>
                        <div class="col-md-8">
                            <button type="submit" class="btn btn-primary">
                                Confirmar cambios
                            </button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </body>
</html>
