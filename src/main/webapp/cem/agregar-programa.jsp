<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../WEB-INF/menu-cem.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
        <title> Registrar programa </title>
    </head>
    <body>
        <div class="container well">

            <div class="row">
                <div class="col-md-12">
                    <h2>
                        <p class="text-center">
                            Registrar nuevo programa de intercambio
                        </p>
                    </h2>
                </div>
            </div>

            <br> <br />

            <div class="col-md-6 col-md-offset-3">

                <form action="cem-programas?accion=agregar" method="post"
                      class="form-horizontal">

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Nombre del programa
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control"
                                   name="nombrePrograma" 
                                   placeholder="Ingrese el nombre del programa"
                                   required="true" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Duración
                        </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <select name="tipoDuracion" required="true">
                                    <option value="normal"> Normal </option>
                                    <option value="corto"> Corto </option>
                                </select>
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Cupos
                        </label>
                        <div class="col-md-8">
                            <input type="number" min="1" max="40" name="cupos" 
                                   placeholder="Ingrese la cantidad de cupos disponibles"
                                   required="true" pattern="[0-9]*" required="true"
                                   class="form-control" inputmode="numeric" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Valor
                        </label>
                        <div class="col-md-8">
                            <input type="number" class="form-control" name="valor" 
                                   placeholder="Ingrese el valor del programa"
                                   required="true" pattern="[0-9]*"
                                   inputmode="numeric" min="0" />
                        </div>
                    </div>

                    <fieldset>
                        <legend> Asignaturas requeridas </legend>

                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Nombre de la asignatura #1
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="asignatura1"
                                       placeholder="Nombre de la asignatura requerida." />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Nombre de la asignatura #2
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="asignatura2"
                                       placeholder="Nombre de la asignatura requerida." />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">
                                Nombre de la asignatura #3
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control"
                                       name="asignatura3"
                                       placeholder="Nombre de la asignatura requerida." />
                            </div>
                        </div>
                    </fieldset>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">
                            ${mensajeEstado}
                        </label>
                        <div class="col-sm-5 control-label">
                            <button type="submit" class="btn btn-default">
                                Agregar
                            </button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </body>
</html>
