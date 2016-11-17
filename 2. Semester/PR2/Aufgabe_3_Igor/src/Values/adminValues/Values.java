package Values.adminValues;

import Values.adminValues.interfaces.Name;
import Values.adminValues.interfaces.StowageLocation;
import Values.adminValues.interfaces.UniqueId;

public final class Values {
    // UTILITY CLASS
    private Values() {
        // ...
    }
    
    // OPERATIONS
    public static Name name(String nameString) {
        return NameImpl.valueOf(nameString);
    }
    
    public static UniqueId uniqueId() {
        return UniqueIdImpl.valueOf();
    }
    
    public static StowageLocation stowageLocation(int bay, int row, int tier) {
        return StowageLocationImpl.valueOf(bay, row, tier);
    }
    
    public static StowageLocation nullStowageLocation() {
        return NullStowageLocation.valueOf();
    }
}