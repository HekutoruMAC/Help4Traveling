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

public class DataPromocionesArrayList {
    private ArrayList<DtPromocion> promociones;
    


public  DataPromocionesArrayList(){    
}

public  DataPromocionesArrayList(ArrayList<DtPromocion>promociones){    
    this.promociones=promociones;
}

public ArrayList<DtPromocion> getPromociones(){
    return promociones;
}

public void setPromociones(ArrayList<DtPromocion> promociones){
    this.promociones = promociones;
}
}