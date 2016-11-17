package units;

import units.interfaces.Angle;

import units.enums.AngleUnit;

final class AngleInRad extends AbstractScalar<Angle> implements Angle {

    // CREATION
    private AngleInRad(double valueInRad) {
        super(valueInRad);
    }

    public static AngleInRad valueOf(double valueInRad) {
        return Values.checkInvariant(new AngleInRad(valueInRad));
    }
    
    public Angle newInstance(double valueInRad) {
        return valueOf(valueInRad);
    }
    
    // SELECTORS
    public double value(AngleUnit unit) {
        return value() / unit.multiplier();
    }
    
    public double rad() {
        return value(AngleUnit.RADIAN);
    }
    
    public double deg() {
        return value(AngleUnit.DEGREES);
    }
    
    public double tur() {
        return value(AngleUnit.TURNS);
    }
    
    // CONVERSIONS
    @Override
    public String toString() {
        return "AngleInRad{" + "valueInRad=" + value() + '}';
    }
}