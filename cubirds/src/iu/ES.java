package iu;

import java.util.Scanner;

public class ES {

    public static final Scanner teclado = new Scanner(System.in);

    public static String leeCadena(String mensaje, boolean permiteVacia) {

        String leer;

        do {

            System.out.print(mensaje);

            try {

                leer = teclado.nextLine().trim();

                if (!permiteVacia && leer.length() == 0) {
                    throw new Exception("La cadena introducida no puede estar vacía. Por favor, introdúcela de nuevo.");
                }

            } catch (Exception exc) {

                System.err.println(exc.getMessage());

            }

        } while ((permiteVacia == false) && leer.length() == 0);

        return leer;

    }

    public static String leeLado(String mensaje) {

        String leer;
        boolean esValido = false;

        do {

            try {

                leer = leeCadena(mensaje, false).toLowerCase();

                if (!leer.equals("derecha") && !leer.equals("izquierda") && !leer.equals("d") && !leer.equals("i")) {

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

            } catch (NumberFormatException exc) {

                esValido = false;
                System.err.println("La cadena introducida no se puede convertir a número entero. Por favor, introdúcela de nuevo.");

            } catch (Exception exc) {

                esValido = false;
                System.err.println(exc.getMessage());

            }

        } while (!esValido);

        return toret;

    }


}
