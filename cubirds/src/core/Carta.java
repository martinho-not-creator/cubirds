package core;

public class Carta {

    public static enum AVE {
        CURRUCA, FLAMENCO, PETIRROJO, TUCAN, PATO, URRACA, LECHUZA, GUACAMAYO
    };

    private final int[][] numerosBandadas = new int[][]{
        {6, 9}, {2, 3}, {6, 9}, {3, 4}, {4, 6}, {5, 7}, {3, 4}, {4, 6}
    };

    public static final int[] numeroCartasAve = new int[]{
        20, 7, 20, 10, 13, 17, 10, 13
    };

    private final AVE nombre;
    private final int bandadaPequena;
    private final int bandadaGrande;

    public Carta(AVE tipoAve) {
        this.nombre = tipoAve;
        this.bandadaPequena = numerosBandadas[tipoAve.ordinal()][0];
        this.bandadaGrande = numerosBandadas[tipoAve.ordinal()][1];
    }

    public AVE getNombre() {
        return nombre;
    }

    public int getBandadaPequena() {
        return bandadaPequena;
    }

    public int getBandadaGrande() {
        return bandadaGrande;
    }

    public static Carta generarCartaAleatoria() {
        int n = (int) (Math.random() * AVE.values().length);
        return new Carta(AVE.values()[n]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ")
                .append(nombre)
                .append("(")
                .append(bandadaPequena)
                .append("/")
                .append(bandadaGrande)
                .append(")")
                .append(" ]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {

        Carta objCarta = (Carta) obj;
        return objCarta.nombre == this.getNombre();

    }

}
