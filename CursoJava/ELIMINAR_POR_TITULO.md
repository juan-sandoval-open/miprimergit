# 🗑️ Cómo Eliminar una Película por Título

## Contexto:
- **Ubicación de datos**: `List<Pelicula> contenido` en clase `Plataforma`
- **Lo que necesitas**: Capturar el nombre/título y eliminar la película

---

## ✅ OPCIÓN 1: Método que retorna booleano (RECOMENDADA)

```java
public boolean eliminarPorTitulo(String titulo) {
    boolean eliminado = contenido.removeIf(p -> p.getTitulo().equalsIgnoreCase(titulo));
    
    if (eliminado) {
        System.out.println("✅ Película '" + titulo + "' eliminada correctamente de " + this.nombre);
    } else {
        System.out.println("❌ Película '" + titulo + "' no encontrada en " + this.nombre);
    }
    
    return eliminado;
}
```

**Ventajas**: 
- Usa `removeIf()` que es eficiente
- Retorna si fue exitosa o no
- Muestra mensajes útiles

**Uso en Main.java**:
```java
case ELIMINAR:
    String nombreEliminar = ScannerUtils.capturarTexto("Nombre a eliminar: ");
    plataforma.eliminarPorTitulo(nombreEliminar);
    break;
```

---

## ✅ OPCIÓN 2: Buscar primero, luego eliminar

```java
public void eliminarPorTitulo(String titulo) {
    Pelicula peliculaAEliminar = contenido.stream()
            .filter(p -> p.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElse(null);
    
    if (peliculaAEliminar != null) {
        contenido.remove(peliculaAEliminar);
        System.out.println("✅ Película '" + titulo + "' eliminada correctamente");
    } else {
        System.out.println("❌ Película '" + titulo + "' no encontrada");
    }
}
```

**Ventajas**: 
- Más explícito (primero busca, luego elimina)
- Fácil de entender

**Desventajas**: 
- Recorre la lista dos veces

---

## ✅ OPCIÓN 3: Con manejo de errores

```java
public void eliminarPorTitulo(String titulo) throws IllegalArgumentException {
    if (titulo == null || titulo.trim().isEmpty()) {
        throw new IllegalArgumentException("El título no puede estar vacío");
    }
    
    boolean eliminado = contenido.removeIf(p -> p.getTitulo().equalsIgnoreCase(titulo));
    
    if (eliminado) {
        System.out.println("✅ Película '" + titulo + "' eliminada correctamente de " + this.nombre);
    } else {
        throw new IllegalArgumentException("❌ Película '" + titulo + "' no encontrada");
    }
}
```

**Ventajas**: 
- Valida entrada
- Manejo robusto de errores

---

## 📊 Comparativa

| Opción | removeIf | Stream | Mensajes | Retorna | Compleja |
|--------|----------|--------|----------|---------|----------|
| 1 | ✅ | ❌ | ✅ | ✅ | ⚠️ Media |
| 2 | ❌ | ✅ | ✅ | ❌ | Media |
| 3 | ✅ | ❌ | ✅ | ❌ | 🔴 Alta |

---

## 🎯 RECOMENDACIÓN: Usa OPCIÓN 1

Es la más eficiente y clara. Actualiza tu `Main.java` así:

```java
case ELIMINAR:
    String nombreEliminar = ScannerUtils.capturarTexto("Nombre a eliminar: ");
    plataforma.eliminarPorTitulo(nombreEliminar);
    break;
```

Y en `Plataforma.java`:

```java
public boolean eliminarPorTitulo(String titulo) {
    boolean eliminado = contenido.removeIf(p -> p.getTitulo().equalsIgnoreCase(titulo));
    
    if (eliminado) {
        System.out.println("✅ Película '" + titulo + "' eliminada correctamente de " + this.nombre);
    } else {
        System.out.println("❌ Película '" + titulo + "' no encontrada en " + this.nombre);
    }
    
    return eliminado;
}
```

---

## 🔑 Comandos importantes:

- **`removeIf(condición)`** - Elimina elementos que cumplan la condición
- **`equalsIgnoreCase()`** - Compara sin importar mayúsculas/minúsculas
- **`stream().filter().findFirst()`** - Busca el primer elemento que coincida
- **`orElse(null)`** - Si no encuentra, devuelve null
