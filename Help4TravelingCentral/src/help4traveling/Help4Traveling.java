/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help4traveling;

import Logica.*;
import Vista.Principal;

public class Help4Traveling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("********* HELP 4 TRAVELING *********");
        System.out.println("Bienvenido a la Estación de Trabajo!");
        System.out.println("************************************");

        Fabrica fab = Fabrica.getInstance();
        Principal p = new Principal();
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        pruebas();
    }

    public static void pruebas() {
        /*
        //Reserva r = fab.getIControladorReserva().nuevaRserva("santiago", 0);
        //fab.getIControladorUsuario().listarClientes();
        //fab.getIControladorServicio().altaDeCategoria("ariel1", null);
        //fab.getIControladorServicio().altaDeCategoria("msrtin1", "ariel1");
        //fab.getIControladorServicio().listarCategorias();
        ///Date a = new Date(12,12,2016);
        //Date b = new Date(12,12,2017);
        //Servicio s = new Servicio();
        //System.out.println("C ......");
        //fab.getIControladorReserva().agregarItem(r,1,a,b,s);
        //fab.getIControladorReserva().verReserva();
        //fab.getIControladorReserva().altaReserva(r);
        //System.out.println("Antes de guardar a la base de datos ......");
        //fab.getIControladorUsuario().listarReservasCliente();
        //ManejadorReserva a;
        //a = new ManejadorReserva();
        //a.altaReserva();
        //private date a;
        //a = new ManejadorReserva();
         */

    }

}
