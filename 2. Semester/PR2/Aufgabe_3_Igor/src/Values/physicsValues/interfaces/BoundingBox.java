package Values.physicsValues.interfaces;

public interface BoundingBox extends PhysicsValue {
    // SELECTORS
    public Length length();
    public Length width();
    public Length height();
    
    // OPERATIONS
    boolean fitsInto(BoundingBox boundingBox);
}