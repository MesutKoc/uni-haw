package units.interfaces;

import units.enums.ForceUnit;

public abstract interface Force extends Scalar<Force> {
    // SELECTORS
    public abstract double value(ForceUnit value);
    
    public abstract double n();
    
    public abstract double kn();
    
    // OPERATIONS
    public abstract Acc div(Mass value);
}