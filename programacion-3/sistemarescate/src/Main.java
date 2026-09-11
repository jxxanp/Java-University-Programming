import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Rastreable> equipos = List.of(
                new DroneRescate("Drone One", 108),
                new DroneRescate("Drone Two", 60),
                new Anfibio("Anfibio One", 95)
        );

        List<Rastreable> equipos2 = List.of(
                new DroneRescate("Drone Three", 90),
                new DroneRescate("Drone Four", 80)
        );

        DespachadorRescate despachadorRio = new DespachadorRescate();
        DespachadorRescate despachadorUrra = new DespachadorRescate();
        despachadorUrra.ejecutarBusqueda(equipos2, "Urra");
        despachadorRio.ejecutarBusqueda(equipos, "Rio");

        Anfibio anfibio = new Anfibio("Anfibio Two", 100);
        anfibio.moverse("Suelo");
        anfibio.navegar("Rio");
    }
}