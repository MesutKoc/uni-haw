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
import Transport.interfaces.ContainerTruck;
import Transport.interfaces.ShipEngine;
import Transport.interfaces.Vehicle;
import java.util.Collection;
import java.util.Set;

public class ContainerTruckImpl extends AbstractContainerCar implements ContainerTruck {
    // CREATION
    private ContainerTruckImpl(ShipEngine engine, Speed maxSpeed, Mass emptyMass, int bays, int rows, int tiers) {
        super(engine, maxSpeed, emptyMass, bays, rows, tiers);
    }
    
    public static ContainerTruck valueOf(ShipEngine engine, Speed maxSpeed, Mass emptyMass, int bays, int rows, int tiers) {
        return new ContainerTruckImpl(engine, maxSpeed, emptyMass, bays, rows, tiers);
    }
    
    // OPERATIONS
    @Override
    protected void reset() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Mass mass() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public BoundingBox boundingBox() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public UniqueId id() {
        throw new UnsupportedOperationException("Not supported yet."); 
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

    @Override
    public int compareTo(Vehicle o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Mass emptyMass() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Mass maxMass() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean isFull() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean contains(Object elem) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Container get(StowageLocation loc) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Set<Container> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public StowageLocation locationOf(Container elem) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void load(int bayNo, int rowNo, Container elem) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void load(Container elem) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void loadAll(Collection<Container> coll) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}