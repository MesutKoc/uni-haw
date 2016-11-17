package units.enums;

public enum AccUnit implements BaseUnit {
	// CONSTANTS
	METERPERSECOND2(1.0),
	KILOMETREPERHOUR2(0.00007716049382716);

	// ATTRIBUTES
	private double multiplier;
	
	// CREATION
	private AccUnit(double multiplier) {
		this.multiplier = multiplier;
	}
	
	// SELECTORS
    @Override
    public double multiplier() {
        return multiplier;
    }
}
