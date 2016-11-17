package Values.adminValues;

import Values.adminValues.interfaces.StowageLocation;

public class NullStowageLocation implements StowageLocation {
    // CREATION (SINGLETON)
    private NullStowageLocation() {
        // ...
    }
    
    private static StowageLocation instance = null;
    public static StowageLocation valueOf() {
        if (instance == null)
            instance = new NullStowageLocation();
        
        return instance;
    }
    
    @Override
    public int bay() {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public int row() {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public int tier() {
        throw new UnsupportedOperationException("Not supported.");
    }
    
    @Override
    public boolean isNull() {
        return true;
    }
}