package Values.physicsValues;

import Utils.InvariantViolatedException;
import Values.physicsValues.enums.*;
import static Values.physicsValues.enums.AccUnit.*;
import static Values.physicsValues.enums.AngleUnit.*;
import static Values.physicsValues.enums.ForceUnit.*;
import static Values.physicsValues.enums.LengthUnit.*;
import static Values.physicsValues.enums.MassUnit.*;
import static Values.physicsValues.enums.PowerUnit.*;
import static Values.physicsValues.enums.SpeedUnit.*;
import static Values.physicsValues.enums.TimeDiffUnit.*;
import Values.physicsValues.interfaces.*;

public final class Values {
    // UTILITY CLASS
    public static final Acc accEarth = acc(9.798d);
    
    private Values() {
        // ...
    }
    
    // OPERATIONS
    public static <T extends PhysicsScalar> T checkInvariant(T instance) throws InvariantViolatedException {
        if(!instance.invariant())
            throw new InvariantViolatedException();
        return instance;
    }
    
    // TimeDiff
    public static TimeDiff timeDiff(double value) {
        return timeDiffInS(value);
    }
    public static TimeDiff timeDiffInH(double value) {
        return timeDiff(value, HOUR);
    }
    public static TimeDiff timeDiffInM(double value) {
        return timeDiff(value, MINUTE);
    }
    public static TimeDiff timeDiffInNs(double value) {
        return timeDiff(value, NANOSECOND);
    }
    public static TimeDiff timeDiffInMs(double value) {
        return timeDiff(value, MILLISECOND);
    }
    public static TimeDiff timeDiffInS(double value) {
        return timeDiff(value, SECOND);
    }
    public static TimeDiff timeDiff(double value, TimeDiffUnit unit) {
        return TimeDiffInS.valueOf(value * unit.multiplier());
    }

    // Length
    public static Length length(double value) {
        return lengthInM(value);
    }
    public static Length lengthInKm(double value) {
        return length(value, KILOMETRE);
    }
    public static Length lengthInNm(double value) {
        return length(value, NANOMETRE);
    }
    public static Length lengthInMm(double value) {
        return length(value, MILLIMETRE);
    }
    public static Length lengthInCm(double value) {
        return length(value, CENTIMETRE);
    }
    public static Length lengthInM(double value) {
        return length(value, METRE);
    }
    public static Length length(double value, LengthUnit unit) {
        return LengthInM.valueOf(value * unit.multiplier());
    }

    // Mass
    public static Mass mass(double value) {
        return massInKg(value);
    }
    public static Mass massInG(double value) {
        return mass(value, GRAM);
    }
    public static Mass massInPg(double value) {
        return mass(value, PICOGRAM);
    }
    public static Mass massInNg(double value) {
        return mass(value, NANOGRAM);
    }
    public static Mass massInMg(double value) {
        return mass(value, MILLIGRAM);
    }
    public static Mass massInCg(double value) {
        return mass(value, CENTIGRAM);
    }
    public static Mass massInKg(double value) {
        return mass(value, KILOGRAM);
    }
    public static Mass mass(double value, MassUnit unit) {
        return MassInKg.valueOf(value * unit.multiplier());
    }

    // Angle
    public static Angle angle(double value) {
        return angleInRad(value);
    }
    public static Angle angleInTur(double value) {
        return angle(value, TURNS);
    }
    public static Angle angleInDeg(double value) {
        return angle(value, DEGREES);
    }
    public static Angle angleInRad(double value) {
        return angle(value, RADIAN);
    }
    public static Angle angle(double value, AngleUnit unit) {
        return AngleInRad.valueOf(value * unit.multiplier());
    }

    // Speed
    public static Speed speed(double value) {
        return speedInMPerS(value);
    }
    public static Speed speedInKmPerH(double value) {
        return speed(value, KILOMETREPERHOUR);
    }
    public static Speed speedInMPerS(double value) {
        return speed(value, METERPERSECOND);
    }
    public static Speed speed(double value, SpeedUnit unit) {
        return SpeedInMPerS.valueOf(value * unit.multiplier());
    }

    // Acc
    public static Acc acc(double value) {
        return accInMPerS2(value);
    }
    public static Acc accInKmPerH2(double value) {
        return acc(value, KILOMETREPERHOUR2);
    }
    public static Acc accInMPerS2(double value) {
        return acc(value, METERPERSECOND2);
    }
    public static Acc acc(double value, AccUnit unit) {
        return AccInMPerS2.valueOf(value * unit.multiplier());
    }

    // Force
    public static Force force(double value) {
        return forceInN(value);
    }
    public static Force forceInKn(double value) {
        return force(value, KILONEWTON);
    }
    public static Force forceInN(double value) {
        return force(value, NEWTON);
    }
    public static Force force(double value, ForceUnit unit) {
        return ForceInN.valueOf(value * unit.multiplier());
    }

    // Power
    public static Power power(double value) {
        return powerInW(value);
    }
    public static Power powerInPs(double value) {
        return power(value, HORSEPOWER);
    }
    public static Power powerInKw(double value) {
        return power(value, KILOWATT);
    }
    public static Power powerInW(double value) {
        return power(value, WATT);
    }
    public static Power power(double value, PowerUnit unit) {
        return PowerInW.valueOf(value * unit.multiplier());
    }
    
    // BoundingBox
    public static BoundingBox boundingBox(double length, double width, double height) {
        return boundingBox(length(length), length(width), length(height));
    }
    
    public static BoundingBox boundingBox(Length length, Length width, Length height) {
        return BoundingBoxImpl.valueOf(length, width, height);
    }
}