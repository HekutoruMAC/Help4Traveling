/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import java.util.Map;
import java.util.TreeMap;

public class Reserva {
    public static enum eEstado{REGISTRADA,CANCELADA,PAGADA,FACTURADA};
    
    //Atributos
    private long id;
    private eEstado estado;
    private Date creada;
    private double total;
    private String cliente;
    private Map<Integer, ItemReserva> items;
    
    //Creadores
    public Reserva(){
        this.id = Reserva.proximoId();
        this.creada = new Date(16,12,1994);
        this.estado = eEstado.REGISTRADA;
        this.total = 0;
        this.cliente = "";
        this.items = new TreeMap<Integer, ItemReserva>();
    }
    
    public Reserva(Date creada, eEstado estado, double total, String cliente, Map<Integer, ItemReserva> items){
        this.id = Reserva.proximoId();
        this.estado = estado;
        this.creada = creada;
        this.total = total;
        this.cliente = cliente;
        this.items = items;
    }
    
    //Geters
    public long getId(){
        return this.id;
    }
    
    public eEstado getEstado(){
        return this.estado;
    }
    
    public String getCreada(){
        return String.valueOf(this.creada.getAno()) + "-" + String.valueOf(this.creada.getMes()) + "-" + String.valueOf(this.creada.getDia());
    }
    
    public String getCliente(){
        return this.cliente;
    }
    
    public double getTotal(){
        return this.total;
    }

    public Map<Integer, ItemReserva> getItems() {
        return items;
    }
    
    
    
    //Seters    
    public void setId(long id){
        this.id = id;
    }
    
    public void setEstado(eEstado estado){
        this.estado = estado;
    }
    
    public void setCreada(Date creada){
        this.creada = creada;
    }
    
    public void setCliente(String cliente){
        this.cliente = cliente;
    }
    
    public void setTotal(double total){
        this.total = total;
    }

    public void setItems(Map<Integer, ItemReserva> items) {
        this.items = items;
    }    
    
    
    //-----> Metodos de clase agregados <-----
    
    //Por ver como implementar ersta funcion ya que el ID al ser identiti en la base de datos no se conoce hasta insertar el objeto.
    private static int proximoId() {
        //Aca se va a llamar a la base de datos para buscar el proximo ID disponible
        return 0;
    }
    
    
    //-----> Metodos de instancia agregados <-----
    
    //Agrega un nuevo item a la lista. El idItem es el identificador del item dentro de la reserva y el idOferta el el identificador del servicio o promocion
    public void agregarItem(int cantidad, Date fi, Date ff, Oferta oferta) {
        int idItem;
        
        if (this.items == null) {
            idItem = 1;
        }else {
            idItem = this.items.size() + 1;
        }
        
        ItemReserva nuevoItem = new ItemReserva(idItem,cantidad,fi,ff,oferta);
        this.items.put(idItem, nuevoItem);
    }
    
    public DtReserva getDtReserva() {
        DtReserva dtR = new DtReserva(this.id, this.creada, this.estado, this.total, this.cliente, this.items);
        return dtR;
    }
}
