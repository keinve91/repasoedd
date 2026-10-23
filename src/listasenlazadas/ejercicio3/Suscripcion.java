package listasenlazadas.ejercicio3;

import java.time.LocalDate;

public class Suscripcion {
	private int idSuscripcion;
	private String usuario;
	private String plan;
	private LocalDate fechaInicio;
	
	public Suscripcion(int idSuscripcion, String usuario, String plan, LocalDate fechaInicio) {
		super();
		this.idSuscripcion = idSuscripcion;
		this.usuario = usuario;
		this.plan = plan;
		this.fechaInicio = fechaInicio;
	}

	public int getIdSuscripcion() {
		return idSuscripcion;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getPlan() {
		return plan;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	@Override
	public String toString() {
		return String.format("ID: %d | Usuario: %s | Plan: %s | Inicio: %s", 
                idSuscripcion, usuario, plan, fechaInicio);
	}
	
	
	
	
}
