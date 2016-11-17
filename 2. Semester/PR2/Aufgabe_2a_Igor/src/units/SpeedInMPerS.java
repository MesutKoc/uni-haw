package units;

import units.interfaces.Speed;
import units.interfaces.Length;
import units.interfaces.Power;
import units.interfaces.TimeDiff;
import units.interfaces.Force;
import units.interfaces.Acc;

import units.enums.SpeedUnit;

final class SpeedInMPerS extends AbstractScalar<Speed> implements Speed {
    
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
    
    @Override
    public Force forceDrag(Speed speedMax, Power powerMax) {
        return ForceInN.valueOf(powerMax.value() / Math.pow(speedMax.value(), 2.0));
    }
    
    @Override
    public Acc curveAcc(Length curveRadius) {
        return AccInMPerS2.valueOf(Math.pow(this.value(), 2) / curveRadius.value());
    }
    
    // CONVERSIONS
    @Override
    public String toString() {
        return "SpeedInMPerS{" + "valueInMPerS=" + value() + '}';
    }
}