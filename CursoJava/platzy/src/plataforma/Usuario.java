package plataforma;


import contenido.Contenido;
import java.time.LocalDateTime;


public class Usuario {
    private String nombre;
    private String email;
    private LocalDateTime fechaRegistro;
    
    

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = LocalDateTime.now();
    }



    public void ver(Contenido pelicula){
        System.out.println(nombre + " está " );
        pelicula.reproducir();

    }

    //Getters
    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }


}
