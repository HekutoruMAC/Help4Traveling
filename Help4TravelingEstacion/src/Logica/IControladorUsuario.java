package Logica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// Comentario para que me reconozca los cambios y pueda comitear...

/**
 *
 * @author yaman
 */
public interface IControladorUsuario {

    public abstract String ingresarDatosUsuario(DtUsuario dtu);

    public abstract String altaUsuarioCliente(DtUsuario dtu);

    public abstract void cancelarAltaUsuario();

    public abstract String altaUsuarioProveedor(DtUsuario dtu);

    public abstract String altaDeUsuario(DtUsuario dtu);

    public abstract ArrayList<DtUsuario> listarClientes();

    public abstract ArrayList<DtReserva> listarReservasCliente(DtUsuario dtu);

    public abstract ArrayList<DtUsuario> listarProveedores();

    public abstract ArrayList<DtServicio> listarServicioProveedor(DtUsuario dtu);
    //public abstract Cliente obtenerCliente(String nick);
    // public abstract void verInfoDeProveedor();
    //public abstract ArrayList<DtUsuario> setClientesDB();
    //public abstract ArrayList<DtUsuario> setProveedoresDB();

    // Servidor Central =====================================================
    public Boolean existeProveedor(String nickname);

    public ArrayList<DtPromocion> listarPromocionesProveedor(String prov);

    public boolean Comprobacion(String nickname, String email) throws SQLException;

    public boolean Registrar(String nickname, String nombre, String apellido, String password, String email, String imagen, String fecha) throws SQLException;

    public DtUsuario getDtUsuario(String nickname);

    public List<DtUsuario> listarUsuariosSistema() throws SQLException;
}
