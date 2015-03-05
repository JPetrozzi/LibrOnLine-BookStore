<%@page import="java.util.ArrayList"%>
<%@page import="Persistencia.CatalogoLibros"%>
<%@page import="Modelo.Usuario" %>
<%@page import="Modelo.Libro" %>
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
		<a href="compra.jsp" class="botonMenu">Ver Carro de Compras</a>
	</div>
	<%
		if(session.getAttribute("carrito") != null)
		{
			ArrayList<Libro> compra = (ArrayList<Libro>)session.getAttribute("carrito");
	%>
			<p style="font-size: small; text-align: right; margin-right: 5%;">Productos agregados al carro: <%= compra.size() %></p>
	<%
		}
	%>
	<div class="listaDisponibles">
		<p id="tituloDisponibles">Libros Disponibles:</p>
		<%
			CatalogoLibros cl = new CatalogoLibros();
			for(Libro l : cl.getDisponibles())
			{
		%>
			<a class="enlaceLibro" href="mainPage.jsp?id=<%= l.getId() %>"><%= l.getTitulo() %></a><br><br>
		<%
			}
		%>
	</div>
	<div class="visorLibro">
		<%
			if(request.getParameter("id") != null)
			{
				Libro l = cl.getOne(Integer.parseInt(request.getParameter("id")));
		%>
				<form action="ComprarLibro" method="post">
					<img class="imagen" alt="Portada del Libro" src="media\img\<%= l.getPortada() %>"/>
					<h2 class="tituloLibro"><%= l.getTitulo() %></h2>
					<h4 align="left"><%= l.getAutor() %></h4>
					<h6 align="left">ISBN: <%= l.getISBN() %></h6>
					<h3 class="precioLibro">Precio: $<%= l.getPrecio() %></h3>
					<input type="hidden" name="id" value="<%= l.getId() %>"/>
					<input type="submit" value="Agregar al Carro" class="boton"/>
				</form>
		<%
			}
			else
			{
				if(request.getParameter("CompraExitosa") != null)
				{
		%>
					<h1 style="color: green;">¡Compra Exitosa!</h1>
					<h4>Ante cualquier inconveniente en el cobro de su compra nos estaremos comunicando con usted a través de su email de contacto.</h4>	
		<%
				}
				else
				{
		%>
					<h1 class="mensaje">Seleccione un libro de la lista para ver los detalles.</h1>
		<%
				}
			}
		%>
	</div>
	<div id="footer">
		Petrozzi, Juan Pablo 2015 Java;
	</div>
</body>
</html>