package units.interfaces;

import units.enums.TimeDiffUnit;

public abstract interface TimeDiff extends Scalar<TimeDiff> {
    // SELECTORS
    public abstract double value(TimeDiffUnit value);
            
    public abstract double s();
    
    public abstract double ms();
    
    public abstract double ns();
    
    public abstract double m();
    
    public abstract double h();
    
    // OPERATIONS
    public abstract Length mul(Speed value);
    
    public abstract Speed mul(Acc value);
}