package Values.physicsValues.interfaces;

import Values.physicsValues.enums.SpeedUnit;

public abstract interface Speed extends PhysicsScalar<Speed>, Comparable<Speed> {
    // SELECTORS
    public abstract double value(SpeedUnit value);
    
    public abstract double mPerS();
    
    public abstract double kmPerH();
    
    // OPERATIONS
    public abstract Length mul(TimeDiff value);
    
    public abstract Force forceDrag(Speed speedMax, Power powerMax);
    
    public abstract Acc curveAcc(Length curveRadius, Speed speed);
}