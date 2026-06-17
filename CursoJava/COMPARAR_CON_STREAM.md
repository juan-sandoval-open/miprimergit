# 🔍 Funciones Stream para Comparar con Parámetros

## Contexto:
- Necesitas filtrar películas comparando un atributo con un valor que recibes como parámetro
- Usar Stream de Java 8+

---

## ✅ OPCIÓN 1: `.filter()` con condición simple (MÁS COMÚN)

```java
public List<Pelicula> getCalificacion(double valor) {
    return contenido.stream()
                    .filter(p -> p.getCalificacion() == valor)
                    .toList();
}
```

**Usos**:
- `==` para igualdad exacta
- `>` para mayor que
- `<` para menor que
- `>=` para mayor o igual
- `<=` para menor o igual

**Ejemplo desde Main**:
```java
List<Pelicula> peliculasAltas = plataforma.getCalificacion(4.5);
```

---

## ✅ OPCIÓN 2: Filtrar películas con calificación MAYOR que el parámetro

```java
public List<Pelicula> getCalificacionMayorQue(double valor) {
    return contenido.stream()
                    .filter(p -> p.getCalificacion() > valor)
                    .toList();
}
```

**Ejemplo**:
```java
List<Pelicula> peliculasBuenas = plataforma.getCalificacionMayorQue(3.5);
```

---

## ✅ OPCIÓN 3: Filtrar películas con calificación MENOR que el parámetro

```java
public List<Pelicula> getCalificacionMenorQue(double valor) {
    return contenido.stream()
                    .filter(p -> p.getCalificacion() < valor)
                    .toList();
}
```

**Ejemplo**:
```java
List<Pelicula> peliculasRegulares = plataforma.getCalificacionMenorQue(3.0);
```

---

## ✅ OPCIÓN 4: Filtrar películas ENTRE dos valores

```java
public List<Pelicula> getCalificacionEntre(double minimo, double maximo) {
    return contenido.stream()
                    .filter(p -> p.getCalificacion() >= minimo && p.getCalificacion() <= maximo)
                    .toList();
}
```

**Ejemplo**:
```java
List<Pelicula> peliculasMedianas = plataforma.getCalificacionEntre(3.0, 4.0);
```

---

## ✅ OPCIÓN 5: Filtrar y ORDENAR por calificación

```java
public List<Pelicula> getCalificacionOrdenada(double valor) {
    return contenido.stream()
                    .filter(p -> p.getCalificacion() >= valor)
                    .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
                    .toList();
}
```

**Ejemplo**:
```java
// Películas con calificación >= 3.0, ordenadas de mayor a menor
List<Pelicula> mejores = plataforma.getCalificacionOrdenada(3.0);
```

---

## ✅ OPCIÓN 6: Comparar con STRING (por género)

```java
public List<Pelicula> getPeliculasPorGenero(String genero) {
    return contenido.stream()
                    .filter(p -> p.getGenero().equalsIgnoreCase(genero))
                    .toList();
}
```

**Ejemplo**:
```java
List<Pelicula> animadas = plataforma.getPeliculasPorGenero("Animada");
```

---

## ✅ OPCIÓN 7: Comparar con INT (por duración)

```java
public List<Pelicula> getPeliculasLargas(int minutos) {
    return contenido.stream()
                    .filter(p -> p.getDuracion() > minutos)
                    .toList();
}
```

**Ejemplo**:
```java
List<Pelicula> largas = plataforma.getPeliculasLargas(150);
```

---

## 📊 Operadores de Comparación

| Operador | Significado | Ejemplo |
|----------|-------------|---------|
| `==` | Igual a | `p.getCalificacion() == 4.5` |
| `!=` | No igual a | `p.getCalificacion() != 3.0` |
| `>` | Mayor que | `p.getCalificacion() > 3.5` |
| `<` | Menor que | `p.getCalificacion() < 4.0` |
| `>=` | Mayor o igual | `p.getCalificacion() >= 3.5` |
| `<=` | Menor o igual | `p.getCalificacion() <= 5.0` |
| `&&` | Y (AND) | `p.getCalificacion() > 3 && p.getDuracion() < 120` |
| `\|\|` | O (OR) | `p.getGenero().equals("Acción") \|\| p.getGenero().equals("Drama")` |

---

## 🎯 Para tu caso específico (getCalificacion):

```java
// Opción A: Exactamente igual al valor
public List<Pelicula> getCalificacion(double valor) {
    return contenido.stream()
                    .filter(p -> p.getCalificacion() == valor)
                    .toList();
}

// Opción B: Mayor o igual al valor (MÁS ÚTIL)
public List<Pelicula> getCalificacion(double valor) {
    return contenido.stream()
                    .filter(p -> p.getCalificacion() >= valor)
                    .toList();
}

// Opción C: Mayor o igual, ordenadas descendente (RECOMENDADA)
public List<Pelicula> getCalificacion(double valor) {
    return contenido.stream()
                    .filter(p -> p.getCalificacion() >= valor)
                    .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
                    .toList();
}
```

---

## 💡 Funciones Stream más usadas para comparar:

| Función | Uso | Ejemplo |
|---------|-----|---------|
| **`.filter()`** | Filtra por condición | `.filter(p -> p.getCalificacion() > 3)` |
| **`.sorted()`** | Ordena resultados | `.sorted(Comparator.comparingDouble(...))` |
| **`.limit()`** | Limita cantidad | `.limit(5)` - primeros 5 |
| **`.skip()`** | Omite primeros | `.skip(2)` - omite primeros 2 |
| **`.findFirst()`** | Obtiene primero | `.findFirst().orElse(null)` |
| **`.findAny()`** | Obtiene cualquiera | `.findAny().orElse(null)` |
| **`.count()`** | Cuenta resultados | `.count()` |
| **`.anyMatch()`** | ¿Alguno coincide? | `.anyMatch(p -> p.getCalificacion() > 4)` |
| **`.allMatch()`** | ¿Todos coinciden? | `.allMatch(p -> p.getDuracion() > 90)` |

---

## 🧪 Ejemplo completo desde Main:

```java
// En Main.java
case BUSCAR_POR_CALIFICACION:
    double calificacionBuscada = ScannerUtils.capturarDecimal("Calificación mínima: ");
    List<Pelicula> peliculasAltas = plataforma.getCalificacion(calificacionBuscada);
    
    if (peliculasAltas.isEmpty()) {
        System.out.println("❌ No hay películas con esa calificación");
    } else {
        System.out.println("✅ Películas encontradas:");
        peliculasAltas.forEach(p -> System.out.println(p.obtenerFichaTecnica()));
    }
    break;
```
