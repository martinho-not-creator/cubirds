package core;

import java.util.Stack;

public class MontonDescartes<E> {

    private Stack<E> elementos;

    public MontonDescartes() {
        this.elementos = new Stack<>();
    }

    public int tamaño() {
        return this.elementos.size();
    }

    public boolean esVacio() {
        return this.elementos.isEmpty();
    }

    public E primero() throws NullPointerException {
        return this.elementos.peek();
    }

    public void insertar(E elemento) {
        this.elementos.push(elemento);
    }

    public E eliminar() throws NullPointerException {
        return this.elementos.pop();
    }

}
