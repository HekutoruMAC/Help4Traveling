

<%@page import="servidorpublicador.Date"%>
<%@page import="servidorpublicador.Proveedor"%>
<%@page import="servidorpublicador.Oferta"%>
<%@page import="servidorpublicador.DtItemReserva"%>
<%@page import="servidorpublicador.ItemReserva"%>
<%--@page import="Logica.ManejadorReserva"--%>
<%--@page import="Logica.Proveedor"--%>
<%--@page import="Logica.Date"--%>
<%--@page import="Logica.Oferta"--%>
<%@page import="java.util.Iterator"%>
<%--@page import="Logica.IControladorReserva"--%>
<%--@page import="Logica.DtItemReserva"--%>
<%@page import="java.util.List"%>
<%--@page import="Logica.Fabrica"--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
        <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <script src="js/jquery-3.1.1.js"></script>
        <script src="js/includes.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <link type="text/css" href="css/jquery-ui.css" rel="Stylesheet" />
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css\test.css" rel="stylesheet" type="text/css">
        <title><%out.println("reserva numero " + request.getParameter("idReserva"));%> </title>
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
        <div class="navbar navbar-default navbar-fixed-top" id="header"></div>

        <% String IdReserva = request.getParameter("idReserva");
            //Fabrica fab = Fabrica.getInstance();
             servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
             servidorpublicador.Publicador port = service.getPublicadorPort();
            Integer numRes = Integer.parseInt(IdReserva);
            System.out.println("Este es el numero de reserva" + numRes);
            List<ItemReserva> itemsReserva;

            

            //itemsReserva= port.listarItems(numRes);
            //itemsReserva= ManejadorReserva.getInstance().listarItems(numRes);
            //aca recibe como parametro la id de reservay devuelve los items vinculados
            //   out.println("la cantidad devuelta es: "+itemsReserva.size());
            /*
    Para la reserva seleccionada se muestra los servicios que
    contiene (mostrando para cada uno su nombre, su precio
    unitario, su estado y si es Promoción o Servicio), el nickname del
    proveedor al que se solicitó el pedido, qué cantidades se
    eligieron para cada servicio y el total general de la reserva
             */
        %>
        <div class="section minimo">
            <div class="container">
                <div class="row">
                    <p class="default">Ofertas en Hoteles, Paquetes de viaje y más</p>
                    <%--          <a><%=out.println("la cantidad devuelta es: "+itemsReserva.size())%></a>--%>

                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="section">
                            <div class="container">
                                <div class="row default">

                                    <table class="default table table-bordered table-hover table-striped">
                                        <tbody>
                                            <tr class="default">
                                                <td class="default" width="200" align="center"><b>nombre</b></td>
                                                <td class="default" width="100" align="center"><b>proveedor</b></td>
                                                <td class="default" width="100" align="center"><b>tipo</b></td>
                                                <td class="default" width="100" align="center"><b>cantidad</b></td>
                                                <td class="default" width="100" align="center"><b>inicio</b></td>
                                                <td class="default" width="100" align="center"><b>fin</b></td>



                                            </tr>
                                            <%            
                                                ///declaraciones
                                                List<DtItemReserva> items = port.listarItems(Integer.parseInt(IdReserva)).getItems();
                                                Iterator<DtItemReserva> iter = items.iterator();
                                                System.out.println("cargue el iter");

                                                DtItemReserva ItRes;

                                                int cant;
                                                Oferta ofertaVinculada = null;
                                                String inicio, fin;
                                                String NombOfr;
                                                Proveedor Prov;
                                                String NombProv;
                                                String servoprom;
                                                String partesfech[];
                                                while (iter.hasNext()) {
                                                    //primero itero
                                                    ItRes = iter.next();

                                                    //  saco las caracteristicas del item
                                                    System.out.println("entre al while");
                                                    cant = ItRes.getCantidad();
                                                    System.out.println(cant);
                                                    ofertaVinculada = ItRes.getOferta();
                                                    Date iniciodate = ItRes.getInicio();
                                                    inicio=iniciodate.toString();
                                                    
                                                    
                                                    inicio = String.valueOf(iniciodate.getDia()) + "/" + String.valueOf(iniciodate.getMes()) + "/" + String.valueOf(iniciodate.getAno());
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    System.out.println(inicio);
                                                    Date findate = ItRes.getFin();
                                                    fin = String.valueOf(findate.getDia()) + "/" + String.valueOf(findate.getMes()) + "/" + String.valueOf(findate.getAno());
                                                    System.out.println(fin);
                                                    NombOfr = ofertaVinculada.getNombre();
                                                    Prov = ofertaVinculada.getProveedor();
                                                    NombProv = Prov.getNombre();
                                                    //System.out.println(NombProv);
                                                    servoprom = ofertaVinculada.getClass().getName();
                                                    //invierto fecha inicio
                                                    //partesfech = inicio.split("/");
                                                    //inicio = partesfech[2] + "/" + partesfech[1] + "/" + partesfech[0];
                                                    //partesfech = fin.split("/");
                                                    //fin = partesfech[2] + "/" + partesfech[1] + "/" + partesfech[0];
                                                    boolean esservicio = port.existeServicio(NombOfr);

                                            %>
                                            <tr class="default">
                                                <td class="default" align="center" width="200" id="numero"><%=NombOfr%></td>
                                                <td class="default" align="center" width="100" id="numero"><%=NombProv%></td>
                                                <% if (esservicio) {%>

                                                <td class="default" align="center" width="100" id="tipo">servicio</td>
                                                <%} else {%>
                                                <td class="default" align="center" width="100" id="tipo">promocion</td>
                                                <%}%>
                                                <%--     <td class="default" align="center" width="400" id="descripcion"><%=servoprom%></td>      --%>
                                                <%--<td class="default" align="center" width="200" id="proveedor"><%=NombProv%></td>--%>
                                                <td class="default" align="center" width="100" id="precio"><%=cant%></td>
                                                <td class="default" align="center" width="100" id="precio"><%=inicio%></td>
                                                <td class="default" align="center" width="100" id="precio"><%=fin%></td>
                                                <%-- <td class="default" align="center" width="100" id="origen"><%=total%></td>--%>
                                            </tr>



                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                    <ul class="list-group"></ul>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="section section-primary" id="footer"></footer>
    </body>
</html>
