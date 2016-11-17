<%--
    Document   : Usuarios
    Created on : 05-oct-2016, 11:33:26
    Author     : Leonardo
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
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
        <script type="text/javascript" src="js/jquery-ui.js"></script>
        <link type="text/css" href="css/jquery-ui.css" rel="Stylesheet" />
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css\test.css" rel="stylesheet" type="text/css">
        <script src="https://code.jquery.com/jquery-1.12.3.js"></script>
        <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <!--
        <link href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
        <link href="http://cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet"/>
        <script src="http://cdn.datatables.net/plug-ins/f2c75b7247b/integration/bootstrap/3/dataTables.bootstrap.js"></script>
        /-->

        <script>
            $(document).ready(function () {
                $('.data-table').DataTable();
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
        <title>Listado de Usuarios</title>
        <!-- <div class="navbar navbar-default navbar-fixed-top" id="header"> /-->
        <jsp:include page="WEB-INF/Header.jsp"/>
        <!-- </div> /-->

        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="section">
                            <div class="container">
                                <div class="row default"><h2><b>Listado de Usuarios</b></h2></div>
                                <hr>
                                <div class="row default">
                                    <table id="data-table" class="display default table table-bordered table-hover table-striped table-responsive dataTable data-table">
                                        <tbody>
                                            <tr class="default">
                                                <td class="default" width="200" align="center"><b>Nickname</b></td>
                                                <td class="default" width="200" align="center"><b>Nombre</b></td>
                                                <td class="default" width="200" align="center"><b>Apellido</b></td>
                                                <td class="default" width="200" align="center"><b>Correo electrónico</b></td>
                                                <td class="default" width="200" align="center"><b>Fecha Nacimiento</b></td>
                                            </tr>
                                            <%  servidorpublicador.DtUsuario dtUsu = null;
                                                List<servidorpublicador.DtUsuario> usuarios;
                                                servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
                                                servidorpublicador.Publicador port = service.getPublicadorPort();
                                                usuarios = port.listarUsuariosSistema().getUsuariosSistema();
                                                Iterator<servidorpublicador.DtUsuario> iter = usuarios.iterator();
                                                while (iter.hasNext()) {
                                                    dtUsu = iter.next();
                                                    String nick = dtUsu.getNickname();
                                                    String nombre = dtUsu.getNombre();
                                                    String apellido = dtUsu.getApellido();
                                                    String correo = dtUsu.getCorreo();
                                                    servidorpublicador.Date fechanac = dtUsu.getNacimiento();
                                                    String nacimiento = String.valueOf(fechanac.getDia()) + "/" + String.valueOf(fechanac.getMes()) + "/" + String.valueOf(fechanac.getAno());
                                            %>
                                            <tr class="default">
                                                <td class="default" align="center" width="200" id="nickname"><%=nick%></td>
                                                <td class="default" align="center" width="200" id="nombre"><%=nombre%></td>
                                                <td class="default" align="center" width="200" id="apellido"><%=apellido%></td>
                                                <td class="default" align="center" width="200" id="correo"><%=correo%></td>
                                                <td class="default" align="center" width="200" id="nacimiento"><%=nacimiento%></td>
                                            </tr>
                                            <% }%>
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
    </body>
</html>




