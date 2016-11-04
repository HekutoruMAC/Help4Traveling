
//Espera a que el documento esta totalmente cargado
$(document).ready(function () {
    $("#header").load("header.html", function () {
        $("#footer").load("footer.html", function () {
            setTimeout(function () {

                //Logo
                $("#idH4T").click(function () {
                    $(location).attr('href', 'index.jsp');
                });

                //Principal / home
                $("#idHome").click(function () {
                    $(location).attr('href', 'index.jsp');
                });

                //Listar usuarios
                $("#idListaUsuarios").click(function () {
                    $(location).attr('href', 'Usuarios.jsp');
                });

                //Listar proveedores
                $("#idProveedores").click(function () {
                    $(location).attr('href', 'Proveedores.jsp');
                });

                //Ofertas Opciones
                $("#idServicios").click(function () {
                    $(location).attr('href', 'Servicios.jsp');
                });

                $("#idPromociones").click(function () {
                    $(location).attr('href', 'Promociones.jsp');
                });

                //Usuarios Opciones
                $("#idPerfil").click(function () {
                    $(location).attr('href', 'Usuario.jsp');
                });

                $("#idReservas").click(function () {
                    $(location).attr('href', '#');
                });

                $("#idIniciar").click(function () {
                    $(location).attr('href', 'InicioSesion.jsp');
                });

                $("#idRegistrar").click(function () {
                    $(location).attr('href', 'InicioSesion.jsp');
                });

                $("#idReservas").hide();

                //Carrito
                $("#idCarrito").click(function () {
                    $(location).attr('href', 'Carrito.jsp');
                });

                //Cerrar Sesion
                $("#idSalir").click(function () {
                    $(location).attr('href', 'CierreSesion.jsp');
                });


            }, 100);
        });
    });
});


