/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Map;
// Comentario para que me reconozca los cambios y pueda comitear...again
/**
 *
 * @author Leonardo
 */
public class Promocion extends Oferta {
    float descuento;
    private Map<String,Servicio> serviciosNom;

    public Promocion(String nombre, Proveedor prov, float descuento) {
        super(nombre,prov);
        this.descuento = descuento;
        this.serviciosNom = null;
    }

    public float getDescuento() {
        return descuento;
    }

    public Map<String, Servicio> getServiciosNom() {
        return serviciosNom;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public void setServiciosNom(Map<String, Servicio> serviciosNom) {
        this.serviciosNom = serviciosNom;
    }    
    
}
