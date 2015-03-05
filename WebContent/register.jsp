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
		if(form.elements["usuario"].value == "" || form.elements["password"].value == "" || form.elements["confirmPassword"].value == "" || form.elements["nombre"].value == "" || form.elements["email"].value == "")
			{
				rta = false;
				window.alert("[ERROR] Complete todos los campos del formulario por favor.");
			}
			else if(form.elements["password"].value != form.elements["confirmPassword"].value)
				{
					rta = false;
					window.alert("[ERROR] Las contraseñas ingresadas no coinciden. Reescribalas por favor.");
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
	<div id="header">
		<h1>LibrOnLine BookStore</h1>
	</div>
	<%
		session = request.getSession();
		if(session.getAttribute("mensajeError")!=null)
		{
			out.println("<p style=\"color: #FF0707;\">"+(String)session.getAttribute("mensajeError")+"</p>");
			session.setAttribute("mensajeError", null);
		}
	%>
	<div id="tablaLogin" class="marco">
		<form action="RegisterAccount" method="post" onsubmit="return validacion(this)">
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
					<td>Confirmar Contraseña: </td>
					<td><input type="password" name="confirmPassword" class="textBox"/></td>
				</tr>
				<tr>
					<td>Nombre y Apellido: </td>
					<td><input type="text" name="nombre" class="textBox"/></td>
				</tr>
				<tr>
					<td>Correo Electrónico: </td>
					<td><input type="text" name="email" class="textBox"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><br/><input type="submit" value="Crear Cuenta" class="boton"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><br/><br/><a href="inicio.jsp">Volver</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="footer">
		Petrozzi, Juan Pablo 2015 Java;
	</div>
</body>
</html>