package listasenlazadas.ejercicio4;

import listasenlazadas.generica.SimpleLinkedList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;




public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		GestorTareas gestor = new GestorTareas();
		
		cargarTareasIniciales(gestor);
		int opcion;
		do {
			System.out.println("\n=== GESTOR DE TAREAS ===");
            System.out.println("1. Mostrar todas las tareas");
            System.out.println("2. Agregar nueva tarea");
            System.out.println("3. Completar tarea");
            System.out.println("4. Ver tareas por responsable");
            System.out.println("5. Ver tareas pendientes");
            System.out.println("6. Eliminar tarea");
            System.out.println("7. Ver tareas próximas de un responsable");
            System.out.println("8. Salir");
            opcion = leerEntero(scanner, "Seleccione una opción: ");
            
            switch(opcion) {
            case 1:
            	System.out.println("\n-- TODAS LAS TAREAS --");
                imprimirLista(gestor.obtenerTodas());
                break;
            case 2:
            	System.out.println("\n-- AGREGAR TAREA --");
                int id = leerEntero(scanner, "ID de la Tarea: ");
                String titulo = leerCadena(scanner, "Título: ");
                String resp = leerCadena(scanner, "Responsable: ");
                LocalDate fecha = leerFecha(scanner, "Fecha límite (dd/MM/yyyy): ");
                gestor.agregarTarea(new Tarea(id, titulo, resp, fecha));
                System.out.println("Tarea agregada exitosamente.");
                break;
            case 3:
            	int idComp = leerEntero(scanner, "\nIngrese ID de la tarea a completar: ");
                if (gestor.completarTarea(idComp)) System.out.println("Tarea marcada como completada.");
                else System.out.println("No se encontró una tarea con ese ID.");
                break;
            case 4:
            	String respBuscado = leerCadena(scanner, "\nIngrese responsable: ");
                imprimirLista(gestor.tareasPorResponsable(respBuscado));
                break;     
            case 5:
            	System.out.println("\n-- TAREAS PENDIENTES --");
                imprimirLista(gestor.tareasPendientes());
                break;
            case 6:
            	int idEliminar = leerEntero(scanner, "\nIngrese ID de la tarea a eliminar: ");
                if (gestor.eliminarTarea(idEliminar)) System.out.println("Tarea eliminada correctamente.");
                else System.out.println("No se encontró una tarea con ese ID.");
                break;
            case 7:
            	String respProx = leerCadena(scanner, "\nIngrese responsable para ver tareas futuras: ");
                imprimirLista(gestor.tareasProximas(respProx));
                break;
            case 8:
            	System.out.println("Saliendo del programa...");
                break;
            default:
            	System.out.println("Opción incorrecta.");
            }
            
		}while (opcion != 8);
	scanner.close();
	}
	private static void cargarTareasIniciales(GestorTareas gestor) {
		gestor.agregarTarea(new Tarea(1, "Diseñar Base de Datos", "Juan", LocalDate.now().minusDays(2)));
        gestor.agregarTarea(new Tarea(2, "Programar API", "Ana", LocalDate.now().plusDays(5)));
        gestor.agregarTarea(new Tarea(3, "Crear Frontend", "Juan", LocalDate.now().plusDays(10)));
        gestor.agregarTarea(new Tarea(4, "Testing General", "Pedro", LocalDate.now().plusDays(1))); 
        gestor.agregarTarea(new Tarea(5, "Redactar Manual", "Ana", LocalDate.now().minusDays(1)));
        
        gestor.completarTarea(1);
        
        
	}
	private static void imprimirLista(SimpleLinkedList<Tarea> lista) {
        if (lista.tamanio() == 0) {
            System.out.println("No hay tareas para mostrar.");
            return;
        }
        for (Tarea t : lista) System.out.println(t);
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

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero válido.");
            }
        }
    }

    private static LocalDate leerFecha(Scanner scanner, String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha inválido. Use dd/MM/yyyy.");
            }
        }
    }
}
