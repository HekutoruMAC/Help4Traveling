/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PruebaModelo;

import Logica.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

/**
 *
 * @author yaman
 */
public class Consultas {

    public boolean Autenticacion(HttpSession sesion) throws SQLException {
        Connection con = Conexion.getInstance().getConnection();
        try (Statement st = con.createStatement()) {
            ResultSet rs = null;
            String Consulta = "SELECT * FROM usuarios";
            rs = st.executeQuery(Consulta);
            while (rs.next()) {
                if (sesion.getAttribute("nickname").equals(rs.getString("nickname")) && sesion.getAttribute("password").equals(rs.getString("password"))) {
                    sesion.setAttribute("nombre", rs.getString("nombre"));
                    sesion.setAttribute("apellido", rs.getString("apellido"));
                    sesion.setAttribute("email", rs.getString("email"));
                    sesion.setAttribute("fechaNac", rs.getString("fechaNac"));
                    rs.close();
                    st.close();
                    return true;
                }
            }
            rs.close();
        }
        return false;
    }

}
