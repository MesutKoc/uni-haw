package Transport;

import Values.adminValues.interfaces.UniqueId;
import Values.physicsValues.interfaces.BoundingBox;
import Values.physicsValues.interfaces.Length;
import Values.physicsValues.interfaces.Mass;
import Values.physicsValues.interfaces.Power;
import Values.physicsValues.interfaces.Speed;
import Values.physicsValues.interfaces.TimeDiff;
import Transport.interfaces.Ship;
import Transport.interfaces.ShipEngine;
import Transport.interfaces.ShipHull;
import Transport.interfaces.Vehicle;

public class ShipImpl extends AbstractShip {
    // CREATION
    private ShipImpl(ShipHull hull, ShipEngine engine, Speed maxSpeed) {
        super(hull, engine, maxSpeed);
    }
    
    public static Ship valueOf(ShipHull hull, ShipEngine engine, Speed maxSpeed) {
        return new ShipImpl(hull, engine, maxSpeed);
    }
    
    // OPERATIONS
    @Override
    public Mass mass() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public BoundingBox boundingBox() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public UniqueId id() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Length pos() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Power power() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Power maxPower() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setLevel(double level) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void moveStep(TimeDiff deltaTime) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public int compareTo(Vehicle o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}