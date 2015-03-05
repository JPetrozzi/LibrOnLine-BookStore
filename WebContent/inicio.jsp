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
		if(form.elements["usuario"].value == "" || form.elements["password"].value == "")
			{
				rta = false;
				window.alert("[ERROR] Ingrese Usuario y Contraseña.")
			}
		return rta;
	}
</script>
</head>
<body>
	<div id="header">
		<h1>LibrOnLine BookStore</h1>
	</div>
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
	<div id="tablaLogin" class="marco">
		<form action="Login" method="post" onsubmit="return validacion(this)">
			<table align="center">
				<tr>
					<td colspan="2" align="center"><p id="tituloLogin">Bienvenido</p></td>
				</tr>
				<tr>
					<td>Usuario: </td>
					<td><input type="text" name="usuario" class="textBox"/></td>
				</tr>
				<tr>
					<td>Contraseña: </td>
					<td><input type="password" name="password" class="textBox"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><br/><input type="submit" value="Ingresar" class="boton"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><br/><br/><a href="register.jsp">Click aquí para registrarse</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="footer">
		Petrozzi, Juan Pablo 2015 Java;
	</div>
</body>
</html>