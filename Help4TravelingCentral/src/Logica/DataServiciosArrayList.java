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
public class DataServiciosArrayList {
    private ArrayList<DtServicio> servicios;
    


public  DataServiciosArrayList(){    
}

public  DataServiciosArrayList(ArrayList<DtServicio> servicios){    
    this.servicios=servicios;
}

public ArrayList<DtServicio> getServicios(){
    return servicios;
}

public void setServicios(ArrayList<DtServicio> servicios){
    this.servicios = servicios;
}
}