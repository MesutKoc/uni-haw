package Values.physicsValues.interfaces;

import Values.physicsValues.enums.AccUnit;

public abstract interface Acc extends PhysicsScalar<Acc>, Comparable<Acc> {
    // SELECTORS
    public abstract double value(AccUnit value);
  
    public abstract double mPerS2();
  
    public abstract double kmPerH2();
    
    // OPERATIONS
    public abstract Force mul(Mass value);
  
    public abstract Speed mul(TimeDiff value);
}