package core;

import static iu.Juego.NUM_CARTAS_MANO_JUGADOR;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Jugador {

    private String nombre;
    private ZonaJuego zonaJuego;
    private Mano mano;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.zonaJuego = new ZonaJuego();
        this.mano = new Mano();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean rellenarMano(Baraja<Carta> baraja) {
        return mano.rellenar(baraja);
    }

    public void anadirCartaMano(Carta carta) {
        mano.insertar(carta);
    }

    public Stack<Carta> eliminarCartasMano(Carta carta) throws Exception {
        return mano.eliminarElementos(carta);
    }

    public void anadirCartasZonaJuego(Carta carta) {
        zonaJuego.insertar(carta);
    }

    public int numCartasMano() {
        return mano.getNumElementos();
    }

    public int numCartasZonaJuego() {
        return zonaJuego.getNumCartas();
    }

    public int existeEspecieMano(Carta especie) {
        return mano.existePilaElemento(especie);
    }

    public List<Carta.AVE> especiesDisponiblesMano(boolean minTamBandada) {
        return mano.especiesDisponibles(minTamBandada);
    }

    public int numEspeciesDistintasZonaJuego() {
        return zonaJuego.getNumEspecies();
    }

    public void mostrarMano() {
        System.out.println("Mano de " + getNombre());
        mano.pintar();
    }

    public void mostrarZonaJuego() {
        System.out.println("Zona juego de " + getNombre());
        zonaJuego.pintar();
    }

    @Override
    public String toString() {
        return getNombre();
    }

    private class ZonaJuego<E> {

        private List<Stack<E>> zona;

        private ZonaJuego() {
            zona = new ArrayList<Stack<E>>();
        }

        private int existePilaAve(E elemento) {
            for (int i = 0; i < zona.size(); i++) {
                Stack<E> pila = zona.get(i);
                if (!pila.isEmpty() && pila.peek().equals(elemento)) {
                    return i;
                }
            }
            return -1;
        }

        private void insertar(E elemento) {
            int pos = existePilaAve(elemento);
            if (pos != -1) {
                zona.get(pos).push(elemento);
            } else {
                Stack<E> nuevaPila = new Stack<>();
                nuevaPila.push(elemento);
                zona.add(nuevaPila);
            }
        }

        private int getNumEspecies() {
            int numEspecies = 0;
            for (Stack<E> pila : zona) {
                if (!pila.isEmpty()) {
                    numEspecies++;
                }
            }
            return numEspecies;
        }

        private int getNumCartas() {
            int total = 0;
            for (Stack<E> pila : zona) {
                total += pila.size();
            }
            return total;
        }

        private void pintar() {
            for (Stack<E> pila : zona) {
                System.out.println("Pila de " + pila.peek() + " | " + pila.size());
            }
        }

    }

    private class Mano<E> {

        private List<Stack<E>> zona;

        private Mano() {
            zona = new ArrayList<Stack<E>>();
        }

        private boolean rellenar(Baraja<E> baraja) {
            for (int i = getNumElementos(); i < NUM_CARTAS_MANO_JUGADOR; i++) {
                if (baraja.esVacio()) {
                    return false;
                }
                insertar(baraja.suprimir());
            }
            return true;
        }

        private int existePilaElemento(E elemento) {
            for (int i = 0; i < zona.size(); i++) {
                Stack<E> pila = zona.get(i);
                if (!pila.empty() && pila.peek().equals(elemento)) {
                    return i;
                }
            }
            return -1;
        }

        private List<Carta.AVE> especiesDisponibles(boolean minTamBandada) {
            List<Carta.AVE> lista = new ArrayList<>();
            for (Stack<E> pila : zona) {
                Carta carta = (Carta) pila.peek();
                if (minTamBandada && !esSuficiente(carta)) {
                    continue;
                }
                lista.add(carta.getNombre());
            }
            return lista;
        }

        private void insertar(E elemento) {
            int pos = existePilaElemento(elemento);
            if (pos != -1) {
                zona.get(pos).push(elemento);
            } else {
                Stack<E> nuevaPila = new Stack<>();
                nuevaPila.push(elemento);
                zona.add(nuevaPila);
            }
        }

        private Stack<E> eliminarElementos(E elementoTipo) throws Exception {
            int pos = existePilaElemento(elementoTipo);
            if (pos == -1) {
                throw new Exception("No existe ese tipo en la mano");
            } else {
                return zona.remove(pos);
            }
        }

        private boolean esSuficiente(Carta carta) {
            int pos = existePilaElemento((E) carta);
            if (pos != -1) {
                return zona.get(pos).size() >= carta.getBandadaPequena();
            }
            return false;
        }

        private int getNumElementos() {
            int contador = 0;
            for (Stack<E> pila : zona) {
                contador += pila.size();
            }
            return contador;
        }

        private void pintar() {
            for (Stack<E> pila : zona) {
                System.out.println("Pila de " + pila.peek() + " | " + pila.size());
            }
        }

    }

}
