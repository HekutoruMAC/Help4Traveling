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
public class DataServiciosProveedorArrayList {
    private ArrayList<DtServicio> serviciosProveedor;

    public DataServiciosProveedorArrayList() {
    }
    
    public DataServiciosProveedorArrayList(ArrayList<DtServicio> serviciosProveedor) {
        this.serviciosProveedor = serviciosProveedor;
    }

    public ArrayList<DtServicio> getServiciosProveedor() {
        return serviciosProveedor;
    }  
    
    
}
