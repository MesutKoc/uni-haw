package Stowage;

import Stowage.interfaces.Bounded3DimStack;
import Stowage.interfaces.Container;
import Stowage.interfaces.Pallet;
import Stowage.interfaces.WithStowLoc;
import Stowage.interfaces.WithForm;
import static Stowage.Entities.nullContainer;
import static Stowage.Entities.nullPallet;
import static Values.adminValues.Values.nullStowageLocation;
import static Values.adminValues.Values.stowageLocation;
import Values.adminValues.interfaces.StowageLocation;
import java.io.InvalidClassException;
import java.util.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class Bounded3DimStackImpl<E extends WithForm & WithStowLoc> implements Bounded3DimStack<E> {
    // ATTRIBUTES
    private final Class<E> genericClass;
    private final int bays;
    private final int rows;
    private final int tiers;
    private final List<List<List<E>>> stowage;
    
    // CREATION
    private Bounded3DimStackImpl(Class<E> genericClass, int bays, int rows, int tiers) {
        this.genericClass = genericClass;
        this.bays = bays;
        this.rows = rows;
        this.tiers = tiers;
        this.stowage = new ArrayList<>();
        try {
            initStowage();
        } catch (InvalidClassException ex) {
            System.out.println(Bounded3DimStackImpl.class.getName() + ": " + ex);
        }
    }
    
    public static Bounded3DimStackImpl valueOf(Class genericClass, int bays, int rows, int tiers) {
        return new Bounded3DimStackImpl(genericClass, bays, rows, tiers);
    }
    
    public int bays() {
        return this.bays;
    }
    
    public int rows() {
        return this.rows;
    }
    
    public int tiers() {
        return this.tiers;
    }
    
    // OPERATIONS
    private void initStowage() throws InvalidClassException {
        stowage.clear();
        for (int bay=0; bay<bays; bay++) {
            List<List<E>> rowList = new ArrayList<>();
            for (int row=0; row<rows; row++) {
                List<E> tierList = new ArrayList<>();
                for (int tier=0; tier<tiers; tier++) {
                    tierList.add(getNullObject());
                }
                rowList.add(tierList);
            }
            stowage.add(rowList);
        }
    }
    
    private E getNullObject() throws InvalidClassException {
        if (genericClass.equals(Container.class) ||
                genericClass.isInstance(Container.class))
            return (E)nullContainer();
        if (genericClass.equals(Pallet.class) ||
                genericClass.isInstance(Pallet.class))
            return (E)nullPallet();
        throw new InvalidClassException("Unknown type!");
    }
    
    @Override
    public boolean isEmpty() {
        for (int bay=0; bay<stowage.size(); bay++) {
            List<List<E>> rowList = stowage.get(bay);
            for (int row=0; row<rowList.size(); row++) {
                if (!tierIsEmpty(bay, row))
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean isFull() {
        for (int bay=0; bay<stowage.size(); bay++) {
            List<List<E>> rowList = stowage.get(bay);
            for (int row=0; row<rowList.size(); row++) {
                if (!tierIsFull(bay, row))
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean tierIsEmpty(int bay, int row) {
        List<E> tierList = stowage.get(bay).get(row);
        
        for (E elem : tierList) {
            if (elem.isOccupied())
                return false;
        }
        return true;
    }

    @Override
    public boolean tierIsFull(int bay, int row) {
        List<E> tierList = stowage.get(bay).get(row);
        Collections.reverse(tierList);
        
        for (E elem : tierList) {
            if (elem.isFree())
                return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object elem) {
        for (List<List<E>> rowList : stowage) {
            for (List<E> tierList : rowList) {
                if (tierList.contains(elem))
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        for (Object elem : coll) {
            if (!contains(elem))
                return false;
        }
        return true;
    }

    @Override
    public E get(StowageLocation loc) {
        return stowage.get(loc.bay()).get(loc.row()).get(loc.tier());
    }

    @Override
    public Set<E> getAll() {
        HashSet<E> result = new HashSet<>();
        
        for (List<List<E>> rowList : stowage) {
            for (List<E> tierList : rowList) {
                for (E elem : tierList) {
                    result.add(elem);
                }
            }
        }
        
        return result;
    }

    @Override
    public StowageLocation locationOf(E elem) {
        for (List<List<E>> rowList : stowage) {
            for (List<E> tierList : rowList) {
                if (tierList.contains(elem))
                    return elem.loc();
            }
        }
        return nullStowageLocation();
    }

    @Override
    public void load(int bayNo, int rowNo, E elem) {
        List<E> tierList = stowage.get(bayNo).get(rowNo);
        for (int i=0; i<tierList.size(); i++) {
            if (tierList.get(i).isFree()) {
                tierList.set(i, elem);
                return;
            }
        }
        throw new IllegalArgumentException("Tier of stowage is full! Object could not been loaded.");
    }

    @Override
    public void load(E elem) {
        for (List<List<E>> rowList : stowage) {
            for (List<E> tierList : rowList) {
                for (int i=0; i<tierList.size(); i++) {
                    if (tierList.get(i).isFree()) {
                        tierList.set(i, elem);
                        return;
                    }
                }
            }
        }
        throw new IllegalArgumentException("Stowage is full! Object could not been loaded.");
    }

    @Override
    public void loadAll(Collection<E> coll) {
        for (E elem : coll) {
            try {
                load(elem);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Stowage is full! Some objects could not been loaded.");
            }
        }
    }
}