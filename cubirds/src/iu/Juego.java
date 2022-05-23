package iu;

import core.Baraja;
import core.Carta;
import core.Mesa;
import core.MontonDescartes;
import core.Jugador;

import static iu.ES.leeEspecie;
import static iu.ES.leeLado;
import static iu.ES.leeEntero;
import static iu.ES.leeDecision;
import static iu.ES.leeCadena;
import java.util.ArrayDeque;
import java.util.ArrayList;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Juego {

    public static final int NUM_FILAS = 4;
    public static final int NUM_CARTAS_MANO_JUGADOR = 8;
    public static final int NUM_CARTAS_ROBAR = 2;
    public static final int NUM_ESPECIES_VICTORIA = 7;
    public static final int NUM_MIN_ESPECIES_FILA = 2;
    public static final int NUM_MIN_INICIAL_ESPECIES_FILA = 3;

    public static void inicio() {

        Baraja baraja = inicializarBaraja();

        MontonDescartes descartes = inicializarMontonDescartes();

        Mesa mesa = inicializarMesa(baraja);

        int numJugadores = leeEntero("Numero de jugadores: ", true, 2, 5);
        Queue<Jugador> jugadores = inicializarJugadores(numJugadores, baraja);

        iniciarJuego(jugadores, mesa, descartes, baraja);

    }

    public static void iniciarJuego(Queue<Jugador> jugadores, Mesa mesa, MontonDescartes montonDescartes, Baraja baraja) {

        boolean hayGanador = false;
        Jugador jugadorActual = null;

        // Flag que nos indica si se ha rellenado la mano del jugador y las filas de la mesa si necesario
        boolean completadasTareas = true;
        boolean decision;

        while (!hayGanador && completadasTareas) {

            try {

                jugadorActual = jugadores.remove();
                jugadores.add(jugadorActual);

                System.out.println("Turno del jugador: " + jugadorActual);
                System.out.println("Tamaño de la baraja: " + baraja.tamaño());

                mesa.pintar();

                // Jugar cartas
                boolean completadoRobo = jugarCartas(jugadorActual, mesa, baraja);
                if (!completadoRobo) {
                    System.out.println("Se termina el juego por robo de cartas");
                    break;
                }

                // Completar bandadas
                do {
                    List<Carta.AVE> bandadasDisponibles = jugadorActual.especiesDisponiblesMano(true);
                    if (!bandadasDisponibles.isEmpty()) {
                        decision = leeDecision("Quieres completar bandada: ");
                        if (decision) {
                            completarBandada(jugadorActual, bandadasDisponibles, montonDescartes);
                            // Para que no pueda completar mas veces
                            decision = false;
                        }
                    } else {
                        decision = false;
                        System.out.println("No hay bandadas para completar");
                    }
                } while (decision);

                // Miramos si el jugador gano
                hayGanador = comprobarVictoria(jugadorActual);

                if (!hayGanador) {

                    boolean completadaMano = true;
                    boolean completadaFilas;

                    // Rellenamos la mano del jugador si no tiene cartas
                    if (jugadorActual.numCartasMano() == 0) {
                        completadaMano = rellenarMano(jugadorActual, baraja);
                    }

                    // Rellenamos las filas si no tienen 2 especies distintas
                    completadaFilas = rellenarFilas(mesa, baraja, false);

                    if (!completadaMano || !completadaFilas) {
                        System.out.println("Se termina el juego por tareas");
                        completadasTareas = false;
                    }

                }

            } catch (Exception e) {

                System.err.println("Error en el turno del jugador: " + jugadorActual);
                System.err.println("Tengase en cuenta que puede haber fallos de aqui en adelante");
                System.err.println(e.getStackTrace()[0]);

            }

        }

        if (hayGanador) {
            System.out.println("Ha ganado el jugador: " + jugadorActual);
        } else {
            List<Jugador> ganadores = revisarGanador(jugadores);
            if (ganadores.size() == 1) {
                System.out.println("Ha ganado el jugador: " + ganadores.get(0));
            } else {
                StringBuilder builder = new StringBuilder();
                builder.append("Empate entre: ");
                for (Jugador ganador : ganadores) {
                    builder.append(ganador).append(", ");
                }
                builder.deleteCharAt(builder.toString().length() - 1);
                System.out.println(builder.toString());
            }
        }

    }

    public static List<Jugador> revisarGanador(Queue<Jugador> jugadores) {
        List<Jugador> ganadores = new ArrayList<>();
        int max = -1;
        for (Jugador jugador : jugadores) {
            if (jugador.numEspeciesDistintasZonaJuego() > max) {
                ganadores.clear();
                ganadores.add(jugador);
                max = jugador.numEspeciesDistintasZonaJuego();
            } else if (jugador.numEspeciesDistintasZonaJuego() == max) {
                ganadores.add(jugador);
            }
        }
        return ganadores;
    }

    public static boolean rellenarFilas(Mesa mesa, Baraja baraja, boolean esInicio) {
        return mesa.rellenarFilas(baraja, esInicio);
    }

    public static boolean rellenarMano(Jugador jugador, Baraja baraja) {
        return jugador.rellenarMano(baraja);
    }

    public static boolean comprobarVictoria(Jugador jugador) {
        return jugador.numEspeciesDistintasZonaJuego() == NUM_ESPECIES_VICTORIA;
    }

    public static void completarBandada(Jugador jugador, List<Carta.AVE> bandadasDisponibles, MontonDescartes montonDescartes) throws Exception {

        jugador.mostrarMano();
        jugador.mostrarZonaJuego();

        if (!bandadasDisponibles.isEmpty()) {

            // Seleccionamos la especie que quiere completar
            Carta.AVE especie = leeEspecie("Que bandada quieres completar: ", bandadasDisponibles);

            Stack<Carta> pilaCartas = jugador.eliminarCartasMano(especie);

            jugador.anadirCartasZonaJuego(pilaCartas.pop());

            for (Carta carta : pilaCartas) {
                montonDescartes.insertar(carta);
            }

        }

        jugador.mostrarMano();
        jugador.mostrarZonaJuego();

    }

    public static boolean jugarCartas(Jugador jugador, Mesa mesa, Baraja baraja) throws Exception {

        boolean jugadaTerminada = true;

        jugador.mostrarMano();
        jugador.mostrarZonaJuego();

        // Jugar cartas
        Carta.AVE cartaBajar = leeEspecie("Que cartas quieres bajar: ", jugador.especiesDisponiblesMano(false));

        // Se eliminan las cartas de esa misma especie de la mano
        Stack<Carta> cartasMano = jugador.eliminarCartasMano(cartaBajar);

        // Posiciones para colocar las cartas
        char lado = leeLado("Introduce el lado para bajar " + cartaBajar + ": ");
        int fila = leeEntero("Introduce la fila para bajar " + cartaBajar + ": ", true, 0, NUM_FILAS - 1);

        // Comprobamos si rodeamos cartas
        List<Carta> eliminadas = mesa.eliminarRodeadas(fila, cartaBajar, lado);
        System.out.println("Rodeaste: " + eliminadas.size());

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

        // Se han eliminado cartas se agregan a la mano
        if (!eliminadas.isEmpty()) {

            for (Carta carta : eliminadas) {

                jugador.anadirCartaMano(carta);

            }

        } else { // No hemos rodeado, preguntamos si quiere coger cartas

            if (leeDecision("Quieres robar cartas: ")) {

                // TODO Seria necesario comprobar que roba todas las cartas necesarias
                for (int i = 0; i < NUM_CARTAS_ROBAR; i++) {

                    if (baraja.esVacio()) {
                        jugadaTerminada = false;
                        break;
                    }

                    jugador.anadirCartaMano(baraja.suprimir());

                }

            }

        }

        jugador.mostrarMano();
        jugador.mostrarZonaJuego();

        return jugadaTerminada;

    }

    public static Baraja inicializarBaraja() {

        Baraja baraja = new Baraja();

        baraja.barajar();

        return baraja;

    }

    public static MontonDescartes inicializarMontonDescartes() {
        MontonDescartes montonDescartes = new MontonDescartes();
        return montonDescartes;
    }

    public static Mesa inicializarMesa(Baraja baraja) {
        Mesa mesa = new Mesa(NUM_FILAS);
        mesa.rellenarFilas(baraja, true);
        return mesa;
    }

    public static Jugador inicializarJugador(Baraja baraja) {

        String nombre = leeCadena("Introduce el nombre del jugador: ", false);
        Jugador nuevoJugador = new Jugador(nombre);

        nuevoJugador.rellenarMano(baraja);
        nuevoJugador.anadirCartasZonaJuego(baraja.suprimir());

        return nuevoJugador;

    }

    public static Queue<Jugador> inicializarJugadores(int numJugadores, Baraja baraja) {

        List<Jugador> jugadores = new ArrayList<>(numJugadores);

        // Metemos los jugadores en la lista
        for (int i = 0; i < numJugadores; i++) {
            jugadores.add(inicializarJugador(baraja));
        }

        // Desordenamos la lista
        for (int i = 0; i < jugadores.size(); i++) {
            int x = (int) (Math.random() * jugadores.size());
            Jugador tempEle = jugadores.remove(x);
            jugadores.add(tempEle);
        }

        Queue<Jugador> cola = new ArrayDeque<>();
        // Metemos los jugadores en una cola
        for (Jugador jugador : jugadores) {
            cola.add(jugador);
        }

        return cola;

    }

}
