/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author Leonardo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DtItemReserva {

    private int id;
    private int cantidad;
    private Date inicio;
    private Date fin;
    private Oferta oferta;

    public DtItemReserva(int id, int cantidad, Date inicio, Date fin, Oferta oferta) {
        this.id = id;
        this.cantidad = cantidad;
        this.inicio = inicio;
        this.fin = fin;
        this.oferta = oferta;
    }

    //Geters
    public int getId() {
        return this.id;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public Date getInicio() {
        return this.inicio;
    }

    public Date getFin() {
        return this.fin;
    }

    public Oferta getOferta() {
        return this.oferta;
    }

}
