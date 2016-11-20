<%--
    Document   : MovilServiciosProveedor
    Created on : 18-nov-2016, 11:22:45
    Author     : Leonardo
--%>

<%@page import="servidorpublicador.DtServicio"%>
<%@page import="servidorpublicador.DtUsuario"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
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
        <title>Servicios del Proveedor</title>
    </head>
    <body>
        <jsp:include page="WEB-INF/Header.jsp"/>
        <%  servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
            servidorpublicador.Publicador port = service.getPublicadorPort();
            String nickname = (String) session.getAttribute("nickname");
            //String nickname = "remus";
            DtUsuario dtProv = port.getDtProveedor(nickname);
            String nombre = dtProv.getNombre() + " " + dtProv.getApellido();
            List<DtServicio> servicios = port.listarServiciosProveedor(dtProv).getServiciosProveedor();
        %>
        <div class="section minimo">
            <div class="container">
                <div class="row">
                    <h1>Servicios del Proveedor</h1>
                </div>
                <hr>
                <%  if (!servicios.isEmpty()) {
                        Iterator<DtServicio> iserv = servicios.iterator();
                        while (iserv.hasNext()) {
                            DtServicio dtServ = iserv.next();
                            String servicio = dtServ.getNombre();
                            String descripcion = dtServ.getDescripcion();
                            String imagen = dtServ.getImagenes().get(0); %>
                <div class="row">
                    <div class="col-xs-12"><img src="<% out.print(imagen);%>" class="img-responsive" alt="Imagen responsive"></div>
                    <div class="col-xs-12"><h3><%=servicio%></h3></div>
                    <div class="col-xs-12"><%=descripcion%></div>
                    <hr>&nbsp;
                </div>

                <%  }
                } else { %>
                <div class="row">
                    <div class="col-xs-12"><b>El proveedor seleccionado no tiene servicios asociados.</b></div>
                </div>
                <% }%>
                <!--/div-->
            </div>
        </div>
        <jsp:include page="WEB-INF/Footer.jsp"/>
    </body>
</html>