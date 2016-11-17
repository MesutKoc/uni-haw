package Stowage.interfaces;

import Values.physicsValues.interfaces.Mass;
import Utils.interfaces.Immutable;

public interface WithCargo extends Immutable {
    // SELECTORS
    public Mass emptyMass();
    public Mass maxMass();
    
    // PREDICATES
    public boolean isEmpty();
    public boolean isFull();
}
