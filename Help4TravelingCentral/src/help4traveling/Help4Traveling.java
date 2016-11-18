/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package help4traveling;

import Logica.Conector;
import ServidorPublicador.Publicador;
import Vista.Principal;
import java.io.IOException;

public class Help4Traveling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("********* HELP 4 TRAVELING *********");
        System.out.println("Bienvenido a la Estación de Trabajo!");
        System.out.println("************************************");

        Principal p = new Principal();
        p.setLocationRelativeTo(null);
        p.setVisible(true);

        Conector con = Conector.getInstance();
        try {
            con.cargarConfig();
        } catch (IOException ex) {
        }

        Publicador pub = new Publicador();
        pub.publicar();

    }

}
