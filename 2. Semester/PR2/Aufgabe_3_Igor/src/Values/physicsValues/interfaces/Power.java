package Values.physicsValues.interfaces;

import Values.physicsValues.enums.PowerUnit;

public abstract interface Power extends PhysicsScalar<Power>, Comparable<Power> {
    // SELECTORS
    public abstract double value(PowerUnit value);
    
    public abstract double w();
    
    public abstract double kw();
    
    public abstract double ps();
    
    // OPERATIONS
    public abstract Force div(Speed value);
}