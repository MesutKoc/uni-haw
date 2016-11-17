package Values.physicsValues.interfaces;

import Utils.InvariantViolatedException;

public abstract interface PhysicsScalar<T extends PhysicsScalar> extends PhysicsValue {
    // CREATION
    default boolean invariant() {
        return !(Double.valueOf(value()).isInfinite() || Double.valueOf(value()).isNaN());
    }

    // SELECTORS
    public abstract double value();
    
    default void checkInvariant() {
        if(!invariant()) {
            throw new InvariantViolatedException();
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