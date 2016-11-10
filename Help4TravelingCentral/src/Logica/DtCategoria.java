/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
// Comentario para que me reconozca los cambios y pueda comitear...

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Leonardo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtCategoria {
    private String nombre;
    private String padre;

    public DtCategoria(String nombre, String padre) {
        this.nombre = nombre;
        this.padre = padre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPadre() {
        return padre;
    }     
    
}
