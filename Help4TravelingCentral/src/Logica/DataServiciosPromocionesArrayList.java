/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Leonardo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DataServiciosPromocionesArrayList {
    private ArrayList<String> serviciosPromociones;
    
    public DataServiciosPromocionesArrayList() {
        this.serviciosPromociones = null;
    }    

    public DataServiciosPromocionesArrayList(ArrayList<String> serviciosPromociones) {
        this.serviciosPromociones = serviciosPromociones;
    }

    public ArrayList<String> getServiciosPromociones() {
        return serviciosPromociones;
    }    
    
}
