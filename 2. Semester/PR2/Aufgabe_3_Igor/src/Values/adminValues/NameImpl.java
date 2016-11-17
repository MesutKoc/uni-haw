package Values.adminValues;

import Values.adminValues.interfaces.Name;

final class NameImpl implements Name {
    // ATTRIBUTES
    private final String nameString;
    
    // CREATION
    private NameImpl(String nameString) {
        this.nameString = nameString;      
    }
    
    public static Name valueOf(String nameString) {
        return new NameImpl(nameString);
    }
    
    // SELECTORS
    @Override
    public String nameString() {
        return this.nameString;
    }
}