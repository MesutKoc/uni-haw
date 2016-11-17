package units;

import units.interfaces.Acc;
import units.interfaces.Force;
import units.interfaces.Mass;
import units.interfaces.Speed;
import units.interfaces.TimeDiff;

import units.enums.AccUnit;

final class AccInMPerS2 extends AbstractScalar<Acc> implements Acc {    
    
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