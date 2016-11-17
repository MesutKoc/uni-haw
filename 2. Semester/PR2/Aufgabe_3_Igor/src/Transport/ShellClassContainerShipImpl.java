package Transport;

import Stowage.interfaces.Container;
import Values.adminValues.interfaces.StowageLocation;
import Values.adminValues.interfaces.UniqueId;
import Values.physicsValues.interfaces.BoundingBox;
import Values.physicsValues.interfaces.Length;
import Values.physicsValues.interfaces.Mass;
import Values.physicsValues.interfaces.Power;
import Values.physicsValues.interfaces.Speed;
import Values.physicsValues.interfaces.TimeDiff;
import Transport.interfaces.ContainerShip;
import Transport.interfaces.XXXClassContainerShip;
import Transport.interfaces.ShipEngine;
import Transport.interfaces.ShipHull;
import java.util.Collection;
import java.util.Set;

public class ShellClassContainerShipImpl implements XXXClassContainerShip {
    // ATTRIBUTES
    private final ContainerShip containerShip;
    
    // CREATION
    private ShellClassContainerShipImpl(ShipHull hull, ShipEngine engine, Speed maxSpeed, int bays, int rows, int tiers) {
        this.containerShip = Entities.containerShip(hull, engine, maxSpeed, bays, rows, tiers);
    }
    
    public static XXXClassContainerShip valueOf(ShipHull hull, ShipEngine engine, Speed maxSpeed, int bays, int rows, int tiers) {
        return new ShellClassContainerShipImpl(hull, engine, maxSpeed, bays, rows, tiers);
    }
    
    @Override
    public Mass mass() {
        return containerShip.mass();
    }

    @Override
    public BoundingBox boundingBox() {
        return containerShip.boundingBox();
    }

    @Override
    public UniqueId id() {
        return containerShip.id();
    }

    @Override
    public Length pos() {
        return containerShip.pos();
    }

    @Override
    public Power power() {
        return containerShip.power();
    }

    @Override
    public Power maxPower() {
        return containerShip.maxPower();
    }

    @Override
    public void setLevel(double level) {
        containerShip.setLevel(level);
    }

    @Override
    public void moveStep(TimeDiff deltaTime) {
        containerShip.moveStep(deltaTime); 
    }

    @Override
    public Mass emptyMass() {
        return containerShip.emptyMass();
    }

    @Override
    public Mass maxMass() {
        return containerShip.maxMass();
    }

    @Override
    public boolean isEmpty() {
        return containerShip.isEmpty();
    }

    @Override
    public boolean isFull() {
        return containerShip.isFull();
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        return containerShip.tierIsEmpty(bay, row);
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        return containerShip.tierIsFull(bay, row);
    }

    @Override
    public boolean contains(Object elem) {
        return containerShip.contains(elem);
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return containerShip.containsAll(coll);
    }

    @Override
    public Container get(StowageLocation loc) {
        return containerShip.get(loc);
    }

    @Override
    public Set<Container> getAll() {
        return containerShip.getAll();
    }

    @Override
    public StowageLocation locationOf(Container elem) {
        return containerShip.locationOf(elem);
    }

    @Override
    public void load(int bayNo, int rowNo, Container elem) {
        containerShip.load(bayNo, rowNo, elem);
    }

    @Override
    public void load(Container elem) {
        containerShip.load(elem);
    }

    @Override
    public void loadAll(Collection<Container> coll) {
        containerShip.loadAll(coll);
    }
}