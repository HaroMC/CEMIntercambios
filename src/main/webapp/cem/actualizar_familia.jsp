<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/menu_cem.jsp" %>

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
            <br />
            <br />
            <div class="col-md-8 col-md-offset-3">
                <form method="post" action="administrar_familias">

                    <input type="hidden" name="accion"
                           value="${fn:escapeXml("modificar")}" />
                    <input type="hidden" name="confirmar"
                           value="${fn:escapeXml("si")}" />

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Rut
                        </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <c:out value="${familiaEditar.persona.rut}" />
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Nombre del representante
                        </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <c:out value="${familiaEditar.persona.nombreCompleto}" />
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Estado
                        </label>
                        <div class="col-md-8">
                            <c:choose>
                                <c:when test="${familiaEditar.estado == 2}">
                                    <label class="form-control">
                                        Habilitada
                                    </label>
                                </c:when>
                                <c:when test="${familiaEditar.estado == 1}">
                                    <select name="estado" required="true" class="selectpicker form-control">
                                        <option value="1" selected>
                                            Aprobación pendiente
                                        </option>
                                        <option value="2">
                                            Habilitada
                                        </option>
                                    </select>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                        </label>
                        <div class="col-md-8">
                            <input type="button" class="btn btn-primary"
                                   onclick="location.href = 'administrar_familias';"
                                   value="Regresar">
                            <c:choose>
                                <c:when test="${inscripcion.estado != 2}">
                                    <input type="submit" class="btn btn-primary"
                                           value="Guardar cambios">
                                </c:when>
                            </c:choose>
                        </div>
                        <label class="col-md-4 control-label">
                            <c:out value="${mensajeEstado}" />
                        </label>
                    </div>

                </form>
            </div>
        </div>
    </body>
</html>
