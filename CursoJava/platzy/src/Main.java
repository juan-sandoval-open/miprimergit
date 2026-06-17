
import contenido.Genero;
import contenido.Idioma;
import contenido.Pelicula;
import exception.PeliculaExistenteException;
import java.util.List;
import plataforma.Plataforma;
import util.ScannerUtils;

public class Main {
    public static final String VERSION = "1.0.0";
    public static final String NOMBREPLATAFORMA = "Platzy Play";

    public static final int AGREGAR = 1;
    public static final int MOSTRAR = 2;
    public static final int BUSCAR = 3;
    public static final int ELIMINAR = 4;
    public static final int SALIR = 5;
    public static final int BUSCAR_GENERO = 6;
    public static final int VER_POPULARES = 7;
    public static final int VER_CALIFICACIONES_MAYORES = 8;
    public static final int BUSCAR_IDIOMA = 9;


    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBREPLATAFORMA);
        System.out.println(NOMBREPLATAFORMA + " v" + VERSION);

        cargarPeliculas(plataforma);

        System.out.println("Más de " + plataforma.getDuracionTotal() + "minutos de contenido\n");

        while(true){
            int opcionElegida = ScannerUtils.capturarNumero("""
                Ingrese una se las siguientes opciones: 
                    1. Agregar contenido
                    2. Mostrar todo
                    3. Buscar por titulo
                    4. Eliminar
                    5. Salir
                    6. Buscar por género
                    7. Ver populares
                    8. Ver calificaciones mayores a
                    9. Buscar por idioma original
            """);
            System.out.println("Opción elegida: " + opcionElegida);

            switch (opcionElegida) {
                case AGREGAR:
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Nombre del genero");
                    int duracion = ScannerUtils.capturarNumero("Duración");
                    double calificacion = ScannerUtils.capturarDecimal("Calificación");
                    Idioma idioma = ScannerUtils.capturarIdioma("Idioma original: ");
                    
                    try {
                        plataforma.agregar(new Pelicula(nombre, duracion, genero, idioma, calificacion));
                        System.out.println("✅ Película '" + nombre + "' agregada exitosamente");
                    } catch (PeliculaExistenteException e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                    
                    break;

                case MOSTRAR:
                    List<String> titulos = plataforma.mostrarTitulos();
                    titulos.forEach(impTitulos -> System.out.println(impTitulos));
                    break;
            
                case BUSCAR:
                    String nombreBuscado = ScannerUtils.capturarTexto("Nombre del contenido a buscar");
                    Pelicula contenido = plataforma.buscar(nombreBuscado);

                    if (contenido != null) {
                        System.out.println(contenido.obtenerFichaTecnica());
                    } else {
                        System.out.println(nombreBuscado + " no existe dentro de " + plataforma.getNombre());
                    }
                    break;

                case BUSCAR_GENERO:
                    Genero nombreGenero = ScannerUtils.capturarGenero("Nombre del genero");
                    List<Pelicula> listaPeliculas = plataforma.buscarPorGenero(nombreGenero);

                    if (listaPeliculas != null) {
                        System.out.println(listaPeliculas.size() + " peliculas encontradas del genero " + nombreGenero + "\n");
                        listaPeliculas.forEach(contenidoGenero -> System.out.println(contenidoGenero.obtenerFichaTecnica() + "\n"));
                    } else {
                        System.out.println(nombreGenero + " no existe dentro de " + plataforma.getNombre());
                    }
                
                    break;
                
                case BUSCAR_IDIOMA:
                    Idioma nombreIdioma = ScannerUtils.capturarIdioma("Nombre del idioma");
                    List<Pelicula> listaIdiomas = plataforma.buscarPorIdioma(nombreIdioma);

                    if (listaIdiomas != null) {
                        System.out.println(listaIdiomas.size() + " peliculas encontradas del idioma original " + nombreIdioma + "\n");
                        listaIdiomas.forEach(contenidoGenero -> System.out.println(contenidoGenero.obtenerFichaTecnica() + "\n"));
                    } else {
                        System.out.println(nombreIdioma + " no existe dentro de " + plataforma.getNombre());
                    }
                
                    break;    
                case VER_POPULARES:
                    int cantidad = ScannerUtils.capturarNumero("Número de peliculas populares: ");
                    List<Pelicula> contenidoPopulares = plataforma.getPopulares(cantidad);
                    contenidoPopulares.forEach(impPopulares -> System.out.println(impPopulares.obtenerFichaTecnica() + "\n"));

                    break;
                
                case VER_CALIFICACIONES_MAYORES:
                    double calificacionRequerida = ScannerUtils.capturarDecimal("Digita el valor de calficación para visualizar las películas con calificación mayora a este valor\n");
                    List<Pelicula> contenidoCalificacion = plataforma.getCalificacion(calificacionRequerida);
                    contenidoCalificacion.forEach(impPopulares -> System.out.println(impPopulares.obtenerFichaTecnica() + "\n"));
                    break;

                case ELIMINAR:
                    String nombreAEliminar = ScannerUtils.capturarTexto("Nombre del contenido a eliminar");
                    Pelicula contenido1 = plataforma.buscar(nombreAEliminar);

                    if (contenido1 != null) {
                        plataforma.eliminar(contenido1);
                        System.out.println(nombreAEliminar + " eliminado! ❌");
                    } else {
                        System.out.println(nombreAEliminar + " no existe dentro de " + plataforma.getNombre());
                    }
                
                    break;

                case SALIR:
                    System.out.println("Opción correcta!");
                    System.exit(0);
                default:
                    break;
            }       

        }
    }    
    private static void cargarPeliculas(Plataforma plataforma) {
        plataforma.agregar(new Pelicula("Shrek", 90, Genero.ANIMADA, Idioma.INGLES));
        plataforma.agregar(new Pelicula("Inception", 148, Genero.CIENCIA_FICCION, Idioma.INGLES));
        plataforma.agregar(new Pelicula("Titanic", 195, Genero.DRAMA, Idioma.INGLES, 4.6));
        plataforma.agregar(new Pelicula("John Wick", 101, Genero.ACCION, Idioma.INGLES));
        plataforma.agregar(new Pelicula("El Conjuro", 112, Genero.TERROR, Idioma.INGLES, 3.0));
        plataforma.agregar(new Pelicula("Coco", 105, Genero.ANIMADA, Idioma.INGLES, 4.7));
        plataforma.agregar(new Pelicula("Interstellar", 169, Genero.CIENCIA_FICCION, Idioma.INGLES, 5));
        plataforma.agregar(new Pelicula("Joker", 122, Genero.DRAMA, Idioma.INGLES));
        plataforma.agregar(new Pelicula("Toy Story", 81, Genero.ANIMADA, Idioma.INGLES, 4.5));
        plataforma.agregar(new Pelicula("Avengers: Endgame", 181, Genero.ACCION, Idioma.INGLES, 3.9));
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
    
}
