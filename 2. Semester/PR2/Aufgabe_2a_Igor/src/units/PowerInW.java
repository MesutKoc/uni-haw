package units;

import units.interfaces.Power;
import units.interfaces.Force;
import units.interfaces.Speed;

import units.enums.PowerUnit;

final class PowerInW extends AbstractScalar<Power> implements Power {
    
    // CREATION
    private PowerInW(double valueInW) {
        super(valueInW);
    }
    
    public static PowerInW valueOf(double valueInW) {
        return Values.checkInvariant(new PowerInW(valueInW));
    }
    
    public Power newInstance(double valueInW) {
        return valueOf(valueInW);
    }
    
    // SELECTORS
    public double value(PowerUnit unit) {
        return value() / unit.multiplier();
    }
    
    public double w() {
        return value(PowerUnit.WATT);
    }
    
    public double kw() {
        return value(PowerUnit.KILOWATT);
    }
    
    public double ps() {
        return value(PowerUnit.HORSEPOWER);
    }
    
    // OPERATIONS
    public Force div(Speed arg) {
        return ForceInN.valueOf(value() / arg.value());
    }
        
    // CONVERSIONS
    @Override
    public String toString() {
        return "PowerInW{" + "valueInW=" + value() + '}';
    }
}
