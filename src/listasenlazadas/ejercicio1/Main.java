package listasenlazadas.ejercicio1;
import java.util.InputMismatchException;
import java.util.Scanner;
import listasenlazadas.generica.SimpleLinkedList;
public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		SimpleLinkedList<Producto> inventario = new SimpleLinkedList<>();
		inventario.agregarAlFinal(new Producto("P01","Teclado",45.0,10));
		inventario.agregarAlFinal(new Producto("P02","Mouse",15.0,25));
		inventario.agregarAlFinal(new Producto("P03","Monitor",150.3,5));
		boolean salir = false;
		while(!salir) {
			System.out.println("\n--- ESTADO DE LA LISTA ---");
			int i=0;
			for(Producto p:inventario) {
				System.out.println("Pos " + i + ": " + p.toString());
				i++;
			}
			System.out.println("--------------------------");
			
			System.out.println("\nOpciones:");
			System.out.println("1. Agregar producto en posición específica");
			System.out.println("2. Eliminar producto en posición específica");
			System.out.println("3. Salir");
			
			int opcion=leerEntero(sc,"Seleccione una opcion: ");
			switch(opcion) {
			case 1:
				insertarProducto(sc,inventario);
				break;
			case 2:
				eliminarProducto(sc,inventario);
				break;
			case 3:
				salir=true;
				System.out.println("Saliendo...");
				break;
			default:
				System.out.println("Error: Opción inválida.");
			}
			
		}
		sc.close();
	}
	
	private static int leerEntero(Scanner sc, String mensaje){
		while(true) {
			try {
				System.out.print(mensaje);
				int valor = sc.nextInt();
				sc.nextLine();
				return valor;
			}catch(InputMismatchException e) {
				System.out.println("Error: Ingrese un número entero válido.");
				sc.nextLine();
			}
		}
	}
	
	private static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número decimal válido.");
                sc.nextLine();
            }
        }
    }

    // --- MÓDULOS DE OPERACIÓN ---
    private static void insertarProducto(Scanner sc, SimpleLinkedList<Producto> lista) {
        int pos = leerEntero(sc, "Ingrese la posición donde insertar (0 a " + lista.tamanio() + "): ");
        System.out.print("Ingrese código: ");
        String cod = sc.nextLine();
        System.out.print("Ingrese nombre: ");
        String nom = sc.nextLine();
        double precio = leerDouble(sc, "Ingrese precio: ");
        int stock = leerEntero(sc, "Ingrese stock: ");

        try {
            lista.insertar(pos, new Producto(cod, nom, precio, stock));
            System.out.println("\n[!] Producto insertado correctamente.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }

    private static void eliminarProducto(Scanner sc, SimpleLinkedList<Producto> lista) {
        if(lista.tamanio() == 0){
            System.out.println("\n[ERROR] La lista ya está vacía.");
            return;
        }
        int max = lista.tamanio() - 1;
        int pos = leerEntero(sc, "Ingrese la posición a eliminar (0 a " + max + "): ");
        try {
            Producto eliminado = lista.eliminar(pos);
            System.out.println("\n[!] Producto eliminado: " + eliminado.toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\n[ERROR] " + e.getMessage());
        }
    }
    
}

