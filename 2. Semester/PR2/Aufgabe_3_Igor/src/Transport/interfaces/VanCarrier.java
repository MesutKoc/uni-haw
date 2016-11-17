package Transport.interfaces;

import Stowage.interfaces.ContainerStowage;
import Stowage.interfaces.WithCargo;

public interface VanCarrier extends Vehicle, WithCargo, ContainerStowage {
    // ...
}