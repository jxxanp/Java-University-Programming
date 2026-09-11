public interface Rastreable {
    Resultado rastrear(String zona);
    default boolean sensorDisponible() {
        return true;

    }
}

