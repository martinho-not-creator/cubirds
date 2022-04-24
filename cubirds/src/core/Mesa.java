package core;

import iu.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Mesa<E> {

    private List<E>[] tablero;

    public Mesa() {
        tablero = new ArrayList[Juego.NUM_FILAS];
        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = new ArrayList<>();
        }
    }

    public boolean existeEnFila(List<E> fila, E elemento) {
        for (E element : fila) {
            if (element.equals(elemento)) {
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void rellenarFilas(Baraja<E> baraja) {

        for (List<E> fila : tablero) {

            int contador = 0;

            for (Carta.AVE especie : Carta.AVE.values()) {
                if (existeEnFila(fila, (E) especie)) {
                    contador++;
                }
            }

            while (contador < 3) {

                if (!baraja.esVacio()) {

                    Carta nuevaCarta = (Carta) baraja.suprimir();

                    if (!existeEnFila(fila, (E) nuevaCarta)) {

                        fila.add((E) nuevaCarta);
                        contador++;

                    } else {

                        baraja.insertar((E) nuevaCarta);

                    }

                }

            }

        }

    }

    public int contarEnFila(List<E> fila, E elemento, boolean iguales) {
        int contador = 0;
        for (E element : fila) {
            if (iguales && element.equals(elemento)) {
                contador++;
            } else if (!iguales && !element.equals(elemento)) {
                contador++;
            }
        }
        return contador;
    }

    public List<E> elementosRepetidos(List<E> fila) {
        List<E> toRet = new ArrayList<>();
        for (E element : fila) {
            if (contarEnFila(fila, element, true) > 1 && !toRet.contains(element)) {
                toRet.add(element);
            }
        }
        return toRet;
    }

    public List<E> eliminarRodeadas(int numFila, E elemento, char lado) {

        List<E> elementos = new ArrayList<>();

        List<E> fila = tablero[numFila];

        boolean cargando = false;

        if (contarEnFila(fila, elemento, true) > 1) { // Existe en esa fila elementos repetidos

            if (lado == 'i') { // De izquierda a derecha

                for (E ele : fila) {

                    elementos.add(ele);

                    if (!ele.equals(elemento)) { // Me encuentro con el primero que no es igual

                        cargando = true;

                    } else { // Los elementos son iguales

                        if (cargando = true) { // Si ya hemos visto uno distinto al siguiente igual se termina

                            break;

                        }

                    }

                }

            } else { // De izquierda a derecha

                ListIterator itr = fila.listIterator();

                for (int i = 0; i < fila.size(); i++) {

                    E ele = (E) itr.previous();
                    elementos.add(ele);

                    if (!ele.equals(elemento)) { // Me encuentro con el primero que no es igual

                        cargando = true;

                    } else { // Los elementos son iguales

                        if (cargando = true) { // Si ya hemos visto uno distinto al anterior igual se termina

                            break;

                        }

                    }

                }

            }
        }

        return elementos;

    }

    public void colocarCartasInicio(Baraja baraja) {

        for (int i = 0; i < tablero.length; i++) {

            int contador = 0;

            while (contador < 3) {

                Carta nuevaCarta = (Carta) baraja.suprimir();

                if (!existeEnFila(tablero[i], (E) nuevaCarta)) {
                    tablero[i].add((E) nuevaCarta);
                    contador++;
                } else {
                    baraja.insertar(nuevaCarta);
                }

            }

        }

    }

    public void insertarDerecha(int fila, E elemento) {
        tablero[fila].add(elemento);
    }

    public void insertarIzquierda(int fila, E elemento) {
        tablero[fila].add(0, elemento);
    }

    public void pintar() {

        for (List<E> tablero1 : tablero) {
            for (E elemento : tablero1) {
                System.out.print(elemento);
            }
            System.out.println("");
        }

    }

}
