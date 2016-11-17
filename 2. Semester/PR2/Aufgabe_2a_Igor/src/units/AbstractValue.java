package units;

import units.interfaces.Value;

public abstract class AbstractValue<T> implements Value<T> {
    public abstract String toString();
    
    public abstract int hashCode();
    
    public abstract boolean equals(Object object);
}