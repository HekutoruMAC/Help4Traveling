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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;

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

    // Servidor Central ========================================================
    public boolean Comprobacion(String nickname, String email) {
        try{
        Connection con = Conexion.getInstance().getConnection();
        Statement st = con.createStatement();
            ResultSet rs = null;
            String sql = "SELECT * FROM usuarios";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (nickname.equals(rs.getString("nickname")) || email.equals(rs.getString("email"))) {
                    rs.close();
                    st.close();
                    return false;
                }
            }
            rs.close();
            return true;
        }catch(SQLException e){
        return true;
        }
    }

    public boolean Registrar(String nickname, String nombre, String apellido, String password, String email, String imagen, String fecha)  {
        Statement st;
        if (imagen != null) {
            imagen = "'" + imagen + "'";
            //imagen = imagen.replace("\\", "\\\\");
        }
        //String fecha = (anio+ "-" + mes + "-" + dia);
        String fechaNac = fecha.replaceAll("/", "-");

        String sqlau = "INSERT INTO help4traveling.usuarios (nickname,nombre,apellido,password,email,imagen,fechaNac) VALUES ('" + nickname + "','" + nombre + "','" + apellido + "','" + password + "','" + email + "'," + imagen + ",'" + fechaNac + "')";
        System.out.println(sqlau);
        String sqlac = "INSERT INTO help4traveling.clientes (nickname) VALUES ('" + nickname + "')";
        String sqlai = "INSERT INTO help4traveling.usuariosimagenes (usuario,imagen) VALUES ('" + nickname + "'," + imagen + ")";
        try {
            Connection con = Conexion.getInstance().getConnection();
            st = con.createStatement();
            System.out.println("anted de insertar" + imagen);
            System.out.println("sentencia de insertar" + sqlai);
            st.executeUpdate(sqlau);
            st.executeUpdate(sqlac);
            //if ((imagen.equals(null)) && (!imagen.equals(""))) {
            st.executeUpdate(sqlai);
            //con.prepareStatement(sqlai);
            //}
            st.close();
            con.close();
            System.out.println("INSERTE :)");
            return true;
        } catch (SQLException e) {
            System.out.println("No pude INSERTAR :(");
            System.out.println(e);
            return false;
        }
    }

    public DtUsuario getDtUsuario(String nickname) {
        ResultSet rsUsu, rsImg;
        DtUsuario nuevo = null;
        Statement stUsu, stImg;
        try {
            Connection con = Conexion.getInstance().getConnection();
            stUsu = con.createStatement();
            String sql = "SELECT * FROM help4traveling.usuarios WHERE nickname='" + nickname + "'";
            rsUsu = stUsu.executeQuery(sql);
            if (rsUsu.next()) {
                String nombre = rsUsu.getString("nombre");
                String apellido = rsUsu.getString("apellido");
                String correo = rsUsu.getString("email");
                Date fecha = new Date(rsUsu.getString("fechaNac"));
                String imagen = null;
                stImg = con.createStatement();
                sql = "SELECT * FROM help4traveling.usuariosimagenes WHERE nickname='" + nickname + "'";
                rsImg = stImg.executeQuery(sql);
                if (rsImg.next()) {
                    imagen = rsImg.getString("imagen");
                }
                rsImg.close();
                stImg.close();
                nuevo = new DtUsuario(nombre, apellido, nickname, "password", correo, fecha, imagen, "tipo", "empresa", "link");
            }
            rsUsu.close();
            stUsu.close();
            con.close();
            System.out.println("Se obtuvo Usuario :)");
        } catch (SQLException e) {
            System.out.println("No obtuve Usuario :(");
            System.err.println(e.getMessage());
        }
        return nuevo;
    }

    public ArrayList<DtUsuario> listarUsuariosSistema() {
        ArrayList<DtUsuario> usuarios = null;
        ResultSet rsUsu, rsImg;
        Statement stUsu, stImg;
        try {
            Connection con = Conexion.getInstance().getConnection();
            stUsu = con.createStatement();
            String sql = "SELECT * FROM help4traveling.usuarios";
            rsUsu = stUsu.executeQuery(sql);
            usuarios = new ArrayList<DtUsuario>();
            while (rsUsu.next()) {
                String nickname = rsUsu.getString("nickname");
                String nombre = rsUsu.getString("nombre");
                String apellido = rsUsu.getString("apellido");
                String correo = rsUsu.getString("email");
                Date fecha = new Date(rsUsu.getString("fechaNac"));
                String imagen = null;
                stImg = con.createStatement();
                sql = "SELECT * FROM help4traveling.usuariosimagenes WHERE usuario='" + nickname + "'";
                rsImg = stImg.executeQuery(sql);
                if (rsImg.next()) {
                    imagen = rsImg.getString("imagen");
                }
                rsImg.close();
                stImg.close();
                DtUsuario nuevo = new DtUsuario(nombre, apellido, nickname, "password", correo, fecha, imagen, "tipo", "empresa", "link");
                usuarios.add(nuevo);
            }
            rsUsu.close();
            stUsu.close();
            con.close();
            System.out.println("Usuarios cargados :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar Usuarios :(");
        }
        return usuarios;
    }

    public String imagenPerfilUsuario(String nickname) {
        String imagen = null;
        ResultSet rs;
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql = "SELECT * FROM help4traveling.usuariosimagenes WHERE usuario='" + nickname + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                imagen = rs.getString("imagen");
            } else {
                imagen = "img/user.png";
            }
            rs.close();
            st.close();
            con.close();
            System.out.println("Imagenes cargadas :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar Imagenes :(");
        }
        return imagen;
    }

    /*public boolean Autenticacion(String nickname, String password) {        
        try {
            Connection con = Conexion.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = null;
            String Consulta = "SELECT * FROM usuarios";
            rs = st.executeQuery(Consulta);
            while (rs.next()) {
                if (nickname.equals(rs.getString("nickname")) && password.equals(rs.getString("password"))) {
                    //sesion.setAttribute("nombre", rs.getString("nombre"));
                    //sesion.setAttribute("apellido", rs.getString("apellido"));
                    //sesion.setAttribute("email", rs.getString("email"));
                    //sesion.setAttribute("fechaNac", rs.getString("fechaNac"));
                    rs.close();
                    st.close();
                    return true;
                }
                
            }
            rs.close();
            return false;
        }catch(SQLException e){
           return false;
        }       
    }*/
    
    public DtUsuario Autenticacion(String nickname, String password) {
        ResultSet rs = null;
        DtUsuario dtu = new DtUsuario();
        try {
            Connection con = Conexion.getInstance().getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM usuarios";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if (nickname.equals(rs.getString("nickname")) && password.equals(rs.getString("password"))) {                    
                    Date fecha = new Date(rs.getString("fechaNac"));
                    dtu = new DtUsuario(rs.getString("nombre"), rs.getString("apellido"), nickname, password, rs.getString("email"), fecha, null, null, null, null);
                }                
            }
            rs.close();
            st.close();
            con.close();           
        } catch (SQLException e) {
            System.out.println("No obtuve DtUsuario :(");
            System.err.println(e.getMessage());
        } 
        return dtu;
    }

}
