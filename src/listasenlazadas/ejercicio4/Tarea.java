package listasenlazadas.ejercicio4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tarea {
 private int idTarea;
 private String titulo;
 private String responsable;
 private LocalDate fechaLimite;
 private boolean completada;
 
 public Tarea(int idTarea, String titulo, String responsable, LocalDate fechaLimite) {
	super();
	this.idTarea = idTarea;
	this.titulo = titulo;
	this.responsable = responsable;
	this.fechaLimite = fechaLimite;
	this.completada = false;
 }

 public boolean isCompletada() {
	return completada;
 }

 public void setCompletada(boolean completada) {
	this.completada = completada;
 }

 public int getIdTarea() {
	return idTarea;
 }

 public String getTitulo() {
	return titulo;
 }

 public String getResponsable() {
	return responsable;
 }

 public LocalDate getFechaLimite() {
	return fechaLimite;
 }

 @Override
 public String toString() {
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     String estado = completada ? "[X] COMPLETADA" : "[ ] PENDIENTE";
     return String.format("ID: %d | %s | Título: '%s' | Resp: %s | Límite: %s", 
                          idTarea, estado, titulo, responsable, fechaLimite.format(formatter));
 }
}
