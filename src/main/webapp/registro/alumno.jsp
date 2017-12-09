<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/menu-registro.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <title> Registro de alumnos </title>
    </head>
    <body>

        <div class="container well">
            <div class="row">
                <div class="col-md-12">
                    <h2>
                        <p class="text-center">
                            Registra tu nueva cuenta
                        </p>
                    </h2>
                </div>
            </div>
            <br> <br />
            <div class="col-md-6 col-md-offset-3">
                <form action="../registrar" method="post"
                      class="form-horizontal">
                    
                    <input type="hidden" name="accion"
                           value="registrar_alumno" />

                    <div class="form-group">
                        <label class="col-md-4 control-label"> Rut </label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="rut" 
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
                        <label class="col-md-4 control-label">
                            Contraseña
                        </label>
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
                        <div class="col-md-8">
                            <button type="submit" class="btn btn-primary"
                                    onClick="comprobarClave()">
                                Registrar
                            </button>
                        </div>
                        <br />
                        <label class="col-md-4 control-label">
                            <c:out value="${mensajeEstado}" />
                        </label>
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
