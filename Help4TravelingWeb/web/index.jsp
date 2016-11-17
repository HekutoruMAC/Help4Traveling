
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
    <body>
        <!-- <div class="navbar navbar-default navbar-fixed-top" id="header"> /-->
        <jsp:include page="WEB-INF/Header.jsp"/>
        <!-- </div> /-->
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-header">
                            <h1 style="float: left"><span class="logo">Help4Traveling</span>
                                <font color="#777777">
                                <span style="font-size: 23.4px; line-height: 23.4px;">Recomendados</span>
                                </font>
                            </h1>
                        </div>
                        <%
                            if ((String) session.getAttribute("nickname") != null) {
                                String nick = (String) session.getAttribute("nickname");
                        %>
                        <span style="float: right"> Bienvenido/a <b><a href="Usuario.jsp"><%=nick%></a></b> </span>
                                <% }%>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div id="fullcarousel-example" data-interval="false" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <div class="item active">
                                    <img src="img/fondo.png">
                                    <!--div class="carousel-caption">
                                      <h2 class="text-muted">Promoción 1</h2>
                                      <p class="text-muted">Descripción</p>
                                    </div-->
                                </div>
                                <div class="item">
                                    <img src="http://www.saldevacaciones.com/wp-content/uploads/2016/01/Agencia-de-viaje-online-1.jpg">
                                    <!--div class="carousel-caption">
                                      <h2 class="text-muted">Promoción 1</h2>
                                      <p class="text-muted">Descripción</p>
                                    </div-->
                                </div>
                                <div class="item">
                                    <img src="http://www.udgtravel.com/wp-content/uploads/2016/08/i3_20160203142344.jpg">
                                    <!--div class="carousel-caption">
                                      <h2 class="text-muted">Promoción 2</h2>
                                      <p class="text-muted">Descripción</p>
                                    </div-->
                                </div>
                                <div class="item">
                                    <img src="http://touratabex.com/wp-content/uploads/2014/12/agencia_de_viaje.jpg">
                                    <!--div class="carousel-caption">
                                      <h2 class="text-muted">Promoción 2</h2>
                                      <p class="text-muted">Descripción</p>
                                    </div-->
                                </div>
                                <div class="item">
                                    <img src="http://guiamexico.com.mx/Imagenes/b/206744977-2-agencia-de-viajes-trending-travel-business.png">
                                    <!--div class="carousel-caption">
                                      <h2 class="text-muted">Promoción 2</h2>
                                      <p class="text-muted">Descripción</p>
                                    </div-->
                                </div>
                            </div>
                            <a class="left carousel-control" href="#fullcarousel-example" data-slide="prev"><i class="icon-prev fa fa-angle-left"></i></a>
                            <a class="right carousel-control" href="#fullcarousel-example" data-slide="next"><i class="icon-next fa fa-angle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- <footer class="section section-primary" id="footer"> /-->
        <jsp:include page="WEB-INF/Footer.jsp"/>
        <!-- </footer> /-->
    </body>
</html>