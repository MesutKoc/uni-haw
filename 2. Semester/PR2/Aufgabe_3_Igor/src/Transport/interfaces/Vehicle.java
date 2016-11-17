package Transport.interfaces;

import Utils.interfaces.Mutable;
import Transport.aspects.interfaces.MyComparable;
import Transport.aspects.interfaces.WithPosition;
import Transport.aspects.interfaces.WithPower;
import Transport.aspects.interfaces.WithPropulsion;
import Transport.aspects.interfaces.WithUniqueId;

public interface Vehicle extends Mutable, Body, WithUniqueId, WithPosition, WithPower, WithPropulsion, MyComparable<Vehicle> {
    // ...
}
