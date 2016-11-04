/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
// Comentario para que me reconozca los cambios y pueda comitear...again
/**
 *
 * @author Leonardo
 */
public class Proveedor extends Usuario {
    private String empresa;
    private String link;
    private Map<String, Servicio> serviciosNom;
    private Map<String, Promocion> promocionesNom;
    
    public Proveedor(String nombre){
        super(nombre);
    }

    //Constructor
    public Proveedor(String nombre, String apellido, String nickname, String password, String correo, Date nacimiento, String imagen, String empresa, String link) {
        super(nombre, apellido, nickname, password, correo, nacimiento, imagen);
        this.empresa = empresa;
        this.link = link;
        this.serviciosNom = new HashMap<String,Servicio>();
        this.promocionesNom = new HashMap<String,Promocion>();
    }

    //Getters
    public String getEmpresa() {
        return empresa;
    }

    public String getLink() {
        return link;
    }

    public Map<String, Servicio> getServiciosNom() {
        return serviciosNom;
    }

    public Map<String, Promocion> getPromocionesNom() {
        return promocionesNom;
    }
    
    //Setters
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setServiciosNom(Map<String, Servicio> serviciosNom) {
        this.serviciosNom = serviciosNom;
    }

    public void setPromocionesNom(Map<String, Promocion> promocionesNom) {
        this.promocionesNom = promocionesNom;
    }
    
    // Operaciones para manejar coleccion de Servicios del Proveedor
    public void agregarServicio(Servicio serv){
        String nombre = serv.getNombre();
        serviciosNom.put(nombre,serv);
    }
    
    public boolean existeServicio(Servicio serv){
        String nombre = serv.getNombre();
        return serviciosNom.containsKey(nombre);        
    }
    
    public Servicio obtenerServicio(String nombre){
        return ((Servicio) serviciosNom.get(nombre));
    }
    
    // Operaciones para manejar coleccion de Promociones del Proveedor
    public void agregarPromocion(Promocion prom){
        String nombre = prom.getNombre();
        promocionesNom.put(nombre,prom);
    }
    
    public boolean existePromocion(Promocion prom){
        String nombre = prom.getNombre();
        return promocionesNom.containsKey(nombre);        
    }
    
    public Promocion obtenerPromocion(String nombre){
        return ((Promocion) promocionesNom.get(nombre));
    }
    
    public ArrayList<DtServicio> listarServicios(){        
        ArrayList<DtServicio> listaservicios = new ArrayList<DtServicio>();
         Iterator<Servicio> iter = this.serviciosNom.values().iterator();
        while (iter.hasNext()) {
            Servicio serv = iter.next();
            DtServicio dtServ = serv.getDtServicio(); 
            listaservicios.add(dtServ);
        }
        return listaservicios;
    }
     public DtProveedor getDtProveedor(){
        DtProveedor dtp = new DtProveedor(this.getNombre(),this.getApellido(),this.getNickname(),this.getCorreo(),this.getNacimiento(),this.getImagen(),this.empresa,this.link);
        return dtp;
     }
        /*
        ArrayList<Servicio> lista = new ArrayList<Servicio>(serviciosNom.values());
        return lista;*/
    
       
}
