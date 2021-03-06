/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

//import Logica.Date;
//import Logica.Fabrica;
//import Logica.Proveedor;
//import static Logica.Reserva.eEstado.REGISTRADA;
//import Logica.Servicio;
import servidorpublicador.Proveedor;
import servidorpublicador.Servicio;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yaman
 */
public class eliminarCarrito extends HttpServlet {

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

        HttpSession sesion = request.getSession();
        String servicio = request.getParameter("servicio_in");

        System.out.println(servicio);
        float precio = 0;
        int cantidad = 0;

        if (request.getParameter("eliminar") != null) {

            List<Reserva> reservas = (List<Reserva>) sesion.getAttribute("carrito");
            Iterator<Reserva> iter = reservas.iterator();

            Reserva res;
            while (iter.hasNext()) {
                res = iter.next();
                if (servicio.equals(res.getServicio())) {
                    reservas.remove(res);
                    precio = res.getPrecio();
                    cantidad = res.getCantidad();
                    break;
                }
            }
            sesion.setAttribute("carrito", reservas);
            float preciototal = (float) sesion.getAttribute("preciototal");
            sesion.setAttribute("preciototal", preciototal - (precio * cantidad));
            if ((float) sesion.getAttribute("preciototal") == 0) {
                sesion.setAttribute("carrito", null);
            }

        } else if (request.getParameter("comprar") != null) {
            List<Reserva> itemsCarrito = (List<Reserva>) sesion.getAttribute("carrito");
            Iterator<Reserva> iter = itemsCarrito.iterator();

            servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
            servidorpublicador.Publicador port = service.getPublicadorPort();

            Reserva res;
            servidorpublicador.Reserva reserva = new servidorpublicador.Reserva();
            
            reserva.setCliente((String) sesion.getAttribute("nickname"));
            reserva.setEstado(servidorpublicador.EEstado.REGISTRADA);
            reserva.setTotal((float) sesion.getAttribute("preciototal"));
            port.altaReservaWeb(reserva);
            
            
            System.out.println(itemsCarrito.size());
            int contador = 0;
            while (iter.hasNext()) {
                res = iter.next();

                //if (servicio.equals(res.getServicio())) {
                cantidad = res.getCantidad();
                servicio = res.getServicio();
                Proveedor prov = new Proveedor();
                //prov.setEmpresa("yama");
                //prov.setNickname("yama");
                if (port.existePromocion(servicio)){
                    prov.setNickname(port.getNkProveedorPromocion(servicio));
                }
                else{
                    prov.setNickname(port.getNkProveedorServicio(servicio));
                }
                
                //prov.setNombre(port.getNkProveedorServicio(servicio));
                Servicio ofertatype = new Servicio();
                ofertatype.setNombre(servicio);
                ofertatype.setProveedor(prov);
                //Date iniciodate = new Date(res.getFechaini());
                //iniciodate.setAno(2006);
                //iniciodate.setMes(8);
                //iniciodate.setDia(05);
                //Date findate = new Date(res.getFechafin());
                //Date findate = new Date("1988-08-07");

                System.out.println("llegue hasta aca");
                System.out.println("Intento agregar un item a la reserva "+reserva.getId() +"con cantidad " +cantidad + " inicio " + res.getFechaini() + " fin " + res.getFechafin() + " servicio de nombre " + ofertatype.getNombre() + " de proveedor " + ofertatype.getProveedor().toString());
                port.agregarItemReserva(reserva, prov,cantidad, res.getFechaini(),res.getFechafin() , ofertatype);
                //port.agregarItem(reserva,cantidad, iniciodate, findate, ofertatype);
                System.out.println("agregue un item");
                //System.out.println("la cantidad de items es " + reserva.getItems().size());

                contador++;

                //itemsCarrito.remove(res);
            }

            //Fabrica fab = Fabrica.getInstance();
            //fab.getIControladorReserva().altaReservaWeb(reserva);

            
            sesion.setAttribute("preciototal", 0);
            for (int i = 0; i < itemsCarrito.size(); i++) {
                itemsCarrito.remove(i);
            }
            sesion.setAttribute("carrito", null);
        }

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
