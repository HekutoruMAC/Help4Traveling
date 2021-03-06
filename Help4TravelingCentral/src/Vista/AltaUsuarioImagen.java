/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Logica.Date;
import Logica.DtUsuario;
import Logica.Fabrica;
import Logica.IControladorUsuario;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Santiago
 */
public class AltaUsuarioImagen extends javax.swing.JInternalFrame {

    private IControladorUsuario IControlador;
    private String tipo = "Cliente";
    private Path pathimg = null;

    /**
     * Creates new form altaCliente
     */
    public AltaUsuarioImagen() {
        Fabrica fabrica = Fabrica.getInstance();
        this.IControlador = fabrica.getIControladorUsuario();
        initComponents();

        ButtonGroup group = new ButtonGroup();
        group.add(rb_cliente);
        group.add(rb_proveedor);
        rb_cliente.setSelected(true);

        //JSpinner.NumberEditor ne = new JSpinner.NumberEditor(sp_anio, "####");
        //NumberFormat yearFormatter = new DecimalFormat("####");
        //yearFormatter.format(sp_anio.getModel().getValue());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fc_seleccionar_archivo = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_apellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tf_nickname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        pf_password = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        pf_conf_password = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        tf_correo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        sp_dia = new javax.swing.JSpinner();
        sp_mes = new javax.swing.JSpinner();
        sp_anio = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        btnSeleccionarImagen = new javax.swing.JButton();
        btnBorrarImagen = new javax.swing.JButton();
        jpImagen = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        l_empresa = new javax.swing.JLabel();
        tf_empresa = new javax.swing.JTextField();
        l_direccion = new javax.swing.JLabel();
        tf_direccion = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        bt_cancelar = new javax.swing.JButton();
        bt_aceptar = new javax.swing.JButton();
        rb_cliente = new javax.swing.JRadioButton();
        rb_proveedor = new javax.swing.JRadioButton();
        btLimpiar = new javax.swing.JButton();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setClosable(true);
        setIconifiable(true);
        setTitle("Nuevo Usuario");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/user-icon.png"))); // NOI18N

        jLabel1.setText("[1] Ingrese los siguientes datos obligatorios:");

        jLabel2.setText("Nombre:");

        tf_nombre.setNextFocusableComponent(tf_apellido);
        tf_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                obligatorioKeyReleased(evt);
            }
        });

        jLabel3.setText("Apellido:");

        tf_apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                obligatorioKeyReleased(evt);
            }
        });

        jLabel4.setText("Nickname:");

        tf_nickname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                obligatorioKeyReleased(evt);
            }
        });

        jLabel7.setText("Password:");

        pf_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pf_passwordActionPerformed(evt);
            }
        });
        pf_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                obligatorioKeyReleased(evt);
            }
        });

        jLabel8.setText("Confirmar:");

        pf_conf_password.setNextFocusableComponent(tf_correo);
        pf_conf_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                obligatorioKeyReleased(evt);
            }
        });

        jLabel5.setText("Correo:");

        tf_correo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                obligatorioKeyReleased(evt);
            }
        });

        jLabel6.setText("Nacimiento:");

        sp_dia.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));

        sp_mes.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));

        sp_anio.setModel(new javax.swing.SpinnerNumberModel(1980, 1916, 2016, 1));

        jLabel9.setText("[2] Seleccione una imagen opcional para el usuario:");

        btnSeleccionarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/search-icon.png"))); // NOI18N
        btnSeleccionarImagen.setText("Seleccionar");
        btnSeleccionarImagen.setFocusable(false);
        btnSeleccionarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarImagenActionPerformed(evt);
            }
        });

        btnBorrarImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete-icon.png"))); // NOI18N
        btnBorrarImagen.setText("Borrar");
        btnBorrarImagen.setEnabled(false);
        btnBorrarImagen.setFocusable(false);
        btnBorrarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarImagenActionPerformed(evt);
            }
        });

        jpImagen.setBackground(new java.awt.Color(255, 255, 255));
        jpImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jpImagen.setPreferredSize(new java.awt.Dimension(105, 105));

        javax.swing.GroupLayout jpImagenLayout = new javax.swing.GroupLayout(jpImagen);
        jpImagen.setLayout(jpImagenLayout);
        jpImagenLayout.setHorizontalGroup(
            jpImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );
        jpImagenLayout.setVerticalGroup(
            jpImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );

        jLabel10.setText("[3] Determine si el usuario es cliente o proveedor:");

        l_empresa.setText("Empresa:");
        l_empresa.setEnabled(false);

        tf_empresa.setEnabled(false);
        tf_empresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                obligatorioKeyReleased(evt);
            }
        });

        l_direccion.setText("Enlace:");
        l_direccion.setEnabled(false);

        tf_direccion.setEnabled(false);
        tf_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_direccionActionPerformed(evt);
            }
        });
        tf_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                obligatorioKeyReleased(evt);
            }
        });

        bt_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel-icon.png"))); // NOI18N
        bt_cancelar.setText("Cancelar");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        bt_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/check-icon.png"))); // NOI18N
        bt_aceptar.setText("Aceptar");
        bt_aceptar.setEnabled(false);
        bt_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_aceptarActionPerformed(evt);
            }
        });

        rb_cliente.setSelected(true);
        rb_cliente.setText("Cliente");
        rb_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_clienteActionPerformed(evt);
            }
        });

        rb_proveedor.setText("Proveedor");
        rb_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_proveedorActionPerformed(evt);
            }
        });

        btLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rename-icon.png"))); // NOI18N
        btLimpiar.setText("Limpiar");
        btLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_cancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_aceptar))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_nombre)
                                    .addComponent(tf_apellido)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(pf_conf_password, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tf_correo, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(pf_password)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(sp_dia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(sp_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(sp_anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(13, 13, 13)
                                    .addComponent(tf_nickname, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rb_cliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rb_proveedor))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnSeleccionarImagen)
                                        .addComponent(btnBorrarImagen))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jpImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(l_empresa)
                                        .addComponent(l_direccion))
                                    .addGap(30, 30, 30)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tf_empresa)
                                        .addComponent(tf_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(12, 12, 12))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {pf_conf_password, pf_password, tf_apellido, tf_correo, tf_nickname, tf_nombre});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tf_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tf_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_nickname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnSeleccionarImagen)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBorrarImagen))
                        .addComponent(jpImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(pf_conf_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rb_cliente)
                    .addComponent(rb_proveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_correo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(l_empresa)
                    .addComponent(tf_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sp_dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(sp_mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sp_anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_direccion)
                    .addComponent(tf_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_cancelar)
                    .addComponent(bt_aceptar)
                    .addComponent(btLimpiar))
                .addGap(12, 12, 12))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {pf_conf_password, pf_password, tf_apellido, tf_correo, tf_nickname, tf_nombre});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public Image extraerImagen(File fichero) {
        Image img = null;
        try {
            img = ImageIO.read(fichero).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void mostrarImagen(Image img) {
        jpImagen.getGraphics().drawImage(img, 2, 2, 100, 100, java.awt.Color.BLACK, null);
    }

    public void limpiarCampos() {
        this.tf_nombre.setText("");
        this.tf_apellido.setText("");
        this.tf_nickname.setText("");
        this.tf_correo.setText("");
        this.pf_password.setText("");
        this.pf_conf_password.setText("");
        this.tf_empresa.setText("");
        this.tf_direccion.setText("");

        this.pathimg = null;
        this.jpImagen.repaint();

        this.rb_cliente.setSelected(true);

        this.tf_nombre.grabFocus();
    }

    public void cambiarTipoUsuario(Boolean proveedor) {
        this.l_empresa.setEnabled(proveedor);
        this.tf_empresa.setEnabled(proveedor);
        this.l_direccion.setEnabled(proveedor);
        this.tf_direccion.setEnabled(proveedor);
    }

    public Boolean obligatoriosRellenos() {
        Boolean o = !((tf_nombre.getText().isEmpty())
                || (tf_apellido.getText().isEmpty())
                || (tf_nickname.getText().isEmpty())
                || (tf_correo.getText().isEmpty()));
        if (rb_proveedor.isSelected()) {
            return (o && !((tf_empresa.getText().isEmpty())
                    || (tf_direccion.getText().isEmpty())));
        }
        return o;
    }

    private void btnSeleccionarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarImagenActionPerformed
        // TODO add your handling code here:
        this.fc_seleccionar_archivo.setVisible(true);
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fc_seleccionar_archivo.setFileFilter(filtroImagen);
        //fc_seleccionar_archivo.setVisible(true);
        int eleccion = this.fc_seleccionar_archivo.showOpenDialog(null);
        //Seleccionamos el fichero
        if (eleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = this.fc_seleccionar_archivo.getSelectedFile();
            pathimg = Paths.get(fichero.getPath());
            //System.out.println(pathimg);
            mostrarImagen(extraerImagen(fichero));
            btnBorrarImagen.setEnabled(true);
        } else {
            pathimg = null;
        }
    }//GEN-LAST:event_btnSeleccionarImagenActionPerformed

    private void bt_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_aceptarActionPerformed
        String mensaje = "";

        String nombre = this.tf_nombre.getText();
        String apellido = this.tf_apellido.getText();
        String nickname = this.tf_nickname.getText();
        char[] arrayPass = this.pf_password.getPassword();
        String password = new String(arrayPass);
        char[] arrayRePass = this.pf_conf_password.getPassword();
        String confpassword = new String(arrayRePass);
        String correo = this.tf_correo.getText();

        int dia = (int) this.sp_dia.getValue();
        int mes = (int) this.sp_mes.getValue();
        int anio = (int) this.sp_anio.getValue();
        //String mes = this.sp_mes.toString();
        //String anio = this.sp_anio.toString();
        String empresa = this.tf_empresa.getText();
        String direccion = this.tf_direccion.getText();
        Date nacimiento = new Date(dia, mes, anio);

        String imagen = null;
        if (pathimg != null) {
            imagen = pathimg.toString();
            //imagen.replaceall("\\", "\\\\");
            System.out.println(imagen);
        }

        if (password.equals("")) {
            mensaje = "ERROR: Password no puede estar vacío.";
        } else if (confpassword.equals("")) {
            mensaje = "ERROR: Confirmación no puede estar vacío.";
        } else if (!confpassword.equals(password)) {
            mensaje = "ERROR: Password no coincide con Confirmación.";
        } else if (!correo.matches("^[\\w\\.=-]+@[\\w\\.-]+\\.[\\w]{2,3}$")) {
            mensaje = "ERROR: Correo con formato incorrecto.";
        } else {
            DtUsuario dtu = new DtUsuario(nombre, apellido, nickname, password, correo, nacimiento, imagen, this.tipo, empresa, direccion);
            mensaje = IControlador.altaDeUsuario(dtu);
        }
        JOptionPane.showMessageDialog(null, mensaje);
        if (!mensaje.startsWith("ERROR")) {
            this.dispose();
        }
        /*
        this.chb_proveedor.setSelected(false);
        this.tipo = "Cliente";
        this.pathimg = null;
         */
    }//GEN-LAST:event_bt_aceptarActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        this.tf_apellido.setText("");
        this.tf_correo.setText("");
        this.tf_nickname.setText("");
        this.tf_nombre.setText("");

    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void pf_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pf_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pf_passwordActionPerformed

    private void tf_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_direccionActionPerformed

    private void btnBorrarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarImagenActionPerformed
        //jp_foto.getGraphics().clearRect(0, 0, 100, 100);
        jpImagen.repaint();
        btnBorrarImagen.setEnabled(false);
    }//GEN-LAST:event_btnBorrarImagenActionPerformed

    private void rb_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_clienteActionPerformed
        this.tipo = "Cliente";
        cambiarTipoUsuario(tipo.contentEquals("Proveedor"));
        bt_aceptar.setEnabled(obligatoriosRellenos());
    }//GEN-LAST:event_rb_clienteActionPerformed

    private void rb_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_proveedorActionPerformed
        this.tipo = "Proveedor";
        cambiarTipoUsuario(tipo.contentEquals("Proveedor"));
        bt_aceptar.setEnabled(obligatoriosRellenos());
    }//GEN-LAST:event_rb_proveedorActionPerformed

    private void obligatorioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_obligatorioKeyReleased
        bt_aceptar.setEnabled(obligatoriosRellenos());
    }//GEN-LAST:event_obligatorioKeyReleased

    private void btLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLimpiarActionPerformed
        limpiarCampos();
        bt_aceptar.setEnabled(false);
    }//GEN-LAST:event_btLimpiarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLimpiar;
    private javax.swing.JButton bt_aceptar;
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton btnBorrarImagen;
    private javax.swing.JButton btnSeleccionarImagen;
    private javax.swing.JFileChooser fc_seleccionar_archivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jpImagen;
    private javax.swing.JLabel l_direccion;
    private javax.swing.JLabel l_empresa;
    private javax.swing.JPasswordField pf_conf_password;
    private javax.swing.JPasswordField pf_password;
    private javax.swing.JRadioButton rb_cliente;
    private javax.swing.JRadioButton rb_proveedor;
    private javax.swing.JSpinner sp_anio;
    private javax.swing.JSpinner sp_dia;
    private javax.swing.JSpinner sp_mes;
    private javax.swing.JTextField tf_apellido;
    private javax.swing.JTextField tf_correo;
    private javax.swing.JTextField tf_direccion;
    private javax.swing.JTextField tf_empresa;
    private javax.swing.JTextField tf_nickname;
    private javax.swing.JTextField tf_nombre;
    // End of variables declaration//GEN-END:variables
}
