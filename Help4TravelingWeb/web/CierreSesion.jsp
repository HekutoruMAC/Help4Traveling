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
      //Cerrar sesion
      sesion.invalidate();      
      response.sendRedirect("InicioSesion.jsp");  
    %>
</html>
