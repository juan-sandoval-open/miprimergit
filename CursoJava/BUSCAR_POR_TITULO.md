# Formas de Buscar por Título en la Lista de Películas

## 📋 Contexto
- **Ubicación de datos**: `List<Pelicula> contenido` en clase `Plataforma`
- **Atributo a buscar**: `titulo` en clase `Pelicula`
- **Método disponible**: `getTitulo()` en clase `Pelicula`

---

## ✅ OPCIÓN 1: Búsqueda con FOR tradicional (Más básica)

```java
public Pelicula buscarPorTitulo(String titulo) {
    for (Pelicula pelicula : contenido) {
        if (pelicula.getTitulo().equals(titulo)) {
            return pelicula;
        }
    }
    return null; // Si no encuentra
}
```

**Ventajas**: Simple, fácil de entender  
**Desventajas**: No usa características modernas de Java

---

## ✅ OPCIÓN 2: Búsqueda con Stream y filter (RECOMENDADO - Moderno)

```java
public Pelicula buscarPorTitulo(String titulo) {
    return contenido.stream()
            .filter(p -> p.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);
}
```

**Ventajas**: Código limpio, funcional, moderno  
**Desventajas**: Requiere conocer Streams de Java 8+

---

## ✅ OPCIÓN 3: Búsqueda que retorna Lista (Para múltiples coincidencias)

```java
public List<Pelicula> buscarPeliculasPorTitulo(String titulo) {
    return contenido.stream()
            .filter(p -> p.getTitulo().contains(titulo))
            .toList();
}
```

**Ventajas**: Devuelve todas las coincidencias (búsqueda parcial)  
**Desventajas**: Más resultados si hay títulos similares

---

## ✅ OPCIÓN 4: Búsqueda sin distinción de mayúsculas/minúsculas

```java
public Pelicula buscarPorTituloIgnoreCase(String titulo) {
    return contenido.stream()
            .filter(p -> p.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElse(null);
}
```

**Ventajas**: Busca sin importar si escribes mayúsculas o minúsculas  
**Uso**: `buscarPorTituloIgnoreCase("avatar")` encontrará "Avatar"

---

## ✅ OPCIÓN 5: Búsqueda con impresión de resultados

```java
public void buscar(String titulo) {
    contenido.stream()
            .filter(p -> p.getTitulo().equalsIgnoreCase(titulo))
            .forEach(p -> System.out.println(p.obtenerFichaTecnica()));
    
    if (contenido.stream().noneMatch(p -> p.getTitulo().equalsIgnoreCase(titulo))) {
        System.out.println("❌ Película no encontrada: " + titulo);
    }
}
```

**Ventajas**: Imprime resultados directamente  
**Uso**: Perfecto para menús interactivos

---

## 📊 Comparativa de Métodos

| Método | Tipo | Búsqueda Exacta | Rápida | Moderno |
|--------|------|-----------------|--------|---------|
| FOR tradicional | Imperativo | ✅ | ✅ | ❌ |
| Stream + filter | Funcional | ✅ | ✅ | ✅ |
| Contains | Funcional | ⚠️ Parcial | ✅ | ✅ |
| IgnoreCase | Funcional | ✅ | ✅ | ✅ |

---

## 🎯 Recomendación

**Para tu caso, usa OPCIÓN 2 o OPCIÓN 4** (Stream + filter):

```java
// Si necesitas búsqueda exacta:
public Pelicula buscarPorTitulo(String titulo) {
    return contenido.stream()
            .filter(p -> p.getTitulo().equals(titulo))
            .findFirst()
            .orElse(null);
}

// Si necesitas búsqueda sin case-sensitive:
public Pelicula buscar(String titulo) {
    return contenido.stream()
            .filter(p -> p.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElse(null);
}
```

---

## 💡 Ejemplo de Uso

```java
Plataforma netflix = new Plataforma("Netflix");
netflix.agregar(new Pelicula("Avatar", 162, "Ciencia Ficción"));
netflix.agregar(new Pelicula("Titanic", 194, "Romance"));

// Búsqueda
Pelicula encontrada = netflix.buscar("Avatar");
if (encontrada != null) {
    System.out.println(encontrada.obtenerFichaTecnica());
} else {
    System.out.println("No encontrada");
}
```
