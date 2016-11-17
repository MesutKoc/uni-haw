package Values.physicsValues.interfaces;

import Values.physicsValues.enums.MassUnit;

public abstract interface Mass extends PhysicsScalar<Mass>, Comparable<Mass> {
    // SELECTORS
    public abstract double value(MassUnit value);
    
    public abstract double kg();
    
    public abstract double cg();
    
    public abstract double mg();
    
    public abstract double ng();
    
    public abstract double pg();
    
    public abstract double g();
      
    // OPERATIONS
    public abstract Force mul(Acc value);
}