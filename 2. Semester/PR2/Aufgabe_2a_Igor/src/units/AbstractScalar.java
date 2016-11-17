package units;

import units.interfaces.Scalar;

public abstract class AbstractScalar<T extends Scalar> implements Scalar<T> {
    private final double value;
    
    protected AbstractScalar(double value) {
        this.value = value;
    }
  
    public double value() {
        return this.value;
    }
  
    public int compareTo(T arg) {
        return Double.compare(value(), arg.value());
    }
  
    public T abs() {
        return (T)newInstance(Math.abs(value()));
    }
  
    public T max(T arg) {
        return value() < arg.value() ? arg : (T)newInstance(value());
    }
  
    public T min(T arg) {
        return value() > arg.value() ? arg : (T)newInstance(value());
    }
  
    public T add(T arg) {
        return (T)newInstance(value() + arg.value());
    }
  
    public T sub(T arg) {
        return (T)newInstance(value() - arg.value());
    }
  
    public T mul(double arg) {
        return (T)newInstance(value() * arg);
    }
  
    public T div(double arg) {
        return (T)newInstance(value() / arg);
    }
  
    public double div(T arg) {
        return value() / arg.value();
    }
    
    // EQUIVALENCES
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (int) (Double.doubleToLongBits(value()) ^ (Double.doubleToLongBits(value()) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (!getClass().equals(obj.getClass()))
            return false;
        
        return (this.compareTo((T)obj) == 0);
    }
}