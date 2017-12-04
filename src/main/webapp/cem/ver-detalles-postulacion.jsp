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

            <div class="col-md-6 col-md-offset-3">
                <c:choose>
                    <c:when test="${tipo == 'alumno'}">
                        <c:set var="entidad" value="inscripciones-alumnos?accion=modificar&confirmar=si" />
                    </c:when>
                    <c:when test="${tipo == 'cel'}">
                        <c:set var="entidad" value="inscripciones-cel?accion=modificar&confirmar=si" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="entidad" value="#" />
                    </c:otherwise>
                </c:choose>
                <form method="post" action="<c:out value="${entidad}"/>">
                    <div class="form-group">
                        <label class="col-md-4 control-label"> Rut </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <c:choose>
                                    <c:when test="${tipo == 'alumno'}">
                                        <c:out value="${inscripcion.rutAlumno.rutPersona}" />
                                    </c:when>
                                    <c:when test="${tipo == 'cel'}">
                                        <c:out value="${inscripcion.rutCel.rutPersona}" />
                                    </c:when>
                                    <c:otherwise>
                                        Hay problema aquí
                                    </c:otherwise>
                                </c:choose>
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            <c:choose>
                                <c:when test="${tipo == 'alumno'}">
                                    <label class="col-md-4 control-label">
                                        Nombre del alumno
                                    </label>
                                    <div class="col-md-8">
                                        <c:out value="${inscripcion.rutAlumno.persona.nombreCompleto}"/>"
                                    </div>
                                </c:when>
                                <c:when test="${tipo == 'cel'}">
                                    <label class="col-md-4 control-label">
                                        Nombre del CEL
                                    </label>
                                    <div class="col-md-8">
                                        <c:out value="${inscripcion.rutCel.persona.nombreCompleto}"/>"
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <label class="col-md-4 control-label">
                                        Nombre del CEL
                                    </label>
                                    <div class="col-md-8">
                                        Hay problema aquí
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </label>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Programa al que postula
                        </label>
                        <div class="col-md-8">
                            <c:out value="${inscripcion.codPrograma.nombrePrograma}"/>"  
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Fecha en la que postuló
                        </label>
                        <div class="col-md-8">
                            <fmt:formatDate dateStyle="long" type="date"
                                            value="${inscripcion.fechaInscripcion}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Fecha en la que fue inscrito
                        </label>
                        <div class="col-md-8">
                            -
                        </div>
                    </div>

                    <c:if test="${tipo == 'alumno'}">
                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Familia con la que se alojará
                            </label>
                            <div class="col-md-8">
                                <c:out value="${inscripcion.rutFamilia.persona.nombreCompleto}"/>
                            </div>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Estado
                        </label>
                        <div class="col-md-8">
                            <select name="estado" required="true">
                                <c:choose>
                                    <c:when test="${inscripcion.estado == 1}">
                                        <option value="1" selected> Postulando </option>
                                        <option value="2"> Inscrito </option>
                                        <option value="3"> Rechazado </option>
                                    </c:when>
                                    <c:when test="${inscripcion.estado == 2}">
                                        <option value="1"> Postulando </option>
                                        <option value="2" selected> Inscrito </option>
                                        <option value="3"> Rechazado </option>
                                    </c:when>                                   
                                    <c:otherwise>
                                        <option value="1"> Postulando </option>
                                        <option value="2"> Inscrito </option>
                                        <option value="3" selected> Rechazado </option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label"> ${mensajeEstado} </label>
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
