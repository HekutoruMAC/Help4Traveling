/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
// Comentario para que me reconozca los cambios y pueda comitear...

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Leonardo
 */
public class ManejadorCategoria {

    //Clase que conserva la colección global de las Categorias del Sistema
    private Map<String, Categoria> categoriasNom;
    private static ManejadorCategoria instancia = null;

    private ManejadorCategoria() {
        categoriasNom = new HashMap<String, Categoria>();
    }

    public static ManejadorCategoria getInstance() {
        if (instancia == null) {
            instancia = new ManejadorCategoria();
        }
        return instancia;
    }

    public boolean agregarCategoria(Categoria cat) {
        //String nombre = cat.getNombre();
        //categoriasNom.put(nombre,cat);
        if (PersistirCategoria(cat) == "Se dio de alta la nueva categoria.") {
            return true;
        } else {
            return false;
        }
    }

    /*  public boolean existeCategoria(String nombre){
        return categoriasNom.containsKey(nombre);
    }*/
    public boolean existeCategoria(String nombreCat) {
        boolean existe = false;
        ResultSet rs;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql1 = "SELECT * FROM help4traveling.categorias WHERE nombre='" + nombreCat + "' OR padre ='" + nombreCat + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql1);
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            con.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("error :(");
        }
        if (existe) {
            System.out.println("Existe la Categoria: " + nombreCat);
        } else {
            System.out.println("NO Existe la Categoria: " + nombreCat);
        }
        return existe;
        //return clientesNK.containsKey(nickname);
    }

    public boolean existeCategoria(Categoria cat) {
        String nombre = cat.getNombre();
        return categoriasNom.containsKey(nombre);
    }

    public Categoria obtenerCategoria(String nombre) {
        return ((Categoria) categoriasNom.get(nombre));
    }

    public void eliminarCategoria(String nombre) {
        categoriasNom.remove(nombre);

    }

    public void sustituirCategoria(String nombre, Categoria cat) {
        categoriasNom.replace(nombre, cat);
    }

    public String PersistirCategoria(Categoria cat) {

        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String mensaje = "Se dio de alta la nueva categoria.";

        if (!(existeCategoria(cat.getNombre()))) {

            String sqlau = "INSERT INTO help4traveling.categorias "
                    + "(nombre,padre) "
                    + "VALUES ('" + cat.getNombre() + "','" + cat.getNomPadre() + "')";
            System.out.println(sqlau);

            try {
                st = con.createStatement();
                System.out.println("antes de insertar");
                st.executeUpdate(sqlau);

                con.close();
                st.close();
                System.out.println("YA INSERTE :)");
            } catch (SQLException e) {
                System.out.println("No pude INSERTAR :(");
            }
        } else {
            mensaje = "ERROR: El Nombre de categoria ingresado ya existe.";
        }
        return mensaje;
    }

    public String obtenerPadre(String hijo) {
        String padre = null;
        ResultSet rs;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql;

        sql = "SELECT padre FROM help4traveling.categorias WHERE nombre='" + hijo + "'";

        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                padre = rs.getString("padre");
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No pude obtener categorias :(");
        }
        return padre;
    }

    public List<String> obtenerCategoriasPadre() {
        List<String> listaCat = new LinkedList<String>();
        ResultSet rsCategorias;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql;

        sql = "SELECT * FROM help4traveling.categorias ";
        try {
            st = con.createStatement();
            rsCategorias = st.executeQuery(sql);
            while (rsCategorias.next()) {
                String padre = (rsCategorias.getString("padre"));
                if (!listaCat.contains(padre)) {
                    listaCat.add(padre);
                }

            }
            rsCategorias.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No pude obtener categorias :(");
        }
        return listaCat;
    }

    public List<DtCategoria> listarCategorias() {
        LinkedList<DtCategoria> listaCat = new LinkedList<DtCategoria>();
        ResultSet rs;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql;
        sql = "SELECT * FROM help4traveling.categorias";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                DtCategoria cat = new DtCategoria(rs.getString("nombre"), rs.getString("padre"));
                listaCat.addLast(cat);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No pude obtener categorias :(");
        }
        return listaCat;
    }

    public ArrayList<String> obtenerCategoriasHijas(String padre) {
        ArrayList<String> listaCat = new ArrayList<String>();
        ResultSet rsCategorias;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql;

        sql = "SELECT * FROM help4traveling.categorias WHERE padre = '" + padre + "'";
        try {
            st = con.createStatement();
            rsCategorias = st.executeQuery(sql);
            while (rsCategorias.next()) {
                listaCat.add(rsCategorias.getString("nombre"));
            }
            rsCategorias.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No pude obtener categorias :(");
        }
        return listaCat;
    }

    public List<String> getNombresCategorias() {
        List<String> listaCat = new LinkedList<String>();
        /*Iterator<Categoria> iter = this.categoriasNom.values().iterator();
	while (iter.hasNext()) {
            Categoria cat = iter.next();
            listaCat.add(cat.getNombre());
	}*/
        Conexion conexion;
        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        String sql = "SELECT * FROM help4traveling.categorias";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                listaCat.add(rs.getString("nombre"));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No pude LISTAR :(");
        }
        return listaCat;
    }
    
    public ArrayList<String> getNombresCategoriasServicio(String nomserv) {
        ArrayList<String> listaCat = new ArrayList<String>();
        try {
            Connection con = Conexion.getInstance().getConnection();
            String sql = "SELECT * FROM help4traveling.servicioscategorias WHERE servicio = '" + nomserv + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                listaCat.add(rs.getString("categoria"));
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No pude LISTAR :(");
        }
        return listaCat;
    }
    
}
