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
public class DataServiciosCategoriasArrayList {
    private ArrayList<String> serviciosCategorias;
    


public  DataServiciosCategoriasArrayList(){    
}

public  DataServiciosCategoriasArrayList(ArrayList<String>serviciosCategorias){    
    this.serviciosCategorias=serviciosCategorias;
}

public ArrayList<String> getserviciosCategorias(){
    return serviciosCategorias;
}

public void setserviciosCategorias(ArrayList<String> serviciosCategorias){
    this.serviciosCategorias = serviciosCategorias;
}
}