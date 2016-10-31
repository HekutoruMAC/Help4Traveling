/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import static Logica.Reserva.eEstado.CANCELADA;
import static Logica.Reserva.eEstado.FACTURADA;
import static Logica.Reserva.eEstado.PAGADA;
import static Logica.Reserva.eEstado.REGISTRADA;
import java.util.Map;

/**
 *
 * @author Santiago
 */
public class DtReserva {

    //Atributos
    private long id;
    private Reserva.eEstado estado;
    private Date creada;
    private double total;
    private String cliente;
    private Map<Integer, ItemReserva> items;
    private Conexion conexion;
    private String sql;

    //Creadores
    public DtReserva(long id, Date creada, Reserva.eEstado estado, double total, String cliente, Map<Integer, ItemReserva> items) {
        this.id = id;
        this.estado = estado;
        this.creada = creada;
        this.total = total;
        this.cliente = cliente;
        this.items = items;
    }

    //Geters
    public long getId() {
        return this.id;
    }

    public Reserva.eEstado getEstado() {
        String estado = this.estado.toString();
        Reserva.eEstado estadoret = null;
        /*
        ////conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        ResultSet rsEstado;

        String sql1 = "SELECT * FROM reservas WHERE numero='" + this.id + "'";
        try {
            st = con.createStatement();

            rsEstado = st.executeQuery(sql1);
            rsEstado.next();
            estado = rsEstado.getString("estado");

            rsEstado.close();
            con.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("No tiene Estado :(");
            System.err.println(e.getMessage());
        }
         */

        switch (estado) {
            case "REGISTRADA":
                estadoret = REGISTRADA;
                break;
            case "CANCELADA":
                estadoret = CANCELADA;
                break;
            case "FACTURADA":
                estadoret = FACTURADA;
                break;
            case "PAGADA":
                estadoret = PAGADA;
                break;
        }
        return estadoret;
    }

    public Date getCreada() {
        return this.creada;
    }

    public String getCliente() {
        return this.cliente;
    }

    public double getTotal() {
        return this.total;
    }

    public Map<Integer, ItemReserva> getItems() {
        return items;
    }
}
