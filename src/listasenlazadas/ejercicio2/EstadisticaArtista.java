package listasenlazadas.ejercicio2;

public class EstadisticaArtista {
	private String nombreArtista;
	private int totalReproducciones;
	
	public EstadisticaArtista(String nombreArtista, int totalReproducciones) {
		this.nombreArtista=nombreArtista;
		this.totalReproducciones=totalReproducciones;
	}
	
	public String getNombreArtista() {
		return nombreArtista;
	}
	
	public int getTotalReproducciones() {
		return totalReproducciones;
	}
	
	
	public void sumarDuracion(int duracion) {
		this.totalReproducciones+=duracion;
	}

	@Override
	public String toString() {
		return String.format("%s: %d segundos totales", nombreArtista, totalReproducciones);
		
	}
	
	
	
}
