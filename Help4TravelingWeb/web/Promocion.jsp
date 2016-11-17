<%--
    Document   : Promocion
    Created on : 15-oct-2016, 15:42:54
    Author     : Leonardo
--%>

<%--@page import="Logica.Fabrica"%>
<%@page import="Logica.DtPromocion"%>
<%@page import="Logica.ManejadorProveedor"%>
<%@page import="Logica.ManejadorServicio"--%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%--@page import="Logica.DtCategoria"--%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.ArrayList"%>
<%--@page import="Logica.DtUsuario"--%>
<%@page import="java.util.List"%>
<%--@page import="Logica.ManejadorCategoria"%>
<%@page import="Logica.DtServicio"--%>
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
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css\test.css" rel="stylesheet" type="text/css">
        <link href="calendario/calendario_dw/calendario_dw-estilos.css" type="text/css" rel="stylesheet">
        <script>
            $(document).ready(function () {
                setTimeout(function () {
                    $("#fechaIni").calendarioDW();
                    $("#fechaFin").calendarioDW();

            <%if ((String) session.getAttribute("nickname") != null) {%>
                    $('#idIniciar').hide();
                    $('#idRegistrar').hide();
            <%} else {%>
                    $('#idPerfil').hide();
                    $('#idReservas').hide();
                    $('#idSalir').hide();
            <%}%>;


                    $("#frmAgregar").submit(function (event) {
                        var Cantidad = $("#cantidad_in").val();
                        if ((Cantidad === "") || (parseInt(Cantidad) === 0)) {
                            alert("Debe ingresar una cantidad valida.");
                            event.preventDefault();
                        } else if ($("#fechaIni").val() === "") {
                            alert("Debe ingresar fecha de inicio.");
                            event.preventDefault();
                        } else if ($("#fechaFin").val() === "") {
                            alert("Debe ingresar fecha de finalizacion.");
                            event.preventDefault();
                        } else {
                            return;
                        }
                    });
                }, 100);
            });
        </script>
    </head>
    <title>Detalle de Promoción</title>
    <div class="navbar navbar-default navbar-fixed-top" id="header"></div>
    <div>
        <p>AAA</p>
    </div>
    <!--div class="section"></div-->
    <% String nombre = (String) request.getParameter("nombre");
       String proveedor = (String) request.getParameter("proveedor");
       servidorpublicador.PublicadorService servicio = new servidorpublicador.PublicadorService();
       servidorpublicador.Publicador port = servicio.getPublicadorPort();
       servidorpublicador.DtPromocion dtProm = port.getDTPromocion(nombre, proveedor);%>
    <div class="section minimo">
        <div class="container">
            <div class="row">
                <p class="default">Ofertas en Hoteles, Paquetes de viaje y más</p>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="section">
                        <div class="container">
                            <div class="col-md-6">
                                <div class="row default">
                                    <h2><b>Servicios de la Promoción</b></h2>
                                </div>
                                <hr>
                                <table class="default table table-bordered table-hover table-striped table-responsive">
                                    <thead>
                                        <tr class="default">
                                            <td class="default" width="200" align="center"><b>Nombre</b></td>
                                            <td class="default" width="50" align="center"><b>Proveedor</b></td>
                                            <td class="default" width="400" align="center"><b>Descripción</b></td>
                                            <td class="default" width="50" align="center"><b>Precio</b></td>
                                            <td class="default" width="100" align="center"><b>Origen</b></td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%  float total = 0;
                                            List<String> nomsServProm = new ArrayList<String>();
                                            nomsServProm = port.listarServiciosDePromociones(nombre, proveedor).getServiciosPromociones();                                            
                                            if (!nomsServProm.isEmpty()) {
                                                List<servidorpublicador.DtServicio> serviciosProm = new ArrayList<servidorpublicador.DtServicio>();
                                                Iterator<String> itnomserv = nomsServProm.iterator();
                                                while (itnomserv.hasNext()) {
                                                    String servprom = itnomserv.next();
                                                    String provserv = port.getNkProveedorServicio(servprom);
                                                    servidorpublicador.DtServicio dtServ = port.getDtServicio(servprom, provserv);
                                                    serviciosProm.add(dtServ);
                                                }
                                                if (!serviciosProm.isEmpty()) {
                                                    Iterator<servidorpublicador.DtServicio> itserv = serviciosProm.iterator();
                                                    total = 0;
                                                    while (itserv.hasNext()) {
                                                        servidorpublicador.DtServicio dtServ1 = itserv.next();
                                                        String nomserv = dtServ1.getNombre();
                                                        String provserv = dtServ1.getNkproveedor();
                                                        String descripcion = dtServ1.getDescripcion();
                                                        float precio = dtServ1.getPrecio();
                                                        String origen = dtServ1.getNomciuorigen();                                                        
                                                        total = total + precio; %>
                                        <tr class="default">
                                            <td class="default" align="center" width="200" id="nombre"><a href="Servicio.jsp?nombre=<% out.print(nomserv); %>&proveedor=<% out.print(provserv); %>&categoria=<% out.print("");%>" target="_blank"><%=nomserv%></a></td>
                                            <td class="default" align="center" width="50" id="proveedor"><%=provserv%></td>
                                            <td class="default" align="center" width="400" id="descripcion"><%=descripcion%></td>
                                            <td class="default" align="center" width="50" id="precio"><%=precio%></td>
                                            <td class="default" align="center" width="100" id="origen"><%=origen%></td>
                                        </tr>
                                        <%  }
                                                }
                                            }%>
                                    </tbody>
                                </table>
                                <ul class="list-group"></ul>
                            </div>
                            <div class="col-md-6">
                                <h1><%=nombre%></h1>
                                <h3><% servidorpublicador.DtUsuario prov = port.getDtProveedor(proveedor);
                                    out.print(prov.getNombre());
                                    out.print(" ");
                                    out.print(prov.getApellido()); %></h3>
                                <hr>
                                <%  float descuento = Float.parseFloat(dtProm.getPrecio());
                                    if (descuento > 0) {%>
                                <h3><%=descuento%>% de Descuento</h3>
                                <% } %>
                                <hr>
                                <% if (descuento > 0) {
                                        total = total - (total * descuento / 100);
                                    }%>
                                <h3>U$D <%=total%><% if (descuento > 0) { %> (con Descuento aplicado) <% }%></h3>
                                <hr>
                                <form id="frmAgregar" class="form-horizontal" role="form" action="agregarCarrito" method="post">
                                    <div class="form-group">
                                        <div class="col-sm-4">
                                            <input type="hidden" class="hiddden" id="servicio_in" name="servicio_in" value="<%=nombre%>">
                                            <input type="hidden" class="hiddden" id="precio_in" name="precio_in" value="<%=total%>">
                                            <div class="form-group" style="width: 100%; margin-left: 0.5px">
                                                <label for="fecha">Fecha inicio</label>
                                                <br>
                                                <input type="text"  class="form-control input-lg" id="fechaIni" name="fechaIni" value="" />
                                            </div>
                                            <% if (((String) session.getAttribute("nickname") != null) && !((Boolean) session.getAttribute("esProv"))) {%>
                                            <input type="text" class="form-control input-lg" id="cantidad_in" name="cantidad_in"  placeholder="Cantidad">
                                            <%} else {%>
                                            <input type="text" disabled="true"class="form-control input-lg" id="cantidad_in" name="cantidad_in"  placeholder="Cantidad">
                                            <%}%>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="form-group" style="width: 100%; margin-left: 0.5px">
                                                <label for="fecha">Fecha fin</label>
                                                <br>
                                                <input type="text"  class="form-control input-lg" id="fechaFin" name="fechaFin" value="" />
                                            </div>
                                            <% if (((String) session.getAttribute("nickname") != null) && !((Boolean) session.getAttribute("esProv"))) {%>
                                            <button type="submit" width="5000" class="active btn btn-info btn-lg">Agregar al Carrito</button>
                                            <%} else {%>
                                            <button type="submit" width="5000" disabled="true" class="active btn btn-info btn-lg">Agregar al Carrito</button>
                                            <%}%>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="section section-primary" id="footer"></footer>
