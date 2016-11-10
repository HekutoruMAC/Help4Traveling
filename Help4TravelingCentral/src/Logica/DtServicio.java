/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
// // Comentario para que me reconozca los cambios y pueda comitear...
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Leonardo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtServicio {
    private String nombre;
    private String nkproveedor;
    private String descripcion;
    private List<String> imagenes;
    private Map<String,DtCategoria> categorias;
    List<String> categoriasservicio;
    private float precio;
    private String nomciuorigen;
    private String nomciudestino;

    public DtServicio() {
        this.nombre = "null";
        this.nkproveedor = "null";
    }
    
    public DtServicio(String nombre, String nkproveedor, String descripcion, List<String> imagenes, Map<String, DtCategoria> categorias, float precio, String origen, String destino) {
        this.nombre = nombre;
        this.nkproveedor = nkproveedor;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.categorias = categorias;
        this.precio = precio;
        this.nomciuorigen = origen;
        this.nomciudestino = destino;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public List<String> getImagenes() {
        return imagenes;
    }
    
    public Map<String,DtCategoria> getDtCategorias() {
        return categorias;
    }

    public List<String> getCategoriasServicio() {
        categoriasservicio = new ArrayList<String>();
        Iterator<DtCategoria> iter = categorias.values().iterator();
        while (iter.hasNext()) {
            categoriasservicio.add(iter.next().getNombre());
        }
        return categoriasservicio;
    }

    public float getPrecio() {
        return precio;
    }

    public String getNkProveedor() {
        return nkproveedor;
    }

    public String getNomCiuOrigen() {
        return nomciuorigen;
    }

    public String getNomCiuDestino() {
        return nomciudestino;
    }

    
        
    
}

