package Stowage.interfaces;

import Transport.aspects.interfaces.MyComparable;
import Transport.aspects.interfaces.WithUniqueId;

public interface Container extends Stowage<Pallet>, WithUniqueId, WithStowLoc<Stowage<Container>>, WithForm, MyComparable<Container> {
    // ...
}