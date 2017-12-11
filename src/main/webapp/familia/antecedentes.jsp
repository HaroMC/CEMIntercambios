<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../WEB-INF/menu_familia.jsp" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Inicio </title>
    </head>
    <body>
        <div class="container">
            <div class="row">         
                <div class="col-sm-3 col-md-12 text-center" style="width: 80%; margin-left: 150px">
                    <fieldset>
                        <legend> <h1>Datos del Usuario</h1></legend>
                        <div class="col-md-6 text-right">
                            <p>
                                Alumno: <c:out value="${usuarioActual.persona.nombreCompleto}" />
                            </p>
                            <p>
                                País de residencia: <c:out value="${usuarioActual.persona.pais}" />
                            </p>
                            <p>
                                Ciudad: <c:out value="${usuarioActual.persona.ciudad}" />
                            </p>
                        </div>
                        <div class="col-md-6 text-left">
                            <p>
                                Correo: <c:out value="${usuarioActual.persona.correo}" />
                            </p>
                            <p>
                                Teléfono: <c:out value="${usuarioActual.persona.telefono}" />
                            </p>
                        </div>
                    </fieldset>
                </div>
            </div>
            <div class="row">
                <fieldset>
                    <legend><h2>Sube aca los antecedentes</h2></legend>
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
                </fieldset>
            </div>
            <br/>
            <br/>
            <br/>

            <div class="row">
                <fieldset>
                    <legend><h2>Sube aca las fotod del lugar de residencia</h2></legend>
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
                </fieldset>
            </div>
        </div>
        <br/>
        <br/>

    </body>

</html>

<!--<html>
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
                    <form action="familia_antecedente" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
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

</html>-->
