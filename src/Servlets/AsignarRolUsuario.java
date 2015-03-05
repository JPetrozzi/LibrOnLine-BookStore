package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Usuario;
import Persistencia.CatalogoUsuarios;

@WebServlet("/AsignarRolUsuario")
public class AsignarRolUsuario extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public AsignarRolUsuario() 
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
		Usuario u = cu.getOne(Integer.parseInt(request.getParameter("id")));
		if(u.getRol().equals("CLIENTE"))
		{
			u.setRol("ADMIN");
		}
		else
		{
			u.setRol("CLIENTE");
		}
		cu.editarUsuario(u);
		response.sendRedirect("usuarios.jsp");
	}

}
