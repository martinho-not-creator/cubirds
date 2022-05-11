package iu;

import core.Carta;
import java.util.List;
import java.util.Scanner;

public class ES {

    public static final Scanner teclado = new Scanner(System.in);

    public static String centrarString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public static Carta.AVE leeEspecie(String mensaje, List<Carta.AVE> especiesDisponibles) {

        Carta.AVE especie = null;
        boolean esValido;

        do {
            try {

                System.out.println("Dispones de estas opciones: ");

                int c = 0;
                for (Carta.AVE especieDisponible : especiesDisponibles) {
                    System.out.println(c + " - " + especieDisponible);
                    c++;
                }

                int pos = leeEntero(mensaje, true, 0, especiesDisponibles.size() - 1);

                c = 0;
                for (Carta.AVE especieDisponible : especiesDisponibles) {
                    if (c == pos) {
                        especie = especieDisponible;
                    }
                    c++;
                }

                esValido = true;

            } catch (Exception e) {

                esValido = false;
                System.err.println(e.getMessage());

            }

        } while (!esValido);

        return especie;

    }

    public static boolean leeDecision(String mensaje) {

        char texto;
        boolean decision = false;
        boolean esValido;

        do {

            try {

                texto = leeCadena(mensaje, false).toLowerCase().charAt(0);

                switch (texto) {
                    case 's':
                        decision = true;
                        break;
                    case 'n':
                        decision = false;
                        break;
                    default:
                        throw new Exception("La cadena introducida no es correcta");
                }

                esValido = true;

            } catch (Exception e) {

                esValido = false;
                System.err.println(e.getMessage());

            }

        } while (!esValido);

        return decision;

    }

    public static String leeCadena(String mensaje, boolean permiteVacia) {

        String leer = "";
        boolean esValido = false;

        do {

            System.out.print(mensaje);

            try {

                leer = teclado.nextLine().trim();

                if (!permiteVacia && leer.length() == 0) {
                    throw new Exception("La cadena introducida no puede estar vacía. Por favor, introdúcela de nuevo.");
                }

                esValido = true;

            } catch (Exception exc) {

                esValido = false;
                System.err.println(exc.getMessage());

            }

        } while (!esValido);

        return leer;

    }

    public static char leeLado(String mensaje) {

        char leer = ' ';
        boolean esValido;

        do {

            try {

                leer = leeCadena(mensaje, false).toLowerCase().charAt(0);

                if (leer != 'd' && leer != 'i') {

                    throw new Exception("La cadena introducida no es un lado válido. Por favor, introdúcela de nuevo.");

                }

                esValido = true;

            } catch (Exception exc) {

                esValido = false;
                System.err.println(exc.getMessage());

            }

        } while (!esValido);

        return leer;

    }

    public static int leeEntero(String msg, boolean rango, int min, int max) {

        boolean esValido;
        int toret = 0;

        do {

            System.out.print(msg);

            try {

                toret = Integer.parseInt(teclado.nextLine());

                if (rango) {

                    if (toret < min || toret > max) {

                        throw new Exception("El número introducido no está en el rango permitido. Por favor, introdúcelo de nuevo.");

                    }

                }

                esValido = true;

            } catch (NumberFormatException e) {

                esValido = false;
                System.err.println("La cadena introducida no se puede convertir a número entero. Por favor, introdúcela de nuevo.");

            } catch (Exception e) {

                esValido = false;
                System.err.println(e.getMessage());

            }

        } while (!esValido);

        return toret;

    }

}
