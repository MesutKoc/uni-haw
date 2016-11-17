package Stowage.interfaces;

import Utils.interfaces.Immutable;

public interface WithForm extends Immutable {
    // PREDICATES
    abstract public boolean isFree();
    abstract public boolean isBlocked();
    default boolean isOccupied() {
        return !isFree() && !isBlocked();
    }
}