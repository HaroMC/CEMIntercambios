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
                                <label class="col-md-4 control-label">

                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Alumno
                            </label>
                            <div class="col-md-8">
                                <label class="col-md-4 control-label">

                                </label>
                            </div>
                        </div>
                    </fieldset>

                    <fieldset>
                        <legend> Asignaturas </legend>

                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Asignatura # 1
                            </label>
                            <div class="col-md-8">
                                <input type="number" class="form-control"
                                       step="0.1" min="1" max="7"
                                       name="asignatura1" style="width: 80px" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Asignatura # 2
                            </label>
                            <div class="col-md-8">
                                <input type="number" class="form-control"
                                       step="0.1" min="1" max="7"
                                       name="asignatura2" style="width: 80px"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Asignatura # 3
                            </label>
                            <div class="col-md-8">
                                <input type="number" class="form-control"
                                       step="0.1" min="1" max="7"
                                       name="asignatura3" style="width: 80px"/>
                            </div>
                        </div>

                    </fieldset>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            <c:out value="${mensajeEstado}" />
                        </label>
                        <div class="col-md-8">
                            <input type="hidden" name="accion" value="agregar" />
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
