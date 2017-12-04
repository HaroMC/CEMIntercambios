<%-- 
    Document   : calificaciones
    Created on : 04-12-2017, 2:09:05
    Author     : Bugueño
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h2>Para colocar una calificacion selecciona un programa</h2>      
            <p>Filtra tu busqueda aqui:</p>
            <input class="form-control" id="myInput" type="text" placeholder="Escribe aca lo que buscas..">
            <br>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Nombre del programa </th>
                        <th>Asignatura</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="myTable">
                    <tr>
                        <td></td>
                        <td></td>
                        <td><button type="button" class="btn btn-primary">
                                Selecccionar
                            </button></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>             
                </tbody>
            </table>
            <!--
                        <button type="button" class="btn btn-primary">
                            Rechazar
                        </button>
            -->
        </div>
    </body>
</html>
