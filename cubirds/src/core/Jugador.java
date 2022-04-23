package core;

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
        mano.anadirCarta(carta);
    }

    public void eliminarCartasMano(Carta carta) {
        mano.eliminarCarta(carta);
    }

    public void colocarCartaMesa(Carta carta) {
        zonaJuego.colocarCarta(carta);
    }

    public void colocarCartasZonaJuego(Carta carta) {
        zonaJuego.colocarCarta(carta);
    }

    public int numCartasMano() {
        return mano.numCartas();
    }

    public int numCartasZonaJuego() {
        return zonaJuego.numCartas();
    }

    public int numEspeciesDistintasZonaJuego() {
        return zonaJuego.numEspeciesDistintas();
    }

    public void mostrarMano() {
        mano.mostrar();
    }

    public void mostrarZonaJuego() {
        zonaJuego.mostrar();
    }

}
