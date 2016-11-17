package Values.physicsValues;

import Values.physicsValues.interfaces.Force;
import Values.physicsValues.interfaces.Acc;
import Values.physicsValues.interfaces.Mass;

import Values.physicsValues.enums.ForceUnit;

final class ForceInN extends AbstractPhysicsScalar<Force> implements Force {

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