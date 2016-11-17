package Values.physicsValues;

import Values.physicsValues.interfaces.Mass;
import Values.physicsValues.interfaces.Acc;
import Values.physicsValues.interfaces.Force;

import Values.physicsValues.enums.MassUnit;

final class MassInKg extends AbstractPhysicsScalar<Mass> implements Mass {
    
    // CREATION
    private MassInKg(double valueInKg) {
        super(valueInKg);
    }
    
    public static MassInKg valueOf(double valueInKg) {
        return Values.checkInvariant(new MassInKg(valueInKg));
    }
    
    public Mass newInstance(double valueInKg) {
        return valueOf(valueInKg);
    }
    
    // SELECTORS
    public double value(MassUnit unit) {
        return value() / unit.multiplier();
    }
    
    public double kg() {
        return value(MassUnit.KILOGRAM);
    }
    
    public double cg() {
        return value(MassUnit.CENTIGRAM);
    }
    
    public double mg() {
        return value(MassUnit.MILLIGRAM);
    }
    
    public double ng() {
        return value(MassUnit.NANOGRAM);
    }
    
    public double pg() {
        return value(MassUnit.PICOGRAM);
    }
    
    public double g() {
        return value(MassUnit.GRAM);
    }
    
    // OPERATIONS
    public Force mul(Acc arg) {
        return ForceInN.valueOf(value() * arg.value());
    }
    
    // CONVERSIONS
    @Override
    public String toString() {
        return "MassInKg{" + "valueInKg=" + value() + '}';
    }
}