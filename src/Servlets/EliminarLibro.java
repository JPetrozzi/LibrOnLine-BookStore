package Servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Persistencia.CatalogoLibros;

@WebServlet("/EliminarLibro")
public class EliminarLibro extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "media\\img";
	//private static final String UPLOAD_DIR = "C:/Documents and Settings/Juan Pablo/Escritorio/TPFinalJava/LibrOnLine/WebContent/media/img/";

    public EliminarLibro() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CatalogoLibros cl = new CatalogoLibros();
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + UPLOAD_DIR;
		String portada = cl.getOne(Integer.parseInt(request.getParameter("id"))).getPortada();
		File fichero = new File(savePath + File.separator + portada);
		fichero.delete();
		cl.eliminarLibro(Integer.parseInt(request.getParameter("id")));
		response.sendRedirect("libros.jsp");
	}
}