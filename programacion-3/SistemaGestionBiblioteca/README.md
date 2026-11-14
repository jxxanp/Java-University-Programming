# Sistema de Gestión de Biblioteca

Proyecto de Programación III desarrollado en Java para representar distintos materiales de una biblioteca y las operaciones que pueden realizarse sobre ellos.

## Funcionalidades y conceptos aplicados

- **Herencia y abstracción:** clase base `MaterialBiblioteca` y especializaciones para libros físicos, libros digitales, revistas, DVD y audiolibros.
- **Interfaces:** `Prestable`, `Descargable`, `Renovable` y `Reservable`, que definen capacidades distintas para cada tipo de material.
- **Polimorfismo:** uso de referencias de interfaces y de la clase base para ejecutar comportamientos específicos.
- **Validaciones:** comprobación de usuarios vacíos, préstamos duplicados, reservas existentes y límites de renovación.
- **Pruebas desde consola:** el programa principal recorre escenarios de préstamo, devolución, descarga, renovación y reserva.

## Contenido del proyecto

El código fuente está disponible directamente en esta carpeta, organizado en `src/clases` y `src/interfaces`, junto con `src/Main.java`. También se incluyen `integrantes.txt` y `.gitignore`. Los archivos compilados y la configuración local del IDE se excluyen del repositorio.

## Ejecución

1. Abre la carpeta `SistemaGestionBiblioteca` en IntelliJ IDEA.
2. Verifica que la carpeta `src` esté configurada como fuente del proyecto.
3. Ejecuta la clase `Main`.

**Período:** Ago.–Sep. 2026  
**Asignatura:** Programación III
