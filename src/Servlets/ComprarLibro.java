package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.Libro;
import Persistencia.CatalogoLibros;

@WebServlet("/ComprarLibro")
public class ComprarLibro extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public HttpSession session;

    public ComprarLibro() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		session = request.getSession(true);
		CatalogoLibros cl = new CatalogoLibros();
		ArrayList<Libro> items = null;
		if(session.getAttribute("carrito") != null)
		{
			items = (ArrayList<Libro>)session.getAttribute("carrito");
		}
		else
		{
			items = new ArrayList<Libro>();
		}
		items.add(cl.getOne(Integer.parseInt(request.getParameter("id"))));
		session.setAttribute("carrito", items);
		response.sendRedirect("mainPage.jsp");
	}
}