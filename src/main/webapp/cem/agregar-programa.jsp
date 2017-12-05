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
        <title> Registrar programa </title>
    </head>
    <body>
        <div class="container">
            <h1> Registrar nuevo programa de intercambio </h1>
            <br />
            <form action="cem-programas?accion=agregar" method="post"
                  class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-2 control-label">
                        Nombre del programa
                    </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control"
                               name="nombrePrograma" 
                               placeholder="Ingrese el nombre del programa"
                               required="true">
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-2 control-label">
                        Duración
                    </label>
                    <div class="col-sm-5">
                        <select name="tipoDuracion" required="true">
                            <option value="normal"> Normal </option>
                            <option value="corto"> Corto </option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">
                        Cupos
                    </label>
                    <div class="col-sm-5">
                        <input type="number" class="form-control" name="cupos" 
                               placeholder="Ingrese la cantidad de cupos disponibles"
                               required="true" pattern="[0-9]*"
                               inputmode="numeric" min="1" max="40">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">
                        Valor
                    </label>
                    <div class="col-sm-5">
                        <input type="number" class="form-control" name="valor" 
                               placeholder="Ingrese el valor del programa"
                               required="true" pattern="[0-9]*"
                               inputmode="numeric" min="0">
                    </div>
                </div>

                        <div class="form-group">
                    <label class="col-sm-2 control-label">
                        Nombre de la asignatura 1
                    </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control"
                               name="nombreAsignatura" 
                               placeholder="Ingrese el nombre de la asignatura"
                               >
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-sm-2 control-label">
                        Nombre de la asignatura 2
                    </label>
                    <div class="col-sm-5">
                        <input type="text" class="form-control"
                               name="nombreAsignatura2" 
                               placeholder="Ingrese el nombre de la asignatura"
                               >
                    </div>
                </div>

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
    </body>
</html>
