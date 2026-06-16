

import contenido.Pelicula;

public class MainStackHeap {
    public static void main(String[] args){
        Pelicula reyLeon = new Pelicula("Rey León", 135, "Romance");
        Pelicula harry = new Pelicula("Harry", 135, "Intriga");

        System.out.println(reyLeon.titulo);
        System.out.println(harry.titulo);
    }
}
