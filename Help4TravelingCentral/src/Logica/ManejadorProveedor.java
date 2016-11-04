/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Leonardo
 */
public class ManejadorProveedor {

    //Clase que conserva la colección global de los Usuarios Proveedores del Sistema
    private Map<String, Proveedor> proveedoresNK;
    private static ManejadorProveedor instancia = null;
    private Conexion conexion;
    private String sql;

    private ManejadorProveedor() {
        proveedoresNK = new HashMap<String, Proveedor>();
    }

    public static ManejadorProveedor getInstance() {
        if (instancia == null) {
            instancia = new ManejadorProveedor();
        }
        return instancia;
    }

    public void agregarProveedor(Proveedor prov) {
        String nk = prov.getNickname();
        proveedoresNK.put(nk, prov);
    }

    public boolean existeNickname(String nickname) {
        boolean existe = false;
        ResultSet rs;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        //String sql1 = "SELECT * FROM mydb.usuarios WHERE Nickname='" + nickname + "' AND Empresa <> NULL AND Link <> NULL)";
        String sql1 = "SELECT * FROM help4traveling.usuarios WHERE nickname='" + nickname + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql1);
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            con.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("No existe proveedor :(");
        }
        return existe;

        //return proveedoresNK.containsKey(nickname);
    }

    public boolean existeCorreo(String correo) {
        boolean existe = false;
        ResultSet rs;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        String sql1 = "SELECT * FROM help4traveling.usuarios WHERE email='" + correo + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql1);
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No existe correo :(");
        }
        /*Iterator<Proveedor> iter = this.proveedoresNK.values().iterator();
	while ((iter.hasNext()) && (!existe)) {
            Proveedor prov = iter.next();
            if (prov.getCorreo() == correo)
		existe = true;
	}*/
        return existe;
    }

    public Proveedor obtenerProveedor(String nickname) {
        ResultSet rs;
        Proveedor p = null;
        //Conexion conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        //String sql1 = "SELECT * FROM mydb.usuarios WHERE Nickname='" + nickname + "' AND Empresa <> NULL AND Link <> NULL)";
        String sql1 = "SELECT * FROM help4traveling.usuarios WHERE nickname='" + nickname + "'";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql1);
            if (rs.next()) {
                Date fecha = new Date();
                p = new Proveedor(rs.getString("nombre"), rs.getString("apellido"), rs.getString("nickname"), rs.getString("password"), rs.getString("email"), fecha, "imagen", "empresa", "link");
            }
            rs.close();
            con.close();
            st.close();
        } catch (SQLException e) {
            System.out.println("No obtuve proveedor :(");
            System.err.println(e.getMessage());
        }
        return p;
        //return ((Proveedor) proveedoresNK.get(nickname));
    }

    public DtUsuario getDtProveedor(String nickname) {
        ResultSet rsUsu, rsProv, rsImg;
        DtUsuario nuevo = null;
        Statement stUsu, stProv, stImg;
        try {
            Connection con = Conexion.getInstance().getConnection();
            stUsu = con.createStatement();
            String sql = "SELECT * FROM help4traveling.usuarios WHERE nickname='" + nickname + "'";
            rsUsu = stUsu.executeQuery(sql);
            stProv = con.createStatement();
            sql = "SELECT * FROM help4traveling.proveedores WHERE nickname='" + nickname + "'";
            rsProv = stProv.executeQuery(sql);
            if (rsUsu.next() && rsProv.next()) {
                String nombre = rsUsu.getString("nombre");
                String apellido = rsUsu.getString("apellido");
                String correo = rsUsu.getString("email");
                Date fecha = new Date(rsUsu.getString("fechaNac"));
                String imagen = null;
                stImg = con.createStatement();
                sql = "SELECT * FROM help4traveling.usuariosimagenes WHERE usuario='" + nickname + "'";
                rsImg = stImg.executeQuery(sql);
                if (rsImg.next()) {
                    imagen = rsImg.getString("imagen");
                }
                rsImg.close();
                stImg.close();
                String empresa = rsProv.getString("empresa");
                String enlace = rsProv.getString("link");
                nuevo = new DtUsuario(nombre, apellido, nickname, "password", correo, fecha, imagen, "Proveedor", empresa, enlace);
            }
            rsProv.close();
            stProv.close();
            rsUsu.close();
            stUsu.close();
            con.close();
            System.out.println("Se obtuvo Proveedor :)");
        } catch (SQLException e) {
            System.out.println("No obtuve Proveedor :(");
            System.err.println(e.getMessage());
        }
        return nuevo;
    }

    public ArrayList<DtUsuario> listarProveedores() {

        ResultSet rsProveedores;

        //conexion = new Conexion();
        Connection con = Conexion.getInstance().getConnection();
        Statement st;

        sql = "SELECT * FROM help4traveling.usuarios U, help4traveling.proveedores P WHERE U.nickname = P.nickname";

        try {
            st = con.createStatement();
            rsProveedores = st.executeQuery(sql);

            //String tabla = rsProveedores.toString();
            System.out.println("realize el executeQuery" /*+ tabla*/);

            System.out.println("llegue");

            while (rsProveedores.next()) {

                String nombre = rsProveedores.getString("nombre");

                String apellido = rsProveedores.getString("apellido");
                String nickname = rsProveedores.getString("nickname");
                String password = rsProveedores.getString("password");
                String correo = rsProveedores.getString("email");
                String fecha = rsProveedores.getString("fechaNac");
                Date nacimiento = new Date(fecha);
                String empresa = rsProveedores.getString("empresa");
                System.out.println(empresa);
                String link = rsProveedores.getString("link");
                System.out.println(link);
                String imagen = rsProveedores.getString("imagen");;
                //String empresa = rsProveedores.getString("Empresa");
                //String direccion = rsProveedores.getString("Direccion");
                Proveedor nuevo = new Proveedor(nombre, apellido, nickname, password, correo, nacimiento, imagen, empresa, link);
                proveedoresNK.put(nickname, nuevo);
            }
            rsProveedores.close();
            con.close();
            st.close();

            System.out.println("Usuarios cargados :)");
        } catch (SQLException e) {
            System.out.println("No pude cargar usuarios :(");
        }

        ArrayList<DtUsuario> listaProveedores = new ArrayList<>();
        Iterator<Proveedor> iter = this.proveedoresNK.values().iterator();
        while (iter.hasNext()) {
            Proveedor pr = iter.next();
            listaProveedores.add(pr.getDtProveedor());
        }
        return listaProveedores;
    }

    public String persistirProveedor(Proveedor prov) {
        //Conexion conexion;
        System.out.println("Entro a persistir");
        Connection con = Conexion.getInstance().getConnection();
        //conexion = new Conexion();
        String mensaje = "Se dio de alta al Usuario Proveedor.";
        //Connection con = conexion.getConnection();
        Statement st;
        if (!existeNickname(prov.getNickname())) {
            String imagen = prov.getImagen();
            if (imagen != null) {
                imagen = "'" + imagen + "'";
                imagen = imagen.replace("\\", "\\\\");
            }
            String fecha = String.valueOf(prov.getNacimiento().getAno()) + "-" + String.valueOf(prov.getNacimiento().getMes()) + "-" + String.valueOf(prov.getNacimiento().getDia());
            String sqlau = "INSERT INTO help4traveling.usuarios "
                    + "(nickname,nombre,apellido,password,email,imagen,fechaNac) "
                    + "VALUES ('" + prov.getNickname() + "','" + prov.getNombre() + "','" + prov.getApellido() + "','" + prov.getPassword() + "','" + prov.getCorreo()
                    + "'," + imagen + ",'" + fecha + "')";
            System.out.println(sqlau);
            String sqlap = "INSERT INTO help4traveling.proveedores (nickname,empresa,link) VALUES ('" + prov.getNickname() + "','" + prov.getEmpresa() + "','" + prov.getLink() + "')";
            String sqlai = "INSERT INTO help4traveling.usuariosimagenes (usuario,imagen) VALUES ('" + prov.getNickname() + "','" + prov.getImagen() + "')";
            try {
                st = con.createStatement();
                //System.out.println("antes de insertar");
                st.executeUpdate(sqlau);
                st.executeUpdate(sqlap);
                if (imagen != null) {
                    st.executeUpdate(sqlai);
                }
                con.close();
                st.close();
                System.out.println("INSERTE :)");
            } catch (SQLException e) {
                mensaje = "ERROR: No se pudo insertar.";
                System.out.println("No pude INSERTAR :(");
            }
        } else {
            mensaje = "ERROR: El Nickname ingresado ya existe.";
        }
        return mensaje;
    }

    // Servidor Central ========================================================
    public Boolean existeProveedor(String nickname) {
        Boolean esProv = false;
        ResultSet rs;
        Statement st;
        try {
            Connection con = Conexion.getInstance().getConnection();
            sql = "SELECT * FROM help4traveling.proveedores WHERE nickname='" + nickname + "'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                esProv = true;
            }
            rs.close();
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("No pude obtener Proveedor :(");
        }
        return esProv;
    }

    public ArrayList<DtPromocion> listarPromocionesProveedor(String prov) {
        Connection con = Conexion.getInstance().getConnection();
        Statement st;
        ResultSet rs;
        sql = "SELECT * FROM help4traveling.promociones WHERE proveedor= '" + prov + "'";
        ArrayList<DtPromocion> promociones = new ArrayList<DtPromocion>();
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);

            DtPromocion prom;
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String proveedor = rs.getString("proveedor");
                String descuento = rs.getString("descuento");
                String total = rs.getString("total");
                prom = new DtPromocion(nombre, proveedor, descuento, total);
                promociones.add(prom);
            }
            rs.close();
            con.close();
            st.close();

        } catch (SQLException e) {
            System.out.println("No se encontraron promociones.");
            System.out.println(e.getMessage());
        }
        return promociones;
    }

}
