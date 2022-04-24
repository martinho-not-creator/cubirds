package iu;

import core.Baraja;
import core.Carta;
import core.Mesa;
import core.MontonDescartes;
import core.Jugador;
import core.Mano;
import core.ZonaJuego;

import static iu.ES.leeEspecie;
import static iu.ES.leeLado;
import static iu.ES.leeEntero;
import static iu.ES.leeDecision;
import static iu.ES.leeCadena;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class Juego {

    public static final int NUM_CARTAS = 110;
    public static final int NUM_FILAS = 4;
    public static final int NUM_CARTAS_INICIALES_FILA = 3;
    public static final int NUM_CARTAS_MANO_JUGADOR = 8;
    public static final int NUM_CARTAS_ROBAR = 2;
    public static final int NUM_ESPECIES_VICTORIA = 7;

    public static void inicio() {

        Baraja<Carta> baraja = inicializarBaraja();

        MontonDescartes<Carta> descartes = inicializarMontonDescartes();

        Mesa<Carta> mesa = inicializarMesa(baraja);

        int numJugadores = leeEntero("Numero de jugadores: ", true, 2, 5);
        List<Jugador> jugadores = new ArrayList<>();

        for (int i = 0; i < numJugadores; i++) {
            Jugador nuevoJugador = inicializarJugador(baraja);
            jugadores.add(nuevoJugador);
        }

        iniciarJuego(jugadores, mesa, descartes, baraja);

    }

    public static void iniciarJuego(List<Jugador> jugadores, Mesa<Carta> mesa, MontonDescartes<Carta> montonDescartes, Baraja<Carta> baraja) {

        boolean hayGanador = false;
        Jugador jugadorActual = null;

        try {

            boolean decision;

            ListIterator itr = jugadores.listIterator();

            while (!hayGanador && !baraja.esVacio()) {

                jugadorActual = (Jugador) itr.previous();

                System.out.println("Turno del jugador: " + jugadorActual);

                mesa.pintar();

                // Jugar cartas
                decision = leeDecision("Quieres jugar cartas: ");
                if (decision) {
                    jugarCartas(jugadorActual, mesa, baraja);
                }

                // Completar bandadas
                do {
                    List<Carta.AVE> bandadasDisponibles = jugadorActual.especiesDisponiblesMano(true);
                    if (!bandadasDisponibles.isEmpty()) {
                        decision = leeDecision("Quieres completar bandada: ");
                        if (decision) {
                            completarBandada(jugadorActual, bandadasDisponibles, montonDescartes);
                        }
                    } else {
                        decision = false;
                        System.out.println("No hay bandadas para completar");
                    }
                } while (decision);

                // Miramos si el jugador gano
                hayGanador = comprobarVictoria(jugadorActual);

                if (!hayGanador) {

                    // Rellenamos la mano del jugador si no tiene cartas
                    if (jugadorActual.numCartasMano() == 0) {
                        rellenarMano(jugadorActual, baraja);
                    }

                    // Rellenamos las filas si no tienen 2 especies distintas
                    rellenarFilas(mesa, baraja);

                }

            }

        } catch (Exception e) {

            System.err.println(e.getMessage());

        }

        if (hayGanador) {
            System.out.println("Ha ganado el jugador: " + jugadorActual);
        } else {
            Jugador ganador = revisarGanador(jugadores);
            System.out.println("Ha ganado el jugador: " + ganador);
        }

    }

    public static Jugador revisarGanador(List<Jugador> jugadores) {
        Jugador ganador = null;
        int max = -1;
        for (Jugador jugador : jugadores) {
            if (jugador.numEspeciesDistintasZonaJuego() > max) {
                ganador = jugador;
            }
        }
        return ganador;
    }

    public static void rellenarFilas(Mesa<Carta> mesa, Baraja<Carta> baraja) {
        mesa.rellenarFilas(baraja);
    }

    public static void rellenarMano(Jugador jugador, Baraja<Carta> baraja) {
        int cartasInsertadas = 0;
        for (int i = 0; i < NUM_CARTAS_MANO_JUGADOR && !baraja.esVacio(); i++) {
            jugador.anadirCartaMano(baraja.suprimir());
            cartasInsertadas++;
        }
    }

    public static boolean comprobarVictoria(Jugador jugador) {
        return jugador.numEspeciesDistintasZonaJuego() == NUM_ESPECIES_VICTORIA;
    }

    public static void completarBandada(Jugador jugador, List<Carta.AVE> bandadasDisponibles, MontonDescartes<Carta> montonDescartes) throws Exception {

        jugador.mostrarMano();
        jugador.mostrarZonaJuego();

        if (!bandadasDisponibles.isEmpty()) {

            // Seleccionamos la especie que quiere completar
            Carta cartaEspecie = new Carta(leeEspecie("Que bandada quieres completar: ", bandadasDisponibles));

            Stack<Carta> pilaCartas = jugador.eliminarCartasMano(cartaEspecie);

            jugador.anadirCartasZonaJuego(pilaCartas.pop());

            for (Carta carta : pilaCartas) {
                montonDescartes.push(carta);
            }

        }

        jugador.mostrarMano();
        jugador.mostrarZonaJuego();

    }

    public static void jugarCartas(Jugador jugador, Mesa<Carta> mesa, Baraja<Carta> baraja) throws Exception {

        jugador.mostrarMano();
        jugador.mostrarZonaJuego();

        // Jugar cartas
        Carta cartaBajar = new Carta(leeEspecie("Que cartas quieres bajar: ", jugador.especiesDisponiblesMano(false)));

        // Se eliminan las cartas de esa misma especie de la mano
        Stack<Carta> cartasMano = jugador.eliminarCartasMano(cartaBajar);

        // Posiciones para colocar las cartas
        char lado = leeLado("Introduce el lado para bajar " + cartaBajar.getNombre());
        int fila = leeEntero("Introduce la fila para bajar " + cartaBajar.getNombre(), true, 0, NUM_FILAS);

        // Colocamos las cartas en la mesa segun el lado y la fila indicada
        if (lado == 'd') {
            while (!cartasMano.isEmpty()) {
                mesa.insertarDerecha(fila, cartasMano.pop());
            }
        } else {
            while (!cartasMano.isEmpty()) {
                mesa.insertarIzquierda(fila, cartasMano.pop());
            }
        }

        // Comprobamos si rodeamos cartas
        List<Carta> eliminadas = mesa.eliminarRodeadas(fila, cartaBajar, lado);

        // Se han eliminado cartas se agregan a la mano
        if (!eliminadas.isEmpty()) {

            for (Carta carta : eliminadas) {

                jugador.anadirCartaMano(carta);

            }

        } else { // No hemos rodeado, preguntamos si quiere coger cartas

            if (leeDecision("Quieres robar cartas: ")) {

                for (int i = 0; i < NUM_CARTAS_ROBAR; i++) {
                    jugador.anadirCartaMano(baraja.suprimir());
                }

            }

        }

        jugador.mostrarMano();
        jugador.mostrarZonaJuego();

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
        MontonDescartes<Carta> montonDescartes = new MontonDescartes<>();
        return montonDescartes;
    }

    public static Mesa<Carta> inicializarMesa(Baraja baraja) {
        Mesa<Carta> mesa = new Mesa<>();
        mesa.colocarCartasInicio(baraja);
        return mesa;
    }

    public static Jugador inicializarJugador(Baraja baraja) {

        Mano mano = new Mano();

        for (int i = 0; i < NUM_CARTAS_MANO_JUGADOR; i++) {
            mano.insertar(baraja.suprimir());
        }

        ZonaJuego zona = new ZonaJuego();
        zona.insertar(baraja.suprimir());

        String nombre = leeCadena("Introduce el nombre del jugador: ", false);

        return new Jugador(nombre, zona, mano);

    }

}
