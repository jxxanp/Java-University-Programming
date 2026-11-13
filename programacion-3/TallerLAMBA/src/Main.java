import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        List<Producto> productos = Arrays.asList(
                new Producto("Teclado", "Periféricos", 120000, 10),
                new Producto("Mouse", "Periféricos", 65000, 0),
                new Producto("Monitor", "Pantallas", 850000, 5),
                new Producto("Memoria USB", "Almacenamiento", 35000, 20),
                new Producto("Disco SSD", "Almacenamiento", 280000, 3),
                new Producto("Portátil", "Computadores", 3200000, 2)
        );

        // PARTE 1: Operaciones con una interfaz funcional
        System.out.println("=== PARTE 1: OPERACIONES DE PRECIO ===");

        OperacionPrecio descuento10 = precio -> precio * 0.90;
        OperacionPrecio incremento5 = precio -> precio * 1.05;
        OperacionPrecio conservarPrecio = precio -> precio;

        double precioPrueba = 100000;

        System.out.println("Descuento del 10 %: $"
                + calcularPrecio(precioPrueba, descuento10));
        System.out.println("Incremento del 5 %: $"
                + calcularPrecio(precioPrueba, incremento5));
        System.out.println("Precio original: $"
                + calcularPrecio(precioPrueba, conservarPrecio));

        // PARTE 2: Filtrado con Predicate
        System.out.println("\n=== PARTE 2: FILTRADO ===");

        List<Producto> economicos =
                filtrar(productos, p -> p.getPrecio() < 100000);
        mostrarNombres("Productos con precio inferior a $100.000", economicos);

        List<Producto> conStock =
                filtrar(productos, p -> p.getStock() > 0);
        mostrarNombres("Productos con stock disponible", conStock);

        List<Producto> deAlmacenamiento =
                filtrar(productos,
                        p -> p.getCategoria().equals("Almacenamiento"));
        mostrarNombres("Productos de Almacenamiento", deAlmacenamiento);

        Predicate<Producto> stockDisponible =
                p -> p.getStock() > 0;
        Predicate<Producto> precioMenorA300000 =
                p -> p.getPrecio() < 300000;

        List<Producto> disponiblesYEconomicos =
                filtrar(productos,
                        stockDisponible.and(precioMenorA300000));
        mostrarNombres(
                "Con stock y precio inferior a $300.000",
                disponiblesYEconomicos
        );

        // PARTE 3: Transformación con Function
        System.out.println("\n=== PARTE 3: TRANSFORMACIÓN ===");

        List<String> nombres =
                transformar(productos, p -> p.getNombre());
        System.out.println("Nombres: " + nombres);

        List<Double> preciosConDescuento =
                transformar(productos, p -> p.getPrecio() * 0.85);
        System.out.println(
                "Precios con descuento del 15 %: " + preciosConDescuento
        );

        List<String> descripciones = transformar(
                productos,
                p -> p.getNombre()
                        + " | " + p.getCategoria()
                        + " | Stock: " + p.getStock()
        );

        System.out.println("Descripciones:");
        procesar(descripciones, descripcion ->
                System.out.println(descripcion)
        );

        System.out.println("Productos originales:");
        procesar(productos, producto ->
                System.out.println(producto)
        );

        // PARTE 4A: Acciones con Consumer
        System.out.println("\n=== PARTE 4A: CONSUMER ===");

        System.out.println("Nombre de cada producto:");
        procesar(productos, producto ->
                System.out.println(producto.getNombre())
        );

        System.out.println("Alertas de productos con stock inferior a 5:");
        procesar(productos, producto -> {
            if (producto.getStock() < 5) {
                System.out.println(
                        "ALERTA: " + producto.getNombre()
                                + " tiene pocas unidades. Stock: "
                                + producto.getStock()
                );
            }
        });

        // PARTE 4B: Creación con Supplier
        System.out.println("\n=== PARTE 4B: SUPPLIER ===");

        Supplier<Producto> creadorProducto =
                () -> new Producto("Producto nuevo", "Sin categoría", 0, 0);

        Producto producto1 = creadorProducto.get();
        Producto producto2 = creadorProducto.get();

        System.out.println("Primer producto: " + producto1);
        System.out.println("Segundo producto: " + producto2);
        System.out.println(
                "¿Son el mismo objeto? " + (producto1 == producto2)
        );

        // PARTE 5: Reto integrador
        System.out.println("\n=== PARTE 5: RETO INTEGRADOR ===");

        Predicate<Producto> categoriaAlmacenamiento =
                p -> p.getCategoria().equals("Almacenamiento");
        Predicate<Producto> stockMayorQueCero =
                p -> p.getStock() > 0;

        List<Producto> productosParaCampana = filtrar(
                productos,
                categoriaAlmacenamiento.and(stockMayorQueCero)
        );

        List<String> mensajesCampana = transformar(
                productosParaCampana,
                p -> p.getNombre()
                        + " - Precio de campaña: $"
                        + (p.getPrecio() * 0.80)
        );

        procesar(mensajesCampana, mensaje ->
                System.out.println(mensaje)
        );
    }

    public static double calcularPrecio(
            double precio,
            OperacionPrecio operacion) {
        return operacion.aplicar(precio);
    }

    public static List<Producto> filtrar(
            List<Producto> productos,
            Predicate<Producto> criterio) {
        List<Producto> resultado = new ArrayList<>();

        for (Producto producto : productos) {
            if (criterio.test(producto)) {
                resultado.add(producto);
            }
        }

        return resultado;
    }

    public static <R> List<R> transformar(
            List<Producto> productos,
            Function<Producto, R> transformacion) {
        List<R> resultado = new ArrayList<>();

        for (Producto producto : productos) {
            resultado.add(transformacion.apply(producto));
        }

        return resultado;
    }

    public static <T> void procesar(
            List<T> elementos,
            Consumer<T> accion) {
        for (T elemento : elementos) {
            accion.accept(elemento);
        }
    }

    public static void mostrarNombres(
            String titulo,
            List<Producto> productos) {
        System.out.println(titulo + ":");

        for (Producto producto : productos) {
            System.out.println("- " + producto.getNombre());
        }
    }
}
