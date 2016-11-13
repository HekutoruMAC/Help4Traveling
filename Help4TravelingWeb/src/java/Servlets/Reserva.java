/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

/**
 *
 * @author yaman
 */
  public class Reserva {

        private String servicio;
        private float precio;
        private int cantidad;
        private String fechaini;
        private String fechafin;
        
        public void Reserva(){
            
        }

        public void Reserva(String servicio, float precio, int cantidad,String fechaini,String fechafin) {
            this.servicio = servicio;
            this.precio = precio;
            this.cantidad = cantidad;
            this.fechaini=fechaini;
            this.fechafin=fechafin;
        }
        public void setServicio(String servicio){
            this.servicio= servicio;
        }
        
        public void setPrecio(float precio){
            this.precio= precio;
        }
        public void setCantidad(int cantidad){
            this.cantidad= cantidad;
        }
        
        public void setFechaini(String fechaini){
            this.fechaini = fechaini;
            
        }
        
        public void setFechafin(String fechafin){
            this.fechafin = fechafin;
            
        }
        
        public String getServicio(){
            return servicio;
        }
        
        public float getPrecio(){
            return precio;
        }
        public int getCantidad(){
            return cantidad;
        }
        
        public String getFechaini(){
            return fechaini;
        }
        
         public String getFechafin(){
            return fechafin;
        }
    }
