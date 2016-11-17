package Values.physicsValues;

import Values.physicsValues.interfaces.Length;
import Values.physicsValues.interfaces.Speed;
import Values.physicsValues.interfaces.TimeDiff;

import Values.physicsValues.enums.LengthUnit;

final class LengthInM extends AbstractPhysicsScalar<Length> implements Length {
    
    // CREATION
    private LengthInM(double valueInM) {
        super(valueInM);
    }
    
    public static LengthInM valueOf(double valueInM) {
        return Values.checkInvariant(new LengthInM(valueInM));
    }
    
    public Length newInstance(double valueInM) {
        return valueOf(valueInM);
    }
    
    // SELECTORS
    public double value(LengthUnit unit) {
        return value() / unit.multiplier();
    }
    
    public double m() {
        return value(LengthUnit.METRE);
    }
    
    public double cm() {
        return value(LengthUnit.CENTIMETRE);
    }
    
    public double mm() {
        return value(LengthUnit.MILLIMETRE);
    }
    
    public double nm() {
        return value(LengthUnit.NANOMETRE);
    }
    
    public double km() {
        return value(LengthUnit.KILOMETRE);
    }
    
    // OPERATIONS
    public TimeDiff div(Speed arg) {
        return TimeDiffInS.valueOf(value() / arg.value());
    }
  
    public Speed div(TimeDiff arg) {
        return SpeedInMPerS.valueOf(value() / arg.value());
    }
    
    // CONVERSIONS
    @Override
    public String toString() {
        return "LengthInM{" + "valueInM=" + value() + "}";
    }
}