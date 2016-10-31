/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.DtPromocion;
import Logica.DtServicio;
import Logica.DtUsuario;
import Logica.Fabrica;
import Logica.IControladorServicio;
import Logica.ManejadorProveedor;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author Hekutoru
 */
public class AltaPromocion extends javax.swing.JInternalFrame {

    private IControladorServicio IControlador;
    private DefaultTableModel modelo;
    private List<DtServicio> listaServicios;
    private List<DtUsuario> listaProveedores;

    private String[] columnas = {"Cantidad", "Nombre", "Proveedor", "Precio"};
    private DefaultTableCellRenderer centerRenderer;
    private DefaultTableCellRenderer rightRenderer;

    /**
     * Creates new form AltaPromocion
     */
    public AltaPromocion() {
        initComponents();

        cargarProveedores();

        Fabrica fabrica = Fabrica.getInstance();
        this.IControlador = fabrica.getIControladorServicio();

        jTextFieldTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        jTextFieldDesc.setHorizontalAlignment(SwingConstants.RIGHT);
        jTextFieldFinal.setHorizontalAlignment(SwingConstants.RIGHT);

        listaServicios = this.IControlador.listarServicios();
        refrescarServicios();
    }

    public class SpinnerEditor extends AbstractCellEditor
            implements TableCellEditor {

        final JSpinner spinner = new JSpinner();

        // Initializes the spinner.
        public SpinnerEditor() {
            spinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
        }

        // Prepares the spinner component and returns it.
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            spinner.setValue(value);
            return spinner;
        }

