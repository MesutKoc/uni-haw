package Stowage;

import Stowage.interfaces.Container;
import Stowage.interfaces.Pallet;
import Stowage.interfaces.Stowage;
import static Values.adminValues.Values.nullStowageLocation;
import static Values.adminValues.Values.uniqueId;
import Values.adminValues.interfaces.StowageLocation;
import Values.adminValues.interfaces.UniqueId;
import Values.physicsValues.Values;
import static Values.physicsValues.Values.lengthInM;
import Values.physicsValues.interfaces.BoundingBox;
import Values.physicsValues.interfaces.Mass;
import java.util.Collection;
import java.util.Set;

public class ContainerImpl implements Container {
    // ATTRIBUTES
    private final UniqueId id;
    private StowageLocation loc;
    private Stowage<Container> stowage;
    private final Bounded3DimStackImpl<Pallet> palletStowage;
    
    // CREATION
    private ContainerImpl() {
        this.id = uniqueId();
        this.loc = nullStowageLocation();
        this.stowage = null;
        this.palletStowage = Bounded3DimStackImpl.valueOf(Pallet.class, 2, 4, 2);
    }
    
    public static Container valueOf() {
        return new ContainerImpl();
    }
    
    // OPERATIONS
    @Override
    public Mass emptyMass() {
        return Values.massInKg(2250.0);
    }

    @Override
    public Mass maxMass() {
        return Values.massInKg(emptyMass().value() + 28230.0);
    }

    @Override
    public boolean isEmpty() {
        return palletStowage.isEmpty();
    }

    @Override
    public boolean isFull() {
        return palletStowage.isFull();
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        return palletStowage.tierIsEmpty(bay, row);
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        return palletStowage.tierIsFull(bay, row);
    }

    @Override
    public boolean contains(Object elem) {
        return palletStowage.contains(elem);
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return palletStowage.containsAll(coll);
    }

    @Override
    public Pallet get(StowageLocation loc) {
        return palletStowage.get(loc);
    }

    @Override
    public Set<Pallet> getAll() {
        return palletStowage.getAll();
    }

    @Override
    public StowageLocation locationOf(Pallet elem) {
        return palletStowage.locationOf(elem);
    }

    @Override
    public void load(int bayNo, int rowNo, Pallet elem) {
        palletStowage.load(bayNo, rowNo, elem);
    }

    @Override
    public void load(Pallet elem) {
        palletStowage.load(elem);
    }

    @Override
    public void loadAll(Collection<Pallet> coll) {
        palletStowage.loadAll(coll);
    }

    @Override
    public UniqueId id() {
        return id;
    }

    @Override
    public StowageLocation loc() {
        return loc;
    }

    @Override
    public void setLocNull() {
        this.stowage = null;
        this.loc = nullStowageLocation();
    }

    @Override
    public void setLoc(Stowage<Container> stowage, StowageLocation loc) {
        this.stowage = stowage;
        this.loc = loc;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public Mass mass() {
        Mass result = emptyMass();
        // TODO: Add mass of loading
        return result;
    }

    @Override
    public BoundingBox boundingBox() {
        return Values.boundingBox(lengthInM(5.89), lengthInM(2.35), lengthInM(2.39));
    }
}