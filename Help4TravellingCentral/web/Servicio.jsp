<%--
    Document   : Servicio
    Created on : 03-oct-2016, 23:40:06
    Author     : Leonardo
--%>

<%@page import="Logica.ManejadorProveedor"%>
<%@page import="Logica.ManejadorServicio"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="Logica.DtCategoria"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Logica.DtUsuario"%>
<%@page import="java.util.List"%>
<%@page import="Logica.ManejadorCategoria"%>
<%@page import="Logica.DtServicio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="js/jquery-3.1.0.min.js"></script>
        <script src="js/includes.js"></script>
        <script type="text/javascript" src="calendario/calendario_dw/calendario_dw.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <link type="text/css" href="css/jquery-ui.css" rel="Stylesheet" />
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css\test.css" rel="stylesheet" type="text/css">
        <link href="calendario/calendario_dw/calendario_dw-estilos.css" type="text/css" rel="stylesheet">
        <script>
            function cambiar(obj) {
                var img = obj.id; // Obtenemos la ID del objeto
                var img_dos = document.getElementById(img).src // Obtenemos la ruta del objeto con ID obtenida atrás
                document.getElementById('principal').src = img_dos; // Cambiamos la ruta de la imagen por la obtenida antes
            }
        </script>
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
    <title>Detalle de Servicio</title>
    <div class="navbar navbar-default navbar-fixed-top" id="header"></div>
    <div>
        <p>AAA</p>
    </div>
    <!--div class="section"></div-->
    <% String nombre = (String) request.getParameter("nombre");
        String proveedor = (String) request.getParameter("proveedor");
        String categoria = (String) request.getParameter("categoria");
        String padre = null;
        String abuelo = null;
        String bisabuelo = null;
        padre = ManejadorCategoria.getInstance().obtenerPadre(categoria);
        DtServicio dtServ = ManejadorServicio.getInstance().getDtServicio(nombre, proveedor);
    %>
    <div class="section minimo">
        <div class="container">
            <div class="row">
                <p class="default">Ofertas en Hoteles, Paquetes de viaje y más</p>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <ul class="breadcrumb">
                        <%  if (padre != null) {
                                abuelo = ManejadorCategoria.getInstance().obtenerPadre(padre);
                                if (abuelo != null) {
                                    bisabuelo = ManejadorCategoria.getInstance().obtenerPadre(abuelo);
                                    if ((bisabuelo != null) && (!bisabuelo.equals("Categorias"))) { %>
                        <li><% out.print(bisabuelo); %></li>
                            <% } %>
                        <li><% out.print(abuelo); %></li>
                            <%  }  %>
                        <li><% out.print(padre); %></li>
                            <% }%>
                        <li class="active"><%=categoria%></li>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="section">
                        <div class="container">
                            <div class="row default">
                                <% List<String> imagenes = dtServ.getImagenes();
                                    int cantimgs = imagenes.size();
                                    System.out.println(cantimgs);
                    if (cantimgs > 0)
                        if (cantimgs == 1) { %>
                                <div class="col-md-6">
                                    <a href="#"><img src="<% out.print(imagenes.get(0)); %>" class="img-responsive"></a>
                                    <hr>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <a href="#"><img src="<% out.print(imagenes.get(0)); %>" class="img-responsive"></a>
                                        </div>
                                    </div>
                                    <% } else if (cantimgs == 2) { %>
                                    <div class="col-md-6">
                                        <img src="<% out.print(imagenes.get(0)); %>" alt="Imagen" id="principal" class="img-responsive">
                                        <hr>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <a href="#"><img src="<% out.print(imagenes.get(0)); %>" alt="Imagen" id="img0" onclick="cambiar(this);" class="img-responsive"></a>
                                            </div>
                                            <div class="col-md-4">
                                                <a href="#"><img src="<% out.print(imagenes.get(1)); %>" alt="Imagen" id="img1" onclick="cambiar(this);" class="img-responsive"></a>
                                            </div>
                                        </div>
                                        <% } else if (cantimgs == 3) { %>
                                        <div class="col-md-6">
                                            <img src="<% out.print(imagenes.get(0)); %>" alt="Imagen" id="principal" class="img-responsive">
                                            <hr>
                                            <div class="row">
                                                <div class="col-md-4">
                                                    <a href="#"><img src="<% out.print(imagenes.get(0)); %>" alt="Imagen" id="img0" onclick="cambiar(this);" class="img-responsive"></a>
                                                </div>
                                                <div class="col-md-4">
                                                    <a href="#"><img src="<% out.print(imagenes.get(1)); %>" alt="Imagen" id="img1" onclick="cambiar(this);" class="img-responsive"></a>
                                                </div>
                                                <div class="col-md-4">
                                                    <a href="#"><img src="<% out.print(imagenes.get(2)); %>" alt="Imagen" id="img2" onclick="cambiar(this);" class="img-responsive"></a>
                                                </div>
                                            </div>
                                            <% } %>
                                            <hr>
                                            <table class="default table table-bordered table-hover table-striped" style="font-size:20px">
                                                <tbody>
                                                    <tr class="default">
                                                        <td class="default" width="200">Proveedor</td>
                                                        <% String nickname = dtServ.getNkProveedor();
                                                            DtUsuario prov = ManejadorProveedor.getInstance().getDtProveedor(nickname);
                                                        %>
                                                        <td id="nomprov"><% out.print(prov.getNombre());
                              out.print(" ");
                              out.print(prov.getApellido());%></td>
                                                    </tr>
                                                    <tr>
                                                        <td class="default" width="200">Origen</td>
                                                        <td id="ciuorigen"><%=dtServ.getNomCiuOrigen()%></td>
                                                    </tr>
                                                    <% if (dtServ.getNomCiuDestino() != null) {%>
                                                    <tr>
                                                        <td class="default" width="200">Destino</td>
                                                        <td id="ciudestino"><%=dtServ.getNomCiuDestino()%></td>
                                                    </tr>
                                                    <% }%>
                                                    <tr>
                                                        <td class="default" width="200">Precio</td>
                                                        <td id="precio">U$D <%=dtServ.getPrecio()%></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <ul class="list-group"></ul>
                                        </div>
                                        <div class="col-md-6">
                                            <h1><%=nombre%></h1>
                                            <h3><%=dtServ.getDescripcion()%><h3>
                                                    <hr>
                                                    <h3>U$D <%=dtServ.getPrecio()%></h3>
                                                    <form id="frmAgregar" class="form-horizontal" role="form" action="agregarCarrito" method="post">
                                                        <div class="form-group">
                                                            <div class="col-sm-4">
                                                                <input type="hidden" class="hiddden" id="servicio_in" name="servicio_in" value="<%=nombre%>">
                                                                <input type="hidden" class="hiddden" id="precio_in" name="precio_in" value="<%=dtServ.getPrecio()%>">
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
                                                    <hr>
                                                    <h3>Categorías</h3>
                                                    <% Map<String, DtCategoria> categorias = dtServ.getDtCategorias();
                     Iterator<DtCategoria> iter = categorias.values().iterator();
                     if (iter.hasNext()) {%>
                                                    <h4 class="lead"><%=iter.next().getNombre()%>
                                                        <% while (iter.hasNext()) {%>
                                                        | <%=iter.next().getNombre()%>
                                                        <% } %>
                                                        <% }%>
                                                    </h4>
                                                    </div>
                                                    </div>
                                                    </div>
                                                    </div>
                                                    </div>
                                                    </div>
                                                    </div>
                                                    </div>
                                                    <footer class="section section-primary" id="footer"></footer>
                                                    </html>
