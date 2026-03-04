# AppMultitarea (Demo escolar)

Aplicación Android básica para demostrar **multitarea real y visible** usando **Kotlin Coroutines**.

## ¿Qué hace?
- Ejecuta **2 procesos diferentes al mismo tiempo**:
  - **Tarea A:** animación/progreso continuo (se ve moverse en tiempo real).
  - **Tarea B:** cálculo pesado por etapas (actualiza resultado y progreso).
- Muestra un **log en pantalla** donde se ve la intercalación de eventos de A y B.
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
