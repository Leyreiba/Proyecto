package BasesDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
				c=new Cancion(rs.getString("titulo"),rs.getInt("genero"), rs.getString("Ruta"));
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		} 
		return c;
	}
	
	
	/**
	 * Compruebo si la canci�n que quiero introducir ya existe en la BD
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
	 * Inserto las canciones en la BD mientras no est�n repetidas. Si una canci�n que quiero meter
	 * ya est� en la BD no la introduce de nuevo
	 * */
	public void insertarNuevaCancion (Cancion c){
		String query = "INSERT INTO cancion (titulo,genero,ruta) VALUES ('"+c.getTitulo()+"',"+c.getGenero()+",'"+c.getRuta()+"')";
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
	 * Creo un numero aleatorio desde 0 hasta el ultimo n�mero de la tabla g�nero
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
	
	public ArrayList<Cancion> obtenerTodasLasCanciones(){
		String query ="SELECT * FROM cancion";
		ResultSet rs;
		ArrayList<Cancion> aCanciones = new ArrayList<Cancion>();
		
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()){
				aCanciones.add(new Cancion(rs.getString("titulo"),rs.getInt("genero"),rs.getString("Ruta")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aCanciones;
	}
	
	public ArrayList<String> obtenerNombresGenero(){
		String query="SELECT desc FROM genero";
		ArrayList<String> aGeneros = new ArrayList<String>();
		try {
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				aGeneros.add(rs.getString("desc"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aGeneros;
	}
	
	public String obtenerRuta(String nombre){
		String ruta="";
		String query="SELECT Ruta FROM Cancion WHERE Nombre='"+nombre+"'";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			while(rs.next())
				ruta=rs.getString("Ruta");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ruta;
	}
}