package Logica;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Conector {

    private static String servidor_original = "jdbc:mysql://localhost:3306/help4traveling?maxReconnects=1&autoReconnect=true&useSSL=false";
    private static String usuario_original = "root";
    private static String clave_original = "root";
    private static String driver_original = "com.mysql.jdbc.Driver";
    private static String service_original = "http://localhost:9128/publicador";

    private String servidor = servidor_original;
    private String usuario = usuario_original;
    private String clave = clave_original;
    private String driver = driver_original;
    private String service = service_original;

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
        this.service = service_original;
    }

    public void cargarConfig() throws IOException {
        Path conf = Paths.get("central.conf");
        List<String> valores = Files.readAllLines(conf, Charset.forName("UTF-8"));
        servidor = valores.get(0);
        usuario = valores.get(1);
        clave = valores.get(2);
        driver = valores.get(3);
        service = valores.get(4);
    }

    // Getters
    public String getServidor() {
        return this.servidor;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public String getClave() {
        return this.clave;
    }

    public String getDriver() {
        return this.driver;
    }

    public String getService() {
        return this.service;
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

    public void setService(String service) {
        this.service = service;
    }
}
