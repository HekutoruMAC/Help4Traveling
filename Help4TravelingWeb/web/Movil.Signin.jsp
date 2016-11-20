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
                
                
        <%if ((String) session.getAttribute("error") == "true") {%>
        <%String mensaje = "\"" + session.getAttribute("mensaje").toString() + "\"";%>
        
         alert(<%=mensaje%>);        
         
         <%}%>
            });
            
    </script>
    <% 
        HttpSession sesion = request.getSession();
        sesion.setAttribute("dispositivo2", "adentro");%>

    <body>

        <jsp:include page="WEB-INF/Header.jsp"/>

        <div class="section minimo">
            <div class="container">
                <div class="row">
                    <div class="col-md-4" id="ingreso_usuario">
                        <h1>Ingreso de Proveedor:</h1>
                        <form role="form" class="form" action="Autenticacion" method="post" id="ingreso_form">
                            <div class="form-group">
                                <label for="nickname_ingreso" class="control-label">Nickname
                                    <br>
                                </label>
                                <input type="text" class="form-control" name="nickname_ingreso" id="nickname_ingreso" placeholder="Ingrese su Nickname" required="true" >
                            </div>
                            <div class="form-group">
                                <label for="password_ingreso" class="control-label">Password
                                    <br>
                                </label>
                               <input type="password" class="form-control" name="password_ingreso" id="password_ingreso" placeholder="Password" required="true">
                            </div>
                            <div class="form-group" action="Movil.Signin.jsp">
                                <div class="checkbox" id="Recordarme">
                                    <label>
                                        <input type="checkbox"  name="Recordarme" > Recordarme
                                        <br>
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-default" id="entrar_bn">Entrar</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="WEB-INF/Footer.jsp"/>

    </body>


</html>