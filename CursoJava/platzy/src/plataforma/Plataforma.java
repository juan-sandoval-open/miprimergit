package plataforma;

import contenido.Contenido;
import contenido.Genero;
import contenido.Idioma;
import contenido.ResumenContenido;
import exception.PeliculaExistenteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.FileUtils;

public class Plataforma {
    private String nombre;
    private List<Contenido> contenido;
    private Map<Contenido, Integer> visualizaciones;

    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();  // ✅ Inicializa la lista
    }

    public void agregar(Contenido elemento){
        Contenido peliculaEncontrada = this.buscar(elemento.getTitulo());

        if (peliculaEncontrada != null) {
            throw new PeliculaExistenteException(elemento.getTitulo());
        }
        
        FileUtils.escribirContenido(elemento);
        this.contenido.add(elemento);
    }

    public void reproducir(Contenido contenido){
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido " + conteoActual + " vez(ces).");

        this.contarVisualizaciones(contenido);
        contenido.reproducir();
    }

    private void contarVisualizaciones(Contenido contenido){
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual +1);
    }

    public List<String> mostrarTitulos(){
        return contenido.stream()
                .map(Contenido::getTitulo)
                .toList();
    }

    public List<ResumenContenido> getResumenes(){
        return contenido.stream()
                .map(c-> new ResumenContenido(c.getTitulo(), c.getDuracion(), c.getGenero()))
                .toList();
    }


    public void eliminar(Contenido elemento) {
        this.contenido.remove(elemento);
    }

    public Contenido buscar(String titulo) {
        return contenido.stream()
            .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElse(null);

    }

    public List<Contenido> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public List<Contenido> buscarPorIdioma(Idioma idioma) {
        return contenido.stream()
                .filter(contenido -> contenido.getLenguaje().equals(idioma))
                .toList();
    }

    public int getDuracionTotal(){
        return contenido.stream()
                        .mapToInt(Contenido::getDuracion)
                        .sum();
    }

    public List<Contenido> getPopulares(int cantidad){
        return contenido.stream()
                        .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed())
                        .limit(cantidad)
                        .toList();
    }

    public List<Contenido> getCalificacion(double valor){
        return contenido.stream()
                        .filter(pelicula -> pelicula.getCalificacion() >= valor)
                        .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed())
                        .toList();
    }


    

    //Getters
    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }

}
