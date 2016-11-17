package units;

import units.interfaces.Force;
import units.interfaces.Acc;
import units.interfaces.Mass;

import units.enums.ForceUnit;

final class ForceInN extends AbstractScalar<Force> implements Force {

    // CREATION
    private ForceInN(double valueInN) {
        super(valueInN);
    }
    
    public static ForceInN valueOf(double valueInN) {
        return Values.checkInvariant(new ForceInN(valueInN));
    }
    
    public Force newInstance(double valueInN) {
        return valueOf(valueInN);
    }
    
    // SELECTORS
    public double value(ForceUnit unit) {
        return value() / unit.multiplier();
    }
    
    public double n() {
        return value(ForceUnit.NEWTON);
    }
    
    public double kn() {
        return value(ForceUnit.KILONEWTON);
    }
    
    // OPERATIONS 
    public Acc div(Mass arg) {
        return AccInMPerS2.valueOf(value() / arg.value());
    }
    
    // CONVERSIONS
    @Override
    public String toString() {
        return "ForceInN{" + "valueInN=" + value() + '}';
    }
}
