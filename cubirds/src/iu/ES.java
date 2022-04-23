package iu;

import core.Carta;
import java.util.Scanner;
import lista.Lista;

public class ES {

    public static final Scanner teclado = new Scanner(System.in);

    public static Carta.AVE leeEspecie(String mensaje, Lista<Carta.AVE> especiesDisponibles) {

        Carta.AVE especie = null;
        boolean esValido = false;

        do {
            try {

                int c = 0;
                for (Carta.AVE especieDisponible : especiesDisponibles) {
                    System.out.println(c + " - " + especieDisponible);
                    c++;
                }

                int pos = leeEntero(mensaje, true, 0, especiesDisponibles.tamaño() - 1);

                c = 0;
                for (Carta.AVE especieDisponible : especiesDisponibles) {
                    if (c == pos) {
                        especie = especieDisponible;
                    }
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
        boolean esValido = false;

        do {

            try {

                texto = leeCadena(mensaje, false).toLowerCase().charAt(0);

                if (texto == 's') {

                    decision = true;

                } else if (texto == 'n') {

                    decision = false;

                } else {

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
        boolean esValido = false;

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

        boolean esValido = false;
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
