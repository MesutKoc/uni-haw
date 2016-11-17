package Stowage;

import Stowage.interfaces.Container;
import Stowage.interfaces.ContainerStowage;
import Values.adminValues.interfaces.StowageLocation;
import Values.physicsValues.Values;
import Values.physicsValues.interfaces.BoundingBox;
import Values.physicsValues.interfaces.Mass;
import java.util.Collection;
import java.util.Set;

public class ContainerStowageImpl implements ContainerStowage {
    // ATTRIBUTES
    private final Bounded3DimStackImpl<Container> containerStowage;
    
    // CREATION
    private ContainerStowageImpl(int bays, int rows, int tiers) {
        this.containerStowage = Bounded3DimStackImpl.valueOf(Container.class,bays,rows,tiers);
    }
    
    public static ContainerStowage valueOf(int bays, int rows, int tiers) {
        return new ContainerStowageImpl(bays, rows, tiers);
    }
    
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
        return (Container)containerStowage.get(loc);
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
        for (Container container : coll) {
            this.load(container);
        }
    }
    
    @Override
    public Mass emptyMass() {
        return Values.massInKg(0.0);
    }
    
    @Override
    public Mass maxMass() {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public Mass mass() {
        Mass result = emptyMass();
        
        Set<Container> coll = this.getAll();
        for (Container elem : coll) {
            if (!elem.isBlocked())
                result.add(elem.mass());
        }
        return result;
    }
    
    @Override
    public BoundingBox boundingBox() {
        BoundingBox boundingBox = Stowage.Entities.nullContainer().boundingBox();
        return Values.boundingBox(boundingBox.width().mul(containerStowage.bays()),
                boundingBox.length().mul(containerStowage.rows()),
                boundingBox.height().mul(containerStowage.tiers()));
    }
}