/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import java.util.List;
// Comentario para que me reconozca los cambios y pueda comitear...

/**
 *
 * @author Leonardo
 */
public interface IControladorServicio {

    public abstract String altaDeServicio(DtServicio dts);

    public abstract String altaDePromocion(DtPromocion dtp);

    public abstract String altaDeCategoria(String nombre, String NombPadre);

    public abstract List<DtServicio> listarServicios();

    public abstract ArrayList<DtPromocion> listarPromociones();

    public abstract List<DtServicio> listarServiciosPromocion(DtPromocion dtp);

    public abstract String actualizarUnServicio(DtServicio dts);

    public abstract List<String> listarCategorias();

    public abstract List<String> listarServiciosCategoria(String cat);

    public abstract void verInfodeServicio(String nombre, String Proevedor);

    public abstract void verInfoDePromocion();

    public abstract Servicio obtenerServicio(String nk);

    public boolean existeServicio(String nombre);

    public DtServicio getDtServicio(String nombre, String proveedor);

    // Servidor Central ========================================================
    
    public String getNkProveedorServicio(String servicio);

    public String getNkProveedorPromocion(String promocion);

    public ArrayList<String> listarServiciosDePromociones(String nombre, String prov);
    
        public String obtenerPrimeraImagenPromocion(String promocion, String proveedor);

}
