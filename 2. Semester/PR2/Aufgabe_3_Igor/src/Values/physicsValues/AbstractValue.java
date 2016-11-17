package Values.physicsValues;

import Values.interfaces.Value;

public abstract class AbstractValue<T> implements Value {
    public abstract String toString();
    
    public abstract int hashCode();
    
    public abstract boolean equals(Object object);
}