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

@WebServlet("/AgregarLibro")
@MultipartConfig(fileSizeThreshold=1024*1024*10, maxFileSize=1024*1024*50, maxRequestSize=1024*1024*100, location="/")
public class AgregarLibro extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public HttpSession session;
	private static final String UPLOAD_DIR = "media\\img";
	//private static final String UPLOAD_DIR = "C:/Documents and Settings/Juan Pablo/Escritorio/TPFinalJava/LibrOnLine/WebContent/media/img/";

    public AgregarLibro() 
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
		if(cl.isIsbnValid(request.getParameter("isbn")))
		{
			String appPath = request.getServletContext().getRealPath("");
			String savePath = appPath + File.separator + UPLOAD_DIR;
			
			File fileSaveDir = new File(savePath);
			if(!fileSaveDir.exists())
			{
				fileSaveDir.mkdirs();
			}
		
			String ISBN = request.getParameter("isbn");
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String portada = ISBN+".png";
			String estado = request.getParameter("estado");
			int stock = Integer.parseInt(request.getParameter("stock"));
			float precio = Float.parseFloat(request.getParameter("precio"));
		
			//request.getPart("portada").write(UPLOAD_DIR + ISBN + ".png");
			request.getPart("portada").write(savePath + File.separator + ISBN + ".png");
		
			Libro nl = new Libro();
			nl.setISBN(ISBN);
			nl.setTitulo(titulo);
			nl.setAutor(autor);
			nl.setPortada(portada);
			nl.setEstado(estado);
			nl.setStock(stock);
			nl.setPrecio(precio);
		
			//System.out.print(savePath + File.separator + ISBN + ".png"); //solo para probar la ruta obtenida

			cl.agregarLibro(nl);
			response.sendRedirect("libros.jsp");
		}
		else
		{
			session = request.getSession(true);
			session.setAttribute("mensajeError", "El ISBN ingresado ya se encuentra registrado, revise la lista de libros o ingrese el ISBN correcto.");
			response.sendRedirect("agregarLibro.jsp");
		}
		
	}
}