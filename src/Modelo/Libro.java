package Modelo;

public class Libro 
{
	private int id;
	private String ISBN;
	private String titulo;
	private String autor;
	private String portada;
	private String estado;
	private int stock;
	private float precio;
	
	public Libro()
	{
		
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int ID)
	{
		id = ID;
	}

	public String getISBN() 
	{
		return ISBN;
	}

	public void setISBN(String iSBN) 
	{
		ISBN = iSBN;
	}

	public String getTitulo() 
	{
		return titulo;
	}

	public void setTitulo(String titulo) 
	{
		this.titulo = titulo;
	}

	public String getAutor() 
	{
		return autor;
	}

	public void setAutor(String autor) 
	{
		this.autor = autor;
	}

	public String getPortada() 
	{
		return portada;
	}

	public void setPortada(String portada) 
	{
		this.portada = portada;
	}

	public String getEstado() 
	{
		return estado;
	}

	public void setEstado(String estado) 
	{
		this.estado = estado;
	}

	public int getStock() 
	{
		return stock;
	}

	public void setStock(int stock) 
	{
		this.stock = stock;
	}

	public float getPrecio() 
	{
		return precio;
	}

	public void setPrecio(float precio) 
	{
		this.precio = precio;
	}	
}