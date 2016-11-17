package Stowage.interfaces;

import Utils.interfaces.Mutable;
import Values.adminValues.interfaces.StowageLocation;
import java.util.Collection;
import java.util.Set;

public interface Bounded3DimStack<E> extends Mutable {
    // PREDICATES
    public boolean isEmpty();
    public boolean isFull();
    public boolean tierIsEmpty(int bay, int row);
    public boolean tierIsFull(int bay, int row);
    public boolean contains(Object elem);
    public boolean containsAll(Collection<?> coll);
    
    // SELECTORS
    public E get(StowageLocation loc);
    public Set<E> getAll();
    public StowageLocation locationOf(E elem);
    
    // OPERATIONS
    public void load(int bayNo, int rowNo, E elem);
    public void load(E elem);
    public void loadAll(Collection<E> coll);
}
