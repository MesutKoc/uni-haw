package Values.physicsValues.enums;

public enum TimeDiffUnit implements BaseUnit {
	// CONSTANTS
	SECOND(1.0),
	MILLISECOND(0.001),
	NANOSECOND(1.0E-9),
	MINUTE(60.0),
	HOUR(3600.0);

	// ATTRIBUTES
	private double multiplier;
	
	// CREATION
	private TimeDiffUnit(double multiplier) {
		this.multiplier = multiplier;
	}
	
	// SELECTORS
    @Override
    public double multiplier() {
        return multiplier;
    }
}