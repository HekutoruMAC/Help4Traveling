/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Leonardo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DataUsuariosSistemaArrayList {
    private ArrayList<DtUsuario> usuariosSistema;

    public DataUsuariosSistemaArrayList() {
    }

    public DataUsuariosSistemaArrayList(ArrayList<DtUsuario> usuariosSistema) {
        this.usuariosSistema = usuariosSistema;
    }

    public ArrayList<DtUsuario> getUsuariosSistema() {
        return usuariosSistema;
    }    
    
}
