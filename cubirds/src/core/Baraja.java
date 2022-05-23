package core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Baraja {

    private Queue<Carta> elementos;

    public Baraja() {

        elementos = new ArrayDeque<>();

        Carta.AVE[] aves = Carta.AVE.values();

        for (int i = 0; i < aves.length; i++) {
            for (int j = 0; j < Carta.numeroCartasAve[i]; j++) {
                elementos.add(new Carta(aves[i]));
            }
        }

    }

    public int tamaño() {
        return this.elementos.size();
    }

    public boolean esVacio() {
        return this.elementos.isEmpty();
    }

    public void barajar() {

        ArrayList<Carta> temp = new ArrayList<>(elementos);
        elementos.clear();

        int x;

        while (!temp.isEmpty()) {
            x = (int) (Math.random() * temp.size());
            Carta tempEle = temp.remove(x);
            elementos.add(tempEle);
        }

    }

    public Carta suprimir() throws NullPointerException {
        return this.elementos.remove();
    }

    public void insertar(Carta carta) throws IllegalArgumentException {
        this.elementos.add(carta);
    }

}
