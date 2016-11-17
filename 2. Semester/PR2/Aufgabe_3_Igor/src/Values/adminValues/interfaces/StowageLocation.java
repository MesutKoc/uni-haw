package Values.adminValues.interfaces;

public interface StowageLocation extends AdminValue {
    // SELECTORS
    public int bay();
    public int row();
    public int tier();
    
    // PREDICATES
    public boolean isNull();
}