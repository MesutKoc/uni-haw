package Stowage;

import Values.physicsValues.interfaces.BoundingBox;
import Transport.interfaces.Body;

public abstract class AbstractBody implements Body {
    // ATTRIBUTES
    private BoundingBox boundingBox;
    
    // OPERATIONS
    @Override
    public BoundingBox boundingBox() {
        return boundingBox;
    }
}
