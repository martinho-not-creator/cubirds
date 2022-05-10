package core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Baraja<E> {

    private Queue<E> elementos;

    public Baraja() {
        elementos = new ArrayDeque<>();
    }

    public int tamaño() {
        return this.elementos.size();
    }

    public boolean esVacio() {
        return this.elementos.isEmpty();
    }

    public void barajar() {

        ArrayList<E> temp = new ArrayList<>(elementos);
        elementos.clear();

        int x;
        
        while (!temp.isEmpty()) {
            x = (int) (Math.random() * temp.size());
            E tempEle = temp.remove(x);
            elementos.add(tempEle);
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
