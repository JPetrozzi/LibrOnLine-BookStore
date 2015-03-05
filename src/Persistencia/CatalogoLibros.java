package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Libro;

public class CatalogoLibros 
{
	public boolean isIsbnValid(String isbn)
	{
		boolean isValid = false;
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM libros WHERE ISBN = ?;";
		try 
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setString(1, isbn);
			rs = ps.executeQuery();
			if(rs.next())
			{
				isValid = false;
			}
			else
			{
				isValid = true;
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
				rs.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		return isValid;
	}
	
	public void agregarLibro(Libro l)
	{
		if(l.getStock()<1)
		{
			l.setEstado("NODISPONIBLE");
		}
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		String queryString = "INSERT INTO libros (ISBN,titulo,autor,portada,estado,stock,precio) VALUES (?,?,?,?,?,?,?)";
		try 
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setString(1, l.getISBN());
			ps.setString(2, l.getTitulo());
			ps.setString(3, l.getAutor());
			ps.setString(4, l.getPortada());
			ps.setString(5, l.getEstado());
			ps.setInt(6, l.getStock());
			ps.setFloat(7, l.getPrecio());
			ps.executeUpdate();
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
	
	public ArrayList<Libro> getAll()
	{
		ArrayList<Libro> libros = new ArrayList<Libro>();
		Libro l;
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM libros ORDER BY titulo;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			rs = ps.executeQuery();
			while(rs.next())
			{
				l = new Libro();
				l.setId(rs.getInt("id_libro"));
				l.setISBN(rs.getString("ISBN"));
				l.setTitulo(rs.getString("titulo"));
				l.setAutor(rs.getString("autor"));
				l.setPortada(rs.getString("portada"));
				l.setEstado(rs.getString("estado"));
				l.setStock(rs.getInt("stock"));
				l.setPrecio(rs.getFloat("precio"));
				libros.add(l);
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
		return libros;
	}
	
	public Libro getOne(int id)
	{
		Libro l = null;
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM libros WHERE id_libro = ?;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				l = new Libro();
				l.setId(rs.getInt("id_libro"));
				l.setISBN(rs.getString("ISBN"));
				l.setTitulo(rs.getString("titulo"));
				l.setAutor(rs.getString("autor"));
				l.setPortada(rs.getString("portada"));
				l.setEstado(rs.getString("estado"));
				l.setStock(rs.getInt("stock"));
				l.setPrecio(rs.getFloat("precio"));
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
		return l;
	}
	
	public ArrayList<Libro> getDisponibles()
	{
		ArrayList<Libro> disponibles = new ArrayList<Libro>();
		Libro l;
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM libros WHERE estado = 'DISPONIBLE' ORDER BY titulo;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			rs = ps.executeQuery();
			while(rs.next())
			{
				l = new Libro();
				l.setId(rs.getInt("id_libro"));
				l.setISBN(rs.getString("ISBN"));
				l.setTitulo(rs.getString("titulo"));
				l.setAutor(rs.getString("autor"));
				l.setPortada(rs.getString("portada"));
				l.setEstado(rs.getString("estado"));
				l.setStock(rs.getInt("stock"));
				l.setPrecio(rs.getFloat("precio"));
				disponibles.add(l);
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
		return disponibles;
	}
	
	public void eliminarLibro(int id)
	{
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		String queryString = "DELETE FROM libros WHERE id_libro = ?;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
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
	
	public void editarLibro(Libro l)
	{
		if(l.getStock()<1)
		{
			l.setEstado("NODISPONIBLE");
		}
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		String queryString = "UPDATE libros SET ISBN = ?, titulo = ?, autor = ?, portada = ?, estado = ?, stock = ?, precio = ? WHERE id_libro = ?;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setString(1, l.getISBN());
			ps.setString(2, l.getTitulo());
			ps.setString(3, l.getAutor());
			ps.setString(4, l.getPortada());
			ps.setString(5, l.getEstado());
			ps.setInt(6, l.getStock());
			ps.setFloat(7, l.getPrecio());
			ps.setInt(8, l.getId());
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
	
	public void reducirStock(Libro l)
	{
		int stock = l.getStock() - 1;
		l.setStock(stock);
		this.editarLibro(l);
	}
	
	public void aumentarStock(Libro l)
	{
		int stock = l.getStock() + 1;
		l.setStock(stock);
		this.editarLibro(l);
	}
}
