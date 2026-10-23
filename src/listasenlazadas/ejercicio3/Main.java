package listasenlazadas.ejercicio3;

import listasenlazadas.generica.SimpleLinkedList;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		SimpleLinkedList<Suscripcion> lista1 = new SimpleLinkedList<>();
		SimpleLinkedList<Suscripcion> lista2 = new SimpleLinkedList<>();
		
		cargarDatos(lista1,lista2);
		
		System.out.println("=== LISTA 1 ===");
        imprimirLista(lista1);
        System.out.println("\n=== LISTA 2 ===");
        imprimirLista(lista2);
        
        System.out.println("\n=== A) INTERSECCIÓN (Usuarios en ambas listas) ===");
        SimpleLinkedList<Suscripcion> interseccion=obtenerInterseccion(lista1,lista2);
        
		imprimirLista(interseccion);
		// b) Contar suscripciones de un usuario
        System.out.println("\n=== B) CONTAR SUSCRIPCIONES ===");
        String usuarioBuscado = leerCadena(scan, "Ingrese el nombre del usuario a contar: ");
        int total = contarSuscripciones(lista1, lista2, usuarioBuscado);
        System.out.println("El usuario '" + usuarioBuscado + "' tiene un total de " + total + " suscripción/es.");

        // c) Unión ordenada por fechaInicio
        System.out.println("\n=== C) UNIÓN (Ordenada por Fecha de Inicio) ===");
        SimpleLinkedList<Suscripcion> unionOrdenada = unirOrdenado(lista1, lista2);
        imprimirLista(unionOrdenada);

        scan.close();
    }

    // --- MÉTODOS DE LÓGICA ---

    private static SimpleLinkedList<Suscripcion> obtenerInterseccion(SimpleLinkedList<Suscripcion> l1, SimpleLinkedList<Suscripcion> l2) {
        SimpleLinkedList<Suscripcion> resultado = new SimpleLinkedList<>();
        
        for (Suscripcion s1 : l1) {
            boolean usuarioEnL2 = false;
            for (Suscripcion s2 : l2) {
                if (s1.getUsuario().equalsIgnoreCase(s2.getUsuario())) {
                    usuarioEnL2 = true;
                    break; // Cortamos la búsqueda porque ya lo encontramos en L2
                }
            }
            if (usuarioEnL2) {
                resultado.agregarAlFinal(s1);
            }
        }
        return resultado;
    }

    private static int contarSuscripciones(SimpleLinkedList<Suscripcion> l1, SimpleLinkedList<Suscripcion> l2, String usuario) {
        int contador = 0;
        for (Suscripcion s : l1) {
            if (s.getUsuario().equalsIgnoreCase(usuario.trim())) contador++;
        }
        for (Suscripcion s : l2) {
            if (s.getUsuario().equalsIgnoreCase(usuario.trim())) contador++;
        }
        return contador;
    }

    private static SimpleLinkedList<Suscripcion> unirOrdenado(SimpleLinkedList<Suscripcion> l1, SimpleLinkedList<Suscripcion> l2) {
        SimpleLinkedList<Suscripcion> resultado = new SimpleLinkedList<>();
        
        // Comparador que extrae la fechaInicio de los objetos Suscripcion y las compara
        Comparator<Suscripcion> comparadorFechas = Comparator.comparing(Suscripcion::getFechaInicio);

        // Agregamos ambas listas usando el nuevo método agregarOrdenado
        for (Suscripcion s : l1) resultado.agregarOrdenado(s, comparadorFechas);
        for (Suscripcion s : l2) resultado.agregarOrdenado(s, comparadorFechas);

        return resultado;
    }

    // --- MÉTODOS AUXILIARES ---

    private static void cargarDatos(SimpleLinkedList<Suscripcion> l1, SimpleLinkedList<Suscripcion> l2) {
        // Usuarios repetidos entre listas: Juan y Ana
        l1.agregarAlFinal(new Suscripcion(1, "Juan", "Básico", LocalDate.of(2023, 1, 15)));
        l1.agregarAlFinal(new Suscripcion(2, "Maria", "Premium", LocalDate.of(2023, 5, 20)));
        l1.agregarAlFinal(new Suscripcion(3, "Juan", "Deportes", LocalDate.of(2023, 8, 10)));
        l1.agregarAlFinal(new Suscripcion(4, "Ana", "Estándar", LocalDate.of(2022, 11, 5)));

        l2.agregarAlFinal(new Suscripcion(5, "Pedro", "Básico", LocalDate.of(2024, 2, 1)));
        l2.agregarAlFinal(new Suscripcion(6, "Juan", "Cine", LocalDate.of(2023, 6, 12)));
        l2.agregarAlFinal(new Suscripcion(7, "Ana", "Premium", LocalDate.of(2024, 1, 10)));
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

    private static void imprimirLista(SimpleLinkedList<Suscripcion> lista) {
        if (lista.tamanio() == 0) {
            System.out.println(" (Lista vacía) ");
            return;
        }
        for (Suscripcion s : lista) {
            System.out.println(" - " + s);
        }
    }
	}
