package util;

import contenido.Contenido;
import contenido.Genero;
import contenido.Idioma;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static final String NOMBRE_ARCHIVO = "CursoJava/platzy/src/contenido1.txt";
    public static final String SEPARADOR = "|";

    public static void escribirContenido(Contenido contenido) {
        String linea = String.join(SEPARADOR,
                contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()),
                contenido.getGenero().name(),
                contenido.getLenguaje().name(),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno() != null ? contenido.getFechaEstreno().toString() : ""
        );

        try {
            Files.writeString(Paths.get(NOMBRE_ARCHIVO),
                    linea + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error escribiendo el archivo. " + e.getMessage());
        }

    }

    public static List<Contenido> leerContenido() {
        List<Contenido> contenidoDesdeArchivo = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));

            lineas.forEach(linea -> {
                String[] datos = linea.split("\\" + SEPARADOR);

                if (datos.length == 6) {
                    String titulo = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    Genero genero = Genero.valueOf(datos[2].toUpperCase());
                    Idioma idioma = Idioma.valueOf(datos[3].toUpperCase());
                    double calificacion = datos[4].isBlank() ? 0 : Double.parseDouble(datos[4]);
                    LocalDate fechaEstreno = LocalDate.parse(datos[5]);

                    Contenido pelicula = new Contenido(titulo, duracion, genero, idioma, calificacion);
                    pelicula.setFechaEstreno(fechaEstreno);

                    contenidoDesdeArchivo.add(pelicula);
                }
            });
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo. " + e.getMessage());
        }

        return contenidoDesdeArchivo;
    }
}
