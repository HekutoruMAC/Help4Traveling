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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Leonardo
 */
public class ManejadorCiudad {

    //Clase que conserva la colección global de las Ciudads del Sistema
    private Map<String, Ciudad> ciudadesNom;

    private static ManejadorCiudad instancia = null;

    private ManejadorCiudad() {
        ciudadesNom = new HashMap<String, Ciudad>();
    }

    public static ManejadorCiudad getInstance() {
        if (instancia == null) {
            instancia = new ManejadorCiudad();
        }
        return instancia;
    }

    public void agregarCiudad(Ciudad ciu) {
        String nombre = ciu.getNombre();
        ciudadesNom.put(nombre, ciu);
    }

    public List<String> listarPaises() {
        ResultSet rs;
        List<String> lista = new LinkedList<String>();
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql = "SELECT * FROM help4traveling.paises ORDER BY nombre";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                lista.add(nombre);
            }
            rs.close();
            st.close();
            con.close();
            System.out.println("Paises cargados :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar Paises :(");
        }
        return lista;
    }

    public List<String> listarCiudadesPorPais(String nombre) {
        ResultSet rs;
        List<String> lista = new LinkedList<String>();
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql = "SELECT * FROM help4traveling.ciudades WHERE pais='" + nombre + "' ORDER BY nombre";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String ciudad = rs.getString("nombre");
                lista.add(ciudad);
            }
            rs.close();
            st.close();
            con.close();
            System.out.println("Paises cargados :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar Paises :(");
        }
        return lista;
    }

    public boolean existeCiudad(String nombre/*, String pais*/) {
        boolean existe;
        ResultSet rs;
        Ciudad ciu = null;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        //String sql = "SELECT * FROM mydb.ciudades, mydb.paises WHERE Pais='" + pais + "' AND Nombre='" + nombre + "')"; 
        String sql = "SELECT * FROM help4traveling.ciudades WHERE nombre='" + nombre + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Pais p = new Pais(rs.getString("pais"), null);
            ciu = new Ciudad(rs.getString("nombre"), p);
            rs.close();
            con.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("No obtuve ciudad :(");
        }
        if (ciu == null) {
            existe = false;
        } else {
            existe = true;
        }
        return existe;
        //return ciudadesNom.containsKey(nombre);        
    }

    public Ciudad obtenerCiudad(String nombre/*, String pais*/) {
        ResultSet rs;
        Ciudad ciu = null;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql = "SELECT * FROM help4traveling.ciudades WHERE nombre='" + nombre + "'";
        System.out.println(sql);
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            String pais = rs.getString("pais");
            String ciudad = rs.getString("nombre");
            Pais p = new Pais(pais, null);
            ciu = new Ciudad(ciudad, p);
            rs.close();
            con.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("No obtuve ciudad :(");
            System.err.println(e.getMessage());
        }
        return ciu;
        //return ((Ciudad) ciudadesNom.get(nombre));
    }

}
