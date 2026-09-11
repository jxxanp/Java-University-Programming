# Sistema de Rescate 🤖

Taller de Programación 3 — Universidad de Córdoba  
**Autor:** Juan Paulo Lugo Rico  
**IDE:** IntelliJ IDEA | **Período:** 2026

---

## Descripción

Sistema de rescate con robots autónomos que aplica los cuatro pilares de la POO. El profesor proporcionó la estructura base y el estudiante completó la implementación. Los robots se clasifican según sus capacidades de movimiento (vuelo, navegación, arrastre) y son coordinados por un despachador central.

## Conceptos aplicados

- **Abstracción** — clase abstracta `Robot` como base de todos los robots
- **Herencia** — `DroneRescate` y `Anfibio` extienden `Robot`
- **Polimorfismo** — lista de `Rastreable` que contiene distintos tipos de robots
- **Encapsulamiento** — atributos privados con getters/setters en `Robot`
- **Interfaces** — `Rastreable`, `Arrastrable` y `Sumergible` definen capacidades específicas

## Estructura del proyecto

```
sistemarescate/
└── src/
    ├── Robot.java              # Clase abstracta base — id y batería
    ├── Rastreable.java         # Interfaz — capacidad de rastreo
    ├── Arrastrable.java        # Interfaz — capacidad de arrastre
    ├── Sumergible.java         # Interfaz — capacidad de navegación
    ├── DroneRescate.java       # Robot aéreo — implementa Rastreable
    ├── Anfibio.java            # Robot terrestre/acuático — implementa Rastreable, Sumergible y Arrastrable
    ├── DespachadorRescate.java # Coordina y ejecuta búsquedas con equipos de robots
    ├── Resultado.java          # Modelo de resultado de una operación de rescate
    └── Main.java               # Punto de entrada — simulación de operaciones de rescate
```

## Cómo ejecutar

1. Abrir el proyecto en **IntelliJ IDEA** (`File → Open`)
2. Navegar hasta la carpeta `sistemarescate`
3. Ejecutar `Main.java` con `Shift + F10`

---

*Programación 3 — Ingeniería de Sistemas, Universidad de Córdoba*
