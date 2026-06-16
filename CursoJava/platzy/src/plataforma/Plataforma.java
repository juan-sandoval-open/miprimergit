package plataforma;

import java.util.List;
import java.util.ArrayList;

import contenido.Pelicula;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    public Plataforma(String nombre){
        this.nombre = nombre;
        this.contenido = new ArrayList<>();  // ✅ Inicializa la lista
    }

    public void agregar(Pelicula elemento){
        this.contenido.add(elemento);
    }

    public void mostrarTitulos(){
       for (Pelicula pelicula : contenido) {
            System.out.println(pelicula.getDuracion());
       }
    }

    public void eliminar(Pelicula elemento){
        this.contenido.remove(elemento);

    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }

}
