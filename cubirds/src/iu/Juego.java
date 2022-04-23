package iu;

import core.Baraja;
import core.Carta;
import core.Mesa;
import core.MontonDescartes;

public class Juego {

    public static final int NUM_CARTAS = 110;
    public static final int NUM_FILAS = 4;
    public static final int NUM_CARTAS_INICIALES_FILA = 3;
    public static final int NUM_CARTAS_MANO_JUGADOR = 8;

    public static void inicio() {

        Baraja<Carta> baraja = inicializarBaraja();

        MontonDescartes<Carta> descartes = inicializarMontonDescartes();

        Mesa<Carta> mesa = inicializarMesa(baraja);

        Jugador jugador1 = inicializarJugador("Jugador 1", baraja);
        Jugador jugador2 = inicializarJugador("Jugador 2", baraja);

        iniciarJuego(jugador1, jugador2, mesa, descartes, baraja);

        // Empieza el juego
        //Jugador coloca en la mesa
        //Se comprueba si el jugador se ha quedado sin cartas
        //Se pregunta si quiere colocar cartas en la zona de juego
        //Si el jugador no es ganador, se comprueba si se ha quedado sin cartas
        //Se rellena la mesa

    }

    public iniciarJuego(Jugador jugador1, Jugador jugador2, Mesa<Carta> mesa, MontonDescartes<Carta> descartes, Baraja<Carta> baraja) {
        // TODO - implement Juego.iniciarJuego
        throw new UnsupportedOperationException();
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

    public static Jugador inicializarJugador(String nombre, Baraja baraja) {

        Mano mano = inicializarMano();

        for (int i = 0; i < NUM_CARTAS_MANO_JUGADOR; i++) {
            mano.anadirCarta(baraja.suprimir());
        }

        ZonaJuego zona = inicializarZonaJuego();
        zona.insertar(baraja.suprimir());

        return new Jugador(nombre, mano, zona);

    }

}
