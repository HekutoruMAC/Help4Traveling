<%--
    Document   : ListarServicios
    Created on : 13-oct-2016, 9:43:58
    Author     : Leonardo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Iterator"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
</head>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="row default"><h2><b>Listado de Servicios</b></h2></div>
            <hr>

            <%
                List<servidorpublicador.DtServicio> servicios = new ArrayList<servidorpublicador.DtServicio>();
                String categoria = request.getParameter("categoria");
                servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
                servidorpublicador.Publicador port = service.getPublicadorPort();

                if (!categoria.equals("null")) {
                    List<String> nombres = new ArrayList<String>();
                    nombres = port.listarServiciosCategorias(categoria).getServiciosCategorias();
                    if (!nombres.isEmpty()) {
                        Iterator<String> inom = nombres.iterator();
                        while (inom.hasNext()) {
                            String nombre = inom.next();
                            String proveedor = port.getNkProveedorServicio(nombre);
                            servidorpublicador.DtServicio dtServ = port.getDtServicio(nombre, proveedor);
                            servicios.add(dtServ);
                        }
                    }

                } else {
                    servicios = port.listarServicios().getServicios();
                }
                if (!servicios.isEmpty()) {
                    Iterator<servidorpublicador.DtServicio> iserv = servicios.iterator(); %>
            <table class="default table table-bordered table-hover table-striped">
                <thead>
                    <tr class="default">
                        <td class="default" width="100" align="center"><b>Nombre</b></td>
                        <td class="default" width="300" align="center"><b>Descripcion</b></td>
                        <td class="default" width="100" align="center"><b>Proveedor</b></td>
                        <td class="default" width="100" align="center"><b>Precio</b></td>
                        <td class="default" width="100" align="center"><b>Origen</b></td>
                    </tr>
                </thead>
                <tbody>
                    <% while (iserv.hasNext()) {
                            servidorpublicador.DtServicio dtServ = iserv.next();
                            String nombre = dtServ.getNombre();
                            String proveedor = dtServ.getNkproveedor();
                            String descripcion = dtServ.getDescripcion();
                            float precio = dtServ.getPrecio();
                            String origen = dtServ.getNomciuorigen();  %>
                    <tr class="default">
                        <td class="default" align="center" width="100" id="nombre"><a href="Servicio.jsp?nombre=<% out.print(nombre); %>&proveedor=<% out.print(proveedor); %>&categoria=<% out.print(categoria);%>" target="_blank"><%=nombre%></a></td>
                        <td class="default" align="center" width="300" id="descripcion"><%=descripcion%></td>
                        <td class="default" align="center" width="100" id="proveedor"><%=proveedor%></td>
                        <td class="default" align="center" width="100" id="precio"><%=precio%></td>
                        <td class="default" align="center" width="100" id="origen"><%=origen%></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
            <% } else { %>
            <div class="row default"><h2><b>La categoría seleccionada no tiene servicios asociados.</b></h2></div>
                        <% }%>
            <ul class="list-group"></ul>
        </div>
    </div>
</div>
