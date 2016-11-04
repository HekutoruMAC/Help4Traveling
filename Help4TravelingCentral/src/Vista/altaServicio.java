/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.DtCategoria;
import Logica.DtServicio;
import Logica.DtUsuario;
import Logica.Fabrica;
import Logica.IControladorServicio;
import Logica.ManejadorCategoria;
import Logica.ManejadorCiudad;
import Logica.ManejadorProveedor;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Santiago
 */
public class altaServicio extends javax.swing.JInternalFrame {

    List<String> listaPaises = null;
    List<String> listaCiudades = null;
    List<DtUsuario> listaProveedores = new ArrayList<DtUsuario>();
    List<String> imagenes = new LinkedList<String>();
    Map<String, DtCategoria> categorias = new HashMap<String, DtCategoria>();
    private IControladorServicio IControlador;
    DefaultListModel modeloListaImagenes = new DefaultListModel();
    DefaultListModel modeloListaCategorias = new DefaultListModel();
    //JTree tr_categorias = new JTree();
    List<DtCategoria> listaCat = new LinkedList<DtCategoria>();
    String nkproveedor;
    String origen;
    boolean tienedestino;
    String destino;
    boolean categoria_seleccionada;
    private Boolean expandir = true;

    
    private void cargarPaisesOrigen() {
        this.listaPaises = ManejadorCiudad.getInstance().listarPaises();
        Iterator<String> iter = this.listaPaises.iterator();
        while (iter.hasNext()) {
            String nompais = iter.next();
            this.cb_paises_origen.addItem(nompais);
        }
    }

    private void cargarCiudadesPaisOrigen(String pais) {
        this.cb_ciudades_origen.removeAllItems();
        this.listaCiudades = ManejadorCiudad.getInstance().listarCiudadesPorPais(pais);
        Iterator<String> iter = this.listaCiudades.iterator();
        boolean primera = true;
        while (iter.hasNext()) {
            String nomciudad = iter.next();
            this.cb_ciudades_origen.addItem(nomciudad);
            if (primera) {
                this.origen = nomciudad;
            }
            primera = false;
        }
    }

    private void cargarPaisesDestino() {
        this.listaPaises = ManejadorCiudad.getInstance().listarPaises();
        Iterator<String> iter = this.listaPaises.iterator();
        while (iter.hasNext()) {
            String nompais = iter.next();
            this.cb_paises_destino.addItem(nompais);
        }
    }

    private void cargarCiudadesPaisDestino(String pais) {
        cb_ciudades_destino.removeAllItems();
        this.listaCiudades = ManejadorCiudad.getInstance().listarCiudadesPorPais(pais);
        Iterator<String> iter = this.listaCiudades.iterator();
        boolean primera = true;
        while (iter.hasNext()) {
            String nomciudad = iter.next();
            this.cb_ciudades_destino.addItem(nomciudad);
            if (primera) {
                this.destino = nomciudad;
            }
            primera = false;
        }
    }

    private void cargarProveedores() {
        cb_proveedores.removeAllItems();
        this.listaProveedores = ManejadorProveedor.getInstance().listarProveedores();
        Iterator<DtUsuario> iter = this.listaProveedores.iterator();
        boolean primero = true;
        while (iter.hasNext()) {
            String nkprov = iter.next().getNickname();
            this.cb_proveedores.addItem(nkprov);
            if (primero) {
                this.nkproveedor = nkprov;
            }
            primero = false;
        }
    }

    public altaServicio() {
        initComponents();
        Fabrica fabrica = Fabrica.getInstance();
        this.IControlador = fabrica.getIControladorServicio();
        cargarPaisesOrigen();
        cargarPaisesDestino();
        cargarProveedores();
        this.tienedestino = false;
        this.destino = null;
        this.pn_destinos.setVisible(false);
        this.tf_nombre_s.setText("");
        this.tf_precio.setText("");
        this.ta_descripcion.setText("");
        this.btn_seleccionar_imagen.setEnabled(true);
        this.lst_imagenes.setModel(modeloListaImagenes);
        this.lst_categorias.setModel(modeloListaCategorias);
        DefaultMutableTreeNode modeloCategorias = new DefaultMutableTreeNode("Categorias");
        DefaultTreeModel modelo = new DefaultTreeModel(modeloCategorias);
        MostrarArbol(modeloCategorias);
        this.tr_categorias.setModel(modelo);
        this.categoria_seleccionada = false;
        jButtonExpandir.setIcon(UIManager.getIcon("Tree.openIcon"));

    }

