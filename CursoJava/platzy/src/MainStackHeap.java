

import contenido.Contenido;

public class MainStackHeap {
    public static void main(String[] args){
        Contenido reyLeon = new Contenido("Rey León", 135, "Romance");
        Contenido harry = new Contenido("Harry", 135, "Intriga");

        System.out.println(reyLeon.titulo);
        System.out.println(harry.titulo);
    }
}
