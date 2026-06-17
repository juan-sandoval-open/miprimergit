package util;

import contenido.Genero;
import contenido.Idioma;
import java.util.Scanner;


public class ScannerUtils {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static String capturarTexto(String mensaje) {
        System.out.println(mensaje + ": ");
        return SCANNER.nextLine();
    }

    public static int capturarNumero(String mensaje) {
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Dato no aceptado. " + mensaje + ": ");
            SCANNER.next();
        }

        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double capturarDecimal(String mensaje) {
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextDouble()) { // Esta negación valida si el dato ingresado es double
            System.out.println("Dato no aceptado. " + mensaje + ": ");
            SCANNER.next();// Esta línea busca ir a validar el siguiente valor que ingrese el usuario
            // Este proceso es el mismo para las otras capturadoras
        }

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }

    public static Genero capturarGenero(String mensaje) {
        while (true) {
            System.out.println(mensaje + "... Opciones:");
            for (Genero genero : Genero.values()) {
                System.out.println("-" + genero.name());
            }

            System.out.println("Cuál quieres?");
            String entrada = SCANNER.nextLine();

            try {
                return Genero.valueOf(entrada.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Género no aceptado. ");
            }
        }
    }

    public static Idioma capturarIdioma(String mensaje) {
        while (true) {
            System.out.println(mensaje + "... Opciones:");
            for (Idioma idioma : Idioma.values()) {
                System.out.println("-" + idioma.name());
            }

            System.out.println("Cuál quieres?");
            String entrada = SCANNER.nextLine();

            try {
                return Idioma.valueOf(entrada.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Idioma no aceptado. ");
            }
        }
    }
    

}
