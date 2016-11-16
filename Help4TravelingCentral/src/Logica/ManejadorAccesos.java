/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author yaman
 */
public class ManejadorAccesos {
    
    
    
    private static ManejadorAccesos instancia = null;

       public static ManejadorAccesos getInstance() {
        if (instancia == null) {
            instancia = new ManejadorAccesos();
        }
        return instancia;
    }
       
       
      public void  agregarAcceso(String IP,String URL,String Browser,String SO,String servicio) {
          
      
        //Conexion conexion = new Conexion();
        //Connection con = conexion.getConnection();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String mensaje = "Se registró el Acceso";
        
            String sqlau = "INSERT INTO help4traveling.accesos (IP,URL,Browser,SO,servicio) "
                    + "VALUES ('" +IP + "','" + URL + "','" + Browser + "','" + SO+"','"+servicio+"')";
            System.out.println(sqlau);
            try {
                st = con.createStatement();
                //System.out.println("antes de insertar");
                st.executeUpdate(sqlau);
               
                con.close();
                st.close();
                System.out.println("INSERTE :)");
            } catch (SQLException e) {
                mensaje = "ERROR: No se pudo insertar registro.";
                System.out.println("No pude INSERTAR :(");
                System.out.println(e);
            }
        
        
    }
      
    public void obtenerRegistros(){
        
    }
    
}
