package core;

import cola.*;

public class Baraja<E> implements Cola<E> {

    private E[] elementos;
    private int numElementos;

    public Baraja(int numMaxElementos) {
        this.elementos = (E[]) new Object[numMaxElementos];
        this.numElementos = 0;
    }

    @Override
    public int tamaño() {
        return this.numElementos;
    }

    @Override
    public boolean esVacio() {
        return this.numElementos == 0;
    }

    public void barajar() {
        for (int i = 0; i < numElementos; i++) {
            int x = (int) (Math.random() * numElementos);
            E temp = elementos[i];
            elementos[i] = elementos[x];
            elementos[x] = temp;
        }
    }

    @Override
    public E primero() throws NullPointerException {
        return elementos[0];
    }

    @Override
    public E suprimir() throws NullPointerException {
        E elemento = this.elementos[0];
        for (int i = 0; i < numElementos - 1; i++) {
            this.elementos[i] = this.elementos[i + 1];
        }
        this.numElementos--;
        return elemento;
    }

    @Override
    public void insertar(E e) throws IllegalArgumentException {
        this.elementos[this.numElementos] = e;
        this.numElementos++;
    }

}
