package Values.physicsValues.interfaces;

import Values.physicsValues.enums.AngleUnit;

public abstract interface Angle extends PhysicsScalar<Angle>, Comparable<Angle> {
    // SELECTORS
    public abstract double value(AngleUnit value);
    
    public abstract double rad();
    
    public abstract double deg();
    
    public abstract double tur();
}