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
public class DtProveedor extends DtUsuario {
    private String empresa;
    private String link;
    
    public DtProveedor(String nombre, String apellido, String nickname, String correo, Date nacimiento, String imagen, String empresa, String link){
            super(nombre,apellido,nickname,correo,nacimiento,imagen,"");
            this.empresa =empresa;
            this.link = link;
    }
            
    
    
    public String getEmpresa() {
        return empresa;
    }

    public String getLink() {
        return link;
    }
}

