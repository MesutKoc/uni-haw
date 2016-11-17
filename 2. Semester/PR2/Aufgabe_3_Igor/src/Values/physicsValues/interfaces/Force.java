package Values.physicsValues.interfaces;

import Values.physicsValues.enums.ForceUnit;

public abstract interface Force extends PhysicsScalar<Force>, Comparable<Force> {
    // SELECTORS
    public abstract double value(ForceUnit value);
    
    public abstract double n();
    
    public abstract double kn();
    
    // OPERATIONS
    public abstract Acc div(Mass value);
}