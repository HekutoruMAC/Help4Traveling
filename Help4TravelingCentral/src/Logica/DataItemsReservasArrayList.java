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
public class DataItemsReservasArrayList {
    private ArrayList<DtItemReserva> items;
    


public  DataItemsReservasArrayList(){    
}

public  DataItemsReservasArrayList(ArrayList<DtItemReserva> items){    
    this.items = items;
}

public ArrayList<DtItemReserva> getItems(){
    return items;
}

public void setItems(ArrayList<DtItemReserva> items){
    this.items = items;
}
}