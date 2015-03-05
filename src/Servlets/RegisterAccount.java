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

@WebServlet("/RegisterAccount")
public class RegisterAccount extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public HttpSession session;
	
    public RegisterAccount() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CatalogoUsuarios cu = new CatalogoUsuarios();
		if(cu.isUserValid(request.getParameter("usuario")))
		{
			Usuario us = new Usuario();
			us.setUsername(request.getParameter("usuario"));
			us.setPassword(request.getParameter("password"));
			us.setRol("CLIENTE");
			us.setNombre(request.getParameter("nombre"));
			us.setEmail(request.getParameter("email"));
			cu.RegistrarUsuario(us);
			session = request.getSession(true);
			session.setAttribute("mensajeOK", "¡Usuario registrado correctamente!");
			response.sendRedirect("inicio.jsp");
		}
		else
		{
			session = request.getSession(true);
			session.setAttribute("mensajeError", "El Ususario ya esta en uso, pruebe otro.");
			response.sendRedirect("register.jsp");
		}
		
	}
}