        /*
        // Enables the editor only for double-clicks.
        public boolean isCellEditable(EventObject evt) {
            if (evt instanceof MouseEvent) {
                return ((MouseEvent) evt).getClickCount() >= 2;
            }
            return true;
        }
         */
        // Returns the spinners current value.
        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }
    }

    public void refrescarServicios() {
        Iterator<DtServicio> i = listaServicios.iterator();
        this.modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        while (i.hasNext()) {
            DtServicio serv = i.next();
            Object[] fila = {
                0,
                serv.getNombre(),
                serv.getNkProveedor(),
                serv.getPrecio()};
            modelo.addRow(fila);
        }
        jTableOfertas.setModel(modelo);
        modelo.addTableModelListener(jTableOfertas);

        TableColumn col = jTableOfertas.getColumnModel().getColumn(0);
        SpinnerEditor spinner = new SpinnerEditor();
        col.setCellEditor(spinner);
        //col.setCellRenderer(new SpinnerRenderer(values));

        this.centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        jTableOfertas.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTableOfertas.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
    }

    public void celdaEditada(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        DefaultTableModel model = (DefaultTableModel) e.getSource();
        String columna = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
        calcularTotal();
    }

    public void calcularTotal() {
        Double subtotal = 0.0;
        int filas = modelo.getRowCount();
        for (int i = 0; i < filas; i++) {
            Integer cantidad = Integer.valueOf(modelo.getValueAt(i, 0).toString());
            Float precio = Float.valueOf(modelo.getValueAt(i, 3).toString());
            subtotal += cantidad * precio;
        }
        jTextFieldTotal.setText(subtotal.toString());
        Integer porcentaje = Integer.valueOf(jSpinnerPor100.getValue().toString());
        Double descuento = subtotal * porcentaje / 100;
        jTextFieldDesc.setText(descuento.toString());
        Double total = subtotal - descuento;
        jTextFieldFinal.setText(total.toString());
    }

    public DtPromocion armarDtPromo() {

        Integer index = 0;
        List<DtServicio> listaServiciosPromo = new ArrayList<>();
        Iterator<DtServicio> i = listaServicios.iterator();
        while (i.hasNext()) {
            DtServicio serv = i.next();
            if (Integer.parseInt(modelo.getValueAt(index, 0).toString()) > 0) {
                listaServiciosPromo.add(serv);
            }
            index++;
        }

        DtPromocion dtp = new DtPromocion(
                jTextFieldPromo.getText(),
                jComboBoxProv.getSelectedItem().toString(),
                jTextFieldDesc.getText(),
                jTextFieldFinal.getText(),
                listaServiciosPromo);
        System.out.println(dtp);
        return dtp;
    }

    private void cargarProveedores() {
        jComboBoxProv.removeAllItems();
        this.listaProveedores = ManejadorProveedor.getInstance().listarProveedores();
        Iterator<DtUsuario> iter = listaProveedores.iterator();
        while (iter.hasNext()) {
            String proveedor = iter.next().getNickname();
            this.jComboBoxProv.addItem(proveedor);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelPromo = new javax.swing.JLabel();
        jTextFieldPromo = new javax.swing.JTextField();
        jButtonAceptar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOfertas = new javax.swing.JTable();
        jLabelServ = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        jButtonCalc = new javax.swing.JButton();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabelDesc = new javax.swing.JLabel();
        jTextFieldDesc = new javax.swing.JTextField();
        jLabelTotal1 = new javax.swing.JLabel();
        jTextFieldFinal = new javax.swing.JTextField();
        jLabelTotal2 = new javax.swing.JLabel();
        jSpinnerPor100 = new javax.swing.JSpinner();
        jComboBoxProv = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelPromo1 = new javax.swing.JLabel();
        jButtonLimpiar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Alta Promoción");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/add-icon.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(600, 300));
        setPreferredSize(new java.awt.Dimension(600, 420));

        jLabelPromo.setText("[1] Ingrese el nombre de la nueva promoción:");

        jTextFieldPromo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPromoActionPerformed(evt);
            }
        });
        jTextFieldPromo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPromoKeyReleased(evt);
            }
        });

        jButtonAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/check-icon.png"))); // NOI18N
        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setEnabled(false);
        jButtonAceptar.setFocusable(false);
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel-icon.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.setFocusable(false);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jTableOfertas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Nombre", "Proveedor", "Precio"
            }
        ));
        jTableOfertas.setRowHeight(32);
        jTableOfertas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableOfertasMouseClicked(evt);
            }
        });
        jTableOfertas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableOfertasPropertyChange(evt);
            }
        });
        jTableOfertas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableOfertasKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTableOfertas);

        jLabelServ.setText("[4] Ingrese en la primera columna la cantidad de cada uno de los servicios asociados a la promoción:");

        jLabelTotal.setText("Precio total ($):");
        jLabelTotal.setEnabled(false);

        jButtonCalc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/help-icon.png"))); // NOI18N
        jButtonCalc.setText("Calcular");
        jButtonCalc.setFocusable(false);
        jButtonCalc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCalcActionPerformed(evt);
            }
        });

        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setText("0.0");

        jLabelDesc.setText("[3] Ingrese el descuento aplicado (%):");

        jTextFieldDesc.setEditable(false);
        jTextFieldDesc.setText("0.0");

        jLabelTotal1.setText("Descuento total ($):");
        jLabelTotal1.setEnabled(false);

        jTextFieldFinal.setEditable(false);
        jTextFieldFinal.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextFieldFinal.setText("0.0");

        jLabelTotal2.setText("Precio final ($):");
        jLabelTotal2.setEnabled(false);

        jSpinnerPor100.setModel(new javax.swing.SpinnerNumberModel(10, 1, 100, 1));
        jSpinnerPor100.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerPor100StateChanged(evt);
            }
        });

        jLabel1.setText("Nombre:");

        jLabel2.setText("Proveedor:");

        jLabel3.setText("Descuento:");

        jLabelPromo1.setText("[2] Seleccione el proveedor de la promoción:");

        jButtonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rename-icon.png"))); // NOI18N
        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.setFocusable(false);
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/refresh-icon.png"))); // NOI18N
        jButton1.setText("Refrescar");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCalc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAceptar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelPromo)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(20, 20, 20)
                                .addComponent(jTextFieldPromo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jComboBoxProv, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelPromo1))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelServ)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDesc, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinnerPor100, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelTotal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldFinal)
                            .addComponent(jLabelTotal2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPromo)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPromo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPromo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDesc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jSpinnerPor100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTotal1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTotal2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelServ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAceptar)
                    .addComponent(jButtonCancelar)
                    .addComponent(jButtonCalc)
                    .addComponent(jButtonLimpiar)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        jTextFieldTotal.setHorizontalAlignment(SwingConstants.RIGHT);
        jTextFieldTotal.setHorizontalAlignment(SwingConstants.RIGHT);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPromoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPromoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPromoActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        if (Double.parseDouble(jTextFieldTotal.getText()) > 0) {
            String mensaje = IControlador.altaDePromocion(armarDtPromo());
            if (mensaje.startsWith("ERROR")) {
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, mensaje, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Una promoción debe incluir al menos un servicio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jTextFieldPromoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPromoKeyReleased
        if (jTextFieldPromo.getText().isEmpty()) {
            jButtonAceptar.setEnabled(false);
        } else {
            jButtonAceptar.setEnabled(true);
        }
    }//GEN-LAST:event_jTextFieldPromoKeyReleased

    private void jButtonCalcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCalcActionPerformed
        calcularTotal();
    }//GEN-LAST:event_jButtonCalcActionPerformed

    private void jSpinnerPor100StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerPor100StateChanged
        calcularTotal();
    }//GEN-LAST:event_jSpinnerPor100StateChanged

    private void jTableOfertasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableOfertasKeyReleased
        calcularTotal();
    }//GEN-LAST:event_jTableOfertasKeyReleased

    private void jTableOfertasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableOfertasMouseClicked
        calcularTotal();
    }//GEN-LAST:event_jTableOfertasMouseClicked

    private void jTableOfertasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableOfertasPropertyChange
        calcularTotal();
    }//GEN-LAST:event_jTableOfertasPropertyChange

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        int filas = modelo.getRowCount();
        for (int i = 0; i < filas; i++) {
            jTableOfertas.getModel().setValueAt(0, i, 0);
        }
        jButtonAceptar.setEnabled(false);
        jTextFieldPromo.setText("");
        jTextFieldPromo.grabFocus();
        jSpinnerPor100.setValue(10);
        calcularTotal();
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refrescarServicios();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCalc;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JComboBox<String> jComboBoxProv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDesc;
    private javax.swing.JLabel jLabelPromo;
    private javax.swing.JLabel jLabelPromo1;
    private javax.swing.JLabel jLabelServ;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabelTotal1;
    private javax.swing.JLabel jLabelTotal2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerPor100;
    private javax.swing.JTable jTableOfertas;
    private javax.swing.JTextField jTextFieldDesc;
    private javax.swing.JTextField jTextFieldFinal;
    private javax.swing.JTextField jTextFieldPromo;
    private javax.swing.JTextField jTextFieldTotal;
    // End of variables declaration//GEN-END:variables
}
