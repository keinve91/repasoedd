package listasenlazadas.generica;

import java.util.Iterator;
import java.util.Comparator;

public class DobleLinkedList<ELEMENTO> implements Iterable<ELEMENTO> {

    // region Clase Nodo
    private class Nodo<ELEMENTO> {
        public ELEMENTO dato;
        public Nodo<ELEMENTO> siguiente;
        public Nodo<ELEMENTO> anterior;

        public Nodo() {
            this(null, null, null);
        }

        public Nodo(ELEMENTO dato) {
            this(dato, null, null);
        }

        public Nodo(ELEMENTO dato, Nodo<ELEMENTO> siguiente) {
            this(dato, siguiente, null);
        }

        public Nodo(ELEMENTO dato, Nodo<ELEMENTO> siguiente, Nodo<ELEMENTO> anterior) {
            this.dato = dato;
            this.siguiente = siguiente;
            this.anterior = anterior;
        }

        @Override
        public String toString() {
            return this.dato.toString();
        }
    }
    // endregion

    // region Atributos
    private Nodo<ELEMENTO> cabeza;
    private int cantidad;
    private Nodo<ELEMENTO> cola;
    // endregion

    // region Constructores
    public DobleLinkedList() {
        this.cabeza = null;
        this.cantidad = 0;
        this.cola = null;
    }
    // endregion

    // region Métodos de la Lista Enlazada
    public int tamanio() {
        return this.cantidad;
    }

    public void agregarPrimero(ELEMENTO item) {
        Nodo<ELEMENTO> temporal = new Nodo<ELEMENTO>(item, this.cabeza, null);
        if (this.cantidad == 0) {
            this.cola = temporal;
        } else {
            this.cabeza.anterior = temporal;
        }
        this.cabeza = temporal;
        ++this.cantidad;
    }

    public void agregarUltimo(ELEMENTO item) {
        Nodo<ELEMENTO> temporal = new Nodo<ELEMENTO>(item, null, this.cola);
        if (this.cantidad == 0) {
            this.cabeza = temporal;
        } else {
            this.cola.siguiente = temporal;
        }
        this.cola = temporal;
        ++this.cantidad;
    }

    public ELEMENTO eliminarPrimero() {
        if (this.cantidad == 0) {
            throw new RuntimeException("La lista está vacía...");
        }
        ELEMENTO item = this.cabeza.dato;
        this.cabeza = this.cabeza.siguiente;
        if (this.cabeza == null) {
            this.cola = null;
        } else {
            this.cabeza.anterior = null;
        }
        --this.cantidad;
        return item;
    }

    public ELEMENTO eliminarUltimo() {
        if (this.cantidad == 0) {
            throw new RuntimeException("La lista está vacía...");
        }
        ELEMENTO item = this.cola.dato;
        if (this.cabeza.siguiente == null) {
            this.cabeza = this.cola = null;
        } else {
            this.cola = this.cola.anterior;
            this.cola.siguiente = null;
        }
        --this.cantidad;
        return item;
    }

    // Método agregado para el inciso C: Inserción ordenada
    public void agregarOrdenado(ELEMENTO item, Comparator<ELEMENTO> comparador) {
        Nodo<ELEMENTO> nuevoNodo = new Nodo<>(item, null, null);
        
        if (this.cantidad == 0) {
            this.cabeza = this.cola = nuevoNodo;
            this.cantidad++;
            return;
        }

        Nodo<ELEMENTO> actual = this.cabeza;
        // Avanzar mientras el elemento a insertar deba ir después del actual
        while (actual != null && comparador.compare(item, actual.dato) > 0) {
            actual = actual.siguiente;
        }

        if (actual == this.cabeza) {
            // Insertar al principio
            nuevoNodo.siguiente = this.cabeza;
            this.cabeza.anterior = nuevoNodo;
            this.cabeza = nuevoNodo;
        } else if (actual == null) {
            // Insertar al final
            nuevoNodo.anterior = this.cola;
            this.cola.siguiente = nuevoNodo;
            this.cola = nuevoNodo;
        } else {
            // Insertar en el medio (antes de 'actual')
            nuevoNodo.siguiente = actual;
            nuevoNodo.anterior = actual.anterior;
            actual.anterior.siguiente = nuevoNodo;
            actual.anterior = nuevoNodo;
        }
        this.cantidad++;
    }
    // endregion

    // region Métodos de Object
    @Override
    public String toString() {
        if (this.tamanio() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[" + this.cabeza.dato.toString());
        for (Nodo<ELEMENTO> salto = this.cabeza.siguiente; salto != null; salto = salto.siguiente) {
            sb.append(", " + salto.dato.toString());
        }
        sb.append("]");
        return sb.toString();
    }
    // endregion

    // region Métodos Iterables
    @Override
    public Iterator<ELEMENTO> iterator() {
        return new IteradorListaDoble(this.cabeza);
    }

    public class IteradorListaDoble implements Iterator<ELEMENTO> {
        private Nodo<ELEMENTO> actual;

        public IteradorListaDoble(Nodo<ELEMENTO> actual) {
            this.actual = actual;
        }

        @Override
        public boolean hasNext() { // Obligatorio en inglés por la interfaz Iterator de Java
            return this.actual != null;
        }

        @Override
        public ELEMENTO next() { // Obligatorio en inglés por la interfaz Iterator de Java
            if (!this.hasNext()) {
                throw new RuntimeException("La lista está vacía...");
            }
            ELEMENTO item = this.actual.dato;
            this.actual = this.actual.siguiente;
            return item;
        }
    }
    // endregion
}