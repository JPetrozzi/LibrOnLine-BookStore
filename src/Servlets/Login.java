package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.Usuario;
import Persistencia.CatalogoUsuarios;

@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public HttpSession session;

    public Login() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CatalogoUsuarios cu = new CatalogoUsuarios();
		Usuario usuarioActual = cu.validarLogin(request.getParameter("usuario"), request.getParameter("password"));
		if(usuarioActual == null)
		{
			session = request.getSession(true);
			session.setAttribute("mensajeError", "Usuario o contraseña incorrectas.");
			response.sendRedirect("inicio.jsp");
		}
		else
		{
			session = request.getSession(true);
			session.setAttribute("UsuarioActual", usuarioActual);
			if(usuarioActual.getRol().equals("ADMIN"))
			{
				response.sendRedirect("ventas.jsp?estado=PENDIENTE");
			}
			else
			{
				response.sendRedirect("mainPage.jsp");
			}
		}
	}

}
