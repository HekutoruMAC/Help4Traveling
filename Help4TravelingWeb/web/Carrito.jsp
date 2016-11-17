<%--
    Document   : Carrito
    Created on : 05-oct-2016, 11:33:26
    Author     : yaman
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="Servlets.Reserva"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

<!-- <div class="navbar navbar-default navbar-fixed-top" id="header"> /-->
<jsp:include page="WEB-INF/Header.jsp"/>
<!-- </div> /-->
<div>
    <p>A</p>
</div>
<div class="section minimo">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="section">
                    <div class="container">
                        <div class="row default"><h2><b>Carrito de Compras</b></h2></div>
                        <hr>
                        <%
                            String nickname;
                            nickname = "";
                            HttpSession sesion = request.getSession();

                            if (sesion.getAttribute("nickname") != null) {
                                nickname = (String) sesion.getAttribute("nickname");
                            }

                            if (sesion.getAttribute("carrito") != null) {

                                List<Reserva> reservas = (List<Reserva>) sesion.getAttribute("carrito");
                                Iterator<Reserva> iter = reservas.iterator();
                                Reserva reserva = null;

                                out.print("<div class='row default'>"
                                        + "<table class='default table table-bordered table-hover table-striped table-responsive'>"
                                        + "<tbody>"
                                        + "<tr class='default'>"
                                        + "<td class='default' width='300' align='center'><b><h1>Reservas de " + nickname + "</h1></b></td>"
                                        + " </tr>");

                                out.print("<table class='table'>"
                                        + "<thead>"
                                        + "<tr>"
                                        + "<th><h3>Reserva</h3></th>"
                                        + "<th><h3>Precio</h3></th>"
                                        + "<th><h3>Cantidad</h3></th>"
                                        + "<th><h3>Fecha Inicial</h3></th>"
                                        + "<th><h3>Fecha Final</h3></th>"
                                        + "</tr>"
                                        + "</thead>"
                                        + "<tbody>"
                                        + "<tr>");

                                while (iter.hasNext()) {
                                    reserva = iter.next();
                                    String partesini[] = reserva.getFechaini().split("-");
                                    String fechaini = partesini[2] + "/" + partesini[1] + "/" + partesini[0];
                                    String partesfin[] = reserva.getFechafin().split("-");
                                    String fechafin = partesfin[2] + "/" + partesfin[1] + "/" + partesfin[0];
                                    out.println("<td><h3>" + reserva.getServicio() + "</h3></td>"
                                            + "<td><h3>" + reserva.getPrecio() + "</h3></td>"
                                            + "<td><h3>" + reserva.getCantidad() + "</h3></td>"
                                            + "<td><h3>" + fechaini + "</h3></td>"
                                            + "<td><h3>" + fechafin + "</h3></td>"
                                            + "<form action='eliminarCarrito' method=post>"
                                            //+ "<td width='80' align='center'>"

                                            //+ "<input type='submit' name='comprar' id='comprar' value='Comprar' align='center' width='80' class='active btn btn-info btn-lg'></td>"
                                            + "<td width='80' align='center'>"
                                            + "<input type='hidden' id='servicio_in' name='servicio_in' value='" + reserva.getServicio() + "'>"
                                            + "<input type='submit' name='eliminar' id='eliminar' value='Eliminar del Carrito' align='center' width='80' class='active btn btn-info btn-lg'></a></td>"
                                            + "</tr>"
                                            + "</form>");
                                }

                                out.println(
                                        "<th><h2>Total</h2></th>"
                                        + "<td><h2> U$U " + sesion.getAttribute("preciototal") + "</h2></td>"
                                        + "<form action='eliminarCarrito' method=post>"
                                        + "<td width='80' align='center'>"
                                        + "<input type='submit' name='comprar' id='comprar' value='Comprar' align='center' width='80' class='active btn btn-info btn-lg'></a></td>"
                                        + "</tr>"
                                        + " </form>"
                                        + "</tbody>"
                                        + "</table>");
                            } else {

                                out.println("<tbody>"
                                        + "<table>"
                                        + "<th><h2> Carrito Vacío </h2></th>"
                                        + "</tbody>"
                                        + "</table>");
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
<!-- <footer class="section section-primary" id="footer"> /-->
<jsp:include page="WEB-INF/Footer.jsp"/>
<!-- </footer> /-->
