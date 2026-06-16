
import contenido.Pelicula;
import plataforma.Plataforma;
import plataforma.Usuario;
import util.ScannerUtils;

public class Main {
    public static final String VERSION = "1.0.0";
    public static final String NOMBREPLATAFORMA = "Platzy Play";

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBREPLATAFORMA);
        System.out.println(NOMBREPLATAFORMA + " v" + VERSION);

        // 1. Agregar contenido
        // 2. Mostrar todo
        // 3. Buscar por titulo
        // 4. Eliminar
        // 5. Salir

        while(true){
            int opcionElegida = ScannerUtils.capturarNumero("""
                Ingrese una se las siguientes opciones: 
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Eliminar
                    5. Salir
            """);
            System.out.println("Opción elegida: " + opcionElegida);

            if (opcionElegida == 5){
                System.exit(0);
            }
        }

        // String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
        // String genero = ScannerUtils.capturarTexto("Nombre del genero");
        // int duracion = ScannerUtils.capturarNumero("Duración");
        // double calificacion = ScannerUtils.capturarDecimal("Calificación");

        
        // Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion); 
        // Pelicula pelicula2 = new Pelicula("F1", 220, "Acción"); 
        // //pelicula.calificar(calificacion);
        
        // plataforma.agregar(pelicula);
        // plataforma.agregar(pelicula2);
        // System.out.println("Número de elementos en la plataforma: " + plataforma.getContenido().size());
        // plataforma.mostrarTitulos();
        


        // //Casteo
        // /*long duracionLong = pelicula.duracion;
        // int calificacionInt = (int) pelicula.calificacion; //De esta manera se hace el casteo de una variable
        // long numeroDePremios =  (int) Long.parseLong("25798798798798"); // De esta manera se convierte de String a Long, si quisiera que fuera un int tendría que hacer lo del paso de arriba. 
        // */

        // //System.out.println(pelicula.obtenerFichaTecnica());

        // Usuario usuario = new Usuario("Juan", "juan@openintl.com");
        // usuario.ver(pelicula);
        
    }
}
