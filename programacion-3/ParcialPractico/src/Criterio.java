@FunctionalInterface
public interface Criterio<T> {
    boolean cumple(T elemento);
}
