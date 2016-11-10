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
public class DataReservasUsuarioArrayList {
    private ArrayList<DtReserva> reservasusuario;

    public DataReservasUsuarioArrayList() {
    }

    public DataReservasUsuarioArrayList(ArrayList<DtReserva> reservasusuario) {
        this.reservasusuario = reservasusuario;
    }

    public ArrayList<DtReserva> getReservasUsuario() {
        return reservasusuario;
    }    
    
    public void setReservasUsuario(ArrayList<DtReserva>reservasproveedor) {
         this.reservasusuario = reservasproveedor;
    }    
    
}
