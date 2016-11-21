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
            Paragraph paragraph1 = new Paragraph();
             Image imagen= Image.getInstance("http://localhost:8084/Help4TravelingWeb/img/logo-icon2.png");
            imagen.scaleAbsolute(200f, 200f); 
            
            imagen.setAbsolutePosition(10, 650);
            imagen.setSpacingAfter(20);
            paragraph1.add(imagen);
            document.add(paragraph1);
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

            //.setSpacingBefore(0);
           // paragraph1.add(anchorTarget);
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
                   // servicios += item;
                    PdfPCell idescripcion = new PdfPCell(new Phrase("servicio: "+oferta));
                    idescripcion.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(idescripcion);


                } else {
                    //promos += item;
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
        //PdfContentByte cb = writer.getDirectContent();
        
       /*
        BarcodeEAN codeEAN = new BarcodeEAN();
        codeEAN.setCode("4512345678906");

        // CODE 128
        Barcode128 code128 = new Barcode128();
        code128.setCode("0123456789 hello");
        document.add(code128.createImageWithBarcode(cb, null, null));
        code128.setCode("0123456789\uffffMy Raw Barcode (0 - 9)");
        code128.setCodeType(Barcode.CODE128_RAW);
        document.add(code128.createImageWithBarcode(cb, null, null));
        
                Barcode128 uccEan128 = new Barcode128();
        uccEan128.setCodeType(Barcode.CODE128_UCC);
        uccEan128.setCode("(01)00000090311314(10)ABC123(15)060916");
        document.add(uccEan128.createImageWithBarcode(cb, BaseColor.BLUE,
                BaseColor.BLACK));
        uccEan128.setCode("0191234567890121310100035510ABC123");
        document.add(uccEan128.createImageWithBarcode(cb, BaseColor.BLUE,
                BaseColor.RED));
        uccEan128.setCode("(01)28880123456788");
        document.add(uccEan128.createImageWithBarcode(cb, BaseColor.BLUE,
                BaseColor.BLACK));
 
        
           
 

        document.add(new Paragraph("Barcode QRCode"));
        BarcodeQRCode qrcode = new BarcodeQRCode("Help 4 Travelling", 100, 100, null);
        Image qr = qrcode.getImage();
        qr.setAbsolutePosition(300, 50);
      
        document.add(qr);
       
       
            // Agregar nuevo párrafo formateado
            document.add(new Paragraph("Texto alternativo con otro formato.",
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,
                            new CMYKColor(255, 0, 255, 0))));

            // Agregar nuevo capítulo
            Paragraph title1 = new Paragraph("Chapter 1",
                    FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC,
                            new CMYKColor(0, 255, 255, 17)));
            Chapter chapter1 = new Chapter(title1, 1);
            chapter1.setNumberDepth(0);

            // Agregar nueva sección
            Paragraph title11 = new Paragraph("Sección 1 en Capítulo 1", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor(0, 255, 255, 0)));
            Section section1 = chapter1.addSection(title11);

            Paragraph someSectionText = new Paragraph("Texto correspondiente a la Sección 1 del Capítulo 1.");
            section1.add(someSectionText);
            someSectionText = new Paragraph("A continuación una tabla de 3 X 2.");
            section1.add(someSectionText);

            // Crear tabla manualmente
            PdfPTable t = new PdfPTable(3);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);
            PdfPCell c1 = new PdfPCell(new Phrase("cantidad"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("oferta"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("precio"));
            t.addCell(c3);
            t.addCell("1.1");
            t.addCell("1.2");
            t.addCell("1.3");
            section1.add(t);

            // Crear lista manualmente
            List l = new List(true, false, 10);
            l.add(new ListItem("Primer item de la lista"));
            l.add(new ListItem("Segundo item de la lista"));
            section1.add(l);

            // Agregar imagen
   /*         Image image2 = Image.getInstance("img/logo.png");
            image2.scaleAbsolute(120f, 120f);
 img/logo.png           section1.add(image2);
*/         
            
            // Agregar marcador final
         /*   Paragraph title2 = new Paragraph("Usando marcador",
                    FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                            new CMYKColor(0, 255, 0, 0)));
            section1.add(title2);
            title2.setSpacingBefore(5000);
            Anchor anchor2 = new Anchor("Volver al inicio");
            anchor2.setReference("#BackToTop");
            section1.add(anchor2);*/

            // Agregar capítulo y cerrar documento
            //document.add(chapter1);
            document.close();
           
            
           /* if (rtipo.compareTo("proveedor")==0){
            response.sendRedirect("Movil.Reservas.jsp");
            }
            else{
               response.sendRedirect("Usuario.jsp");
            }*/
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
