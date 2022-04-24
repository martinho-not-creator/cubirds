package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ZonaJuego<E> {

    private List<Stack<E>> zona;

    public ZonaJuego() {
        zona = new ArrayList<Stack<E>>();
    }

    public int existePilaAve(E elemento) {
        for (int i = 0; i < zona.size(); i++) {
            Stack<E> pila = zona.get(i);
            if (!pila.isEmpty() && pila.peek().equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    public void insertar(E elemento) {
        int pos = existePilaAve(elemento);
        if (pos != -1) {
            zona.get(pos).push(elemento);
        } else {
            Stack<E> nuevaPila = new Stack<>();
            nuevaPila.push(elemento);
            zona.add(nuevaPila);
        }
    }

    public String cartasPorEspecie() {
        String m = "";
        for (Stack<E> pila : zona) {
            if (!pila.isEmpty()) {
                Carta carta = (Carta) pila.peek();
                m += carta.getNombre() + " - " + pila.size() + "\n";
            }
        }
        return m;
    }

    public int getNumEspecies() {
        int numEspecies = 0;
        for (Stack<E> pila : zona) {
            if (!pila.isEmpty()) {
                numEspecies++;
            }
        }
        return numEspecies;
    }

    public int getNumCartas() {
        int total = 0;
        for (Stack<E> pila : zona) {
            total += pila.size();
        }
        return total;
    }

    public void pintar() {
        for (Stack<E> pila : zona) {
            System.out.println("Pila de " + pila.peek() + " | " + pila.size());
        }
    }

}
