package plataforma;

import contenido.Genero;
import contenido.Idioma;
import contenido.Pelicula;
import exception.PeliculaExistenteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>();  // ✅ Inicializa la lista
    }

    public void agregar(Pelicula elemento){
        Pelicula peliculaEncontrada = this.buscar(elemento.getTitulo());

        if (peliculaEncontrada != null) {
            throw new PeliculaExistenteException(elemento.getTitulo());
        }

        this.contenido.add(elemento);
    }

    public List<String> mostrarTitulos(){
        return contenido.stream()
                .map(Pelicula::getTitulo)
                .toList();
    }

    public void eliminar(Pelicula elemento) {
        this.contenido.remove(elemento);
    }

    public Pelicula buscar(String titulo) {
        return contenido.stream()
            .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElse(null);

    }

    public List<Pelicula> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public List<Pelicula> buscarPorIdioma(Idioma idioma) {
        return contenido.stream()
                .filter(contenido -> contenido.getLenguaje().equals(idioma))
                .toList();
    }

    public int getDuracionTotal(){
        return contenido.stream()
                        .mapToInt(Pelicula::getDuracion)
                        .sum();
    }

    public List<Pelicula> getPopulares(int cantidad){
        return contenido.stream()
                        .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
                        .limit(cantidad)
                        .toList();
    }

    public List<Pelicula> getCalificacion(double valor){
        return contenido.stream()
                        .filter(pelicula -> pelicula.getCalificacion() >= valor)
                        .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
                        .toList();
    }


    

    //Getters
    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }

}
