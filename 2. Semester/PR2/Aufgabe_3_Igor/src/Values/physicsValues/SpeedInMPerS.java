package Values.physicsValues;

import Values.physicsValues.interfaces.Speed;
import Values.physicsValues.interfaces.Length;
import Values.physicsValues.interfaces.Power;
import Values.physicsValues.interfaces.TimeDiff;

import Values.physicsValues.enums.SpeedUnit;

final class SpeedInMPerS extends AbstractPhysicsScalar<Speed> implements Speed {
    
    // CREATION
    private SpeedInMPerS(double valueInMPerS) {
        super(valueInMPerS);
    }
    
    public static SpeedInMPerS valueOf(double valueInMPerS) {
        return Values.checkInvariant(new SpeedInMPerS(valueInMPerS));
    }
    
    public Speed newInstance(double valueInMPerS) {
        return valueOf(valueInMPerS);
    }
    
    // SELECTORS
    public double value(SpeedUnit unit) {
        return value() / unit.multiplier();
    }
    
    public double mPerS() {
        return value(SpeedUnit.METERPERSECOND);
    }
    
    public double kmPerH() {
        return value(SpeedUnit.KILOMETREPERHOUR);
    }

    // OPERATIONS
    public Length mul(TimeDiff arg) {
        return LengthInM.valueOf(value() * arg.value());
    }
  
    public ForceInN forceDrag(Speed speedMax, Power powerMax) {
        return ForceInN.valueOf(powerMax.value() / Math.pow(speedMax.value(), 2.0));
    }
    
    public AccInMPerS2 curveAcc(Length curveRadius, Speed speed) {
        return AccInMPerS2.valueOf(Math.pow(speed.value(), 2) / curveRadius.value());
    }
    
    // CONVERSIONS
    @Override
    public String toString() {
        return "SpeedInMPerS{" + "valueInMPerS=" + value() + '}';
    }
}