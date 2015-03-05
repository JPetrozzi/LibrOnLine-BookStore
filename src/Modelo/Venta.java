package Modelo;

import java.util.ArrayList;

public class Venta 
{
	private int id;
	private Usuario comprador;
	private ArrayList<Libro> items;
	private String fecha_venta;
	private String titular;
	private String tarjeta;
	private String nroTarjeta;
	private float monto;
	private String estado;
	
	public Venta()
	{
		
	}
	
	public int getId() 
	{
		return id;
	}
	
	public void setId(int i)
	{
		this.id = i;
	}

	public Usuario getComprador() 
	{
		return comprador;
	}

	public void setComprador(Usuario comprador) 
	{
		this.comprador = comprador;
	}

	public ArrayList<Libro> getItems() 
	{
		return items;
	}

	public void setItems(ArrayList<Libro> items) 
	{
		this.items = items;
	}

	public String getFecha_venta() 
	{
		return fecha_venta;
	}

	public void setFecha_venta(String fecha_venta) 
	{
		this.fecha_venta = fecha_venta;
	}
	
	public String getTitular()
	{
		return this.titular;
	}
	
	public void setTitular(String t)
	{
		this.titular = t;
	}

	public String getTarjeta() 
	{
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) 
	{
		this.tarjeta = tarjeta;
	}

	public String getNroTarjeta() 
	{
		return nroTarjeta;
	}

	public void setNroTarjeta(String nroTarjeta) 
	{
		this.nroTarjeta = nroTarjeta;
	}
	
	public float getMonto()
	{
		return this.monto;
	}
	
	public void setMonto(float m)
	{
		this.monto = m;
	}
	
	public String getEstado()
	{
		return this.estado;
	}
	
	public void setEstado(String est)
	{
		this.estado = est;
	}
}