package contenido;

public class Pelicula extends Contenido {
    public Pelicula(String titulo, int duracion, Genero genero, Idioma lenguaje) {
        super(titulo, duracion, genero, lenguaje);
    }

    public Pelicula(String titulo, int duracion, Genero genero, Idioma lenguaje, double calificacion) {
        super(titulo, duracion, genero, lenguaje, calificacion);
    }
}
