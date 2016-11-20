<%--
    Document   : ingresoprueba
    Created on : 24/09/2016, 11:15:20 AM
    Author     : yaman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="js/jquery-3.1.1.js"></script>
        <script src="js/includes.js"></script>
        <script type="text/javascript" src="calendario/calendario_dw/calendario_dw.js"></script>


        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <link type="text/css" href="css/jquery-ui.css" rel="Stylesheet" />
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <link type="text/css" href="css/jquery-ui.css" rel="Stylesheet" />
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css\test.css" rel="stylesheet" type="text/css">
        <link href="calendario/calendario_dw/calendario_dw-estilos.css" type="text/css" rel="stylesheet">
    </head>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#registro_completo_form").hide();



        <%if ((String) session.getAttribute("registra") == "true") {
            String nick = "\"" + session.getAttribute("nickname").toString() + "\"";
            String email = "\"" + session.getAttribute("email").toString() + "\"";%>
            alert("El usuario " + <%=nick%> + " está disponiblie. Complete los campos con sus datos personales");

            $("#registro_completo_form").show();
            $("#ingreso_usuario").hide();
            $("#registrar_bn").hide();
            $("#nickname_registro").val(<%=nick%>);
            $("#nickname_registro").attr("readonly", true);
            $("#email_in").val(<%=email%>);
            $("#email_in").attr("readonly", true);
            //$("#fecha").datepicker();
            $("#fecha").calendarioDW('option', {dateFormat: 'yy/mm/dd'});
            $("#startDate").datepicker({dateFormat: 'yy/mm/dd'});
        <%} else if ((String) session.getAttribute("registra") == "false") {%>;
            alert("Lo siento. El usuario o el email ya han sido registrados");
        <%} else if ((String) session.getAttribute("inicia") == "true") {%>;
        <% String mensaje = "\"" + session.getAttribute("mensaje").toString() + "\"";%>
            alert(<%=mensaje%>);
        
            <%}else if ((String) session.getAttribute("error") == "true") {%>
        <%String mensaje2 = "\"" + session.getAttribute("mensaje").toString() + "\"";%>
        
         alert(<%=mensaje2%>); 
         <%session.invalidate();%>
             
         
         <%}%>

            setTimeout(function () {
        <%if ((String) session.getAttribute("nickname") != null) {%>
                $('#idIniciar').hide();
                $('#idRegistrar').hide();
        <%} else {%>
                $('#idPerfil').hide();
                $('#idReservas').hide();
                $('#idSalir').hide();
        <%}%>;
            }, 100);
        });
    </script>
</head>
<body>

    <!-- <div class="navbar navbar-default navbar-fixed-top" id="header"> /-->
    <jsp:include page="WEB-INF/Header.jsp"/>
    <!-- </div> /-->
    <%
        Cookie[] todoslosCookies = request.getCookies();
        String nicknameCookie = "";
        if (todoslosCookies != null) {
            for (int i = 0; i < todoslosCookies.length; i++) {
                Cookie unCookie = todoslosCookies[i];
                if (unCookie.getName().equals("nick")) {
                    nicknameCookie = unCookie.getValue();
                    // out.println("<div><h1> "+nicknameCookie +"</h1></div>");
                }
            }
        }
    %>
    <div class="section minimo">
        <div class="container">
            <div class="row">
                <div class="col-md-4" id="ingreso_usuario">
                    <h1>Ingreso de Usuario:</h1>
                    <form role="form" class="form" action="Autenticacion" method="post" id="ingreso_form">
                        <div class="form-group">
                            <!--div class="col-sm-2"-->
                            <label for="nickname_ingreso" class="control-label">Nickname
                                <br>
                            </label>
                            <!--/div-->
                            <!--div class="col-sm-10" id="nickname_ingreso"-->
                            <input type="text" class="form-control" name="nickname_ingreso" id="nickname_ingreso" placeholder="Ingrese su Nickname" required="true" value="<%=nicknameCookie%>">
                            <!--/div-->
                        </div>
                        <div class="form-group">
                            <!--div class="col-sm-2"-->
                            <label for="password_ingreso" class="control-label">Password
                                <br>
                            </label>
                            <!--/div-->
                            <!--div class="col-sm-10" id="password_ingreso"-->
                            <input type="password" class="form-control" name="password_ingreso" id="password_ingreso" placeholder="Password" required="true">
                            <!--/div-->
                        </div>
                        <div class="form-group" action="InicioSesion.jsp">
                            <!--div class="col-sm-offset-2 col-sm-10"-->
                            <div class="checkbox" id="Recordarme">
                                <label>
                                    <input type="checkbox"  name="Recordarme" > Recordarme
                                    <br>
                                </label>
                            </div>
                            <!--/div-->
                        </div>
                        <div class="form-group">
                            <!--div class="col-sm-offset-2 col-sm-10"-->
                            <button type="submit" class="btn btn-default" id="entrar_bn">Entrar</button>
                            <!--/div-->
                        </div>
                    </form>
                </div>
                <div class="col-md-4"></div>
                <div class="col-md-4" id="registro_usuario" >
                    <h1>Registro de Usuario:</h1>
                    <form role="form" id="registro_form" class="form" action="Comprobacion" method="post" >
                        <div class="form-group">
                            <label class="control-label" for="nickname_registro">Nickname
                                <br>
                            </label>
                            <input class="form-control" id="nickname_registro" name="nickname_registro" placeholder="Ingrese su Nickname" type="text" required="true">
                        </div>
                        <div class="form-group">
                            <div class="form-group">
                                <label>Email&nbsp;
                                    <br>
                                </label>
                                <input type="email" id="email_in" name="email_registro" class="form-control" placeholder="Ingrese su Email" required="true">

                            </div>
                        </div>
                        <button type="submit" class="btn btn-default" id="registrar_bn">Registrar</button>
                    </form>

                    <form role="form"  class="form"  action="Registro" method="post" id="registro_completo_form" >
                        <div class="form-group">
                            <label class="control-label" for="password_registro">Password &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                <br>
                            </label>
                            <input class="form-control" id="password_in" name="password_in" placeholder="Ingrese el Password" type="password" required="true">
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="password2_registro">Password &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                <br>
                            </label>
                            <input class="form-control" id="password2_in" name="password2_in" placeholder="Reingrese el Password" type="password" required="true">
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" class="form-control" id= "nombre_in" name="nombre_in" placeholder="Nombre" required="true">
                        </div>
                        <div class="form-group">
                            <label>Apellido</label>
                            <input type="text" class="form-control" id="apellido_in" name="apellido_in" placeholder="Apellido">
                        </div>
                        <div class="form-group">
                            <label class="control-label">Seleccionar Imagen</label>
                            <br>
                            <input id="imagen_registro" name="imagen_registro" type="text" class="form-control" placeholder="Ingrese su Imagen">
                        </div>
                        <div class="form-group">
                            <label for="fecha">Fecha</label>
                            <br>
                            <input type="text"  class="form-control" id="fecha" name="fecha" value="" />
                        </div>
                        <button type="submit" class="btn btn-default" id="registrar_completo_bn">Registrar</button>
                        <button type="submit" class="btn btn-default" id="cancelar_registro">Cancelar</button>

                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- <footer class="section section-primary" id="footer"> /-->
    <jsp:include page="WEB-INF/Footer.jsp"/>
    <!-- </footer> /-->
</html>
