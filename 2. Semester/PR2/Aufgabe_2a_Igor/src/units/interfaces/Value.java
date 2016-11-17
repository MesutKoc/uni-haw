package units.interfaces;

public abstract interface Value<T> extends MyComparable<T> {
    // OPERATIONS
    default boolean invariant() {
        return true;
    }
    
    default void checkInvariant() {
        if(!invariant()) {
            System.exit(-1);
        }
    }
    
    public abstract T newInstance(double value);
  
    public abstract T abs();
  
    public abstract T min(T value);
  
    public abstract T max(T value);
  
    public abstract T add(T value);
  
    public abstract T sub(T value);
  
    public abstract T mul(double value);
  
    public abstract T div(double value);
  
    public abstract double div(T value);
}