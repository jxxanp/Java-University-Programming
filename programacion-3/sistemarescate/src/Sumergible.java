public interface Sumergible {
    default void navegar(String zona) {
        System.out.println("Nadando/sumergido por agua en la zona: " + zona);
    }

    default boolean puedeNavegarEnAgua() {
        return true;
    }
}