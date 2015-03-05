<%@page import="Modelo.Libro"%>
<%@page import="Modelo.Venta"%>
<%@page import="Persistencia.CatalogoVentas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibrOnLine-BookStore</title>
<link rel="stylesheet" type="text/css" href="MyStyle.css" />
</head>
<body>
<%
	session = request.getSession();
	Modelo.Usuario usAct = (Modelo.Usuario)session.getAttribute("UsuarioActual");
%>
	<p class="usuarioLogeado">Usuario: <%= usAct.getUsername() %> - 
							  <a href="editarUsuario.jsp?id=<%= usAct.getId() %>">Editar Perfil</a> - 
							  <a href="CerrarSesion" onclick="if(!confirm('Usted esta saliendo de LibrOnLine. ¿Esta seguro?'))return false">Cerrar Sesion</a></p>
	<div id="header">
		<h1>LibrOnLine BookStore</h1>
	</div>
	<div id="menu">
		<a class="botonMenu" href="usuarios.jsp">Usuarios</a>
		<a class="botonMenu" href="libros.jsp">Libros</a>
		<a class="botonMenu" href="ventas.jsp?estado=PENDIENTE">Ventas</a>
	</div>
	<div id="subMenu">
		<a class="botonMenu" href="ventas.jsp?estado=PENDIENTE">Ver Pendientes</a>
		<a class="botonMenu" href="ventas.jsp?estado=ENTREGADO">Ver Entregadas</a>
	</div>
	<div class="listaUsuarios">
		<table align="center">
			<%
				CatalogoVentas cv = new CatalogoVentas();
				for(Venta v : cv.getAll(request.getParameter("estado")))
				{
			%>
					<tr>
						<td class="tablaHeader">Usuario:</td>
					</tr>
					<tr>
						<td colspan="2" class="tablaData"><%= v.getComprador().getUsername() %></td>
						<td colspan="3" class="tablaData"><%= v.getComprador().getEmail() %></td>
					</tr>
					<tr>
						<td class="tablaHeader">Tarjeta:</td>
					</tr>
					<tr>
						<td colspan="2" class="tablaData"><%= v.getTitular() %></td>
						<td colspan="1" class="tablaData"><%= v.getTarjeta() %></td>
						<td colspan="2" class="tablaData"><%= v.getNroTarjeta() %></td>	
					</tr>
					<tr>
						<td class="tablaHeader">Compra:</td>
					</tr>
					<tr>
						<td colspan="1" class="tablaData">Fecha: <%= v.getFecha_venta() %></td>
						<td colspan="3" class="tablaData">Libros: <br><br>
						<%
							for(Libro l : v.getItems())
							{
						%>
								<%= l.getTitulo() %> - <%= l.getAutor() %><br>
						<%
							}
						%>
						</td>
						<td colspan="1" class="tablaData">Monto: $<%= v.getMonto() %></td>
					</tr>
					<tr>
						<td colspan="3" class="tablaData"><br><br><a href="EditarVenta?id=<%= v.getId() %>">Cambiar Estado</a></td>
						<%
							if(request.getParameter("estado").equals("PENDIENTE"))
							{
						%>
							<td colspan="2" class="tablaData"><br><br><a href="EliminarVenta?id=<%= v.getId() %>" onclick="if(!confirm('¿Esta seguro de que quiere eliminar esta venta?'))return false">Eliminar</a></td>
						<%
							}
						%>
					</tr>
					<tr>
						<td colspan="5"><hr/></td>
					</tr>
			<%
				}
			%>
		</table>
	</div>
	<div id="footer">
		Petrozzi, Juan Pablo 2015 Java;
	</div>
</body>
</html>