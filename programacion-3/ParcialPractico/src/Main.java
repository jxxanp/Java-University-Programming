import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Main {

    // --- Punto 4: ejecuta una ReglaTarifa sobre un precio ---
    public static double calcularTarifa(double precio, ReglaTarifa regla) {
        return regla.aplicar(precio);
    }

    // --- Punto 5: filtra una lista genérica según un Criterio<T> ---
    public static <T> List<T> seleccionar(List<T> elementos, Criterio<T> criterio) {
        List<T> resultado = new ArrayList<>();
        for (int i = 0; i < elementos.size(); i++) {
            if (criterio.cumple(elementos.get(i))) {
                resultado.add(elementos.get(i));
            }
        }
        return resultado;
    }

    // --- Punto 6: recorre la lista e imprime cada curso con el formato dado ---
    public static void mostrarCursos(List<Curso> cursos, FormatoCurso formato) {
        Iterator<Curso> it = cursos.iterator();
        while (it.hasNext()) {
            Curso curso = it.next();
            System.out.println(formato.formatear(curso));
        }
    }

    public static void main(String[] args) {

        List<Curso> cursos = Arrays.asList(
                new Curso("Java_básico ", "Programación ", 180000, 12),
                new Curso("Python_aplicado ", "Programación ", 240000, 0),
                new Curso("Bases_de_datos ", "Datos ", 210000, 8),
                new Curso("Diseño_web ", "Diseño ", 150000, 4),
                new Curso("Java_avanzado ", "Programación ", 320000, 5)
        );

        // ===== Punto 4: ReglaTarifa =====
        ReglaTarifa descuento10 = precio -> precio * 0.9;
        ReglaTarifa cargoFijo = precio -> precio + 20000;

        double precioJavaBasico = cursos.get(0).getPrecio();
        System.out.println("--- Punto 4 ---");
        System.out.println("Descuento 10%: $" + calcularTarifa(precioJavaBasico, descuento10));
        System.out.println("Cargo fijo: $" + calcularTarifa(precioJavaBasico, cargoFijo));

        // ===== Punto 5: Criterio<T> =====
        Criterio<Curso> programacionConCupos =
                c -> c.getArea().equals("Programación") && c.getCupos() > 0;
        Criterio<Curso> precioMenor200k = c -> c.getPrecio() < 200000;

        List<Curso> cursosProgConCupos = seleccionar(cursos, programacionConCupos);
        List<Curso> cursosBaratos = seleccionar(cursos, precioMenor200k);

        System.out.println("\n--- Punto 5 ---");
        System.out.println("Programación con cupos: " + cursosProgConCupos);
        System.out.println("Precio < 200000: " + cursosBaratos);

        List<Integer> numeros = Arrays.asList(4, 11, 7, 20, 15);
        Criterio<Integer> mayorQueDiez = n -> n > 10;
        System.out.println("Mayores que diez: " + seleccionar(numeros, mayorQueDiez));

        // ===== Punto 6: FormatoCurso =====
        FormatoCurso formatoNombreArea = c -> c.getNombre() + " | Área: " + c.getArea();
        FormatoCurso formatoDisponibilidad = c ->
                c.getNombre() + " | " + (c.getCupos() > 0 ? "Disponible" : "Sin cupos");

        System.out.println("\n--- Punto 6: formato nombre y área ---");
        mostrarCursos(cursos, formatoNombreArea);

        System.out.println("\n--- Punto 6: formato nombre y disponibilidad ---");
        mostrarCursos(cursos, formatoDisponibilidad);

        // ===== Punto 7: Reto integrador =====
        Criterio<Curso> participantesCampana =
                c -> c.getArea().equals("Programación") && c.getCupos() > 0;
        List<Curso> participantes = seleccionar(cursos, participantesCampana);

        ReglaTarifa reglaCampana = precio ->
                precio >= 300000 ? precio * 0.85 : precio * 0.95;

        FormatoCurso formatoPromocional = c -> {
            double precioPromo = calcularTarifa(c.getPrecio(), reglaCampana);
            return c.getNombre() + " | Precio promocional: $" + (long) precioPromo;
        };

        System.out.println("\n--- Punto 7: Reto integrador ---");
        mostrarCursos(participantes, formatoPromocional);

        System.out.println("\n--- Cursos originales (sin modificar) ---");
        mostrarCursos(cursos, formatoNombreArea);
    }
}
