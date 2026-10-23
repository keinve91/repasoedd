package listasenlazadas.ejercicio5;
import listasenlazadas.generica.SimpleLinkedList;
public class GestorAspirantes {
	private SimpleLinkedList<Aspirante> listaAspirantes;
	public GestorAspirantes() {
        this.listaAspirantes = new SimpleLinkedList<>();
    }

    public void agregarAspirante(Aspirante aspirante) {
        listaAspirantes.agregarAlFinal(aspirante);
    }

    public SimpleLinkedList<Aspirante> obtenerTodos() {
        return listaAspirantes;
    }

    // a) Mostrar promedio de cada aspirante
    public void mostrarPromedios() {
        if (listaAspirantes.tamanio() == 0) {
            System.out.println("No hay aspirantes registrados.");
            return;
        }
        for (Aspirante a : listaAspirantes) {
            System.out.printf("Aspirante: %s - Promedio: %.2f%n", a.getNombreCompleto(), a.calcularPromedio());
        }
    }

    // b) Devolver aspirante con la nota promedio más alta
    public Aspirante obtenerMejorAspirante() {
        if (listaAspirantes.tamanio() == 0) return null;

        Aspirante mejor = null;
        for (Aspirante a : listaAspirantes) {
            if (mejor == null || a.calcularPromedio() > mejor.calcularPromedio()) {
                mejor = a;
            }
        }
        return mejor;
    }

    // c) Crear nueva lista con aprobados (promedio >= 7)
    public SimpleLinkedList<Aspirante> obtenerAprobados() {
        SimpleLinkedList<Aspirante> aprobados = new SimpleLinkedList<>();
        for (Aspirante a : listaAspirantes) {
            if (a.calcularPromedio() >= 7.0) {
                aprobados.agregarAlFinal(a);
            }
        }
        return aprobados;
    }

}
