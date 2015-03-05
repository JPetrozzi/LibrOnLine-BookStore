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

@WebServlet("/EditarUsuario")
public class EditarUsuario extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public HttpSession session;

    public EditarUsuario() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		session = request.getSession(true);
		CatalogoUsuarios cu = new CatalogoUsuarios();
		Usuario u = null;
		if(request.getParameter("password").equals(request.getParameter("oldPassword")))
		{
			if(cu.isUserValid(request.getParameter("username")))
			{
				u = new Usuario();
				u.setId(Integer.parseInt(request.getParameter("id")));
				u.setUsername(request.getParameter("username"));
				u.setNombre(request.getParameter("nombre"));
				u.setEmail(request.getParameter("email"));
				u.setRol(request.getParameter("rol"));
				if(request.getParameter("newPassword").equals(""))
				{
					u.setPassword(request.getParameter("password"));
				}
				else
				{
					u.setPassword(request.getParameter("newPassword"));
				}
				cu.editarUsuario(u);
				session.setAttribute("UsuarioActual", u);
				session.setAttribute("mensajeOK", "¡Cambios Guardados!");
			}
			else
			{
				session.setAttribute("mensajeError", "La Contraseña es incorrecta.");
			}
		}
		else
		{
			session.setAttribute("mensajeError", "La Contraseña es incorrecta.");
		}
		response.sendRedirect("editarUsuario.jsp");
	}

}
