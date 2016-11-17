package units.interfaces;

import units.enums.SpeedUnit;

public abstract interface Speed extends Scalar<Speed> {
    // SELECTORS
    public abstract double value(SpeedUnit value);
    
    public abstract double mPerS();
    
    public abstract double kmPerH();
    
    // OPERATIONS
    public abstract Length mul(TimeDiff value);
    
    public abstract Force forceDrag(Speed speedMax, Power powerMax);
    
    public abstract Acc curveAcc(Length curveRadius);
}