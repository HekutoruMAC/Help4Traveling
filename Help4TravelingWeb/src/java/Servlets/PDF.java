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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hekutoru
 */
public class PDF extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws com.itextpdf.text.DocumentException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, DocumentException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Crear y abrir documento
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("ITextTest.pdf"));
            document.open();

            // Agregar marcador inicial
            Anchor anchorTarget = new Anchor("Primera página del documento.");
            anchorTarget.setName("Volver al inicio.");

            // Crear y agregar párrafo simple
            Paragraph paragraph1 = new Paragraph();
            paragraph1.setSpacingBefore(50);
            paragraph1.add(anchorTarget);
            document.add(paragraph1);

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
            PdfPCell c1 = new PdfPCell(new Phrase("Header1"));
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Header2"));
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Header3"));
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
            Image image2 = Image.getInstance("IBMLogo.bmp");
            image2.scaleAbsolute(120f, 120f);
            section1.add(image2);

            // Agregar marcador final
            Paragraph title2 = new Paragraph("Usando marcador",
                    FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                            new CMYKColor(0, 255, 0, 0)));
            section1.add(title2);
            title2.setSpacingBefore(5000);
            Anchor anchor2 = new Anchor("Volver al inicio");
            anchor2.setReference("#BackToTop");
            section1.add(anchor2);

            // Agregar capítulo y cerrar documento
            document.add(chapter1);
            document.close();
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
        } catch (DocumentException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (DocumentException ex) {
            Logger.getLogger(PDF.class.getName()).log(Level.SEVERE, null, ex);
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