    /*public void VerArbol() {
        initComponents();
        Fabrica fabrica = Fabrica.getInstance();
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Categorias");
        DefaultTreeModel modelo = new DefaultTreeModel(raiz);

        MostrarArbol(raiz);
        tr_categorias.setModel(modelo);

    }*/

    public void MostrarArbol(DefaultMutableTreeNode nodo) {
        ManejadorCategoria mc = ManejadorCategoria.getInstance();
        List<String> listaHijos = mc.obtenerCategoriasHijas(nodo.toString());
        Iterator<String> iter = listaHijos.iterator();
        while (iter.hasNext()) {
            String hijo = iter.next();
            System.out.println(hijo);
            DefaultMutableTreeNode nuevoNodo = new DefaultMutableTreeNode(hijo);
            nodo.add(nuevoNodo);
            System.out.println("Agregue el nodo " + hijo);
        }

        for (int j = 0; j < nodo.getChildCount(); j++) {
            MostrarArbol((DefaultMutableTreeNode) nodo.getChildAt(j));
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

        lb_nombre_s = new javax.swing.JLabel();
        tf_nombre_s = new javax.swing.JTextField();
        lb_origen = new javax.swing.JLabel();
        bt_aceptar_s = new javax.swing.JButton();
        bt_cancelar_s = new javax.swing.JButton();
        cb_paises_origen = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cb_ciudades_origen = new javax.swing.JComboBox<>();
        pn_destinos = new javax.swing.JPanel();
        l_empresa = new javax.swing.JLabel();
        l_direccion = new javax.swing.JLabel();
        cb_paises_destino = new javax.swing.JComboBox<>();
        cb_ciudades_destino = new javax.swing.JComboBox<>();
        lb_origen1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_origen2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ta_descripcion = new javax.swing.JTextArea();
        btn_seleccionar_imagen = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        lst_imagenes = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        lst_categorias = new javax.swing.JList<>();
        cb_proveedores = new javax.swing.JComboBox<>();
        tf_precio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tr_categorias = new javax.swing.JTree();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButtonExpandir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Nuevo Servicio");

        lb_nombre_s.setText("Nombre:");

        lb_origen.setText("País Origen:");

        bt_aceptar_s.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/check-icon.png"))); // NOI18N
        bt_aceptar_s.setText("Aceptar");
        bt_aceptar_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_aceptar_sActionPerformed(evt);
            }
        });

        bt_cancelar_s.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete-icon.png"))); // NOI18N
        bt_cancelar_s.setText("Cancelar");
        bt_cancelar_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelar_sActionPerformed(evt);
            }
        });

        cb_paises_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_paises_origenActionPerformed(evt);
            }
        });

        jLabel1.setText("Precio:");

        cb_ciudades_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ciudades_origenActionPerformed(evt);
            }
        });

        l_empresa.setText("País Destino:");

        l_direccion.setText("Ciudad Destino:");

        cb_paises_destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_paises_destinoActionPerformed(evt);
            }
        });

        cb_ciudades_destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_ciudades_destinoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_destinosLayout = new javax.swing.GroupLayout(pn_destinos);
        pn_destinos.setLayout(pn_destinosLayout);
        pn_destinosLayout.setHorizontalGroup(
            pn_destinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_destinosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(l_empresa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_paises_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(l_direccion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cb_ciudades_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pn_destinosLayout.setVerticalGroup(
            pn_destinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_destinosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_destinosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_direccion)
                    .addComponent(l_empresa)
                    .addComponent(cb_paises_destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_ciudades_destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        lb_origen1.setText("Proveedor:");

        jLabel2.setText("Ciudad Origen:");

        lb_origen2.setText("Descripción:");

        ta_descripcion.setColumns(20);
        ta_descripcion.setRows(5);
        jScrollPane2.setViewportView(ta_descripcion);

        btn_seleccionar_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search-icon.png"))); // NOI18N
        btn_seleccionar_imagen.setText("Seleccionar Imagen");
        btn_seleccionar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionar_imagenActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(lst_imagenes);

        jScrollPane4.setViewportView(lst_categorias);

        cb_proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_proveedoresActionPerformed(evt);
            }
        });

        tf_precio.setText("50");
        tf_precio.setToolTipText("");

        jLabel3.setText("(U$D)");

        tr_categorias.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                tr_categoriasValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(tr_categorias);

        jLabel4.setText("Categorias Disponibles:");

        jLabel5.setText("Categorías Seleccionadas:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/add-icon.png"))); // NOI18N
        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonExpandir.setText("Expandir Todas");
        jButtonExpandir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExpandirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonExpandir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_aceptar_s)
                        .addGap(18, 18, 18)
                        .addComponent(bt_cancelar_s)
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_origen1)
                        .addGap(18, 18, 18)
                        .addComponent(cb_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_precio, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(55, 55, 55))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_nombre_s)
                                    .addComponent(lb_origen2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tf_nombre_s, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(lb_origen)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cb_paises_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addGap(10, 10, 10)
                                        .addComponent(cb_ciudades_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(btn_seleccionar_imagen)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1))
                                    .addComponent(jLabel4))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(pn_destinos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_nombre_s, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_nombre_s))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_origen1)
                    .addComponent(jLabel1)
                    .addComponent(cb_proveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_origen2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_origen)
                    .addComponent(cb_paises_origen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_ciudades_origen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_seleccionar_imagen)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton1)))
                .addGap(18, 18, 18)
                .addComponent(pn_destinos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_aceptar_s)
                    .addComponent(bt_cancelar_s)
                    .addComponent(jButtonExpandir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_aceptar_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_aceptar_sActionPerformed
        // TODO add your handling code here:
        String nombre = this.tf_nombre_s.getText();
        String descripcion = this.ta_descripcion.getText();
        float precio;
        if (this.tf_precio.getText().equals("")) {
            precio = 0;
        } else {
            precio = Float.parseFloat(this.tf_precio.getText());
        }
        DtServicio dts = new DtServicio(nombre, this.nkproveedor, descripcion, this.imagenes, categorias, precio, this.origen, this.destino);
        String mensaje = IControlador.altaDeServicio(dts);
        JOptionPane.showMessageDialog(null, mensaje);
        //this.setVisible(false);
        //this.tr_categoria.setVisible(false);
        this.tf_nombre_s.setText("");
        this.tf_precio.setText("");
        this.ta_descripcion.setText("");
        this.btn_seleccionar_imagen.setEnabled(true);
        modeloListaImagenes.clear();
        modeloListaCategorias.clear();
        this.pn_destinos.setVisible(false);
        this.tienedestino = false;
        this.destino = null;
        this.imagenes = new LinkedList<String>();
        this.categorias = new HashMap<String, DtCategoria>();
    }//GEN-LAST:event_bt_aceptar_sActionPerformed

    private void bt_cancelar_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelar_sActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.tr_categorias.setVisible(false);
        this.tf_nombre_s.setText("");    
    }//GEN-LAST:event_bt_cancelar_sActionPerformed

    private void cb_paises_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_paises_origenActionPerformed
        // TODO add your handling code here:
        cargarCiudadesPaisOrigen((String) cb_paises_origen.getSelectedItem());
    }//GEN-LAST:event_cb_paises_origenActionPerformed

    private void cb_proveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_proveedoresActionPerformed
        // TODO add your handling code here:
        this.nkproveedor = (String) cb_proveedores.getSelectedItem();
    }//GEN-LAST:event_cb_proveedoresActionPerformed

    private void btn_seleccionar_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionar_imagenActionPerformed
        // TODO add your handling code here:
        JFileChooser fc_seleccionar_archivo = new javax.swing.JFileChooser();
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fc_seleccionar_archivo.setFileFilter(filtroImagen);
        fc_seleccionar_archivo.setVisible(true);
        int eleccion = fc_seleccionar_archivo.showOpenDialog(null);
        //Seleccionamos el fichero
        if (eleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fc_seleccionar_archivo.getSelectedFile();
            String pathimg = fichero.getAbsolutePath();
            this.imagenes.add(pathimg);
            modeloListaImagenes.addElement(pathimg);
        }
        if (this.imagenes.size() == 3) {
            this.btn_seleccionar_imagen.setEnabled(false);
        }
    }//GEN-LAST:event_btn_seleccionar_imagenActionPerformed

    private void cb_ciudades_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ciudades_origenActionPerformed
        // TODO add your handling code here:
        this.origen = (String) cb_ciudades_origen.getSelectedItem();
    }//GEN-LAST:event_cb_ciudades_origenActionPerformed

    private void cb_ciudades_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_ciudades_destinoActionPerformed
        // TODO add your handling code here:
        this.destino = (String) cb_ciudades_destino.getSelectedItem();
    }//GEN-LAST:event_cb_ciudades_destinoActionPerformed

    private void cb_paises_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_paises_destinoActionPerformed
        // TODO add your handling code here:
        cargarCiudadesPaisDestino((String) cb_paises_destino.getSelectedItem());
    }//GEN-LAST:event_cb_paises_destinoActionPerformed

    private void tr_categoriasValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_tr_categoriasValueChanged
        // TODO add your handling code here:
        this.categoria_seleccionada = true;
    }//GEN-LAST:event_tr_categoriasValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (this.categoria_seleccionada) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) tr_categorias.getLastSelectedPathComponent();
            String padre = ManejadorCategoria.getInstance().obtenerPadre(nodo.toString());
            DtCategoria cat = new DtCategoria(nodo.toString(), padre);
            this.categorias.put(nodo.toString(), cat);
            modeloListaCategorias.addElement(nodo.toString());
            if (padre.equals("Vuelos") || padre.equals("Empresas") || padre.equals("Tipo vuelo"))
                this.tienedestino = true;
            if (this.tienedestino)
                this.pn_destinos.setVisible(true);
            else this.pn_destinos.setVisible(false);            
        }
        this.categoria_seleccionada = false;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonExpandirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExpandirActionPerformed
        //jTree1.expandPath(jTree1.getSelectionPath());
        if (expandir) {
            for (int i = 0; i < tr_categorias.getRowCount(); i++) {
                tr_categorias.expandRow(i);
            }
            jButtonExpandir.setText("Colapsar Todas");
            expandir = false;
            jButtonExpandir.setIcon(UIManager.getIcon("Tree.closedIcon"));
        } else {
            for (int i = tr_categorias.getRowCount(); i > 0; i--) {
                tr_categorias.collapseRow(i);
            }
            jButtonExpandir.setText("Expandir Todas");
            expandir = true;
            jButtonExpandir.setIcon(UIManager.getIcon("Tree.openIcon"));
        }
    }//GEN-LAST:event_jButtonExpandirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_aceptar_s;
    private javax.swing.JButton bt_cancelar_s;
    private javax.swing.JButton btn_seleccionar_imagen;
    private javax.swing.JComboBox<String> cb_ciudades_destino;
    private javax.swing.JComboBox<String> cb_ciudades_origen;
    private javax.swing.JComboBox<String> cb_paises_destino;
    private javax.swing.JComboBox<String> cb_paises_origen;
    private javax.swing.JComboBox<String> cb_proveedores;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonExpandir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel l_direccion;
    private javax.swing.JLabel l_empresa;
    private javax.swing.JLabel lb_nombre_s;
    private javax.swing.JLabel lb_origen;
    private javax.swing.JLabel lb_origen1;
    private javax.swing.JLabel lb_origen2;
    private javax.swing.JList<String> lst_categorias;
    private javax.swing.JList<String> lst_imagenes;
    private javax.swing.JPanel pn_destinos;
    private javax.swing.JTextArea ta_descripcion;
    private javax.swing.JTextField tf_nombre_s;
    private javax.swing.JTextField tf_precio;
    private javax.swing.JTree tr_categorias;
    // End of variables declaration//GEN-END:variables
}
