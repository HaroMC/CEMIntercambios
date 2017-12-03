<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../WEB-INF/menu-registro.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <!-- Link Bootstrap CSS -->
        <link rel="stylesheet"
              href="../bootstrap-3.3.7-dist/css/bootstrap.min.css">
        <!--Link J.S.-->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                crossorigin="anonymous">
        </script>
        <title> Registro de familias </title>
    </head>
    <body>

        <div class="container well">

            <!--TITULO DE FORMULARIO-->
            <div class="row">
                <div class="col-md-12">
                    <h2><p class="text-center">Formulario Registro Alumnos</p></h2>
                </div>
            </div>
            <br> <br />
            <div class="col-md-6 col-md-offset-3">
                <form action="../registrar?tipo=alumno" method="post"
                      class="form-horizontal">
                    
                    <div class="form-group">
                        <label class="col-md-4 control-label"> RUN </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="run" 
                                   placeholder="12345678-9" required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Nombre de usuario
                        </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control"
                                   name="nombreUsuario" 
                                   placeholder="Ingrese un nombre de usuario"
                                   required="true" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label"> Contraseña </label>
                        <div class="col-md-8">
                            <input type="password" class="form-control"
                                   name="clave1" 
                                   placeholder="Indique una contraseña"
                                   required="true" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label">
                            Repita su contraseña
                        </label>
                        <div class="col-md-8">
                            <input type="password" class="form-control"
                                   name="clave2" 
                                   placeholder="Repita su contraseña"
                                   required="true" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-md-4 control-label"> ${mensaje} </label>
                        <div class="col-md-8">
                            <button type="submit" class="btn btn-primary"
                                    onClick="comprobarClave()">
                                Registrar cuenta
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script>
            function comprobarClave() {
                clave1 = document.f1.clave1.value;
                clave2 = document.f1.clave2.value;
                if (clave1 === clave2)
                    alert("Las dos claves son iguales...\nRealizaríamos las acciones del caso positivo");
                else
                    alert("Las dos claves son distintas...\nRealizaríamos las acciones del caso negativo");
            }
        </script>
    </body>
</html>
