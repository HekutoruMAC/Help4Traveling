package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {

    Conector conector = Conector.getInstance();

    private static Connection con;

    private Conexion() {
    }

    public static Conexion getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {

        private static final Conexion INSTANCE = new Conexion();
    }

    public Boolean getEstado() {
        Boolean estado = false;
        if (con != null) {
            try {
                estado = ((con.isValid(0)) && (!con.isClosed()));
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return estado;
    }

    public Connection getConnection() {
        abrirConexion();
        return con;
    }

    public Boolean abrirConexion() {
        Boolean abierta = false;

        String servidor = conector.getServidor();
        String usuario = conector.getUsuario();
        String clave = conector.getClave();
        String driver = conector.getDriver();

        try {
            System.out.print("Conectando al servidor: ");
            Class.forName(driver).newInstance();
            try {
                con = DriverManager.getConnection(servidor, usuario, clave);
                System.out.println("OK");
                abierta = true;
            } catch (SQLException ex) {
                System.out.println("ERROR.");
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "Error: No se pudo establecer la conexión.", "Aviso", JOptionPane.ERROR_MESSAGE);
                con.close();
            }
        } catch (Exception e) {
            System.out.println("ERROR.");
            System.out.println(e);
        }
        return abierta;
    }

    public Boolean cerrarConexion() {
        Boolean cerrada = false;
        if (this.getEstado()) {
            try {
                con.close();
                cerrada = true;
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
        return cerrada;
    }

    public Boolean probarConexion() {
        Boolean probada = false;
        cerrarConexion();
        abrirConexion();
        if (getEstado()) {
            probada = true;
            cerrarConexion();
        }
        return probada;
    }

    public void ejecutarSentencia(String sentencia, Boolean update) {
        if (!this.getEstado()) {
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión.", "Aviso", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Statement st;
        String sql;
        sql = sentencia;
        try {
            st = con.createStatement();
            if (update) {
                st.executeUpdate(sql);
            } else {
                ResultSet rs;
                rs = st.executeQuery(sql);
                rs.close();
            }
            st.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se pudo ejecutar la sentencia.", "Aviso", JOptionPane.ERROR_MESSAGE);
            System.err.println(e.getMessage());
        }
    }
}
