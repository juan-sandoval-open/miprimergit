package contenido;

public class Documental extends Contenido {
    private String narrador;

    public Documental(String titulo, int duracion, Genero genero, Idioma lenguaje) {
        super(titulo, duracion, genero, lenguaje);
    }

    public Documental(String titulo, int duracion, Genero genero, Idioma lenguaje,  double calificacion, String narrador) {
        super(titulo, duracion, genero, lenguaje, calificacion);
        this.narrador = narrador;
    }

    public String getNarrador() {
        return narrador;
    }
}
