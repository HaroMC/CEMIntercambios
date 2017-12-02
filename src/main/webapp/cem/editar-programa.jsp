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
        <div class="container">
            <h1> Editar programa  </h1>
            <br />
            <form action="editar-programa" method="post"
                  class="form-horizontal">


                <c:out value="codigo" />
                
                Código
                <input type="text" readonly="true" value="<c:out value="${pEditado.codigo}" />" />
                Estado
                <input type="text" name="estado" value="<c:out value="${pEditado.estado}" />" />
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                

                <div class="form-group">
                    <label class="col-sm-2 control-label">
                        ${mensajeEstado}
                    </label>
                    <div class="col-sm-5 control-label">
                        <button type="submit" class="btn btn-default">
                            Modificar
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
