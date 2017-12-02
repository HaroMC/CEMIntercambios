<%@ page contentType="text/html" pageEncoding="UTF-8" session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Bienvenido </title>
        <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="bootstrap-3.3.7-dist/css/estilo-login.css">
    </head>
    <body>          
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-md-offset-4">
                    <h1 class="text-center login-title"> 
                        Centro de estudios Montreal 
                    </h1>
                    <div class="account-wall">
                        <img class="profile-img" alt=""
                             src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120" />
                        <form class="form-signin" action="ingresar"
                              method="post">
                            <input type="text" class="form-control"
                                   placeholder="Nombre de usuario"
                                   name="nombreUsuario"
                                   required="true"
                                   autofocus="true" />
                            <input type="password" class="form-control"
                                   placeholder="Contraseña"
                                   name="contrasena"
                                   required="true" />
                            <button class="btn btn-lg btn-primary btn-block"
                                    type="submit">
                                Entrar
                            </button>
                            <c:out value="${mensajeEstado}" />
                        </form>
                    </div>                       
                    <a href="registro/alumno.jsp" class="text-center new-account">
                        Si eres un alumno regular, regístrate aquí.
                    </a>
                    <a href="registro/familia.jsp" class="text-center new-account">
                        Si eres un representante familiar, regístrate aquí.
                    </a>                         
                </div>
            </div>
        </div>
    </body>
</html>
