package Stowage;

import Stowage.interfaces.Container;
import Stowage.interfaces.ContainerStowage;
import Stowage.interfaces.Pallet;

public final class Entities {
    // UTILITY CLASS
    private Entities() {
        // ...
    }
    
    // OPERATIONS
    public static Container container() {
        return ContainerImpl.valueOf();
    }
    
    public static Container nullContainer() {
        return NullContainer.valueOf();
    }
    
    public static Container nonContainer() {
        return NonContainer.valueOf();
    }
    
    public static Pallet pallet() {
        return null;
    }
    
    public static Pallet nullPallet() {
        return null;
    }
    
    public static Pallet nonPallet() {
        return null;
    }
    
    public static ContainerStowage containerStowage(int bays, int rows, int tiers) {
        return ContainerStowageImpl.valueOf(bays, rows, tiers);
    }
}