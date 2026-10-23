package listasenlazadas.ejercicio2;

import java.util.Scanner;

import listasenlazadas.generica.DobleLinkedList;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DobleLinkedList<Cancion> listaCanciones = new DobleLinkedList<>();

        // a) Cargar al menos 10 canciones
        cargarCanciones(listaCanciones);

        System.out.println("=== LISTA DE CANCIONES ORIGINAL ===");
        imprimirLista(listaCanciones);

        // a) Canción más antigua
        Cancion masAntigua = obtenerCancionMasAntigua(listaCanciones);
        System.out.println("\n=== A) CANCIÓN MÁS ANTIGUA ===");
        System.out.println(masAntigua != null ? masAntigua : "La lista está vacía.");

        // b) Filtrar por artista
        System.out.println("\n=== B) FILTRAR POR ARTISTA ===");
        String artistaBuscado = leerCadena(scanner, "Ingrese el nombre del artista a buscar: ");
        DobleLinkedList<Cancion> filtradas = filtrarPorArtista(listaCanciones, artistaBuscado);
        
        if (filtradas.tamanio() > 0) {
            System.out.println("Canciones de " + artistaBuscado + ":");
            imprimirLista(filtradas);
        } else {
            System.out.println("No se encontraron canciones para el artista ingresado.");
        }

        // c) Estadísticas por artista ordenadas
        System.out.println("\n=== C) ESTADÍSTICAS POR ARTISTA (ORDENADAS DESCENDENTE) ===");
        DobleLinkedList<EstadisticaArtista> estadisticas = generarEstadisticasOrdenadas(listaCanciones);
        for (EstadisticaArtista ea : estadisticas) {
            System.out.println(ea);
        }

        scanner.close();
    }

    // --- MÉTODOS DE LÓGICA DEL NEGOCIO ---

    private static Cancion obtenerCancionMasAntigua(DobleLinkedList<Cancion> lista) {
        if (lista.tamanio() == 0) return null;

        Cancion masAntigua = null;
        for (Cancion c : lista) {
            if (masAntigua == null || c.getAnioLanzamiento() < masAntigua.getAnioLanzamiento()) {
                masAntigua = c;
            }
        }
        return masAntigua;
    }

    private static DobleLinkedList<Cancion> filtrarPorArtista(DobleLinkedList<Cancion> lista, String artista) {
        DobleLinkedList<Cancion> resultado = new DobleLinkedList<>();
        for (Cancion c : lista) {
            if (c.getArtista().equalsIgnoreCase(artista.trim())) {
                resultado.agregarUltimo(c);
            }
        }
        return resultado;
    }

    private static DobleLinkedList<EstadisticaArtista> generarEstadisticasOrdenadas(DobleLinkedList<Cancion> lista) {
        // Paso 1: Agrupar y sumar
        DobleLinkedList<EstadisticaArtista> agrupados = new DobleLinkedList<>();
        
        for (Cancion c : lista) {
            boolean encontrado = false;
            for (EstadisticaArtista ea : agrupados) {
                if (ea.getNombreArtista().equalsIgnoreCase(c.getArtista())) {
                    ea.sumarDuracion(c.getDuracionSegundos());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                agrupados.agregarUltimo(new EstadisticaArtista(c.getArtista(), c.getDuracionSegundos()));
            }
        }

        // Paso 2: Insertar ordenadamente en la lista final
        DobleLinkedList<EstadisticaArtista> listaOrdenada = new DobleLinkedList<>();
        
        // Comparador para ordenar de mayor a menor (descendente)
        Comparator<EstadisticaArtista> comparadorDesc = (ea1, ea2) -> 
            Integer.compare(ea2.getTotalReproducciones(), ea1.getTotalReproducciones());

        for (EstadisticaArtista ea : agrupados) {
            listaOrdenada.agregarOrdenado(ea, comparadorDesc);
        }

        return listaOrdenada;
    }

    // --- MÉTODOS AUXILIARES ---

    private static void cargarCanciones(DobleLinkedList<Cancion> lista) {
        lista.agregarUltimo(new Cancion("Bohemian Rhapsody", "Queen", 1975, 354));
        lista.agregarUltimo(new Cancion("Don't Stop Me Now", "Queen", 1979, 209));
        lista.agregarUltimo(new Cancion("Let It Be", "The Beatles", 1970, 243));
        lista.agregarUltimo(new Cancion("Yesterday", "The Beatles", 1965, 125));
        lista.agregarUltimo(new Cancion("Come Together", "The Beatles", 1969, 259));
        lista.agregarUltimo(new Cancion("Billie Jean", "Michael Jackson", 1982, 294));
        lista.agregarUltimo(new Cancion("Beat It", "Michael Jackson", 1982, 258));
        lista.agregarUltimo(new Cancion("Thriller", "Michael Jackson", 1982, 357));
        lista.agregarUltimo(new Cancion("Shape of You", "Ed Sheeran", 2017, 233));
        lista.agregarUltimo(new Cancion("Perfect", "Ed Sheeran", 2017, 263));
    }

    private static String leerCadena(Scanner scanner, String mensaje) {
        String input;
        do {
            System.out.print(mensaje);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Error: El dato no puede estar vacío. Intente nuevamente.");
            }
        } while (input.isEmpty());
        return input;
    }

    private static void imprimirLista(DobleLinkedList<Cancion> lista) {
        for (Cancion c : lista) {
            System.out.println(" - " + c);
        }
    }
}