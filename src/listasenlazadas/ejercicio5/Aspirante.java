package listasenlazadas.ejercicio5;

public class Aspirante {
	private String dni;
	private String nombre;
	private String apellido;
	private double notaEscrito;
	private double notaOral;
	private double notaPractico;
	public Aspirante(String dni, String nombre, String apellido, double notaEscrito, double notaOral,
			double notaPractico) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.notaEscrito = notaEscrito;
		this.notaOral = notaOral;
		this.notaPractico = notaPractico;
	}
	public String getDni() {
		return dni;
	}
	public String getNombreCompleto() {
		return apellido + ", " + nombre;
	}
	
	public double calcularPromedio() {
		return(notaEscrito + notaOral + notaPractico)/3.0;
	}
	@Override
	public String toString() {
		return "Aspirante [dni=" + dni + ", getNombreCompleto()=" + getNombreCompleto() + ", calcularPromedio()="
				+ calcularPromedio() + "]";
	}
	
	
	
}
