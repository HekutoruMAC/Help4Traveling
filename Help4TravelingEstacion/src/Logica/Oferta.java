/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
// Comentario para que me reconozca los cambios y pueda comitear...again
/**
 *
 * @author Leonardo
 */
public abstract class Oferta {
    private String nombre;
    private Proveedor proveedor;

    public Oferta(String nombre, Proveedor prov) {
        this.nombre = nombre;
        this.proveedor = prov;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Proveedor getProveedor() {
        return this.proveedor;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }    
    
}
