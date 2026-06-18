# 🔴 Errores Comunes con el Paquete Exception

## Problema 1: Falta el Constructor con Mensaje ❌

**Error típico:**
```
The constructor PeliculaExistenteException() is undefined
```

**Solución:**
Tu excepción necesita constructores que acepten mensajes:

```java
package exception;

public class PeliculaExistenteException extends RuntimeException {
    
    // Constructor vacío
    public PeliculaExistenteException() {
        super();
    }
    
    // Constructor con mensaje
    public PeliculaExistenteException(String mensaje) {
        super(mensaje);
    }
    
    // Constructor con mensaje y causa
    public PeliculaExistenteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
```

---

## Problema 2: No se Importa la Excepción ❌

**Error típico:**
```
PeliculaExistenteException cannot be resolved to a type
```

**Solución en Main.java:**
```java
import exception.PeliculaExistenteException;  // ✅ Agregar esto
import contenido.Genero;
import contenido.Idioma;
// ... resto de imports
```

---

## Problema 3: Falta Usar la Excepción en Plataforma ❌

**Solución en Plataforma.java:**

```java
package plataforma;

import exception.PeliculaExistenteException;  // ✅ Importar
import contenido.Pelicula;
import contenido.Genero;
import contenido.Idioma;
// ... resto de imports

public class Plataforma {
    // ... código existente ...
    
    public void agregar(Pelicula elemento) throws PeliculaExistenteException {
        // Verificar si la película ya existe
        boolean peliculaExiste = contenido.stream()
                .anyMatch(p -> p.getTitulo().equalsIgnoreCase(elemento.getTitulo()));
        
        if (peliculaExiste) {
            throw new PeliculaExistenteException(
                "La película '" + elemento.getTitulo() + "' ya existe en " + this.nombre
            );
        }
        
        this.contenido.add(elemento);
        System.out.println("✅ Película '" + elemento.getTitulo() + "' agregada exitosamente");
    }
}
```

---

## Problema 4: No Manejar la Excepción en Main ❌

**Solución en Main.java:**

```java
case AGREGAR:
    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
    Genero genero = ScannerUtils.capturarGenero("Nombre del genero");
    int duracion = ScannerUtils.capturarNumero("Duración");
    double calificacion = ScannerUtils.capturarDecimal("Calificación");
    Idioma idioma = ScannerUtils.capturarIdioma("Idioma original: ");
    
    try {
        Pelicula pelicula = new Pelicula(nombre, duracion, genero, idioma, calificacion);
        plataforma.agregar(pelicula);
    } catch (PeliculaExistenteException e) {
        System.out.println("❌ Error: " + e.getMessage());
    }
    break;
```

---

## 📋 Checklist para Resolver el Error:

- [ ] ¿El archivo `exception/PeliculaExistenteException.java` existe?
- [ ] ¿La excepción tiene constructores (vacío y con mensaje)?
- [ ] ¿Se importa en `Main.java` con `import exception.PeliculaExistenteException;`?
- [ ] ¿Se importa en `Plataforma.java` si se usa allí?
- [ ] ¿Se lanza la excepción en el método `agregar()`?
- [ ] ¿Se captura con `try-catch` en `Main.java`?

---

## ✅ Estructura Completa Correcta:

### 1. exception/PeliculaExistenteException.java
```java
package exception;

public class PeliculaExistenteException extends RuntimeException {
    
    public PeliculaExistenteException() {
        super();
    }
    
    public PeliculaExistenteException(String mensaje) {
        super(mensaje);
    }
    
    public PeliculaExistenteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
```

### 2. plataforma/Plataforma.java (fragmento)
```java
package plataforma;

import exception.PeliculaExistenteException;
// ... otros imports ...

public class Plataforma {
    // ...
    
    public void agregar(Pelicula elemento) throws PeliculaExistenteException {
        boolean existe = contenido.stream()
                .anyMatch(p -> p.getTitulo().equalsIgnoreCase(elemento.getTitulo()));
        
        if (existe) {
            throw new PeliculaExistenteException(
                "La película '" + elemento.getTitulo() + "' ya existe"
            );
        }
        
        contenido.add(elemento);
    }
}
```

### 3. Main.java (fragmento)
```java
import exception.PeliculaExistenteException;
// ... otros imports ...

// En el switch case AGREGAR:
try {
    Pelicula pelicula = new Pelicula(nombre, duracion, genero, idioma, calificacion);
    plataforma.agregar(pelicula);
} catch (PeliculaExistenteException e) {
    System.out.println("❌ " + e.getMessage());
}
```

---

## 🎯 ¿Cuál es el error específico que ves?

Comparte el mensaje de error exacto y te lo resuelvo inmediatamente.
