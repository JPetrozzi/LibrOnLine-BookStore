<%@page import="Persistencia.CatalogoLibros"%>
<%@page import="Modelo.Libro" %>
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
	<div id="subMenu">
		<a class="botonMenu" href="agregarLibro.jsp">Ingesar nuevo libro</a>
	</div>
	<div class="listaUsuarios">
			<table align="center">
				<thead>
					<tr>
						<th class="tablaHeader">Título</th>
						<th class="tablaHeader">Autor</th>
						<th class="tablaHeader">ISBN</th>
						<th class="tablaHeader">Precio</th>
						<th class="tablaHeader">Stock</th>
						<th class="tablaHeader">Estado</th>
						<th class="tablaHeader">Opciones</th>
					</tr>
				</thead>
				<tbody>
<%
	CatalogoLibros cl = new CatalogoLibros();
	for(Libro l : cl.getAll())
	{	
%>
					<tr>
						<td class="tablaData"><%= l.getTitulo() %></td>
						<td class="tablaData"><%= l.getAutor() %></td>
						<td class="tablaData"><%= l.getISBN() %></td>
						<td class="tablaData"><%= l.getPrecio() %></td>
						<td class="tablaData"><%= l.getStock() %></td>
						<td class="tablaData"><%= l.getEstado() %></td>
						<td class="tablaData"><a href="editarLibro.jsp?id=<%= l.getId() %>">Editar</a> - <a href="EliminarLibro?id=<%= l.getId() %>" onclick="if(!confirm('¿Esta seguro de que quiere eliminar este libro?'))return false">Eliminar</a></td>
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