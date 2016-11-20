<script>
    $(document).ready(function () {

        var arrayValoresAutocompletar = [
            "Air-France-FC",
            "Casa para p4 BsAs",
            "Coche-Miami",
            "Euro-Car-1",
            "Euro-Car-2",
            "Euro-Car-3",
            "Euro-Vuelo-FC",
            "Euro-Vuelo-LC",
            "Euro-Vuelo-S",
            "Floripa G.House",
            "Luxury south beach corner apartment",
            "TAM-FC",
            "Euro-Cars-E-F",
            "Euro-Cars-E-S",
            "Euro-Cars-S-F",
            "Euro-Vuelos-LC-FC",
            "Euro-Vuelos-S-LC",
            "Miami-Viaje",
            "Sudamerica-Casas"
        ];

        $("#buscar").autocomplete({
            source: arrayValoresAutocompletar
        });
        $("#buscar_bn").click(function () {
            $(location).attr('index.jsp');
        });
    });

</script>

<div class="navbar navbar-default navbar-fixed-top" id="header">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <img src="img/logo.png" width="48" height="48"/>
            <a class="navbar-brand logo" href="#" id="idH4T"> Help4Traveling<br></a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-ex-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#" id="idHome"><span class="glyphicon glyphicon-home"></span> Principal<br></a>
                </li>

                <%  String nicksesion = null;
                    if (session.getAttribute("nickname") != null) {
                        nicksesion = (String) session.getAttribute("nickname");
                    }
                    Boolean sesioninvitado = true;
                    Boolean sesionproveedor = false;
                    
                    
                    /*if ((nicksesion != null)) {
                        sesioninvitado = false;
                        sesionproveedor = (Boolean) session.getAttribute("esProv");*/
                        if(!(session.getAttribute("error")==null) && (session.getAttribute("error")=="true")){
                            session.invalidate();
                        }else if ((nicksesion != null)) {
                                    sesioninvitado = false;
                                    sesionproveedor = (Boolean) session.getAttribute("esProv");
                            /*sesioninvitado = true;
                            sesionproveedor = false;*/
                        }
                    
                %>

                <!--NAVEGACION de INVITADOS------------------------------------>

                <% if (sesioninvitado) { %>

                <!--Listados de Servicios, Promociones y Proveedores/-->


                <li id="idServicios">
                    <a href="#"><span class="glyphicon glyphicon-road"></span> Servicios</a>
                </li>
                <li id="idPromociones">
                    <a href="#"><span class="glyphicon glyphicon-gift"></span> Promociones</a>
                </li>
                <li id="idProveedores">
                    <a href="#"><span class="glyphicon glyphicon-star"></span> Proveedores</a>
                </li>

                <!--Perfil de Usuario y Control de Sesión/-->

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-user"></span> Usuario <i class="fa fa-caret-down"></i></a>
                    <ul class="dropdown-menu" role="menu">
                        <li id="idIniciar">
                            <a href="#"><span class="glyphicon glyphicon-ok"></span> Iniciar sesión</a>
                        </li>
                        <li id="idRegistrar">
                            <a href="#"><span class="glyphicon glyphicon-plus"></span> Registrar usuario</a>
                        </li>
                    </ul>
                </li>

                <% } else { %>

                <!-- NAVEGACION de CLIENTES ----------------------------------->

                <% if (!sesionproveedor) { %>

                <!--Listados de Usuarios y Proveedores
                <li class="dropdown">
                    <ul class="dropdown-menu" role="menu">
                        <li id="idListaUsuarios">
                            <a href="#"><span class="glyphicon glyphicon-heart"></span> Usuarios</a>
                        </li>
                        <li id="idProveedores">
                            <a href="#"><span class="glyphicon glyphicon-star"></span> Proveedores</a>
                        </li>
                    </ul>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-th-list"></span> Usuarios <i class="fa fa-caret-down"></i></a>
                </li-->

                <!--Listados de Servicios, Promociones y Proveedores/-->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-plane"></span> Ofertas <i class="fa fa-caret-down"></i></a>
                    <ul class="dropdown-menu" role="menu">
                        <li id="idServicios">
                            <a href="#"><span class="glyphicon glyphicon-road"></span> Servicios</a>
                        </li>
                        <li id="idPromociones">
                            <a href="#"><span class="glyphicon glyphicon-gift"></span> Promociones</a>
                        </li>
                        <li id="idProveedores">
                            <a href="#"><span class="glyphicon glyphicon-star"></span> Proveedores</a>
                        </li>
                    </ul>
                </li>

                <!--Perfil de Usuario y Control de Sesión/-->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-user"></span> Usuario <i class="fa fa-caret-down"></i></a>
                    <ul class="dropdown-menu" role="menu">
                        <li id="idPerfil">
                            <a href="#"><span class="glyphicon glyphicon-camera"></span> Perfil</a>
                        </li>
                        <li id="idReservas">
                            <a href="#"><span class="glyphicon glyphicon-bookmark"></span> Reservas</a>
                        </li>
                        <li id="idSalir">
                            <a href="#"><span class="glyphicon glyphicon-remove"></span> Salir</a>
                    </ul>
                </li>

                <!--Carrito-->
                <li id="idCarrito">
                    <a href="#" class="default"><span class="glyphicon glyphicon-shopping-cart"></span> Carrito</a>
                </li>
                <% }
                    } %>


                <!-- NAVEGACION de PROVEEDORES /------------------------------->

                <% if (sesionproveedor) { %>

                <li>
                    <a href="#" id="idTabServicios"><span class="glyphicon glyphicon-road"></span> Servicios</a>
                </li>
                <li>
                    <a href="#" id="idTabPromociones"><span class="glyphicon glyphicon-gift"></span> Promociones</a>
                </li>
                <li>
                    <a href="#" id="idTabReservas"><span class="glyphicon glyphicon-bookmark"></span> Reservas</a>
                </li>
                <li id="idSalir">
                    <a href="#"><span class="glyphicon glyphicon-remove"></span> Salir</a>
                </li>
                <% }%>
            </ul>

            <form class="navbar-form navbar-left" role="search" action="Buscar" method="post">
                <div class="form-group" >
                    <input type="search" id="buscar" name="buscar" class="form-control" placeholder="Servicios y Promociones">
                    <button type="submit" class="btn btn-default" id="buscar_bn" name="buscar_bn" ><span class="glyphicon glyphicon-search"></span> Buscar</button>
                </div>
            </form>
        </div>
    </div>
</div>