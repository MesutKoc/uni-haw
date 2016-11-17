package Transport;

import Stowage.interfaces.Container;
import Stowage.interfaces.ContainerStowage;
import static Values.adminValues.Values.uniqueId;
import Values.adminValues.interfaces.StowageLocation;
import Values.adminValues.interfaces.UniqueId;
import Values.physicsValues.interfaces.BoundingBox;
import Values.physicsValues.interfaces.Length;
import Values.physicsValues.interfaces.Mass;
import Values.physicsValues.interfaces.Power;
import Values.physicsValues.interfaces.Speed;
import Values.physicsValues.interfaces.TimeDiff;
import static Stowage.Entities.containerStowage;
import Transport.interfaces.ContainerShip;
import Transport.interfaces.ShipEngine;
import Transport.interfaces.ShipHull;
import Values.physicsValues.Values;
import java.util.Collection;
import java.util.Set;

public class ContainerShipImpl extends AbstractShip implements ContainerShip {
    // ATTRIBUTES
    private final UniqueId id;
    private final ContainerStowage containerStowage;
    
    // CREATION
    private ContainerShipImpl(ShipHull hull, ShipEngine engine, Speed maxSpeed, ContainerStowage containerStowage) {
        super(hull, engine, maxSpeed);
        this.id = uniqueId();
        this.containerStowage = containerStowage;
    }
    
    public static ContainerShip valueOf(ShipHull hull, ShipEngine engine, Speed maxSpeed, ContainerStowage containerStowage) {
        return new ContainerShipImpl(hull, engine, maxSpeed, containerStowage);
    }
    
    public static ContainerShip valueOf(ShipHull hull, ShipEngine engine, Speed maxSpeed, int bays, int rows, int tiers) {
        return new ContainerShipImpl(hull, engine, maxSpeed, containerStowage(bays, rows, tiers));
    }
    
    // OPERATIONS
    @Override
    public boolean isEmpty() {
        return containerStowage.isEmpty();
    }

    @Override
    public boolean isFull() {
        return containerStowage.isFull();
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        return containerStowage.tierIsEmpty(bay, row);
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        return containerStowage.tierIsFull(bay, row);
    }

    @Override
    public boolean contains(Object elem) {
        return containerStowage.contains(elem);
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return containerStowage.containsAll(coll);
    }

    @Override
    public Container get(StowageLocation loc) {
        return containerStowage.get(loc);
    }

    @Override
    public Set<Container> getAll() {
        return containerStowage.getAll();
    }

    @Override
    public StowageLocation locationOf(Container elem) {
        return containerStowage.locationOf(elem);
    }

    @Override
    public void load(int bayNo, int rowNo, Container elem) {
        containerStowage.load(bayNo, rowNo, elem);
    }

    @Override
    public void load(Container elem) {
        containerStowage.load(elem);
    }

    @Override
    public void loadAll(Collection<Container> coll) {
        containerStowage.loadAll(coll);
    }
    
    @Override
    public Mass mass() {
        Mass result = emptyMass().add(containerStowage.mass());

        return result; 
    }
    
    @Override
    public Mass emptyMass() {
        return Values.massInKg(55000.0); 
    }

    @Override
    public Mass maxMass() {
        return Values.massInKg(emptyMass().value() + 155000.0); 
    }

    @Override
    public BoundingBox boundingBox() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public UniqueId id() {
        return id;
    }

    @Override
    public Length pos() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Power power() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Power maxPower() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setLevel(double level) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void moveStep(TimeDiff deltaTime) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}