package Values.physicsValues;

import Values.physicsValues.interfaces.BoundingBox;
import Values.physicsValues.interfaces.Length;

final class BoundingBoxImpl implements BoundingBox {
    // ATTRIBUTES
    private final Length length;
    private final Length width;
    private final Length height;
    
    // CREATION
    private BoundingBoxImpl(Length length, Length width, Length height) {
        this.length = length;
        this.width = width;
        this.height = height;        
    }
    
    public static BoundingBox valueOf(Length length, Length width, Length height) {
        return new BoundingBoxImpl(length, width, height);
    }
    
    // SELECTORS
    @Override
    public Length length() {
        return this.length;
    }

    @Override
    public Length width() {
        return this.width;
    }

    @Override
    public Length height() {
        return this.height;
    }
    
    // OPERATIONS
    @Override
    public boolean fitsInto(BoundingBox boundingBox) {
        int compLength = this.length().compareTo(boundingBox.length());
        int compWidth = this.width().compareTo(boundingBox.width());
        int compHeight = this.height().compareTo(boundingBox.height());
        return (compLength <= 0 && compWidth <= 0 && compHeight <= 0);
    }
}