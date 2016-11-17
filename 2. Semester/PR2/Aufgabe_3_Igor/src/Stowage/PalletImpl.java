package Stowage;

import Stowage.interfaces.Pallet;
import Stowage.interfaces.Stowage;
import Values.adminValues.NullStowageLocation;
import static Values.adminValues.Values.uniqueId;
import Values.adminValues.interfaces.StowageLocation;
import Values.adminValues.interfaces.UniqueId;
import Values.physicsValues.interfaces.Mass;

public class PalletImpl extends AbstractPallet {
    // ATTRIBUTES
    private final Mass mass;
    private final UniqueId id;
    private StowageLocation loc;
    private Stowage<Pallet> stowage;
    
    // CREATION
    private PalletImpl(Mass mass) {
        this.mass = mass;
        this.id = uniqueId();
        this.loc = NullStowageLocation.valueOf();
    }
    
    public static Pallet valueOf(Mass mass) {
        return new PalletImpl(mass);
    }
    
    // OPERATIONS
    @Override
    public Mass mass() {
        return mass;
    }

    @Override
    public UniqueId id() {
        return id;
    }

    @Override
    public StowageLocation loc() {
        return loc;
    }

    @Override
    public void setLocNull() {
        this.loc = NullStowageLocation.valueOf();
    }

    @Override
    public void setLoc(Stowage<Pallet> stowage, StowageLocation loc) {
        this.stowage = stowage;
        this.loc = loc;
    }

    @Override
    public boolean isFree() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isBlocked() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}