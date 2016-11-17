package Stowage;

import Stowage.interfaces.Pallet;
import Stowage.interfaces.Stowage;
import static Values.adminValues.Values.uniqueId;
import Values.adminValues.interfaces.StowageLocation;
import Values.adminValues.interfaces.UniqueId;
import Values.physicsValues.Values;
import Values.physicsValues.interfaces.Mass;

public class NullPallet extends AbstractPallet {
    // ATTRIBUTES
    private final UniqueId id;
    
    // CREATION
    private NullPallet() {
        this.id = uniqueId();
    }
    
    private static Pallet instance = null;
    public static Pallet valueOf() {
        if (instance == null)
            instance = new NullPallet();
        
        return instance;
    }
    
    // OPERATIONS
    @Override
    public Mass mass() {
        return Values.mass(0.0);
    }

    @Override
    public UniqueId id() {
        return id;
    }

    @Override
    public StowageLocation loc() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setLocNull() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setLoc(Stowage<Pallet> stowage, StowageLocation loc) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }
}