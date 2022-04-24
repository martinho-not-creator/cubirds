package core;

import java.util.List;
import java.util.Stack;

public class Jugador {

    private String nombre;
    private ZonaJuego zonaJuego;
    private Mano mano;

    public Jugador(String nombre, ZonaJuego zonaJuego, Mano mano) {
        this.nombre = nombre;
        this.zonaJuego = zonaJuego;
        this.mano = mano;
    }

    public String getNombre() {
        return nombre;
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

}
