package Datos;

public class Cancion {
	
	private String titulo;
	private String autor;
	private int duracion;
	
	public Cancion(String titulo, String autor, int duracion) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.duracion = duracion;
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
	
	
	
	
	
	

}
