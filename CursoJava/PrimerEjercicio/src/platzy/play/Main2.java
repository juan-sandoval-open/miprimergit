package platzy.play;

import java.util.Scanner;

// Nunca poner los nombres de los paquetes con puntos y/o mayusculas 

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Platzy play!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Cuál es tu nombre?");
        String name = scanner.nextLine();

        System.out.println("Cuál es tu edad?");
        int edad = scanner.nextInt();

        System.out.println("Hola " + name + " tienes " + edad + " años. Esto es platzi Play!");
    }
}
