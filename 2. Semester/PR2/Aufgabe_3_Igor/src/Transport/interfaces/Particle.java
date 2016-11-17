package Transport.interfaces;

import Utils.interfaces.Mutable;
import Transport.aspects.interfaces.WithMass;
import Transport.aspects.interfaces.WithPosition;
import Transport.aspects.interfaces.WithSpeed;

public interface Particle extends Mutable, PhysObject, WithMass, WithPosition, WithSpeed {
    // ...
}