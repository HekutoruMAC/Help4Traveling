/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yaman
 */
public class FacturarReserva extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            String action = request.getParameter("action");
            if (action.equals("Item")) {
                FacturarItem(request, response);
            } else if (action.equals("Reserva")) {
                FacturarReserva(request, response);
            }
        } catch (SQLException ex) {
            response.sendRedirect("Movil.Reservas.jsp");
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Comprobacion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Comprobacion.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    //Factura la reserva completa. Es decir cambia el estado de la reserva.
    private void FacturarReserva(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {  
        try {
            Integer reserva = Integer.parseInt(request.getParameter("reserva"));
            String proveedorServicio = request.getParameter("servicioProveedor");

            servidorpublicador.PublicadorService servicio = new servidorpublicador.PublicadorService();
            String proveedorServicio2 = request.getParameter("servicioproveedor");
            servidorpublicador.Publicador port = servicio.getPublicadorPort();
            port.facturarReserva(reserva, proveedorServicio);
            if (port.itemsFacturados(reserva)) {
                //Cambio estado
                port.actualizarEstadoDeReserva(reserva, "FACTURADA");
                
                //Mando mail
                String cliente = request.getParameter("cliente");
                Double total = Double.parseDouble(request.getParameter("total"));
                response.sendRedirect("Email?reserva=" + reserva.toString() + "&cliente=" + cliente + "&total=" + total.toString());
            }else {
                response.sendRedirect("Usuario.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("Movil.Reservas.jsp");
        }
    }
    
    //Factura un item de una reserva.
    private void FacturarItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        Integer reserva = Integer.parseInt(request.getParameter("reserva"));
        String servicio = request.getParameter("servicio");
        String proveedorServicio = request.getParameter("proveedorServicio");
        String promocion = request.getParameter("proveedorServicio");
        
        servidorpublicador.PublicadorService publicador = new servidorpublicador.PublicadorService();
        servidorpublicador.Publicador port = publicador.getPublicadorPort();
        port.facturarItemReserva(reserva, servicio, proveedorServicio, promocion);
        response.sendRedirect("Movil.Reservas.jsp");
    }

}
