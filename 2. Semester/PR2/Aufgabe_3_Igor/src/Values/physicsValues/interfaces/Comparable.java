package Values.physicsValues.interfaces;

public interface Comparable<Unit extends PhysicsScalar> {
    // OPERATIONS
    abstract public int compareTo(Unit value);
    
    default public boolean smallerThan(Unit value) {
        return this.compareTo(value) < 0;
    }
    default public boolean biggerThan(Unit value) {
        return this.compareTo(value) > 0;
    }
    default public boolean smallerThanOrEquals(Unit value) {
        return !biggerThan(value);
    }
    default public boolean biggerThanOrEquals(Unit value) {
        return !smallerThan(value);
    }
    default public boolean between(Unit min, Unit max) {
        return !(smallerThan(min) || biggerThan(max));
    }
}