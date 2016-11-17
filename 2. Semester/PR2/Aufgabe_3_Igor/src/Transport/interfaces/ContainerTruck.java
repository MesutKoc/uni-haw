package Transport.interfaces;

import Stowage.interfaces.ContainerStowage;
import Stowage.interfaces.WithCargo;

public interface ContainerTruck extends Vehicle, WithCargo, ContainerStowage {
    // ...
}