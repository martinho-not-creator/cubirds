package core;

import iu.*;
import java.util.ArrayList;
import java.util.List;

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
                if (existeEnFila(fila, (E) new Carta(especie))) {
                    contador++;
                }
            }

            while (contador < 2 || fila.size() < 3) {

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
        List<E> copiaFila = fila;

        int contadorDistintos = 0;
        int contador = 0;

        if (contarEnFila(fila, elemento, true) > 1) { // Existe en esa fila elementos repetidos

            if (lado == 'i') { // De izquierda a derecha

                while (contarEnFila(copiaFila, elemento, true) > 0) {

                    E elementoExtraido = fila.remove(contador);

                    if (!elementoExtraido.equals(elemento)) {
                        contadorDistintos++;
                    }
                    elementos.add(elemento);
                    contador++;

                }

            } else { // De derecha a izquierda

                contador = fila.size() - 1;

                while (contarEnFila(copiaFila, elemento, true) > 0) {

                    E elementoExtraido = fila.remove(contador);

                    if (!elementoExtraido.equals(elemento)) {
                        contadorDistintos++;
                    }

                    elementos.add(elementoExtraido);
                    contador--;

                }

            }
        }

        // No hemos encontrado ningun distinto no se actualiza la fila ni se devuelve ningun elemento
        if (contadorDistintos == 0) {
            elementos.clear();
        } else {
            // Al encontrar elementos distintos a lo que queremos insertar actualizamos la fila a la modificadda
            tablero[numFila] = copiaFila;
        }

        return elementos;

    }

    public void colocarCartasInicio(Baraja baraja) {

        rellenarFilas(baraja);

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
