package Persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Usuario;

public class CatalogoUsuarios 
{
	public Usuario validarLogin(String user, String pass)
	{
		Usuario usuario = null;
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM usuarios WHERE username = ? AND password = ?;";
		try 
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if(rs.next())
			{
				usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setUsername(rs.getString("username"));
				usuario.setPassword(rs.getString("password"));
				usuario.setRol(rs.getString("tipo_usuario"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setEmail(rs.getString("email"));
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
		return usuario;
	}
	
	public boolean isUserValid(String usuario)
	{
		boolean isValid = false;
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM usuarios WHERE username = ?;";
		try 
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setString(1, usuario);
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
	
	public void RegistrarUsuario(Usuario u)
	{
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		String queryString = "INSERT INTO usuarios (username,password,tipo_usuario,nombre,email) VALUES (?,?,?,?,?)";
		try 
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getRol());
			ps.setString(4, u.getNombre());
			ps.setString(5, u.getEmail());
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
	
	public ArrayList<Usuario> getAll()
	{
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		Usuario u;
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM usuarios ORDER BY username;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			rs = ps.executeQuery();
			while(rs.next())
			{
				u = new Usuario();
				u.setId(rs.getInt("id_usuario"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setRol(rs.getString("tipo_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setEmail(rs.getString("email"));
				usuarios.add(u);
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
		return usuarios;
	}
	
	public Usuario getOne(int id)
	{
		Usuario u = null;
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String queryString = "SELECT * FROM usuarios WHERE id_usuario = ?;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				u = new Usuario();
				u.setId(rs.getInt("id_usuario"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setRol(rs.getString("tipo_usuario"));
				u.setNombre(rs.getString("nombre"));
				u.setEmail(rs.getString("email"));
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
		return u;
	}
	
	public void eliminarUsuario(int id)
	{
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		String queryString = "DELETE FROM usuarios WHERE id_usuario = ?;";
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
	
	public void editarUsuario(Usuario u)
	{
		ConeccionDB con = new ConeccionDB();
		PreparedStatement ps = null;
		String queryString = "UPDATE usuarios SET username = ?, password = ?, tipo_usuario = ?, nombre = ?, email = ? WHERE id_usuario = ?;";
		try
		{
			ps = con.getConnection().prepareStatement(queryString);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getRol());
			ps.setString(4, u.getNombre());
			ps.setString(5, u.getEmail());
			ps.setInt(6, u.getId());
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
