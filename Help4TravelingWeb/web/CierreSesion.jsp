<%--
    Document   : CierreSesion
    Created on : 16-oct-2016, 21:34:39
    Author     : Leonardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        HttpSession sesion = request.getSession(true);
        Boolean esProv = (Boolean) session.getAttribute("esProv");
        //Cerrar sesion
        sesion.invalidate();
        if (esProv) {
            response.sendRedirect("Movil.Signin.jsp");
        } else {
            response.sendRedirect("InicioSesion.jsp");
        }
    %>
</html>
