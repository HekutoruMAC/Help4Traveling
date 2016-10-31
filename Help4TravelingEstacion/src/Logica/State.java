/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Hekutoru
 */
public enum State {
    REGISTRADA("REGISTRADA"),
    PAGADA("PAGADA"),
    FACTURADA("FACTURADA"),
    CANCELADA("CANCELADA");
    private String nombre;

    State(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
