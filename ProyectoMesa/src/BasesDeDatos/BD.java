package BasesDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Datos.Cancion;

public class BD {
	
	private Connection con;
	private static Statement stmt;
	
	/**
	 * Metodo que crea una sentencia para acceder a la base de datos 
	 */
	public void crearSentencia()
	{
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que permite conectarse a la base de datos
	 */

	public void conectar()
	{
		try {
			Class.forName("org.sqlite.JDBC");
			con= DriverManager.getConnection("jdbc:sqlite:mesa.db");
			crearSentencia();
		}catch(Exception e)
		{
			System.out.println("No se ha podido conectar a la base de datos");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que cierra una sentencia 
	 */
	public void cerrarSentencia()
	{
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que permite desconectarse de la base de datos
	 */
	public void desconectar()
	{
		try {
			cerrarSentencia();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BD(){
		conectar();
	}
	
	
	public Cancion obtenerCancion(String titulo){
		String query;
		Cancion c=null;
		
		query="SELECT * FROM cliente WHERE titulo='"+titulo+"'"; 
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) //Si la select ha devuelto filas
				c=new Cancion(rs.getString("titulo"),rs.getString("autor"),rs.getInt("duracion"));
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		} 
		return c;
	}
	
	public void insertarNuevaCancion (Cancion c){
		String query = "INSERT INTO cancion (titulo, autor, duracion) VALUES ('"+c.getTitulo()+"','"+c.getAutor()+"','"+c.getDuracion()+"')";
		try {
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}