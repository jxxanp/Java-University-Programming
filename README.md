# Java University Programming ☕

Repositorio personal que reúne los proyectos, talleres y trabajos desarrollados en Java durante mi formación universitaria en **Ingeniería de Sistemas** — Universidad de Córdoba, Colombia.

**Autor:** Juan Paulo Lugo Rico  
**GitHub:** [@jxxanp](https://github.com/jxxanp)  
**Carrera:** Ingeniería de Sistemas — Universidad de Córdoba

---

## 📁 Estructura del repositorio

```
java-university-projects/
├── programacion-1/       # Proyectos del 2° semestre (2025)
├── programacion-2/       # Proyectos del 3° semestre (2026)
└── programacion-3/       # Proyectos del 4° semestre (en curso)
```

Cada carpeta de semestre contiene sus propios proyectos organizados individualmente, junto con un `README.md` que describe los temas vistos en ese curso.

---

## 🗂️ Tabla de proyectos

| Proyecto | Descripción | Curso | Período |
|---|---|---|---|
| [CalculadoraRacionales](./programacion-1/CalculadoraRacionales/) | Calculadora de números racionales con operaciones de suma, resta, multiplicación y división. Implementa normalización por MCD y GUI con Swing. | Programación 1 | Ago–Sep 2025 |
| [TiendaOfertas](./programacion-1/TiendaOfertas/) | Sistema de compras al por mayor con cálculo de descuentos por volumen y obsequios según cantidad de docenas. Incluye GUI. | Programación 1 | Sep 2025 |
| [CuentaBancariaProject](./programacion-1/CuentaBancariaProject/) | Gestión de cuenta bancaria con operaciones de depósito, retiro, transferencia, cálculo de intereses y simulación de inversión. GUI con Swing. | Programación 1 | Sep 2025 |
| [ConcesionarioProject](./programacion-1/ConcesionarioProject/) | Gestión de inventario de vehículos en un concesionario. Permite registrar, buscar y marcar vehículos como disponibles o vendidos. GUI con JTable. | Programación 1 | Oct 2025 |
| [TrabajadoresProject](./programacion-1/TrabajadoresProject/) | Gestión de trabajadores usando vectores de objetos. Registra código, nombres, departamento, cargo, años trabajados y salario. GUI con JTable. | Programación 1 | Oct 2025 |
| [VectoresParalelos](./programacion-1/VectoresParalelos/) | Manejo de inventario de productos mediante vectores paralelos (código, nombre, precio, cantidad). Visualización en tabla interactiva con Swing. | Programación 1 | Oct–Nov 2025 |
| [SeleccionFutbol](./programacion-2/SeleccionFutbol/) | Gestión de una selección de fútbol con jerarquía de herencia: clase base `SeleccionFutbol` extendida por `Futbolista`, `Entrenador` y `Masajista`. | Programación 2 | Feb–Mar 2026 |
| [SistemaDeGestionPersonas](./programacion-2/SistemaDeGestionPersonas/) | Sistema de gestión con herencia: clase base `Persona` extendida por `Cliente` y `Empleado`. Interfaz gráfica completa con Swing. | Programación 2 | Mar 2026 |
| [SistemaDeGestionAcademica](./programacion-2/SistemaDeGestionAcademica/) | Sistema académico con jerarquía `BusinessEntityBase → Persona → Alumno / Instructor`. Aplica herencia multinivel y clases abstractas. | Programación 2 | Mar–Abr 2026 |
| [SistemaDeGestionPedidos](./programacion-2/SistemaDeGestionPedidos/) | Sistema de pedidos con modelo de dominio completo: `Cliente`, `ClienteCorporativo`, `ClientePersonal`, `Producto`, `LineaPedido`, `Pedido` y `Vendedor`. | Programación 2 | Abr 2026 |
| [GestionEmpleados](./programacion-2/GestionEmpleados/) | Gestión de empleados con herencia: clase base `Empleado` extendida por `TiempoCompleto` y `MedioTiempo`. GUI con Swing. Taller de práctica. | Programación 2 | Abr–May 2026 |
| [SistemasGestionEmpleados](./programacion-2/SistemasGestionEmpleados/) | Versión extendida del sistema de gestión de empleados desarrollada como examen parcial. Incluye formulario de registro separado y gestor centralizado. | Programación 2 | May–Jun 2026 |
| [APPListas](./programacion-2/APPListas/) | Aplicación de gestión de estudiantes implementando una **lista simplemente enlazada desde cero** con nodos. Operaciones: agregar al inicio/final, buscar por ID y eliminar. Patrón MVC con GUI en Swing. | Programación 2 | Abr–May 2026 |
| [SistemaRescate](./programacion-3/sistemarescate/) | Taller — sistema de robots de rescate con interfaces, clases abstractas y polimorfismo. | Programación 3 | Ago.–Sep. 2026 |
| [TallerLAMBA](./programacion-3/TallerLAMBA/) | Taller de interfaces funcionales y expresiones lambda. Aplica `Predicate`, `Function`, `Consumer` y `Supplier` para filtrar, transformar y procesar productos. | Programación 3 | Sep.–Oct. 2026 |
| [ParcialPractico](./programacion-3/ParcialPractico/) | Parcial práctico con interfaces funcionales genéricas, expresiones lambda, criterios de filtrado, iteradores y reglas de tarifas aplicadas a cursos. | Programación 3 | Oct.–Nov. 2026 |

---

## 🛠️ Cómo ejecutar cualquier proyecto

Los proyectos de Programación 1 y 2 se desarrollaron principalmente en **NetBeans IDE**. Los proyectos recientes de Programación 3 utilizan **IntelliJ IDEA**.

**Requisitos:**
- Java JDK 8 o superior
- NetBeans IDE para proyectos de Programación 1 y 2
- IntelliJ IDEA para los proyectos de Programación 3

**Pasos:**
1. Clona el repositorio:
   ```bash
   git clone https://github.com/jxxanp/java-university-projects.git
   ```
2. Abre el proyecto con el IDE correspondiente (NetBeans o IntelliJ IDEA).
3. Navega hasta la carpeta del proyecto que deseas abrir
4. Haz clic en `Run Project` (F6)

---

## 📚 Temas cubiertos

**Programación 1** — 2° semestre (2025)
- Clases, objetos y encapsulamiento
- Vectores y arrays de objetos
- Vectores en paralelo
- Interfaces gráficas con Swing y JFrame
- JTable y manejo de datos tabulares
- Métodos de búsqueda y ordenamiento

**Programación 2** — 3° semestre (2026)
- Herencia y polimorfismo
- Clases abstractas e interfaces
- Jerarquías de herencia multinivel
- Estructuras de datos: listas enlazadas
- Patrones de diseño básicos (MVC)
- Manejo de colecciones (ArrayList)

---

**Programación 3** — 4° semestre (2026)
- Interfaces funcionales propias y anotación `@FunctionalInterface`
- Expresiones lambda
- Interfaces funcionales de Java: `Predicate`, `Function`, `Consumer` y `Supplier`
- Genéricos y criterios de filtrado reutilizables
- Iteradores (`Iterator`) y procesamiento de colecciones
- Transformación de datos y aplicación de reglas de negocio mediante funciones

---

## 🚀 Crecimiento del repositorio

Este repositorio está pensado como un historial activo de mi formación. A medida que avance en la carrera, iré agregando los nuevos proyectos por semestre mediante commits organizados.

---

*Universidad de Córdoba — Ingeniería de Sistemas*
