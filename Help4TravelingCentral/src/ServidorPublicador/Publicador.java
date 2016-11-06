/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ServidorPublicador;
import Logica.*;
import java.sql.SQLException;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;
/**
 *
 * @author Leonardo
 */
@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class Publicador {

    private ManejadorCliente mc = ManejadorCliente.getInstance();
    private Endpoint endpoint = null;
    //Constructor
    public Publicador(){}

    //Operaciones las cuales quiero publicar

    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:9128/publicador", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }

    @WebMethod
    public DtUsuario devolverUsuario(String nickname) {
        DtUsuario u = mc.getDtUsuario(nickname);
        return u;
    }
    
            
    @WebMethod
    public boolean Registrar(String nickname, String nombre, String apellido, String password, String email, String imagen, String fecha) {
        return Fabrica.getInstance().getIControladorUsuario().Registrar(nickname, nombre, apellido, password, email, imagen, fecha);
    }

}
