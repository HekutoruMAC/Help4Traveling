<%--
    Document   : Error
    Created on : Nov 18, 2016, 6:09:32 PM
    Author     : Hekutoru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="js/includes.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <link type="text/css" href="css/jquery-ui.css" rel="Stylesheet" />
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css\test.css" rel="stylesheet" type="text/css">
        <title>Error!</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"/>
        <div class="section minimo">
            <div class="container">
                <h1 class="text-center text-uppercase bg-primary">Error 404</h1>
                <h3 class="text-center">
                    La página no se encuentra disponible...
                    porque se fue de vacaciones gracias a <span class="logo">Help4Traveling</span>!
                </h3>
                <img class="img-responsive img-rounded" src="img/fondo.png"/>
            </div>
        </div>
        <jsp:include page="Footer.jsp"/>
    </body>
</html>
