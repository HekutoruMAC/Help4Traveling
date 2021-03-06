<%--
    Document   : Usuario
    Created on : 26/09/2016, 05:07:29 PM
    Author     : HP Usuario
--%>

<%@page import="servidorpublicador.DtPromocion"%>
<%@page import="servidorpublicador.DtServicio"%>
<%@page import="servidorpublicador.Date"%>
<%@page import="servidorpublicador.DtUsuario"%>
<%--@page import="Logica.DtPromocion"%>
<%@page import="Logica.Fabrica"%>
<%@page import="Logica.ManejadorServicio"--%>
<%@page import="java.util.LinkedList"%>
<%--@page import="Logica.DtServicio"%>
<%@page import="Logica.DtProveedor"%>
<%@page import="Logica.ManejadorProveedor"%>
<%@page import="Logica.DtUsuario"%>
<%@page import="Logica.Reserva"--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%--@page import="Logica.DtReserva"%>
<%@page import="Logica.Cliente" %>
<%@page import="Logica.Date"--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css\test.css" rel="stylesheet" type="text/css">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="js/jquery-3.1.1.js"></script>
        <script src="js/includes.js"></script>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <link type="text/css" href="css/jquery-ui.css" rel="Stylesheet" />
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css\test.css" rel="stylesheet" type="text/css">
        <script type="text/javascript"></script>
        <script>
            $(document).ready(function () {
            <%if ((String) session.getAttribute("registra") == "true") {%>;
            <%--String nick = "\"" + session.getAttribute("nickname").toString() + "\"";
               String nombre = "\"" + session.getAttribute("nombre").toString() + "\"";
               String apellido = "\"" + session.getAttribute("apellido").toString() + "\"";
                String fecha = "\"" + session.getAttribute("fNac").toString() + "\"";
               String email = "\"" + session.getAttribute("email").toString() + "\"";

            --%>
            <%};%>

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

        <script>
                $(function () {
                        var baseURL = 'http://localhost:8084/Help4TravelingWeb/';
                        //load content for first tab and initialize
                        $('#Datos').load(baseURL + 'Datos', function () {
                                $('#myTabs').tab(); //initialize tabs
                        });    
                        $('#myTabs').bind('show', function (e) {    
                               var pattern = '/#.+/gi'; //use regex to get anchor(==selector)
                               var contentID = e.target.toString().match(pattern)[0]; //get anchor         
                               //load content for selected tab
                                $(contentID).load(baseURL + contentID.replace('#', ''), function () {
                                        $('#myTabs').tab(); //reinitialize tabs
                                });
                        });
                });
        </script>

        <title>Detalle de Proveedor</title>
    </head>
    <body>
        <!-- <div class="navbar navbar-default navbar-fixed-top" id="header"> /-->
        <jsp:include page="WEB-INF/Header.jsp"/>
        <!-- </div> /-->
        <%
            servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
            servidorpublicador.Publicador port = service.getPublicadorPort();

            String nick = (String) request.getParameter("nick");
            DtUsuario dtProv = port.getDtProveedor(nick);
            String nombre = dtProv.getNombre();
            String apellido = dtProv.getApellido();
            String correo = dtProv.getCorreo();
            String empresa = dtProv.getEmpresa();
            String enlace = dtProv.getLink();
            Date fechanac = dtProv.getNacimiento();
            String nacimiento = String.valueOf(fechanac.getDia()) + "/" + String.valueOf(fechanac.getMes()) + "/" + String.valueOf(fechanac.getAno());

            String imagen = port.imagenPerfilUsuario(nick);
            out.print(dtProv.getNombre());
        %>
        <br>
        <br>
        <div class="section minimo">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <ul class="media-list">
                            <li class="media">
                                <a class="pull-left" href="#"><img class="media-object" src="<% out.print(imagen);%>" height="130" width="130"></a>
                                <div class="media-body">
                                    <h2 class="media-heading"><%=nick%></h2>
                                    <p><h3 class="media-body"><%=empresa%></h3></p>
                                    <p><a href="mailto:<% out.print(correo);%>"><%=correo%></a></p>
                                    <p><a href="<% out.print(enlace);%>" target="_blank"><%=enlace%></a></p>
                                </div>
                            </li>
                            <li class="media"></li>
                        </ul>
                    </div>
                </div>

                <ul class="nav nav-tabs" id="myTabs">
                    <li class="active">
                        <a data-toggle="tab" href="#Datos">Datos personales</a></li>
                    <li><a data-toggle="tab" href="#reservas">Servicios</a></li>
                    <li><a data-toggle="tab" href="#promociones">Promociones</a></li>

                </ul>

                <div class="tab-content">
                    <div id="Datos" class="tab-pane fade in active">

                        <div class="row">
                            <div class="col-md-12">
                                <ul class="list-group">
                                    <li class="list-group-item">Nombre: <%=nombre%> </li>
                                    <li class="list-group-item">Apellido: <%=apellido%> </li>
                                    <li class="list-group-item">Nacimiento: <%=nacimiento%></li>
                                </ul>

                            </div>
                        </div>
                    </div>
                    <div id="reservas" class="tab-pane fade">


                        <%
                            List<DtServicio> servicios = port.listarServiciosProveedor(dtProv).getServiciosProveedor();

                            if (!servicios.isEmpty()) {
                                Iterator<DtServicio> iserv = servicios.iterator(); %>
                        <table class="default table table-bordered table-hover table-striped table-responsive">
                            <thead>
                                <tr class="default">
                                    <td class="default" width="100" align="center"><b>Nombre</b></td>
                                    <td class="default" width="300" align="center"><b>Descripcion</b></td>
                                    <td class="default" width="100" align="center"><b>Precio</b></td>
                                    <td class="default" width="100" align="center"><b>Origen</b></td>
                                </tr>
                            </thead>
                            <tbody>
                                <% while (iserv.hasNext()) {
                                        DtServicio dtServ = iserv.next();
                                        String servicio = dtServ.getNombre();
                                        String descripcion = dtServ.getDescripcion();
                                        float precio = dtServ.getPrecio();
                                        String origen = dtServ.getNomciuorigen();  %>
                                <tr class="default">
                                    <td class="default" align="center" width="100" id="nombre"><a href="Servicio.jsp?nombre=<% out.print(servicio); %>&proveedor=<% out.print(nick); %>&categoria=<% out.print("");%>" target="_blank"><%=servicio%></a></td>
                                    <td class="default" align="center" width="300" id="descripcion"><%=descripcion%></td>
                                    <td class="default" align="center" width="100" id="precio"><%=precio%></td>
                                    <td class="default" align="center" width="100" id="origen"><%=origen%></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <% } else { %>
                        <table class="default table">
                            <tbody>
                                <tr>
                                    <td>
                                        <b>El proveedor seleccionado no tiene servicios asociados.</b>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <% }%>
                        <ul class="list-group"></ul>
                    </div>


                    <%
                        //Fabrica fab = Fabrica.getInstance();
                        List<DtPromocion> promociones = port.listarPromocionesProveedor(nick).getPromocionesProveedor();
                        if (!promociones.isEmpty()) {
                            Iterator<DtPromocion> ipromo = promociones.iterator(); %>
                    <div id="promociones" class="tab-pane fade">
                        <table class="default table table-bordered table-hover table-striped table-responsive">
                            <thead>
                                <tr class="default">
                                    <td class="default" width="200" align="center"><b>Nombre</b></td>
                                    <td class="default" width="100" align="center"><b>Proveedor</b></td>
                                    <td class="default" width="100" align="center"><b>Descuento</b></td>
                                    <td class="default" width="100" align="center"><b>Total</b></td>
                                </tr>
                            </thead>
                            <tbody>
                                <% while (ipromo.hasNext()) {
                                        DtPromocion dtPromo = ipromo.next();
                                        String promocion = dtPromo.getNombre();
                                        String proveedor = dtPromo.getProveedor();
                                        String descuento = dtPromo.getDescuento();
                                        String total = dtPromo.getPrecio();  %>
                                <tr class="default">
                                    <td class="default" align="center" width="200" id="nombre"><a href="Promocion.jsp?nombre=<% out.print(promocion); %>&proveedor=<% out.print(nick);%>" target="_blank"><%=promocion%></a></td>
                                    <td class="default" align="center" width="100" id="proveedor"><%=proveedor%></td>
                                    <td class="default" align="center" width="100" id="descuento"><%=descuento%></td>
                                    <td class="default" align="center" width="100" id="total"><%=total%></td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <% } else { %>
                        <table class="default table">
                            <tbody>
                                <tr>
                                    <td>
                                        <b>El proveedor seleccionado no tiene promociones asociadas.</b>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <ul class="list-group"></ul>
                        <% }%>
                    </div>


                </div>
            </div>
        </div>
        <!-- <footer class="section section-primary" id="footer"> /-->
        <jsp:include page="WEB-INF/Footer.jsp"/>
        <!-- </footer> /-->
    </body>
</html>