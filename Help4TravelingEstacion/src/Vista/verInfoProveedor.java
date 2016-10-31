/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.DtReserva;
import Logica.DtServicio;
import Logica.DtUsuario;
import Logica.Fabrica;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import Logica.IControladorUsuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yaman
 */
public class verInfoProveedor extends javax.swing.JInternalFrame {

    private IControladorUsuario IControlador;
    private ArrayList<DtUsuario> listaProveedores;
    private DefaultTableModel modeloTablaPr;
    private DefaultTableModel modeloTablaSer;

    /**
     * Creates new form verInfoProveedor
     */
    public verInfoProveedor() {
        initComponents();
        Fabrica fabrica = Fabrica.getInstance();
        this.IControlador = fabrica.getIControladorUsuario();

        DefaultListModel modelo = new DefaultListModel();

        this.listaProveedores = this.IControlador.listarProveedores();
        Iterator<DtUsuario> i = this.listaProveedores.iterator();
        while (i.hasNext()) {
            DtUsuario user = i.next();
            modelo.addElement(user.getNombre());
        }

        jListaProveedores.setModel(modelo);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListaProveedores = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablaServicios = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTablaProveedores = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jListaProveedores.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListaProveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jListaProveedores);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("PROVEEDORES");

        jTablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "servicio", "precio", "origen", "destino"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTablaServicios);

        jTablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nombre", "apellido", "nickname", "nacimiento", "empresa", "link"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTablaProveedores);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("DATOS");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("SERVICIOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(151, 151, 151)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(168, 168, 168)
                            .addComponent(jLabel3))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(182, 182, 182)
                            .addComponent(jLabel2))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(46, 46, 46)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jListaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListaProveedoresMouseClicked

        String nombre = jListaProveedores.getSelectedValue();

        String[] columnasProveedores = {"nickname", "nombre", "apellido","nacimiento", "empresa", "link"};

        String[] registrosProveedores = new String[6];

        String[] columnasServicios = {"servicio", "precio", "origen", "destino"};

        String[] registrosServicios = new String[4];

        modeloTablaPr = new DefaultTableModel(null, columnasProveedores);
        modeloTablaSer = new DefaultTableModel(null, columnasServicios);

        Iterator<DtUsuario> i = this.listaProveedores.iterator();
        boolean es = false;
        while (i.hasNext() & !es) {
            DtUsuario user = i.next();
            if (nombre.equals(user.getNombre())) {
                es = true;

                registrosProveedores[0] = user.getNombre();
                registrosProveedores[1] = user.getApellido();
                registrosProveedores[2] = user.getNickname();
                registrosProveedores[3] = user.getNacimiento().getFecha("-");
                registrosProveedores[4] = user.getEmpresa();
                System.out.println("Laempresa es " +registrosProveedores[4]);
                registrosProveedores[5] = user.getLink();
                System.out.println("La direccion es " +registrosProveedores[5]);

                modeloTablaPr.addRow(registrosProveedores);
                jTablaProveedores.setModel(modeloTablaPr);

                Fabrica fabrica = Fabrica.getInstance();

                ArrayList<DtServicio> listaServicios = fabrica.getIControladorUsuario().listarServicioProveedor(user);

                Iterator<DtServicio> iter = listaServicios.iterator();
                while (iter.hasNext()) {

                    DtServicio res = iter.next();
                    registrosServicios[0] = res.getNombre();
                    System.out.println(registrosServicios[0]);
                    registrosServicios[1] = Float.toString(res.getPrecio());
                    registrosServicios[2] = res.getNomCiuOrigen();
                    registrosServicios[3] = res.getNomCiuDestino();

                    modeloTablaSer.addRow(registrosServicios);
                }
            }
            jTablaServicios.setModel(modeloTablaSer);
    }//GEN-LAST:event_jListaProveedoresMouseClicked
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jListaProveedores;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTablaProveedores;
    private javax.swing.JTable jTablaServicios;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
