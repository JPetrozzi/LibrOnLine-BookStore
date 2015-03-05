<%@page import="Modelo.Usuario" %>
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
		if(form.elements["username"].value == "" || form.elements["oldPassword"].value == "" || form.elements["nombre"].value == "" || form.elements["email"].value == "")
			{
				rta = false;
				window.alert("[ERROR] No puede dejar los campos Usuario, Nombre y Apellido, Correo Electrónico y Contraseña actual en blanco .");
			}
			else if(!(/^[a-z][\w.-]+@\w[\w.-]+\.[\w.-]*[a-z][a-z]$/i.test(form.elements["email"].value)))
				{
					rta = false;
					window.alert("[ERROR] E-Mail invalio, pruebe otro por favor.");
				}
		return rta;
	}
</script>
</head>
<body>
<%
	session = request.getSession();
	Usuario u = (Usuario)session.getAttribute("UsuarioActual");
%>
	<div>
	<%
		session = request.getSession();
		if(session.getAttribute("mensajeOK")!=null)
		{
			out.println("<p style=\"color: #2FC61F;\">"+(String)session.getAttribute("mensajeOK")+"</p>");
			session.setAttribute("mensajeOK", null);
		}
		if(session.getAttribute("mensajeError")!=null)
		{
			out.println("<p style=\"color: #F70707;\">"+(String)session.getAttribute("mensajeError")+"</p>");
			session.setAttribute("mensajeError", null);
		}
	%>
	</div>
	<div id="tablaLogin" class="marco">
	<form action="EditarUsuario" method="post" onsubmit="return validacion(this)">
		<table align="center">
			<tr>
				<td><label>Usuario: </label></td><td><input type="text" name="username" class="textBox" value="<%= u.getUsername() %>"/></td>
			</tr>
			<tr>
				<td><label>Nombre y Apellido: </label></td><td><input type="text" name="nombre" class="textBox" value="<%= u.getNombre() %>"/></td>
			</tr>
			<tr>
				<td><label>Correo Electrónico: </label></td><td><input type="text" name="email" class="textBox" value="<%= u.getEmail() %>"/></td>
			</tr>
			<tr>
				<td><label>Nueva Contraseña: </label></td><td><input type="password" name="newPassword" class="textBox"/></td>
			</tr>
			<tr>
				<td><label>Contraseña Actual: </label></td><td><input type="password" name="oldPassword" class="textBox"/></td>
			</tr>
			<tr>
				<td><input type="hidden" name="id" value="<%= u.getId() %>"><input type="hidden" name="password" value="<%= u.getPassword() %>"></td><td><input type="hidden" name="rol" value="<%= u.getRol() %>"/></td>
			</tr>
			<tr>
				<td colspan="2"><br><input type="submit" value="Guardar Cambios" class="boton"/></td>
			</tr>
			<tr>
				<td colspan="2"><br>
				<%
					if(u.getRol().equals("ADMIN"))
					{
						%><a href="usuarios.jsp">Volver</a><%
					}
					else
					{
						%><a href="mainPage.jsp">Volver</a><%
					}
				%>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>