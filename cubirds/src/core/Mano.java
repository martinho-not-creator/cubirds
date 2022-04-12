package core;

import java.util.ArrayList;
import java.util.List;
import pila.EnlazadaPila;
import pila.Pila;

public class Mano<E> {

    private List<Pila<E>> zona;

    public Mano() {
        zona = new ArrayList<Pila<E>>();
    }

    public int existePilaElemento(E elemento) {
        for (int i = 0; i < zona.size(); i++) {
            Pila<E> pila = zona.get(i);
            if (!pila.esVacio() && pila.top().equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    public void insertar(E elemento) {
        int pos = existePilaElemento(elemento);
        if (pos != -1) {
            zona.get(pos).push(elemento);
        } else {
            Pila<E> nuevaPila = new EnlazadaPila<>();
            nuevaPila.push(elemento);
            zona.add(nuevaPila);
        }
    }

    public void eliminarElementos(E elementoTipo) throws Exception {
        int pos = existePilaElemento(elementoTipo);
        if (pos == -1) {
            throw new Exception("No existe ese tipo en la mano");
        } else {
            zona.remove(pos);
        }
    }

    public boolean esSuficiente(Carta carta) {
        int pos = existePilaElemento((E) carta);
        if (pos != -1) {
            return zona.get(pos).tamaño() == carta.getBandadaPequena();
        }
        return false;
    }

    public int getNumElementos() {
        int contador = 0;
        for (Pila<E> pila : zona) {
            contador += pila.tamaño();
        }
        return contador;
    }

    public void pintar() {
        System.out.println("Mano");
        for (Pila<E> pila : zona) {
            System.out.println("Pila de " + pila.top() + " | " + pila.tamaño());
        }
    }

}
