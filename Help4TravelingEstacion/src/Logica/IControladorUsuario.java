
package Logica;
import java.util.ArrayList;
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
    
}
