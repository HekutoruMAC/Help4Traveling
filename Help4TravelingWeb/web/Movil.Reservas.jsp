<%-- 
    Document   : Movil.Reservas
    Created on : 17/11/2016, 07:15:34 PM
    Author     : yaman
--%>

<%@page import="servidorpublicador.DtItemReserva"%>
<%@page import="servidorpublicador.DataItemsReservasArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="servidorpublicador.DtReserva"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

    </head> 
    <%
        servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
        servidorpublicador.Publicador port = service.getPublicadorPort();
        //String nick = session.getAttribute("nickname").toString();
        String nick = "mHooch";
        servidorpublicador.DtUsuario dtProv = null;

        dtProv = port.getDtProveedor(nick);%>
    <body>
        <jsp:include page="WEB-INF/Header.jsp"/>
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h2> Reservas del Proveedor </h2>
                    </div>    
                </div>
            </div>       
        </div>  
        <div class="row">
            <div class="col-md-12">
                <div class="panel-group">
                    <div class="row"></div>

                    <%  DtReserva dtRes = null;
                        List<DtReserva> reservas;

                        //reservas = fab.getIControladorReserva().listarReservasProveedor(nick);
                        reservas = port.listarReservasProveedor(nick).getReservasProveedor();

                        Iterator<DtReserva> iter = reservas.iterator();
                        Integer i = 0;
                        while (iter.hasNext()) {
                            i++;
                            dtRes = iter.next();
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
                            }%>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-primary">
                                <div class="panel-heading"> Reserva <%=id%></div>


                                <%   DataItemsReservasArrayList Items = port.listarItems((int) (long) id);
                                    List<DtItemReserva> listaItems = Items.getItems();

                                    DtItemReserva dtItem = null;%>
                                <div class="panel-body">

                                    <%Iterator<DtItemReserva> iter2 = listaItems.iterator();
                                        while (iter2.hasNext()) {

                                            dtItem = iter2.next();
                                            String servicio = dtItem.getOferta().getNombre();%>
                                    <div class="row" >  

                                        <div class="panel panel-warning" ><%=servicio%></div>


                                    </div>
                                    <%}%>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%}%>

        <jsp:include page="WEB-INF/Footer.jsp"/>

    </body>

</html>