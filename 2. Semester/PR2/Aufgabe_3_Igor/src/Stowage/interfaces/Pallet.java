package Stowage.interfaces;

import Utils.interfaces.Immutable;
import Transport.aspects.interfaces.MyComparable;
import Transport.aspects.interfaces.WithUniqueId;
import Transport.interfaces.Body;

public interface Pallet extends Immutable, Body, WithUniqueId, WithStowLoc<Stowage<Pallet>>, WithForm, MyComparable<Pallet> {
    
}