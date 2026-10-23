package listasenlazadas.ejercicio4;

import listasenlazadas.generica.SimpleLinkedList;
import java.time.LocalDate;

public class GestorTareas {
	private SimpleLinkedList<Tarea> listaTareas;

	public GestorTareas() {
		this.listaTareas=new SimpleLinkedList<>();
	}
	
	public void agregarTarea(Tarea tarea) {
		listaTareas.agregarAlFinal(tarea);
	}
	
	public boolean completarTarea(int idTarea) {
		for(Tarea t: listaTareas) {
			if (t.getIdTarea()==idTarea) {
				t.setCompletada(true);
				return true;
			}
		}
		return false;
	}
	
	public SimpleLinkedList<Tarea> tareasPorResponsable(String responsable){
		SimpleLinkedList<Tarea> resultado = new SimpleLinkedList<>();
		for(Tarea t:listaTareas) {
			if(t.getResponsable().equalsIgnoreCase(responsable.trim())) {
				resultado.agregarAlFinal(t);
			}
		}
		return resultado;
	}
	
	public SimpleLinkedList<Tarea> tareasPendientes(){
		SimpleLinkedList<Tarea> pendientes = new SimpleLinkedList<>();
		for(Tarea t:listaTareas) {
			if(!t.isCompletada()) {
				pendientes.agregarAlFinal(t);
			}
		}
		return pendientes;
	}
	
	public boolean eliminarTarea(int idTarea) {
		int indice=0;
		for(Tarea t:listaTareas) {
			if(t.getIdTarea()==idTarea) {
				listaTareas.eliminar(indice);
				return true;
			}
			indice++;
		}
		return false;
		
	}
	
	public SimpleLinkedList<Tarea> tareasProximas(String responsable){
		SimpleLinkedList<Tarea> resultado = new SimpleLinkedList<>();
		LocalDate hoy = LocalDate.now();
		for (Tarea t: listaTareas) {
			if(t.getResponsable().equalsIgnoreCase(responsable.trim())&&t.getFechaLimite().isAfter(hoy)) {
				resultado.agregarAlFinal(t);
				
			}
		}
		return resultado;
		}
	
	public SimpleLinkedList<Tarea>obtenerTodas(){
		return listaTareas;
	}
	
	
	
}
