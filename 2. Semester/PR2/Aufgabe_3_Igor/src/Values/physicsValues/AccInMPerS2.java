package Values.physicsValues;

import Values.physicsValues.interfaces.Acc;
import Values.physicsValues.interfaces.Force;
import Values.physicsValues.interfaces.Mass;
import Values.physicsValues.interfaces.Speed;
import Values.physicsValues.interfaces.TimeDiff;

import Values.physicsValues.enums.AccUnit;

final class AccInMPerS2 extends AbstractPhysicsScalar<Acc> implements Acc {    
    
    // CREATION
    private AccInMPerS2(double valueInMPerS2) {
        super(valueInMPerS2);
    }
    
    public static AccInMPerS2 valueOf(double valueInMPerS2) {
        return Values.checkInvariant(new AccInMPerS2(valueInMPerS2));
    }
    
    public Acc newInstance(double valueInMPerS2) {
        return valueOf(valueInMPerS2);
    }
    
    // SELECTORS
    public double value(AccUnit unit) {
        return value() / unit.multiplier();
    }
  
    public double mPerS2() {
        return value(AccUnit.METERPERSECOND2);
    }
  
    public double kmPerH2() {
        return value(AccUnit.KILOMETREPERHOUR2);
    }
    
    // OPERATIONS
    public Force mul(Mass arg) {
        return ForceInN.valueOf(value() * arg.value());
    }
  
    public Speed mul(TimeDiff arg) {
        return SpeedInMPerS.valueOf(value() * arg.value());
    }
    
    // CONVERSIONS
    @Override
    public String toString() {
        return "AccInMPerS2{" + "valueInMPerS2=" + value() + '}';
    }
}