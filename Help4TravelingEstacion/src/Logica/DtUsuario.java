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

/**
 *
 * @author Leonardo
 */
public class DtUsuario {

    private String nombre;
    private String apellido;
    private String nickname;
    private String correo;
    private String password;
    private Date nacimiento;
    private String imagen;
    private String tipo;
    private String empresa;
    private String link;    

    public DtUsuario(String nombre, String apellido, String nickname, String password, String correo, Date nacimiento, String imagen, String tipo, String empresa, String link) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.correo = correo;
        this.password = password;
        this.nacimiento = nacimiento;
        this.imagen = imagen;
        this.tipo = tipo;
        this.empresa = empresa;
        this.link = link;
    }

    public DtUsuario(String nombre, String apellido, String nickname, String correo, Date nacimiento, String imagen, String tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.imagen = imagen;
        this.tipo = tipo;
    }

    public DtUsuario() {

    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPassword() {
        return password;
    }    

    public Date getNacimiento() {
        return nacimiento;       
    }

    public String getImagen() {
        return imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEmpresa() {
        return empresa;       
    }

    public String getLink() {
        return link;       
    }
}

