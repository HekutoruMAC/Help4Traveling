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
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Leonardo
 */
public class ManejadorCliente {

    //Clase que conserva la colección global de los Usuarios Clientes del Sistema
    private Map<String, Cliente> clientesNK;
    private static ManejadorCliente instancia = null;
    private Conexion conexion;
    private String sql;

    private ManejadorCliente() {
        clientesNK = new HashMap<String, Cliente>();
    }

    public static ManejadorCliente getInstance() {
        if (instancia == null) {
            instancia = new ManejadorCliente();
        }
        return instancia;
    }

    public void agregarCliente(Cliente cli) {
        String nk = cli.getNickname();
        clientesNK.put(nk, cli);
    }

    public boolean existeNickname(String nickname) {
        boolean existe = false;
        ResultSet rs;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql1 = "SELECT * FROM help4traveling.usuarios WHERE nickname='" + nickname + "'";
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
            System.out.println("No exite cliente :(");
        }
        if (existe) {
            System.out.println("Existe nickname");
        } else {
            System.out.println("NO Existe nickname");
        }
        return existe;
        //return clientesNK.containsKey(nickname);
    }

    public boolean existeCorreo(String correo) {
        boolean existe = false;
        ResultSet rs;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql1 = "SELECT * FROM help4traveling.usuarios WHERE email='" + correo + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql1);
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No existe correo :(");
        }
        return existe;
    }

    /*public Cliente obtenerCliente(String nk){

        ResultSet rsCliente;
        Cliente cl = null;

        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;

        sql = "SELECT * FROM help4traveling.usuarios WHERE Nick='" + nk + "'";

        try{
            st = con.createStatement();
            rsCliente = st.executeQuery(sql);

            Date fecha = new Date();
            cl = new Cliente(rsCliente.getString("nombre"),rsCliente.getString("apellido"),rsCliente.getString("nickname"),rsCliente.getString("email"),fecha,"imagen");


            rsCliente.close();
            con.close();
            st.close();

        } catch(SQLException e){
            System.out.println("No pude cargar usuarios :(");
        }
        return cl;

    } */
    //return ((Cliente) clientesNK.get(nk));
    //Obtener clientes de la base de datos.
    public ArrayList<DtUsuario> listarClientes() {
        ResultSet rsClientes;

        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;

        sql = "SELECT * FROM help4traveling.usuarios WHERE nickname in (SELECT nickname FROM help4traveling.clientes)";

        try {
            st = con.createStatement();
            rsClientes = st.executeQuery(sql);

            while (rsClientes.next()) {
                String nombre = rsClientes.getString("nombre");
                String apellido = rsClientes.getString("apellido");
                String nickname = rsClientes.getString("nickname");
                String password = rsClientes.getString("password");
                String correo = rsClientes.getString("email");
                String fecha = rsClientes.getString("fechaNac");
                Date nacimiento = new Date(fecha);
                String imagen = rsClientes.getString("imagen");

                Cliente nuevo = new Cliente(nombre, apellido, nickname, password, correo, nacimiento, imagen);
                clientesNK.put(nickname, nuevo);
            }
            rsClientes.close();
            con.close();
            st.close();

            System.out.println("Usuarios cargados :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar usuarios :(");
            System.out.println(e);
        }

        ArrayList<DtUsuario> listaClientes = new ArrayList<>();
        Iterator<Cliente> iter = this.clientesNK.values().iterator();
        while (iter.hasNext()) {
            Cliente cli = iter.next();
            listaClientes.add(cli.getDtUsuario());
        }
        return listaClientes;
    }

    public String persistirCliente(Cliente cli) {
        //Conexion conexion = new Conexion();
        //Connection con = conexion.getConnection();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String mensaje = "Se dio de alta al Usuario Cliente.";
        String imagen = cli.getImagen();
        if (imagen != null) {
            imagen = "'" + imagen + "'";
            imagen = imagen.replace("\\", "\\\\");
        }
        if (!existeNickname(cli.getNickname())) {
            String fecha = String.valueOf(cli.getNacimiento().getAno()) + "-" + String.valueOf(cli.getNacimiento().getMes()) + "-" + String.valueOf(cli.getNacimiento().getDia());
            String sqlau = "INSERT INTO help4traveling.usuarios (nickname,nombre,apellido,password,email,imagen,fechaNac) "
                    + "VALUES ('" + cli.getNickname() + "','" + cli.getNombre() + "','" + cli.getApellido() + "','" + cli.getPassword() + "','" + cli.getCorreo() + "'," + imagen + ",'" + fecha + "')";
            System.out.println(sqlau);
            String sqlac = "INSERT INTO help4traveling.clientes (nickname) VALUES ('" + cli.getNickname() + "')";
            String sqlai = "INSERT INTO help4traveling.usuariosimagenes (usuario,imagen) VALUES ('" + cli.getNickname() + "','" + cli.getImagen() + "')";
            try {
                st = con.createStatement();
                //System.out.println("antes de insertar");
                st.executeUpdate(sqlau);
                st.executeUpdate(sqlac);
                if ((imagen != null) && (!imagen.equals(""))) {
                    //st.executeUpdate(sqlai);
                    con.prepareStatement(sqlai);
                }
                con.close();
                st.close();
                System.out.println("INSERTE :)");
            } catch (SQLException e) {
                mensaje = "ERROR: No se pudo insertar.";
                System.out.println("No pude INSERTAR :(");
                System.out.println(e);
            }
        } else {
            mensaje = "ERROR: El Nickname ingresado ya existe.";
        }
        return mensaje;
    }

}
