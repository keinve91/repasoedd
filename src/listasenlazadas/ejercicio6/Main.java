package listasenlazadas.ejercicio6;

import listasenlazadas.generica.SimpleLinkedList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("=== GENERACIÓN DE LISTA ALEATORIA ===");
		int n = leerEntero(scan,"Ingrese la cantidad de numeros a generar (N): ");
		SimpleLinkedList<Integer> listaOriginal = generarAleatorio(n,-50,50);
		System.out.println("\nLista Original");
		System.out.println(listaOriginal);
		
		System.out.println("\n=== A) NEGATIVOS AL PRINCIPIO Y POSITIVOS AL FINAL ===");
		SimpleLinkedList<Integer> listaSeparada = separarNegativosyPositivos(listaOriginal);
		System.out.println(listaSeparada);
		
		System.out.println("\n=== B) SUMA EN RANGO [A, B] ===");
		int a = leerEntero(scan,"Ingrese el valor inicial del rango (A): ");
		int b = leerEntero(scan,"Ingrese el valor final del rango (B): ");
		
		if(a>b) {
			System.out.println("Nota: Como A (" + a + ") es mayor que B (" + b + "), se invertirá el rango a [" + b + ", " + a + "].");
			int temp=a;
			a=b;
			b=temp;
		}
		int suma = sumarEnRango(listaOriginal,a,b);
		System.out.println("La suma de los números en el rango [" + a + ", " + b + "] es: " + suma);
		
		System.out.println("\n=== C) LISTA ORDENADA ASCENDENTE ===");
		SimpleLinkedList<Integer> listaOrdenada = ordenarAscendente(listaOriginal);
		System.out.println(listaOrdenada);
		
		scan.close();
		
	}
	
	private static SimpleLinkedList<Integer> generarAleatorio(int n, int min, int max){
		SimpleLinkedList<Integer> lista = new SimpleLinkedList<>();
		Random random = new Random();
		for(int i=0; i<n;i++) {
			int numero = random.nextInt((max-min)+min);
			lista.agregarAlFinal(numero);	
		}
		return lista;
	}
	
	private static SimpleLinkedList<Integer> separarNegativosyPositivos(SimpleLinkedList<Integer> lista){
		SimpleLinkedList<Integer> nuevaLista = new SimpleLinkedList<>();
		for(Integer num:lista) {
			if(num<0) {
				nuevaLista.agregarAlPrincipio(num);
			}else {
				nuevaLista.agregarAlFinal(num);
			}
		}
		return nuevaLista;
	}
	
	private static int sumarEnRango(SimpleLinkedList<Integer> lista, int a, int b) {
		int suma=0;
		for(Integer num :lista) {
			if (num>= a && num<=b) {
				suma+=num;
				
			}
		}
		return suma;
	}
	
	private static SimpleLinkedList<Integer>ordenarAscendente(SimpleLinkedList<Integer> lista){
		SimpleLinkedList<Integer> ordenada = new SimpleLinkedList<>();
		Comparator<Integer>comparadorAsc=Comparator.naturalOrder();
		
		for (Integer num:lista) {
			ordenada.agregarOrdenado(num, comparadorAsc);
			
		}
		return ordenada;
	}
	
	private static int leerEntero(Scanner scan, String mensaje) {
		while(true) {
			System.out.print(mensaje);
			try {
				return Integer.parseInt(scan.nextLine().trim());
				
			}catch(NumberFormatException e) {
				System.out.println("Error: Debe ingresar un numero entero valido");
				
			}
		}
	}
	
	private static int leerEnteroPositivo(Scanner scan, String mensaje) {
		while(true) {
			int valor = leerEntero(scan,mensaje);
				if(valor>0) {
					return valor;
				}
				System.out.println("Error: La cantidad N debe ser mayor a cero.");
			}
		}
	

}

