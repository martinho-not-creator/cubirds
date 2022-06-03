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

    public boolean rellenarMano(Baraja baraja) {
        for (int i = mano.getNumElementos(); i < NUM_CARTAS_MANO_JUGADOR; i++) {
            if (baraja.esVacio()) {
                return false;
            }
            mano.insertar(baraja.suprimir());
        }
        return true;
    }

    public void anadirCartaMano(Carta carta) {
        mano.insertar(carta);
    }

    public Stack<Carta> eliminarCartasMano(Carta.AVE especie) throws Exception {
        return mano.eliminarElementos(especie);
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

    public int existeEspecieMano(Carta.AVE especie) {
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

    private class ZonaJuego {

        private List<Stack<Carta>> zonaJuego;

        private ZonaJuego() {
            zonaJuego = new ArrayList<Stack<Carta>>();
        }

        private int existePilaAve(Carta.AVE especie) {
            for (int i = 0; i < zonaJuego.size(); i++) {
                Stack<Carta> pila = zonaJuego.get(i);
                if (!pila.isEmpty() && pila.peek().getNombre() == especie) {
                    return i;
                }
            }
            return -1;
        }

        private void insertar(Carta carta) {
            int pos = existePilaAve(carta.getNombre());
            if (pos != -1) {
                zonaJuego.get(pos).push(carta);
            } else {
                Stack<Carta> nuevaPila = new Stack<>();
                nuevaPila.push(carta);
                zonaJuego.add(nuevaPila);
            }
        }

        private int getNumEspecies() {
            int numEspecies = 0;
            for (Stack<Carta> pila : zonaJuego) {
                if (!pila.isEmpty()) {
                    numEspecies++;
                }
            }
            return numEspecies;
        }

        private int getNumCartas() {
            int total = 0;
            for (Stack<Carta> pila : zonaJuego) {
                total += pila.size();
            }
            return total;
        }

        private void pintar() {
            for (Stack<Carta> pila : zonaJuego) {
                System.out.println("Pila de " + pila.peek() + " | " + pila.size());
            }
        }

    }

    private class Mano {

        private List<Stack<Carta>> mano;

        private Mano() {
            mano = new ArrayList<Stack<Carta>>();
        }

        private int existePilaElemento(Carta.AVE especie) {
            for (int i = 0; i < mano.size(); i++) {
                Stack<Carta> pila = mano.get(i);
                if (!pila.empty() && pila.peek().getNombre() == especie) {
                    return i;
                }
            }
            return -1;
        }

        private List<Carta.AVE> especiesDisponibles(boolean minTamBandada) {
            List<Carta.AVE> lista = new ArrayList<>();
            for (Stack<Carta> pila : mano) {
                Carta carta = pila.peek();
                if (minTamBandada && !esSuficiente(carta.getNombre())) {
                    continue;
                }
                lista.add(carta.getNombre());
            }
            return lista;
        }

        private void insertar(Carta carta) {
            int pos = existePilaElemento(carta.getNombre());
            if (pos != -1) {
                mano.get(pos).push(carta);
            } else {
                Stack<Carta> nuevaPila = new Stack<>();
                nuevaPila.push(carta);
                mano.add(nuevaPila);
            }
        }

        private Stack<Carta> eliminarElementos(Carta.AVE especie) throws Exception {
            int pos = existePilaElemento(especie);
            if (pos == -1) {
                throw new Exception("No existe ese tipo en la mano");
            } else {
                return mano.remove(pos);
            }
        }

        private boolean esSuficiente(Carta.AVE especie) {
            int pos = existePilaElemento(especie);
            if (pos != -1) {
                return mano.get(pos).size() >= new Carta(especie).getBandadaPequena();
            }
            return false;
        }

        private int getNumElementos() {
            int contador = 0;
            for (Stack<Carta> pila : mano) {
                contador += pila.size();
            }
            return contador;
        }

        private void pintar() {
            for (Stack<Carta> pila : mano) {
                System.out.println("Pila de " + pila.peek() + " | " + pila.size());
            }
        }

    }

}
