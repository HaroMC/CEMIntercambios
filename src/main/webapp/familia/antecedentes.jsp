<%-- 
    Document   : antecedentes
    Created on : 04-12-2017, 17:30:35
    Author     : Bugueño
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/menu-familia.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1>aqui se dene subir los antecedentes</h1>
                <br/>
                <div class="col-md-6">
                    <form>
                        <h4>Cerificado de antecedentes</h4>
                        <input type="file" name="file" size="50" />
                        <button class="btn btn-primary" type="submit">Subir Archivo </button>
                    </form>
                </div>
                <div class="col-md-6">
                    <form>
                        <h4>Certificado de residencia</h4>
                        <input type="file" name="file" size="50" />
                        <button class="btn btn-primary" type="submit">Subir Archivo </button>
                    </form>
                </div>
            </div>
            <br/>
            <br/>
            <div class="col-md-12 text-center" style="margin-bottom: 50px">
                <h2>Fotos del lugar de residencia</h2>
            </div>
            <br/>
       
            <div class="row">
                <div class="col-md-4">
                    <form>
                        <h4>Foto 1</h4>
                        <input type="file" name="file" size="50" />
                        <button class="btn btn-primary" type="submit">Subir Archivo </button>
                    </form>
                </div>
                <div class="col-md-4">
                    <form>
                        <h4>Foto 2</h4>
                        <input type="file" name="file" size="50" />
                        <button class="btn btn-primary" type="submit">Subir Archivo </button>
                    </form>
                </div>
                <div class="col-md-4">
                    <form>
                        <h4>Foto 3</h4>
                        <input type="file" name="file" size="50" />
                        <button class="btn btn-primary" type="submit">Subir Archivo </button>
                    </form>
                </div>
            </div>
        </div>
        <br/>
        <br/>

    </body>

</html>
