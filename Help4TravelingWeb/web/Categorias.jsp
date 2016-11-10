<%--
    Document   : Usuarios
    Created on : 05-oct-2016, 11:33:26
    Author     : yaman
--%>


<%@page import="java.util.Iterator"%>
<%@page import="Logica.ManejadorCategoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="js/includes.js"></script>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="css\test.css" rel="stylesheet" type="text/css">
    <script src="js/jquery-3.1.1.js"></script>
    <script src="js/jstree.min.js"></script>
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
    <!--script>
        $(document).ready(function () {
            $("#selector").jstree();
            $("#selector").on(("select_node.jstree", function (e, data) {
            var href = data.node.a_attr.href;
            var target = data.node.a_attr.target;
            document.location.href = href;
        });
    </script-->
</head>
<div class="section">
    <div class="col-md-12">
        <div>
            <div>
                <p>Ofertas en Hoteles, Paquetes de viaje y más</p>
                <div><h2><b>Arbol de Categorías</b></h2></div>
                <hr>
                <div><!--div class="row default"-->
                    <div id="selector">
                        <ul>
                            <%  String catn0 = "Categorias"; %>
                            <li class="jstree-open"><a href="ListarServicios.jsp?categoria=<% out.print(catn0);%>" target="servicios"><%=catn0%></a><%
                                List<String> listaNivel1 = ManejadorCategoria.getInstance().obtenerCategoriasHijas(catn0);
                                if (!listaNivel1.isEmpty()) {
                                %><ul><%
                                    Iterator<String> icatn1 = listaNivel1.iterator();
                                    while (icatn1.hasNext()) {
                                        String catn1 = icatn1.next();
                                    %><li><a href="ListarServicios.jsp?categoria=<% out.print(catn1);%>" target="servicios"><%=catn1%></a><%
                                        List<String> listaNivel2 = ManejadorCategoria.getInstance().obtenerCategoriasHijas(catn1);
                                        if (!listaNivel2.isEmpty()) {
                                        %><ul><%
                                            Iterator<String> icatn2 = listaNivel2.iterator();
                                            while (icatn2.hasNext()) {
                                                String catn2 = icatn2.next();
                                            %><li><a href="ListarServicios.jsp?categoria=<% out.print(catn2);%>" target="servicios"><%=catn2%></a><%
                                                List<String> listaNivel3 = ManejadorCategoria.getInstance().obtenerCategoriasHijas(catn2);
                                                if (!listaNivel3.isEmpty()) {
                                                %><ul><%
                                                    Iterator<String> icatn3 = listaNivel3.iterator();
                                                    while (icatn3.hasNext()) {
                                                        String catn3 = icatn3.next();
                                                    %><li><a href="ListarServicios.jsp?categoria=<%=catn3%>" target="servicios"><%=catn3%></a><%
                                                    List<String> listaNivel4 = ManejadorCategoria.getInstance().obtenerCategoriasHijas(catn3);
                                                    if (!listaNivel4.isEmpty()) {
                                                        %><ul><%
                                                            Iterator<String> icatn4 = listaNivel4.iterator();
                                                            while (icatn4.hasNext()) {
                                                                String catn4 = icatn4.next();
                                                            %><li><a href="ListarServicios.jsp?categoria=<% out.print(catn4);%>" target="servicios"><%=catn4%></a></li><%
                                                                }
                                                                %></ul><%
                                                            }
                                                            %></li><%
                                                        }
                                                        %></ul><%
                                                    }
                                                    %></li><%
                                                }
                                                %></ul><%
                                            }
                                            %></li><%
                                        }
                                        %></ul><%
                                    }
                                    %></li>
                        </ul>
                    </div>
                    <ul class="list-group"></ul>
                </div>
            </div>
        </div>
    </div>
</div>


