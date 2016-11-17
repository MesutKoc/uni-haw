package Stowage.interfaces;

import Utils.interfaces.Mutable;
import Values.adminValues.interfaces.StowageLocation;

public interface WithStowLoc<S extends Stowage<?>> extends Mutable {
    // SELECTORS
    public StowageLocation loc();
    public void setLocNull();
    public void setLoc(S stowage, StowageLocation loc);
}
