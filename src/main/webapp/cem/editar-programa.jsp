<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ include file="../WEB-INF/menu-cem.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css" >
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
        <title> Editar programa </title>
    </head>
    <body>
        <div class="container well">
            <div class="row">
                <div class="col-md-12">
                    <h2><p class="text-center">Modificar datos</p></h2>
                </div>
            </div>
            <br> <br />
            <div class="col-md-6 col-md-offset-3">
                <form action="cem-programas?accion=modificar&confirmar=si"
                      method="post"
                      class="form-horizontal">

                    <div class="form-group">
                        <label class="col-md-4 control-label"> Código </label>
                        <div class="col-md-8">
                            <label class="form-control">
                                <c:out value="${pEditar.codigo}" />
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Nombre
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control"
                                   name="nombrePrograma"
                                   required="true"
                                   value="<c:out value="${pEditar.nombrePrograma}"/>"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Tipo de duración
                        </label>
                        <div class="col-md-8">
                            <select name="tipoDuracion" required="true">
                                <option value="normal"> Normal </option>
                                <option value="corto"> Corto </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Cupos
                        </label>
                        <div class="col-md-8">
                            <input type="number" class="form-control"
                                   name="cupos" required="true" pattern="[0-9]*"
                                   inputmode="numeric" min="1" max="40"
                                   value="<c:out value="${pEditar.cupos}"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Valor
                        </label>
                        <div class="col-md-8">
                            <input type="number" class="form-control" name="valor"
                                   required="true" pattern="[0-9]*"
                                   inputmode="numeric" min="0" max="10000000"
                                   value="<c:out value="${pEditar.valor}"/>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Estado
                        </label>
                        <div class="col-md-8">
                            <select name="estado" required="true">
                                <c:choose>
                                    <c:when test="${pEditar.estado == 2}">
                                        <option value="1"> Sin CEL asignado </option>
                                        <option value="2" selected> Disponible </option>
                                        <option value="3"> Sin cupos </option>
                                        <option value="4"> Iniciado </option>
                                        <option value="5"> Finalizado </option>
                                    </c:when>
                                    <c:when test="${pEditar.estado == 3}">
                                        <option value="1"> Sin CEL asignado </option>
                                        <option value="2"> Disponible </option>
                                        <option value="3" selected> Sin cupos </option>
                                        <option value="4"> Iniciado </option>
                                        <option value="5"> Finalizado </option>
                                    </c:when>
                                    <c:when test="${pEditar.estado == 4}">
                                        <option value="1"> Sin CEL asignado </option>
                                        <option value="2"> Disponible </option>
                                        <option value="3"> Sin cupos </option>
                                        <option value="4" selected> Iniciado </option>
                                        <option value="5"> Finalizado </option>
                                    </c:when>
                                    <c:when test="${pEditar.estado == 5}">
                                        <option value="1"> Sin CEL asignado </option>
                                        <option value="2"> Disponible </option>
                                        <option value="3"> Sin cupos </option>
                                        <option value="4"> Iniciado </option>
                                        <option value="5" selected> Finalizado </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="1" selected> Sin CEL asignado </option>
                                        <option value="2"> Disponible </option>
                                        <option value="3"> Sin cupos </option>
                                        <option value="4"> Iniciado </option>
                                        <option value="5"> Finalizado </option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
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
