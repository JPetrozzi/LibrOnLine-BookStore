package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Venta;
import Persistencia.CatalogoVentas;

@WebServlet("/EditarVenta")
public class EditarVenta extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    public EditarVenta() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CatalogoVentas cv = new CatalogoVentas();
		Venta v = cv.getOne(Integer.parseInt(request.getParameter("id")));
		if(v.getEstado().equals("PENDIENTE"))
		{
			v.setEstado("ENTREGADO");
		}
		else 
		{
			v.setEstado("PENDIENTE");
		}
		cv.editarVenta(v);
		response.sendRedirect("ventas.jsp?estado=PENDIENTE");
	}
}