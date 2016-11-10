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
    private Endpoint endpoint = null;

    //Constructor
    public Publicador() {
    }

    //Operaciones las cuales quiero publicar
    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:9128/publicador", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }

    @WebMethod
    public DtUsuario getDtUsuario(String nickname) {
        return mcli.getDtUsuario(nickname);
    }

    @WebMethod
    public boolean Registrar(String nickname, String nombre, String apellido, String password, String email, String imagen, String fecha) {
        return fab.getIControladorUsuario().Registrar(nickname, nombre, apellido, password, email, imagen, fecha);
    }

    @WebMethod
    public DtUsuario Autenticacion(String nickname, String password) {
        //public boolean Autenticacion(String nickname, String password) {
        return fab.getIControladorUsuario().Autenticacion(nickname, password);
    }

    @WebMethod
    public boolean existeProveedor(String nickname) {
        return fab.getIControladorUsuario().existeProveedor(nickname);
    }

    @WebMethod
    public boolean Comprobacion(String nickname, String password) {
        return fab.getIControladorUsuario().Comprobacion(nickname, password);
    }

    // Categorías.jsp
    @WebMethod
    public ArrayList<String> obtenerCategoriasHijas(String padre) {
        return mcat.obtenerCategoriasHijas(padre);
    }

    // ListarServicios.jsp
    @WebMethod
    public ArrayList<String> listarServiciosCategoria(String categoria) {
        return mser.listarServiciosCategoria(categoria);
    }

    // [x] ListarServicios.jsp, Promocion.jsp Reserva.jsp Buscar.java
    @WebMethod
    public String getNkProveedorServicio(String servicio) {
        return fab.getIControladorServicio().getNkProveedorServicio(servicio);
    }

    // [x] Buscar.java
    @WebMethod
    public String getNkProveedorPromocion(String promocion) {
        return fab.getIControladorServicio().getNkProveedorPromocion(promocion);
    }

    // Promocion.jsp
    @WebMethod
    public DtPromocion getDTPromocion(String nombre, String Proveedor) {
        return mser.getDTPromocion(nombre, Proveedor);
    }

    // Promocion.jsp
    @WebMethod
    public ArrayList<String> listarServiciosDePromociones(String nombre, String prov) {
        return fab.getIControladorServicio().listarServiciosDePromociones(nombre, prov);
    }

    // Promocion.jsp, Servicio.jsp
    @WebMethod
    public DtServicio getDtServicio(String nombre, String proveedor) {
        return fab.getIControladorServicio().getDtServicio(nombre, proveedor);
    }

    // Promocion.jsp, Proveedor.jsp, Servicio.jsp, Usuario.jsp
    @WebMethod
    public DtUsuario getDtProveedor(String nickname) {
        return mprov.getDtProveedor(nickname);
    }

    // Promociones.jsp
    @WebMethod
    public ArrayList<DtPromocion> listarPromociones() {
        return fab.getIControladorServicio().listarPromociones();
    }

    // Proveedor.jsp (ArrayList)
    @WebMethod
    public ArrayList<DtServicio> listarServiciosProveedor(DtUsuario user) {
        return mser.listarServiciosProveedor(user);
    }

    // Proveedor.jsp (ArrayList)
    @WebMethod
    public ArrayList<DtPromocion> listarPromocionesProveedor(String prov) {
        return fab.getIControladorUsuario().listarPromocionesProveedor(prov);
    }

    // Proveedor.jsp (ArrayList)
    @WebMethod
    public ArrayList<DtUsuario> listarProveedores() {
        return mprov.listarProveedores();
    }

    // Reserva.jsp
    @WebMethod
    public ArrayList<DtItemReserva> listarItems(Integer reserva) {
        return fab.getIControladorReserva().listarItems(reserva);
    }

    // Reserva.jsp
    @WebMethod
    public boolean existeServicio(String nombre) {
        return fab.getIControladorServicio().existeServicio(nombre);
    }

    // Servicio.jsp
    @WebMethod
    public String obtenerPadre(String hijo) {
        return mcat.obtenerPadre(hijo);
    }

    // Usuario.jsp
    @WebMethod
    public String imagenPerfilUsuario(String nickname) {
        return fab.getIControladorUsuario().imagenPerfilUsuario(nickname);
    }

    // Usuarios.jsp
    @WebMethod
    public ArrayList<DtUsuario> listarUsuariosSistema() {
        return fab.getIControladorUsuario().listarUsuariosSistema();
    }

}
