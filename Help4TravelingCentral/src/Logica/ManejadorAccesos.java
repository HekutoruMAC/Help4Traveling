/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
      
    public ArrayList<CantServicios> obtenerCantServicio(){
        String sql = "SELECT COUNT(*) as cantidad,servicio FROM help4traveling.accesos WHERE servicio!='null' GROUP BY servicio ORDER BY COUNT(*) DESC LIMIT 10";
        
        ResultSet rs;

        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        System.out.println(sql);
        ArrayList<CantServicios> servicios = new ArrayList<CantServicios>();        
        try {
                st = con.createStatement();
                //System.out.println("antes de insertar");
                rs= st.executeQuery(sql);
                while(rs.next()){
                    CantServicios servicio = new CantServicios(Integer.parseInt(rs.getString("cantidad")),rs.getString("servicio"));
                    servicios.add(servicio);
                }
               
                con.close();
                st.close();
                System.out.println("OBTUVE SERVICIOS :)");
            } catch (SQLException e) {
                
                System.out.println("No pude OBTENER SERVICIOS :(");
                System.out.println(e);
            }     
        return servicios;
        
    }
    
    
    
    
    public ArrayList<Acceso> obtenerRegistros(){
        /*
        String sql1 = "SELECT COUNT(*) as cantidad,servicio";
        String sql2 = "FROM accesos WHERE servicio!=";
        String sql3 = "";
        String sql4 = "GROUP BY servicio ORDER BY COUNT(*) DESC";
        String sql = sql1+sql2+sql3+sql4;
        */
        
        String sql = "SELECT * FROM help4traveling.accesos";
        ResultSet rs;
        ArrayList<Acceso> registros = new ArrayList<>();

        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        System.out.println(sql);
        try {
                st = con.createStatement();
                //System.out.println("antes de insertar");
                rs= st.executeQuery(sql);
                while(rs.next()){
                    String num = rs.getString("#");
                    String IP = rs.getString("IP");
                    String URL = rs.getString("URL");
                    String Browser = rs.getString("Browser");
                    String SO = rs.getString("SO");
                    
                    Acceso acc = new Acceso(Integer.parseInt(num),IP,URL,Browser,SO);
                    
                    registros.add(acc);
                }
               
                con.close();
                st.close();
                System.out.println("OBTUVE REGISTROS :)");
            } catch (SQLException e) {
                
                System.out.println("No pude OBTENER REGISTROS :(");
                System.out.println(e);
            }     
        return registros;
        
    }
    
}
