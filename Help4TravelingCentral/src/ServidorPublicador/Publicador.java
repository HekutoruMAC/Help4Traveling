/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorPublicador;

import Logica.*;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

/**
 *
 * @author Leonardo
 */
@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {

    private Fabrica fab = Fabrica.getInstance();
    private ManejadorCliente mcli = ManejadorCliente.getInstance();
    private ManejadorCategoria mcat = ManejadorCategoria.getInstance();
    private ManejadorProveedor mprov = ManejadorProveedor.getInstance();
    private ManejadorServicio mser = ManejadorServicio.getInstance();
    private Conector conector = Conector.getInstance();
    private Endpoint endpoint = null;

    //Constructor
    private Publicador() {
    }

    public static Publicador getInstance() {
        return PublicadorHolder.INSTANCE;
    }

    private static class PublicadorHolder {

        private static final Publicador INSTANCE = new Publicador();
    }

    //Operaciones las cuales quiero publicar
    @WebMethod(exclude = true)
    public void publicar() {
        String url = conector.getService();
        endpoint = Endpoint.publish(url, this);
    }

    @WebMethod(exclude = true)
    public void despublicar() {
        endpoint.stop();
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public DtUsuario getDtUsuario(String nickname) {
        return mcli.getDtUsuario(nickname);
    }

    // Servicios para Servlets =================================================
    // [OK] Buscar.java
    @WebMethod
    public String getNkProveedorPromocion(String promocion) {
        return fab.getIControladorServicio().getNkProveedorPromocion(promocion);
    }

    // [OK] CancelarReserva.java
    @WebMethod
    public void actualizarEstadoDeReserva(Integer reserva, String estado) {
        fab.getIControladorReserva().actualizarEstadoDeReserva(reserva, estado);
    }

    // [OK] Comprobacion.java
    @WebMethod
    public boolean Comprobacion(String nickname, String password) {
        return fab.getIControladorUsuario().Comprobacion(nickname, password);
    }

    // [OK] Comprobacion.java, Validacion.java
    @WebMethod
    public boolean existeProveedor(String nickname) {
        return fab.getIControladorUsuario().existeProveedor(nickname);
    }

    // [  ] eliminarCarrito.java
    @WebMethod
    public void altaReservaWeb(Reserva nueva) {
        fab.getIControladorReserva().altaReservaWeb(nueva);
    }

    @WebMethod
    public boolean existePromocion(String nombre) {
        return mser.existePromo(nombre);
    }

    // [OK] Registrar.java
    @WebMethod
    public boolean Registrar(String nickname, String nombre, String apellido, String password, String email, String imagen, String fecha) {
        return fab.getIControladorUsuario().Registrar(nickname, nombre, apellido, password, email, imagen, fecha);
    }

    // [OK] Validacion.java
    @WebMethod
    public DtUsuario Autenticacion(String nickname, String password) {
        //public boolean Autenticacion(String nickname, String password) {
        return fab.getIControladorUsuario().Autenticacion(nickname, password);
    }

    // Servicios para JSP ======================================================
    // [OK] Categorías.jsp
    @WebMethod
    public DataNombresCategoriasArrayList obtenerCategoriasHijas(String padre) {
        ArrayList<String> categorias = mcat.obtenerCategoriasHijas(padre);
        return new DataNombresCategoriasArrayList(categorias);
    }

    // [  ] ListarServicios.jsp
    /*
    @WebMethod
    public ArrayList<String> listarServiciosCategoria(String categoria) {
        return mser.listarServiciosCategoria(categoria);
    }
     */
    @WebMethod
    public DataServiciosCategoriasArrayList listarServiciosCategorias(String categoria) {
        ArrayList<String> lista = mser.listarServiciosCategoria(categoria);
        return new DataServiciosCategoriasArrayList(lista);
    }

    @WebMethod
    public DataServiciosArrayList listarServicios() {
        ArrayList<DtServicio> lista = mser.listarServicios();
        return new DataServiciosArrayList(lista);

    }

    // [OK] ListarServicios.jsp, Promocion.jsp Reserva.jsp Buscar.java
    @WebMethod
    public String getNkProveedorServicio(String servicio) {
        return fab.getIControladorServicio().getNkProveedorServicio(servicio);
    }

    // [OK] Promocion.jsp
    @WebMethod
    public DtPromocion getDTPromocion(String nombre, String Proveedor) {
        return mser.getDTPromocion(nombre, Proveedor);
    }

    // [OK] Promocion.jsp
    @WebMethod
    public DataServiciosPromocionesArrayList listarServiciosDePromociones(String nombre, String prov) {
        ArrayList<String> serviciospromociones = fab.getIControladorServicio().listarServiciosDePromociones(nombre, prov);
        return new DataServiciosPromocionesArrayList(serviciospromociones);
    }

    // [OK] Promocion.jsp, Servicio.jsp
    @WebMethod
    public DtServicio getDtServicio(String nombre, String proveedor) {
        return fab.getIControladorServicio().getDtServicio(nombre, proveedor);
    }

    // [OK] Promocion.jsp, Proveedor.jsp, Servicio.jsp, Usuario.jsp
    @WebMethod
    public DtUsuario getDtProveedor(String nickname) {
        return mprov.getDtProveedor(nickname);
    }

    // [  ] Promociones.jsp
    /*
    @WebMethod
    public ArrayList<DtPromocion> listarPromociones() {
        return fab.getIControladorServicio().listarPromociones();
    }
     */
    @WebMethod
    public DataPromocionesArrayList listarPromociones() {
        ArrayList<DtPromocion> lista = fab.getIControladorServicio().listarPromociones();
        return new DataPromocionesArrayList(lista);
    }

    // [OK] Proveedor.jsp
    @WebMethod
    public DataServiciosProveedorArrayList listarServiciosProveedor(DtUsuario user) {
        ArrayList<DtServicio> serviciosproveedor = mser.listarServiciosProveedor(user);
        return new DataServiciosProveedorArrayList(serviciosproveedor);
    }

    // [  ] Proveedor.jsp
    /*
    @WebMethod
    public ArrayList<DtPromocion> listarPromocionesProveedor(String prov) {
        return fab.getIControladorUsuario().listarPromocionesProveedor(prov);
    }
     */
    @WebMethod
    public DataPromocionesProveedorArrayList listarPromocionesProveedor(String prov) {
        ArrayList<DtPromocion> listapromociones = mprov.listarPromocionesProveedor(prov);
        return new DataPromocionesProveedorArrayList(listapromociones);
    }

    // [  ] Proveedor.jsp
    /*
    @WebMethod
    public ArrayList<DtUsuario> listarProveedores() {
        return mprov.listarProveedores();
    }
     */
    @WebMethod
    public DataProveedoresArrayList listarProveedores() {
        ArrayList<DtUsuario> listaproveedores = mprov.listarProveedores();
        return new DataProveedoresArrayList(listaproveedores);
    }

    // [  ] Reserva.jsp
    /*
    @WebMethod
    public ArrayList<DtItemReserva> listarItems(Integer reserva) {
        return fab.getIControladorReserva().listarItems(reserva);
    }
     */
    @WebMethod
    public DataItemsReservasArrayList listarItems(Integer reserva) {
        ArrayList<DtItemReserva> items = fab.getIControladorReserva().listarItems(reserva);
        return new DataItemsReservasArrayList(items);
    }

    @WebMethod
    public void agregarItemReserva(Reserva nueva, Proveedor prov, int cantidad, String fi, String ff, Servicio oferta) {
        Date fini = new Date(fi);
        Date ffin = new Date(ff);
        fab.getIControladorReserva().agregarItemReserva(nueva, oferta, prov, cantidad, fini, ffin);
    }

    // [OK] Buscar.java, Reserva.jsp
    @WebMethod
    public boolean existeServicio(String nombre) {
        return fab.getIControladorServicio().existeServicio(nombre);
    }

    // [OK] Servicio.jsp
    @WebMethod
    public String obtenerPadre(String hijo) {
        return mcat.obtenerPadre(hijo);
    }

    @WebMethod
    public DataNombresCategoriasArrayList getNombresCategoriasServicio(String nomserv) {
        ArrayList<String> categoriasservicio = mcat.getNombresCategoriasServicio(nomserv);
        return new DataNombresCategoriasArrayList(categoriasservicio);
    }

    // [OK] Proveedor.jsp, Usuario.jsp
    @WebMethod
    public String imagenPerfilUsuario(String nickname) {
        return fab.getIControladorUsuario().imagenPerfilUsuario(nickname);
    }

    // [OK] Usuarios.jsp
    @WebMethod
    public DataUsuariosSistemaArrayList listarUsuariosSistema() {
        ArrayList<DtUsuario> usuariossistema = fab.getIControladorUsuario().listarUsuariosSistema();
        return new DataUsuariosSistemaArrayList(usuariossistema);
    }

    @WebMethod
    public DataReservasProveedorArrayList listarReservasProveedor(String nick) {
        ArrayList<DtReserva> reservas = fab.getIControladorReserva().listarReservasProveedor(nick);
        return new DataReservasProveedorArrayList(reservas);
    }

    @WebMethod
    public DataReservasUsuarioArrayList listarReservasUsuario(String nick) {
        ArrayList<DtReserva> reservas = fab.getIControladorReserva().listarReservasUsuario(nick);
        return new DataReservasUsuarioArrayList(reservas);
    }

    @WebMethod
    public void FacturarReserva(int reserva, String proveedorServicio) {
        fab.getIControladorReserva().FacturarReserva(reserva, proveedorServicio);
    }

    @WebMethod
    public void FacturarItemReserva(int reserva, String servicio, String proveedorServicio, String promocion) {
        fab.getIControladorReserva().FacturarItemReserva(reserva, servicio, proveedorServicio, promocion);
    }

    @WebMethod
    public int EstadoParcialReserva(int reserva, String proveedor) {
        return fab.getIControladorReserva().EstadoParcialReserva(reserva, proveedor);
    }

    @WebMethod
    public boolean ItemsFacturados(int reserva) {
        return fab.getIControladorReserva().ItemsFacturados(reserva);
    }

    @WebMethod
    public void agregarRegistro(String IP, String URL, String Browser, String SO, String servicio) {
        ManejadorAccesos macc = ManejadorAccesos.getInstance();
        macc.agregarAcceso(IP, URL, Browser, SO, servicio);

    }

    @WebMethod
    public String obtenerPrimeraImagenPromocion(String promocion, String proveedor) {
        return mser.obtenerPrimeraImagenPromocion(promocion, proveedor);
    }
}
