/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.DtCategoria;
import Logica.DtServicio;
import Logica.Fabrica;
import Logica.IControladorServicio;
import Logica.ManejadorCategoria;
import Logica.ManejadorCiudad;
import Logica.ManejadorServicio;
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
 * @author Hekutoru
 */
public class ActualizarServicio extends javax.swing.JInternalFrame {

    private IControladorServicio IControlador;
    private List<DtServicio> listaServicios = new ArrayList<DtServicio>();
    //private List<String> listaPaises1 = new ArrayList<String>();
    //private List<String> listaPaises2 = new ArrayList<String>();
    //private List<String> listaCiudades1 = new ArrayList<String>();
    //private List<String> listaCiudades2 = new ArrayList<String>();
    List<String> imagenes = new ArrayList<String>();
    Map<String, DtCategoria> categorias = new HashMap<String, DtCategoria>();
    DefaultListModel modeloListaImagenes = new DefaultListModel();
    DefaultListModel modeloListaCategorias = new DefaultListModel();
    List<DtCategoria> listaCat = new LinkedList<DtCategoria>();
    String proveedor;
    boolean tienedestino;
    boolean categoria_seleccionada;
    boolean inicio = true;
    private boolean expandir = true;

    public ActualizarServicio() {
        initComponents();
        Fabrica fabrica = Fabrica.getInstance();
        this.IControlador = fabrica.getIControladorServicio();
        bajarServicios();
        bajarServicio();
        this.lst_imagenes.setModel(modeloListaImagenes);
        this.lst_categorias.setModel(modeloListaCategorias);
        DefaultMutableTreeNode modeloCategorias = new DefaultMutableTreeNode("Categorias");
        DefaultTreeModel modelo = new DefaultTreeModel(modeloCategorias);
        MostrarArbol(modeloCategorias);
        this.tr_categorias.setModel(modelo);
        this.categoria_seleccionada = false;
    }

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

    private void bajarServicios() {
        this.cb_nombre.removeAllItems();
        this.listaServicios = ManejadorServicio.getInstance().listarServicios();
        Iterator<DtServicio> iter = listaServicios.iterator();
        while (iter.hasNext()) {
            String servicio = iter.next().getNombre();
            this.cb_nombre.addItem(servicio);
        }
    }

    private void bajarServicio() {
        Integer index = cb_nombre.getSelectedIndex();
        this.imagenes = new ArrayList<String>();
        this.categorias = new HashMap<String, DtCategoria>();
        this.listaServicios = ManejadorServicio.getInstance().listarServicios();
        DtServicio servicio = listaServicios.get(index);
        modeloListaImagenes.clear();
        modeloListaCategorias.clear();
        tfProveedor.setText(proveedor);
        ta_descripcion.setText(servicio.getDescripcion());
        tf_precio.setText(Float.toString(servicio.getPrecio()));
        proveedor = servicio.getNkProveedor();
        //this.cb_pais_origen.removeAllItems();
        bajarPaisesOrigen();
        String ciudadOrigen = servicio.getNomCiuOrigen();
        String paisOrigen = ManejadorCiudad.getInstance().obtenerCiudad(ciudadOrigen).getPais().getNombre();
        cb_pais_origen.setSelectedItem(paisOrigen);
        bajarCiudadesOrigen(paisOrigen);
        cb_ciudad_origen.setSelectedItem(ciudadOrigen);
        if (servicio.getNomCiuDestino() != null) {
            cb_pais_destino.setEnabled(true);
            cb_ciudad_destino.setEnabled(true);
            lb_destino.setEnabled(true);
            bajarPaisesDestino();
            String ciudadDestino = servicio.getNomCiuDestino();
            String paisDestino = ManejadorCiudad.getInstance().obtenerCiudad(ciudadDestino).getPais().getNombre();
            cb_pais_destino.setSelectedItem(paisDestino);
            bajarCiudadesDestino(paisDestino);
            cb_ciudad_destino.setSelectedItem(ciudadDestino);
            tienedestino = true;
        } else {
            cb_pais_destino.setEnabled(false);
            cb_ciudad_destino.setEnabled(false);
            lb_destino.setEnabled(false);
            cb_pais_destino.removeAllItems();
            cb_pais_destino.addItem("-");
            cb_ciudad_destino.removeAllItems();
            cb_ciudad_destino.addItem("-");
        }
        this.imagenes = servicio.getImagenes();
        for (int i = 0; i < imagenes.size(); i++) {
            modeloListaImagenes.addElement(imagenes.get(i));
            //System.out.println(imagenes.get(i));
        }
        this.categorias = servicio.getDtCategorias();
        Iterator<DtCategoria> iterc = categorias.values().iterator();
        while (iterc.hasNext()) {
            modeloListaCategorias.addElement(iterc.next().getNombre());
        }
        inicio = false;
    }

