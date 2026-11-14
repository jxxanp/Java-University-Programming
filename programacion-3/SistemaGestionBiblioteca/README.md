# Sistema de Gestión de Biblioteca

Proyecto de Programación III desarrollado en Java para representar distintos materiales de una biblioteca y las operaciones que pueden realizarse sobre ellos.

## Funcionalidades y conceptos aplicados

- **Herencia y abstracción:** clase base `MaterialBiblioteca` y especializaciones para libros físicos, libros digitales, revistas, DVD y audiolibros.
- **Interfaces:** `Prestable`, `Descargable`, `Renovable` y `Reservable`, que definen capacidades distintas para cada tipo de material.
- **Polimorfismo:** uso de referencias de interfaces y de la clase base para ejecutar comportamientos específicos.
- **Validaciones:** comprobación de usuarios vacíos, préstamos duplicados, reservas existentes y límites de renovación.
- **Pruebas desde consola:** el programa principal recorre escenarios de préstamo, devolución, descarga, renovación y reserva.

## Organización del proyecto

```
SistemaGestionBiblioteca/
├── src/
│   ├── clases/
│   │   ├── Audiolibro.java
│   │   ├── DVD.java
│   │   ├── LibroDigital.java
│   │   ├── LibroFisico.java
│   │   ├── MaterialBiblioteca.java
│   │   └── Revista.java
│   ├── interfaces/
│   │   ├── Descargable.java
│   │   ├── Prestable.java
│   │   ├── Renovable.java
│   │   └── Reservable.java
│   └── Main.java
├── integrantes.txt
└── .gitignore
```

## Ejecución

Abrir la carpeta del proyecto en IntelliJ IDEA y ejecutar la clase `Main`.

**Período:** Ago.–Sep. 2026  
**Asignatura:** Programación III
