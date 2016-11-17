<%--
    Document   : Usuario
    Created on : 26/09/2016, 05:07:29 PM
    Author     : HP Usuario
--%>

<%@page import="servidorpublicador.DtReserva"%>
<%@page import="servidorpublicador.Reserva"%>
<%@page import="servidorpublicador.DtPromocion"%>
<%--@page import="Logica.Fabrica"%>
<%--@page import="Logica.ManejadorServicio"%>
<%--@page import="Logica.DtServicio"%>
<%--@page import="Logica.ManejadorProveedor"%>
<%--@page import="Logica.DtUsuario"%>
<%--@page import="Logica.Reserva"--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%--@page import="Logica.DtPromocion"--%>
<%--@page import="Logica.DtReserva"--%>
<%--@page import="Logica.Cliente" --%>
<%--@page import="Logica.Date" --%>

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
               String correo = "\"" + session.getAttribute("email").toString() + "\"";
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
        <title>Perfil de Usuario</title>
    </head>
    <body>
        <!-- <div class="navbar navbar-default navbar-fixed-top" id="header"> /-->
        <jsp:include page="WEB-INF/Header.jsp"/>
        <!-- </div> /-->
        <%  servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
            servidorpublicador.Publicador port = service.getPublicadorPort();
            Boolean esProv = (Boolean) session.getAttribute("esProv");
            String nick = session.getAttribute("nickname").toString();
            String nombre = session.getAttribute("nombre").toString();
            String apellido = session.getAttribute("apellido").toString();
            String fecha = session.getAttribute("fechaNac").toString();
            String correo = session.getAttribute("email").toString();
            String empresa = "";
            String enlace = "";
            servidorpublicador.DtUsuario dtProv = null;
            if (esProv) {
                dtProv = port.getDtProveedor(nick);
                empresa = dtProv.getEmpresa();
                enlace = dtProv.getLink();
            }
            String partes[] = fecha.split("-");
            fecha = partes[2] + "/" + partes[1] + "/" + partes[0];
            //Fabrica fab = Fabrica.getInstance();
            String imagen = port.imagenPerfilUsuario(nick);
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
                                        <% if (esProv) { %>
                                    <p><a href="<% out.print(enlace);%>" target="_blank"><%=enlace%></a></p>
                                        <% }%>
                                </div>
                            </li>
                            <li class="media"></li>
                        </ul>
                    </div>
                </div>

                <ul class="nav nav-tabs">
                    <li class="active">
                        <a data-toggle="tab" href="#Datos">Datos personales</a></li>
                        <% if (esProv) { %>
                    <li><a data-toggle="tab" href="#servicios">Servicios</a></li>
                    <li><a data-toggle="tab" href="#promociones">Promociones</a></li>
                        <% }%>
                    <li><a data-toggle="tab" href="#reservas">Reservas</a></li>

                </ul>

                <div class="tab-content">
                    <div id="Datos" class="tab-pane fade in active">

                        <div class="row">
                            <div class="col-md-12">
                                <ul class="list-group">
                                    <li class="list-group-item">Nombre: <%=nombre%> </li>
                                    <li class="list-group-item">Apellido: <%=apellido%> </li>
                                        <%--   lo ideal pero el formato Date da AAAAMMDD entonces se presenta al reves
                                               <li class="list-group-item">F de nac: <%= usu.getNacimiento().getFecha("/")  %></li> --%>


                                    <li class="list-group-item">F de nac: <%=fecha%></li>
                                </ul>

                            </div>
                        </div>
                    </div>

                    <% if (esProv) { %>

                    <%
                        List<servidorpublicador.DtServicio> servicios = port.listarServiciosProveedor(dtProv).getServiciosProveedor();
                        if (!servicios.isEmpty()) {
                            Iterator<servidorpublicador.DtServicio> iserv = servicios.iterator(); %>
                    <div id="servicios" class="tab-pane fade">
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
                                        servidorpublicador.DtServicio dtServ = iserv.next();
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
                        <ul class="list-group"></ul>
                        <% } %>
                    </div>
                    <%
                        //List<DtPromocion> promociones = fab.getIControladorUsuario().listarPromocionesProveedor(nick);
                        List<DtPromocion> promociones = port.listarPromocionesProveedor(nick).getPromocionesProveedor();
                        if (!promociones.isEmpty()) {
                            Iterator<servidorpublicador.DtPromocion> ipromo = promociones.iterator(); %>
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
                                        servidorpublicador.DtPromocion dtPromo = ipromo.next();
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
                        <% } %>
                    </div>

                    <% } %>
                    <div id="reservas" class="tab-pane fade">
                        <table class="default table table-bordered table-hover table-striped table-responsive">
                            <tbody>
                                <tr class="default">
                                    <td class="default" width="50" align="center"><b>Número</b></td>
                                    <td class="default" width="50" align="center"><b>Reserva</b></td>
                                    <% if (esProv) { %>
                                    <td class="default" width="100" align="center"><b>Cliente</b></td>
                                    <% } %>
                                    <td class="default" width="100" align="center"><b>Fecha</b></td>
                                    <td class="default" width="100" align="center"><b>Estado</b></td>
                                    <td class="default" width="100" align="center"><b>Total</b></td>
                                    <td class="default" width="200" align="center"><b>Acciones</b></td>

                                </tr>
                                <%  DtReserva dtRes = null;
                                    List<DtReserva> reservas;
                                    if (esProv) {

                                        //reservas = fab.getIControladorReserva().listarReservasProveedor(nick);
                                        reservas = port.listarReservasProveedor(nick).getReservasProveedor();
                                    } else {

                                        //reservas = fab.getIControladorReserva().listarReservasUsuario(nick);
                                        reservas = port.listarReservasUsuario(nick).getReservasUsuario();
                                    }
                                    Iterator<DtReserva> iter = reservas.iterator();
                                    Integer i = 0;
                                    while (iter.hasNext()) {
                                        i++;
                                        dtRes = iter.next();
                                        String numero = i.toString();
                                        Long id = dtRes.getId();
                                        String estado = "";
                                        int itemsNF = port.estadoParcialReserva(id.intValue(), dtRes.getCliente());
                                        if (dtRes.getEstado().toString().equals("REGISTRADA")) {
                                            estado = "REGISTRADA";
                                        } else if (dtRes.getEstado().toString().equals("CANCELADA")) {
                                            estado = "CANCELADA";
                                        } else if (dtRes.getEstado().toString().equals("FACTURADA")) {
                                            estado = "FACTURADA";
                                        } else if (dtRes.getEstado().toString().equals("PAGADA")) {
                                            if (itemsNF == 0) {
                                                estado = "PARCIAL";
                                            } else {
                                                estado = "PAGADA";
                                            }
                                        }
                                        String idres = id.toString();
                                        String cliente = dtRes.getCliente();
                                        servidorpublicador.Date fechac = dtRes.getCreada();
                                        String creada = String.valueOf(fechac.getAno()) + "/" + String.format("%02d", fechac.getMes()) + "/" + String.format("%02d", fechac.getDia());
                                        String partesCre[] = creada.split("/");
                                        creada = partesCre[2] + "/" + partesCre[1] + "/" + partesCre[0];
                                        String total = String.valueOf(dtRes.getTotal());%>

                                <tr class="default" style="height:50px">
                                    <td class="default" align="center" width="50" id="numero" style="vertical-align: middle"><%=numero%></td>
                                    <td class="default" align="center" width="50" id="reserva" style="vertical-align: middle"><a href="Reserva.jsp?idReserva=<% out.print(idres);%>"><%=idres%></a></td>
                                        <% if (esProv) {%>
                                    <td class="default" align="center" width="100" id="cliente" style="vertical-align: middle"><%=cliente%></td>
                                    <% }%>
                                    <td class="default" align="center" width="100" id="fecha" style="vertical-align: middle"><%=creada%></td>
                                    <td class="default" align="center" width="100" id="estado" style="vertical-align: middle"><%=estado%></td>
                                    <td class="default" align="center" width="100" id="total" style="vertical-align: middle"><%=total%></td>
                                    <td class="default" align="center" width="200">
                                        <% if (estado == "FACTURADA") {%>
                                        <form role="form" action='' method="post">
                                            <input type='hidden' id='reserva' name='reserva' value=<%=idres%>>
                                            <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-eye-open"></span> Ver Factura</button>
                                        </form>
                                        <% } else %>
                                        <% if (estado == "PARCIAL") {%>
                                        <form role="form" action='' method="">
                                            <input type='hidden' id='reserva' name='reserva' value=<%=idres%>>
                                            <button type="button" class="btn btn-primary" style="background-color: background;"><span class="glyphicon glyphicon-check"></span> Facturada</button>
                                        </form>
                                        <% } else %>
                                        <% if ((estado == "PAGADA") && (esProv)) {%>
                                        <form role="form" action='FacturarReserva' method="post">
                                            <input type='hidden' id='action' name='action' value='Reserva'>
                                            <input type='hidden' id='reserva' name='reserva' value=<%=idres%>>
                                            <input type='hidden' id='reserva' name='total' value=<%=total%>>
                                            <input type='' id='servicioProveedor' name='servicioProveedor' value=<%=cliente%>>
                                            <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-usd"></span> Facturar</button>
                                        </form>
                                        <% } else %>
                                        <% if ((estado == "REGISTRADA") && !(esProv)) {%>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <form role="form" action='PagarReserva' method="post">
                                                    <input type='hidden' id='reserva' name='reserva' value=<%=idres%>>
                                                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-piggy-bank"></span> Pagar</button>
                                                </form></div>
                                            <div class="col-md-4">
                                                <form role="form" action='Email' method="post">
                                                    <input type='hidden' id='reserva' name='reserva' value=<%=idres%>>
                                                    <input type='hidden' id='reserva' name='cliente' value=<%=cliente%>>
                                                    <input type='hidden' id='reserva' name='total' value=<%=total%>>
                                                    <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-envelope"></span> Correo</button>
                                                </form></div>
                                            <div class="col-md-4">
                                                <form role="form" action='CancelarReserva' method="post">
                                                    <input type='hidden' id='reserva' name='reserva' value=<%=idres%>>
                                                    <button type="submit" class="btn btn-danger" onclick="return confirm('Está seguro de cancelar la reserva?')"><span class="glyphicon glyphicon-remove"></span> Cancelar</button>
                                                </form></div>
                                        </div>
                                        <% } %>
                                    </td>
                                </tr>
                                <%}
                                %>
                            </tbody>
                        </table>
                        <!--<button type="submit" class="btn btn-danger" style="float:right;">Cancelar</button>-->
                        <ul class="list-group"></ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- <footer class="section section-primary" id="footer"> /-->
        <jsp:include page="WEB-INF/Footer.jsp"/>
        <!-- </footer> /-->
    </body>
</html>
