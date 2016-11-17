package Transport.interfaces;

import Utils.interfaces.Mutable;
import Transport.aspects.interfaces.WithPower;
import Transport.aspects.interfaces.WithUniqueId;

public interface ShipEngine extends Mutable, Body, WithUniqueId, WithPower {
    // ...
}