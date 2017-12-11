<%@ page contentType = "text/html" pageEncoding = "UTF-8" session = "true" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"  %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> 
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/tamano_menu.css"> 
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <img src="../imagenes/study.png" style="width: 50px; height: 50px"/>
                    <!--<a class="navbar-brand" href="#"> WebSiteName </a>-->
                </div>
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#"> Perfil </a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle"
                           data-toggle="dropdown"
                           href="#"> Administración de usuarios
                            <span class="caret"> </span> </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="cem_alumnos"> Alumnos </a>
                            </li>
                            <li>
                                <a href="administrar_familias"> Familias </a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle"
                           data-toggle="dropdown"
                           href="#"> Administración de programas
                            <span class="caret"> </span> </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="cem_programas">
                                    Ver listado de programas
                                </a>
                            </li>
                            <li>
                                <a href="agregar_programa.jsp">
                                    Agregar programas
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle"
                           data-toggle="dropdown" href="#">
                            Consultar postulaciones
                            <span class="caret"> </span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="inscripciones_cel"> Centros de estudio locales </a>
                            </li>
                            <li>
                                <a href="inscripciones_alumnos"> Alumnos </a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <label class="nav navbar-nav navbar-right label label-default"
                       style="color: white; width: 10%; height: 50px">
                    <br />
                    <c:out value="${mensajeBienvenida}" />
                    <br />
                    <br />
                    <form  class="label label-default" style="color: #204d74"
                           action="/intercambioscem.cl/salir" method="get">
                        <input type="submit" value="Cerrar sesión" />
                    </form>
                </label>
            </div>
        </nav>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
                integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
        crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
                integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
        crossorigin="anonymous"></script>
    </body>
</html>


<%/*
    if (session.getAttribute("usuarioActual") == null) {
        response.sendRedirect("no-autorizado.html");
    }
    else {
        if (((Usuario) session.getAttribute("usuarioActual"))
                .getPerfil()
                .compareToIgnoreCase("Cem") != 0) {
            response.sendRedirect("no-autorizado.html");
        }
    }*/
%>
