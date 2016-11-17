package Transport.interfaces;

import Transport.aspects.interfaces.WithBoundingBox;
import Transport.aspects.interfaces.WithMass;

public interface Body extends PhysObject, WithMass, WithBoundingBox {
    // ...
}