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
public class Fabrica {

    private static Fabrica instancia = null;

    private Fabrica(){  
    }

    public static Fabrica getInstance(){
        if (instancia == null){
            instancia = new Fabrica();
        }
        return instancia;
    }
    
    public IControladorUsuario getIControladorUsuario() {
        IControladorUsuario ICU = new ControladorUsuario();
        return ICU;
    }
    
    public IControladorReserva getIControladorReserva() {
        IControladorReserva ICR = new ControladorReserva();
        return ICR;
    }
    
    public IControladorServicio getIControladorServicio() {
        IControladorServicio ICU = new ControladorServicio();
        return ICU;
    }

}
