<%@page import="java.util.ArrayList"%>
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
	<div id="header">
		<h1>LibrOnLine BookStore</h1>
	</div>
	<div class="listaUsuarios">
		<table align="center">
			<thead>
				<tr>
					<th class="tablaHeader">Título</th>
					<th class="tablaHeader">Autor</th>
					<th class="tablaHeader">ISBN</th>
					<th class="tablaHeader">Precio</th>
					<th class="tablaHeader">Opciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					float total = 0;
					if(session.getAttribute("carrito") != null)
					{
						ArrayList<Libro> items = (ArrayList<Libro>)session.getAttribute("carrito");
						for(Libro l : items)
						{
							total = total + l.getPrecio();
				%>
							<tr>
								<td class="tablaData"><%= l.getTitulo() %></td>
								<td class="tablaData"><%= l.getAutor() %></td>
								<td class="tablaData"><%= l.getISBN() %></td>
								<td class="tablaData">$<%= l.getPrecio() %></td>
								<td class="tablaData"><a href="QuitarLibro?id=<%= l.getId() %>" onclick="if(!confirm('¿Esta seguro de que quiere retirar este libro de su carrito de compras?'))return false">Eliminar</a></td>
							</tr>
				<%
						}
					}
				%>
			</tbody>
		</table>
		<br/>
		<h3>Monto TOTAL: $<%= total %></h3>
		<%
			if(total>0)
			{		
		%>
				<div class="botonChico">
					<a class='botonMenu' href='formularioCompra.jsp'>Finalizar Compra</a>
				</div>
		<%
			}
		%>
		<br/><br/><a href="mainPage.jsp">Volver</a>
	</div>
	<div id="footer">
		Petrozzi, Juan Pablo 2015 Java;
	</div>
</body>
</html>