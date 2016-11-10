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
 * @author Santiago
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Date {
    //Atributos
    private int dia;
    private int mes;
    private int ano;
    
    //Creadores
    public Date() {
        this.dia = 1;
        this.mes = 1;
        this.ano = 1970;
    }
    
    /*
    // Funciones obsoletas
    public Date(java.sql.Date d){
        this.dia = d.getDay();
        this.mes = d.getMonth();
        this.ano = d.getYear();
    }
    */
    
    public Date(String d){
        String delims = "[ -/]";
        String tokens[] = d.split(delims);
        this.dia = Integer.valueOf(tokens[2]);
        this.mes = Integer.valueOf(tokens[1]);
        this.ano = Integer.valueOf(tokens[0]);
    }
    
    public Date(int dia, int mes, int ano){
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    
    //Geters
    public int getDia(){
        return this.dia;
    }
    
    public int getMes(){
        return this.mes;
    }
    
    public int getAno(){
        return this.ano;
    }
    
    public String getFecha(String sep) {
        return String.valueOf(this.ano)+ sep +
               String.format("%02d",this.mes) + sep +
               String.format("%02d",this.dia);
    }
    
    //Seters
    public void setDia(int dia){
        this.dia = dia;
    }
    
    public void setMes(int mes){
        this.mes = mes;
    }
    
    public void setAno(int ano){
        this.ano = ano;
    }
}