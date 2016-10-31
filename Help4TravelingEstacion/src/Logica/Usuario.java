/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author yaman
 */
public abstract class Usuario {    
    private String nombre;
    private String apellido;
    private String nickname;
    private String correo;
    private String password;
    private Date nacimiento;
    private String imagen;
    
    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public Usuario(String nombre, String apellido, String nickname, String password, String correo, Date nacimiento, String imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nickname = nickname;
        this.correo = correo;
        this.password = password;
        this.nacimiento = nacimiento;
        this.imagen = imagen;
    }
    
   //Getters

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
    
    public DtUsuario getDtUsuario(){
        DtUsuario dtu = new DtUsuario(this.nombre,this.apellido,this.nickname,this.password,this.correo,this.nacimiento,this.imagen,"","","");
        return dtu;
        //Verificar desdpués
        
    }
    
    //Setters
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }   
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}

