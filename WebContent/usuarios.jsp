<%@page import="Persistencia.CatalogoUsuarios"%>
<%@page import="Modelo.Usuario" %>
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
	Usuario usAct = (Usuario)session.getAttribute("UsuarioActual");
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
	<div class="listaUsuarios">
			<table align="center">
				<thead>
					<tr>
						<th class="tablaHeader">Usuario</th>
						<th class="tablaHeader">Nombre y Apellido</th>
						<th class="tablaHeader">Correo Electrónico</th>
						<th class="tablaHeader">Tipo de Usuario</th>
						<th class="tablaHeader">Acciones</th>
					</tr>
				</thead>
				<tbody>
<%
	CatalogoUsuarios cu = new CatalogoUsuarios();
	for(Usuario u : cu.getAll())
	{	
%>
					<tr>
						<td class="tablaData"><%= u.getUsername() %></td>
						<td class="tablaData"><%= u.getNombre() %></td>
						<td class="tablaData"><%= u.getEmail() %></td>
						<td class="tablaData"><%= u.getRol() %></td>
						<td class="tablaData"><a href="AsignarRolUsuario?id=<%= u.getId() %>">Cambiar Rol</a> - <a href="EliminarUsuario?id=<%= u.getId() %>" onclick="if(!confirm('¿Esta seguro de que quiere eliminar este usuario?'))return false">Eliminar</a></td>
					</tr>
<%
	}
%>
				</tbody>
			</table>
	</div>
	<div id="footer">
		Petrozzi, Juan Pablo 2015 Java;
	</div>
</body>
</html>