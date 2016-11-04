/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Servlets.Reserva;
import java.util.Iterator;

/**
 *
 * @author yaman
 */
public class agregarCarrito extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String servicio = request.getParameter("servicio_in");
        String precio = request.getParameter("precio_in");
        System.out.println(servicio);
        System.out.println(precio);
        String cantidad = request.getParameter("cantidad_in");
        System.out.println(cantidad);
        String fechaini = request.getParameter("fechaIni");
        System.out.println(fechaini);
        String fechafin = request.getParameter("fechaFin");
        System.out.println(fechafin);
        Reserva reserva = new Reserva();
        reserva.setServicio(servicio);
        reserva.setPrecio(Float.parseFloat(precio));
        reserva.setCantidad(Integer.parseInt(cantidad));
        reserva.setFechaini(fechaini);
        reserva.setFechafin(fechafin);
        
        

        List<Reserva> reservas = new ArrayList<Reserva>();
        boolean existe = false;
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("carrito") != null) {
            reservas = (List<Reserva>) sesion.getAttribute("carrito");
            float preciototal = (float)sesion.getAttribute("preciototal");
            sesion.setAttribute("preciototal",preciototal + Integer.parseInt(cantidad)*Float.parseFloat(precio));
            Iterator<Reserva> iter = reservas.iterator();
            Reserva res;
            while (iter.hasNext()) {
                res = iter.next();
                if (servicio.equals(res.getServicio())) {
                    int cant = Integer.parseInt(cantidad) + res.getCantidad();
                    reserva.setCantidad(cant);
                    reservas.remove(res);
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                int contador = (int) (sesion.getAttribute("contador"));
                sesion.setAttribute("contador", contador + 1);
            }

        }
        else{
            sesion.setAttribute("contador",1);
            sesion.setAttribute("preciototal",Integer.parseInt(cantidad)*Float.parseFloat(precio));
        }
        reservas.add(reserva);
        sesion.setAttribute("carrito", reservas);

        System.out.println(sesion.getAttribute("preciototal"));
        response.sendRedirect("Carrito.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
