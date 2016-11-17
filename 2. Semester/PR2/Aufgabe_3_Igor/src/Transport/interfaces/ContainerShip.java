package Transport.interfaces;

import Stowage.interfaces.ContainerStowage;
import Stowage.interfaces.WithCargo;

public interface ContainerShip extends Ship, WithCargo, ContainerStowage {
    // ...
}