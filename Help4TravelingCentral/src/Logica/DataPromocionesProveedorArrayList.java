/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author yaman
 */
public class DataPromocionesProveedorArrayList {
    private ArrayList<DtPromocion> promocionesproveedor;
    


public  DataPromocionesProveedorArrayList(){    
}

public  DataPromocionesProveedorArrayList(ArrayList<DtPromocion>promocionesproveedor){    
    this.promocionesproveedor=promocionesproveedor;
}

public ArrayList<DtPromocion> getPromocionesProveedor(){
    return promocionesproveedor;
}

public void setPromocionesProveedor(ArrayList<DtPromocion> promocionesProveedor){
    this.promocionesproveedor = promocionesproveedor;
}
}