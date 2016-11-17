package Values.adminValues;

import Values.adminValues.interfaces.StowageLocation;

final class StowageLocationImpl implements StowageLocation {
    // ATTRIBUTES
    private final int bay;
    private final int row;
    private final int tier;
    
    // CREATION
    private StowageLocationImpl(int bay, int row, int tier) {
        this.bay = bay;
        this.row = row;
        this.tier = tier;
    }
    
    public static StowageLocation valueOf(int bay, int row, int tier) {
        return new StowageLocationImpl(bay, row, tier);
    }
    
    // SELECTORS
    @Override
    public int bay() {
        return bay;
    }

    @Override
    public int row() {
        return row;
    }

    @Override
    public int tier() {
        return tier;
    }
    
    // PREDICATES
    @Override
    public boolean isNull() {
        return false;
    }
    
    // EQUIVALENCES
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.bay;
        hash = 47 * hash + this.row;
        hash = 47 * hash + this.tier;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StowageLocationImpl other = (StowageLocationImpl) obj;
        if (this.bay != other.bay) {
            return false;
        }
        if (this.row != other.row) {
            return false;
        }
        if (this.tier != other.tier) {
            return false;
        }
        return true;
    }
}