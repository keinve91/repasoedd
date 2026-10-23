package listasenlazadas.ejercicio5;
import listasenlazadas.generica.SimpleLinkedList;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorAspirantes gestor = new GestorAspirantes();

        System.out.println("=== CARGA DE ASPIRANTES ===");
        cargarAspirantesDePrueba(gestor); // Carga predefinida para facilitar la prueba
        
        // Opcional: Carga manual por teclado
        String continuar;
        do {
            continuar = leerCadena(scanner, "¿Desea ingresar un nuevo aspirante manualmente? (s/n): ");
            if (continuar.equalsIgnoreCase("s")) {
                cargarAspiranteManual(scanner, gestor);
            }
        } while (continuar.equalsIgnoreCase("s"));

        System.out.println("\n=== A) PROMEDIOS INDIVIDUALES ===");
        gestor.mostrarPromedios();

        System.out.println("\n=== B) MEJOR ASPIRANTE ===");
        Aspirante mejor = gestor.obtenerMejorAspirante();
        if (mejor != null) {
            System.out.printf("El mejor aspirante es %s con un promedio de %.2f%n", 
                              mejor.getNombreCompleto(), mejor.calcularPromedio());
        } else {
            System.out.println("La lista está vacía.");
        }

        System.out.println("\n=== C) LISTA DE APROBADOS (PROMEDIO >= 7) ===");
        SimpleLinkedList<Aspirante> aprobados = gestor.obtenerAprobados();
        if (aprobados.tamanio() > 0) {
            for (Aspirante a : aprobados) {
                System.out.println(" - " + a);
            }
        } else {
            System.out.println("Ningún aspirante superó el proceso.");
        }

        scanner.close();
    }

    // --- MÉTODOS DE LÓGICA Y VALIDACIÓN ---

    private static void cargarAspirantesDePrueba(GestorAspirantes gestor) {
        gestor.agregarAspirante(new Aspirante("111", "Juan", "Perez", 6.0, 7.0, 8.0)); // Prom: 7.0
        gestor.agregarAspirante(new Aspirante("222", "Maria", "Gomez", 9.0, 9.5, 10.0)); // Prom: 9.5
        gestor.agregarAspirante(new Aspirante("333", "Carlos", "Ruiz", 4.0, 5.0, 6.0)); // Prom: 5.0
        System.out.println("3 aspirantes de prueba cargados automáticamente.");
    }

    private static void cargarAspiranteManual(Scanner scanner, GestorAspirantes gestor) {
        System.out.println("\n-- Datos del Aspirante --");
        String dni = leerCadena(scanner, "DNI: ");
        String nombre = leerCadena(scanner, "Nombre: ");
        String apellido = leerCadena(scanner, "Apellido: ");
        double nEscrito = leerNota(scanner, "Nota Escrito (0-10): ");
        double nOral = leerNota(scanner, "Nota Oral (0-10): ");
        double nPractico = leerNota(scanner, "Nota Práctico (0-10): ");

        gestor.agregarAspirante(new Aspirante(dni, nombre, apellido, nEscrito, nOral, nPractico));
        System.out.println("Aspirante registrado con éxito.");
    }

    private static String leerCadena(Scanner scanner, String mensaje) {
        String input;
        do {
            System.out.print(mensaje);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) System.out.println("Error: El dato no puede estar vacío.");
        } while (input.isEmpty());
        return input;
    }

    private static double leerNota(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(scanner.nextLine().trim());
                if (valor >= 0.0 && valor <= 10.0) {
                    return valor;
                } else {
                    System.out.println("Error: La nota debe estar entre 0 y 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido (ej: 8.5).");
            }
        }
    }
}
