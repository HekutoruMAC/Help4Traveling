/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servidorpublicador.DataItemsReservasArrayList;
import servidorpublicador.DtItemReserva;
import servidorpublicador.DtPromocion;
import servidorpublicador.DtServicio;
import servidorpublicador.DtUsuario;

/**
 *
 * @author Hekutoru
 */
public class PDF extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            doPost(request, response);
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            response.setContentType("application/pdf");
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        
          
        try  {
            //obtengo datos de cliente,  reserva y total
            String rreserva = request.getParameter("reserva");
            String rcliente = request.getParameter("cliente");
            String rtotal = request.getParameter("total");
            //String rtipo = request.getParameter("esProv");
            
            // Obtener datos de cliente e items de reserva
            DtUsuario dtu = getDtUsuario(rcliente);
            String nombre = dtu.getNombre();
            String apellido = dtu.getApellido();
            String servicios = "";
            String promos = "";
            java.util.List<DtItemReserva> dtItems = listarItems(Integer.parseInt(rreserva)).getItems();
            Iterator<DtItemReserva> iter = dtItems.iterator();
            DtItemReserva dtItem;


            
            // Crear y abrir documento
            
            String HomeDeUSuario =System.getProperty("user.home");
            
            String ruta=HomeDeUSuario+"/Factura Reserva "+rreserva+".pdf";
            
            FileOutputStream archivo = new FileOutputStream(ruta);
            
            PdfWriter writer = PdfWriter.getInstance(document, archivo);
            
            document.open();
            
            Date date = new Date();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fecha= dateFormat.format(date);
           
            // Agregar marcador inicial
            
            // Crear y agregar párrafo simple
            /*Paragraph paragraph1 = new Paragraph();
            Image imagen= Image.getInstance("http://localhost:8084/Help4TravelingWeb/img/logo-icon2.png");
            //Image imagen= Image.getInstance("../img/logo-icon2.png");
            imagen.scaleAbsolute(200f, 200f); 
            
            imagen.setAbsolutePosition(10, 650);
            imagen.setSpacingAfter(20);
            paragraph1.add(imagen);
            document.add(paragraph1);*/
            //fecha
            Paragraph fecha1 =new Paragraph(fecha);
            
            //encabezado
            Paragraph futuros=new Paragraph("Futuros Tecnologos SRL");
            Paragraph rut=new Paragraph("RUT 123456789012");
            Paragraph direccion=new Paragraph(" Av. Gral. Rivera 3629");
            Paragraph telefono=new Paragraph(" Tel: 555-5412");
            Paragraph nombre_empresa=new Paragraph(" Help4Travelling");
            
            
            //datos cliente
            String nombrecliente = nombre.toUpperCase()+" "+apellido.toUpperCase();
            String direccioncliente = " ";
            String rutcliente = "consumidor final".toUpperCase();
            Paragraph cliente=new Paragraph("Cliente: "+nombrecliente);
            Paragraph dircliente=new Paragraph("Direccion: "+direccioncliente);
            Paragraph rutcli=new Paragraph("RUT: " +rutcliente);
            
            
            //datos boleta
            
            String factura = rreserva;
            Paragraph tipodoc=new Paragraph("Contado");
            Paragraph Nfac=new Paragraph(" N°  "+factura);
            
            //alineaciones
            
            //fecha 
            fecha1.setAlignment(Element.ALIGN_RIGHT);
            
            //encabezado
            futuros.setAlignment(Element.ALIGN_CENTER);
            rut.setAlignment(Element.ALIGN_CENTER);
            direccion.setAlignment(Element.ALIGN_CENTER);
            telefono.setAlignment(Element.ALIGN_CENTER);
            
            //nombre empresa
            nombre_empresa.setAlignment(Element.ALIGN_LEFT);
            nombre_empresa.setSpacingBefore(10);
            nombre_empresa.setSpacingAfter(30);
            
            //datos de boleta
            tipodoc.setAlignment(Element.ALIGN_RIGHT);
            Nfac.setAlignment(Element.ALIGN_RIGHT);
            
            
            document.add(fecha1);
            document.add(futuros);
            document.add(rut);
            document.add(direccion);
            document.add(telefono);
            document.add(nombre_empresa);
            document.add(tipodoc);
            document.add(Nfac);
            document.add(cliente);
            document.add(dircliente);
            document.add(rutcli);

            PdfPTable tabla = new PdfPTable(4);
            tabla.setSpacingBefore(25);
            tabla.setSpacingAfter(25);
            
            //creo encabezado de tabla
            PdfPCell codigo = new PdfPCell(new Phrase("Proveedor".toUpperCase()));
            PdfPCell descripcion = new PdfPCell(new Phrase("descripcion".toUpperCase()));
            PdfPCell ecantidad = new PdfPCell(new Phrase("cantidad".toUpperCase()));
            PdfPCell eprecio = new PdfPCell(new Phrase("precio".toUpperCase()));
            
            tabla.setHeaderRows(1);
            tabla.setWidthPercentage(100f);
            
            //alineamos las frases del cabezal
            codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
            descripcion.setHorizontalAlignment(Element.ALIGN_CENTER);
            ecantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            eprecio.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            
            //agrego cabezal de tabla
            tabla.addCell(codigo);
            tabla.addCell(descripcion);
            tabla.addCell(ecantidad);
            tabla.addCell(eprecio);
            
            //obtengo datos de la reserva para imprimir las distintas rows
            
            while (iter.hasNext()) {
                dtItem = iter.next();
                Integer cantidad = dtItem.getCantidad();
                String oferta = dtItem.getOferta().getNombre();
                String precio;
                String proveedor;
                if (existeServicio(oferta)) {
                    proveedor = getNkProveedorServicio(oferta);
                    DtServicio dts = getDtServicio(oferta, proveedor);
                    precio = String.valueOf(dts.getPrecio());
                } else {
                    proveedor = getNkProveedorPromocion(oferta);
                    DtPromocion dtp = getDTPromocion(oferta, proveedor);
                    precio = dtp.getPrecio();
                }
              /*  String item = "<li>Nombre: <em>" + oferta + "</em>"
                        + " - Cantidad: <em>" + cantidad + "</em>"
                        + " - $:<em>" + precio + "</em>"
                        + " - Proveedor: <em>" + proveedor + "</em></li>";*/
                            //creo encabezado de tabla
                PdfPCell iproveedor = new PdfPCell(new Phrase(proveedor.toUpperCase()));
            
                PdfPCell icantidad = new PdfPCell(new Phrase(cantidad.toString()));
                PdfPCell iprecio = new PdfPCell(new Phrase(precio.toUpperCase()));
            
                tabla.setHeaderRows(1);
            
                //alineamos las frases del cabezal
                iproveedor.setHorizontalAlignment(Element.ALIGN_CENTER);
                icantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
                iprecio.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla.addCell(iproveedor);
                if (existeServicio(oferta)) {
                    PdfPCell idescripcion = new PdfPCell(new Phrase("servicio: "+oferta));
                    idescripcion.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(idescripcion);
                } else {
                    PdfPCell idescripcion = new PdfPCell(new Phrase("Promo: "+oferta));
                    idescripcion.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(idescripcion);
                }
                tabla.addCell(icantidad);
                tabla.addCell(iprecio);
            }
            
      /*      // las distintas rows de los articulos
            tabla.addCell(proveedor);
            tabla.addCell(oferta);
            tabla.addCell(cantidad);
            tabla.addCell(precio);
 */
            //el ulimo de la tabla que da el total
            
            PdfPCell celdaFinal = new PdfPCell(new Paragraph(""));
            PdfPCell celdaTotal = new PdfPCell(new Paragraph("total:"));
            PdfPCell celdaPrecioTotal = new PdfPCell(new Paragraph(rtotal));
            // Indicamos cuantas columnas ocupa la celda
            celdaFinal.setColspan(2);
            
            celdaTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            celdaPrecioTotal.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            tabla.addCell(celdaFinal);
            tabla.addCell(celdaTotal);
            tabla.addCell(celdaPrecioTotal);
            document.add(tabla);
            document.close();
            if (request.getParameter("dispositivo").equals("true"))
                response.sendRedirect("Movil.Reservas.jsp");
            else response.sendRedirect("Usuario.jsp");
        }
        catch(DocumentException e){
            e.printStackTrace();
        }
    }
//metodos para publicador 
    
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

    private static DtServicio getDtServicio(java.lang.String arg0, java.lang.String arg1) {
        servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
        servidorpublicador.Publicador port = service.getPublicadorPort();
        return port.getDtServicio(arg0, arg1);
    }

    private static DtPromocion getDTPromocion(java.lang.String arg0, java.lang.String arg1) {
        servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
        servidorpublicador.Publicador port = service.getPublicadorPort();
        return port.getDTPromocion(arg0, arg1);
    }

    private static String getNkProveedorServicio(java.lang.String arg0) {
        servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
        servidorpublicador.Publicador port = service.getPublicadorPort();
        return port.getNkProveedorServicio(arg0);
    }

    private static String getNkProveedorPromocion(java.lang.String arg0) {
        servidorpublicador.PublicadorService service = new servidorpublicador.PublicadorService();
        servidorpublicador.Publicador port = service.getPublicadorPort();
        return port.getNkProveedorPromocion(arg0);
    }
    
    
}
