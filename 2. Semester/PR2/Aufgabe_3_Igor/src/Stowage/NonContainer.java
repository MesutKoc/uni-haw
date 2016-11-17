package Stowage;

import Stowage.interfaces.Container;
import Stowage.interfaces.Pallet;
import Stowage.interfaces.Stowage;
import static Values.adminValues.Values.uniqueId;
import Values.adminValues.interfaces.StowageLocation;
import Values.adminValues.interfaces.UniqueId;
import static Values.physicsValues.Values.lengthInM;
import Values.physicsValues.Values;
import Values.physicsValues.interfaces.BoundingBox;
import Values.physicsValues.interfaces.Mass;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class NonContainer extends AbstractContainer {
    // ATTRIBUTES
    private final UniqueId id;
    
    // CREATION
    private NonContainer() {
        this.id = uniqueId();
    }
    
    private static Container instance = null;
    public static Container valueOf() {
        if (instance == null)
            instance = new NonContainer();
        
        return instance;
    }
    
    // OPERATIONS
    @Override
    public Mass emptyMass() {
        return Values.mass(0.0);
    }

    @Override
    public Mass maxMass() {
        return Values.mass(0.0);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return true;
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean contains(Object elem) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return false;
    }

    @Override
    public Pallet get(StowageLocation loc) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Set<Pallet> getAll() {
        return new HashSet<>();
    }

    @Override
    public StowageLocation locationOf(Pallet elem) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void load(int bayNo, int rowNo, Pallet elem) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void load(Pallet elem) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void loadAll(Collection<Pallet> coll) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public UniqueId id() {
        return id;
    }

    @Override
    public StowageLocation loc() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setLocNull() {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void setLoc(Stowage<Container> stowage, StowageLocation loc) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public boolean isBlocked() {
        return true;
    }

    @Override
    public Mass mass() {
        return Values.mass(0.0);
    }

    @Override
    public BoundingBox boundingBox() {
        return Values.boundingBox(lengthInM(5.89), lengthInM(2.35), lengthInM(2.39));
    }
}