package BasesDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Datos.Cancion;

public class BD {
	
	//creo una carpeta dentro del proyecto donde meto todas las canciones con la extension mp3. 
	//En la base de datos introduzco el nombre de la cancion y desde el programa hago un audioClip llamando a la cancion con ese nombre
	//despues hago un start y se reproduce
	
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
		System.out.println("CONECTADA");
	}
	
	
	public Cancion obtenerCancion(String titulo){
		String query;
		Cancion c=null;
		
		query="SELECT * FROM cliente WHERE titulo='"+titulo+"'"; 
		try {
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) //Si la select ha devuelto filas
				c=new Cancion(rs.getString("titulo"),rs.getInt("genero"));
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		} 
		return c;
	}
	
	
	/**
	 * Compruebo si la canción que quiero introducir ya existe en la BD
	 * */
	public boolean existeCancion(Cancion c){
		boolean existe=false;
		String query="SELECT * FROM Cancion WHERE titulo='"+c.getTitulo()+"'";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			if(rs.next())
				existe=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return existe;
	}
	
	/**
	 * Inserto las canciones en la BD mientras no estén repetidas. Si una canción que quiero meter
	 * ya está en la BD no la introduce de nuevo
	 * */
	public void insertarNuevaCancion (Cancion c){
		String query = "INSERT INTO cancion (titulo,genero) VALUES ('"+c.getTitulo()+"',"+c.getGenero()+")";
		try {
			if(!existeCancion(c))
				stmt.executeUpdate(query);
				System.out.println("Insertada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creo un numero aleatorio desde 0 hasta el ultimo número de la tabla género
	 * */
	public int obtenerGeneroAleatorio(){
		int cont, genero=0;
		String query="SELECT COUNT(*) FROM Genero";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			if(rs.next()){
				cont=rs.getInt(1);
				System.out.println(cont);
				genero = (int)(Math.random()*cont)+1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genero;
	}
}