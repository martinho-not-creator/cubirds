package core;

import java.util.ArrayList;
import java.util.List;

import pila.EnlazadaPila;
import pila.Pila;

public class ZonaJuego<E> {

    private List<Pila<E>> zona;

    public ZonaJuego() {
        zona = new ArrayList<Pila<E>>();
    }

    public int existePilaAve(E elemento) {
        for (int i = 0; i < zona.size(); i++) {
            Pila<E> pila = zona.get(i);
            if (!pila.esVacio() && pila.top().equals(elemento)) {
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
            Pila<E> nuevaPila = new EnlazadaPila<>();
            nuevaPila.push(elemento);
            zona.add(nuevaPila);
        }
    }

    public String cartasPorEspecie() {
        String m = "";
        for(Pila pila : zona) {
            if (!pila.esVacio()) {
                m += pila.top().getNombre() + " - " + pila.tamaño() "\n";
            }
        }
       return m;
    }

    public boolean existe

    public int getNumEspecies() {
        int numEspecies = 0;
        for (Pila<E> pila : zona) {
            if (!pila.esVacio()) {
                numEspecies++;
            }
        }
        return numEspecies;
    }

    public int getNumCartas() {
        int total = 0;
        for (Pila<E> pila : zona) {
            total += pila.tamaño();
        }
        return total;
    }

    public <Carta> void pintar() {
        for (Pila<E> pila : zona) {
            System.out.println("Pila de " + pila.top() + " | " + pila.tamaño());
        }
    }

}
