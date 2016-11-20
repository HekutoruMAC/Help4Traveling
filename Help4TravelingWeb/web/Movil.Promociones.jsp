<%--
    Document   : Promociones
    Created on : 17/11/2016, 07:20:38 PM
    Author     : yaman
--%>

<%@page import="java.util.Iterator"%>
<%@page import="servidorpublicador.DtPromocion"%>
<%@page import="java.util.List"%>
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
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <link type="text/css" href="css/jquery-ui.css" rel="Stylesheet" />
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css\test.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <jsp:include page="WEB-INF/Header.jsp"/>

        <%
            servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
            servidorpublicador.Publicador port = service.getPublicadorPort();
            String nick = session.getAttribute("nickname").toString();
            //String nick = "moody";

            List<DtPromocion> promociones = port.listarPromocionesProveedor(nick).getPromocionesProveedor();%>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-header">
                            <h1 style="float: left"><span class="logo"><h2>Listado de Promociones</h2></span></h1>
                        </div>
                    </div>
                </div>
                <%   if (!promociones.isEmpty()) {
                        Iterator<servidorpublicador.DtPromocion> ipromo = promociones.iterator();
                        while (ipromo.hasNext()) {
                            servidorpublicador.DtPromocion dtPromo = ipromo.next();
                            String promocion = dtPromo.getNombre();
                            String imagen = port.obtenerPrimeraImagenPromocion(promocion, nick);
                %>
                <div class="row">
                    <div class="col-md-12">
                        <div id="fullcarousel-example" data-interval="false" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <div class="page-header">
                                    <h1 style="float: left"><span class="logo"><h3><%=promocion%></h3></span></h1>
                                </div>
                                <div class="col-xs-12">
                                    <img src="<% out.print(imagen); %>" class="img-responsive" alt="Imagen responsive">

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <% }%>
                <% }%>
            </div>
        </div>
        <jsp:include page="WEB-INF/Footer.jsp"/>
    </body>
</html>