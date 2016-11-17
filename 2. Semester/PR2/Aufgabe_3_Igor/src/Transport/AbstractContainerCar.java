package Transport;

import Stowage.interfaces.Bounded3DimStack;
import Values.physicsValues.interfaces.Mass;
import Values.physicsValues.interfaces.Speed;
import Transport.interfaces.ShipEngine;

public abstract class AbstractContainerCar {
    // ATTRIBUTES
    private final ShipEngine engine;
    private final Speed maxSpeed;
    private final Mass emptyMass;
    protected final int bays;
    protected final int rows;
    protected final int tiers;
    private final Bounded3DimStack containerStowage;
    
    // CREATION
    protected AbstractContainerCar(ShipEngine engine, Speed maxSpeed, Mass emptyMass, int bays, int rows, int tiers) {
        this.engine = engine;
        this.maxSpeed = maxSpeed;
        this.emptyMass = emptyMass;
        this.bays = bays;
        this.rows = rows;
        this.tiers = tiers;
        this.containerStowage = Stowage.Entities.containerStowage(bays, rows, tiers);
        reset();
    }
    
    // OPERATIONS
    protected abstract void reset();
}