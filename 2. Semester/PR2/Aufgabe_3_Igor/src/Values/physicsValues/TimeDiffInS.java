package Values.physicsValues;

import Values.physicsValues.interfaces.TimeDiff;
import Values.physicsValues.interfaces.Acc;
import Values.physicsValues.interfaces.Length;
import Values.physicsValues.interfaces.Speed;

import Values.physicsValues.enums.TimeDiffUnit;

final class TimeDiffInS extends AbstractPhysicsScalar<TimeDiff> implements TimeDiff {
    
    // CREATION
    private TimeDiffInS(double valueInS) {
        super(valueInS);
    }
    
    public static TimeDiffInS valueOf(double valueInS) {
        return Values.checkInvariant(new TimeDiffInS(valueInS));
    }
    
    public TimeDiff newInstance(double valueInS) {
        return valueOf(valueInS);
    }
    
    // SELECTORS
    public double value(TimeDiffUnit unit) {
        return value() / unit.multiplier();
    }
    
    public double s() {
        return value(TimeDiffUnit.SECOND);
    }
    
    public double ms() {
        return value(TimeDiffUnit.MILLISECOND);
    }
    
    public double ns() {
        return value(TimeDiffUnit.NANOSECOND);
    }
    
    public double m() {
        return value(TimeDiffUnit.MINUTE);
    }
    
    public double h() {
        return value(TimeDiffUnit.HOUR);
    }
    
    // OPERATIONS
    public Length mul(Speed arg) {
        return LengthInM.valueOf(value() * arg.value());
    }
  
    public Speed mul(Acc arg) {
        return SpeedInMPerS.valueOf(value() * arg.value());
    }
    
    // CONVERSIONS
    @Override
    public String toString() {
        return "TimeDiffInS{" + "valueInS=" + value() + '}';
    }
}