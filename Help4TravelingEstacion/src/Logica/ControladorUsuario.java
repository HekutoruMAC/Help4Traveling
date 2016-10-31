/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




package Logica;
import java.util.ArrayList;
// Comentario para que me reconozca los cambios y pueda comitear...
/**
 *
 * @author Leonardo
 */
public class ControladorUsuario implements IControladorUsuario {
	
    private boolean esFechaValida(Date fecha) {
        return true;
    }
    
    private String comprobarCompletitudDatos(DtUsuario dtu) {
        String mensaje = "CAMPOSOK";
        if (dtu.getNombre().equals(""))
	    mensaje = "ERROR: El Nombre no puede ser vacío...";
        else if (dtu.getApellido().equals(""))
                 mensaje = "ERROR: El Apellido no puede ser vacío...";
	     else if (dtu.getNickname().equals(""))
                      mensaje = "ERROR: El Nickname no puede ser vacío...";
                  else if (dtu.getCorreo().equals(""))
			   mensaje = "ERROR: El Correo no puede ser vacío...";
                       else if (!esFechaValida(dtu.getNacimiento()))
                                mensaje = "ERROR: Fecha inválida...";
                            else if (dtu.getTipo().equals("Proveedor")) {
                                     if (dtu.getEmpresa().equals(""))
					  mensaje = "ERROR: La Empresa no puede ser vacía...";
                                     else if (dtu.getLink().equals(""))
                                               mensaje = "ERROR: El Link no puede ser vacío...";
                                 }
        return mensaje;
    }
	
    private boolean esUsuarioCliente(DtUsuario dtu) {
	if (dtu.getTipo().equals("Cliente"))
            return true;
	else return false;		
    }
    
    public String ingresarDatosUsuario(DtUsuario dtu) {
        boolean clienteok, existenk, existemail;
        //boolean datosok = false;
        String mensaje = "DATOSOK";
	String camposok = comprobarCompletitudDatos(dtu);
        System.out.println(camposok);
	if (camposok.equals("CAMPOSOK")) {
            clienteok = esUsuarioCliente(dtu);
            if (clienteok) {
		ManejadorCliente muc = ManejadorCliente.getInstance();
		existenk = muc.existeNickname(dtu.getNickname());
		if (!existenk) {
                    existemail = muc.existeCorreo(dtu.getCorreo());
                    if (existemail)
			mensaje = "ERROR: El Correo ingresado ya existe.";
		}
                else mensaje = "ERROR: El Nickname ingresado ya existe.";
            }
            else {
		ManejadorProveedor mup = ManejadorProveedor.getInstance();
		existenk = mup.existeNickname(dtu.getNickname());
		if (!existenk) {
                    existemail = mup.existeCorreo(dtu.getCorreo());
                    if (existemail)
                        mensaje = "ERROR: El Correo ingresado ya existe.";
		}
                else mensaje = "ERROR: El Nickname ingresado ya existe.";
            }
	}
        else mensaje = camposok;        
	return mensaje;	
    }
	
    public String altaUsuarioCliente(DtUsuario dtu) {
        Cliente cli = new Cliente(dtu.getNombre(), dtu.getApellido(), dtu.getNickname(), dtu.getPassword(), dtu.getCorreo(), dtu.getNacimiento(), dtu.getImagen());
	ManejadorCliente muc = ManejadorCliente.getInstance();
	//muc.agregarCliente(cli);
        return muc.persistirCliente(cli);
    }
	
    public String altaUsuarioProveedor(DtUsuario dtu) {
	Proveedor prov = new Proveedor(dtu.getNombre(), dtu.getApellido(), dtu.getNickname(), dtu.getPassword(), dtu.getCorreo(), dtu.getNacimiento(), dtu.getImagen(), dtu.getEmpresa(), dtu.getLink());
	ManejadorProveedor mup = ManejadorProveedor.getInstance();
	//mup.agregarProveedor(prov);	
        return mup.persistirProveedor(prov);
    }
	
    public void cancelarAltaUsuario() {
		
    }
		
    public String altaDeUsuario(DtUsuario dtu) {
	boolean clienteok;
        String mensaje = ingresarDatosUsuario(dtu);
        if (mensaje.equals("DATOSOK")) {
            clienteok = esUsuarioCliente(dtu);
            if (clienteok) {
		mensaje = altaUsuarioCliente(dtu);
                //mensaje = "Se dio de alta al Usuario Cliente.";
            }
            else {
                mensaje = altaUsuarioProveedor(dtu);
                //mensaje = "Se dio de alta al Usuario Proveedor.";
            }
	}    
	return mensaje;
    }
    
    public ArrayList<DtUsuario> listarClientes() {
        ManejadorCliente muc = ManejadorCliente.getInstance();
        return muc.listarClientes();
        
    }
        
    public ArrayList<DtReserva> listarReservasCliente(DtUsuario dtu){
        ManejadorReserva mr = ManejadorReserva.getInstance();
        return mr.listarReservasCliente(dtu);
    }
       
    public ArrayList<DtUsuario> listarProveedores() {
        ManejadorProveedor map = ManejadorProveedor.getInstance();
        return map.listarProveedores();        
    }
        
    public ArrayList<DtServicio> listarServicioProveedor(DtUsuario dtu){
        ManejadorServicio ms = ManejadorServicio.getInstance();
        return ms.listarServiciosProveedor(dtu);
    }
        
    public void verInfoDeProveedor(){
        
    }    
    
    /*public Cliente obtenerCliente(String nick){
        ManejadorCliente mc = ManejadorCliente.getInstance();
        return mc.obtenerCliente(nick);
    }*/
}

