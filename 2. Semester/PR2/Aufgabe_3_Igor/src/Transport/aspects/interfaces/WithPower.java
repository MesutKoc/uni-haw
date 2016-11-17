package Transport.aspects.interfaces;

import Values.physicsValues.interfaces.Power;

public interface WithPower {
    // SELECTORS
    public Power power();
    public Power maxPower();
    public void setLevel(double level);
}