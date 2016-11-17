<%--
    Document   : Servicios
    Created on : 05-oct-2016, 15:00:32
    Author     : Leonardo
--%>

<%--@page import="Logica.ManejadorServicio"--%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList"%>
<%--@page import="Logica.DtServicio"--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="js/includes.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <link type="text/css" href="css/jquery-ui.css" rel="Stylesheet" />
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css\test.css" rel="stylesheet" type="text/css">
        <script src="js/jquery-3.1.1.js"></script>
        <script src="js/jstree.min.js"></script>
        <script>
            $(document).ready(function () {
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
    <title>Listado de Servicios</title>
    <body>
        <!-- <div class="navbar navbar-default navbar-fixed-top" id="header"> /-->
        <jsp:include page="WEB-INF/Header.jsp"/>
        <!-- </div> /-->
        <div class="section minimo">
            <iframe name="categorias" src="Categorias.jsp" style="float: left; position:relative; width:30%; height: 600px" scrolling="auto" frameborder="0">
            </iframe>
            <iframe name="servicios" src="ListarServicios.jsp?categoria=Categorias" style="float: right; position:relative; width:70%; height: 600px" scrolling="auto" frameborder="0">
            </iframe>
        </div>
        <div><!-- <footer class="section section-primary" id="footer"> /-->
        <jsp:include page="WEB-INF/Footer.jsp"/>
        <!-- </footer> /--></div>
    </body>
</html>



