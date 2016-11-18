package Datos;

import java.io.Serializable;

public class Cancion implements Serializable{
	
	private String titulo;
	private String autor;
	private int duracion;
	

	
	public Cancion(String titulo, String autor, int duracion) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.duracion = duracion;
		
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Cancion [titulo=" + titulo + ", autor=" + autor + ", duracion=" + duracion + "]";
	}
	
	
	
	
	
	
	

}
