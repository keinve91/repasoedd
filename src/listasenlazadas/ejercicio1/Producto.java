package listasenlazadas.ejercicio1;

public class Producto {
	private String codigo;
	private String nombre;
	private double precio;
	private int stock;
	
	
	
	public Producto(String codigo, String nombre, double precio, int stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}


	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + "]";
	}
}
