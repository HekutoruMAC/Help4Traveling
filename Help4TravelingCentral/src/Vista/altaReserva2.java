/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.DtPromocion;
import Logica.DtServicio;
import Logica.Servicio;
import Logica.Fabrica;
import Logica.IControladorServicio;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;

/**
 *
 * @author Santiago
 */
public class altaReserva2 extends javax.swing.JInternalFrame {

    private IControladorServicio IControlador;
    private DefaultListModel modelo;
    private List<DtServicio> listaServicios;
    private List<DtPromocion> listaPromociones;

    /**
     * Creates new form altaReserva2
     */
    public altaReserva2() {
        initComponents();

        Fabrica fabrica = Fabrica.getInstance();
        this.IControlador = fabrica.getIControladorServicio();

        //Agregar los servicios a la lista
        listaServicios = this.IControlador.listarServicios();
        Iterator<DtServicio> i = listaServicios.iterator();
        modelo = new DefaultListModel();
        while (i.hasNext()) {
            DtServicio servicio = i.next();
            modelo.addElement(servicio.getNombre() + "~" + servicio.getNkProveedor());
        }
        
        //Agregar las promociones a la lista
        listaPromociones = this.IControlador.listarPromociones();
        Iterator<DtPromocion> ip = listaPromociones.iterator();
        while (ip.hasNext()) {
            DtPromocion promocion = ip.next();
            modelo.addElement(promocion.getNombre() + "~" + promocion.getProveedor());
        }
        
        //Agrego los servicios y las primociones a la lista.
        this.Ofertas.setModel(modelo);

        //Setear
        buscar.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        infoItem = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nombreItem = new javax.swing.JTextField();
        inicio = new com.toedter.calendar.JDateChooser();
        fin = new com.toedter.calendar.JDateChooser();
        agregar = new javax.swing.JButton();
        proveedorItem = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        unidad = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        cantidad = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        Ofertas = new javax.swing.JList<>();
        buscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        confirmar = new javax.swing.JButton();
        bBuscar = new javax.swing.JButton();

        infoItem.setTitle("Datos item reserva");
        infoItem.setResizable(false);

        jLabel1.setText("Cantiad:");

        jLabel3.setText("Fecha inicio:");

        jLabel4.setText("Fecha fin:");

        jLabel6.setText("Item:");

        nombreItem.setEditable(false);

        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/add-icon.png"))); // NOI18N
        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        proveedorItem.setEditable(false);

        jLabel7.setText("Proveedor:");

        unidad.setEditable(false);

        jLabel8.setText("Precio p/u:");

        jLabel9.setText("Precio total:");

        total.setEditable(false);
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });

        cantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 20, 1));
        cantidad.setValue(1);
        cantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cantidadStateChanged(evt);
            }
        });

        javax.swing.GroupLayout infoItemLayout = new javax.swing.GroupLayout(infoItem.getContentPane());
        infoItem.getContentPane().setLayout(infoItemLayout);
        infoItemLayout.setHorizontalGroup(
            infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoItemLayout.createSequentialGroup()
                        .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(proveedorItem)
                            .addGroup(infoItemLayout.createSequentialGroup()
                                .addComponent(unidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(infoItemLayout.createSequentialGroup()
                        .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(9, 9, 9)
                        .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(infoItemLayout.createSequentialGroup()
                                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(nombreItem)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoItemLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(agregar)))
                .addContainerGap())
        );
        infoItemLayout.setVerticalGroup(
            infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoItemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(proveedorItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(agregar)
                .addGap(6, 6, 6))
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Lista de servicios y promociones");

        Ofertas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OfertasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Ofertas);

        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscarKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarKeyTyped(evt);
            }
        });

        jLabel2.setText("Buscar:");

        confirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/check-icon.png"))); // NOI18N
        confirmar.setText("Listo");
        confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarActionPerformed(evt);
            }
        });

        bBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search-icon.png"))); // NOI18N
        bBuscar.setText("Buscar");
        bBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bBuscarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscar)
                        .addGap(18, 18, 18)
                        .addComponent(bBuscar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(bBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmar)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OfertasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OfertasMouseClicked
        String sServicio;
        Servicio oServicio;
        
        this.infoItem.setSize(390, 290);
        this.infoItem.setLocationRelativeTo(this);

        StringTokenizer st = new StringTokenizer(Ofertas.getSelectedValue(), "~", true);
        sServicio = st.nextToken();
        this.nombreItem.setText(sServicio);
        st.nextToken();
        this.proveedorItem.setText(st.nextToken());
        this.inicio.setDate(new Date());
        this.fin.setDate(new Date());
        //this.cantidad.("1");
        
        //Obtengo info extra del servicio 
        oServicio = IControlador.obtenerServicio(sServicio);
        this.unidad.setText(String.valueOf(oServicio.getPrecio()));
        this.total.setText(String.valueOf(oServicio.getPrecio()));
        
        this.infoItem.setVisible(true);
    }//GEN-LAST:event_OfertasMouseClicked

    private void confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarActionPerformed
        this.dispose();
    }//GEN-LAST:event_confirmarActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyTyped

    }//GEN-LAST:event_buscarKeyTyped

    private void buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyPressed

    }//GEN-LAST:event_buscarKeyPressed

    private void bBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bBuscarMouseClicked
        DefaultListModel modeloBuscar = new DefaultListModel();
        String texto = buscar.getText();

        if (texto != "") {
            this.Ofertas.setModel(modeloBuscar);

            Iterator<DtServicio> i = listaServicios.iterator();
            while (i.hasNext()) {
                DtServicio oServicio = i.next();
                String servicio = oServicio.getNombre() + "~" + oServicio.getNkProveedor();
                if (servicio.indexOf(texto) != -1) {
                    modeloBuscar.addElement(servicio);
                }
            }
            this.Ofertas.setModel(modeloBuscar);

        } else {
            this.Ofertas.setModel(modelo);
        }
    }//GEN-LAST:event_bBuscarMouseClicked

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        int iCantidad = Integer.parseInt(String.valueOf(cantidad.getValue()));
        String sNombreItem = nombreItem.getText();
        String sProveedor = proveedorItem.getText();
        String sInicio = dateToString(inicio.getDate());
        String sFin = dateToString(fin.getDate());
        double dUnidad = Double.parseDouble(unidad.getText());
        double dTotal = Double.parseDouble(total.getText());
        
        altaReserva.agregarItem(new Object[]{true, iCantidad, sNombreItem, sProveedor, sInicio, sFin, dUnidad, dTotal}, dTotal);
        infoItem.dispose();
    }//GEN-LAST:event_agregarActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void cantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cantidadStateChanged
        double dTotal;
        dTotal = Double.parseDouble(unidad.getText()) * Double.parseDouble(String.valueOf(cantidad.getValue()));
        total.setText(String.valueOf(dTotal));
    }//GEN-LAST:event_cantidadStateChanged

    private static String dateToString(Date date) {
        String fecha, dia, mes, ano;
        dia = String.valueOf(date.getDay());
        mes = String.valueOf(date.getMonth());
        ano = String.valueOf(date.getYear() - 100);

        fecha = "20" + ano;
        fecha += "-";
        if (mes.length() == 1) {
            fecha += "0" + mes;
        } else {
            fecha += mes;
        }
        fecha += "-";
        if (dia.length() == 1) {
            fecha += "0" + dia;
        } else {
            fecha += dia;
        }
        return fecha;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Ofertas;
    private javax.swing.JButton agregar;
    private javax.swing.JButton bBuscar;
    private javax.swing.JTextField buscar;
    private javax.swing.JSpinner cantidad;
    private javax.swing.JButton confirmar;
    private com.toedter.calendar.JDateChooser fin;
    private javax.swing.JDialog infoItem;
    private com.toedter.calendar.JDateChooser inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombreItem;
    private javax.swing.JTextField proveedorItem;
    private javax.swing.JTextField total;
    private javax.swing.JTextField unidad;
    // End of variables declaration//GEN-END:variables
}
