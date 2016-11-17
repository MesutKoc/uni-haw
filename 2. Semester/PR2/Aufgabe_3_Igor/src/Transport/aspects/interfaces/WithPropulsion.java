package Transport.aspects.interfaces;

import Values.physicsValues.interfaces.TimeDiff;

public interface WithPropulsion extends WithPower {
    // OPERATIONS
    public void moveStep(TimeDiff deltaTime);
}