/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Fabrica;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yaman
 */
public class Validacion extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String nickname = request.getParameter("nickname_ingreso");
        String password = request.getParameter("password_ingreso");
        String recordar = request.getParameter("Recordarme");

        System.out.println(nickname + "   " + password + "  " + recordar);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("nickname", nickname);
        sesion.setAttribute("password", password);
        sesion.setAttribute("inicia", "true");

        //Fabrica fab = Fabrica.getInstance();
        servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
        servidorpublicador.Publicador port = service.getPublicadorPort();
        if (port.autenticacion(nickname,password)){
       // if (fab.getIControladorUsuario().Autenticacion(sesion)) {
            sesion.setAttribute("mensaje", "Bienvenido usuario " + nickname);

            boolean esProv = port.existeProveedor(nickname);
            sesion.setAttribute("esProv", esProv);

            if (!(request.getParameter("Recordarme") == null)) {
                if (recordar.equals("on")) {
                    Cookie Galleta = new Cookie("nick", nickname);
                    Galleta.setMaxAge(60 * 60 * 24 * 7);
                    response.addCookie(Galleta);
                    System.out.println("se cre la galleta con nombre " + Galleta.getName());
                    System.out.println("entre al recordar");
                    //falta terminar la gestion de las cookies
                }
            }
            response.sendRedirect("index.jsp");
        } else {
            sesion.setAttribute("mensaje", "Usuario o contraseña incorrectos");
            response.sendRedirect("InicioSesion.jsp");
        }
        //response.sendRedirect("InicioSesion.jsp");
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
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Validacion.class.getName()).log(Level.SEVERE, null, ex);
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

}
