import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import contenido.Pelicula;
import plataforma.Usuario;

public class Main {
    public static void main(String[] args) {
        System.out.println("Platzi play");

        Pelicula pelicula = new Pelicula();
        pelicula.titulo = "El Origen";
        pelicula.fechaEstreno = LocalDate.of(2024, 11, 5);
        pelicula.genero = "Fantasía";
        pelicula.calificar(4);
        pelicula.duracion = 120;
        
        long duracionLong = pelicula.duracion;
        System.out.println(duracionLong);

        //System.out.println(pelicula.obtenerFichaTecnica());

        Usuario usuario = new Usuario();
        usuario.nombre = "Juan";
        usuario.fechaRegistro =  LocalDateTime.of(2014, 11, 30, 17, 52);

        
        //usuario.ver(pelicula);



        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Cuál es tu nombre?");
        String nombre = scanner.nextLine();

        System.out.println("Hola " + nombre + ", estás en platzi");

        System.out.println(nombre + "Cuántos años tienes?");
        int edad = scanner.nextInt();

        System.out.println(nombre + " puedes ver contenido +" + edad);*/


        
    }
}
