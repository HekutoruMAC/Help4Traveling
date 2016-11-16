/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author yaman
 */
public class CantServicios {
    private int cantidad;
    private String servicio;

    public CantServicios() {
    }

    public CantServicios(int cantidad, String servicio) {
        this.cantidad = cantidad;
        this.servicio = servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getServicio() {
        return servicio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
    
    
    
    
    
}
