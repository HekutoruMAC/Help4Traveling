/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Leonardo
 */
public class ControladorServicio implements IControladorServicio {

    private String comprobarCompletitudDatos(DtServicio dts) {
        String mensaje = "CAMPOSOK";
        if (dts.getNombre().equals("")) {
            mensaje = "ERROR: El Nombre no puede ser vacío...";
        } else if (dts.getPrecio() <= 0) {
            mensaje = "ERROR: El Pecio debe ser mayor que 0...";
        } else if (dts.getImagenes().size() > 3) {
            mensaje = "ERROR: Debe elegir como mucho 3 imagenes...";
        } else if (dts.getNomCiuOrigen().equals("")) {
            mensaje = "ERROR: La ciudad de origen no puede ser vacía...";
        } else if (dts.getDtCategorias().isEmpty()) {
            mensaje = "ERROR: Debe elegir al menos una categoria...";
        }
        return mensaje;
    }

    private boolean existenCategorias(Map<String, DtCategoria> catServ, List<String> catSist) {
        boolean categoriasok = true;
        boolean encontrado;
        Iterator<DtCategoria> iter1 = catServ.values().iterator();
        while ((iter1.hasNext()) && (categoriasok)) {
            Iterator<String> iter2 = catSist.iterator();
            String nom = iter1.next().getNombre();
            encontrado = false;
            while (iter2.hasNext() && (!encontrado)) {
                if (nom == iter2.next()) {
                    encontrado = true;
                }
            }
            if (!encontrado) {
                categoriasok = false;
            }
        }
        return categoriasok;
    }

    private String altaServicio(DtServicio dts) {
        String mensaje = comprobarCompletitudDatos(dts);
        if (mensaje.equals("CAMPOSOK")) {
            ManejadorServicio ms = ManejadorServicio.getInstance();
            mensaje = ms.persistirServicio(dts);
        }
        return mensaje;
    }

    public String altaDeServicio(DtServicio dts) {
        String mensaje = "El servicio fue agregado con exito.";
        mensaje = altaServicio(dts);
        return mensaje;
    }

    private String actualizarServicio(DtServicio dts) {
        String mensaje = comprobarCompletitudDatos(dts);
        if (mensaje.equals("CAMPOSOK")) {
            ManejadorServicio ms = ManejadorServicio.getInstance();
            mensaje = ms.persistirActualizacionServicio(dts);
        }
        return mensaje;
    }

    public String actualizarUnServicio(DtServicio dts) {
        String mensaje = "El servicio fue actualizado con exito.";
        mensaje = actualizarServicio(dts);
        return mensaje;
    }

    public String altaDePromocion(DtPromocion dtp) {
        ManejadorServicio ms = ManejadorServicio.getInstance();
        return ms.persistirPromo(dtp);
    }

    public String altaDeCategoria(String nombre, String NombPadre) {

        //String retorno="";
        if (!(ManejadorCategoria.getInstance().existeCategoria(nombre))) {

            if (NombPadre == null) {
                Categoria nueva = new CatHoja(nombre, null);
                if (ManejadorCategoria.getInstance().agregarCategoria(nueva)) {
                    return "Categroria base " + nombre + " creada correctamente)";
                } else {
                    return ("La categroria base " + nombre + " no pudo ser  creada");
                }
            } else if ((ManejadorCategoria.getInstance().existeCategoria(NombPadre))) {
                Categoria nueva1 = new CatHoja(nombre, NombPadre);
                /*((CatCompuesta)instPadre).insertarCategoria(nueva1);*/
                ManejadorCategoria.getInstance().agregarCategoria(nueva1);
                return "Categroria  " + nombre + " hija de " + NombPadre + " fue creada correctamente";
            } else {
                return "Categroria  " + nombre + " No fue creada correctamente ya que " + NombPadre + " no existe ";
            }
        } else {
            return "Categroria  " + nombre + " No fue creada correctamente ya que esa categoria ya existe ";

        }
    }

    public List<DtServicio> listarServicios() {
        return ManejadorServicio.getInstance().listarServicios();
    }

    public List<DtPromocion> listarPromociones() {
        return ManejadorServicio.getInstance().listarPromociones();
    }

    public List<DtServicio> listarServiciosPromocion(DtPromocion dtp) {
        return ManejadorServicio.getInstance().listarServiciosPromocion(dtp);
    }

    public List<String> listarCategorias() {
        ManejadorCategoria mac = ManejadorCategoria.getInstance();
        return mac.getNombresCategorias();
    }

    public List<String> listarServiciosCategoria(String categoria) {
        return ManejadorServicio.getInstance().listarServiciosCategoria(categoria);
    }

    public void verInfodeServicio(String nombre, String Proevedor) {

    }

    public void verInfoDePromocion() {

    }

    public Servicio obtenerServicio(String nk) {
        return ManejadorServicio.getInstance().obtenerServicio(nk);
    }

    public boolean existeServicio(String nombre) {
        return ManejadorServicio.getInstance().existeServicio(nombre);
    }
}
