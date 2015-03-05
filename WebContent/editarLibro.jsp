<%@page import="Persistencia.CatalogoLibros"%>
<%@page import="Modelo.Libro" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibrOnLine-BookStore</title>
<link rel="stylesheet" type="text/css" href="MyStyle.css" />
<script type="text/javascript">
	function validacion(form)
	{
		var rta = true;
		if(form.elements["isbn"].value == "" || form.elements["titulo"].value == "" || form.elements["autor"].value == "" || form.elements["precio"].value == "" || form.elements["stock"].value == "")
			{
				rta = false;
				window.alert("[ERROR] Complete todos los campos por favor.");
			}
		else if(!/^([0-9])*$/.test(form.elements["stock"].value))
			{
				rta = false;
				window.alert("[ERROR] Utilice un valor numérico entero para indicar el Stock.");
			}
		else if(!/^([0-9])*[.]?[0-9]*$/.test(form.elements["precio"].value))
			{
				rta = false;
				window.alert("[ERROR] Utilice un valor numérico para indicar el Precio.");
			}
		return rta;
	}
</script>
</head>
<body>
<%
	CatalogoLibros cl = new CatalogoLibros();
	Libro l = cl.getOne(Integer.parseInt(request.getParameter("id")));
	session = request.getSession();
	if(session.getAttribute("mensajeOK")!=null)
	{
		out.println("<p style=\"color: #2FC61F;\">"+(String)session.getAttribute("mensajeOK")+"</p>");
		session.setAttribute("mensajeOK", null);
	}
	if(session.getAttribute("mensajeError")!=null)
	{
		out.println("<p style=\"color: #FF0707;\">"+(String)session.getAttribute("mensajeError")+"</p>");
		session.setAttribute("mensajeError", null);
	}
%>
	<div id="tablaLogin" class="marco">
		<form action="EditarLibro" enctype="multipart/form-data" method="post" onsubmit="return validacion(this)">
			<table align="center">
				<tr>
					<td colspan="2"><img alt="Imagen de Portada" src="media\img\<%= l.getPortada() %>"/></td>
				</tr>
				<tr>
					<td><label>ISBN: </label></td><td><input type="text" name="isbn" value="<%= l.getISBN() %>" class="textBox"/></td>
				</tr>
				<tr>
					<td><label>Título: </label></td><td><input type="text" name="titulo" value="<%= l.getTitulo() %>" class="textBox"/></td>
				</tr>
				<tr>
					<td><label>Autor: </label></td><td><input type="text" name="autor" value="<%= l.getAutor() %>" class="textBox"/></td>
				</tr>
				<tr>
					<td><label>Portada: </label></td><td><input type="file" name="portada"/></td>
				</tr>
				<tr>
					<td><label>Precio: </label></td><td><input type="text" name="precio" value="<%= l.getPrecio() %>" class="textBox" style="text-align: right; width: 80px;"/>$</td>
				</tr>
				<tr>
					<td><label>Stock: </label></td><td><input type="text" name="stock" value="<%= l.getStock() %>" class="textBox"/></td>
				</tr>
				<tr>
					<td><label>Estado: </label></td>
					<td>
					<%
						if(l.getEstado().equals("DISPONIBLE"))
						{
					%>
						<select name="estado" class="select"><option value="DISPONIBLE" selected>Disponible<option value="NODISPONIBLE">No Disponible</select>
					<%
						}else{
					%>
						<select name="estado" class="select"><option value="DISPONIBLE">Disponible<option value="NODISPONIBLE" selected>No Disponible</select>
					<% 
						}
					%>
					</td>
				</tr>
				<tr>
					<td colspan="2"><br><input type="submit" value="Guardar Cambios" class="boton"/></td>
				</tr>
				<tr>
					<td colspan="2"><br><a href="libros.jsp">Volver</a></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="<%= l.getId() %>">
		</form>
	</div>
	<div id="footer">
		Petrozzi, Juan Pablo 2015 Java;
	</div>
</body>
</html>