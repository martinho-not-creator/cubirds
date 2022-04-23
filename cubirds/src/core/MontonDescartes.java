package core;

import pila.*;

public class MontonDescartes<E> implements Pila<E> {

    private E[] elementos;
    private int contador;

    public MontonDescartes(int maxTamano) {
        this.elementos = (E[]) new Object[maxTamano];
        this.contador = 0;
    }

    @Override
    public int tamaño() {
        return this.contador;
    }

    @Override
    public boolean esVacio() {
        return this.contador == 0;
    }

    @Override
    public E top() throws NullPointerException {
        return elementos[contador--];
    }

    @Override
    public void push(E elemento) {
        elementos[contador] = elemento;
        contador++;
    }

    @Override
    public void push(E... elementos) {
        for (E elemento : elementos) {
            elementos[contador] = elemento;
            contador++;
        }
    }

    @Override
    public E pop() throws NullPointerException {
        E elemento = elementos[contador--];
        contador--;
        return elemento;
    }

}
