package core;

import lista.Lista;
import pila.Pila;

public class Jugador {

    private String nombre;
    private ZonaJuego zonaJuego;
    private Mano mano;

    public Jugador(String nombre, ZonaJuego zonaJuego, Mano mano) {
        this.nombre = nombre;
        this.zonaJuego = zonaJuego;
        this.mano = mano;
    }

    public void anadirCartaMano(Carta carta) {
        mano.insertar(carta);
    }

    public Pila<Carta> eliminarCartasMano(Carta carta) throws Exception {
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

    public Lista<Carta.AVE> especiesDisponiblesMano() {
        return mano.especiesDisponibles();
    }

    public int numEspeciesDistintasZonaJuego() {
        return zonaJuego.getNumEspecies();
    }

    public void mostrarMano() {
        mano.pintar();
    }

    public void mostrarZonaJuego() {
        zonaJuego.pintar();
    }

}
