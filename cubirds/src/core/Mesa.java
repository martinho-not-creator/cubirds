package core;

import iu.Juego;
import static iu.Juego.NUM_MIN_ESPECIES_FILA;
import static iu.Juego.NUM_MIN_INICIAL_ESPECIES_FILA;
import java.util.ArrayList;
import java.util.List;

public class Mesa {

    private List<Carta>[] tablero;

    public Mesa(int numFilas) {
        tablero = new ArrayList[numFilas];
        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = new ArrayList<>();
        }
    }

    public boolean existeEnFila(List<Carta> fila, Carta.AVE especie) {
        for (Carta carta : fila) {
            if (carta.getNombre() == especie) {
                return true;
            }
        }
        return false;
    }

    public boolean rellenarFilas(Baraja baraja, boolean esInicio) {

        for (List<Carta> fila : tablero) {

            int contadorEspeciesDistintas = 0;

            for (Carta.AVE especie : Carta.AVE.values()) {
                if (existeEnFila(fila, especie)) {
                    contadorEspeciesDistintas++;
                }
            }

            while ((esInicio && contadorEspeciesDistintas < NUM_MIN_INICIAL_ESPECIES_FILA) || contadorEspeciesDistintas < NUM_MIN_ESPECIES_FILA) {

                if (baraja.esVacio()) {

                    return false;

                }

                Carta nuevaCarta = baraja.suprimir();

                if ((esInicio && contadorEspeciesDistintas < Juego.NUM_MIN_INICIAL_ESPECIES_FILA)) {

                    if (!existeEnFila(fila, nuevaCarta.getNombre())) {

                        fila.add(nuevaCarta);
                        contadorEspeciesDistintas++;

                    } else {

                        baraja.insertar(nuevaCarta);

                    }

                } else {

                    if (!existeEnFila(fila, nuevaCarta.getNombre())) {

                        contadorEspeciesDistintas++;

                    }

                    fila.add(nuevaCarta);

                }

            }

        }

        return true;

    }

    public int contarEnFila(List<Carta> fila, Carta.AVE especie, boolean iguales) {
        int contador = 0;
        for (Carta carta : fila) {
            if (iguales && carta.getNombre() == especie) {
                contador++;
            } else if (!iguales && carta.getNombre() != especie) {
                contador++;
            }
        }
        return contador;
    }

    public List<Carta> eliminarRodeadas(int numFila, Carta.AVE especie, char lado) {

        List<Carta> fila = tablero[numFila];
        List<Carta> cartasRodeadas = new ArrayList<>();

        if (existeEnFila(fila, especie)) {

            // Vamos de izquierda a derecha
            if (lado == 'i') {

                while (fila.get(0).getNombre() != especie) {

                    cartasRodeadas.add(fila.remove(0));

                }

            } else {

                while (fila.get(fila.size() - 1).getNombre() != especie) {

                    cartasRodeadas.add(fila.remove(fila.size() - 1));

                }

            }

        }

        return cartasRodeadas;

    }

    public void insertarDerecha(int fila, Carta carta) {
        tablero[fila].add(carta);
    }

    public void insertarIzquierda(int fila, Carta carta) {
        tablero[fila].add(0, carta);
    }

    public void pintar() {

        for (List<Carta> tablero1 : tablero) {

            for (Carta carta : tablero1) {

                System.out.print(carta);

            }

            System.out.println();

        }

    }

}
