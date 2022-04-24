package core;

import java.util.ArrayDeque;
import java.util.Queue;

public class Baraja<E> {

    private Queue<E> elementos;

    public Baraja(int numMaxElementos) {
        elementos = new ArrayDeque<>();
    }

    public int tamaño() {
        return this.elementos.size();
    }

    public boolean esVacio() {
        return this.elementos.isEmpty();
    }

    public void barajar() {

        for (E ele : elementos) {

            int rand = (int) (Math.random() * 10);
            if (rand <= 5) {
                elementos.remove(ele);
                elementos.add(ele);
            }

        }

    }

    public E primero() throws NullPointerException {
        return this.elementos.peek();
    }

    public E suprimir() throws NullPointerException {
        return this.elementos.remove();
    }

    public void insertar(E e) throws IllegalArgumentException {
        this.elementos.add(e);
    }

}
