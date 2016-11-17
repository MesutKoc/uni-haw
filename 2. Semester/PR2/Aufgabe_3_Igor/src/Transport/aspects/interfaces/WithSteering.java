package Transport.aspects.interfaces;

import Values.physicsValues.interfaces.Angle;

public interface WithSteering {
    // SELECTORS
    public Angle steeringAngle();
    public Angle courseAngle();
    public void setSteeringAngle(Angle angle);
}