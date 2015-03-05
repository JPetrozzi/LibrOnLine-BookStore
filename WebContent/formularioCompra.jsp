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
		if(form.elements["titular"].value == "" || form.elements["numero"].value == "")
			{
				rta = false;
				window.alert("[ERROR] Complete todos los campos del formulario por favor.");
			}
			else if(!(/^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$/.test(form.elements["numero"].value)))
				{
					rta = false;
					window.alert("[ERROR] Ingrese un nro. de tarjeta de credito de la forma 0000-0000-0000-0000.");
				}
		return rta;
	}
</script>
</head>
<body>
	<div id="header">
		<h1>LibrOnLine BookStore</h1>
	</div>
	<div id="tablaLogin" class="marco">
		<form action="FinalizarCompra" method="post" onsubmit="return validacion(this)">
			<table align="center">
				<tr>
					<td colspan="2" align="center"><p id="tituloLogin">Datos de la Tarjeta</p></td>
				</tr>
				<tr>
					<td>Titular: </td>
					<td><input type="text" name="titular" class="textBox" value="Nombre y Apellido"/></td>
				</tr>
				<tr>
					<td>Tarjeta: </td>
					<td><select name="tarjeta" class="select"><option value="Visa">Visa
															  <option value="MasterCard">MasterCard
															  <option value="American Express">American Express
															  <option value="Naranja">Naranja
															  <option value="Italcred">Italcred
															  <option value="Nativa">Nativa</select></td>
				</tr>
				<tr>
					<td>Nro. Tarjeta: </td>
					<td><input type="text" name="numero" class="textBox" value="0000-0000-0000-0000"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><br><br><input type="submit" class="boton" value="COMPRAR"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><br><br><a href="compra.jsp">Volver</a></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="footer">
		Petrozzi, Juan Pablo 2015 Java;
	</div>
</body>
</html>