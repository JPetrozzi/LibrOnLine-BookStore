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

@WebServlet("/QuitarLibro")
public class QuitarLibro extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public HttpSession session;

    public QuitarLibro() 
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
		ArrayList<Libro> items = (ArrayList<Libro>)session.getAttribute("carrito");
		ArrayList<Libro> update = new ArrayList<Libro>();
		for(Libro l : items)
		{
			if(l.getId() != Integer.parseInt(request.getParameter("id")))
			{
				update.add(l);
			}
		}
		session.setAttribute("carrito", update);
		response.sendRedirect("compra.jsp");
	}
}