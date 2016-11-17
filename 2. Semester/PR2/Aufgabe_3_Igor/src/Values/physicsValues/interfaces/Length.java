package Values.physicsValues.interfaces;

import Values.physicsValues.enums.LengthUnit;

public abstract interface Length extends PhysicsScalar<Length>, Comparable<Length> {
    // SELECTORS
    public abstract double value(LengthUnit value);
    
    public abstract double m();
    
    public abstract double cm();
    
    public abstract double mm();
    
    public abstract double nm();
    
    public abstract double km();
    
    // OPERATIONS
    public abstract TimeDiff div(Speed value);
    
    public abstract Speed div(TimeDiff value);
}