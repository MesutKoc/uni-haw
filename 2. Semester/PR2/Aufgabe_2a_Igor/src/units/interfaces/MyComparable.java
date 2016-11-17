package units.interfaces;

public abstract interface MyComparable<T> extends Comparable<T>{
    // OPERATIONS
    public abstract int compareTo(T value);
    
    default boolean smallerThan(T value) {
        return this.compareTo(value) < 0;
    }
    default boolean biggerThan(T value) {
        return this.compareTo(value) > 0;
    }
    default boolean smallerThanOrEquals(T value) {
        return !biggerThan(value);
    }
    default boolean biggerThanOrEquals(T value) {
        return !smallerThan(value);
    }
    default boolean between(T min, T max) {
        return !(smallerThan(min) || biggerThan(max));
    }
}