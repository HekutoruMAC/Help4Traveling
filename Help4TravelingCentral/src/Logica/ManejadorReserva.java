package Logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ManejadorReserva {

    private static ManejadorReserva instancia = null;
    private Conexion conexion;
    private String sql;
    private Map<Long, Reserva> reservasId;
    private Map<Integer, List<ItemReserva>> itemsId;

    public static enum eEstado {
        REGISTRADA, CANCELADA, PAGADA, FACTURADA
    };

    private ManejadorReserva() {
        reservasId = new HashMap<>();
        itemsId = new HashMap<>();
    }

    //Constructor
    public static ManejadorReserva getInstance() {
        if (instancia == null) {
            instancia = new ManejadorReserva();
        }
        return instancia;
    }

    //Funciones agregadas
    public void altaReserva(Reserva nueva) {
        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        ResultSet rsId;
        String sid;

        sql = "INSERT INTO help4traveling.reservas (fecha,total,estado,cliente) "
                + "VALUES ('" + nueva.getCreada() + "'," + nueva.getTotal() + ",'" + nueva.getEstado() + "','" + nueva.getCliente() + "')";

        try {
            st = con.createStatement();
            st.executeUpdate(sql);

            try {
                sql = "SELECT MAX(numero) AS id FROM help4traveling.reservas";
                rsId = st.executeQuery(sql);
                rsId.next();
                sid = rsId.getString("id");

                try {
                    if (nueva.getItems().size() > 0) {
                        for (Map.Entry<Integer, ItemReserva> entry : nueva.getItems().entrySet()) {
                            ItemReserva key = entry.getValue();
                            String oferta = key.getOferta().getNombre();
                            String proveedor = key.getOferta().getProveedor().getNombre();
                            String cantidad = String.valueOf(key.getCantidad());
                            String inicio = key.getInicio().getAno() + "-" + key.getInicio().getMes() + "-" + key.getInicio().getDia();
                            String fin = key.getFin().getAno() + "-" + key.getFin().getMes() + "-" + key.getFin().getDia();

                            sql = "INSERT INTO help4traveling.reservasitems (reserva, oferta, proveedorOferta, cantidad, inicio, fin) "
                                    + "VALUES (" + sid + ",'" + oferta + "','" + proveedor + "'," + cantidad + ",'" + inicio + "','" + fin + "')";

                            st.executeUpdate(sql);
                        }
                        con.close();
                        st.close();
                        System.out.println("Reserva creada con exito :)");
                    }
                } catch (SQLException e) {
                    System.out.println("No se pudo insertar item reserva :(");
                    System.out.println(e);
                }
            } catch (SQLException e) {
                System.out.println("No se pudo obtener id :(");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo crear reserva :(");
        }
    }

    public void cancelarReserva(long id) {
        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;

        sql = "DELETE FROM ReservasItems "
                + "WHERE reserva=" + id;
        System.out.println(sql);

        try {
            st = con.createStatement();
            System.out.print("Eliminando items...");
            st.executeUpdate(sql);
            //con.close();
            st.close();
            System.out.println("OK");

            reservasId.remove(id);
            sql = "DELETE FROM Reservas "
                    + "WHERE numero=" + id;

            System.out.println(sql);

            st = con.createStatement();
            System.out.print("Eliminando reserva...");
            st.executeUpdate(sql);
            con.close();
            st.close();
            System.out.println("OK");

            reservasId.remove(id);

        } catch (SQLException e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
        }
    }

    public boolean existeReserva(long id) {
        return reservasId.containsKey(id);
    }

    public List<DtReserva> listarReservas() {
        List<DtReserva> listares = new ArrayList<>();
        Iterator<Reserva> iter = this.reservasId.values().iterator();
        while (iter.hasNext()) {
            Reserva res = iter.next();
            listares.add(res.getDtReserva());
        }
        return listares;
    }

    public ArrayList<DtItemReserva> listarItems(Integer reserva) {
        ArrayList<DtItemReserva> items = new ArrayList<DtItemReserva>();
        ResultSet rs;
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        sql = "SELECT * FROM help4traveling.reservasitems WHERE reserva='" + reserva + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String oferta = rs.getString("oferta");
                String proveedorOferta = rs.getString("proveedorOferta");
                String inicio = rs.getString("inicio");
                String fin = rs.getString("fin");
                String cantidad = rs.getString("cantidad");
                Date iniciodate = new Date(inicio);
                Date findate = new Date(fin);
                Proveedor prov = new Proveedor(proveedorOferta);
                Servicio ofertatype = new Servicio(oferta, prov, null, null, 0, null);

                DtItemReserva item = new DtItemReserva(reserva, Integer.parseInt(cantidad), iniciodate, findate, ofertatype);

                items.add(item);
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No pude obtener categorias :(");
        }
        return items;
    }

    public List<DtItemReserva> listarItemsReserva(Integer reserva) {
        List<DtItemReserva> listaItems = new ArrayList<>();
        List<ItemReserva> itemsReserva = this.itemsId.get(reserva);

        if (itemsReserva != null) {
            Iterator<ItemReserva> iter = itemsReserva.iterator();
            while (iter.hasNext()) {
                ItemReserva item = iter.next();
                listaItems.add(item.getDtItem());
            }
        }

        return listaItems;
    }

    public void modificarEstadoReserva(Integer reserva, String estado) {
        //reservasId.get(reserva).setEstado(Logica.Reserva.eEstado.valueOf(estado));

        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;

        sql = "UPDATE Reservas "
                + "SET estado = '" + estado + "' "
                + "WHERE numero = " + reserva;
        System.out.println(sql);

        try {
            st = con.createStatement();
            System.out.print("Modificando estado...");
            st.executeUpdate(sql);
            con.close();
            st.close();
            System.out.println("OK");

        } catch (SQLException e) {
            System.out.println("ERROR");
            System.out.println(e.getMessage());
        }
    }

    public List<DtReserva> getDtReservas() {
        List<DtReserva> listaDtRes = new LinkedList<>();
        Iterator<Reserva> iter = this.reservasId.values().iterator();
        while (iter.hasNext()) {
            Reserva res = iter.next();
            DtReserva dtRes = res.getDtReserva();
            listaDtRes.add(dtRes);
        }
        return listaDtRes;
    }

    /*
    public List<DtItemReserva> getDtItems() {
        List<DtItemReserva> listaDtItems = new LinkedList<>();
        Iterator<ItemReserva> iterador = this.itemsId.values().iterator();
        while (iterador.hasNext()) {
            ItemReserva item = iterador.next();
            DtItemReserva dtItem = item.getDtItem();
            listaDtItems.add(dtItem);
        }
        return listaDtItems;
    }
     */
    public List<DtItemReserva> getDtItems() {
        List<DtItemReserva> listaDtItems = new LinkedList<>();
        Iterator<List<ItemReserva>> iterlista = this.itemsId.values().iterator();
        while (iterlista.hasNext()) {
            List<ItemReserva> items = iterlista.next();
            Iterator<ItemReserva> iteritem = items.iterator();
            while (iteritem.hasNext()) {
                ItemReserva item = iteritem.next();
                DtItemReserva dtItem = item.getDtItem();
                listaDtItems.add(dtItem);
            }
        }
        return listaDtItems;
    }

    public void setReservasDB() {
        ResultSet rsReservas;

        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;

        if (con != null) {
            sql = "SELECT * FROM help4traveling.reservas";

            try {
                st = con.createStatement();
                rsReservas = st.executeQuery(sql);

                System.out.print("Cargando Reservas: ");

                while (rsReservas.next()) {

                    Long num = rsReservas.getLong("numero");
                    Date creada = new Date(rsReservas.getString("fecha"));
                    double total = rsReservas.getDouble("total");
                    String estado = rsReservas.getString("estado");
                    String cliente = rsReservas.getString("cliente");
                    Map<Integer, ItemReserva> mapa = new HashMap();

                    Reserva nueva = new Reserva(creada, Reserva.eEstado.valueOf(estado), total, cliente, mapa);

                    /* CARGAR RESERVASITEMS */
                    nueva.setId(num);       //Temporal?
                    Long id = nueva.getId();
                    reservasId.put(id, nueva);
                    System.out.print(nueva.getId() + " ");
                }
                System.out.println();
                rsReservas.close();
                con.close();
                st.close();

                System.out.println("Reservas cargadas!");
            } catch (SQLException e) {
                System.out.println("Reservas no cargadas!");
                System.out.println(e.getMessage());
            }

        }
    }

    public void setItemsDB() {
        ResultSet rsItems;
        itemsId.clear();

        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;

        if (con != null) {
            sql = "SELECT * FROM help4traveling.reservasitems";

            try {
                st = con.createStatement();
                rsItems = st.executeQuery(sql);

                System.out.print("Cargando Items: ");

                while (rsItems.next()) {

                    int reserva = rsItems.getInt("reserva");
                    int cantidad = rsItems.getInt("cantidad");

                    Date inicio = new Date(rsItems.getString("inicio"));
                    Date fin = new Date(rsItems.getString("fin"));

                    String nomoferta = rsItems.getString("oferta");
                    String nomprov = rsItems.getString("proveedorOferta");
                    //Proveedor proveedor = ManejadorProveedor.getInstance().obtenerProveedor(nomprov);
                    Oferta oferta = new Servicio();
                    oferta.setNombre(nomoferta);

                    ItemReserva nuevo = new ItemReserva(reserva, cantidad, inicio, fin, oferta);

                    if (itemsId.containsKey(reserva)) {
                        itemsId.get(reserva).add(nuevo);
                    } else {
                        List<ItemReserva> item = new ArrayList();
                        item.add(nuevo);
                        itemsId.put(reserva, item);
                    }
                    System.out.print(nuevo.getId() + " ");

                }
                System.out.println();
                rsItems.close();
                con.close();
                st.close();

                System.out.println("Items cargados!");
            } catch (SQLException e) {
                System.out.println("Items no cargados!");
                System.out.println(e.getMessage());
            }
        }

    }

    public ArrayList<DtReserva> listarReservasCliente(DtUsuario user) {
        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        ResultSet rsReservasCliente;
        sql = "SELECT * FROM help4traveling.reservas WHERE cliente='" + user.getNickname() + "'";

        try {
            st = con.createStatement();
            rsReservasCliente = st.executeQuery(sql);
            while (rsReservasCliente.next()) {
                String id = rsReservasCliente.getString("numero");
                String estado = rsReservasCliente.getString("estado");
                String fecha = rsReservasCliente.getString("fecha");
                String cliente = rsReservasCliente.getString("cliente");
                String precio = rsReservasCliente.getString("total");

                long idint = Integer.parseInt(id);
                double precioint = Integer.parseInt(precio);
                Reserva nueva = new Reserva(/*idint,"REGISTRADA", cliente,null*/);
                nueva.setCliente(cliente);
                nueva.setId(idint);

                //nueva.setEstado(Reserva.eEstado.REGISTRADA);
                nueva.setEstado(Reserva.eEstado.valueOf(estado));

                nueva.setTotal(precioint);
                this.reservasId.put(idint, nueva);

            }
            rsReservasCliente.close();
            con.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("No hubo resultado");
        }

        ArrayList<DtReserva> listaReservasCliente = new ArrayList<>();
        Iterator<Reserva> iter = this.reservasId.values().iterator();
        while (iter.hasNext()) {
            Reserva res = iter.next();
            listaReservasCliente.add(res.getDtReserva());
        }

        reservasId.values().clear();
        return listaReservasCliente;

    }

    // Servidor Central ========================================================
    public ArrayList<DtReserva> listarReservasUsuario(String cli) {
        ArrayList<DtReserva> ReservaxUsuario = null;
        ResultSet rs;
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        sql = "SELECT * FROM help4traveling.reservas where cliente ='" + cli + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            ReservaxUsuario = new ArrayList<DtReserva>();
            while (rs.next()) {
                String numero = rs.getString("numero");
                String fecha1 = rs.getString("fecha");
                String total = rs.getString("total");
                String estado = rs.getString("estado");

                String partes[] = fecha1.split("-");

                Date fecha = new Date(Integer.valueOf(partes[2]), Integer.valueOf(partes[1]), Integer.valueOf(partes[0]));
                Map<Integer, ItemReserva> items = null;

                DtReserva nueva = new DtReserva(Long.parseLong(numero), fecha, Reserva.eEstado.valueOf(estado), Double.parseDouble(total), cli, items);
                ReservaxUsuario.add(nueva);
            }
            rs.close();
            con.close();
            st.close();
            System.out.println("usuarios  cargados :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar usuarios :(");
        }
        return ReservaxUsuario;
    }

    public ArrayList<DtReserva> listarReservasProveedor(String prov) {
        ArrayList<DtReserva> ReservaxProveedor = null;
        ResultSet rs;
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        sql = "SELECT * FROM help4traveling.reservas WHERE numero IN (SELECT reserva FROM help4traveling.reservasitems WHERE proveedorOferta ='" + prov + "' or numero IN (SELECT reserva FROM help4traveling.reservasitemspromociones WHERE proveedorServicio ='" + prov + "'))";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            ReservaxProveedor = new ArrayList<DtReserva>();
            while (rs.next()) {
                String numero = rs.getString("numero");
                String fecha1 = rs.getString("fecha");
                String total = rs.getString("total");
                String estado = rs.getString("estado");
                String cliente = rs.getString("cliente");

                String partes[] = fecha1.split("-");

                Date fecha = new Date(Integer.valueOf(partes[2]), Integer.valueOf(partes[1]), Integer.valueOf(partes[0]));
                Map<Integer, ItemReserva> items = null;

                DtReserva nueva = new DtReserva(Long.parseLong(numero), fecha, Reserva.eEstado.valueOf(estado), Double.parseDouble(total), cliente, items);
                ReservaxProveedor.add(nueva);
            }
            rs.close();
            con.close();
            st.close();
            System.out.println("Reservas  cargadas :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar reservas :(");
        }
        return ReservaxProveedor;
    }

    public void altaReservaWeb(Reserva nueva) {
        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        ResultSet rsId;
        String sid;
        Calendar c = Calendar.getInstance();
        int dia = c.get(Calendar.DATE);
        int mes = c.get(Calendar.MONTH) + 1;
        int anio = c.get(Calendar.YEAR);
        String creada = Integer.toString(anio) + "-" + Integer.toString(mes) + "-" + Integer.toString(dia);
        String sql = "INSERT INTO help4traveling.reservas (fecha,total,estado,cliente) "
                + "VALUES ('" + creada + "'," + nueva.getTotal() + ",'" + nueva.getEstado() + "','" + nueva.getCliente() + "')";

        try {
            st = con.createStatement();
            st.executeUpdate(sql);

            try {
                sql = "SELECT MAX(numero) AS id FROM help4traveling.reservas";
                rsId = st.executeQuery(sql);
                rsId.next();
                sid = rsId.getString("id");

                try {
                    if (nueva.getItems().size() > 0) {
                        System.out.println("entre al if 1");
                        for (Map.Entry<Integer, ItemReserva> entry : nueva.getItems().entrySet()) {
                            System.out.println("entre al for 1");
                            ItemReserva key = entry.getValue();
                            String oferta = key.getOferta().getNombre();
                            String proveedor = "";
                            Fabrica fab = Fabrica.getInstance();
                            if (fab.getIControladorServicio().existeServicio(oferta)) {
                                proveedor = fab.getIControladorServicio().getNkProveedorServicio(oferta);
                            } else {
                                proveedor = fab.getIControladorServicio().getNkProveedorPromocion(oferta);
                            }
                            String cantidad = String.valueOf(key.getCantidad());
                            String inicio = key.getInicio().getAno() + "-" + key.getInicio().getMes() + "-" + key.getInicio().getDia();
                            String fin = key.getFin().getAno() + "-" + key.getFin().getMes() + "-" + key.getFin().getDia();
                            System.out.println(sid + " " + oferta + " " + proveedor + " " + cantidad + " " + inicio + " " + fin);
                            sql = "INSERT INTO help4traveling.reservasitems (reserva, oferta, proveedorOferta, cantidad, inicio, fin) "
                                    + "VALUES (" + sid + ",'" + oferta + "','" + proveedor + "'," + cantidad + ",'" + inicio + "','" + fin + "')";

                            st.executeUpdate(sql);
                        }
                        con.close();
                        st.close();
                        System.out.println("Reserva creada con exito :)");
                    }
                } catch (SQLException e) {
                    System.out.println("No se pudo insertar item reserva :(");
                    System.err.println(e);
                }
            } catch (SQLException e) {
                System.out.println("No se pudo obtener id :(");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo crear reserva :(");
            System.err.println(e);
        }
    }
    
    //Factura la primicion en la tabla itemsreserva siempre y cuando todos los items de la promocion estan facturados.
    private void FacturarPromocion(int reserva) {
        Connection con = Conexion.getInstance().getConnection();
        String SQL = "";
        Statement st ;
        ResultSet rsItemsNF;

        try {
            SQL = "SELECT oferta, proveedorOferta "
                + "FROM reservasitems i "
                + "WHERE oferta not in (SELECT oferta "
                                     + "FROM reservasitemspromociones p "
                                     + "WHERE p.reserva = i.reserva AND p.oferta = i.oferta AND p.proveedorOferta = i.proveedorOferta AND facturada = false) "
                                     + "AND proveedorOferta = 'PROMOCION' AND reserva = " + reserva + " AND facturada = false ";
            st = con.createStatement();
            rsItemsNF = st.executeQuery(SQL);
            if (rsItemsNF.next()) {
                try {
                    SQL = "UPDATE reservasitems SET facturada = true WHERE reserva = " + reserva + " AND oferta = '" + rsItemsNF.getString("oferta") + "' AND proveedorOferta = '" + rsItemsNF.getString("proveedorOferta") + "'";
                    st.executeUpdate(SQL);
                } catch (SQLException e) {
                    System.out.println("Error al facturar el item promocion");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar item promocion");
        }
    }
    
    //Cambia el estado a facturada de todos los items de un proveedor dentro de una reserva.
    public void FacturarReserva(int reserva, String proveedorServicio) {
        Connection con = Conexion.getInstance().getConnection();
        String SQL = "";
        Statement st ;

        try {
            SQL = "UPDATE reservasitems "
                + "SET facturada = true "
                + "WHERE reserva = " + String.valueOf(reserva) + " AND proveedorOferta = '" + proveedorServicio + "'";
            st = con.createStatement();
            st.executeUpdate(SQL);
            
            try {
                SQL = "UPDATE reservasitemspromociones "
                    + "SET facturada = true "
                    + "WHERE reserva = " + String.valueOf(reserva) + " AND proveedorServicio = '" + proveedorServicio + "'";
                st = con.createStatement();
                st.executeUpdate(SQL);
                FacturarPromocion(reserva);
            } catch (SQLException e) {
                System.out.println(SQL);
                System.out.println("No se pudo facturar las promociones!");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo facturar los servicios!");
        }
    }

    //Cambia el estado a facturada de un itemreserva o itemreservapromocion (dependiendo de si es promocion o no)
    public void FacturarItemReserva(int reserva, String servicio, String proveedorServicio, String promocion) {
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        if (promocion == "") {
            String SQL = "UPDATE reservasitems "
                    + "SET facturada = true "
                    + "WHERE reserva = " + String.valueOf(reserva) + " AND servicio = '" + servicio + "' AND proveedorServicio = '" + proveedorServicio + "'";
        } else {
            String SQL = "UPDATE reservasitemspromociones "
                    + "SET facturada = true "
                    + "WHERE reserva = " + String.valueOf(reserva) + " AND oferta = '" + promocion + "' AND servicio = '" + servicio + "' AND proveedorServicio = '" + proveedorServicio + "'";
        }

        try {
            st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("No se pudo facturar!");
        }
    }
    
    //Devuelve la cantidad de items no facturados de un proveedor en una reserva, ya sean de una promocion o simplemente servicios.
    public int EstadoParcialReserva(int reserva, String proveedor) {
        int cantidad = 0;
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String  SQL;
        ResultSet rsItemsNF;
        
        try {
            SQL = "SELECT oferta FROM reservasitems WHERE reserva = " + reserva + " AND proveedorOferta = '" + proveedor + "' AND facturada = false "
                + "UNION "
                + "SELECT oferta FROM reservasitemspromociones WHERE reserva = " + reserva + " AND proveedorServicio = '" + proveedor + "' AND facturada = false";
            st = con.createStatement();
            rsItemsNF = st.executeQuery(SQL);
            while (rsItemsNF.next()) {
                cantidad += 1;
            }
        } catch (SQLException e) {
            System.out.println("No se pudo verificar los items"); 
        }
        return cantidad;
    }
    
    //Devuelve true en caso de que todos los items de la reserva hayan sido facturados independientemente del proveedor, y false en caso contrario.
    public boolean ItemsFacturados(int reserva) {
        boolean Facturados = false;
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String  SQL;
        ResultSet rsItemsNF;
        
        try {
            SQL = "SELECT oferta FROM reservasitems WHERE reserva = " + reserva + " AND facturada = false LIMIT 1";
            st = con.createStatement();
            rsItemsNF = st.executeQuery(SQL);
            if (rsItemsNF.next()) {
                Facturados = false;
            }else {
                Facturados = true;
            }
        } catch (SQLException e) {
            System.out.println("No se pudo verificar los items"); 
        }
        return Facturados;
    }

    public void agregarItemReserva(Reserva nueva, Oferta oferta, Proveedor proveedor, int cantidad, Date inicio, Date fin) {
        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        ResultSet rsId, rsItemsPromo;
        String sid;

        /*sql = "INSERT INTO help4traveling.reservas (fecha,total,estado,cliente) "
                + "VALUES ('" + nueva.getCreada() + "'," + nueva.getTotal() + ",'" + nueva.getEstado() + "','" + nueva.getCliente() + "')";

        try {
            st = con.createStatement();
            st.executeUpdate(sql);*/
        try {
            st = con.createStatement();
            sql = "SELECT MAX(numero) AS id FROM help4traveling.reservas";
            rsId = st.executeQuery(sql);
            rsId.next();
            sid = rsId.getString("id");
            String iniciostr = Integer.toString(inicio.getAno())+"-"+Integer.toString(inicio.getMes())+"-"+Integer.toString(inicio.getDia());
            String finstr = Integer.toString(fin.getAno())+"-"+Integer.toString(fin.getMes())+"-"+Integer.toString(fin.getDia());
            
            try {
                
                System.out.println(sid);
              
                String ofertastr = oferta.getNombre();
                String proveedorstr = proveedor.getNickname();
                String cantidadstr = Integer.toString(cantidad);
                System.out.println(ofertastr);
                System.out.println(proveedorstr);
                System.out.println(cantidadstr);
                System.out.println(iniciostr);
                System.out.println(finstr);
                
                
                sql = "INSERT INTO help4traveling.reservasitems (reserva, oferta, proveedorOferta, cantidad, inicio, fin) "
                        + "VALUES (" + sid + ",'" + ofertastr + "','"+proveedorstr+"','" + cantidadstr + "','" + iniciostr + "','" + finstr + "')";
                st.executeUpdate(sql);
                
                try {
                    if (proveedorstr.equals("promocion")) {
                        sql = "SELECT * FROM promocionesservicios WHERE promocion = '" + ofertastr + "' AND proveedorPromocion = 'promocion'";
                        st = con.createStatement();
                        rsItemsPromo = st.executeQuery(sql);

                        while (rsItemsPromo.next()) {
                            String sServicio = rsItemsPromo.getString("servicio");
                            String sProveedor = rsItemsPromo.getString("proveedorServicio");

                            sql = "INSERT INTO reservasitemspromociones (reserva, oferta, proveedorOferta, servicio, proveedorServicio, facturada) VALUE (" + sid + ",'" + ofertastr + "','" + proveedorstr + "','" + sServicio + "','" + sProveedor + "',false)";
                            st = con.createStatement();
                            st.executeUpdate(sql);                        
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Error al insertar items de la promocion en la reserva");
                }

                con.close();
                st.close();
                System.out.println("Item agregado con exito :)");

            } catch (SQLException e) {
                System.out.println("No se pudo insertar item reserva :(");
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.out.println("No se pudo obtener id :(");
            System.out.println(e);
        }

    }
}
