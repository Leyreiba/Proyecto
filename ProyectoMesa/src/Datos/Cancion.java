package Datos;

import java.io.Serializable;

public class Cancion implements Serializable{
	
	private String titulo;
	private int genero;
	String ruta;
	

	
	public Cancion(String titulo, int genero, String ruta) {
		super();
		this.titulo = titulo;
		this.genero= genero;
		this.ruta=ruta;
		
		//crear un array de titulos cogiendo los de la carpeta de musica
		
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Cancion() {
	
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}
	@Override
	public String toString() {
		return "Cancion [titulo=" + titulo + ", genero=" + genero + "]";
	}
	
	
	
	
	
	
	

}
