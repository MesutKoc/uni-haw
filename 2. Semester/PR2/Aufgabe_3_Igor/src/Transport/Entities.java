package Transport;

import Values.physicsValues.interfaces.BoundingBox;
import Values.physicsValues.interfaces.Mass;
import Values.physicsValues.interfaces.Power;
import Values.physicsValues.interfaces.Speed;
import Transport.interfaces.ContainerShip;
import Transport.interfaces.ContainerTruck;
import Transport.interfaces.XXXClassContainerShip;
import Transport.interfaces.ShipEngine;
import Transport.interfaces.ShipHull;
import Transport.interfaces.VanCarrier;

public final class Entities {
    // UTILITY CLASS
    private Entities() {
        // ...
    }
    
    // OPERATIONS
    public static ContainerTruck containerTruck(ShipEngine engine, Speed maxSpeed, Mass emptyMass, int bays, int rows, int tiers) {
        return ContainerTruckImpl.valueOf(engine, maxSpeed, emptyMass, bays, rows, tiers);
    }
    
    public static VanCarrier vanCarrierTruck(ShipEngine engine, Speed maxSpeed, Mass emptyMass, int bays, int rows, int tiers) {
        return VanCarrierImpl.valueOf(engine, maxSpeed, emptyMass, bays, rows, tiers);
    }
    
    public static ShipEngine shipEngine(BoundingBox boundingBox, Power maxPower) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static ShipHull shipHull(BoundingBox boundingBox) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static ContainerShip containerShip(ShipHull hull, ShipEngine engine, Speed maxSpeed, int bays, int rows, int tiers) {
        return ContainerShipImpl.valueOf(hull, engine, maxSpeed, bays, rows, tiers);
    }
    
    public static XXXClassContainerShip XXXTypeShip(ShipHull hull, ShipEngine engine, Speed maxSpeed, int bays, int rows, int tiers) {
        return ShellClassContainerShipImpl.valueOf(hull, engine, maxSpeed, bays, rows, tiers);
    }
}