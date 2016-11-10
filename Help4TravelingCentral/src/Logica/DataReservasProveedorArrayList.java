/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author Leonardo
 */
public class DataReservasProveedorArrayList {
    private ArrayList<DtReserva> reservasproveedor;

    public DataReservasProveedorArrayList() {
    }

    public DataReservasProveedorArrayList(ArrayList<DtReserva> reservasproveedor) {
        this.reservasproveedor = reservasproveedor;
    }

    public ArrayList<DtReserva> getReservasProveedor() {
        return reservasproveedor;
    }    
    
    public void setReservasProveedor(ArrayList<DtReserva>reservasproveedor) {
         this.reservasproveedor = reservasproveedor;
    }    
    
}
