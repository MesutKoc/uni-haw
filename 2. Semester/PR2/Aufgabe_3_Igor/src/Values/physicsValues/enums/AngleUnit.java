package Values.physicsValues.enums;

public enum AngleUnit implements BaseUnit {
	// CONSTANTS
	RADIAN(1.0),
	DEGREES(0.017453292519943295),
	TURNS(6.283185307179586);

	// ATTRIBUTES
	private double multiplier;
	
	// CREATION
	private AngleUnit(double multiplier) {
		this.multiplier = multiplier;
	}
	
	// SELECTORS
    @Override
    public double multiplier() {
        return multiplier;
    }
}