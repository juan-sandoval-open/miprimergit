# ✅ Cómo Funciona el Manejo de Excepciones en tu Aplicación

## 📋 Flujo Completo:

### 1️⃣ Excepción Personalizada (`exception/PeliculaExistenteException.java`)
```java
package exception;

public class PeliculaExistenteException extends RuntimeException {
    public PeliculaExistenteException(String titulo) {
        super("La pelicula " + titulo + " ya existe.");
    }
}
```
- Define una excepción personalizada que extiende `RuntimeException`
- Acepta el título de la película como parámetro
- Genera un mensaje descriptivo

---

### 2️⃣ Verificación en Plataforma (`plataforma/Plataforma.java`)
```java
public void agregar(Pelicula elemento){
    Pelicula contenido = this.buscar(elemento.getTitulo());

    if (contenido != null) {
        // Si la película ya existe, lanza la excepción
        throw new PeliculaExistenteException(elemento.getTitulo());
    }

    // Si no existe, la agrega
    this.contenido.add(elemento);
}
```

**Flujo:**
1. Llama a `buscar()` con el título de la película
2. Si `buscar()` retorna algo (película encontrada), lanza la excepción
3. Si `buscar()` retorna `null` (película no existe), agrega la película

---

### 3️⃣ Captura en Main (`Main.java`)
```java
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
```

**Flujo:**
1. Captura los datos de la película
2. Intenta agregar la película dentro del `try`
3. Si se lanza la excepción, la captura en el `catch`
4. Muestra un mensaje de éxito o error

---

## 🧪 Ejemplos de Ejecución:

### Ejemplo 1: Agregar película nueva ✅
```
Nombre del contenido: Avatar
Nombre del genero: CIENCIA_FICCION
Duración: 162
Calificación: 4.8
Idioma original: INGLES

✅ Película 'Avatar' agregada exitosamente
```

### Ejemplo 2: Intentar agregar película duplicada ❌
```
Nombre del contenido: Shrek
Nombre del genero: ANIMADA
Duración: 90
Calificación: 4.5
Idioma original: INGLES

❌ Error: La pelicula Shrek ya existe.
```

---

## 🔑 Puntos Importantes:

### ¿Por qué la película NO aparece dos veces?
- Porque la excepción se lanza **ANTES** de agregar la película a la lista
- La línea `this.contenido.add(elemento);` nunca se ejecuta si hay duplicado

### ¿Por qué ves el mensaje de error?
- Porque ahora lo estamos mostrando con: `System.out.println("❌ Error: " + e.getMessage());`
- El mensaje viene de la excepción personalizada

### ¿Qué es `try-catch`?
- **`try`**: Bloque donde puede ocurrir una excepción
- **`catch`**: Captura la excepción si ocurre
- Si ocurre, ejecuta el código del `catch` en lugar de fallar el programa

---

## 📊 Comparación: Con vs Sin Excepción

### ❌ SIN Excepción (Incorrecto)
```java
public void agregar(Pelicula elemento){
    this.contenido.add(elemento);  // Agrega directamente
    // ❌ No verifica si existe duplicado
    // ❌ Resultado: Dos películas con mismo título
}
```

### ✅ CON Excepción (Correcto)
```java
public void agregar(Pelicula elemento){
    Pelicula contenido = this.buscar(elemento.getTitulo());

    if (contenido != null) {
        throw new PeliculaExistenteException(elemento.getTitulo());
        // ✅ Se lanza excepción
        // ✅ No se ejecuta el add()
        // ✅ Resultado: Solo una película
    }

    this.contenido.add(elemento);
}
```

---

## 🎯 Garantías del Sistema Actual:

1. ✅ **No hay duplicados**: Imposible tener dos películas con mismo título
2. ✅ **Mensaje claro**: El usuario sabe exactamente qué sucedió
3. ✅ **No falla la app**: El programa continúa aunque haya error
4. ✅ **Facilita debug**: Sabes exactamente qué película causó el problema

---

## 💡 Próximas Mejoras (Opcional):

### Agregar excepciones para otros casos:
```java
// Excepción si la lista está vacía
public Pelicula buscar(String titulo) {
    return contenido.stream()
        .filter(p -> p.getTitulo().equalsIgnoreCase(titulo))
        .findFirst()
        .orElseThrow(() -> 
            new PeliculaNoEncontradaException("No se encontró: " + titulo)
        );
}

// Excepción si intentas eliminar algo que no existe
public void eliminar(String titulo) {
    Pelicula pelicula = buscar(titulo);
    if (pelicula == null) {
        throw new PeliculaNoEncontradaException("No existe: " + titulo);
    }
    contenido.remove(pelicula);
}
```

---

## ✨ Resumen:

Tu aplicación ahora:
1. ✅ Verifica que no hay duplicados al agregar
2. ✅ Lanza una excepción clara si hay intento duplicado
3. ✅ Muestra mensaje de error amigable
4. ✅ Continúa funcionando normalmente
5. ✅ Nunca agrega películas duplicadas
