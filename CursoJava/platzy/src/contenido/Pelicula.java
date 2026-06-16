package contenido;

import java.time.LocalDate;

public class Pelicula {
        private String titulo;
        private String description;
        private int duracion;
        private String genero;
        private LocalDate fechaEstreno;
        private double calificacion;
        private boolean disponible;

        public Pelicula(String titulo, int duracion, String genero){ // Permite generar acciones de la pelicula
            this.titulo = titulo;
            this.duracion = duracion;
            this.genero = genero;
            this.fechaEstreno = LocalDate.now();
            this.disponible = true;
        }
        


        public Pelicula(String titulo, int duracion, String genero, 
                double calificacion) {
            this(titulo, duracion, genero);
            this.calificar(calificacion);
            
        }



        public void reproducir (){
            System.out.print("Reproduciendo "+ titulo);

        }

        public String obtenerFichaTecnica (){
            return titulo + " (" + fechaEstreno.getYear() + ")\n" +
                    "Género: " + genero + "\n" +
                    "Duración: " + duracion + "\n" +
                    "Calificación " + calificacion +  "/5";
            
        }

        public void calificar(double calificacion){
            if(calificacion >= 0 && calificacion <= 5){
                this.calificacion = calificacion; 
            }
            
        }

        public boolean esPopular(){
            return calificacion >= 4;
        }

        //Getters
        public String getTitulo(){
            return titulo;
        }

        public String getDescription() {
            return description;
        }

        public int getDuracion() {
            return duracion;
        }

        public String getGenero() {
            return genero;
        }

        public LocalDate getFechaEstreno() {
            return fechaEstreno;
        }

        public double getCalificacion() {
            return calificacion;
        }

        public boolean isDisponible() {
            return disponible;
        }

        // Setters
        public void setDescription(String description) {
            this.description = description;
        }

        public void setDuracion(int duracion) {
            this.duracion = duracion;
        }

        public void setFechaEstreno(LocalDate fechaEstreno) {
            this.fechaEstreno = fechaEstreno;
        }

        public void setDisponible(boolean disponible) {
            this.disponible = disponible;
        }

        
}
