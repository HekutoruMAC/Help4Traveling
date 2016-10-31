package Logica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionVieja {

    Conector conector = Conector.getInstance();

    private String servidor = conector.getServidor();
    private String usuario = conector.getUsuario();
    private String clave = conector.getClave();
    private String driver = conector.getDriver();

    private static Connection conexion;

    public ConexionVieja() {
        try {
            System.out.print("Conectando al servidor: ");
            Class.forName(driver).newInstance();
            try {
                conexion = DriverManager.getConnection(servidor, usuario, clave);
                System.out.println("OK");
            } catch (SQLException ex) {
                System.out.println("ERROR.");
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "No se pudo establecer la Conexión.", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("ERROR.");
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        System.out.println(conexion);
        return conexion;
    }
}
