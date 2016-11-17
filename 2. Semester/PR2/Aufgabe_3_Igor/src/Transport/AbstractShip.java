package Transport;

import Values.physicsValues.interfaces.Speed;
import Transport.interfaces.Ship;
import Transport.interfaces.ShipEngine;
import Transport.interfaces.ShipHull;

public abstract class AbstractShip implements Ship {
    // ATTRIBUTES
    protected ShipHull hull;
    protected ShipEngine engine;
    protected Speed maxSpeed;
    
    // CREATION
    protected AbstractShip(ShipHull hull, ShipEngine engine, Speed maxSpeed) {
        this.hull = hull;
        this.engine = engine;
        this.maxSpeed = maxSpeed;
    }
}
