package iu;

import core.Baraja;
import core.Carta;
import core.Mesa;
import core.MontonDescartes;

public class Juego {

    public static final int NUM_CARTAS = 110;
    public static final int NUM_FILAS = 4;
    public static final int NUM_CARTAS_INICIALES_FILA = 3;

    public static void inicio() {

        Baraja<Carta> baraja = inicializarBaraja();
        MontonDescartes<Carta> descartes = inicializarMontonDescartes();
        Mesa<Carta> mesa = inicializarMesa(baraja);
        //Se crean los jugadores    
        // Se reparten las cartas
        // Empieza el juego
        //Jugador coloca en la mesa
        //Se comprueba si el jugador se ha quedado sin cartas
        //Se pregunta si quiere colocar cartas en la zona de juego
        //Si el jugador no es ganador, se comprueba si se ha quedado sin cartas
        //Se rellena la mesa

    }

    public static Baraja<Carta> inicializarBaraja() {

        Baraja<Carta> baraja = new Baraja<>(NUM_CARTAS);
        Carta.AVE[] aves = Carta.AVE.values();

        for (int i = 0; i < aves.length; i++) {
            for (int j = 0; j < Carta.numeroCartasAve[i]; j++) {
                baraja.insertar(new Carta(aves[i]));
            }
        }

        baraja.barajar();

        return baraja;

    }

    public static MontonDescartes<Carta> inicializarMontonDescartes() {
        MontonDescartes<Carta> montonDescartes = new MontonDescartes<>(NUM_CARTAS);
        return montonDescartes;
    }

    public static Mesa<Carta> inicializarMesa(Baraja baraja) {
        Mesa<Carta> mesa = new Mesa<>();
        mesa.colocarCartasInicio(baraja);
        return mesa;
    }

}
