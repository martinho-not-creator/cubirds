package core;

import iu.*;
import lista.IteradorLista;
import lista.Lista;
import lista.ListaEnlazada;

public class Mesa<E> {

    private Lista<E>[] tablero;

    public Mesa() {
        tablero = new ListaEnlazada[Juego.NUM_FILAS];
        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = new ListaEnlazada<>();
        }
    }

    public boolean existeEnFila(Lista<E> fila, E elemento) {
        for (E element : fila) {
            if (element.equals(elemento)) {
                {
                    return true;
                }
            }
        }
        return false;
    }

    public int contarEnFila(Lista<E> fila, E elemento) {
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

    public Lista<E> elementosRepetidos(Lista<E> fila) {
        Lista<E> toRet = new ListaEnlazada<>();
        for (E element : fila) {
            if (contarEnFila(fila, element) > 1 && !toRet.contiene(element)) {
                toRet.insertarFinal(element);
            }
        }
        return toRet;
    }

    public Lista<E> eliminarRodeadas(int numFila, E elemento, char lado) {

        Lista<E> elementos = new ListaEnlazada<>();

        Lista<E> fila = tablero[numFila];

        boolean cargando = false;

        if (contarEnFila(fila, elemento) > 1) { // Existe en esa fila elementos repetidos

            if (lado == 'i') { // De izquierda a derecha

                for (E ele : fila) {

                    elementos.insertarFinal(ele);

                    if (!ele.equals(elemento)) { // Me encuentro con el primero que no es igual

                        cargando = true;

                    } else { // Los elementos son iguales

                        if (cargando = true) { // Si ya hemos visto uno distinto al siguiente igual se termina

                            break;

                        }

                    }

                }

            } else { // De izquierda a derecha

                IteradorLista itr = fila.iteradorLista();

                for (int i = 0; i < fila.tamaño(); i++) {

                    E ele = (E) itr.previous();
                    elementos.insertarFinal(ele);

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
                    tablero[i].insertarFinal((E) nuevaCarta);
                    contador++;
                } else {
                    baraja.insertar(nuevaCarta);
                }

            }

        }

    }

    public void insertarDerecha(int fila, E elemento) {
        tablero[fila].insertarFinal(elemento);
    }

    public void insertarIzquierda(int fila, E elemento) {
        tablero[fila].insertarPrincipio(elemento);
    }

    public void pintar() {

        for (Lista<E> tablero1 : tablero) {
            for (E elemento : tablero1) {
                System.out.print(elemento);
            }
            System.out.println("");
        }

    }

}
