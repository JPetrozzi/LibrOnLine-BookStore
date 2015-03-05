package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.Libro;
import Modelo.Usuario;
import Modelo.Venta;
import Persistencia.CatalogoVentas;

@WebServlet("/FinalizarCompra")
public class FinalizarCompra extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public HttpSession session;

    public FinalizarCompra() 
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
		CatalogoVentas cv = new CatalogoVentas();
		Venta v = new Venta();
		float total = 0;
		ArrayList<Libro> items = (ArrayList<Libro>)session.getAttribute("carrito");
		for(Libro l : items)
		{
			total = total + l.getPrecio();
		}
		v.setComprador((Usuario)session.getAttribute("UsuarioActual"));
		v.setItems(items);
		v.setFecha_venta(this.getFechaVenta());
		v.setMonto(total);
		v.setTitular(request.getParameter("titular"));
		v.setTarjeta(request.getParameter("tarjeta"));
		v.setNroTarjeta(request.getParameter("numero"));
		cv.nuevaVenta(v);
		session.setAttribute("carrito", null);
		response.sendRedirect("mainPage.jsp?CompraExitosa");
	}
	
	private String getFechaVenta()
	{
		Calendar c = Calendar.getInstance();
		int d = c.get(Calendar.DATE);
		int m = c.get(Calendar.MONTH) + 1;
		int a = c.get(Calendar.YEAR);
		return d+"/"+m+"/"+a;
	}
}