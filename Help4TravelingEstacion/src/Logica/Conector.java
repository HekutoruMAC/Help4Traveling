/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Hekutoru
 */
public class Conector {

    private static String servidor_original = "jdbc:mysql://localhost:3306/help4traveling?maxReconnects=1&autoReconnect=true&useSSL=false";
    private static String usuario_original = "root";
    private static String clave_original = "root";
    private static String driver_original = "com.mysql.jdbc.Driver";

    private String servidor = servidor_original;
    private String usuario = usuario_original;
    private String clave = clave_original;
    private String driver = driver_original;

    private Conector() {
    }

    public static Conector getInstance() {
        return ConectorHolder.INSTANCE;
    }

    private static class ConectorHolder {

        private static final Conector INSTANCE = new Conector();
    }

    public void revertir() {
        this.servidor = servidor_original;
        this.usuario = usuario_original;
        this.clave = clave_original;
        this.driver = driver_original;
    }

    // Getters
    public String getServidor() {
        return servidor;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public String getDriver() {
        return driver;
    }

    // Setters
    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
}