    private void bajarPaisesOrigen() {
        this.cb_pais_origen.removeAllItems();
        List<String> listaPaises = new ArrayList<String>();
        listaPaises = ManejadorCiudad.getInstance().listarPaises();
        Iterator<String> iter = listaPaises.iterator();
        while (iter.hasNext()) {
            String pais = iter.next();
            this.cb_pais_origen.addItem(pais);
        }
    }

    private void bajarCiudadesOrigen(String pais) {
        this.cb_ciudad_origen.removeAllItems();
        List<String> listaCiudades = new ArrayList<String>();
        listaCiudades = ManejadorCiudad.getInstance().listarCiudadesPorPais(pais);
        Iterator<String> iter = listaCiudades.iterator();
        while (iter.hasNext()) {
            String nomciudad = iter.next();
            this.cb_ciudad_origen.addItem(nomciudad);
        }
    }

    private void bajarPaisesDestino() {
        this.cb_pais_destino.removeAllItems();
        List<String> listaPaises = new ArrayList<String>();
        listaPaises = ManejadorCiudad.getInstance().listarPaises();
        Iterator<String> iter = listaPaises.iterator();
        while (iter.hasNext()) {
            String pais = iter.next();
            this.cb_pais_destino.addItem(pais);
        }
    }

    private void bajarCiudadesDestino(String pais) {
        this.cb_ciudad_destino.removeAllItems();
        List<String> listaCiudades = new ArrayList<String>();
        listaCiudades = ManejadorCiudad.getInstance().listarCiudadesPorPais(pais);
        Iterator<String> iter = listaCiudades.iterator();
        while (iter.hasNext()) {
            String nomciudad = iter.next();
            this.cb_ciudad_destino.addItem(nomciudad);
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

        Aceptar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jLabNom = new javax.swing.JLabel();
        cb_nombre = new javax.swing.JComboBox<>();
        jLabelServ = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_descripcion = new javax.swing.JTextPane();
        jLabDesc = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelPrecio = new javax.swing.JLabel();
        tf_precio = new javax.swing.JTextField();
        jLabOrig = new javax.swing.JLabel();
        lb_destino = new javax.swing.JLabel();
        cb_pais_origen = new javax.swing.JComboBox<>();
        cb_pais_destino = new javax.swing.JComboBox<>();
        cb_ciudad_destino = new javax.swing.JComboBox<>();
        cb_ciudad_origen = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        btn_seleccionar_imagen = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        lst_imagenes = new javax.swing.JList<>();
        jLabOrig1 = new javax.swing.JLabel();
        btn_eliminar_imagen = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tr_categorias = new javax.swing.JTree();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_agregar_categoria = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lst_categorias = new javax.swing.JList<>();
        btn_eliminar_categoria = new javax.swing.JButton();
        jButtonExpandir = new javax.swing.JButton();
        jLabelPrecio1 = new javax.swing.JLabel();
        lProveedor = new javax.swing.JLabel();
        tfProveedor = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Actualizar Servicio");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/edit-icon.png"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(550, 550));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(550, 550));

        Aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/check-icon.png"))); // NOI18N
        Aceptar.setText("Aceptar");
        Aceptar.setFocusable(false);
        Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AceptarActionPerformed(evt);
            }
        });

        Cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel-icon.png"))); // NOI18N
        Cancelar.setText("Cancelar");
        Cancelar.setFocusable(false);
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        jLabNom.setText("Nombre:");

        cb_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_nombreActionPerformed(evt);
            }
        });

        jLabelServ.setText("Seleccione el Servicio que desea modificar:");

        ta_descripcion.setFocusCycleRoot(false);
        jScrollPane1.setViewportView(ta_descripcion);

        jLabDesc.setText("Descripción:");

        jLabelPrecio.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabelPrecio.setText("Precio:");

        jLabOrig.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabOrig.setText("Origen:");

        lb_destino.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lb_destino.setText("Destino:");

        cb_pais_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pais_origenActionPerformed(evt);
            }
        });

        cb_pais_destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pais_destinoActionPerformed(evt);
            }
        });

        btn_seleccionar_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search-icon.png"))); // NOI18N
        btn_seleccionar_imagen.setText("Seleccionar");
        btn_seleccionar_imagen.setFocusable(false);
        btn_seleccionar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_seleccionar_imagenActionPerformed(evt);
            }
        });

        lst_imagenes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lst_imagenesValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(lst_imagenes);

        jLabOrig1.setText("Imágenes:");

        btn_eliminar_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete-icon.png"))); // NOI18N
        btn_eliminar_imagen.setText("  Eliminar");
        btn_eliminar_imagen.setEnabled(false);
        btn_eliminar_imagen.setFocusable(false);
        btn_eliminar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_imagenActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        tr_categorias.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        tr_categorias.setMaximumSize(new java.awt.Dimension(0, 0));
        tr_categorias.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                tr_categoriasValueChanged(evt);
            }
        });
        jScrollPane5.setViewportView(tr_categorias);

        jLabel4.setText("Categorias:");

        jLabel5.setText("Categorías Disponibles:");

        btn_agregar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/previous-icon.png"))); // NOI18N
        btn_agregar_categoria.setText("Agregar");
        btn_agregar_categoria.setEnabled(false);
        btn_agregar_categoria.setFocusable(false);
        btn_agregar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_categoriaActionPerformed(evt);
            }
        });

        lst_categorias.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lst_categoriasValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(lst_categorias);

        btn_eliminar_categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/out-icon.png"))); // NOI18N
        btn_eliminar_categoria.setText("Eliminar");
        btn_eliminar_categoria.setEnabled(false);
        btn_eliminar_categoria.setFocusable(false);
        btn_eliminar_categoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_categoriaActionPerformed(evt);
            }
        });

        jButtonExpandir.setText("Expandir");
        jButtonExpandir.setFocusable(false);
        jButtonExpandir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExpandirActionPerformed(evt);
            }
        });
        jButtonExpandir.setIcon(UIManager.getIcon("Tree.openIcon"));

        jLabelPrecio1.setText("(U$D)");

        lProveedor.setText("Proveedor:");

        tfProveedor.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Cancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Aceptar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 163, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_eliminar_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_agregar_categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonExpandir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_seleccionar_imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_eliminar_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabOrig1)
                                    .addComponent(jLabDesc))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_destino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabOrig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cb_pais_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cb_ciudad_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cb_pais_origen, 0, 100, Short.MAX_VALUE)
                                    .addComponent(tf_precio))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelPrecio1)
                                    .addComponent(cb_ciudad_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabNom)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cb_nombre, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelServ, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addComponent(lProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfProveedor)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelServ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabNom)
                    .addComponent(cb_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lProveedor)
                    .addComponent(tfProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabDesc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPrecio)
                            .addComponent(tf_precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPrecio1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_ciudad_origen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_pais_origen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabOrig)))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_destino)
                            .addComponent(cb_pais_destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_ciudad_destino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabOrig1)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_seleccionar_imagen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar_imagen))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_agregar_categoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar_categoria)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jButtonExpandir))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Aceptar)
                    .addComponent(Cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AceptarActionPerformed
        String nombre = this.cb_nombre.getSelectedItem().toString();
        String descripcion = this.ta_descripcion.getText();
        String origen = this.cb_ciudad_origen.getSelectedItem().toString();
        String destino = null;
        if (tienedestino) {
            destino = this.cb_ciudad_destino.getSelectedItem().toString();
        }
        float precio;
        if (this.tf_precio.getText().equals("")) {
            precio = 0;
        } else {
            precio = Float.parseFloat(this.tf_precio.getText());
        }
        //System.out.println(descripcion);
        DtServicio dts = new DtServicio(nombre, proveedor, descripcion, this.imagenes, this.categorias, precio, origen, destino);
        String mensaje = IControlador.actualizarUnServicio(dts);
        JOptionPane.showMessageDialog(null, mensaje);
        //this.btn_seleccionar_imagen.setEnabled(true);
        //modeloListaImagenes.clear();
        //modeloListaCategorias.clear();
        //this.imagenes = new ArrayList<String>();
        //this.categorias = new HashMap<String, DtCategoria>();
        //tienedestino = true;
    }//GEN-LAST:event_AceptarActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_CancelarActionPerformed

    private void cb_pais_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pais_origenActionPerformed
        bajarCiudadesOrigen((String) cb_pais_origen.getSelectedItem());
    }//GEN-LAST:event_cb_pais_origenActionPerformed

    private void cb_pais_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pais_destinoActionPerformed
        bajarCiudadesDestino((String) cb_pais_destino.getSelectedItem());
    }//GEN-LAST:event_cb_pais_destinoActionPerformed

    private void cb_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_nombreActionPerformed
        bajarServicio();
        lst_imagenesValueChanged(null);
        lst_imagenesValueChanged(null);
    }//GEN-LAST:event_cb_nombreActionPerformed

    private void btn_seleccionar_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_seleccionar_imagenActionPerformed
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
        if (this.modeloListaImagenes.size() > 2) {
            this.btn_seleccionar_imagen.setEnabled(false);
        }
    }//GEN-LAST:event_btn_seleccionar_imagenActionPerformed

    private void btn_eliminar_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_imagenActionPerformed
        //List<int> itemelegido = java.util.Arrays.asList(lst_imagenes.getSelectedIndices());
        List<String> itemselegidos = lst_imagenes.getSelectedValuesList();
        Iterator<String> it = itemselegidos.iterator();
        while (it.hasNext()) {
            String item = it.next();
            modeloListaImagenes.removeElement(item);
            imagenes.remove(item);
        }
        btn_eliminar_imagen.setEnabled(false);
        btn_seleccionar_imagen.setEnabled(true);
    }//GEN-LAST:event_btn_eliminar_imagenActionPerformed

    private void tr_categoriasValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_tr_categoriasValueChanged
        //this.categoria_seleccionada = true;
        if (tr_categorias.getSelectionCount() > 0) {
            if (categorias.containsKey(tr_categorias.getLastSelectedPathComponent().toString())) {
                btn_agregar_categoria.setEnabled(false);
            } else {
                btn_agregar_categoria.setEnabled(true);
            }
        }
    }//GEN-LAST:event_tr_categoriasValueChanged

    private void btn_agregar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_categoriaActionPerformed
        // TODO add your handling code here:
        //if (this.categoria_seleccionada) {
        if (tr_categorias.getSelectionCount() > 0) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) tr_categorias.getLastSelectedPathComponent();
            String padre = ManejadorCategoria.getInstance().obtenerPadre(nodo.toString());
            if (!categorias.containsKey(nodo.toString())) {
                if (padre.equals("Vuelos") || padre.equals("Empresas") || padre.equals("Tipo vuelo")) {
                    tienedestino = true;
                    cb_pais_destino.setVisible(true);
                    cb_ciudad_destino.setVisible(true);
                    lb_destino.setVisible(true);
                    bajarPaisesDestino();
                    bajarCiudadesDestino(cb_pais_destino.getSelectedItem().toString());
                }
                DtCategoria cat = new DtCategoria(nodo.toString(), padre);
                this.categorias.put(nodo.toString(), cat);
                modeloListaCategorias.addElement(nodo.toString());
            }
            btn_agregar_categoria.setEnabled(false);
        }
    }//GEN-LAST:event_btn_agregar_categoriaActionPerformed

    private void jButtonExpandirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExpandirActionPerformed
        //jTree1.expandPath(jTree1.getSelectionPath());
        if (expandir) {
            for (int i = 0; i < tr_categorias.getRowCount(); i++) {
                tr_categorias.expandRow(i);
            }
            jButtonExpandir.setText("Colapsar");
            expandir = false;
            jButtonExpandir.setIcon(UIManager.getIcon("Tree.closedIcon"));
        } else {
            for (int i = tr_categorias.getRowCount(); i > 0; i--) {
                tr_categorias.collapseRow(i);
            }
            jButtonExpandir.setText("Expandir");
            expandir = true;
            jButtonExpandir.setIcon(UIManager.getIcon("Tree.openIcon"));
        }
    }//GEN-LAST:event_jButtonExpandirActionPerformed

    private void btn_eliminar_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_categoriaActionPerformed
        String itemelegido = lst_categorias.getSelectedValue();
        if (itemelegido != null) {
            if (categorias.containsKey(itemelegido)) {
                btn_agregar_categoria.setEnabled(true);
            }
            String padre = ManejadorCategoria.getInstance().obtenerPadre(itemelegido);
            if (padre.equals("Vuelos") || padre.equals("Empresas") || padre.equals("Tipo vuelo")) {
                tienedestino = false;
                cb_pais_destino.setVisible(false);
                cb_ciudad_destino.setVisible(false);
                lb_destino.setVisible(false);

            }
            this.categorias.remove(itemelegido);
            modeloListaCategorias.removeElement(itemelegido);
            btn_eliminar_categoria.setEnabled(false);
        }
    }//GEN-LAST:event_btn_eliminar_categoriaActionPerformed

    private void lst_categoriasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lst_categoriasValueChanged
        if (lst_categorias.getSelectedIndex() >= 0) {
            btn_eliminar_categoria.setEnabled(true);
        }
    }//GEN-LAST:event_lst_categoriasValueChanged

    private void lst_imagenesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lst_imagenesValueChanged
        if (modeloListaImagenes.size() > 2) {
            btn_seleccionar_imagen.setEnabled(false);
        } else {
            btn_seleccionar_imagen.setEnabled(true);
        }
        if (lst_imagenes.getSelectedIndex() >= 0) {
            btn_eliminar_imagen.setEnabled(true);
        }
    }//GEN-LAST:event_lst_imagenesValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Aceptar;
    private javax.swing.JButton Cancelar;
    private javax.swing.JButton btn_agregar_categoria;
    private javax.swing.JButton btn_eliminar_categoria;
    private javax.swing.JButton btn_eliminar_imagen;
    private javax.swing.JButton btn_seleccionar_imagen;
    private javax.swing.JComboBox<String> cb_ciudad_destino;
    private javax.swing.JComboBox<String> cb_ciudad_origen;
    private javax.swing.JComboBox<String> cb_nombre;
    private javax.swing.JComboBox<String> cb_pais_destino;
    private javax.swing.JComboBox<String> cb_pais_origen;
    private javax.swing.JButton jButtonExpandir;
    private javax.swing.JLabel jLabDesc;
    private javax.swing.JLabel jLabNom;
    private javax.swing.JLabel jLabOrig;
    private javax.swing.JLabel jLabOrig1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JLabel jLabelPrecio1;
    private javax.swing.JLabel jLabelServ;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lProveedor;
    private javax.swing.JLabel lb_destino;
    private javax.swing.JList<String> lst_categorias;
    private javax.swing.JList<String> lst_imagenes;
    private javax.swing.JTextPane ta_descripcion;
    private javax.swing.JTextField tfProveedor;
    private javax.swing.JTextField tf_precio;
    private javax.swing.JTree tr_categorias;
    // End of variables declaration//GEN-END:variables
}
