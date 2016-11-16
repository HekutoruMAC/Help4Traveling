/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servidorpublicador.DataItemsReservasArrayList;
import servidorpublicador.DtItemReserva;
import servidorpublicador.DtUsuario;

/**
 *
 * @author Hekutoru
 */
public class Email extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws javax.mail.internet.AddressException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, AddressException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Obtener parametros de reserva
            String reserva = request.getParameter("reserva");
            String cliente = request.getParameter("cliente");
            String total = request.getParameter("total");

            // Obtener datos de cliente e items de reserva
            DtUsuario dtu = getDtUsuario(cliente);
            String nombre = dtu.getNombre();
            String apellido = dtu.getApellido();
            String servicios = "";
            String promos = "";
            List<DtItemReserva> dtItems = listarItems(Integer.parseInt(reserva)).getItems();
            Iterator<DtItemReserva> iter = dtItems.iterator();
            DtItemReserva ItRes;
            while (iter.hasNext()) {
                ItRes = iter.next();
                int cant = ItRes.getCantidad();
                String oferta = ItRes.getOferta().getNombre();
                String proveedor = ItRes.getOferta().getProveedor().getNickname();
                String item = "-Nombre: " + oferta
                        + " - Cantidad: " + cant
                        + " - $:x"
                        + " - Proveedor: " + proveedor + "<br/>";
                if (existeServicio(oferta)) {
                    servicios += item;
                } else {
                    promos += item;
                }
            }

            // Configurar mensaje
            String to = dtu.getCorreo();
            String from = "facturacion@help4traveling.com";
            String host = "localhost";
            Properties props = System.getProperties();
            props.setProperty("mail.smtp.host", host);
            props.setProperty("mail.user", "myuser");
            props.setProperty("mail.password", "mypwd");
            Session session = Session.getDefaultInstance(props);

            // Obtener fecha y hora actual
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            String fechayhora = sdf.format(cal.getTime());

            // Ensamblar mensaje
            String cuerpo = "<p>Estimado/a <strong>" + nombre + " " + apellido + "</strong>. "
                    + "Su compra ha sido facturada con &eacute;xito:</p>"
                    + "<p>---Detalles de la Compra</p>";
            if (!servicios.isEmpty()) {
                cuerpo += "<p>-<em>Servicios</em>:</p>" + servicios;
            }
            if (!servicios.isEmpty()) {
                cuerpo += "<p>-<em>Promociones</em>:</p>" + promos;
            }
            cuerpo += "<p>---Precio total: $ " + total + "</p>"
                    + "<p>Gracias por preferirnos, Saludos.<br/>"
                    + "Help4Traveling</p>";

            try {
                // Crear mensaje
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Ingresar contenido
                message.setSubject("[Help4Traveling] [" + fechayhora + "]");
                message.setContent(cuerpo, "text/html");

                // Enviar mensaje
                Transport.send(message);
                response.sendRedirect("Usuario.jsp");
                System.out.println("Mensaje enviado correctamente.");
            } catch (MessagingException mex) {
                response.sendRedirect("Usuario.jsp");
                System.out.println("El mensaje no pudo enviarse.");
            }
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
        } catch (MessagingException ex) {
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
        } catch (MessagingException ex) {
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

    private static DtUsuario getDtUsuario(java.lang.String arg0) {
        servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
        servidorpublicador.Publicador port = service.getPublicadorPort();
        return port.getDtUsuario(arg0);
    }

    private static DataItemsReservasArrayList listarItems(int arg0) {
        servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
        servidorpublicador.Publicador port = service.getPublicadorPort();
        return port.listarItems(arg0);
    }

    private static boolean existeServicio(java.lang.String arg0) {
        servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
        servidorpublicador.Publicador port = service.getPublicadorPort();
        return port.existeServicio(arg0);
    }

}
