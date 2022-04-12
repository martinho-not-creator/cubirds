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

    public int contarEnFila(List<E> fila, E elemento) {
        int contador = 0;
        for (E element : fila) {
            if (element.equals(elemento)) {
                {
                    contador++;
                }
            }
        }
        return contador;
    }

    public List<E> elementosRepetidos(List<E> fila) {
        List<E> toRet = new ArrayList<>();
        for (E element : fila) {
            if (contarEnFila(fila, element) > 1 && !toRet.contains(element)) {
                toRet.add(element);
            }
        }
        return toRet;
    }

    public List<E> eliminarRodeadas(List<E> fila, E elemento, int lado) {
        List<E> posiciones = new ArrayList<>();
        boolean cargando = false;
        if (contarEnFila(fila, elemento) > 1) {
            if (lado == 0) {
                for (int i = 0; i < fila.size(); i++) {
                    E e = fila.get(i);
                    if (elemento.equals(e)) {
                        if (cargando == false) {
                            cargando = true;
                            continue;
                        } else {
                            break;
                        }
                    }
                    if (cargando) {
                        fila.remove(e);
                        posiciones.add(e);
                        i--;
                    }
                }
            } else {
                for (int i = fila.size() - 1; i < -1; i--) {
                    E e = fila.get(i);
                    if (elemento.equals(e)) {
                        if (cargando == false) {
                            cargando = true;
                            continue;
                        } else {
                            break;
                        }
                    }
                    if (cargando) {
                        fila.remove(e);
                        posiciones.add(e);
                        i++;
                    }
                }
            }
        }
        return posiciones;
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

        for (int i = 0; i < tablero.length; i++) {
            for (E elemento : tablero[i]) {
                System.out.print(elemento);
            }
            System.out.println("");
        }

    }

}
