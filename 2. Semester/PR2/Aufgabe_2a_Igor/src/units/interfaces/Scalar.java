package units.interfaces;

public abstract interface Scalar<T extends Scalar> extends Value<T> {
    // CREATION
    @Override
    default boolean invariant() {
        return !(Double.valueOf(value()).isInfinite() || Double.valueOf(value()).isNaN());
    }

    // SELECTORS
    public abstract double value();
    
//    default double value() {
//        return valueSI();
//    }
//    default double value(Unit unit) {
//        return value() / unit.multiplier();
//    }
}
