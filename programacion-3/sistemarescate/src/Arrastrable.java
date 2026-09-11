public interface Arrastrable {
    default void moverse(String zona) {
        System.out.println("Arrastrándose por tierra en la zona: " + zona);
    }

    default boolean requiereTraccionEspecial() {
        return true;
    }
}