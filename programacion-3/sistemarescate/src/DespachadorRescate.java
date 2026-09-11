import java.util.List;

public class DespachadorRescate {
    public void ejecutarBusqueda(
            List<Rastreable> equipos, String zona) {
        for (Rastreable unidad : equipos) {
            Resultado resultado = unidad.rastrear(zona);
        System.out.println(resultado);
        }

    }
}
