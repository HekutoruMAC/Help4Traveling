/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import java.util.List;
/**
 *
 * @author Leonardo
 */
public class Pais {
    private String nombre;
    private List<Ciudad> ciudades;

    public Pais(String nombre, List<Ciudad> ciudades) {
        this.nombre = nombre;
        this.ciudades = ciudades;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
    
    
}
