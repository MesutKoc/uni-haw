package Values.adminValues;

import Values.adminValues.interfaces.UniqueId;

final class UniqueIdImpl implements UniqueId {
    // ATTRIBUTES
    private final long idNumber;
    private static long currentIdNumber = 0;
    
    // CREATION
    private UniqueIdImpl(long idNumber) {
        this.idNumber = idNumber;  
    }
    
    public static synchronized UniqueId valueOf() {
        UniqueId instance = new UniqueIdImpl(currentIdNumber);
        currentIdNumber++;
        return instance;
    }
    
    // SELECTORS
    @Override
    public long idNumber() {
        return this.idNumber;
    }
}