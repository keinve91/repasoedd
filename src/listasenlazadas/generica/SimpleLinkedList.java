package listasenlazadas.generica;

import java.util.Iterator;
public class SimpleLinkedList<T> implements Iterable<T> {

    // --- CLASE INTERNA NODO ---
    private class Nodo<T> {
        public T dato;
        public Nodo<T> siguiente;

        public Nodo(T dato, Nodo<T> siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
        
        @Override
        public String toString() {
            return this.dato.toString();
        }
    }

    // --- ATRIBUTOS ---
    protected Nodo<T> cabeza; // head
    protected Nodo<T> cola;   // tail
    protected int cantidad;   // count

    // --- CONSTRUCTOR ---
    public SimpleLinkedList() {
        this.cabeza = null;
        this.cola = null;
        this.cantidad = 0;
    }

    // --- MÉTODOS BÁSICOS 
    public int tamanio() {
        return this.cantidad;
    }

    public void agregarAlPrincipio(T dato) {
        Nodo<T> temporal = new Nodo<>(dato, this.cabeza);
        if (this.cantidad == 0) {
            this.cola = temporal;
        }
        this.cabeza = temporal;
        this.cantidad++;
    }

    public void agregarAlFinal(T dato) {
        Nodo<T> temporal = new Nodo<>(dato, null);
        if (this.cantidad == 0) {
            this.cabeza = temporal;
        } else {
            this.cola.siguiente = temporal;
        }
        this.cola = temporal;
        this.cantidad++;
    }

    public T eliminarElPrimero() {
        if (this.cantidad == 0) {
            throw new RuntimeException("La lista está vacía...");
        }
        T dato = this.cabeza.dato;
        this.cabeza = this.cabeza.siguiente;
        if (this.cabeza == null) {
            this.cola = null;
        }
        this.cantidad--;
        return dato;
    }

    // --- EJERCICIOS PROPUESTOS ---

    // a) Agregar en una posición ingresada por el usuario
    public void insertar(int posicion, T dato) {
        if (posicion < 0 || posicion > this.cantidad) {
            throw new IndexOutOfBoundsException("Posición fuera de límites. Tamaño actual: " + this.cantidad);
        }

        // Si es al principio, reciclamos el método que ya gestiona la cabeza
        if (posicion == 0) {
            agregarAlPrincipio(dato);
            return;
        }

        // Si es al final exacto, reciclamos el método que gestiona la cola
        if (posicion == this.cantidad) {
            agregarAlFinal(dato);
            return;
        }

        // Si es en el medio:
        Nodo<T> actual = this.cabeza;
        for (int i = 0; i < posicion - 1; i++) {
            actual = actual.siguiente;
        }

        Nodo<T> nuevo = new Nodo<>(dato, actual.siguiente);
        actual.siguiente = nuevo;
        this.cantidad++;
    }

    // b) Eliminar un elemento en una posición ingresada
    public T eliminar(int posicion) {
        if (posicion < 0 || posicion >= this.cantidad) {
            throw new IndexOutOfBoundsException("Posición fuera de límites. Tamaño actual: " + this.cantidad);
        }

        // Si es el primero, reciclamos el método ya hecho
        if (posicion == 0) {
            return eliminarElPrimero();
        }

        Nodo<T> actual = this.cabeza;
        for (int i = 0; i < posicion - 1; i++) {
            actual = actual.siguiente;
        }

        Nodo<T> nodoAEliminar = actual.siguiente;
        T datoEliminado = nodoAEliminar.dato;

        // Saltamos el nodo eliminado
        actual.siguiente = nodoAEliminar.siguiente;

        // IMPORTANTE: Si el que eliminamos era el último, la 'cola' debe apuntar al actual
        if (actual.siguiente == null) {
            this.cola = actual;
        }

        this.cantidad--;
        return datoEliminado;
    }
 // c) Método para insertar manteniendo un orden basado en un Comparator
    public void agregarOrdenado(T dato, java.util.Comparator<T> comparador) {
        Nodo<T> nuevoNodo = new Nodo<>(dato, null);

        // Si la lista está vacía o el nuevo dato es "menor" que el primero
        if (this.cabeza == null || comparador.compare(this.cabeza.dato, dato) >= 0) {
            nuevoNodo.siguiente = this.cabeza;
            this.cabeza = nuevoNodo;
            if (this.cantidad == 0) {
                this.cola = nuevoNodo; // Si era el primero, también es la cola
            }
            this.cantidad++;
        } else {
            // Buscar la posición de inserción en el medio o al final
            Nodo<T> actual = this.cabeza;
            while (actual.siguiente != null && comparador.compare(actual.siguiente.dato, dato) < 0) {
                actual = actual.siguiente;
            }
            
            // Insertar después del nodo 'actual'
            nuevoNodo.siguiente = actual.siguiente;
            actual.siguiente = nuevoNodo;
            
            // Si lo insertamos justo al final, actualizamos el puntero 'cola'
            if (nuevoNodo.siguiente == null) {
                this.cola = nuevoNodo;
            }
            this.cantidad++;
        }
    }

    // --- MÉTODOS DE OBJETO E ITERADOR ---
    @Override
    public String toString() {
        if (this.tamanio() <= 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(this.cabeza.dato.toString());
        for (Nodo<T> salto = this.cabeza.siguiente; salto != null; salto = salto.siguiente) {
            sb.append(", ").append(salto.dato.toString());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorListaEnlazadaSimple(this.cabeza);
    }

    private class IteradorListaEnlazadaSimple implements Iterator<T> {
        private Nodo<T> actual;

        public IteradorListaEnlazadaSimple(Nodo<T> actual) {
            this.actual = actual;
        }

        @Override
        public boolean hasNext() {
            return this.actual != null;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new RuntimeException("No hay más elementos...");
            }
            T dato = this.actual.dato;
            this.actual = this.actual.siguiente;
            return dato;
        }
    }
}
