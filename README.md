# AppMultitarea (Demo escolar)

Aplicación Android básica para demostrar **multitarea** usando **Kotlin Coroutines**.

## ¿Qué hace?
- Ejecuta dos tareas en paralelo (Tarea A y Tarea B).
- Muestra progreso independiente en dos barras.
- Permite cancelar ambas tareas.

## Abrir en Android Studio
1. Abre este directorio en Android Studio.
2. Sincroniza Gradle.
3. Ejecuta en un emulador o dispositivo.

## Nota sobre binarios
Este repositorio evita incluir binarios para facilitar pull requests.
Si necesitas wrapper de Gradle, puedes generarlo localmente con:

```bash
gradle wrapper
```

(Esto crea `gradle/wrapper/gradle-wrapper.jar`, que normalmente no se sube aquí.)
