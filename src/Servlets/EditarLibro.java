package Servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Modelo.Libro;
import Persistencia.CatalogoLibros;

@WebServlet("/EditarLibro")
@MultipartConfig(fileSizeThreshold=1024*1024*10, maxFileSize=1024*1024*50, maxRequestSize=1024*1024*100, location="/")
public class EditarLibro extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public HttpSession session;
	private static final String UPLOAD_DIR = "media\\img";
	//private static final String UPLOAD_DIR = "C:/Documents and Settings/Juan Pablo/Escritorio/TPFinalJava/LibrOnLine/WebContent/media/img/";

    public EditarLibro() 
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
		CatalogoLibros cl = new CatalogoLibros();
		if(!request.getParameter("isbn").equals(cl.getOne(Integer.parseInt(request.getParameter("id"))).getISBN()))
		{
			if(cl.isIsbnValid(request.getParameter("isbn")))
			{
				this.cargarLibro(request, response);
				session.setAttribute("mensajeOK", "¡Cambios Guardados!");
			}
			else
			{
				session.setAttribute("mensajeError", "El ISBN ingresado ya se encuentra registrado, revise la lista de libros o ingrese el ISBN correcto.");
			}
		}
		else
		{
			this.cargarLibro(request, response);
			session.setAttribute("mensajeOK", "¡Cambios Guardados!");
		}
		response.sendRedirect("editarLibro.jsp?id="+request.getParameter("id"));
	}
	
	private void cargarLibro(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException, ServletException
	{
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + UPLOAD_DIR;
		CatalogoLibros cl = new CatalogoLibros();
		Libro l = new Libro();
		l.setId(Integer.parseInt(request.getParameter("id")));
		l.setISBN(request.getParameter("isbn"));
		l.setTitulo(request.getParameter("titulo"));
		l.setAutor(request.getParameter("autor"));
		l.setEstado(request.getParameter("estado"));
		l.setPortada(cl.getOne(Integer.parseInt(request.getParameter("id"))).getPortada());
		l.setStock(Integer.parseInt(request.getParameter("stock")));
		l.setPrecio(Float.parseFloat(request.getParameter("precio")));
		if(request.getPart("portada").getSize() != 0)
		{
			File fichero = new File(savePath + File.separator + l.getPortada());
			fichero.delete();
			request.getPart("portada").write(savePath + File.separator + l.getPortada());
		}
		cl.editarLibro(l);
	}
}