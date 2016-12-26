package Datos;

import java.io.Serializable;

public class Cancion implements Serializable{
	
	private String titulo;
	private int genero;
	

	
	public Cancion(String titulo, int genero) {
		super();
		this.titulo = titulo;
		this.genero= genero;
		
		//crear un array de titulos cogiendo los de la carpeta de musica
		
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
