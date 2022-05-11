package core;

import java.util.Stack;

public class MontonDescartes<E> {

    private Stack<E> elementos;

    public MontonDescartes() {
        this.elementos = new Stack<>();
    }

    public void insertar(E elemento) {
        this.elementos.push(elemento);
    }

}
