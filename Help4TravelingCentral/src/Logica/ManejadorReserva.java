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
        sql = "SELECT * FROM help4traveling.reservas WHERE numero IN (SELECT reserva FROM help4traveling.reservasitems WHERE proveedorOferta ='" + prov + "')";
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

                DtReserva nueva = new DtReserva(Long.parseLong(numero), fecha, Reserva.eEstado.valueOf(estado), Double.parseDouble(total), prov, items);
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

}
