/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
// Comentario para que me reconozca los cambios y pueda comitear...again
/**
 *
 * @author Leonardo
 */
public class Servicio extends Oferta {
    private String descripcion;
    private List<String> imagenes;
    private Map<String,Categoria> categoriasNom;
    private float precio;
    private Ciudad origen;
    private Ciudad destino;
    
    public Servicio() {
        super("PorDefecto",null);
        this.descripcion = "";
        this.imagenes = new LinkedList<String>();;
        this.categoriasNom = new HashMap<String,Categoria>();
        this.precio = 0;
        this.origen = null;
        this.destino = null;
    }
    
    public Servicio(String nombre, Proveedor proveedor) {
        super(nombre,proveedor);
        this.descripcion = "";
        this.imagenes = new LinkedList<String>();;
        this.categoriasNom = new HashMap<String,Categoria>();
        this.precio = 0;
        this.origen = null;
        this.destino = null;
    }

    public Servicio(String nombre, Proveedor prov, String descripcion, List<String> imagenes, float precio, Ciudad origen) {
        super(nombre, prov);
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.categoriasNom = new HashMap<String,Categoria>();
        this.precio = precio;
        this.origen = origen;
        this.destino = null;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getImagenes() {
        return imagenes;
    }

    public Map<String, Categoria> getCategorias() {
        return categoriasNom;
    }   
    
    public float getPrecio() {
        return precio;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }    

    public void setOrigen(Ciudad origen) {
        this.origen = origen;
    }

    public void setDestino(Ciudad destino) {
        this.destino = destino;
    }    
    
    // Operaciones para manejar coleccion de Categorias del Servicio
    public void agregarCategoria(Categoria cat){
        String nombre = cat.getNombre();
        categoriasNom.put(nombre,cat);
    }
    
    public boolean existeCategoria(Categoria cat){
        String nombre = cat.getNombre();
        return categoriasNom.containsKey(nombre);        
    }
    
    public Categoria obtenerCategoria(String nombre){
        return ((Categoria) categoriasNom.get(nombre));
    }      
    
    public DtServicio getDtServicio() {
        Map<String, DtCategoria> listaCat = new HashMap<String, DtCategoria>();
        Iterator<Categoria> itercat = this.categoriasNom.values().iterator();
        while (itercat.hasNext()) {
            String nomcat = itercat.next().getNombre();
            String padrecat = itercat.next().getNomPadre();
            DtCategoria dtCat = new DtCategoria(nomcat,padrecat); 
            listaCat.put(nomcat, dtCat);
        }
        return new DtServicio(this.getNombre(), this.getProveedor().getNickname(), this.getDescripcion(), this.getImagenes(), listaCat, this.getPrecio(), this.getOrigen().getNombre(), this.getDestino().getNombre());
    }
}
