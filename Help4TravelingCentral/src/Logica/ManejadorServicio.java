/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Leonardo
 */
public class ManejadorServicio {

    //Clase que conserva la colección global de las Ofertas Servicios del Sistema
    private Map<String, Servicio> serviciosNom;
    private Map<String, Promocion> promosNom;
    private static ManejadorServicio instancia = null;
    private Conexion conexion;
    private String sql;

    private ManejadorServicio() {
        serviciosNom = new HashMap<String, Servicio>();
    }

    public static ManejadorServicio getInstance() {
        if (instancia == null) {
            instancia = new ManejadorServicio();
        }
        return instancia;
    }

    public void agregarServicio(Servicio serv) {
        String nombre = serv.getNombre();
        serviciosNom.put(nombre, serv);
    }

    public boolean existeServicio(String nombre) {
        boolean existe = false;
        ResultSet rs;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql1 = "SELECT * FROM help4traveling.servicios WHERE nombre='" + nombre + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql1);
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            con.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("No se sabe si existe Servicio :(");
        }
        if (existe) {
            System.out.println("Existe Servicio");
        } else {
            System.out.println("No existe Servicio");
        }
        return existe;
        //return serviciosNom.containsKey(nombre);
    }

    public boolean existePromo(String nombre) {
        boolean existe = false;
        ResultSet rs;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql1 = "SELECT * FROM help4traveling.promociones WHERE nombre='" + nombre + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql1);
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            con.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("No exite servicio :(");
        }
        if (existe) {
            System.out.println("Existe Servicio");
        } else {
            System.out.println("NO Existe Servico");
        }
        return existe;
        //return serviciosNom.containsKey(nombre);
    }

    public Servicio obtenerServicio(String nk) {

        ResultSet rsServicio;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql1 = "SELECT * FROM help4traveling.servicios S inner join help4traveling.servicioscategorias SC on S.nombre = SC.servicio WHERE S.nombre ='" + nk + "'";
        Servicio nuevo = new Servicio();
        try {
            st = con.createStatement();
            rsServicio = st.executeQuery(sql1);
            rsServicio.next();
            String nombre = rsServicio.getString("nombre");
            String proveedor = rsServicio.getString("proveedor");
            String descripcion = rsServicio.getString("descripcion");
            String precio = rsServicio.getString("precio");
            String origen = rsServicio.getString("origen");
            String destino = rsServicio.getString("destino");
            //String imagen = rsServicio.getString("imagen");
            String categoria = rsServicio.getString("categoria");

            nuevo.setDescripcion(descripcion);
            nuevo.setNombre(nombre);
            Proveedor prov = ManejadorProveedor.getInstance().obtenerProveedor(proveedor);
            nuevo.setProveedor(prov);
            System.out.println(prov.getNickname());

            Ciudad ori = ManejadorCiudad.getInstance().obtenerCiudad(origen);

            Ciudad dest = null;
            if (!(destino == null)) {

                dest = ManejadorCiudad.getInstance().obtenerCiudad(destino);
                nuevo.setDestino(dest);
            }

            nuevo.setOrigen(ori);

            List<String> img = new LinkedList<String>();
            img.add("");
            nuevo.setImagenes(img);
            HashMap<String, Categoria> cat = new HashMap<String, Categoria>();
            Categoria cate = new CatHoja(categoria, "sin padrepor ahora");
            cat.put(categoria, cate);
            float precioint = Float.parseFloat(precio);
            nuevo.setPrecio(precioint);

            rsServicio.close();
            con.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("No existe servicio :(");
            System.err.println(e.getMessage());
        }
        return nuevo;
    }

    public DtServicio getDtServicio(String nombre, String proveedor) {
        ResultSet rsServ, rsCat, rsImg;
        Connection con = Conexion.getInstance().getConnection();
        DtServicio nuevo = null;
        Statement stServ, stImg, stCat;
        String sql = "SELECT * FROM help4traveling.servicios WHERE nombre='" + nombre + "' AND proveedor='" + proveedor + "'";
        try {
            stServ = con.createStatement();
            rsServ = stServ.executeQuery(sql);
            while (rsServ.next()) {
                String descripcion = rsServ.getString("descripcion");
                String precio = rsServ.getString("precio");
                String origen = rsServ.getString("origen");
                String destino = rsServ.getString("destino");
                Float valor = Float.parseFloat(precio);
                List<String> imagenes = new ArrayList<String>();
                sql = "SELECT * FROM help4traveling.serviciosimagenes WHERE servicio='" + nombre + "'";
                stImg = con.createStatement();
                rsImg = stImg.executeQuery(sql);
                while (rsImg.next()) {
                    imagenes.add(rsImg.getString("imagen"));
                }
                rsImg.close();
                stImg.close();
                Map<String, DtCategoria> categorias = new HashMap<String, DtCategoria>();
                sql = "SELECT * FROM help4traveling.servicioscategorias WHERE servicio='" + nombre + "' AND proveedorServicio='" + proveedor + "'";
                stCat = con.createStatement();
                rsCat = stCat.executeQuery(sql);
                while (rsCat.next()) {
                    DtCategoria dtCat = new DtCategoria(rsCat.getString("categoria"), rsCat.getString("categoriaPadre"));
                    categorias.put(rsCat.getString("categoria"), dtCat);
                }
                rsCat.close();
                stCat.close();
                nuevo = new DtServicio(nombre, proveedor, descripcion, imagenes, categorias, valor, origen, destino);
            }
            rsServ.close();
            con.close();
            stServ.close();
        } catch (SQLException e) {
            System.out.println("No pude crear DtServicio :(");
        }
        return nuevo;
    }

    public ArrayList<DtServicio> listarServicios() {
        ResultSet rsServicios;
        ResultSet rsServImagenes;
        ResultSet rsServCategorias;
        Connection con = Conexion.getInstance().getConnection();
        Statement st, sti, stc;
        String sqlServicio, sqlImagenes, sqlCategorias;
        ArrayList<DtServicio> listaServicios = new ArrayList<DtServicio>();
        sqlServicio = "SELECT * FROM help4traveling.servicios";
        try {
            st = con.createStatement();
            rsServicios = st.executeQuery(sqlServicio);
            while (rsServicios.next()) {
                String nombre = rsServicios.getString("nombre");
                String proveedor = rsServicios.getString("proveedor");
                String descripcion = rsServicios.getString("descripcion");
                String precio = rsServicios.getString("precio");
                String origen = rsServicios.getString("origen");
                String destino = rsServicios.getString("destino");
                List<String> listaImagenes = new LinkedList<String>();
                sqlImagenes = "SELECT * FROM help4traveling.serviciosimagenes WHERE servicio = '" + nombre + "'";
                sti = con.createStatement();
                rsServImagenes = sti.executeQuery(sqlImagenes);
                while (rsServImagenes.next()) {
                    listaImagenes.add(rsServImagenes.getString("imagen"));
                }
                rsServImagenes.close();
                sti.close();
                sqlCategorias = "SELECT * FROM help4traveling.servicioscategorias WHERE servicio = '" + nombre + "'";
                stc = con.createStatement();
                rsServCategorias = stc.executeQuery(sqlCategorias);
                Map<String, DtCategoria> listaCategorias = new TreeMap<String, DtCategoria>();
                while (rsServCategorias.next()) {
                    String nomCat = rsServCategorias.getString("categoria");
                    DtCategoria categoria = new DtCategoria(nomCat, rsServCategorias.getString("categoriaPadre"));
                    listaCategorias.put(nomCat, categoria);
                }
                rsServCategorias.close();
                stc.close();
                DtServicio nuevo = new DtServicio(nombre, proveedor, descripcion, listaImagenes, listaCategorias, Float.parseFloat(precio), origen, destino);
                listaServicios.add(nuevo);
            }
            rsServicios.close();
            st.close();
            con.close();
            System.out.println("Servicios cargados :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar servicios :(");
        }
        return listaServicios;
    }

    public ArrayList<String> listarServiciosCategoria(String categoria) {
        ResultSet rsServicios;
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql;

        ArrayList<String> listaServicios = new ArrayList<String>();
        sql = "SELECT * FROM help4traveling.servicioscategorias WHERE categoria='" + categoria + "'";
        System.out.println("entre al listar servicios");
        try {

            st = con.createStatement();
            rsServicios = st.executeQuery(sql);

            while (rsServicios.next()) {
                listaServicios.add(rsServicios.getString("servicio"));
            }

        } catch (SQLException e) {
            System.out.println("No pude cargar servicios");
            System.err.println(e.getMessage());
        }
        return listaServicios;
    }

    public ArrayList<DtServicio> listarServiciosProveedor(DtUsuario user) {
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        ResultSet rsServiciosProveedor;
        sql = "SELECT * FROM help4traveling.servicios WHERE proveedor= '" + user.getNickname() + "'";
        ArrayList<DtServicio> listaServicios = new ArrayList<DtServicio>();
        try {
            st = con.createStatement();
            rsServiciosProveedor = st.executeQuery(sql);

            DtServicio ser;
            while (rsServiciosProveedor.next()) {
                System.out.println("llegue2");
                String nombre = rsServiciosProveedor.getString("nombre");
                String nkproveedor = rsServiciosProveedor.getString("proveedor");
                ser = getDtServicio(nombre, nkproveedor);
                listaServicios.add(ser);
            }
            rsServiciosProveedor.close();
            con.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("No hubo resultado");
            System.out.println(e.getMessage());
        }
        return listaServicios;
    }

    public List<DtServicio> getDtServicios() {
        List<DtServicio> listaDtServ = new LinkedList<DtServicio>();
        Iterator<Servicio> iter = this.serviciosNom.values().iterator();
        while (iter.hasNext()) {
            Servicio serv = iter.next();
            DtServicio dtServ = serv.getDtServicio();
            listaDtServ.add(dtServ);
        }
        return listaDtServ;
    }

    public List<DtServicio> listarServiciosPromocion(DtPromocion promo) {
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        ResultSet rsServiciosPromo;
        sql = "SELECT * FROM help4traveling.promocionesservicios WHERE promocion= '" + promo.getNombre() + "'";

        List<DtServicio> listaServicios = new ArrayList<DtServicio>();
        try {
            st = con.createStatement();
            rsServiciosPromo = st.executeQuery(sql);

            DtServicio ser;
            while (rsServiciosPromo.next()) {
                String nombre = rsServiciosPromo.getString("servicio");
                String nkproveedor = rsServiciosPromo.getString("proveedorServicio");
                ser = getDtServicio(nombre, nkproveedor);
                listaServicios.add(ser);
            }
            rsServiciosPromo.close();
            con.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("No hubo resultado");
            System.out.println(e.getMessage());
        }
        return listaServicios;
    }

    public ArrayList<DtPromocion> listarPromociones() {
        ArrayList<DtPromocion> promociones = null;
        ResultSet rs;
        Statement st;
        try {
            Connection con = Conexion.getInstance().getConnection();
            st = con.createStatement();
            String sql = "SELECT * FROM help4traveling.promociones";
            rs = st.executeQuery(sql);
            promociones = new ArrayList<DtPromocion>();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String proveedor = rs.getString("proveedor");
                String descuento = rs.getString("descuento");
                String total = rs.getString("total");
                DtPromocion nuevo = new DtPromocion(nombre, proveedor, descuento, total);
                promociones.add(nuevo);
            }
            rs.close();
            con.close();
            st.close();
            System.out.println("promociones  cargadas :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar promociones :(");
        }
        return promociones;
    }

    public DtPromocion getDTPromocion(String nombre, String Proevedor) {
        ResultSet rsPromociones;
        DtPromocion nuevo = null;
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        sql = "SELECT * FROM help4traveling.promociones WHERE nombre='" + nombre + "' and proveedor='" + Proevedor + "'";
        try {
            st = con.createStatement();
            rsPromociones = st.executeQuery(sql);
            while (rsPromociones.next()) {
                String nombre1 = rsPromociones.getString("nombre");
                String proveedor = rsPromociones.getString("proveedor");
                String descuento = rsPromociones.getString("descuento");
                String total = rsPromociones.getString("total");
                nuevo = new DtPromocion(nombre1, proveedor, descuento, total/*, servicios*/);

            }
            rsPromociones.close();
            con.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("No pude cargar usuarios :(");
        }
        return nuevo;
    }

    public String persistirServicio(DtServicio serv) {
        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String mensaje = "Se dio de alta al Servicio.";
        System.out.println(serv.getNombre());
        if (!existeServicio(serv.getNombre())) {
            //sql = "INSERT INTO help4traveling.servicios (nombre,proveedor,descripcion,precio,origen,destino) VALUES ('" + serv.getNombre() + "','" + serv.getProveedor().getNickname() + "','" + serv.getDescripcion() + "'," + (double) serv.getPrecio() + ",'" + serv.getOrigen().getNombre() + "','" + serv.getDestino().getNombre() + "')";
            sql = "INSERT INTO help4traveling.ofertas (nombre,proveedor) VALUES ('" + serv.getNombre() + "','" + serv.getNkProveedor() + "')";
            //System.out.println(sql);
            try {
                st = con.createStatement();
                //System.out.println("antes de insertar");
                st.executeUpdate(sql);
                //con.close();
                st.close();
                System.out.println("INSERTE en oferta:)");
            } catch (SQLException e) {
                System.out.println("No pude INSERTAR :(");
            }
            String ciudestino = serv.getNomCiuDestino();
            if (ciudestino != null) {
                ciudestino = "'" + ciudestino + "'";
            }
            sql = "INSERT INTO help4traveling.servicios (nombre,proveedor,descripcion,precio,origen,destino) "
                    + "VALUES ('" + serv.getNombre() + "','" + serv.getNkProveedor() + "','" + serv.getDescripcion() + "'," + (double) serv.getPrecio() + ",'" + serv.getNomCiuOrigen() + "'," + ciudestino + ")";
            System.out.println(sql);
            try {
                st = con.createStatement();
                //System.out.println("antes de insertar");
                st.executeUpdate(sql);
                //con.close();
                st.close();
                System.out.println("INSERTE en servicio:)");
            } catch (SQLException e) {
                System.out.println("No pude INSERTAR :(");
            }
            List<String> imagserv = serv.getImagenes();
            Iterator<String> iteri = imagserv.iterator();
            while (iteri.hasNext()) {
                sql = "INSERT INTO help4traveling.serviciosimagenes (servicio,imagen) VALUES ('" + serv.getNombre() + "','" + iteri.next() + "')";
                try {
                    st = con.createStatement();
                    st.executeUpdate(sql);
                    st.close();
                    //con.close();
                    System.out.println("INSERTE :)");
                } catch (SQLException e) {
                    System.out.println("No pude INSERTAR :(");
                    System.out.println(e);
                }
            }
            Map<String, DtCategoria> catserv = serv.getDtCategorias();
            Iterator<DtCategoria> iterc = catserv.values().iterator();
            while (iterc.hasNext()) {
                DtCategoria cat = iterc.next();
                //sql = "INSERT INTO help4traveling.servicioscategorias (servicio,proveedorServicio,categoria) VALUES ('" + serv.getNombre() + "','" + serv.getProveedor().getNickname() + "','" + iterc.next().getNombre() + "')";
                sql = "INSERT INTO help4traveling.servicioscategorias (servicio,proveedorServicio,categoria,categoriaPadre) VALUES ('" + serv.getNombre() + "','" + serv.getNkProveedor() + "','" + cat.getNombre() + "','" + cat.getPadre() + "')";
                System.out.println(sql);
                try {
                    st = con.createStatement();
                    //System.out.println("antes de categoria");
                    st.executeUpdate(sql);
                    st.close();
                    //con.close();
                    System.out.println("INSERTE :)");
                } catch (SQLException e) {
                    System.out.println("No pude INSERTAR :(");
                }
            }

        } else {
            mensaje = "ERROR: El Servicio ingresado ya existe...";
        }
        return mensaje;
    }

    public String persistirActualizacionServicio(DtServicio serv) {
        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String mensaje = "Se realizó la actualización del Servicio.";
        System.out.println(serv.getNombre());
        String ciudestino = serv.getNomCiuDestino();
        if (ciudestino != null) {
            ciudestino = "'" + ciudestino + "'";
        }
        sql = "UPDATE help4traveling.servicios SET descripcion='" + serv.getDescripcion() + "',precio=" + (double) serv.getPrecio()
                + ",origen='" + serv.getNomCiuOrigen() + "',destino=" + ciudestino + " WHERE nombre='" + serv.getNombre() + "';";
        //System.out.println(sql);
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            //con.close();
            st.close();
            System.out.println("ACTUALICE en servicio:)");
        } catch (SQLException e) {
            System.out.println("No pude ACTUALIZAR :(");
        }
        sql = "DELETE FROM help4traveling.serviciosimagenes WHERE servicio='" + serv.getNombre() + "';";
        //System.out.println(sql);
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
            //con.close();
            System.out.println("ELIMINE :)");
        } catch (SQLException e) {
            System.out.println("No pude ELIMINAR :(");
        }
        List<String> imagserv = serv.getImagenes();
        Iterator<String> iteri = imagserv.iterator();
        while (iteri.hasNext()) {
            sql = "INSERT INTO help4traveling.serviciosimagenes (servicio,imagen) VALUES ('" + serv.getNombre() + "','" + iteri.next() + "')";
            //System.out.println(sql);
            try {
                st = con.createStatement();
                st.executeUpdate(sql);
                st.close();
                //con.close();
                System.out.println("INSERTE :)");
            } catch (SQLException e) {
                System.out.println("No pude INSERTAR :(");
            }
        }
        sql = "DELETE FROM help4traveling.servicioscategorias WHERE servicio='" + serv.getNombre() + "';";
        //System.out.println(sql);
        try {
            st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
            //con.close();
            System.out.println("ELIMINE :)");
        } catch (SQLException e) {
            System.out.println("No pude ELIMINAR :(");
        }
        Map<String, DtCategoria> catserv = serv.getDtCategorias();
        Iterator<DtCategoria> iterc = catserv.values().iterator();
        while (iterc.hasNext()) {
            DtCategoria cat = iterc.next();
            sql = "INSERT INTO help4traveling.servicioscategorias (servicio,proveedorServicio,categoria,categoriaPadre) VALUES ('" + serv.getNombre() + "','" + serv.getNkProveedor() + "','" + cat.getNombre() + "','" + cat.getPadre() + "')";
            //System.out.println(sql);
            try {
                st = con.createStatement();
                System.out.println("antes de categoria");
                st.executeUpdate(sql);
                st.close();
                //con.close();
                System.out.println("INSERTE :)");
            } catch (SQLException e) {
                System.out.println("No pude INSERTAR :(");
            }
        }
        return mensaje;
    }

    public String persistirPromo(DtPromocion promo) {
        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String mensaje = "Promo almacenada correctamente.";
        System.out.println(promo.getNombre());
        if (!existePromo(promo.getNombre())) {
            //sql = "INSERT INTO help4traveling.servicios (nombre,proveedor,descripcion,precio,origen,destino) VALUES ('" + serv.getNombre() + "','" + serv.getProveedor().getNickname() + "','" + serv.getDescripcion() + "'," + (double) serv.getPrecio() + ",'" + serv.getOrigen().getNombre() + "','" + serv.getDestino().getNombre() + "')";
            sql = "INSERT INTO help4traveling.ofertas (nombre,proveedor) VALUES ('" + promo.getNombre() + "','" + promo.getProveedor() + "')";
            System.out.println(sql);
            try {
                st = con.createStatement();
                st.executeUpdate(sql);
                //con.close();
                st.close();
                System.out.println("INSERTE en oferta:)");
            } catch (SQLException e) {
                System.out.println("No pude INSERTAR :(");
            }
            sql = "INSERT INTO help4traveling.promociones (nombre,proveedor,descuento,total) "
                    + "VALUES ('" + promo.getNombre() + "','" + promo.getProveedor() + "','" + promo.getDescuento() + "','" + promo.getPrecio() + "')";
            System.out.println(sql);
            try {
                st = con.createStatement();
                st.executeUpdate(sql);
                //con.close();
                st.close();
                System.out.println("INSERTE en promocion:)");
            } catch (SQLException e) {
                System.out.println("No pude INSERTAR :(");
                System.err.println(e);
            }

            //List<String> servicios = listarServiciosDePromociones(promo.getNombre(), promo.getProveedor());
            List<DtServicio> servicios = promo.getServicios();

            if (servicios != null) {
                Iterator<DtServicio> it = servicios.iterator();
                while (it.hasNext()) {
                    DtServicio dts = it.next();
                    String servicio = dts.getNombre();
                    String proveedor = dts.getNkProveedor();
                    sql = "INSERT INTO help4traveling.promocionesservicios"
                            + " (promocion,proveedorPromocion,servicio,proveedorServicio) VALUES ('"
                            + promo.getNombre() + "','" + promo.getProveedor() + "','" + servicio + "','" + proveedor + "')";
                    try {
                        st = con.createStatement();
                        st.executeUpdate(sql);
                        st.close();
                        System.out.println("INSERTE :)");
                    } catch (SQLException e) {
                        System.out.println("No pude INSERTAR :(");
                        System.err.println(e);
                    }
                }
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("No pude INSERTAR :(");
                    System.err.println(ex);
                }
            }
        } else {
            mensaje = "ERROR: La promo ingresada ya existe.";
        }
        return mensaje;

    }

    // Servidor Central ========================================================
    public String getNkProveedorServicio(String servicio) {
        String prov = null;
        ResultSet rs;
        Statement st;
        try {
            Connection con = Conexion.getInstance().getConnection();
            String sql = "SELECT * FROM help4traveling.servicios WHERE nombre='" + servicio + "'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                prov = rs.getString("proveedor");
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No pude obtener Proveedor :(");
        }
        return prov;
    }

    public String getNkProveedorPromocion(String promocion) {
        String prov = null;
        ResultSet rs;
        Statement st;
        try {
            Connection con = Conexion.getInstance().getConnection();
            String sql = "SELECT * FROM help4traveling.promociones WHERE nombre='" + promocion + "'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                prov = rs.getString("proveedor");
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No pude obtener Proveedor :(");
        }
        return prov;
    }

    public ArrayList<String> listarServiciosDePromociones(String nombre, String prov) {
        ArrayList<String> listaServicios = new ArrayList<String>();
        ResultSet rs;
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql = "SELECT * FROM help4traveling.promocionesservicios WHERE promocion='" + nombre + "' AND proveedorPromocion='" + prov + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String servicio = rs.getString("servicio");
                //String proveedor = rs.getString("proveedorServicio");
                listaServicios.add(servicio);
            }
            rs.close();
            st.close();
            con.close();
            System.out.println("servicios cargados :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar servicios :(");
        }
        return listaServicios;
    }
    
    
        
    public String obtenerPrimeraImagenPromocion(String promocion, String proveedor){
        
        ArrayList<String> servicios = ManejadorServicio.getInstance().listarServiciosDePromociones(promocion, proveedor);
        DtServicio servicio = ManejadorServicio.getInstance().getDtServicio(servicios.get(0),proveedor);
        System.out.println("esta es la imagen del primer servicio  "+servicio.getNombre());
        List<String> imagenes = servicio.getImagenes();
        String imagen = imagenes.get(0);
        //String imagen2 = imagenes.get(1);
        
        System.out.println("esta es la  imagen del primer servicio  "+imagen);
        //System.out.println("esta es la segunda imagen del primer servicio  "+imagen);
        return imagen;
    }

}
