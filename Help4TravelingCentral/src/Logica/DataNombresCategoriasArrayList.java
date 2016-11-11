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
public class DataNombresCategoriasArrayList {
    ArrayList<String> categorias;

    public DataNombresCategoriasArrayList() {
    }

    public DataNombresCategoriasArrayList(ArrayList<String> categorias) {
        this.categorias = categorias;
    }

    public ArrayList<String> getCategorias() {
        return categorias;
    }      
    
}
