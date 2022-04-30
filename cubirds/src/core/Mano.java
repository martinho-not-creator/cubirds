package core;

import static iu.Juego.NUM_CARTAS_MANO_JUGADOR;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Mano<E> {

    private List<Stack<E>> zona;

    public Mano() {
        zona = new ArrayList<Stack<E>>();
    }

    public boolean rellenar(Baraja<E> baraja) {
        for (int i = getNumElementos(); i < NUM_CARTAS_MANO_JUGADOR; i++) {
            if (baraja.esVacio()) {
                return false;
            }
            insertar(baraja.suprimir());
        }
        return true;
    }

    public int existePilaElemento(E elemento) {
        for (int i = 0; i < zona.size(); i++) {
            Stack<E> pila = zona.get(i);
            if (!pila.empty() && pila.peek().equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    public List<Carta.AVE> especiesDisponibles(boolean minTamBandada) {
        List<Carta.AVE> lista = new ArrayList<>();
        for (Stack<E> pila : zona) {
            Carta carta = (Carta) pila.peek();
            if (minTamBandada && !esSuficiente(carta)) {
                continue;
            }
            lista.add(carta.getNombre());
        }
        return lista;
    }

    public void insertar(E elemento) {
        int pos = existePilaElemento(elemento);
        if (pos != -1) {
            zona.get(pos).push(elemento);
        } else {
            Stack<E> nuevaPila = new Stack<>();
            nuevaPila.push(elemento);
            zona.add(nuevaPila);
        }
    }

    public Stack<E> eliminarElementos(E elementoTipo) throws Exception {
        int pos = existePilaElemento(elementoTipo);
        if (pos == -1) {
            throw new Exception("No existe ese tipo en la mano");
        } else {
            return zona.remove(pos);
        }
    }

    public boolean esSuficiente(Carta carta) {
        int pos = existePilaElemento((E) carta);
        if (pos != -1) {
            return zona.get(pos).size() >= carta.getBandadaPequena();
        }
        return false;
    }

    public int getNumElementos() {
        int contador = 0;
        for (Stack<E> pila : zona) {
            contador += pila.size();
        }
        return contador;
    }

    public void pintar() {
        for (Stack<E> pila : zona) {
            System.out.println("Pila de " + pila.peek() + " | " + pila.size());
        }
    }

}
