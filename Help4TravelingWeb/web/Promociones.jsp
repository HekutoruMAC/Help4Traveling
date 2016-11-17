<%--
    Document   : Promociones
    Created on : 05-oct-2016, 9:49:28
    Author     : Leonardo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.LinkedList"%>
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
        <title>Listado de Promociones</title>
        <!-- <div class="navbar navbar-default navbar-fixed-top" id="header"> /-->
        <jsp:include page="WEB-INF/Header.jsp"/>
        <!-- </div> /-->
        <div>
            <p>AAA</p>
        </div>
        <!--div class="section minimo"-->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="section">
                        <div class="container">
                            <div class="row">
                                <p class="default">Ofertas en Hoteles, Paquetes de viaje y más</p>
                            </div>
                            <div class="row default">
                                <div class="row default"><h2><b>Listado de Promociones</b></h2></div>
                                <hr>
                                <table class="default table table-bordered table-hover table-striped table-responsive">
                                    <thead>
                                        <tr class="default">
                                            <td class="default" width="200" align="center"><b>Nombre</b></td>
                                            <td class="default" width="200" align="center"><b>Proveedor</b></td>
                                            <td class="default" width="200" align="center"><b>Descuento</b></td>
                                            <td class="default" width="200" align="center"><b>Total</b></td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!--tr >
                                          <td colspan="4"></td>
                                        </tr-->
                                        <% servidorpublicador.DtPromocion dtProm = null;
                                            List<servidorpublicador.DtPromocion> promos = new ArrayList();
                                            servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
                                            servidorpublicador.Publicador port = service.getPublicadorPort();
                                            promos = port.listarPromociones().getPromociones();
                                            Iterator<servidorpublicador.DtPromocion> iter = promos.iterator();
                                            while (iter.hasNext()) {
                                                dtProm = iter.next();
                                                String nombre = dtProm.getNombre();
                                                String proveedor = dtProm.getProveedor();
                                                String descuento = dtProm.getPrecio();
                                                String total = dtProm.getDescuento();  %>
                                        <tr class="default">
                                            <td class="default" align="center" width="200" id="nombre"><a href="Promocion.jsp?nombre=<% out.print(nombre); %>&proveedor=<% out.print(proveedor);%>" target="_blank"><%=nombre%></a></td>
                                            <td class="default" align="center" width="200" id="proveedor"><%=proveedor%></td>
                                            <td class="default" align="center" width="200" id="descuento"><%=descuento%></td>
                                            <td class="default" align="center" width="200" id="total"><%=total%></td>
                                        </tr>
                                        <% }%>
                                    </tbody>
                                </table>
                                <ul class="list-group"></ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--/div-->
        <!-- <footer class="section section-primary" id="footer"> /-->
        <jsp:include page="WEB-INF/Footer.jsp"/>
        <!-- </footer> /-->
    </body>
</html>



