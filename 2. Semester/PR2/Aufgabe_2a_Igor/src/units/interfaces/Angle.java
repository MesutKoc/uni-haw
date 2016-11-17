package units.interfaces;

import units.enums.AngleUnit;

public abstract interface Angle extends Scalar<Angle> {
    // SELECTORS
    public abstract double value(AngleUnit value);
    
    public abstract double rad();
    
    public abstract double deg();
    
    public abstract double tur();
}
