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

    public E top() throws NullPointerException {
        return this.elementos.peek();
    }

    public void push(E elemento) {
        this.elementos.push(elemento);
    }

    public void push(E... elementos) {
        for (E elemento : elementos) {
            this.elementos.push(elemento);
        }
    }

    public E pop() throws NullPointerException {
        return this.elementos.pop();
    }

}
