package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Libro;
import Modelo.Venta;

public class CatalogoVentas 
{
	public void nuevaVenta(Venta v)
	{
		ConeccionDB con = new ConeccionDB();
		CatalogoLibros cl = new CatalogoLibros();
		int nroVenta = this.getNuevoNroVenta();
		PreparedStatement ps = null;
		String queryString = "INSERT INTO ventas (id_venta,id_usuario,fecha_venta,tarjeta,titular,nro_tarjeta,monto,estado) VALUES (?,?,?,?,?,?,?,?)";
		try 
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setInt(1, nroVenta);
			ps.setInt(2, v.getComprador().getId());
			ps.setString(3, v.getFecha_venta());
			ps.setString(4, v.getTarjeta());
			ps.setString(5, v.getTitular());
			ps.setString(6, v.getNroTarjeta());
			ps.setFloat(7, v.getMonto());
			ps.setString(8, "PENDIENTE");
			ps.executeUpdate();	
			ps.close();
			ps = con.getConnection().prepareStatement("INSERT INTO items_venta (id_libro,id_venta) VALUES (?,?)");
			for(Libro l : v.getItems())
			{
				ps.setInt(1, l.getId());
				ps.setInt(2, nroVenta);
				ps.executeUpdate();
				cl.reducirStock(l);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				con.getConnection().close();
				ps.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private int getNuevoNroVenta()
	{
		int nro = 0;
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT MAX(id_venta) FROM ventas;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			rs = ps.executeQuery();
			if(rs.next())
			{
				nro = rs.getInt(1);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.getConnection().close();
				rs.close();
				ps.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return nro + 1;
	}
	
	public ArrayList<Venta> getAll(String filtro)
	{
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		Venta v;
		CatalogoUsuarios cu = new CatalogoUsuarios();
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM ventas WHERE estado = ?;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setString(1, filtro);
			rs = ps.executeQuery();
			while(rs.next())
			{
				v = new Venta();
				v.setId(rs.getInt("id_venta"));
				v.setComprador(cu.getOne(rs.getInt("id_usuario")));
				v.setFecha_venta(rs.getString("fecha_venta"));
				v.setTitular(rs.getString("titular"));
				v.setTarjeta(rs.getString("tarjeta"));
				v.setNroTarjeta(rs.getString("nro_tarjeta"));
				v.setMonto(rs.getFloat("monto"));
				v.setEstado(rs.getString("estado"));
				v.setItems(this.getItemsPorVenta(v.getId(), con));
				ventas.add(v);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.getConnection().close();
				rs.close();
				ps.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return ventas;
	}
	
	public Venta getOne(int id)
	{
		CatalogoUsuarios cu = new CatalogoUsuarios();
		Venta v = null;
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM ventas WHERE id_venta = ?;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				v = new Venta();
				v.setId(rs.getInt("id_venta"));
				v.setComprador(cu.getOne(rs.getInt("id_usuario")));
				v.setFecha_venta(rs.getString("fecha_venta"));
				v.setTitular(rs.getString("titular"));
				v.setTarjeta(rs.getString("tarjeta"));
				v.setNroTarjeta(rs.getString("nro_tarjeta"));
				v.setMonto(rs.getFloat("monto"));
				v.setEstado(rs.getString("estado"));
				v.setItems(this.getItemsPorVenta(v.getId(), con));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.getConnection().close();
				rs.close();
				ps.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return v;
	}
	
	private ArrayList<Libro> getItemsPorVenta(int id_venta, ConeccionDB con)
	{
		CatalogoLibros cl = new CatalogoLibros();
		ArrayList<Libro> items = new ArrayList<Libro>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT id_libro FROM items_venta WHERE id_venta = ?;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setInt(1, id_venta);
			rs = ps.executeQuery();
			while(rs.next())
			{
				items.add(cl.getOne(rs.getInt("id_libro")));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				rs.close();
				ps.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return items;
	}
	
	public void editarVenta(Venta v)  //está pensado para editar unicamente el "estado" de la venta
	{
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		String queryString = "UPDATE ventas SET id_usuario = ?, fecha_venta = ?, tarjeta = ?, titular = ?, nro_tarjeta = ?, monto = ?, estado = ? WHERE id_venta = ?;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setInt(1, v.getComprador().getId());
			ps.setString(2, v.getFecha_venta());
			ps.setString(3, v.getTarjeta());
			ps.setString(4, v.getTitular());
			ps.setString(5, v.getNroTarjeta());
			ps.setFloat(6, v.getMonto());
			ps.setString(7, v.getEstado());
			ps.setInt(8, v.getId());
			ps.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.getConnection().close();
				ps.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void eliminarVenta(int id)
	{
		CatalogoLibros cl = new CatalogoLibros();
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		String queryString = "DELETE FROM items_venta WHERE id_venta = ?;";
		try
		{
			for(Libro l : this.getOne(id).getItems())
			{
				cl.aumentarStock(l);
			}
			ps = con.getConnection().prepareStatement(queryString);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			ps = con.getConnection().prepareStatement("DELETE FROM ventas WHERE id_venta = ?;");
			ps.setInt(1, id);
			ps.execute();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				con.getConnection().close();
				ps.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}